package com.byd.carsettings.system.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class UFlashInfo implements Serializable {
    public static final int STATE_IN_USE = 0;
    public static final int STATE_MOUNT_ING = 3;
    public static final int STATE_TAN_CHU_ING = 1;
    public static final int STATE_YI_TAN_CHU = 2;
    private boolean defPrimary;
    public int diskState;
    private String name;
    private String path;
    private long size;
    public long sizeUsed;
    private DiskType type;
    private String volId;
    private int volumeCount;

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public long getSize() {
        return this.size;
    }

    public DiskType getType() {
        return this.type;
    }

    public String getVolId() {
        return this.volId;
    }

    public int getVolumeCount() {
        return this.volumeCount;
    }

    public boolean isDefPrimary() {
        return this.defPrimary;
    }

    public void setDefPrimary(boolean z) {
        this.defPrimary = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setType(DiskType diskType) {
        this.type = diskType;
    }

    public void setVolId(String str) {
        this.volId = str;
    }

    public void setVolumeCount(int i) {
        this.volumeCount = i;
    }

    public String toString() {
        return "UFlashInfo{name='" + this.name + "', volId='" + this.volId + "', size=" + this.size + ", type=" + this.type + ", diskState=" + this.diskState + ", volumeCount=" + this.volumeCount + ", defPrimary=" + this.defPrimary + ", path='" + this.path + "'}";
    }
}
