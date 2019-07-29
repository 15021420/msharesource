package com.lvt.msharesource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DataCheckHashDTO implements Serializable {

    @JsonProperty("url_upload")
    private String urlUpload;

    public DataCheckHashDTO() {
    }

    public DataCheckHashDTO(String urlUpload) {
        this.urlUpload = urlUpload;
    }

    public String getUrlUpload() {
        return urlUpload;
    }

    public void setUrlUpload(String urlUpload) {
        this.urlUpload = urlUpload;
    }
}
