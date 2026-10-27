package com.amazon.mp3.upsellweb;

import android.util.Base64;
import androidx.core.os.EnvironmentCompat;

/* JADX INFO: loaded from: classes4.dex */
public enum OfferPageRedirectPath {
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
    SUCCESS("/success"),
    SUCCESS_LISTEN("/success_listen"),
    NOT_NOW("/not_now"),
    HOME("/home");

    private final String mType;

    OfferPageRedirectPath(final String type) {
        this.mType = type;
    }

    String getType() {
        return this.mType;
    }

    String getBase64EncodedType() {
        return Base64.encodeToString(this.mType.getBytes(), 0).substring(0, r0.length() - 1);
    }

    public static OfferPageRedirectPath fromString(final String value) {
        for (OfferPageRedirectPath offerPageRedirectPath : values()) {
            String type = offerPageRedirectPath.getType();
            if (value != null && value.contains(type)) {
                return offerPageRedirectPath;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mType;
    }
}
