package com.amplifyframework.auth.result.step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.auth.AuthCodeDeliveryDetails;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthNextUpdateAttributeStep {
    private final Map<String, String> additionalInfo;
    private final AuthCodeDeliveryDetails codeDeliveryDetails;
    private final AuthUpdateAttributeStep updateAttributeStep;

    public AuthNextUpdateAttributeStep(@NonNull AuthUpdateAttributeStep authUpdateAttributeStep, @NonNull Map<String, String> map, @Nullable AuthCodeDeliveryDetails authCodeDeliveryDetails) {
        Objects.requireNonNull(authUpdateAttributeStep);
        this.updateAttributeStep = authUpdateAttributeStep;
        HashMap map2 = new HashMap();
        this.additionalInfo = map2;
        Objects.requireNonNull(map);
        map2.putAll(map);
        this.codeDeliveryDetails = authCodeDeliveryDetails;
    }

    @NonNull
    public AuthUpdateAttributeStep getUpdateAttributeStep() {
        return this.updateAttributeStep;
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
        return ObjectsCompat.hash(getUpdateAttributeStep(), getAdditionalInfo(), getCodeDeliveryDetails());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthNextUpdateAttributeStep.class != obj.getClass()) {
            return false;
        }
        AuthNextUpdateAttributeStep authNextUpdateAttributeStep = (AuthNextUpdateAttributeStep) obj;
        return ObjectsCompat.equals(getUpdateAttributeStep(), authNextUpdateAttributeStep.getUpdateAttributeStep()) && ObjectsCompat.equals(getAdditionalInfo(), authNextUpdateAttributeStep.getAdditionalInfo()) && ObjectsCompat.equals(getCodeDeliveryDetails(), authNextUpdateAttributeStep.getCodeDeliveryDetails());
    }

    public String toString() {
        return "AuthNextUpdateAttributeStep{updateAttributeStep=" + getUpdateAttributeStep() + ", additionalInfo=" + getAdditionalInfo() + ", codeDeliveryDetails=" + getCodeDeliveryDetails() + '}';
    }
}
