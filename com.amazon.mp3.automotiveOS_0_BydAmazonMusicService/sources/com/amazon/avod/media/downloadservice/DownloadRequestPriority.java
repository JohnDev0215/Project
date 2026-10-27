package com.amazon.avod.media.downloadservice;

import com.amazon.avod.util.DataUnit;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.primitives.Longs;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadRequestPriority implements Comparable<DownloadRequestPriority> {
    private final int mApprxSizeBytes;
    private final PriorityTier mTier;
    private final long mTimeUntilNeededInNanoseconds;

    public DownloadRequestPriority(PriorityTier priorityTier, int i, long j) {
        this.mTier = priorityTier;
        this.mApprxSizeBytes = i;
        this.mTimeUntilNeededInNanoseconds = j;
    }

    public PriorityTier getTier() {
        return this.mTier;
    }

    public int getApprxSizeBytes() {
        return this.mApprxSizeBytes;
    }

    public long getTimeUntilNeededInNanos() {
        return this.mTimeUntilNeededInNanoseconds;
    }

    @Override // java.lang.Comparable
    public int compareTo(DownloadRequestPriority downloadRequestPriority) {
        int iCompareTo = getTier().compareTo(downloadRequestPriority.getTier());
        return iCompareTo != 0 ? iCompareTo : Longs.compare(getTimeUntilNeededInNanos(), downloadRequestPriority.getTimeUntilNeededInNanos());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DownloadRequestPriority downloadRequestPriority = (DownloadRequestPriority) obj;
        return Objects.equal(Long.valueOf(this.mTimeUntilNeededInNanoseconds), Long.valueOf(downloadRequestPriority.mTimeUntilNeededInNanoseconds)) && Objects.equal(this.mTier, downloadRequestPriority.mTier);
    }

    public int hashCode() {
        return Objects.hashCode(this.mTier, Long.valueOf(this.mTimeUntilNeededInNanoseconds));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("Tier", this.mTier).add("Time", this.mTimeUntilNeededInNanoseconds).add("Size (KB)", DataUnit.BYTES.toKiloBytes(this.mApprxSizeBytes)).toString();
    }
}
