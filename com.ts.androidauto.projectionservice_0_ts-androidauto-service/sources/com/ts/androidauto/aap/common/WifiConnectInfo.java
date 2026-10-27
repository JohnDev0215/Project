package com.ts.androidauto.aap.common;

import com.google.android.projection.proto.Protos;
import com.ts.androidauto.fsdk.common.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class WifiConnectInfo {
    private int mApChannel;
    private String mHostBssid;
    private String mHostIp;
    private int mPort;
    private String mWifiPassword;
    private Protos.WifiSecurityMode mWifiSecurityType;
    private String mWifiSsid;

    public String getWifiSsid() {
        return this.mWifiSsid;
    }

    public void setWifiSsid(String wifiSsid) {
        this.mWifiSsid = wifiSsid;
    }

    public String getWifiPassword() {
        return this.mWifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.mWifiPassword = wifiPassword;
    }

    public int getPort() {
        return this.mPort;
    }

    public void setPort(int port) {
        this.mPort = port;
    }

    public int getApChannel() {
        return this.mApChannel;
    }

    public void setApChannel(int apChannel) {
        this.mApChannel = apChannel;
    }

    public Protos.WifiSecurityMode getWifiSecurityType() {
        return this.mWifiSecurityType;
    }

    public void setWifiSecurityType(Protos.WifiSecurityMode wifiSecurityType) {
        this.mWifiSecurityType = wifiSecurityType;
    }

    public String getHostIp() {
        return this.mHostIp;
    }

    public void setHostIp(String hostIp) {
        this.mHostIp = hostIp;
    }

    public String getHostBssid() {
        return this.mHostBssid;
    }

    public void setHostBssid(String hostBssid) {
        this.mHostBssid = hostBssid;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("WifiSsid=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mWifiSsid));
        sb.append(" Passwd=");
        sb.append(LogEncrypt.IpAddressEncrypt(this.mWifiPassword));
        sb.append(" Bssid=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mHostBssid));
        sb.append(" HostIP=");
        sb.append(LogEncrypt.IpAddressEncrypt(this.mHostIp));
        return sb.toString();
    }
}
