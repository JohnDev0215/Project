package com.amazon.music.skyfire.core.network;

/* JADX INFO: loaded from: classes4.dex */
public class ValidationResult {
    private final Exception exception;
    private final boolean isSuccess;

    public ValidationResult(boolean z, Exception exc) {
        this.isSuccess = z;
        this.exception = exc;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public Exception getException() {
        return this.exception;
    }
}
