package com.micphone.bean;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class TsBydDeviceInfo {
    public TsBydDongleInfo mTsBydDongleInfo = new TsBydDongleInfo();
    public ConcurrentHashMap<Integer, TsBydMicInfo> mTsBydMicInfoS = new ConcurrentHashMap<>();

    public String toString() {
        return "TsBydDeviceInfo{mTsBydDongleInfo=" + this.mTsBydDongleInfo + ", mTsBydMicInfoS=" + this.mTsBydMicInfoS + '}';
    }
}
