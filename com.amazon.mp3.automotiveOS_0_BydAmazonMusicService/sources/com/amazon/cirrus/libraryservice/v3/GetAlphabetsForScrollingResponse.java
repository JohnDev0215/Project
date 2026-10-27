package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetAlphabetsForScrollingResponse extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetAlphabetsForScrollingResponse");
    private List<AlphabetBucket> alphabetBuckets;

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
        if (!(cirrusBaseRequestV2 instanceof GetAlphabetsForScrollingResponse)) {
            return 1;
        }
        List<AlphabetBucket> alphabetBuckets = getAlphabetBuckets();
        List<AlphabetBucket> alphabetBuckets2 = ((GetAlphabetsForScrollingResponse) cirrusBaseRequestV2).getAlphabetBuckets();
        if (alphabetBuckets != alphabetBuckets2) {
            if (alphabetBuckets == null) {
                return -1;
            }
            if (alphabetBuckets2 == null) {
                return 1;
            }
            if (alphabetBuckets instanceof Comparable) {
                int iCompareTo = ((Comparable) alphabetBuckets).compareTo(alphabetBuckets2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!alphabetBuckets.equals(alphabetBuckets2)) {
                int iHashCode = alphabetBuckets.hashCode();
                int iHashCode2 = alphabetBuckets2.hashCode();
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
        if (obj instanceof GetAlphabetsForScrollingResponse) {
            return super.equals(obj) && internalEqualityCheck(getAlphabetBuckets(), ((GetAlphabetsForScrollingResponse) obj).getAlphabetBuckets());
        }
        return false;
    }

    public List<AlphabetBucket> getAlphabetBuckets() {
        return this.alphabetBuckets;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAlphabetBuckets());
    }

    public void setAlphabetBuckets(List<AlphabetBucket> list) {
        this.alphabetBuckets = list;
    }
}
