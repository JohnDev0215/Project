package com.ecarx.xui.adaptapi.device.ads;

/* JADX INFO: loaded from: classes2.dex */
public interface IBootAdInfo {
    String getAdId();

    String getAdPath();

    long getDuration();

    long getEffectiveTime();

    long getExpiredTime();

    int getMaxTimes();

    String getMd5();
}
