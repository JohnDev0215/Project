package com.byd.vrsettings.utils;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyPolicyModel implements Serializable {
    private String isAgree;
    private String time;

    public PrivacyPolicyModel(String str, String str2) {
        this.isAgree = str;
        this.time = str2;
    }

    public String getIsAgree() {
        return this.isAgree;
    }

    public void setIsAgree(String str) {
        this.isAgree = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
