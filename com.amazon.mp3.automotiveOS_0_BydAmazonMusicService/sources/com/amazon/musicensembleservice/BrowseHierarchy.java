package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseHierarchy implements Comparable<BrowseHierarchy> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseHierarchy");
    private List<BrowseObject> browseObjects;
    private String name;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BrowseHierarchy browseHierarchy) {
        if (browseHierarchy == null) {
            return -1;
        }
        if (browseHierarchy == this) {
            return 0;
        }
        List<BrowseObject> browseObjects = getBrowseObjects();
        List<BrowseObject> browseObjects2 = browseHierarchy.getBrowseObjects();
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
        String type = getType();
        String type2 = browseHierarchy.getType();
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
        String name = getName();
        String name2 = browseHierarchy.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo3 = name.compareTo(name2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BrowseHierarchy)) {
            return false;
        }
        BrowseHierarchy browseHierarchy = (BrowseHierarchy) obj;
        return internalEqualityCheck(getBrowseObjects(), browseHierarchy.getBrowseObjects()) && internalEqualityCheck(getType(), browseHierarchy.getType()) && internalEqualityCheck(getName(), browseHierarchy.getName());
    }

    public List<BrowseObject> getBrowseObjects() {
        return this.browseObjects;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBrowseObjects(), getType(), getName());
    }

    public void setBrowseObjects(List<BrowseObject> list) {
        this.browseObjects = list;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
