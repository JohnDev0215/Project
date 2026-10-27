package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
enum PEMObjectType {
    PRIVATE_KEY_PKCS1("-----BEGIN RSA PRIVATE KEY-----"),
    PRIVATE_KEY_PKCS8("-----BEGIN PRIVATE KEY-----"),
    PUBLIC_KEY_X509("-----BEGIN PUBLIC KEY-----"),
    CERTIFICATE_X509("-----BEGIN CERTIFICATE-----");

    private final String beginMarker;

    public String getBeginMarker() {
        return this.beginMarker;
    }

    PEMObjectType(String str) {
        this.beginMarker = str;
    }

    public static PEMObjectType fromBeginMarker(String str) {
        for (PEMObjectType pEMObjectType : values()) {
            if (pEMObjectType.getBeginMarker().equals(str)) {
                return pEMObjectType;
            }
        }
        return null;
    }
}
