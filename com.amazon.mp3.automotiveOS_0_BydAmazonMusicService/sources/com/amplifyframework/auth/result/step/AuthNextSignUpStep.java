package com.amplifyframework.auth.result.step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.AuthCodeDeliveryDetails;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthNextSignUpStep {
    private final Map<String, String> additionalInfo;
    private final AuthCodeDeliveryDetails codeDeliveryDetails;
    private final AuthSignUpStep signUpStep;

    public AuthNextSignUpStep(@NonNull AuthSignUpStep authSignUpStep, @NonNull Map<String, String> map, @Nullable AuthCodeDeliveryDetails authCodeDeliveryDetails) {
        Objects.requireNonNull(authSignUpStep);
        this.signUpStep = authSignUpStep;
        HashMap map2 = new HashMap();
        this.additionalInfo = map2;
        Objects.requireNonNull(map);
        map2.putAll(map);
        this.codeDeliveryDetails = authCodeDeliveryDetails;
    }

    @NonNull
    public AuthSignUpStep getSignUpStep() {
        return this.signUpStep;
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
        return ObjectsCompat.hash(getSignUpStep(), getAdditionalInfo(), getCodeDeliveryDetails());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthNextSignUpStep.class != obj.getClass()) {
            return false;
        }
        AuthNextSignUpStep authNextSignUpStep = (AuthNextSignUpStep) obj;
        return ObjectsCompat.equals(getSignUpStep(), authNextSignUpStep.getSignUpStep()) && ObjectsCompat.equals(getAdditionalInfo(), authNextSignUpStep.getAdditionalInfo()) && ObjectsCompat.equals(getCodeDeliveryDetails(), authNextSignUpStep.getCodeDeliveryDetails());
    }

    public String toString() {
        return "AuthNextSignUpStep{signUpStep=" + getSignUpStep() + ", additionalInfo=" + getAdditionalInfo() + ", codeDeliveryDetails=" + getCodeDeliveryDetails() + '}';
    }
}
