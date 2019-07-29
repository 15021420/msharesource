package com.lvt.msharesource.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "google_drive_credentials")
public class GoogleDriveCredentials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String authorizationCode;

    @Column
    private String refreshToken;

    @Column
    private String token;

    public GoogleDriveCredentials() {}

    public GoogleDriveCredentials(int id, String authorizationCode, String refreshToken, String token) {
        this.id = id;
        this.authorizationCode = authorizationCode;
        this.refreshToken = refreshToken;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
