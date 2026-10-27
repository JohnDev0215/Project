package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetConnectionInfoResponse implements Comparable<GetConnectionInfoResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse");
    private String clientId;
    private String identityPoolId;
    private String iotEndpoint;
    private String region;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetConnectionInfoResponse getConnectionInfoResponse) {
        if (getConnectionInfoResponse == null) {
            return -1;
        }
        if (getConnectionInfoResponse == this) {
            return 0;
        }
        String iotEndpoint = getIotEndpoint();
        String iotEndpoint2 = getConnectionInfoResponse.getIotEndpoint();
        if (iotEndpoint != iotEndpoint2) {
            if (iotEndpoint == null) {
                return -1;
            }
            if (iotEndpoint2 == null) {
                return 1;
            }
            int iCompareTo = iotEndpoint.compareTo(iotEndpoint2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String identityPoolId = getIdentityPoolId();
        String identityPoolId2 = getConnectionInfoResponse.getIdentityPoolId();
        if (identityPoolId != identityPoolId2) {
            if (identityPoolId == null) {
                return -1;
            }
            if (identityPoolId2 == null) {
                return 1;
            }
            int iCompareTo2 = identityPoolId.compareTo(identityPoolId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String clientId = getClientId();
        String clientId2 = getConnectionInfoResponse.getClientId();
        if (clientId != clientId2) {
            if (clientId == null) {
                return -1;
            }
            if (clientId2 == null) {
                return 1;
            }
            int iCompareTo3 = clientId.compareTo(clientId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String region = getRegion();
        String region2 = getConnectionInfoResponse.getRegion();
        if (region != region2) {
            if (region == null) {
                return -1;
            }
            if (region2 == null) {
                return 1;
            }
            int iCompareTo4 = region.compareTo(region2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetConnectionInfoResponse)) {
            return false;
        }
        GetConnectionInfoResponse getConnectionInfoResponse = (GetConnectionInfoResponse) obj;
        return internalEqualityCheck(getIotEndpoint(), getConnectionInfoResponse.getIotEndpoint()) && internalEqualityCheck(getIdentityPoolId(), getConnectionInfoResponse.getIdentityPoolId()) && internalEqualityCheck(getClientId(), getConnectionInfoResponse.getClientId()) && internalEqualityCheck(getRegion(), getConnectionInfoResponse.getRegion());
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public String getIotEndpoint() {
        return this.iotEndpoint;
    }

    public String getRegion() {
        return this.region;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getIotEndpoint(), getIdentityPoolId(), getClientId(), getRegion());
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public void setIotEndpoint(String str) {
        this.iotEndpoint = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }
}
