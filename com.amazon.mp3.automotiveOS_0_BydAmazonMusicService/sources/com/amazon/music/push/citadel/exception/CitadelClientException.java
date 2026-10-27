package com.amazon.music.push.citadel.exception;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelClientException extends RuntimeException {
    public CitadelClientException() {
    }

    public CitadelClientException(Exception exc) {
        super(exc.getMessage(), exc.getCause());
    }

    public CitadelClientException(String str) {
        super(str);
    }
}
