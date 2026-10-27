package com.byd.systemsettings.wifi2.model;

import android.net.wifi.WifiInfo;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.util.WifiSecurityUtils;
import m4.d;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectedWifiInfo extends BydData {
    private String capabilities;
    private CipherType cipherType;
    private WifiInfo info;
    private String ipAddr;
    private String name;
    private String security = "";

    public String getCapabilities() {
        return this.capabilities;
    }

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

    public boolean isSecurityInvalid() {
        return this.cipherType == null || this.capabilities == null || this.security == null;
    }

    public void setCipherTypeAndSecurity(CipherType cipherType) {
        this.cipherType = cipherType;
        this.security = WifiSecurityUtils.toSecurity(cipherType);
    }

    public void setInfo(WifiInfo wifiInfo) {
        this.info = wifiInfo;
    }

    public void setIpAddr(int i8) {
        this.ipAddr = d.n(i8);
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSecurity(String str) {
        this.capabilities = str;
        this.security = WifiSecurityUtils.toSecurity(str);
        this.cipherType = CipherType.get(str);
    }

    public String toString() {
        return "ConnectedWifiInfo{, dn='" + d.f(this.name) + "', capabilities='" + this.capabilities + "', security='" + this.security + "', cipherType=" + this.cipherType + '}';
    }
}
