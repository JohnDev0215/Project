package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class PlaymodeEligibility implements Comparable<PlaymodeEligibility> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.PlaymodeEligibility");
    private Boolean onDemand;
    private Boolean sfa;
    private String shuffleType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaymodeEligibility playmodeEligibility) {
        if (playmodeEligibility == null) {
            return -1;
        }
        if (playmodeEligibility == this) {
            return 0;
        }
        String shuffleType = getShuffleType();
        String shuffleType2 = playmodeEligibility.getShuffleType();
        if (shuffleType != shuffleType2) {
            if (shuffleType == null) {
                return -1;
            }
            if (shuffleType2 == null) {
                return 1;
            }
            int iCompareTo = shuffleType.compareTo(shuffleType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsSfa = isSfa();
        Boolean boolIsSfa2 = playmodeEligibility.isSfa();
        if (boolIsSfa != boolIsSfa2) {
            if (boolIsSfa == null) {
                return -1;
            }
            if (boolIsSfa2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsSfa.compareTo(boolIsSfa2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsOnDemand = isOnDemand();
        Boolean boolIsOnDemand2 = playmodeEligibility.isOnDemand();
        if (boolIsOnDemand != boolIsOnDemand2) {
            if (boolIsOnDemand == null) {
                return -1;
            }
            if (boolIsOnDemand2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsOnDemand.compareTo(boolIsOnDemand2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaymodeEligibility)) {
            return false;
        }
        PlaymodeEligibility playmodeEligibility = (PlaymodeEligibility) obj;
        return internalEqualityCheck(getShuffleType(), playmodeEligibility.getShuffleType()) && internalEqualityCheck(isSfa(), playmodeEligibility.isSfa()) && internalEqualityCheck(isOnDemand(), playmodeEligibility.isOnDemand());
    }

    public String getShuffleType() {
        return this.shuffleType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getShuffleType(), isSfa(), isOnDemand());
    }

    public Boolean isOnDemand() {
        return this.onDemand;
    }

    public Boolean isSfa() {
        return this.sfa;
    }

    public void setOnDemand(Boolean bool) {
        this.onDemand = bool;
    }

    public void setSfa(Boolean bool) {
        this.sfa = bool;
    }

    public void setShuffleType(String str) {
        this.shuffleType = str;
    }
}
