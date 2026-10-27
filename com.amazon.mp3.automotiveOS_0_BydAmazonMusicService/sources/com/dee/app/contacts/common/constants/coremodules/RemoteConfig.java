package com.dee.app.contacts.common.constants.coremodules;

/* JADX INFO: loaded from: classes5.dex */
public enum RemoteConfig {
    ACMS_HOST("ACMS.Endpoints.PFM", "https://alexa-comms-mobile-service.amazon.com");

    private final String defaultValue;
    private final String key;

    RemoteConfig(String str, String str2) {
        this.key = str;
        this.defaultValue = str2;
    }

    public String getKey() {
        return this.key;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }
}
