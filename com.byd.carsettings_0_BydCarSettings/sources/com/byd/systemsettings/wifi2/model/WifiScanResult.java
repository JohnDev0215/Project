package com.byd.systemsettings.wifi2.model;

import android.net.wifi.ScanResult;
import b.b.m.g.f;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.util.WifiSecurityUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class WifiScanResult extends BydData implements Comparable<WifiScanResult> {
    private String capabilities;
    private boolean checked;
    private CipherType cipherType;
    private String mac;
    private String name;
    private ScanResult result;
    private int rssi;
    private int rssiLevel;
    private boolean saved;
    private String security;

    public WifiScanResult() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WifiScanResult wifiScanResult = (WifiScanResult) obj;
        return Objects.equals(this.name, wifiScanResult.name) && Objects.equals(this.mac, wifiScanResult.mac) && Objects.equals(this.capabilities, wifiScanResult.capabilities);
    }

    public String getCapabilities() {
        return this.capabilities;
    }

    public CipherType getCipherType() {
        return this.cipherType;
    }

    public String getMac() {
        return this.mac;
    }

    public String getName() {
        return this.name;
    }

    public ScanResult getResult() {
        return this.result;
    }

    public int getRssi() {
        return this.rssi;
    }

    public int getRssiLevel() {
        return this.rssiLevel;
    }

    public String getSecurity() {
        return this.security;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.mac, this.capabilities);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean isSaved() {
        return this.saved;
    }

    public void setCapabilities(String str) {
        this.capabilities = str;
        this.security = WifiSecurityUtils.toSecurity(str);
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setCipherType(CipherType cipherType) {
        this.cipherType = cipherType;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setResult(ScanResult scanResult) {
        this.result = scanResult;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setRssiLevel(int i) {
        this.rssiLevel = i;
    }

    public void setSaved(boolean z) {
        this.saved = z;
    }

    public String toString() {
        return "WifiScanResult{ssid='" + f.g(this.name) + ", rssi=" + this.rssi + ", bssid=" + f.e(this.mac) + ", cipherType=" + this.cipherType + ", security=" + this.security + ", checked=" + this.checked + ", saved=" + this.saved + ", rssiLevel=" + this.rssiLevel + '}';
    }

    public WifiScanResult(ScanResult scanResult, int i, String str) {
        this.result = scanResult;
        this.name = scanResult.SSID;
        this.mac = scanResult.BSSID;
        this.rssi = i;
        this.capabilities = str;
        this.cipherType = CipherType.get(str);
        this.security = WifiSecurityUtils.toSecurity(str);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    @Override // java.lang.Comparable
    public int compareTo(WifiScanResult wifiScanResult) {
        ?? r0 = this.saved;
        ?? IsSaved = wifiScanResult.isSaved();
        if (r0 > IsSaved) {
            return -1;
        }
        if (r0 < IsSaved) {
            return 1;
        }
        if (this.rssi > wifiScanResult.getRssi()) {
            return -1;
        }
        return this.rssi < wifiScanResult.getRssi() ? 1 : 0;
    }
}
