package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public class IonException extends RuntimeException {
    public IonException() {
    }

    public IonException(String str) {
        super(str);
    }

    public IonException(String str, Throwable th) {
        super(str, th);
    }

    public IonException(Throwable th) {
        super(th.getMessage(), th);
    }
}
