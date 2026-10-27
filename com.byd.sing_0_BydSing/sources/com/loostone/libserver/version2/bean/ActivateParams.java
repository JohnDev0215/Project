package com.loostone.libserver.version2.bean;

import com.android.volley.BuildConfig;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class ActivateParams {
    private String deviceId = BuildConfig.FLAVOR;
    private String modelTypeName = BuildConfig.FLAVOR;
    private String modelName = BuildConfig.FLAVOR;
    private String manufacturerName = BuildConfig.FLAVOR;
    private String hardwareName = BuildConfig.FLAVOR;
    private String customer = BuildConfig.FLAVOR;
    private String deviceName = BuildConfig.FLAVOR;
    private String deviceOriginId = BuildConfig.FLAVOR;
    private String mac = BuildConfig.FLAVOR;
    private String pid = BuildConfig.FLAVOR;
    private String vid = BuildConfig.FLAVOR;
    private String version = BuildConfig.FLAVOR;
    private String versionName = BuildConfig.FLAVOR;

    public final String getCustomer() {
        return this.customer;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final String getDeviceOriginId() {
        return this.deviceOriginId;
    }

    public final String getHardwareName() {
        return this.hardwareName;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getManufacturerName() {
        return this.manufacturerName;
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final String getModelTypeName() {
        return this.modelTypeName;
    }

    public final String getPid() {
        return this.pid;
    }

    public final String getVersion() {
        return this.version;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final String getVid() {
        return this.vid;
    }

    public final void setCustomer(String str) {
        h.d(str, "<set-?>");
        this.customer = str;
    }

    public final void setDeviceId(String str) {
        h.d(str, "<set-?>");
        this.deviceId = str;
    }

    public final void setDeviceName(String str) {
        h.d(str, "<set-?>");
        this.deviceName = str;
    }

    public final void setDeviceOriginId(String str) {
        h.d(str, "<set-?>");
        this.deviceOriginId = str;
    }

    public final void setHardwareName(String str) {
        h.d(str, "<set-?>");
        this.hardwareName = str;
    }

    public final void setMac(String str) {
        h.d(str, "<set-?>");
        this.mac = str;
    }

    public final void setManufacturerName(String str) {
        h.d(str, "<set-?>");
        this.manufacturerName = str;
    }

    public final void setModelName(String str) {
        h.d(str, "<set-?>");
        this.modelName = str;
    }

    public final void setModelTypeName(String str) {
        h.d(str, "<set-?>");
        this.modelTypeName = str;
    }

    public final void setPid(String str) {
        h.d(str, "<set-?>");
        this.pid = str;
    }

    public final void setVersion(String str) {
        h.d(str, "<set-?>");
        this.version = str;
    }

    public final void setVersionName(String str) {
        h.d(str, "<set-?>");
        this.versionName = str;
    }

    public final void setVid(String str) {
        h.d(str, "<set-?>");
        this.vid = str;
    }

    public String toString() {
        return "ActivateParams(\ndeviceId=" + this.deviceId + ", \nmodelTypeName=" + this.modelTypeName + ", \nmodelName=" + this.modelName + ", \nmanufacturerName=" + this.manufacturerName + ", \nhardwareName=" + this.hardwareName + ", \ncustomer=" + this.customer + ", \ndeviceName=" + this.deviceName + ", \ndeviceOriginId=" + this.deviceOriginId + ", \nmac=" + this.mac + ", \npid=" + this.pid + ", \nvid=" + this.vid + ", \nversion=" + this.version + ", \nversionName=" + this.versionName + ')';
    }
}
