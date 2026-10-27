package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveDeviceRequest extends BaseDeviceRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveDeviceRequest");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (cirrusBaseRequestV2 instanceof RetrieveDeviceRequest) {
            return super.compareTo(cirrusBaseRequestV2);
        }
        return 1;
    }

    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof RetrieveDeviceRequest) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.stratus.BaseDeviceRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
