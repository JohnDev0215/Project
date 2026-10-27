package com.ecarx.xui.adaptapi.device.ads;

/* JADX INFO: loaded from: classes2.dex */
public class AdInfo {
    long adDuration;
    long adEffectiveTime;
    long adExpiredTime;
    public String adId;
    int adMaxTimes;
    String adMd5;
    String adPath;

    public AdInfo(String str, String str2, String str3, long j, int i, long j2, long j3) {
        this.adId = str;
        this.adPath = str2;
        this.adMd5 = str3;
        this.adDuration = j;
        this.adMaxTimes = i;
        this.adEffectiveTime = j2;
        this.adExpiredTime = j3;
    }
}
