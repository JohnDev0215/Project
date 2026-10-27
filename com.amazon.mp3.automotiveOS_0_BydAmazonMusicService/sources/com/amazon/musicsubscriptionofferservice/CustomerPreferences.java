package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerPreferences implements Comparable<CustomerPreferences> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.CustomerPreferences");
    private String cop;
    private String lop;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerPreferences customerPreferences) {
        if (customerPreferences == null) {
            return -1;
        }
        if (customerPreferences == this) {
            return 0;
        }
        String cop = getCop();
        String cop2 = customerPreferences.getCop();
        if (cop != cop2) {
            if (cop == null) {
                return -1;
            }
            if (cop2 == null) {
                return 1;
            }
            int iCompareTo = cop.compareTo(cop2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String lop = getLop();
        String lop2 = customerPreferences.getLop();
        if (lop != lop2) {
            if (lop == null) {
                return -1;
            }
            if (lop2 == null) {
                return 1;
            }
            int iCompareTo2 = lop.compareTo(lop2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerPreferences)) {
            return false;
        }
        CustomerPreferences customerPreferences = (CustomerPreferences) obj;
        return internalEqualityCheck(getCop(), customerPreferences.getCop()) && internalEqualityCheck(getLop(), customerPreferences.getLop());
    }

    public String getCop() {
        return this.cop;
    }

    public String getLop() {
        return this.lop;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCop(), getLop());
    }

    public void setCop(String str) {
        this.cop = str;
    }

    public void setLop(String str) {
        this.lop = str;
    }
}
