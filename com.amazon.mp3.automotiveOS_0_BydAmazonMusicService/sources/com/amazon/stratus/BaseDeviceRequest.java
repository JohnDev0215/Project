package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseDeviceRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.BaseDeviceRequest");
    private String targetDeviceId;
    private String targetDeviceType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof BaseDeviceRequest)) {
            return 1;
        }
        BaseDeviceRequest baseDeviceRequest = (BaseDeviceRequest) cirrusBaseRequestV2;
        String targetDeviceType = getTargetDeviceType();
        String targetDeviceType2 = baseDeviceRequest.getTargetDeviceType();
        if (targetDeviceType != targetDeviceType2) {
            if (targetDeviceType == null) {
                return -1;
            }
            if (targetDeviceType2 == null) {
                return 1;
            }
            int iCompareTo = targetDeviceType.compareTo(targetDeviceType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String targetDeviceId = getTargetDeviceId();
        String targetDeviceId2 = baseDeviceRequest.getTargetDeviceId();
        if (targetDeviceId != targetDeviceId2) {
            if (targetDeviceId == null) {
                return -1;
            }
            if (targetDeviceId2 == null) {
                return 1;
            }
            int iCompareTo2 = targetDeviceId.compareTo(targetDeviceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseDeviceRequest)) {
            return false;
        }
        BaseDeviceRequest baseDeviceRequest = (BaseDeviceRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getTargetDeviceType(), baseDeviceRequest.getTargetDeviceType()) && internalEqualityCheck(getTargetDeviceId(), baseDeviceRequest.getTargetDeviceId());
    }

    public String getTargetDeviceId() {
        return this.targetDeviceId;
    }

    public String getTargetDeviceType() {
        return this.targetDeviceType;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTargetDeviceType(), getTargetDeviceId());
    }

    public void setTargetDeviceId(String str) {
        this.targetDeviceId = str;
    }

    public void setTargetDeviceType(String str) {
        this.targetDeviceType = str;
    }
}
