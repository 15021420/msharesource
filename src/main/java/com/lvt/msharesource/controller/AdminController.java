package com.lvt.msharesource.controller;

import com.lvt.msharesource.entity.GoogleDriveCredentials;
import com.lvt.msharesource.services.GoogleDriveCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
