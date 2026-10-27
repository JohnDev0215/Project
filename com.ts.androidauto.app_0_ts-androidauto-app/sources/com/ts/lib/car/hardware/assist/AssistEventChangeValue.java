package com.ts.lib.car.hardware.assist;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes2.dex */
public class AssistEventChangeValue extends CarBaseProperty {
    private int[] mRtcTime;

    public int[] getRtcTime() {
        return this.mRtcTime;
    }

    public void setRtcTime(int[] rtcTime) {
        this.mRtcTime = rtcTime;
    }
}
