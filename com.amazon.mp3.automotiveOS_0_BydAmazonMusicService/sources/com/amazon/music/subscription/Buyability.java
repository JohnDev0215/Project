package com.amazon.music.subscription;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Buyability implements Comparable<Buyability> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.Buyability");
    private Boolean isBuyable;
    private List<String> reasons;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Buyability buyability) {
        if (buyability == null) {
            return -1;
        }
        if (buyability == this) {
            return 0;
        }
        Boolean boolIsIsBuyable = isIsBuyable();
        Boolean boolIsIsBuyable2 = buyability.isIsBuyable();
        if (boolIsIsBuyable != boolIsIsBuyable2) {
            if (boolIsIsBuyable == null) {
                return -1;
            }
            if (boolIsIsBuyable2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsBuyable.compareTo(boolIsIsBuyable2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> reasons = getReasons();
        List<String> reasons2 = buyability.getReasons();
        if (reasons != reasons2) {
            if (reasons == null) {
                return -1;
            }
            if (reasons2 == null) {
                return 1;
            }
            if (reasons instanceof Comparable) {
                int iCompareTo2 = ((Comparable) reasons).compareTo(reasons2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!reasons.equals(reasons2)) {
                int iHashCode = reasons.hashCode();
                int iHashCode2 = reasons2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Buyability)) {
            return false;
        }
        Buyability buyability = (Buyability) obj;
        return internalEqualityCheck(isIsBuyable(), buyability.isIsBuyable()) && internalEqualityCheck(getReasons(), buyability.getReasons());
    }

    public List<String> getReasons() {
        return this.reasons;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIsBuyable(), getReasons());
    }

    public Boolean isIsBuyable() {
        return this.isBuyable;
    }

    public void setIsBuyable(Boolean bool) {
        this.isBuyable = bool;
    }

    public void setReasons(List<String> list) {
        this.reasons = list;
    }
}
