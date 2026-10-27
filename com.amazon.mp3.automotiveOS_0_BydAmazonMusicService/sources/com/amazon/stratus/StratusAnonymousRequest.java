package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class StratusAnonymousRequest implements Comparable<StratusAnonymousRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.StratusAnonymousRequest");
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
    public int compareTo(@Deprecated StratusAnonymousRequest stratusAnonymousRequest) {
        if (stratusAnonymousRequest == null) {
            return -1;
        }
        if (stratusAnonymousRequest == this) {
            return 0;
        }
        String deviceId = getDeviceId();
        String deviceId2 = stratusAnonymousRequest.getDeviceId();
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
        String deviceType = getDeviceType();
        String deviceType2 = stratusAnonymousRequest.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo2 = deviceType.compareTo(deviceType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StratusAnonymousRequest)) {
            return false;
        }
        StratusAnonymousRequest stratusAnonymousRequest = (StratusAnonymousRequest) obj;
        return internalEqualityCheck(getDeviceId(), stratusAnonymousRequest.getDeviceId()) && internalEqualityCheck(getDeviceType(), stratusAnonymousRequest.getDeviceType());
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeviceId(), getDeviceType());
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
