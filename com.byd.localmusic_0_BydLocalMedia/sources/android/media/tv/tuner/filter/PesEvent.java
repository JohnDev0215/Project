package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class PesEvent extends FilterEvent {
    private final int mDataLength;
    private final int mMpuSequenceNumber;
    private final int mStreamId;

    private PesEvent(int i, int i2, int i3) {
        this.mStreamId = i;
        this.mDataLength = i2;
        this.mMpuSequenceNumber = i3;
    }

    public int getStreamId() {
        return this.mStreamId;
    }

    public int getDataLength() {
        return this.mDataLength;
    }

    public int getMpuSequenceNumber() {
        return this.mMpuSequenceNumber;
    }
}
