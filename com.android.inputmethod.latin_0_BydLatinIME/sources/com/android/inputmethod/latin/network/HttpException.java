package com.android.inputmethod.latin.network;

/* JADX INFO: loaded from: classes2.dex */
public class HttpException extends Exception {
    private final int mStatusCode;

    public HttpException(int statusCode) {
        super("Response Code: " + statusCode);
        this.mStatusCode = statusCode;
    }

    public int getHttpStatusCode() {
        return this.mStatusCode;
    }
}
