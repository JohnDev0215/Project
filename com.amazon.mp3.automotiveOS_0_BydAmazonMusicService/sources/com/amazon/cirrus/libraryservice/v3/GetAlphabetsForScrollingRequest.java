package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetAlphabetsForScrollingRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetAlphabetsForScrollingRequest");
    private String entityType;

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
        if (!(cirrusBaseRequestV2 instanceof GetAlphabetsForScrollingRequest)) {
            return 1;
        }
        String entityType = getEntityType();
        String entityType2 = ((GetAlphabetsForScrollingRequest) cirrusBaseRequestV2).getEntityType();
        if (entityType != entityType2) {
            if (entityType == null) {
                return -1;
            }
            if (entityType2 == null) {
                return 1;
            }
            int iCompareTo = entityType.compareTo(entityType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof GetAlphabetsForScrollingRequest) {
            return super.equals(obj) && internalEqualityCheck(getEntityType(), ((GetAlphabetsForScrollingRequest) obj).getEntityType());
        }
        return false;
    }

    public String getEntityType() {
        return this.entityType;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getEntityType());
    }
}
