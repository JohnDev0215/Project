package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.content.smoothstream.storage.singlefile.ByteRange;

/* JADX INFO: loaded from: classes2.dex */
class DownloadRequestStatus {
    private final ByteRange mByteRange;
    private long mDownloadedBytes;
    private long mDownloadedBytesCheckPoint;
    private int mTryCount;

    public DownloadRequestStatus(ByteRange byteRange) {
        this.mByteRange = byteRange;
    }

    public ByteRange getByteRange() {
        return this.mByteRange;
    }

    public int getTryCount() {
        return this.mTryCount;
    }

    public void setTryCount(int i) {
        this.mTryCount = i;
    }

    public long getDownloadedBytes() {
        return this.mDownloadedBytes;
    }

    public void setDownloadedBytes(long j) {
        this.mDownloadedBytes = j;
    }

    public long getDownloadedBytesCheckPoint() {
        return this.mDownloadedBytesCheckPoint;
    }

    public void setDownloadedBytesCheckPoint(long j) {
        this.mDownloadedBytesCheckPoint = j;
    }
}
