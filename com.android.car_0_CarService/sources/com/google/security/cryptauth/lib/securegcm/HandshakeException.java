package com.google.security.cryptauth.lib.securegcm;

/* JADX INFO: loaded from: classes3.dex */
public class HandshakeException extends Exception {
    public HandshakeException(String message) {
        super(message);
    }

    public HandshakeException(Exception e) {
        super(e);
    }

    public HandshakeException(String message, Exception e) {
        super(message, e);
    }
}
