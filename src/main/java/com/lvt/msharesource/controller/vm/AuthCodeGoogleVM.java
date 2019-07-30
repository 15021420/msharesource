package com.lvt.msharesource.controller.vm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthCodeGoogleVM implements Serializable {

    @JsonProperty("authorization_code")
    private String authorizationCode;

    public AuthCodeGoogleVM() {
    }

    public AuthCodeGoogleVM(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
