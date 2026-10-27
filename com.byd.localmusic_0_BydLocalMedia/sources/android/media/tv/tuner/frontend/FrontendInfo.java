package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import android.util.Range;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class FrontendInfo {
    private final int mAcquireRange;
    private final int mExclusiveGroupId;
    private final Range<Integer> mFrequencyRange;
    private final FrontendCapabilities mFrontendCap;
    private final int mId;
    private final int[] mStatusCaps;
    private final Range<Integer> mSymbolRateRange;
    private final int mType;

    private FrontendInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, FrontendCapabilities frontendCapabilities) {
        this.mId = i;
        this.mType = i2;
        this.mFrequencyRange = new Range<>(Integer.valueOf(i3), Integer.valueOf(i4));
        this.mSymbolRateRange = new Range<>(Integer.valueOf(i5), Integer.valueOf(i6));
        this.mAcquireRange = i7;
        this.mExclusiveGroupId = i8;
        this.mStatusCaps = iArr;
        this.mFrontendCap = frontendCapabilities;
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public Range<Integer> getFrequencyRange() {
        return this.mFrequencyRange;
    }

    public Range<Integer> getSymbolRateRange() {
        return this.mSymbolRateRange;
    }

    public int getAcquireRange() {
        return this.mAcquireRange;
    }

    public int getExclusiveGroupId() {
        return this.mExclusiveGroupId;
    }

    public int[] getStatusCapabilities() {
        return this.mStatusCaps;
    }

    public FrontendCapabilities getFrontendCapabilities() {
        return this.mFrontendCap;
    }
}
