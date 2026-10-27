package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class StratusBaseRequest implements Comparable<StratusBaseRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.StratusBaseRequest");
    private String customerId;
    private String deviceId;
    private String deviceType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated StratusBaseRequest stratusBaseRequest) {
        if (stratusBaseRequest == null) {
            return -1;
        }
        if (stratusBaseRequest == this) {
            return 0;
        }
        String deviceId = getDeviceId();
        String deviceId2 = stratusBaseRequest.getDeviceId();
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
        String customerId = getCustomerId();
        String customerId2 = stratusBaseRequest.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo2 = customerId.compareTo(customerId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = stratusBaseRequest.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo3 = deviceType.compareTo(deviceType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StratusBaseRequest)) {
            return false;
        }
        StratusBaseRequest stratusBaseRequest = (StratusBaseRequest) obj;
        return internalEqualityCheck(getDeviceId(), stratusBaseRequest.getDeviceId()) && internalEqualityCheck(getCustomerId(), stratusBaseRequest.getCustomerId()) && internalEqualityCheck(getDeviceType(), stratusBaseRequest.getDeviceType());
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeviceId(), getCustomerId(), getDeviceType());
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
