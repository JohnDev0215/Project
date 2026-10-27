package com.dee.app.contacts.common.exceptions;

/* JADX INFO: loaded from: classes5.dex */
public class AuthException extends Exception {
    public AuthException(Throwable th) {
        super(th + "while making api call from contacts sdk");
    }
}
