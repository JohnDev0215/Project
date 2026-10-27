package com.amplifyframework.auth.result.step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.AuthCodeDeliveryDetails;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthNextSignInStep {
    private final Map<String, String> additionalInfo;
    private final AuthCodeDeliveryDetails codeDeliveryDetails;
    private final AuthSignInStep signInStep;

    public AuthNextSignInStep(@NonNull AuthSignInStep authSignInStep, @NonNull Map<String, String> map, @Nullable AuthCodeDeliveryDetails authCodeDeliveryDetails) {
        Objects.requireNonNull(authSignInStep);
        this.signInStep = authSignInStep;
        HashMap map2 = new HashMap();
        this.additionalInfo = map2;
        Objects.requireNonNull(map);
        map2.putAll(map);
        this.codeDeliveryDetails = authCodeDeliveryDetails;
    }

    @NonNull
    public AuthSignInStep getSignInStep() {
        return this.signInStep;
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
        return ObjectsCompat.hash(getSignInStep(), getAdditionalInfo(), getCodeDeliveryDetails());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthNextSignInStep.class != obj.getClass()) {
            return false;
        }
        AuthNextSignInStep authNextSignInStep = (AuthNextSignInStep) obj;
        return ObjectsCompat.equals(getSignInStep(), authNextSignInStep.getSignInStep()) && ObjectsCompat.equals(getAdditionalInfo(), authNextSignInStep.getAdditionalInfo()) && ObjectsCompat.equals(getCodeDeliveryDetails(), authNextSignInStep.getCodeDeliveryDetails());
    }

    public String toString() {
        return "AuthNextSignInStep{signInStep=" + getSignInStep() + ", additionalInfo=" + getAdditionalInfo() + ", codeDeliveryDetails=" + getCodeDeliveryDetails() + '}';
    }
}
