package com.amazon.avod.media;

import com.amazon.avod.util.DataUnit;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class DataRate {
    private final long mRateBitsPerSecond;

    public DataRate(long j, DataUnit dataUnit) {
        this.mRateBitsPerSecond = dataUnit.toBits(j);
    }

    public long getBitsPerSecond() {
        return this.mRateBitsPerSecond;
    }

    public long getKiloBitsPerSecond() {
        return (long) DataUnit.BITS.toKiloBits(this.mRateBitsPerSecond);
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("bitsPerSecond", getBitsPerSecond()).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DataRate)) {
            return false;
        }
        return Objects.equal(Long.valueOf(this.mRateBitsPerSecond), Long.valueOf(((DataRate) obj).mRateBitsPerSecond));
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(getBitsPerSecond()));
    }
}
