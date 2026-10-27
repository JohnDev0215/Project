package com.loostone.libtuning.inf.extern;

/* JADX INFO: loaded from: classes.dex */
public interface IMicInfoListener {
    void onInfoEvent(int i, int i2, Object obj);

    void onMicBattery(int i, int i2);

    void onMicBatteryPercentage(int i, double d2);
}
