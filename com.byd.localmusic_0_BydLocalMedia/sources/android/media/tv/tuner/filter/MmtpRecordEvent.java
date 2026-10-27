package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class MmtpRecordEvent extends FilterEvent {
    private final long mDataLength;
    private final int mScHevcIndexMask;

    private MmtpRecordEvent(int i, long j) {
        this.mScHevcIndexMask = i;
        this.mDataLength = j;
    }

    public int getScHevcIndexMask() {
        return this.mScHevcIndexMask;
    }

    public long getDataLength() {
        return this.mDataLength;
    }
}
