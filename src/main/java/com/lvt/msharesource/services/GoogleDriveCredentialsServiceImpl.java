package com.lvt.msharesource.services;

import com.lvt.msharesource.entity.GoogleDriveCredentials;
import com.lvt.msharesource.repository.GoogleDriveCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleDriveCredentialsServiceImpl implements GoogleDriveCredentialsService{

    @Autowired
    private GoogleDriveCredentialsRepository googleDriveCredentialsRepository;

    @Override
    public List<GoogleDriveCredentials> getListGoogleDriveCredentials() {
        return googleDriveCredentialsRepository.findAll();
    }

    @Override
    public GoogleDriveCredentials insertGoogleDriveCredentials(GoogleDriveCredentials record) {
        return googleDriveCredentialsRepository.save(record);
    }

    @Override
    public GoogleDriveCredentials updateGoogleDriveCredentials(GoogleDriveCredentials record) {

        GoogleDriveCredentials credentials = googleDriveCredentialsRepository.findById(record.getId());

        if (credentials == null)
            return null;
        return googleDriveCredentialsRepository.save(record);
    }
}
