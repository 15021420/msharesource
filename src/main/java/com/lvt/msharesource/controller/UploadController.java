package com.lvt.msharesource.controller;

import com.lvt.msharesource.controller.vm.DataCheckHashVM;
import com.lvt.msharesource.dto.DataCheckHashDTO;
import com.lvt.msharesource.entity.GoogleDriveCredentials;
import com.lvt.msharesource.services.GoogleDriveCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private RestTemplate rst = new RestTemplate();

    private  URI uri = new URI("https://www.googleapis.com/upload/drive/v3/files?uploadType=resumable");


    @Autowired
    private GoogleDriveCredentialsService googleDriveCredentialsService;

    public UploadController() throws URISyntaxException {
    }

    @PostMapping("/check-hash")
    @ResponseBody
    public DataCheckHashDTO checkHash(@RequestBody DataCheckHashVM dataCheckHashVM) {
        GoogleDriveCredentials credentials = googleDriveCredentialsService.getListGoogleDriveCredentials().get(0);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("X-upload-content-length", dataCheckHashVM.getFileSize());
        httpHeaders.add("X-upload-content-type", dataCheckHashVM.getMimeType());
        httpHeaders.add("Authorization",  credentials.getToken());
        httpHeaders.add("key", credentials.getKey());

        HttpEntity<String> request = new HttpEntity<>("{\"uploadType\": \"resumable\"}", httpHeaders);

        ResponseEntity<String> result = rst.postForEntity(uri, request, String.class);

        return new DataCheckHashDTO(result.getHeaders().getLocation().toString());
    }
}
