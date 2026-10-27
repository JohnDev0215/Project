package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetLibraryCountsRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetLibraryCountsRequest");
    private List<String> counts;

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
        if (!(cirrusBaseRequestV2 instanceof GetLibraryCountsRequest)) {
            return 1;
        }
        List<String> counts = getCounts();
        List<String> counts2 = ((GetLibraryCountsRequest) cirrusBaseRequestV2).getCounts();
        if (counts != counts2) {
            if (counts == null) {
                return -1;
            }
            if (counts2 == null) {
                return 1;
            }
            if (counts instanceof Comparable) {
                int iCompareTo = ((Comparable) counts).compareTo(counts2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!counts.equals(counts2)) {
                int iHashCode = counts.hashCode();
                int iHashCode2 = counts2.hashCode();
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
        if (obj instanceof GetLibraryCountsRequest) {
            return super.equals(obj) && internalEqualityCheck(getCounts(), ((GetLibraryCountsRequest) obj).getCounts());
        }
        return false;
    }

    public List<String> getCounts() {
        return this.counts;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCounts());
    }
}
