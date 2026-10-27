package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetTracksByIdResponse implements Comparable<GetTracksByIdResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetTracksByIdResponse");
    private List<TrackError> errorList;
    private List<Track> trackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetTracksByIdResponse getTracksByIdResponse) {
        if (getTracksByIdResponse == null) {
            return -1;
        }
        if (getTracksByIdResponse == this) {
            return 0;
        }
        List<Track> trackList = getTrackList();
        List<Track> trackList2 = getTracksByIdResponse.getTrackList();
        if (trackList != trackList2) {
            if (trackList == null) {
                return -1;
            }
            if (trackList2 == null) {
                return 1;
            }
            if (trackList instanceof Comparable) {
                int iCompareTo = ((Comparable) trackList).compareTo(trackList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!trackList.equals(trackList2)) {
                int iHashCode = trackList.hashCode();
                int iHashCode2 = trackList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<TrackError> errorList = getErrorList();
        List<TrackError> errorList2 = getTracksByIdResponse.getErrorList();
        if (errorList != errorList2) {
            if (errorList == null) {
                return -1;
            }
            if (errorList2 == null) {
                return 1;
            }
            if (errorList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) errorList).compareTo(errorList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!errorList.equals(errorList2)) {
                int iHashCode3 = errorList.hashCode();
                int iHashCode4 = errorList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetTracksByIdResponse)) {
            return false;
        }
        GetTracksByIdResponse getTracksByIdResponse = (GetTracksByIdResponse) obj;
        return internalEqualityCheck(getTrackList(), getTracksByIdResponse.getTrackList()) && internalEqualityCheck(getErrorList(), getTracksByIdResponse.getErrorList());
    }

    public List<TrackError> getErrorList() {
        return this.errorList;
    }

    public List<Track> getTrackList() {
        return this.trackList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTrackList(), getErrorList());
    }

    public void setErrorList(List<TrackError> list) {
        this.errorList = list;
    }

    public void setTrackList(List<Track> list) {
        this.trackList = list;
    }
}
