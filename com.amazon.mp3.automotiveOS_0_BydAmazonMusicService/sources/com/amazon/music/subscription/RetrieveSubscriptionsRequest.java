package com.amazon.music.subscription;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveSubscriptionsRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.RetrieveSubscriptionsRequest");
    private Boolean activeOnly;

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
        if (!(cirrusBaseRequestV2 instanceof RetrieveSubscriptionsRequest)) {
            return 1;
        }
        Boolean boolIsActiveOnly = isActiveOnly();
        Boolean boolIsActiveOnly2 = ((RetrieveSubscriptionsRequest) cirrusBaseRequestV2).isActiveOnly();
        if (boolIsActiveOnly != boolIsActiveOnly2) {
            if (boolIsActiveOnly == null) {
                return -1;
            }
            if (boolIsActiveOnly2 == null) {
                return 1;
            }
            int iCompareTo = boolIsActiveOnly.compareTo(boolIsActiveOnly2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof RetrieveSubscriptionsRequest) {
            return super.equals(obj) && internalEqualityCheck(isActiveOnly(), ((RetrieveSubscriptionsRequest) obj).isActiveOnly());
        }
        return false;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isActiveOnly());
    }

    public Boolean isActiveOnly() {
        return this.activeOnly;
    }

    public void setActiveOnly(Boolean bool) {
        this.activeOnly = bool;
    }
}
