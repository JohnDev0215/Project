package com.byd.repository.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class UrlSuffixInfo implements Serializable {
    public String area;
    public String country;
    public String data;
    public int key;

    public String toString() {
        return "UrlSuffixInfo{key=" + this.key + ", data='" + this.data + "', country='" + this.country + "', area='" + this.area + "'}";
    }
}
