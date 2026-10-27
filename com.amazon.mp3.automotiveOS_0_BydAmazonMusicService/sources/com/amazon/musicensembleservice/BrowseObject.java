package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class BrowseObject implements Comparable<BrowseObject> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.BrowseObject");
    private String browseId;
    private String browseName;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BrowseObject browseObject) {
        if (browseObject == null) {
            return -1;
        }
        if (browseObject == this) {
            return 0;
        }
        String browseName = getBrowseName();
        String browseName2 = browseObject.getBrowseName();
        if (browseName != browseName2) {
            if (browseName == null) {
                return -1;
            }
            if (browseName2 == null) {
                return 1;
            }
            int iCompareTo = browseName.compareTo(browseName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String browseId = getBrowseId();
        String browseId2 = browseObject.getBrowseId();
        if (browseId != browseId2) {
            if (browseId == null) {
                return -1;
            }
            if (browseId2 == null) {
                return 1;
            }
            int iCompareTo2 = browseId.compareTo(browseId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BrowseObject)) {
            return false;
        }
        BrowseObject browseObject = (BrowseObject) obj;
        return internalEqualityCheck(getBrowseName(), browseObject.getBrowseName()) && internalEqualityCheck(getBrowseId(), browseObject.getBrowseId());
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public String getBrowseName() {
        return this.browseName;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBrowseName(), getBrowseId());
    }

    public void setBrowseId(String str) {
        this.browseId = str;
    }

    public void setBrowseName(String str) {
        this.browseName = str;
    }
}
