package com.amazon.identity.auth.device.api;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum RegistrationType {
    WITH_LOGIN_CREDENTIALS("LoginCredentials"),
    WITH_DEVICE_SECRET("DeviceSecret"),
    WITH_EXPLICIT_URL("ExplicitUrl"),
    FROM_ATMAIN("ATMain"),
    FROM_AUTH_TOKEN("AuthToken"),
    FROM_ACCESS_TOKEN("AccessToken"),
    FROM_ADP_TOKEN("AdpToken"),
    REGISTER_DELEGATED_ACCOUNT("RegisterDelegatedAccount"),
    WITH_DIRECTEDID_CREDENTIALS("DirectedIdCredentials"),
    WITH_PRIMARY_DIRECTEDID_CREDENTIALS("PrimaryDirectedIdCredentials"),
    WITH_LINK_CODE("LinkCode"),
    WITH_SSO_CODE("SSOCode"),
    ANONYMOUS("Anonymous"),
    FROM_AUTHORIZATION_CODE("AuthorizationCode");

    private final String mName;

    RegistrationType(String str) {
        this.mName = str;
    }

    public static RegistrationType fromName(String str) {
        RegistrationType[] registrationTypeArrValues = values();
        for (int i = 0; i < 14; i++) {
            RegistrationType registrationType = registrationTypeArrValues[i];
            if (registrationType.getName().equals(str)) {
                return registrationType;
            }
        }
        return null;
    }

    public String getName() {
        return this.mName;
    }
}
