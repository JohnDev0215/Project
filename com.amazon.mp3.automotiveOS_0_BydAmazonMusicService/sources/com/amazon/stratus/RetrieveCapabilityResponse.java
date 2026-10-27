package com.amazon.stratus;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCapabilityResponse implements Comparable<RetrieveCapabilityResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveCapabilityResponse");
    private List<DeviceCapabilityDetailsBase> capabilities;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveCapabilityResponse retrieveCapabilityResponse) {
        List<DeviceCapabilityDetailsBase> capabilities;
        List<DeviceCapabilityDetailsBase> capabilities2;
        if (retrieveCapabilityResponse == null) {
            return -1;
        }
        if (retrieveCapabilityResponse != this && (capabilities = getCapabilities()) != (capabilities2 = retrieveCapabilityResponse.getCapabilities())) {
            if (capabilities == null) {
                return -1;
            }
            if (capabilities2 == null) {
                return 1;
            }
            if (capabilities instanceof Comparable) {
                int iCompareTo = ((Comparable) capabilities).compareTo(capabilities2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!capabilities.equals(capabilities2)) {
                int iHashCode = capabilities.hashCode();
                int iHashCode2 = capabilities2.hashCode();
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
        if (obj instanceof RetrieveCapabilityResponse) {
            return internalEqualityCheck(getCapabilities(), ((RetrieveCapabilityResponse) obj).getCapabilities());
        }
        return false;
    }

    public List<DeviceCapabilityDetailsBase> getCapabilities() {
        return this.capabilities;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCapabilities());
    }

    public void setCapabilities(List<DeviceCapabilityDetailsBase> list) {
        this.capabilities = list;
    }
}
