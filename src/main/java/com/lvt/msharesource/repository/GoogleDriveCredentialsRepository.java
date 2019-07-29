package com.lvt.msharesource.repository;

import com.lvt.msharesource.entity.GoogleDriveCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleDriveCredentialsRepository extends JpaRepository<GoogleDriveCredentials, Integer> {
    GoogleDriveCredentials findById(int id);
}
