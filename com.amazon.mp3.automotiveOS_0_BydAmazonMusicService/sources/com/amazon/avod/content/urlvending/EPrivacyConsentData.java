package com.amazon.avod.content.urlvending;

import com.google.common.base.Objects;
import java.io.Serializable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class EPrivacyConsentData implements Serializable {
    public static final EPrivacyConsentData EMPTY = new EPrivacyConsentData("", "");

    @Nullable
    private final String mEPrivacyConsentAVL;

    @Nullable
    private final String mEPrivacyConsentGVL;

    public EPrivacyConsentData(@Nullable String str, @Nullable String str2) {
        this.mEPrivacyConsentGVL = str;
        this.mEPrivacyConsentAVL = str2;
    }

    @Nullable
    public String getEPrivacyConsentGVL() {
        return this.mEPrivacyConsentGVL;
    }

    @Nullable
    public String getEPrivacyConsentAVL() {
        return this.mEPrivacyConsentAVL;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EPrivacyConsentData)) {
            return false;
        }
        EPrivacyConsentData ePrivacyConsentData = (EPrivacyConsentData) obj;
        return Objects.equal(getEPrivacyConsentGVL(), ePrivacyConsentData.getEPrivacyConsentGVL()) && Objects.equal(getEPrivacyConsentAVL(), ePrivacyConsentData.getEPrivacyConsentAVL());
    }

    public int hashCode() {
        return Objects.hashCode(getEPrivacyConsentGVL(), getEPrivacyConsentAVL());
    }
}
