package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseFilter extends BrowseHierarchyV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseFilter");
    private List<BrowseObject> browseObjects;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.BrowseHierarchyV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BrowseHierarchyV2 browseHierarchyV2) {
        if (browseHierarchyV2 == null) {
            return -1;
        }
        if (browseHierarchyV2 == this) {
            return 0;
        }
        if (!(browseHierarchyV2 instanceof BrowseFilter)) {
            return 1;
        }
        List<BrowseObject> browseObjects = getBrowseObjects();
        List<BrowseObject> browseObjects2 = ((BrowseFilter) browseHierarchyV2).getBrowseObjects();
        if (browseObjects != browseObjects2) {
            if (browseObjects == null) {
                return -1;
            }
            if (browseObjects2 == null) {
                return 1;
            }
            if (browseObjects instanceof Comparable) {
                int iCompareTo = ((Comparable) browseObjects).compareTo(browseObjects2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!browseObjects.equals(browseObjects2)) {
                int iHashCode = browseObjects.hashCode();
                int iHashCode2 = browseObjects2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(browseHierarchyV2);
    }

    @Override // com.amazon.musicensembleservice.BrowseHierarchyV2
    public boolean equals(Object obj) {
        if (obj instanceof BrowseFilter) {
            return super.equals(obj) && internalEqualityCheck(getBrowseObjects(), ((BrowseFilter) obj).getBrowseObjects());
        }
        return false;
    }

    public List<BrowseObject> getBrowseObjects() {
        return this.browseObjects;
    }

    @Override // com.amazon.musicensembleservice.BrowseHierarchyV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBrowseObjects());
    }

    public void setBrowseObjects(List<BrowseObject> list) {
        this.browseObjects = list;
    }
}
