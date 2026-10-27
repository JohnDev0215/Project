package com.byd.systemsettings.hotspot.model;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes.dex */
public class HotspotParams extends BydData {
    private int apBand;
    private int apSecurityType;
    private int flowLimited;
    private boolean isPwdHide = true;
    private String name;
    private String pwd;

    public HotspotParams() {
    }

    public int getApBand() {
        return this.apBand;
    }

    public int getApSecurityType() {
        return this.apSecurityType;
    }

    public int getFlowLimited() {
        return this.flowLimited;
    }

    public String getName() {
        return this.name;
    }

    public String getPwd() {
        return this.pwd;
    }

    public boolean isPwdHide() {
        return this.isPwdHide;
    }

    public void setApBand(int i) {
        this.apBand = i;
    }

    public void setApSecurityType(int i) {
        this.apSecurityType = i;
    }

    public void setFlowLimited(int i) {
        this.flowLimited = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPwd(String str) {
        this.pwd = str;
    }

    public void setPwdHide(boolean z) {
        this.isPwdHide = z;
    }

    public String toString() {
        return "HotspotParams{ssid='" + this.name + "', flowLimited=" + this.flowLimited + ", isPwdHide=" + this.isPwdHide + ", apBand=" + this.apBand + ", apSecurityType=" + this.apSecurityType + '}';
    }

    public HotspotParams(String str, String str2, int i, int i2) {
        this.name = str;
        this.pwd = str2;
        this.apBand = i;
        this.apSecurityType = i2;
    }
}
