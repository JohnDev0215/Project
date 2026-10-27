package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSCPUInfoImpl implements voOSCPUInfo {
    int mCPUType;
    int mCoreCount;
    int mFrequency;
    long mllReserved;

    @Override // com.visualon.OSMPUtils.voOSCPUInfo
    public int CoreCount() {
        return this.mCoreCount;
    }

    @Override // com.visualon.OSMPUtils.voOSCPUInfo
    public int CPUType() {
        return this.mCPUType;
    }

    @Override // com.visualon.OSMPUtils.voOSCPUInfo
    public int Frequency() {
        return this.mFrequency;
    }

    @Override // com.visualon.OSMPUtils.voOSCPUInfo
    public long ReservedField() {
        return this.mllReserved;
    }

    public voOSCPUInfoImpl(int i, int i2, int i3, long j) {
        this.mCoreCount = i;
        this.mCPUType = i2;
        this.mFrequency = i3;
        this.mllReserved = j;
    }
}
