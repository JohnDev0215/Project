package com.byd.systemsettings.wifi2.model;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class ScanResultParams extends BydData {
    public String capabilities;
    public CipherType cipherType;
    public String ssid;

    public ScanResultParams(String str, String str2, CipherType cipherType) {
        this.ssid = str;
        this.capabilities = str2;
        this.cipherType = cipherType;
    }

    public String toString() {
        return "ScanResultParams{ssid='" + this.ssid + "', capabilities='" + this.capabilities + "', cipherType=" + this.cipherType + '}';
    }
}
