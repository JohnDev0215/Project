package com.amazon.weblab.mobile.service;

/* JADX INFO: loaded from: classes5.dex */
class UriTooLongException extends RuntimeException {
    private int uriLength;

    UriTooLongException(String str, int i) {
        super(str);
        this.uriLength = i;
    }

    public int getUriLength() {
        return this.uriLength;
    }
}
