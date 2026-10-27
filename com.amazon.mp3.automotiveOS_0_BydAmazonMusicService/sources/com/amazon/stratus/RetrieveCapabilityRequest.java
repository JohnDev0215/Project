package com.amazon.stratus;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCapabilityRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrieveCapabilityRequest");
    private List<String> capabilityTypes;

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
        if (!(cirrusBaseRequestV2 instanceof RetrieveCapabilityRequest)) {
            return 1;
        }
        List<String> capabilityTypes = getCapabilityTypes();
        List<String> capabilityTypes2 = ((RetrieveCapabilityRequest) cirrusBaseRequestV2).getCapabilityTypes();
        if (capabilityTypes != capabilityTypes2) {
            if (capabilityTypes == null) {
                return -1;
            }
            if (capabilityTypes2 == null) {
                return 1;
            }
            if (capabilityTypes instanceof Comparable) {
                int iCompareTo = ((Comparable) capabilityTypes).compareTo(capabilityTypes2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!capabilityTypes.equals(capabilityTypes2)) {
                int iHashCode = capabilityTypes.hashCode();
                int iHashCode2 = capabilityTypes2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof RetrieveCapabilityRequest) {
            return super.equals(obj) && internalEqualityCheck(getCapabilityTypes(), ((RetrieveCapabilityRequest) obj).getCapabilityTypes());
        }
        return false;
    }

    public List<String> getCapabilityTypes() {
        return this.capabilityTypes;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCapabilityTypes());
    }

    public void setCapabilityTypes(List<String> list) {
        this.capabilityTypes = list;
    }
}
