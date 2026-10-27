package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public class UnsupportedIonVersionException extends IonException {
    private final String _unsupportedIonVersionId;

    public UnsupportedIonVersionException(String str) {
        this._unsupportedIonVersionId = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Unsupported Ion version " + this._unsupportedIonVersionId;
    }
}
