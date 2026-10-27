package com.byd.logtools.auth.data;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class QrCodeResponse implements Serializable {
    private int code;
    private a data;
    private String message;

    private static class a {
    }

    public QrCodeResponse(int i, String str, a aVar) {
        this.code = i;
        this.message = str;
    }

    public int getCode() {
        return this.code;
    }

    public a getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(a aVar) {
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "QrCodeResponse{code=" + this.code + ", message='" + this.message + "', data=" + this.data + '}';
    }
}
