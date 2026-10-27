package com.byd.systemsettings.wifi2.model;

import android.net.wifi.WifiInfo;
import b.b.m.g.f;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.util.WifiSecurityUtils;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedWifiInfo extends BydData {
    private CipherType cipherType;
    private WifiInfo info;
    private String ipAddr;
    private String name;
    private String security = "";

    public CipherType getCipherType() {
        return this.cipherType;
    }

    public WifiInfo getInfo() {
        return this.info;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }

    public String getName() {
        return this.name;
    }

    public String getSecurity() {
        return this.security;
    }

    public void setInfo(WifiInfo wifiInfo) {
        this.info = wifiInfo;
    }

    public void setIpAddr(int i) {
        this.ipAddr = f.m(i);
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSecurity(String str) {
        this.security = WifiSecurityUtils.toSecurity(str);
        this.cipherType = CipherType.get(str);
    }

    public String toString() {
        return "ConnectedWifiInfo{, dn='" + f.g(this.name) + "', security='" + this.security + "', cipherType=" + this.cipherType + '}';
    }
}
