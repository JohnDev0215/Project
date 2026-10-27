package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveDeviceResponse implements Comparable<RetrieveDeviceResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveDeviceResponse");
    private Device device;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveDeviceResponse retrieveDeviceResponse) {
        Device device;
        Device device2;
        if (retrieveDeviceResponse == null) {
            return -1;
        }
        if (retrieveDeviceResponse != this && (device = getDevice()) != (device2 = retrieveDeviceResponse.getDevice())) {
            if (device == null) {
                return -1;
            }
            if (device2 == null) {
                return 1;
            }
            int iCompareTo = device.compareTo(device2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RetrieveDeviceResponse) {
            return internalEqualityCheck(getDevice(), ((RetrieveDeviceResponse) obj).getDevice());
        }
        return false;
    }

    public Device getDevice() {
        return this.device;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDevice());
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
