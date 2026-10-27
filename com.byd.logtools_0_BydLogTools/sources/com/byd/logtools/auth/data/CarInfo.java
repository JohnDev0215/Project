package com.byd.logtools.auth.data;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class CarInfo implements Serializable {
    String imei;
    String vin;

    public CarInfo(String str, String str2) {
        this.vin = str;
        this.imei = str2;
    }

    public String getImei() {
        return this.imei;
    }

    public String getVin() {
        return this.vin;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public void setVin(String str) {
        this.vin = str;
    }

    public String toString() {
        return "CarInfo{vin='" + this.vin + "', imei='" + this.imei + "'}";
    }
}
