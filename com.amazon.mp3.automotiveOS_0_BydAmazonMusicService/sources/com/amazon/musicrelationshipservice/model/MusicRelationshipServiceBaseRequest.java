package com.amazon.musicrelationshipservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class MusicRelationshipServiceBaseRequest implements Comparable<MusicRelationshipServiceBaseRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicrelationshipservice.model.MusicRelationshipServiceBaseRequest");
    private boolean childRequest;
    private String customerId;
    private String deviceId;
    private String deviceType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicRelationshipServiceBaseRequest musicRelationshipServiceBaseRequest) {
        if (musicRelationshipServiceBaseRequest == null) {
            return -1;
        }
        if (musicRelationshipServiceBaseRequest == this) {
            return 0;
        }
        String deviceType = getDeviceType();
        String deviceType2 = musicRelationshipServiceBaseRequest.getDeviceType();
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
        String deviceId = getDeviceId();
        String deviceId2 = musicRelationshipServiceBaseRequest.getDeviceId();
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
        String customerId2 = musicRelationshipServiceBaseRequest.getCustomerId();
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
        if (isChildRequest() || !musicRelationshipServiceBaseRequest.isChildRequest()) {
            return (!isChildRequest() || musicRelationshipServiceBaseRequest.isChildRequest()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicRelationshipServiceBaseRequest)) {
            return false;
        }
        MusicRelationshipServiceBaseRequest musicRelationshipServiceBaseRequest = (MusicRelationshipServiceBaseRequest) obj;
        return internalEqualityCheck(getDeviceType(), musicRelationshipServiceBaseRequest.getDeviceType()) && internalEqualityCheck(getDeviceId(), musicRelationshipServiceBaseRequest.getDeviceId()) && internalEqualityCheck(getCustomerId(), musicRelationshipServiceBaseRequest.getCustomerId()) && internalEqualityCheck(Boolean.valueOf(isChildRequest()), Boolean.valueOf(musicRelationshipServiceBaseRequest.isChildRequest()));
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeviceType(), getDeviceId(), getCustomerId(), Boolean.valueOf(isChildRequest()));
    }

    public boolean isChildRequest() {
        return this.childRequest;
    }

    public void setChildRequest(boolean z) {
        this.childRequest = z;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
