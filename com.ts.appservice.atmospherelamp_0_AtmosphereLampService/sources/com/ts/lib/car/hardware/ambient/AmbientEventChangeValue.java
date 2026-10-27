package com.ts.lib.car.hardware.ambient;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes3.dex */
public class AmbientEventChangeValue extends CarBaseProperty {
    private int[] mFrequencyPoint;
    private int mMediaWorkingModeS;
    private int mMediaWorkingStatusS;

    public int[] getFrequencyPoint() {
        return this.mFrequencyPoint;
    }

    public void setFrequencyPoint(int[] frequencyPoint) {
        this.mFrequencyPoint = frequencyPoint;
    }

    public int getMediaWorkingModeS() {
        return this.mMediaWorkingModeS;
    }

    public void setMediaWorkingModeS(int mediaWorkingModeS) {
        this.mMediaWorkingModeS = mediaWorkingModeS;
    }

    public int getMediaWorkingStatusS() {
        return this.mMediaWorkingStatusS;
    }

    public void setMediaWorkingStatusS(int mediaWorkingStatusS) {
        this.mMediaWorkingStatusS = mediaWorkingStatusS;
    }
}
