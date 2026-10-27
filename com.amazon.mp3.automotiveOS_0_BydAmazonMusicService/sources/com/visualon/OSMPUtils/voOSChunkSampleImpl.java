package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSChunkSampleImpl implements voOSChunkSample {
    long mChunkStartTime;
    int mFlag;
    long mPeriodFirstChunkStartTime;
    int mPeriodSequenceNumber;
    long mSampleTime;
    long mSampleTimePointer;

    public voOSChunkSampleImpl(int i, long j, int i2, long j2, long j3, long j4) {
        this.mFlag = i;
        this.mChunkStartTime = j;
        this.mPeriodSequenceNumber = i2;
        this.mPeriodFirstChunkStartTime = j2;
        this.mSampleTime = j3;
        this.mSampleTimePointer = j4;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkSample
    public int getFlag() {
        return this.mFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkSample
    public long getChunkStartTime() {
        return this.mChunkStartTime;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkSample
    public int getPeriodSequenceNumber() {
        return this.mPeriodSequenceNumber;
    }

    public long getPeriodFirstChunkStartTime() {
        return this.mPeriodFirstChunkStartTime;
    }

    @Override // com.visualon.OSMPUtils.voOSChunkSample
    public long getSampleTime() {
        return this.mSampleTime;
    }

    public long getSampleTimePointer() {
        return this.mSampleTimePointer;
    }
}
