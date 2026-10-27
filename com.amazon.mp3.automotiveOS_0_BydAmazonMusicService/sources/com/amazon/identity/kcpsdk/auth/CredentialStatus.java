package com.amazon.identity.kcpsdk.auth;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum CredentialStatus {
    CredentialStatusOK(0),
    CredentialStatusStale(1),
    CredentialStatusInvalid(2),
    CredentialStatusUnknownWarning(3),
    CredentialStatusUnknownError(4);

    private final int mValue;

    CredentialStatus(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
