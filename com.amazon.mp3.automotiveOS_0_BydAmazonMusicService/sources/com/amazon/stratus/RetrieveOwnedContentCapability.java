package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveOwnedContentCapability extends DeviceCapabilityDetailsBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveOwnedContentCapability");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.DeviceCapabilityDetailsBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceCapabilityDetailsBase deviceCapabilityDetailsBase) {
        if (deviceCapabilityDetailsBase == null) {
            return -1;
        }
        if (deviceCapabilityDetailsBase == this) {
            return 0;
        }
        if (deviceCapabilityDetailsBase instanceof RetrieveOwnedContentCapability) {
            return super.compareTo(deviceCapabilityDetailsBase);
        }
        return 1;
    }

    @Override // com.amazon.stratus.DeviceCapabilityDetailsBase
    public boolean equals(Object obj) {
        if (obj instanceof RetrieveOwnedContentCapability) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.stratus.DeviceCapabilityDetailsBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
