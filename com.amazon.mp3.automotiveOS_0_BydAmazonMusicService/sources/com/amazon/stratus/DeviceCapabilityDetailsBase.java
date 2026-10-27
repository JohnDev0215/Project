package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceCapabilityDetailsBase implements Comparable<DeviceCapabilityDetailsBase> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.DeviceCapabilityDetailsBase");
    private String capability;
    private Integer maxGrantsPerCustomer;
    private Date validUntil;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceCapabilityDetailsBase deviceCapabilityDetailsBase) {
        if (deviceCapabilityDetailsBase == null) {
            return -1;
        }
        if (deviceCapabilityDetailsBase == this) {
            return 0;
        }
        String capability = getCapability();
        String capability2 = deviceCapabilityDetailsBase.getCapability();
        if (capability != capability2) {
            if (capability == null) {
                return -1;
            }
            if (capability2 == null) {
                return 1;
            }
            int iCompareTo = capability.compareTo(capability2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer maxGrantsPerCustomer = getMaxGrantsPerCustomer();
        Integer maxGrantsPerCustomer2 = deviceCapabilityDetailsBase.getMaxGrantsPerCustomer();
        if (maxGrantsPerCustomer != maxGrantsPerCustomer2) {
            if (maxGrantsPerCustomer == null) {
                return -1;
            }
            if (maxGrantsPerCustomer2 == null) {
                return 1;
            }
            int iCompareTo2 = maxGrantsPerCustomer.compareTo(maxGrantsPerCustomer2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date validUntil = getValidUntil();
        Date validUntil2 = deviceCapabilityDetailsBase.getValidUntil();
        if (validUntil != validUntil2) {
            if (validUntil == null) {
                return -1;
            }
            if (validUntil2 == null) {
                return 1;
            }
            int iCompareTo3 = validUntil.compareTo(validUntil2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceCapabilityDetailsBase)) {
            return false;
        }
        DeviceCapabilityDetailsBase deviceCapabilityDetailsBase = (DeviceCapabilityDetailsBase) obj;
        return internalEqualityCheck(getCapability(), deviceCapabilityDetailsBase.getCapability()) && internalEqualityCheck(getMaxGrantsPerCustomer(), deviceCapabilityDetailsBase.getMaxGrantsPerCustomer()) && internalEqualityCheck(getValidUntil(), deviceCapabilityDetailsBase.getValidUntil());
    }

    public String getCapability() {
        return this.capability;
    }

    public Integer getMaxGrantsPerCustomer() {
        return this.maxGrantsPerCustomer;
    }

    public Date getValidUntil() {
        return this.validUntil;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCapability(), getMaxGrantsPerCustomer(), getValidUntil());
    }

    public void setCapability(String str) {
        this.capability = str;
    }

    public void setMaxGrantsPerCustomer(Integer num) {
        this.maxGrantsPerCustomer = num;
    }

    public void setValidUntil(Date date) {
        this.validUntil = date;
    }
}
