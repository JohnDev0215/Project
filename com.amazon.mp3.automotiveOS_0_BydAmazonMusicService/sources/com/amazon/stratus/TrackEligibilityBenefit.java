package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class TrackEligibilityBenefit extends Benefit {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.TrackEligibilityBenefit");
    private String tier;

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
        if (!(benefit instanceof TrackEligibilityBenefit)) {
            return 1;
        }
        String tier = getTier();
        String tier2 = ((TrackEligibilityBenefit) benefit).getTier();
        if (tier != tier2) {
            if (tier == null) {
                return -1;
            }
            if (tier2 == null) {
                return 1;
            }
            int iCompareTo = tier.compareTo(tier2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(benefit);
    }

    @Override // com.amazon.stratus.Benefit
    public boolean equals(Object obj) {
        if (obj instanceof TrackEligibilityBenefit) {
            return super.equals(obj) && internalEqualityCheck(getTier(), ((TrackEligibilityBenefit) obj).getTier());
        }
        return false;
    }

    public String getTier() {
        return this.tier;
    }

    @Override // com.amazon.stratus.Benefit
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTier());
    }

    public void setTier(String str) {
        this.tier = str;
    }
}
