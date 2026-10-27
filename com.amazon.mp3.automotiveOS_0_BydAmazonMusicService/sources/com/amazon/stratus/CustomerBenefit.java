package com.amazon.stratus;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerBenefit implements Comparable<CustomerBenefit> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.CustomerBenefit");
    private Benefit benefit;
    private List<String> contributingPlans;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerBenefit customerBenefit) {
        if (customerBenefit == null) {
            return -1;
        }
        if (customerBenefit == this) {
            return 0;
        }
        Benefit benefit = getBenefit();
        Benefit benefit2 = customerBenefit.getBenefit();
        if (benefit != benefit2) {
            if (benefit == null) {
                return -1;
            }
            if (benefit2 == null) {
                return 1;
            }
            int iCompareTo = benefit.compareTo(benefit2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> contributingPlans = getContributingPlans();
        List<String> contributingPlans2 = customerBenefit.getContributingPlans();
        if (contributingPlans != contributingPlans2) {
            if (contributingPlans == null) {
                return -1;
            }
            if (contributingPlans2 == null) {
                return 1;
            }
            if (contributingPlans instanceof Comparable) {
                int iCompareTo2 = ((Comparable) contributingPlans).compareTo(contributingPlans2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!contributingPlans.equals(contributingPlans2)) {
                int iHashCode = contributingPlans.hashCode();
                int iHashCode2 = contributingPlans2.hashCode();
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
        if (!(obj instanceof CustomerBenefit)) {
            return false;
        }
        CustomerBenefit customerBenefit = (CustomerBenefit) obj;
        return internalEqualityCheck(getBenefit(), customerBenefit.getBenefit()) && internalEqualityCheck(getContributingPlans(), customerBenefit.getContributingPlans());
    }

    public Benefit getBenefit() {
        return this.benefit;
    }

    public List<String> getContributingPlans() {
        return this.contributingPlans;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBenefit(), getContributingPlans());
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public void setContributingPlans(List<String> list) {
        this.contributingPlans = list;
    }
}
