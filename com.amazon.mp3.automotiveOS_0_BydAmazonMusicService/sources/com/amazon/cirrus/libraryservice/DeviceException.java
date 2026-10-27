package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceException extends RuntimeException {
    private static final long serialVersionUID = -1;
    private String code;

    public static class Builder {
    }

    public DeviceException() {
    }

    public DeviceException(String str) {
        super(str);
    }

    public DeviceException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DeviceException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public void setCode(String str) {
        this.code = str;
    }
}
