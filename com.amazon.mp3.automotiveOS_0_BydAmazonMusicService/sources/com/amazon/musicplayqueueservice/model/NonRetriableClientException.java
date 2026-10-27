package com.amazon.musicplayqueueservice.model;

/* JADX INFO: loaded from: classes5.dex */
public class NonRetriableClientException extends RuntimeException {
    public NonRetriableClientException() {
    }

    public NonRetriableClientException(Throwable th) {
        initCause(th);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }
}
