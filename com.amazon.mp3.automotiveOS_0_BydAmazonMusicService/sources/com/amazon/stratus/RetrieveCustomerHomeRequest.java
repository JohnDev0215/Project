package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCustomerHomeRequest extends StratusBaseRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveCustomerHomeRequest");
    private String ipAddress;
    private String sessionId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.StratusBaseRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated StratusBaseRequest stratusBaseRequest) {
        if (stratusBaseRequest == null) {
            return -1;
        }
        if (stratusBaseRequest == this) {
            return 0;
        }
        if (!(stratusBaseRequest instanceof RetrieveCustomerHomeRequest)) {
            return 1;
        }
        RetrieveCustomerHomeRequest retrieveCustomerHomeRequest = (RetrieveCustomerHomeRequest) stratusBaseRequest;
        String sessionId = getSessionId();
        String sessionId2 = retrieveCustomerHomeRequest.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo = sessionId.compareTo(sessionId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = retrieveCustomerHomeRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo2 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(stratusBaseRequest);
    }

    @Override // com.amazon.stratus.StratusBaseRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof RetrieveCustomerHomeRequest)) {
            return false;
        }
        RetrieveCustomerHomeRequest retrieveCustomerHomeRequest = (RetrieveCustomerHomeRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getSessionId(), retrieveCustomerHomeRequest.getSessionId()) && internalEqualityCheck(getIpAddress(), retrieveCustomerHomeRequest.getIpAddress());
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazon.stratus.StratusBaseRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSessionId(), getIpAddress());
    }
}
