package com.amazon.avod.media.downloadservice.exceptions;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkResponseException extends Exception {
    private final int mResponseCode;
    private final String mResponseSource;

    public NetworkResponseException(String str, int i, String str2) {
        this(str, null, i, str2);
    }

    public NetworkResponseException(String str, Throwable th, int i, @Nullable String str2) {
        super(str, th);
        this.mResponseCode = i;
        this.mResponseSource = str2;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    @Nullable
    public String getResponseSource() {
        return this.mResponseSource;
    }
}
