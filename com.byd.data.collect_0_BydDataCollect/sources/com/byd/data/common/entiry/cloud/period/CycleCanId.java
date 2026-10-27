package com.byd.data.common.entiry.cloud.period;

import com.byd.data.common.entiry.cloud.event.CanBean;

/* JADX INFO: loaded from: classes.dex */
public class CycleCanId extends CanBean {
    public int collectCycle;
    public byte[] data;

    public CycleCanId() {
        this.type = (byte) 2;
    }
}
