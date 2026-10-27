package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerPreferenceRequest implements Comparable<CustomerPreferenceRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.CustomerPreferenceRequest");
    private CustomerPreferences customerPreferences;
    private Boolean requireCop;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerPreferenceRequest customerPreferenceRequest) {
        if (customerPreferenceRequest == null) {
            return -1;
        }
        if (customerPreferenceRequest == this) {
            return 0;
        }
        Boolean boolIsRequireCop = isRequireCop();
        Boolean boolIsRequireCop2 = customerPreferenceRequest.isRequireCop();
        if (boolIsRequireCop != boolIsRequireCop2) {
            if (boolIsRequireCop == null) {
                return -1;
            }
            if (boolIsRequireCop2 == null) {
                return 1;
            }
            int iCompareTo = boolIsRequireCop.compareTo(boolIsRequireCop2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        CustomerPreferences customerPreferences = getCustomerPreferences();
        CustomerPreferences customerPreferences2 = customerPreferenceRequest.getCustomerPreferences();
        if (customerPreferences != customerPreferences2) {
            if (customerPreferences == null) {
                return -1;
            }
            if (customerPreferences2 == null) {
                return 1;
            }
            int iCompareTo2 = customerPreferences.compareTo(customerPreferences2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerPreferenceRequest)) {
            return false;
        }
        CustomerPreferenceRequest customerPreferenceRequest = (CustomerPreferenceRequest) obj;
        return internalEqualityCheck(isRequireCop(), customerPreferenceRequest.isRequireCop()) && internalEqualityCheck(getCustomerPreferences(), customerPreferenceRequest.getCustomerPreferences());
    }

    public CustomerPreferences getCustomerPreferences() {
        return this.customerPreferences;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isRequireCop(), getCustomerPreferences());
    }

    public Boolean isRequireCop() {
        return this.requireCop;
    }

    public void setCustomerPreferences(CustomerPreferences customerPreferences) {
        this.customerPreferences = customerPreferences;
    }

    public void setRequireCop(Boolean bool) {
        this.requireCop = bool;
    }
}
