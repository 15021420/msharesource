package com.lvt.msharesource.controller;

import com.lvt.msharesource.controller.vm.AuthCodeGoogleVM;
import com.lvt.msharesource.entity.GoogleDriveCredentials;
import com.lvt.msharesource.services.GoogleDriveCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private GoogleDriveCredentialsService credentialsService;

    @GetMapping
    public String defaultPage(Model model) {
        List<GoogleDriveCredentials> listCredentials = credentialsService.getListGoogleDriveCredentials();
        if (listCredentials.size() != 0) {
            model.addAttribute("currentCredentials", listCredentials.get(0));
        } else {
            model.addAttribute("currentCredentials", new GoogleDriveCredentials());
        }
        return "admin_home";
    }

    @PostMapping
    public String updateConfigSetting(@ModelAttribute GoogleDriveCredentials currentCredentials) {
        credentialsService.insertGoogleDriveCredentials(currentCredentials);
        return "redirect:/admin";
    }

    @GetMapping("/google-login")
    public String googleLogin() {
        return "google_login";
    }

    @PostMapping("/save-config-credentials")
    @ResponseBody
    public ResponseEntity<String> googleLogin(@RequestBody GoogleDriveCredentials googleDriveCredentials) {
        List<GoogleDriveCredentials> credentialsList = credentialsService.getListGoogleDriveCredentials();
        System.out.println(googleDriveCredentials.getTimeExpiredToken());
        GoogleDriveCredentials googleDriveCredentialsData;
        if (credentialsList.size() == 0) {
            googleDriveCredentialsData = new GoogleDriveCredentials(
                    googleDriveCredentials.getAuthorizationCode(),
                    googleDriveCredentials.getRefreshToken(),
                    googleDriveCredentials.getToken(),
                    googleDriveCredentials.getKey(),
                    googleDriveCredentials.getTimeExpiredToken());
        } else {
            GoogleDriveCredentials currentCredentials = credentialsList.get(0);
            googleDriveCredentialsData = new GoogleDriveCredentials();
            googleDriveCredentialsData.setId(currentCredentials.getId());
            googleDriveCredentialsData.setKey(currentCredentials.getKey());
            googleDriveCredentialsData.setAuthorizationCode(googleDriveCredentials.getAuthorizationCode());
            googleDriveCredentialsData.setRefreshToken(googleDriveCredentials.getRefreshToken());
            googleDriveCredentialsData.setToken(googleDriveCredentials.getToken());
            googleDriveCredentialsData.setTimeExpiredToken(googleDriveCredentials.getTimeExpiredToken());
        }

        try {
            credentialsService.insertGoogleDriveCredentials(googleDriveCredentialsData);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/store-google-auth-code")
    @ResponseBody
    public String storeAuthCode(@RequestBody AuthCodeGoogleVM authCodeGoogleVM) {
        return authCodeGoogleVM.getAuthorizationCode();
    }

}
