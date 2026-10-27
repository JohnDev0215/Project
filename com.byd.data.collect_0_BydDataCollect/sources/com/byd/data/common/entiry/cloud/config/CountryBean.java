package com.byd.data.common.entiry.cloud.config;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class CountryBean {
    private String area;
    public int associate;
    public short countryCode;
    private short[] countryList;
    private int[] mccList;
    public String url;

    public CountryBean(int i2, String str, short s) {
        this.associate = i2;
        this.url = str;
        this.countryCode = s;
        c();
    }

    private void c() {
        if (!TextUtils.isEmpty(this.url) && TextUtils.isEmpty(this.area) && this.url.length() >= 20) {
            this.area = this.url.substring(8, 10);
        }
    }

    public String a() {
        return this.area;
    }

    public short[] b() {
        return this.countryList;
    }

    public void d(short s) {
        this.countryCode = s;
        c();
        this.countryList = null;
        this.mccList = null;
    }
}
