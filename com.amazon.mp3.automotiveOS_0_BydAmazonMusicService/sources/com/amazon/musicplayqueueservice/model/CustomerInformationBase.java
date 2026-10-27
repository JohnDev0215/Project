package com.amazon.musicplayqueueservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CustomerInformationBase implements Comparable<CustomerInformationBase> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.model.CustomerInformationBase");
    private String customerId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerInformationBase customerInformationBase) {
        String customerId;
        String customerId2;
        if (customerInformationBase == null) {
            return -1;
        }
        if (customerInformationBase != this && (customerId = getCustomerId()) != (customerId2 = customerInformationBase.getCustomerId())) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo = customerId.compareTo(customerId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CustomerInformationBase) {
            return internalEqualityCheck(getCustomerId(), ((CustomerInformationBase) obj).getCustomerId());
        }
        return false;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCustomerId());
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }
}
