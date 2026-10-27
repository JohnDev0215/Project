package com.amplifyframework.auth.result;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.result.step.AuthNextUpdateAttributeStep;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthUpdateAttributeResult {
    private final boolean isUpdated;
    private final AuthNextUpdateAttributeStep nextStep;

    public AuthUpdateAttributeResult(boolean z, @NonNull AuthNextUpdateAttributeStep authNextUpdateAttributeStep) {
        this.isUpdated = z;
        Objects.requireNonNull(authNextUpdateAttributeStep);
        this.nextStep = authNextUpdateAttributeStep;
    }

    public boolean isUpdated() {
        return this.isUpdated;
    }

    @NonNull
    public AuthNextUpdateAttributeStep getNextStep() {
        return this.nextStep;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(isUpdated()), getNextStep());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthUpdateAttributeResult.class != obj.getClass()) {
            return false;
        }
        AuthUpdateAttributeResult authUpdateAttributeResult = (AuthUpdateAttributeResult) obj;
        return ObjectsCompat.equals(Boolean.valueOf(isUpdated()), Boolean.valueOf(authUpdateAttributeResult.isUpdated())) && ObjectsCompat.equals(getNextStep(), authUpdateAttributeResult.getNextStep());
    }

    public String toString() {
        return "AuthUpdateAttributeResult{isUpdated=" + isUpdated() + ", nextStep=" + getNextStep() + '}';
    }
}
