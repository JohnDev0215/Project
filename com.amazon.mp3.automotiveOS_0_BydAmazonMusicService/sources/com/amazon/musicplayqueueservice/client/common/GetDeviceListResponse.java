package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetDeviceListResponse implements Comparable<GetDeviceListResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetDeviceListResponse");
    private List<CastingDevice> devices;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetDeviceListResponse getDeviceListResponse) {
        List<CastingDevice> devices;
        List<CastingDevice> devices2;
        if (getDeviceListResponse == null) {
            return -1;
        }
        if (getDeviceListResponse != this && (devices = getDevices()) != (devices2 = getDeviceListResponse.getDevices())) {
            if (devices == null) {
                return -1;
            }
            if (devices2 == null) {
                return 1;
            }
            if (devices instanceof Comparable) {
                int iCompareTo = ((Comparable) devices).compareTo(devices2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!devices.equals(devices2)) {
                int iHashCode = devices.hashCode();
                int iHashCode2 = devices2.hashCode();
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
        if (obj instanceof GetDeviceListResponse) {
            return internalEqualityCheck(getDevices(), ((GetDeviceListResponse) obj).getDevices());
        }
        return false;
    }

    public List<CastingDevice> getDevices() {
        return this.devices;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDevices());
    }

    public void setDevices(List<CastingDevice> list) {
        this.devices = list;
    }
}
