package com.byd.systemsettings.hotspot.model;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class HotspotInfo extends BydData {
    private String device;
    private String ip;
    private String mac;
    private int state;

    public String getDevice() {
        return this.device;
    }

    public String getIp() {
        return this.ip;
    }

    public String getMac() {
        return this.mac;
    }

    public int getState() {
        return this.state;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setState(int i8) {
        this.state = i8;
    }

    public String toString() {
        return "HotspotInfo{ip='" + this.ip + "', mac='" + this.mac + "', device='" + this.device + "', state=" + this.state + '}';
    }
}
