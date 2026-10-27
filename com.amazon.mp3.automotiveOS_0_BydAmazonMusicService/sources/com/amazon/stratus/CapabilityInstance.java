package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CapabilityInstance implements Comparable<CapabilityInstance> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.CapabilityInstance");
    private String capability;
    private Date grantedDate;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CapabilityInstance capabilityInstance) {
        if (capabilityInstance == null) {
            return -1;
        }
        if (capabilityInstance == this) {
            return 0;
        }
        Date grantedDate = getGrantedDate();
        Date grantedDate2 = capabilityInstance.getGrantedDate();
        if (grantedDate != grantedDate2) {
            if (grantedDate == null) {
                return -1;
            }
            if (grantedDate2 == null) {
                return 1;
            }
            int iCompareTo = grantedDate.compareTo(grantedDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String capability = getCapability();
        String capability2 = capabilityInstance.getCapability();
        if (capability != capability2) {
            if (capability == null) {
                return -1;
            }
            if (capability2 == null) {
                return 1;
            }
            int iCompareTo2 = capability.compareTo(capability2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CapabilityInstance)) {
            return false;
        }
        CapabilityInstance capabilityInstance = (CapabilityInstance) obj;
        return internalEqualityCheck(getGrantedDate(), capabilityInstance.getGrantedDate()) && internalEqualityCheck(getCapability(), capabilityInstance.getCapability());
    }

    public String getCapability() {
        return this.capability;
    }

    public Date getGrantedDate() {
        return this.grantedDate;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getGrantedDate(), getCapability());
    }

    public void setCapability(String str) {
        this.capability = str;
    }

    public void setGrantedDate(Date date) {
        this.grantedDate = date;
    }
}
