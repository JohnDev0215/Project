package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class TsRecordEvent extends FilterEvent {
    private final long mDataLength;
    private final int mPid;
    private final int mScIndexMask;
    private final int mTsIndexMask;

    private TsRecordEvent(int i, int i2, int i3, long j) {
        this.mPid = i;
        this.mTsIndexMask = i2;
        this.mScIndexMask = i3;
        this.mDataLength = j;
    }

    public int getPacketId() {
        return this.mPid;
    }

    public int getTsIndexMask() {
        return this.mTsIndexMask;
    }

    public int getScIndexMask() {
        return this.mScIndexMask;
    }

    public long getDataLength() {
        return this.mDataLength;
    }
}
