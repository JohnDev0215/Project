package com.neusoft.na.ocservice.greatwallsdk.bean;

/* JADX INFO: loaded from: classes2.dex */
public class PeripheralData {
    private int category;
    private GeoPosition poi;
    private boolean showLoading;
    private boolean showOnMap;
    private int RequestID = 0;
    private String keyWord = "";
    private int range = 0;

    public GeoPosition getPoi() {
        return this.poi;
    }

    public void setPoi(GeoPosition geoPosition) {
        this.poi = geoPosition;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int i) {
        this.category = i;
    }

    public String getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(String str) {
        this.keyWord = str;
    }

    public boolean isShowOnMap() {
        return this.showOnMap;
    }

    public void setShowOnMap(boolean z) {
        this.showOnMap = z;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int i) {
        this.range = i;
    }

    public boolean isShowLoading() {
        return this.showLoading;
    }

    public void setShowLoading(boolean z) {
        this.showLoading = z;
    }

    public int getRequestID() {
        return this.RequestID;
    }

    public void setRequestID(int i) {
        this.RequestID = i;
    }
}
