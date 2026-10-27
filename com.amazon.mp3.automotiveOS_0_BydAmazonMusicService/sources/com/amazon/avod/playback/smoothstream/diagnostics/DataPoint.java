package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Objects;

/* JADX INFO: loaded from: classes2.dex */
abstract class DataPoint implements Comparable<DataPoint> {
    protected final TimeSpan mTimeStamp;

    public DataPoint(TimeSpan timeSpan) {
        this.mTimeStamp = timeSpan;
    }

    public TimeSpan getTimeStamp() {
        return this.mTimeStamp;
    }

    @Override // java.lang.Comparable
    public int compareTo(DataPoint dataPoint) {
        return getTimeStamp().compareTo(dataPoint.getTimeStamp());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return Objects.equal(getTimeStamp(), ((DataPoint) obj).getTimeStamp());
    }

    public int hashCode() {
        return Objects.hashCode(getTimeStamp());
    }
}
