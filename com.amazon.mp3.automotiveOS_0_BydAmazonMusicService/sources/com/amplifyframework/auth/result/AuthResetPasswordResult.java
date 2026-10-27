package com.amplifyframework.auth.result;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.result.step.AuthNextResetPasswordStep;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthResetPasswordResult {
    private final boolean isPasswordReset;
    private final AuthNextResetPasswordStep nextStep;

    public AuthResetPasswordResult(boolean z, @NonNull AuthNextResetPasswordStep authNextResetPasswordStep) {
        this.isPasswordReset = z;
        Objects.requireNonNull(authNextResetPasswordStep);
        this.nextStep = authNextResetPasswordStep;
    }

    public boolean isPasswordReset() {
        return this.isPasswordReset;
    }

    @NonNull
    public AuthNextResetPasswordStep getNextStep() {
        return this.nextStep;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(isPasswordReset()), getNextStep());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthResetPasswordResult.class != obj.getClass()) {
            return false;
        }
        AuthResetPasswordResult authResetPasswordResult = (AuthResetPasswordResult) obj;
        return ObjectsCompat.equals(Boolean.valueOf(isPasswordReset()), Boolean.valueOf(authResetPasswordResult.isPasswordReset())) && ObjectsCompat.equals(getNextStep(), authResetPasswordResult.getNextStep());
    }

    public String toString() {
        return "AuthResetPasswordResult{isPasswordReset=" + isPasswordReset() + ", nextStep=" + getNextStep() + '}';
    }
}
