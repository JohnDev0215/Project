package com.amazon.avod.media.events.clientapi;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidRequestException extends Exception {

    @Nullable
    private Object mResponseObject;

    public InvalidRequestException(String str, Throwable th, @Nullable Object obj) {
        super(str, th);
        this.mResponseObject = obj;
    }

    @Nullable
    public Object getResponseObject() {
        return this.mResponseObject;
    }
}
