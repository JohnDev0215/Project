package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum AuthenticationType {
    None("None", false),
    DeviceAuthenticator("DeviceAuthenticator", false),
    ADPAuthenticator("ADPAuthenticator", false),
    OAuth("OAuth", true);

    private final boolean mRequireHttps;
    private final String mValue;

    AuthenticationType(String str, boolean z) {
        this.mValue = str;
        this.mRequireHttps = z;
    }

    @FireOsSdk
    public static AuthenticationType parse(String str) {
        if (str == null) {
            return null;
        }
        AuthenticationType[] authenticationTypeArrValues = values();
        for (int i = 0; i < 4; i++) {
            AuthenticationType authenticationType = authenticationTypeArrValues[i];
            if (str.equals(authenticationType.getValue())) {
                return authenticationType;
            }
        }
        return null;
    }

    @FireOsSdk
    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public boolean requireHttps() {
        return this.mRequireHttps;
    }
}
