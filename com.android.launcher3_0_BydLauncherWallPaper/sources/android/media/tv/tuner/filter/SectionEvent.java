package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class SectionEvent extends FilterEvent {
    private final int mDataLength;
    private final int mSectionNum;
    private final int mTableId;
    private final int mVersion;

    private SectionEvent(int i, int i2, int i3, int i4) {
        this.mTableId = i;
        this.mVersion = i2;
        this.mSectionNum = i3;
        this.mDataLength = i4;
    }

    public int getTableId() {
        return this.mTableId;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public int getSectionNumber() {
        return this.mSectionNum;
    }

    public int getDataLength() {
        return this.mDataLength;
    }
}
