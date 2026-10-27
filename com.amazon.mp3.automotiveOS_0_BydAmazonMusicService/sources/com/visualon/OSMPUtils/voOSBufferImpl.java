package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSBufferImpl implements voOSBuffer {
    byte[] mBuffer;
    int mBufferSize;
    long mTimestamp;

    public voOSBufferImpl() {
    }

    public voOSBufferImpl(long j, int i, byte[] bArr) {
        this.mBuffer = bArr;
        this.mBufferSize = i;
        this.mTimestamp = j;
    }

    @Override // com.visualon.OSMPUtils.voOSBuffer
    public long getTimestamp() {
        return this.mTimestamp;
    }

    @Override // com.visualon.OSMPUtils.voOSBuffer
    public int getBufferSize() {
        return this.mBufferSize;
    }

    @Override // com.visualon.OSMPUtils.voOSBuffer
    public byte[] getBuffer() {
        return this.mBuffer;
    }
}
