package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Device implements Comparable<Device> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.Device");
    private DeviceAvailableState availableState;
    private DeviceCurrentState currentState;
    private String customerId;
    private String deviceId;
    private String deviceType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Device device) {
        if (device == null) {
            return -1;
        }
        if (device == this) {
            return 0;
        }
        String customerId = getCustomerId();
        String customerId2 = device.getCustomerId();
        if (customerId != customerId2) {
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
        String deviceId = getDeviceId();
        String deviceId2 = device.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo2 = deviceId.compareTo(deviceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        DeviceAvailableState availableState = getAvailableState();
        DeviceAvailableState availableState2 = device.getAvailableState();
        if (availableState != availableState2) {
            if (availableState == null) {
                return -1;
            }
            if (availableState2 == null) {
                return 1;
            }
            int iCompareTo3 = availableState.compareTo(availableState2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = device.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo4 = deviceType.compareTo(deviceType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        DeviceCurrentState currentState = getCurrentState();
        DeviceCurrentState currentState2 = device.getCurrentState();
        if (currentState != currentState2) {
            if (currentState == null) {
                return -1;
            }
            if (currentState2 == null) {
                return 1;
            }
            int iCompareTo5 = currentState.compareTo(currentState2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return internalEqualityCheck(getCustomerId(), device.getCustomerId()) && internalEqualityCheck(getDeviceId(), device.getDeviceId()) && internalEqualityCheck(getAvailableState(), device.getAvailableState()) && internalEqualityCheck(getDeviceType(), device.getDeviceType()) && internalEqualityCheck(getCurrentState(), device.getCurrentState());
    }

    public DeviceAvailableState getAvailableState() {
        return this.availableState;
    }

    public DeviceCurrentState getCurrentState() {
        return this.currentState;
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCustomerId(), getDeviceId(), getAvailableState(), getDeviceType(), getCurrentState());
    }

    public void setAvailableState(DeviceAvailableState deviceAvailableState) {
        this.availableState = deviceAvailableState;
    }

    public void setCurrentState(DeviceCurrentState deviceCurrentState) {
        this.currentState = deviceCurrentState;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
