package com.byd.carsettings.model.dilink;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class DiskSize implements Serializable {
    private long availableSize;
    private long realTotalSize;
    private long totalSzie;

    public DiskSize() {
    }

    public long getAvailableSize() {
        return this.availableSize;
    }

    public long getRealTotalSize() {
        return this.realTotalSize;
    }

    public long getTotalSzie() {
        return this.totalSzie;
    }

    public void setAvailableSize(long j) {
        this.availableSize = j;
    }

    public void setRealTotalSize(long j) {
        this.realTotalSize = j;
    }

    public void setTotalSzie(long j) {
        this.totalSzie = j;
    }

    public String toString() {
        return "DiskSize{totalSzie=" + this.totalSzie + ", availableSize=" + this.availableSize + ", realTotalSize=" + this.realTotalSize + '}';
    }

    public DiskSize(long j, long j2) {
        this.totalSzie = j;
        this.availableSize = j2;
    }
}
