package com.amazon.musicplayqueueservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerInformation extends CustomerInformationBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.model.CustomerInformation");
    private String deviceId;
    private String deviceType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.model.CustomerInformationBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerInformationBase customerInformationBase) {
        if (customerInformationBase == null) {
            return -1;
        }
        if (customerInformationBase == this) {
            return 0;
        }
        if (!(customerInformationBase instanceof CustomerInformation)) {
            return 1;
        }
        CustomerInformation customerInformation = (CustomerInformation) customerInformationBase;
        String deviceId = getDeviceId();
        String deviceId2 = customerInformation.getDeviceId();
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
        String deviceType2 = customerInformation.getDeviceType();
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
        return super.compareTo(customerInformationBase);
    }

    @Override // com.amazon.musicplayqueueservice.model.CustomerInformationBase
    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerInformation)) {
            return false;
        }
        CustomerInformation customerInformation = (CustomerInformation) obj;
        return super.equals(obj) && internalEqualityCheck(getDeviceId(), customerInformation.getDeviceId()) && internalEqualityCheck(getDeviceType(), customerInformation.getDeviceType());
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.musicplayqueueservice.model.CustomerInformationBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDeviceId(), getDeviceType());
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
