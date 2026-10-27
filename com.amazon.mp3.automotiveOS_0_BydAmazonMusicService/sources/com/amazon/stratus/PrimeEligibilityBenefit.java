package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PrimeEligibilityBenefit extends Benefit {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.PrimeEligibilityBenefit");
    private Boolean eligible;

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
        if (!(benefit instanceof PrimeEligibilityBenefit)) {
            return 1;
        }
        Boolean boolIsEligible = isEligible();
        Boolean boolIsEligible2 = ((PrimeEligibilityBenefit) benefit).isEligible();
        if (boolIsEligible != boolIsEligible2) {
            if (boolIsEligible == null) {
                return -1;
            }
            if (boolIsEligible2 == null) {
                return 1;
            }
            int iCompareTo = boolIsEligible.compareTo(boolIsEligible2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(benefit);
    }

    @Override // com.amazon.stratus.Benefit
    public boolean equals(Object obj) {
        if (obj instanceof PrimeEligibilityBenefit) {
            return super.equals(obj) && internalEqualityCheck(isEligible(), ((PrimeEligibilityBenefit) obj).isEligible());
        }
        return false;
    }

    @Override // com.amazon.stratus.Benefit
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isEligible());
    }

    public Boolean isEligible() {
        return this.eligible;
    }

    public void setEligible(Boolean bool) {
        this.eligible = bool;
    }
}
