package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TrackError implements Comparable<TrackError> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.TrackError");
    private String cirrusError;
    private String trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackError trackError) {
        if (trackError == null) {
            return -1;
        }
        if (trackError == this) {
            return 0;
        }
        String cirrusError = getCirrusError();
        String cirrusError2 = trackError.getCirrusError();
        if (cirrusError != cirrusError2) {
            if (cirrusError == null) {
                return -1;
            }
            if (cirrusError2 == null) {
                return 1;
            }
            int iCompareTo = cirrusError.compareTo(cirrusError2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String trackId = getTrackId();
        String trackId2 = trackError.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo2 = trackId.compareTo(trackId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackError)) {
            return false;
        }
        TrackError trackError = (TrackError) obj;
        return internalEqualityCheck(getCirrusError(), trackError.getCirrusError()) && internalEqualityCheck(getTrackId(), trackError.getTrackId());
    }

    public String getCirrusError() {
        return this.cirrusError;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCirrusError(), getTrackId());
    }

    public void setCirrusError(String str) {
        this.cirrusError = str;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }
}
