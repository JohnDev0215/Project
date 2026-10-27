package com.byd.event_upload;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class EventPackage {
    private String appId;
    private String appVersion;
    private String netWork;
    private String padPlatver;
    private String padSysver;
    private ArrayList<EventInfo> triggerEventList;
    private String uploadTime;
    private String userId;
    private int vehicle;
    private String vin;

    public EventPackage() {
    }

    public EventPackage(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        this.appId = str;
        this.userId = str2;
        this.netWork = str3;
        this.uploadTime = str4;
        this.padSysver = str5;
        this.padPlatver = str6;
        this.appVersion = str7;
        this.vin = str8;
        this.vehicle = i;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getNetWork() {
        return this.netWork;
    }

    public String getPadPlatver() {
        return this.padPlatver;
    }

    public String getPadSysver() {
        return this.padSysver;
    }

    public ArrayList<EventInfo> getTriggerEventList() {
        return this.triggerEventList;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public String getUserId() {
        return this.userId;
    }

    public int getVehicle() {
        return this.vehicle;
    }

    public String getVin() {
        return this.vin;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setNetWork(String str) {
        this.netWork = str;
    }

    public void setPadPlatver(String str) {
        this.padPlatver = str;
    }

    public void setPadSysver(String str) {
        this.padSysver = str;
    }

    public void setTriggerEventList(ArrayList<EventInfo> arrayList) {
        this.triggerEventList = arrayList;
    }

    public void setUploadTime(String str) {
        this.uploadTime = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setVehicle(int i) {
        this.vehicle = i;
    }

    public void setVin(String str) {
        this.vin = str;
    }
}
