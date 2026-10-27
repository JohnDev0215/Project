package com.byd.systemsettings.wifi2.model;

/* JADX INFO: loaded from: classes2.dex */
public enum CipherType {
    UNKNOW("UNKNOW"),
    OPEN("OPEN"),
    WEP("WEP"),
    WPA("WPA"),
    WPA2("WPA2"),
    WPA3_PERSONAL("WPA3_PERSONAL"),
    WPA3("WPA3"),
    SAE("SAE"),
    EAP("EAP");

    private final String value;

    CipherType(String str) {
        this.value = str;
    }

    public static CipherType get(String str) {
        return (str == null || str.length() <= 0) ? OPEN : (str.contains("WPA") || str.contains("wpa")) ? (str.contains("WPA3") || str.contains("wpa3")) ? WPA3 : (str.contains("WPA2") && str.contains("+SAE")) ? WPA3_PERSONAL : (str.contains("WPA2") || str.contains("wpa2")) ? WPA2 : WPA : (str.contains("SAE") || str.contains("sae")) ? SAE : (str.contains("WEP") || str.contains("wep")) ? WEP : (str.contains("EAP") || str.contains("eap")) ? EAP : (str.contains("ESS") || str.contains("ess")) ? OPEN : UNKNOW;
    }

    public static CipherType getType(String str) {
        for (CipherType cipherType : values()) {
            if (cipherType.name().equals(str)) {
                return cipherType;
            }
        }
        return UNKNOW;
    }

    public boolean isSecurity() {
        return this == WPA || this == WPA2 || this == WPA3 || this == WPA3_PERSONAL || this == SAE || this == EAP;
    }
}
