package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
public class TxwException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public TxwException(String str) {
        super(str);
    }

    public TxwException(Throwable th) {
        super(th);
    }

    public TxwException(String str, Throwable th) {
        super(str, th);
    }
}
