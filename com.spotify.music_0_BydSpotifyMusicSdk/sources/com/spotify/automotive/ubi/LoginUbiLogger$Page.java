package com.spotify.automotive.ubi;

/* JADX INFO: loaded from: classes.dex */
public enum LoginUbiLogger$Page {
    QR_CODE("login-page-qr-code"),
    PIN("login-page-pin"),
    PASSWORD("login-page-password"),
    NEED_HELP("login-page-need-help");

    private final String id;

    LoginUbiLogger$Page(String str) {
        this.id = str;
    }

    public final String a() {
        return this.id;
    }
}
