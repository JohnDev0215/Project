package com.amazon.cirrus.libraryservice;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class StreamingDeviceNotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = -1;
    private List<Object> streamingDevices;

    public static class Builder {
    }

    public StreamingDeviceNotAuthorizedException() {
    }

    public StreamingDeviceNotAuthorizedException(String str) {
        super(str);
    }

    public StreamingDeviceNotAuthorizedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public StreamingDeviceNotAuthorizedException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public List<Object> getStreamingDevices() {
        return this.streamingDevices;
    }

    public void setStreamingDevices(List<Object> list) {
        this.streamingDevices = list;
    }
}
