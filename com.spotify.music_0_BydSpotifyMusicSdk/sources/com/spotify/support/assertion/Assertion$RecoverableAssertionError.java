package com.spotify.support.assertion;

/* JADX INFO: loaded from: classes.dex */
public class Assertion$RecoverableAssertionError extends RuntimeException {
    private static final long serialVersionUID = 1;
    private final String mDetails;

    public Assertion$RecoverableAssertionError(String str, String str2) {
        super(str);
        this.mDetails = str2;
    }

    public Assertion$RecoverableAssertionError(String str, Throwable th) {
        super(str, th);
        this.mDetails = null;
    }
}
