package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class Item implements Comparable<Item> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.Item");
    private Map<String, String> metadata;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Item item) {
        Map<String, String> metadata;
        Map<String, String> metadata2;
        if (item == null) {
            return -1;
        }
        if (item != this && (metadata = getMetadata()) != (metadata2 = item.getMetadata())) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            if (metadata instanceof Comparable) {
                int iCompareTo = ((Comparable) metadata).compareTo(metadata2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!metadata.equals(metadata2)) {
                int iHashCode = metadata.hashCode();
                int iHashCode2 = metadata2.hashCode();
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
        if (obj instanceof Item) {
            return internalEqualityCheck(getMetadata(), ((Item) obj).getMetadata());
        }
        return false;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMetadata());
    }

    public void setMetadata(Map<String, String> map) {
        this.metadata = map;
    }
}
