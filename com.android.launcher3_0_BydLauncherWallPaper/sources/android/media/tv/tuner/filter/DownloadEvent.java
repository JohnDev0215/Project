package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DownloadEvent extends FilterEvent {
    private final int mDataLength;
    private final int mItemFragmentIndex;
    private final int mItemId;
    private final int mLastItemFragmentIndex;
    private final int mMpuSequenceNumber;

    private DownloadEvent(int i, int i2, int i3, int i4, int i5) {
        this.mItemId = i;
        this.mMpuSequenceNumber = i2;
        this.mItemFragmentIndex = i3;
        this.mLastItemFragmentIndex = i4;
        this.mDataLength = i5;
    }

    public int getItemId() {
        return this.mItemId;
    }

    public int getMpuSequenceNumber() {
        return this.mMpuSequenceNumber;
    }

    public int getItemFragmentIndex() {
        return this.mItemFragmentIndex;
    }

    public int getLastItemFragmentIndex() {
        return this.mLastItemFragmentIndex;
    }

    public int getDataLength() {
        return this.mDataLength;
    }
}
