package com.amazon.musicplayqueueservice.client.common.casting;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Track implements Comparable<Track> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.casting.Track");
    private String identifier;
    private String identifierType;
    private String metricsContextIdentifier;
    private Integer shuffledIndex;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Track track) {
        if (track == null) {
            return -1;
        }
        if (track == this) {
            return 0;
        }
        Integer shuffledIndex = getShuffledIndex();
        Integer shuffledIndex2 = track.getShuffledIndex();
        if (shuffledIndex != shuffledIndex2) {
            if (shuffledIndex == null) {
                return -1;
            }
            if (shuffledIndex2 == null) {
                return 1;
            }
            int iCompareTo = shuffledIndex.compareTo(shuffledIndex2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String metricsContextIdentifier = getMetricsContextIdentifier();
        String metricsContextIdentifier2 = track.getMetricsContextIdentifier();
        if (metricsContextIdentifier != metricsContextIdentifier2) {
            if (metricsContextIdentifier == null) {
                return -1;
            }
            if (metricsContextIdentifier2 == null) {
                return 1;
            }
            int iCompareTo2 = metricsContextIdentifier.compareTo(metricsContextIdentifier2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String identifier = getIdentifier();
        String identifier2 = track.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo3 = identifier.compareTo(identifier2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String identifierType = getIdentifierType();
        String identifierType2 = track.getIdentifierType();
        if (identifierType != identifierType2) {
            if (identifierType == null) {
                return -1;
            }
            if (identifierType2 == null) {
                return 1;
            }
            int iCompareTo4 = identifierType.compareTo(identifierType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) {
            return false;
        }
        Track track = (Track) obj;
        return internalEqualityCheck(getShuffledIndex(), track.getShuffledIndex()) && internalEqualityCheck(getMetricsContextIdentifier(), track.getMetricsContextIdentifier()) && internalEqualityCheck(getIdentifier(), track.getIdentifier()) && internalEqualityCheck(getIdentifierType(), track.getIdentifierType());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    public String getMetricsContextIdentifier() {
        return this.metricsContextIdentifier;
    }

    public Integer getShuffledIndex() {
        return this.shuffledIndex;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getShuffledIndex(), getMetricsContextIdentifier(), getIdentifier(), getIdentifierType());
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setIdentifierType(String str) {
        this.identifierType = str;
    }

    public void setMetricsContextIdentifier(String str) {
        this.metricsContextIdentifier = str;
    }

    public void setShuffledIndex(Integer num) {
        this.shuffledIndex = num;
    }
}
