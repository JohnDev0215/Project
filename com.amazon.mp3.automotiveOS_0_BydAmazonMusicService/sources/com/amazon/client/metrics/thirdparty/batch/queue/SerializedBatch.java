package com.amazon.client.metrics.thirdparty.batch.queue;

/* JADX INFO: loaded from: classes2.dex */
public class SerializedBatch {
    private byte[] mBatchContent;
    private long mTimestamp;

    public SerializedBatch(byte[] bArr, long j) {
        this.mBatchContent = bArr;
        this.mTimestamp = j;
    }

    public SerializedBatch(byte[] bArr) {
        this.mBatchContent = bArr;
        this.mTimestamp = System.currentTimeMillis();
    }

    public byte[] getSerializedBytes() {
        return this.mBatchContent;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public long getLength() {
        if (this.mBatchContent == null) {
            return 0L;
        }
        return r0.length;
    }
}
