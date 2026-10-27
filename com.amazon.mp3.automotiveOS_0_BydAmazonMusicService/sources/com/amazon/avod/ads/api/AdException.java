package com.amazon.avod.ads.api;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdException extends Exception {
    public AdException() {
    }

    public AdException(String str, Throwable th) {
        super(str, th);
    }

    public AdException(String str) {
        super(str);
    }

    public AdException(Throwable th) {
        super(th);
    }
}
