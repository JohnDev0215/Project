package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class TemiEvent extends FilterEvent {
    private final byte[] mDescrData;
    private final byte mDescrTag;
    private final long mPts;

    private TemiEvent(long j, byte b, byte[] bArr) {
        this.mPts = j;
        this.mDescrTag = b;
        this.mDescrData = bArr;
    }

    public long getPts() {
        return this.mPts;
    }

    public byte getDescriptorTag() {
        return this.mDescrTag;
    }

    public byte[] getDescriptorData() {
        return this.mDescrData;
    }
}
