package com.spotify.connectivity;

/* JADX INFO: loaded from: classes.dex */
public final class MobileDeviceInfo {
    private int apiLevel;
    private String brand;
    private int deviceType;
    private String manufacturer;
    private String model;
    private String name;
    private String osVersion;
    private int screenDensityCurrent;
    private int screenDensityStable;
    private int screenHeight;
    private int screenWidth;
    private int smallestScreenWidthDp;
    private int volumeSteps = 32;

    public final int getApiLevel() {
        return this.apiLevel;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public final String getManufacturer() {
        return this.manufacturer;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final int getScreenDensityCurrent() {
        return this.screenDensityCurrent;
    }

    public final int getScreenDensityStable() {
        return this.screenDensityStable;
    }

    public final int getScreenHeight() {
        return this.screenHeight;
    }

    public final int getScreenWidth() {
        return this.screenWidth;
    }

    public final int getSmallestScreenWidthDp() {
        return this.smallestScreenWidthDp;
    }

    public final int getVolumeSteps() {
        return this.volumeSteps;
    }

    public final void setApiLevel(int i) {
        this.apiLevel = i;
    }

    public final void setBrand(String str) {
        this.brand = str;
    }

    public final void setDeviceType(int i) {
        this.deviceType = i;
    }

    public final void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public final void setModel(String str) {
        this.model = str;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setOsVersion(String str) {
        this.osVersion = str;
    }

    public final void setScreenDensityCurrent(int i) {
        this.screenDensityCurrent = i;
    }

    public final void setScreenDensityStable(int i) {
        this.screenDensityStable = i;
    }

    public final void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public final void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public final void setSmallestScreenWidthDp(int i) {
        this.smallestScreenWidthDp = i;
    }

    public final void setVolumeSteps(int i) {
        this.volumeSteps = i;
    }
}
