package com.byd.carsettings.model.dilink.param;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class EqualizerParams extends BydData {
    public int freq;
    public int value;

    public EqualizerParams(int i, int i2) {
        this.freq = i;
        this.value = i2;
    }

    public String toString() {
        return "EqualizerParams{freq=" + this.freq + ", value=" + this.value + '}';
    }
}
