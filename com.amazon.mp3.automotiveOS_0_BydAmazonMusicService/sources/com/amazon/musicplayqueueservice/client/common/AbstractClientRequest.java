package com.amazon.musicplayqueueservice.client.common;

import com.amazon.musicplayqueueservice.model.CustomerInformation;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractClientRequest implements Comparable<AbstractClientRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.AbstractClientRequest");
    private AllowedParentalControls allowedParentalControls;
    private CustomerInformation customerInfo;
    private boolean isVoiceInitiatedRequest;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = abstractClientRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (!isIsVoiceInitiatedRequest() && abstractClientRequest.isIsVoiceInitiatedRequest()) {
            return -1;
        }
        if (isIsVoiceInitiatedRequest() && !abstractClientRequest.isIsVoiceInitiatedRequest()) {
            return 1;
        }
        CustomerInformation customerInfo = getCustomerInfo();
        CustomerInformation customerInfo2 = abstractClientRequest.getCustomerInfo();
        if (customerInfo != customerInfo2) {
            if (customerInfo == null) {
                return -1;
            }
            if (customerInfo2 == null) {
                return 1;
            }
            int iCompareTo2 = customerInfo.compareTo(customerInfo2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractClientRequest)) {
            return false;
        }
        AbstractClientRequest abstractClientRequest = (AbstractClientRequest) obj;
        return internalEqualityCheck(getAllowedParentalControls(), abstractClientRequest.getAllowedParentalControls()) && internalEqualityCheck(Boolean.valueOf(isIsVoiceInitiatedRequest()), Boolean.valueOf(abstractClientRequest.isIsVoiceInitiatedRequest())) && internalEqualityCheck(getCustomerInfo(), abstractClientRequest.getCustomerInfo());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public CustomerInformation getCustomerInfo() {
        return this.customerInfo;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAllowedParentalControls(), Boolean.valueOf(isIsVoiceInitiatedRequest()), getCustomerInfo());
    }

    public boolean isIsVoiceInitiatedRequest() {
        return this.isVoiceInitiatedRequest;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setCustomerInfo(CustomerInformation customerInformation) {
        this.customerInfo = customerInformation;
    }

    public void setIsVoiceInitiatedRequest(boolean z) {
        this.isVoiceInitiatedRequest = z;
    }
}
