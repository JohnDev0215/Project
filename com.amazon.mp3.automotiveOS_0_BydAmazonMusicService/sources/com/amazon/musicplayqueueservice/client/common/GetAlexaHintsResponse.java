package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetAlexaHintsResponse implements Comparable<GetAlexaHintsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetAlexaHintsResponse");
    private List<Hint> hints;
    private int timeToLiveInSeconds;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetAlexaHintsResponse getAlexaHintsResponse) {
        if (getAlexaHintsResponse == null) {
            return -1;
        }
        if (getAlexaHintsResponse == this) {
            return 0;
        }
        if (getTimeToLiveInSeconds() < getAlexaHintsResponse.getTimeToLiveInSeconds()) {
            return -1;
        }
        if (getTimeToLiveInSeconds() > getAlexaHintsResponse.getTimeToLiveInSeconds()) {
            return 1;
        }
        List<Hint> hints = getHints();
        List<Hint> hints2 = getAlexaHintsResponse.getHints();
        if (hints != hints2) {
            if (hints == null) {
                return -1;
            }
            if (hints2 == null) {
                return 1;
            }
            if (hints instanceof Comparable) {
                int iCompareTo = ((Comparable) hints).compareTo(hints2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!hints.equals(hints2)) {
                int iHashCode = hints.hashCode();
                int iHashCode2 = hints2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetAlexaHintsResponse)) {
            return false;
        }
        GetAlexaHintsResponse getAlexaHintsResponse = (GetAlexaHintsResponse) obj;
        return internalEqualityCheck(Integer.valueOf(getTimeToLiveInSeconds()), Integer.valueOf(getAlexaHintsResponse.getTimeToLiveInSeconds())) && internalEqualityCheck(getHints(), getAlexaHintsResponse.getHints());
    }

    public List<Hint> getHints() {
        return this.hints;
    }

    public int getTimeToLiveInSeconds() {
        return this.timeToLiveInSeconds;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getTimeToLiveInSeconds()), getHints());
    }

    public void setHints(List<Hint> list) {
        this.hints = list;
    }

    public void setTimeToLiveInSeconds(int i) {
        this.timeToLiveInSeconds = i;
    }
}
