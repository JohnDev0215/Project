package com.google.firebase.installations;

import androidx.annotation.NonNull;
import com.google.firebase.FirebaseException;

/* JADX INFO: loaded from: classes6.dex */
public class FirebaseInstallationsException extends FirebaseException {

    @NonNull
    private final Status status;

    public enum Status {
        BAD_CONFIG,
        UNAVAILABLE,
        TOO_MANY_REQUESTS
    }

    public FirebaseInstallationsException(@NonNull Status status) {
        this.status = status;
    }

    public FirebaseInstallationsException(@NonNull String str, @NonNull Status status) {
        super(str);
        this.status = status;
    }
}
