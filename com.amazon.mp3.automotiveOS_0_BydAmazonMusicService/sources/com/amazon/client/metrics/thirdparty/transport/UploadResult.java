package com.amazon.client.metrics.thirdparty.transport;

/* JADX INFO: loaded from: classes2.dex */
public class UploadResult {
    private Exception mExceptionThrown;
    private int mResponseCode;
    private int mUploadStatus;

    public UploadResult(int i, int i2, Exception exc) {
        this.mUploadStatus = i;
        this.mResponseCode = i2;
        this.mExceptionThrown = exc;
    }

    public UploadResult(int i, Exception exc) {
        this(i, -1, exc);
    }

    public UploadResult(int i, int i2) {
        this(i, i2, null);
    }

    public UploadResult(int i) {
        this(i, -1, null);
    }

    public int getUploadStatus() {
        return this.mUploadStatus;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public Exception getExceptionThrown() {
        return this.mExceptionThrown;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UploadResult uploadResult = (UploadResult) obj;
        if (this.mUploadStatus != uploadResult.mUploadStatus || this.mResponseCode != uploadResult.mResponseCode) {
            return false;
        }
        Exception exc = this.mExceptionThrown;
        Exception exc2 = uploadResult.mExceptionThrown;
        return exc != null ? exc.equals(exc2) : exc2 == null;
    }

    public int hashCode() {
        int i = ((this.mUploadStatus * 31) + this.mResponseCode) * 31;
        Exception exc = this.mExceptionThrown;
        return i + (exc != null ? exc.hashCode() : 0);
    }

    public String toString() {
        return "UploadResult{mUploadStatus=" + this.mUploadStatus + ", mResponseCode=" + this.mResponseCode + ", mExceptionThrown=" + this.mExceptionThrown + '}';
    }
}
