package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SortOrder implements Comparable<SortOrder> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.SortOrder");
    private String order;
    private String sort;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SortOrder sortOrder) {
        if (sortOrder == null) {
            return -1;
        }
        if (sortOrder == this) {
            return 0;
        }
        String sort = getSort();
        String sort2 = sortOrder.getSort();
        if (sort != sort2) {
            if (sort == null) {
                return -1;
            }
            if (sort2 == null) {
                return 1;
            }
            int iCompareTo = sort.compareTo(sort2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String order = getOrder();
        String order2 = sortOrder.getOrder();
        if (order != order2) {
            if (order == null) {
                return -1;
            }
            if (order2 == null) {
                return 1;
            }
            int iCompareTo2 = order.compareTo(order2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SortOrder)) {
            return false;
        }
        SortOrder sortOrder = (SortOrder) obj;
        return internalEqualityCheck(getSort(), sortOrder.getSort()) && internalEqualityCheck(getOrder(), sortOrder.getOrder());
    }

    public String getOrder() {
        return this.order;
    }

    public String getSort() {
        return this.sort;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSort(), getOrder());
    }
}
