package com.ts.androidauto.app.proxy.navigation;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceLineMapBean {
    private int mIndex;
    private int mMapIconId;

    public GuidanceLineMapBean() {
    }

    public GuidanceLineMapBean(int index, int mapIconId) {
        this.mIndex = index;
        this.mMapIconId = mapIconId;
    }

    public int getmIndex() {
        return this.mIndex;
    }

    public void setmIndex(int index) {
        this.mIndex = index;
    }

    public int getmMapIconId() {
        return this.mMapIconId;
    }

    public void setmMapIconId(int mapIconId) {
        this.mMapIconId = mapIconId;
    }

    public String toString() {
        return "GuidanceLineMapBean{mIndex=" + this.mIndex + ", mMapIconId=" + this.mMapIconId + '}';
    }
}
