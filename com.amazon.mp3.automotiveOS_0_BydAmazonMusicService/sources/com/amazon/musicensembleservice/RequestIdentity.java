package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class RequestIdentity implements Comparable<RequestIdentity> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.RequestIdentity");
    private String customerIP;
    private String customerId;
    private String deviceId;
    private String deviceType;
    private String marketplaceId;
    private String musicRequestIdentityContextToken;
    private String musicTerritory;
    private String requestedContent;
    private String sessionId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestIdentity requestIdentity) {
        if (requestIdentity == null) {
            return -1;
        }
        if (requestIdentity == this) {
            return 0;
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = requestIdentity.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String deviceId = getDeviceId();
        String deviceId2 = requestIdentity.getDeviceId();
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
        String customerId = getCustomerId();
        String customerId2 = requestIdentity.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo3 = customerId.compareTo(customerId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String sessionId = getSessionId();
        String sessionId2 = requestIdentity.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo4 = sessionId.compareTo(sessionId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String customerIP = getCustomerIP();
        String customerIP2 = requestIdentity.getCustomerIP();
        if (customerIP != customerIP2) {
            if (customerIP == null) {
                return -1;
            }
            if (customerIP2 == null) {
                return 1;
            }
            int iCompareTo5 = customerIP.compareTo(customerIP2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = requestIdentity.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo6 = deviceType.compareTo(deviceType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String requestedContent = getRequestedContent();
        String requestedContent2 = requestIdentity.getRequestedContent();
        if (requestedContent != requestedContent2) {
            if (requestedContent == null) {
                return -1;
            }
            if (requestedContent2 == null) {
                return 1;
            }
            int iCompareTo7 = requestedContent.compareTo(requestedContent2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String musicRequestIdentityContextToken = getMusicRequestIdentityContextToken();
        String musicRequestIdentityContextToken2 = requestIdentity.getMusicRequestIdentityContextToken();
        if (musicRequestIdentityContextToken != musicRequestIdentityContextToken2) {
            if (musicRequestIdentityContextToken == null) {
                return -1;
            }
            if (musicRequestIdentityContextToken2 == null) {
                return 1;
            }
            int iCompareTo8 = musicRequestIdentityContextToken.compareTo(musicRequestIdentityContextToken2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = requestIdentity.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo9 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RequestIdentity)) {
            return false;
        }
        RequestIdentity requestIdentity = (RequestIdentity) obj;
        return internalEqualityCheck(getMarketplaceId(), requestIdentity.getMarketplaceId()) && internalEqualityCheck(getDeviceId(), requestIdentity.getDeviceId()) && internalEqualityCheck(getCustomerId(), requestIdentity.getCustomerId()) && internalEqualityCheck(getSessionId(), requestIdentity.getSessionId()) && internalEqualityCheck(getCustomerIP(), requestIdentity.getCustomerIP()) && internalEqualityCheck(getDeviceType(), requestIdentity.getDeviceType()) && internalEqualityCheck(getRequestedContent(), requestIdentity.getRequestedContent()) && internalEqualityCheck(getMusicRequestIdentityContextToken(), requestIdentity.getMusicRequestIdentityContextToken()) && internalEqualityCheck(getMusicTerritory(), requestIdentity.getMusicTerritory());
    }

    public String getCustomerIP() {
        return this.customerIP;
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

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getRequestedContent() {
        return this.requestedContent;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMarketplaceId(), getDeviceId(), getCustomerId(), getSessionId(), getCustomerIP(), getDeviceType(), getRequestedContent(), getMusicRequestIdentityContextToken(), getMusicTerritory());
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setRequestedContent(String str) {
        this.requestedContent = str;
    }
}
