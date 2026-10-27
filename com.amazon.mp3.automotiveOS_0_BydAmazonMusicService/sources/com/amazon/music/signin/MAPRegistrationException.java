package com.amazon.music.signin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class MAPRegistrationException extends Exception {
    private final int errorCode;
    private final String errorMessage;
    private final String mapAccountManagerDetailedErrorType;
    private final MAPApiName mapApiName;

    public MAPRegistrationException(@Nullable int i, @Nullable String str, @NonNull String str2, @NonNull MAPApiName mAPApiName) {
        this.errorCode = i;
        this.errorMessage = str;
        this.mapAccountManagerDetailedErrorType = str2;
        this.mapApiName = mAPApiName;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getMapAccountManagerDetailedErrorType() {
        return this.mapAccountManagerDetailedErrorType;
    }

    public MAPApiName getMAPApiName() {
        return this.mapApiName;
    }
}
