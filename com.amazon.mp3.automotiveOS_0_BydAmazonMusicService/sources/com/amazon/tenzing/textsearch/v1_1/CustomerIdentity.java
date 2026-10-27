package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerIdentity implements Comparable<CustomerIdentity> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.CustomerIdentity");
    private String customerId;
    private String deviceId;
    private String deviceType;
    private String musicRequestIdentityContextToken;
    private String sessionId;

    public static class Builder {
        protected String customerId;
        protected String deviceId;
        protected String deviceType;
        protected String musicRequestIdentityContextToken;
        protected String sessionId;

        public CustomerIdentity build() {
            CustomerIdentity customerIdentity = new CustomerIdentity();
            populate(customerIdentity);
            return customerIdentity;
        }

        protected void populate(CustomerIdentity customerIdentity) {
            customerIdentity.setCustomerId(this.customerId);
            customerIdentity.setDeviceId(this.deviceId);
            customerIdentity.setMusicRequestIdentityContextToken(this.musicRequestIdentityContextToken);
            customerIdentity.setSessionId(this.sessionId);
            customerIdentity.setDeviceType(this.deviceType);
        }

        public Builder withCustomerId(String str) {
            this.customerId = str;
            return this;
        }

        public Builder withDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder withDeviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder withMusicRequestIdentityContextToken(String str) {
            this.musicRequestIdentityContextToken = str;
            return this;
        }

        public Builder withSessionId(String str) {
            this.sessionId = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerIdentity customerIdentity) {
        if (customerIdentity == null) {
            return -1;
        }
        if (customerIdentity == this) {
            return 0;
        }
        String customerId = getCustomerId();
        String customerId2 = customerIdentity.getCustomerId();
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
        String deviceId2 = customerIdentity.getDeviceId();
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
        String musicRequestIdentityContextToken = getMusicRequestIdentityContextToken();
        String musicRequestIdentityContextToken2 = customerIdentity.getMusicRequestIdentityContextToken();
        if (musicRequestIdentityContextToken != musicRequestIdentityContextToken2) {
            if (musicRequestIdentityContextToken == null) {
                return -1;
            }
            if (musicRequestIdentityContextToken2 == null) {
                return 1;
            }
            int iCompareTo3 = musicRequestIdentityContextToken.compareTo(musicRequestIdentityContextToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String sessionId = getSessionId();
        String sessionId2 = customerIdentity.getSessionId();
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
        String deviceType = getDeviceType();
        String deviceType2 = customerIdentity.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo5 = deviceType.compareTo(deviceType2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerIdentity)) {
            return false;
        }
        CustomerIdentity customerIdentity = (CustomerIdentity) obj;
        return internalEqualityCheck(getCustomerId(), customerIdentity.getCustomerId()) && internalEqualityCheck(getDeviceId(), customerIdentity.getDeviceId()) && internalEqualityCheck(getMusicRequestIdentityContextToken(), customerIdentity.getMusicRequestIdentityContextToken()) && internalEqualityCheck(getSessionId(), customerIdentity.getSessionId()) && internalEqualityCheck(getDeviceType(), customerIdentity.getDeviceType());
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

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCustomerId(), getDeviceId(), getMusicRequestIdentityContextToken(), getSessionId(), getDeviceType());
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

    public void setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}
