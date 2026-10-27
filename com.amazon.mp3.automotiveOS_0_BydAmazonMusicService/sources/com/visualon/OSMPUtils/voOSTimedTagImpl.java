package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSTimedTagImpl implements voOSTimedTag {
    private byte[] mData;
    private int mFlag;
    private Object mReserve;
    private int mSize;
    private long mTimeStamp;

    public voOSTimedTagImpl() {
    }

    public voOSTimedTagImpl(long j, int i, byte[] bArr, int i2, Object obj) {
        this.mTimeStamp = j;
        this.mSize = i;
        this.mData = bArr;
        this.mFlag = i2;
        this.mReserve = obj;
    }

    @Override // com.visualon.OSMPUtils.voOSTimedTag
    public long TimeStamp() {
        return this.mTimeStamp;
    }

    @Override // com.visualon.OSMPUtils.voOSTimedTag
    public int Size() {
        return this.mSize;
    }

    @Override // com.visualon.OSMPUtils.voOSTimedTag
    public byte[] Data() {
        return this.mData;
    }

    @Override // com.visualon.OSMPUtils.voOSTimedTag
    public int Flag() {
        return this.mFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSTimedTag
    public Object Reserve() {
        return this.mReserve;
    }
}
