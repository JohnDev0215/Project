package com.amazon.avod.media.downloadservice.exceptions;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidCDNResponseException extends Exception {
    public InvalidCDNResponseException(String str) {
        this(str, null);
    }

    public InvalidCDNResponseException(String str, Throwable th) {
        super(str, th);
    }
}
