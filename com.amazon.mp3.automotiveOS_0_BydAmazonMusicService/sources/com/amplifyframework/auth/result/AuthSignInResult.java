package com.amplifyframework.auth.result;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.result.step.AuthNextSignInStep;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthSignInResult {
    private final boolean isSignInComplete;
    private final AuthNextSignInStep nextStep;

    public AuthSignInResult(boolean z, @NonNull AuthNextSignInStep authNextSignInStep) {
        this.isSignInComplete = z;
        Objects.requireNonNull(authNextSignInStep);
        this.nextStep = authNextSignInStep;
    }

    public boolean isSignInComplete() {
        return this.isSignInComplete;
    }

    @NonNull
    public AuthNextSignInStep getNextStep() {
        return this.nextStep;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(isSignInComplete()), getNextStep());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthSignInResult.class != obj.getClass()) {
            return false;
        }
        AuthSignInResult authSignInResult = (AuthSignInResult) obj;
        return ObjectsCompat.equals(Boolean.valueOf(isSignInComplete()), Boolean.valueOf(authSignInResult.isSignInComplete())) && ObjectsCompat.equals(getNextStep(), authSignInResult.getNextStep());
    }

    public String toString() {
        return "AuthSignInResult{isSignInComplete=" + isSignInComplete() + ", nextStep=" + getNextStep() + '}';
    }
}
