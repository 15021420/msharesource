package com.lvt.msharesource.services;

import com.lvt.msharesource.entity.GoogleDriveCredentials;

import java.util.List;

public interface GoogleDriveCredentialsService {
     List<GoogleDriveCredentials> getListGoogleDriveCredentials();
     GoogleDriveCredentials insertGoogleDriveCredentials(GoogleDriveCredentials record);
     GoogleDriveCredentials updateGoogleDriveCredentials(GoogleDriveCredentials record);
     String refreshTokenGoogle();
}
