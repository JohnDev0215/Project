package com.byd.data.common.entiry.cloud.event;

/* JADX INFO: loaded from: classes.dex */
public class EventCanId extends CanBean {
    public final byte[] Lock = new byte[0];
    public int peekIndex;
    public long tailTimeDivide;
    public long tempTimestamps;

    public boolean g() {
        return this.type == 3 && this.tempTimestamps == 0;
    }

    @Override // com.byd.data.common.entiry.cloud.event.CanBean
    public String toString() {
        return "EventCanId{canId=" + ((int) this.canId) + ", subTag=" + this.subTag + ", subVal=" + ((int) this.subVal) + ", size=" + ((int) this.size) + ",type=" + ((int) this.type) + '}';
    }
}
