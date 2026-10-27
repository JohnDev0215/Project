package com.byd.vrsettings.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyPolicyEvent {
    private String type;
    private String value;

    public PrivacyPolicyEvent(String str, String str2) {
        this.type = str;
        this.value = str2;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
}
