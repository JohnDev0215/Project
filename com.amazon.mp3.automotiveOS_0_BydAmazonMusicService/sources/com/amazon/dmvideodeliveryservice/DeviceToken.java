package com.amazon.dmvideodeliveryservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceToken implements Comparable<DeviceToken> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.dmvideodeliveryservice.DeviceToken");
    private String deviceId;
    private String deviceTypeId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceToken deviceToken) {
        if (deviceToken == null) {
            return -1;
        }
        if (deviceToken == this) {
            return 0;
        }
        String deviceId = getDeviceId();
        String deviceId2 = deviceToken.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo = deviceId.compareTo(deviceId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String deviceTypeId = getDeviceTypeId();
        String deviceTypeId2 = deviceToken.getDeviceTypeId();
        if (deviceTypeId != deviceTypeId2) {
            if (deviceTypeId == null) {
                return -1;
            }
            if (deviceTypeId2 == null) {
                return 1;
            }
            int iCompareTo2 = deviceTypeId.compareTo(deviceTypeId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceToken)) {
            return false;
        }
        DeviceToken deviceToken = (DeviceToken) obj;
        return internalEqualityCheck(getDeviceId(), deviceToken.getDeviceId()) && internalEqualityCheck(getDeviceTypeId(), deviceToken.getDeviceTypeId());
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeviceId(), getDeviceTypeId());
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceTypeId(String str) {
        this.deviceTypeId = str;
    }
}
