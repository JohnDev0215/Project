package com.amplifyframework.auth.result.step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.AuthCodeDeliveryDetails;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthNextResetPasswordStep {
    private final Map<String, String> additionalInfo;
    private final AuthCodeDeliveryDetails codeDeliveryDetails;
    private final AuthResetPasswordStep resetPasswordStep;

    public AuthNextResetPasswordStep(@NonNull AuthResetPasswordStep authResetPasswordStep, @NonNull Map<String, String> map, @Nullable AuthCodeDeliveryDetails authCodeDeliveryDetails) {
        Objects.requireNonNull(authResetPasswordStep);
        this.resetPasswordStep = authResetPasswordStep;
        HashMap map2 = new HashMap();
        this.additionalInfo = map2;
        Objects.requireNonNull(map);
        map2.putAll(map);
        this.codeDeliveryDetails = authCodeDeliveryDetails;
    }

    @NonNull
    public AuthResetPasswordStep getResetPasswordStep() {
        return this.resetPasswordStep;
    }

    @Nullable
    public Map<String, String> getAdditionalInfo() {
        return this.additionalInfo;
    }

    @Nullable
    public AuthCodeDeliveryDetails getCodeDeliveryDetails() {
        return this.codeDeliveryDetails;
    }

    public int hashCode() {
        return ObjectsCompat.hash(getResetPasswordStep(), getAdditionalInfo(), getCodeDeliveryDetails());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthNextResetPasswordStep.class != obj.getClass()) {
            return false;
        }
        AuthNextResetPasswordStep authNextResetPasswordStep = (AuthNextResetPasswordStep) obj;
        return ObjectsCompat.equals(getResetPasswordStep(), authNextResetPasswordStep.getResetPasswordStep()) && ObjectsCompat.equals(getAdditionalInfo(), authNextResetPasswordStep.getAdditionalInfo()) && ObjectsCompat.equals(getCodeDeliveryDetails(), authNextResetPasswordStep.getCodeDeliveryDetails());
    }

    public String toString() {
        return "AuthNextResetPasswordStep{resetPasswordStep=" + getResetPasswordStep() + ", additionalInfo=" + getAdditionalInfo() + ", codeDeliveryDetails=" + getCodeDeliveryDetails() + '}';
    }
}
