package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RenewalPolicy implements Comparable<RenewalPolicy> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.RenewalPolicy");
    private Boolean autoRenew;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RenewalPolicy renewalPolicy) {
        Boolean boolIsAutoRenew;
        Boolean boolIsAutoRenew2;
        if (renewalPolicy == null) {
            return -1;
        }
        if (renewalPolicy != this && (boolIsAutoRenew = isAutoRenew()) != (boolIsAutoRenew2 = renewalPolicy.isAutoRenew())) {
            if (boolIsAutoRenew == null) {
                return -1;
            }
            if (boolIsAutoRenew2 == null) {
                return 1;
            }
            int iCompareTo = boolIsAutoRenew.compareTo(boolIsAutoRenew2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RenewalPolicy) {
            return internalEqualityCheck(isAutoRenew(), ((RenewalPolicy) obj).isAutoRenew());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isAutoRenew());
    }

    public Boolean isAutoRenew() {
        return this.autoRenew;
    }

    public void setAutoRenew(Boolean bool) {
        this.autoRenew = bool;
    }
}
