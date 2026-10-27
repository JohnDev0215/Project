package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BrowseHierarchyV2 implements Comparable<BrowseHierarchyV2> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseHierarchyV2");
    private String name;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BrowseHierarchyV2 browseHierarchyV2) {
        if (browseHierarchyV2 == null) {
            return -1;
        }
        if (browseHierarchyV2 == this) {
            return 0;
        }
        String name = getName();
        String name2 = browseHierarchyV2.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo = name.compareTo(name2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String type = getType();
        String type2 = browseHierarchyV2.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo2 = type.compareTo(type2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BrowseHierarchyV2)) {
            return false;
        }
        BrowseHierarchyV2 browseHierarchyV2 = (BrowseHierarchyV2) obj;
        return internalEqualityCheck(getName(), browseHierarchyV2.getName()) && internalEqualityCheck(getType(), browseHierarchyV2.getType());
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getName(), getType());
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
