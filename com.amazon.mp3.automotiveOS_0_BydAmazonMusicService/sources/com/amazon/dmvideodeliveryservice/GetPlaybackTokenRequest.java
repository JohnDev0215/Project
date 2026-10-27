package com.amazon.dmvideodeliveryservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackTokenRequest implements Comparable<GetPlaybackTokenRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequest");
    private String customerId;
    private CustomerInfo customerInfo;
    private DeviceToken deviceToken;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetPlaybackTokenRequest getPlaybackTokenRequest) {
        if (getPlaybackTokenRequest == null) {
            return -1;
        }
        if (getPlaybackTokenRequest == this) {
            return 0;
        }
        CustomerInfo customerInfo = getCustomerInfo();
        CustomerInfo customerInfo2 = getPlaybackTokenRequest.getCustomerInfo();
        if (customerInfo != customerInfo2) {
            if (customerInfo == null) {
                return -1;
            }
            if (customerInfo2 == null) {
                return 1;
            }
            int iCompareTo = customerInfo.compareTo(customerInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = getPlaybackTokenRequest.getCustomerId();
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
        DeviceToken deviceToken = getDeviceToken();
        DeviceToken deviceToken2 = getPlaybackTokenRequest.getDeviceToken();
        if (deviceToken != deviceToken2) {
            if (deviceToken == null) {
                return -1;
            }
            if (deviceToken2 == null) {
                return 1;
            }
            int iCompareTo3 = deviceToken.compareTo(deviceToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetPlaybackTokenRequest)) {
            return false;
        }
        GetPlaybackTokenRequest getPlaybackTokenRequest = (GetPlaybackTokenRequest) obj;
        return internalEqualityCheck(getCustomerInfo(), getPlaybackTokenRequest.getCustomerInfo()) && internalEqualityCheck(getCustomerId(), getPlaybackTokenRequest.getCustomerId()) && internalEqualityCheck(getDeviceToken(), getPlaybackTokenRequest.getDeviceToken());
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public CustomerInfo getCustomerInfo() {
        return this.customerInfo;
    }

    public DeviceToken getDeviceToken() {
        return this.deviceToken;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCustomerInfo(), getCustomerId(), getDeviceToken());
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public void setDeviceToken(DeviceToken deviceToken) {
        this.deviceToken = deviceToken;
    }
}
