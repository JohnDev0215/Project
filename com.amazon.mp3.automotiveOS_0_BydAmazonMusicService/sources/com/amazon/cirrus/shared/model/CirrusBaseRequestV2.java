package com.amazon.cirrus.shared.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CirrusBaseRequestV2 implements Comparable<CirrusBaseRequestV2> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.shared.model.CirrusBaseRequestV2");
    private String customerId;
    private String deviceId;
    private String deviceType;

    public static abstract class Builder {
        protected String customerId;
        protected String deviceId;
        protected String deviceType;

        protected void populate(CirrusBaseRequestV2 cirrusBaseRequestV2) {
            cirrusBaseRequestV2.setDeviceType(this.deviceType);
            cirrusBaseRequestV2.setCustomerId(this.customerId);
            cirrusBaseRequestV2.setDeviceId(this.deviceId);
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
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        String deviceType = getDeviceType();
        String deviceType2 = cirrusBaseRequestV2.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo = deviceType.compareTo(deviceType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = cirrusBaseRequestV2.getCustomerId();
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
        String deviceId = getDeviceId();
        String deviceId2 = cirrusBaseRequestV2.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo3 = deviceId.compareTo(deviceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CirrusBaseRequestV2)) {
            return false;
        }
        CirrusBaseRequestV2 cirrusBaseRequestV2 = (CirrusBaseRequestV2) obj;
        return internalEqualityCheck(getDeviceType(), cirrusBaseRequestV2.getDeviceType()) && internalEqualityCheck(getCustomerId(), cirrusBaseRequestV2.getCustomerId()) && internalEqualityCheck(getDeviceId(), cirrusBaseRequestV2.getDeviceId());
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeviceType(), getCustomerId(), getDeviceId());
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
