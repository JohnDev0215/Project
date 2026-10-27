package com.amplifyframework.auth.result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.result.step.AuthNextSignUpStep;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthSignUpResult {
    private final boolean isSignUpComplete;
    private final AuthNextSignUpStep nextStep;
    private final AuthUser user;

    public AuthSignUpResult(boolean z, @NonNull AuthNextSignUpStep authNextSignUpStep, @Nullable AuthUser authUser) {
        this.isSignUpComplete = z;
        Objects.requireNonNull(authNextSignUpStep);
        this.nextStep = authNextSignUpStep;
        this.user = authUser;
    }

    public boolean isSignUpComplete() {
        return this.isSignUpComplete;
    }

    @NonNull
    public AuthNextSignUpStep getNextStep() {
        return this.nextStep;
    }

    @Nullable
    public AuthUser getUser() {
        return this.user;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(isSignUpComplete()), getNextStep(), getUser());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthSignUpResult.class != obj.getClass()) {
            return false;
        }
        AuthSignUpResult authSignUpResult = (AuthSignUpResult) obj;
        return ObjectsCompat.equals(Boolean.valueOf(isSignUpComplete()), Boolean.valueOf(authSignUpResult.isSignUpComplete())) && ObjectsCompat.equals(getNextStep(), authSignUpResult.getNextStep()) && ObjectsCompat.equals(getUser(), authSignUpResult.getUser());
    }

    public String toString() {
        return "AuthSignUpResult{isSignUpComplete=" + isSignUpComplete() + ", nextStep=" + getNextStep() + ", user=" + getUser() + '}';
    }
}
