package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AuthorizationRequiredRequest implements Comparable<AuthorizationRequiredRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.AuthorizationRequiredRequest");
    private int apiRevision;
    private String customerId;
    private String dependentServiceName;
    private String deviceId;
    private String deviceType;

    public static abstract class Builder {
        protected int apiRevision;
        protected String customerId;
        protected String dependentServiceName;
        protected String deviceId;
        protected String deviceType;

        protected void populate(AuthorizationRequiredRequest authorizationRequiredRequest) {
            authorizationRequiredRequest.setCustomerId(this.customerId);
            authorizationRequiredRequest.setDeviceId(this.deviceId);
            authorizationRequiredRequest.setDependentServiceName(this.dependentServiceName);
            authorizationRequiredRequest.setApiRevision(this.apiRevision);
            authorizationRequiredRequest.setDeviceType(this.deviceType);
        }

        public Builder withApiRevision(int i) {
            this.apiRevision = i;
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
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AuthorizationRequiredRequest authorizationRequiredRequest) {
        if (authorizationRequiredRequest == null) {
            return -1;
        }
        if (authorizationRequiredRequest == this) {
            return 0;
        }
        String customerId = getCustomerId();
        String customerId2 = authorizationRequiredRequest.getCustomerId();
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
        String deviceId2 = authorizationRequiredRequest.getDeviceId();
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
        String dependentServiceName = getDependentServiceName();
        String dependentServiceName2 = authorizationRequiredRequest.getDependentServiceName();
        if (dependentServiceName != dependentServiceName2) {
            if (dependentServiceName == null) {
                return -1;
            }
            if (dependentServiceName2 == null) {
                return 1;
            }
            int iCompareTo3 = dependentServiceName.compareTo(dependentServiceName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (getApiRevision() < authorizationRequiredRequest.getApiRevision()) {
            return -1;
        }
        if (getApiRevision() > authorizationRequiredRequest.getApiRevision()) {
            return 1;
        }
        String deviceType = getDeviceType();
        String deviceType2 = authorizationRequiredRequest.getDeviceType();
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthorizationRequiredRequest)) {
            return false;
        }
        AuthorizationRequiredRequest authorizationRequiredRequest = (AuthorizationRequiredRequest) obj;
        return internalEqualityCheck(getCustomerId(), authorizationRequiredRequest.getCustomerId()) && internalEqualityCheck(getDeviceId(), authorizationRequiredRequest.getDeviceId()) && internalEqualityCheck(getDependentServiceName(), authorizationRequiredRequest.getDependentServiceName()) && internalEqualityCheck(Integer.valueOf(getApiRevision()), Integer.valueOf(authorizationRequiredRequest.getApiRevision())) && internalEqualityCheck(getDeviceType(), authorizationRequiredRequest.getDeviceType());
    }

    public int getApiRevision() {
        return this.apiRevision;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDependentServiceName() {
        return this.dependentServiceName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCustomerId(), getDeviceId(), getDependentServiceName(), Integer.valueOf(getApiRevision()), getDeviceType());
    }

    public void setApiRevision(int i) {
        this.apiRevision = i;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setDependentServiceName(String str) {
        this.dependentServiceName = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
