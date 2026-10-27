package com.amazon.minerva.client.thirdparty.transport;

/* JADX INFO: loaded from: classes3.dex */
public class UploadResult {
    private byte[] responsePayload;
    private String uploadMessage;
    private String uploadStatus;

    public UploadResult(String str, String str2) {
        this.uploadStatus = str;
        this.uploadMessage = str2;
    }

    public UploadResult(String str, String str2, byte[] bArr) {
        this.uploadStatus = str;
        this.uploadMessage = str2;
        this.responsePayload = bArr;
    }

    public String getUploadStatus() {
        return this.uploadStatus;
    }

    public String getUploadMessage() {
        return this.uploadMessage;
    }

    public byte[] getResponsePayload() {
        return this.responsePayload;
    }
}
