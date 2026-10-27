package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseHierarchyV2Response implements Comparable<BrowseHierarchyV2Response> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseHierarchyV2Response");
    private List<BrowseHierarchyV2> browseHierarchy;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BrowseHierarchyV2Response browseHierarchyV2Response) {
        List<BrowseHierarchyV2> browseHierarchy;
        List<BrowseHierarchyV2> browseHierarchy2;
        if (browseHierarchyV2Response == null) {
            return -1;
        }
        if (browseHierarchyV2Response != this && (browseHierarchy = getBrowseHierarchy()) != (browseHierarchy2 = browseHierarchyV2Response.getBrowseHierarchy())) {
            if (browseHierarchy == null) {
                return -1;
            }
            if (browseHierarchy2 == null) {
                return 1;
            }
            if (browseHierarchy instanceof Comparable) {
                int iCompareTo = ((Comparable) browseHierarchy).compareTo(browseHierarchy2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!browseHierarchy.equals(browseHierarchy2)) {
                int iHashCode = browseHierarchy.hashCode();
                int iHashCode2 = browseHierarchy2.hashCode();
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
        if (obj instanceof BrowseHierarchyV2Response) {
            return internalEqualityCheck(getBrowseHierarchy(), ((BrowseHierarchyV2Response) obj).getBrowseHierarchy());
        }
        return false;
    }

    public List<BrowseHierarchyV2> getBrowseHierarchy() {
        return this.browseHierarchy;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBrowseHierarchy());
    }

    public void setBrowseHierarchy(List<BrowseHierarchyV2> list) {
        this.browseHierarchy = list;
    }
}
