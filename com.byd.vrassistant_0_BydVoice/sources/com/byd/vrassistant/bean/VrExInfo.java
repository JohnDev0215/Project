package com.byd.vrassistant.bean;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class VrExInfo {
    String area;
    boolean isNetAvailable;

    public VrExInfo() {
    }

    public VrExInfo(VrExInfo vrExInfo) {
        if (vrExInfo == null) {
            return;
        }
        this.area = vrExInfo.getArea();
        this.isNetAvailable = vrExInfo.isNetAvailable();
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public boolean isNetAvailable() {
        return this.isNetAvailable;
    }

    public void setNetAvailable(boolean z) {
        this.isNetAvailable = z;
    }

    public String toString() {
        return "VrExInfo{area='" + this.area + "', isNetAvailable=" + this.isNetAvailable + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VrExInfo vrExInfo = (VrExInfo) obj;
        return this.isNetAvailable == vrExInfo.isNetAvailable && Objects.equals(this.area, vrExInfo.area);
    }

    public int hashCode() {
        return Objects.hash(this.area, Boolean.valueOf(this.isNetAvailable));
    }
}
