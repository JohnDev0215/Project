package com.android.inputmethod.latin.makedict;

import com.android.inputmethod.latin.utils.CombinedFormatUtils;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class ProbabilityInfo {
    public final int mCount;
    public final int mLevel;
    public final int mProbability;
    public final int mTimestamp;

    public static ProbabilityInfo max(final ProbabilityInfo probabilityInfo1, final ProbabilityInfo probabilityInfo2) {
        return probabilityInfo1 == null ? probabilityInfo2 : (probabilityInfo2 != null && probabilityInfo1.mProbability <= probabilityInfo2.mProbability) ? probabilityInfo2 : probabilityInfo1;
    }

    public ProbabilityInfo(final int probability) {
        this(probability, -1, 0, 0);
    }

    public ProbabilityInfo(final int probability, final int timestamp, final int level, final int count) {
        this.mProbability = probability;
        this.mTimestamp = timestamp;
        this.mLevel = level;
        this.mCount = count;
    }

    public boolean hasHistoricalInfo() {
        return this.mTimestamp != -1;
    }

    public int hashCode() {
        return hasHistoricalInfo() ? Arrays.hashCode(new Object[]{Integer.valueOf(this.mProbability), Integer.valueOf(this.mTimestamp), Integer.valueOf(this.mLevel), Integer.valueOf(this.mCount)}) : Arrays.hashCode(new Object[]{Integer.valueOf(this.mProbability)});
    }

    public String toString() {
        return CombinedFormatUtils.formatProbabilityInfo(this);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProbabilityInfo)) {
            return false;
        }
        ProbabilityInfo probabilityInfo = (ProbabilityInfo) o;
        return (hasHistoricalInfo() || probabilityInfo.hasHistoricalInfo()) ? this.mProbability == probabilityInfo.mProbability && this.mTimestamp == probabilityInfo.mTimestamp && this.mLevel == probabilityInfo.mLevel && this.mCount == probabilityInfo.mCount : this.mProbability == probabilityInfo.mProbability;
    }
}
