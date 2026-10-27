package org.chromium.base.library_loader;

/* JADX INFO: loaded from: classes3.dex */
public class ProcessInitException extends Exception {
    private int mErrorCode;

    public ProcessInitException(int i) {
        this.mErrorCode = i;
    }

    public ProcessInitException(int i, Throwable th) {
        super(null, th);
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }
}
