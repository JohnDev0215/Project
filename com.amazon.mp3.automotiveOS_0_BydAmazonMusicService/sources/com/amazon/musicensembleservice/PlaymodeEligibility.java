package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class PlaymodeEligibility implements Comparable<PlaymodeEligibility> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.PlaymodeEligibility");
    private Boolean onDemand;
    private Boolean sfa;
    private String shuffleType;

    public static class Builder {
        protected Boolean onDemand;
        protected Boolean sfa;
        protected String shuffleType;

        public PlaymodeEligibility build() {
            PlaymodeEligibility playmodeEligibility = new PlaymodeEligibility();
            populate(playmodeEligibility);
            return playmodeEligibility;
        }

        protected void populate(PlaymodeEligibility playmodeEligibility) {
            playmodeEligibility.setOnDemand(this.onDemand);
            playmodeEligibility.setSfa(this.sfa);
            playmodeEligibility.setShuffleType(this.shuffleType);
        }

        public Builder withOnDemand(Boolean bool) {
            this.onDemand = bool;
            return this;
        }

        public Builder withSfa(Boolean bool) {
            this.sfa = bool;
            return this;
        }

        public Builder withShuffleType(String str) {
            this.shuffleType = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

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
        Boolean boolIsOnDemand = isOnDemand();
        Boolean boolIsOnDemand2 = playmodeEligibility.isOnDemand();
        if (boolIsOnDemand != boolIsOnDemand2) {
            if (boolIsOnDemand == null) {
                return -1;
            }
            if (boolIsOnDemand2 == null) {
                return 1;
            }
            int iCompareTo = boolIsOnDemand.compareTo(boolIsOnDemand2);
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
        String shuffleType = getShuffleType();
        String shuffleType2 = playmodeEligibility.getShuffleType();
        if (shuffleType != shuffleType2) {
            if (shuffleType == null) {
                return -1;
            }
            if (shuffleType2 == null) {
                return 1;
            }
            int iCompareTo3 = shuffleType.compareTo(shuffleType2);
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
        return internalEqualityCheck(isOnDemand(), playmodeEligibility.isOnDemand()) && internalEqualityCheck(isSfa(), playmodeEligibility.isSfa()) && internalEqualityCheck(getShuffleType(), playmodeEligibility.getShuffleType());
    }

    public String getShuffleType() {
        return this.shuffleType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isOnDemand(), isSfa(), getShuffleType());
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
