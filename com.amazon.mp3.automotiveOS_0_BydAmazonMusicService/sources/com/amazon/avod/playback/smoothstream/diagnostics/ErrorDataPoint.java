package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Objects;

/* JADX INFO: loaded from: classes2.dex */
class ErrorDataPoint extends DataPoint {
    private final int mBitrate;

    ErrorDataPoint(TimeSpan timeSpan, int i) {
        super(timeSpan);
        this.mBitrate = i;
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint, java.lang.Comparable
    public int compareTo(DataPoint dataPoint) {
        int iCompareTo = super.compareTo(dataPoint);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int bitrate = this.mBitrate - ((ErrorDataPoint) dataPoint).getBitrate();
        if (bitrate < 0) {
            return -1;
        }
        return bitrate > 0 ? 1 : 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equal(Integer.valueOf(getBitrate()), Integer.valueOf(((ErrorDataPoint) obj).getBitrate()));
        }
        return false;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(getBitrate()));
    }
}
