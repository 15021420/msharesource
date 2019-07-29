package com.lvt.msharesource.controller.vm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DataCheckHashVM implements Serializable {

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_size")
    private String fileSize;

    @JsonProperty("mime_type")
    private String mimeType;

    public DataCheckHashVM() {
    }

    public DataCheckHashVM(String fileName, String fileSize, String mimeType) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
