package com.lvt.msharesource.scheduler;

import com.lvt.msharesource.entity.GoogleDriveCredentials;
import com.lvt.msharesource.services.GoogleDriveCredentialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SchedulerTask {

    private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private RestTemplate restTemplate = new RestTemplate();

    private URI uri = new URI("https://www.googleapis.com/oauth2/v4/token");

    @Value("${google.credentials.client_id}")
    private String clientId;

    @Value("${google.credentials.client_secret}")
    private String clientSecret;

    @Autowired
    private GoogleDriveCredentialsService googleDriveCredentialsService;

    public SchedulerTask() throws URISyntaxException {
    }

    @Scheduled(fixedRate = 3000)
    public void refreshTokenGoogle() {
        List<GoogleDriveCredentials> googleDriveCredentialsList = googleDriveCredentialsService.getListGoogleDriveCredentials();

        if (!googleDriveCredentialsList.isEmpty()) {
            GoogleDriveCredentials googleDriveCredentials = googleDriveCredentialsList.get(0);

            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            httpHeaders.add("Content-length", "163");

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

            map.add("client_secret", clientSecret);
            map.add("grant_type", "refresh_token");
            map.add("refresh_token", googleDriveCredentials.getRefreshToken());
            map.add("client_id", clientId);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map , httpHeaders);

            ResponseEntity<String> response = restTemplate.postForEntity( uri, request , String.class );

            if (response.getStatusCode().is2xxSuccessful()) {

            }
        }
    }

}
