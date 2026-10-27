package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class TrackCountBenefit extends Benefit {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.TrackCountBenefit");
    private Integer numTracksAllowed;
    private Boolean unlimited;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.Benefit, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Benefit benefit) {
        if (benefit == null) {
            return -1;
        }
        if (benefit == this) {
            return 0;
        }
        if (!(benefit instanceof TrackCountBenefit)) {
            return 1;
        }
        TrackCountBenefit trackCountBenefit = (TrackCountBenefit) benefit;
        Integer numTracksAllowed = getNumTracksAllowed();
        Integer numTracksAllowed2 = trackCountBenefit.getNumTracksAllowed();
        if (numTracksAllowed != numTracksAllowed2) {
            if (numTracksAllowed == null) {
                return -1;
            }
            if (numTracksAllowed2 == null) {
                return 1;
            }
            int iCompareTo = numTracksAllowed.compareTo(numTracksAllowed2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsUnlimited = isUnlimited();
        Boolean boolIsUnlimited2 = trackCountBenefit.isUnlimited();
        if (boolIsUnlimited != boolIsUnlimited2) {
            if (boolIsUnlimited == null) {
                return -1;
            }
            if (boolIsUnlimited2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsUnlimited.compareTo(boolIsUnlimited2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(benefit);
    }

    @Override // com.amazon.stratus.Benefit
    public boolean equals(Object obj) {
        if (!(obj instanceof TrackCountBenefit)) {
            return false;
        }
        TrackCountBenefit trackCountBenefit = (TrackCountBenefit) obj;
        return super.equals(obj) && internalEqualityCheck(getNumTracksAllowed(), trackCountBenefit.getNumTracksAllowed()) && internalEqualityCheck(isUnlimited(), trackCountBenefit.isUnlimited());
    }

    public Integer getNumTracksAllowed() {
        return this.numTracksAllowed;
    }

    @Override // com.amazon.stratus.Benefit
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getNumTracksAllowed(), isUnlimited());
    }

    public Boolean isUnlimited() {
        return this.unlimited;
    }

    public void setNumTracksAllowed(Integer num) {
        this.numTracksAllowed = num;
    }

    public void setUnlimited(Boolean bool) {
        this.unlimited = bool;
    }
}
