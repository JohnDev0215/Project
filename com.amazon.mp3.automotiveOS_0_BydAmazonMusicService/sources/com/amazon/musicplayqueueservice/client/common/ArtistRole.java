package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ArtistRole implements Comparable<ArtistRole> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.ArtistRole");
    private String sequenceNumber;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ArtistRole artistRole) {
        if (artistRole == null) {
            return -1;
        }
        if (artistRole == this) {
            return 0;
        }
        String sequenceNumber = getSequenceNumber();
        String sequenceNumber2 = artistRole.getSequenceNumber();
        if (sequenceNumber != sequenceNumber2) {
            if (sequenceNumber == null) {
                return -1;
            }
            if (sequenceNumber2 == null) {
                return 1;
            }
            int iCompareTo = sequenceNumber.compareTo(sequenceNumber2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String type = getType();
        String type2 = artistRole.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo2 = type.compareTo(type2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistRole)) {
            return false;
        }
        ArtistRole artistRole = (ArtistRole) obj;
        return internalEqualityCheck(getSequenceNumber(), artistRole.getSequenceNumber()) && internalEqualityCheck(getType(), artistRole.getType());
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSequenceNumber(), getType());
    }

    public void setSequenceNumber(String str) {
        this.sequenceNumber = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
