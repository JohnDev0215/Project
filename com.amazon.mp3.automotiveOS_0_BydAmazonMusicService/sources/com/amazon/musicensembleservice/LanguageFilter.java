package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageFilter extends BrowseHierarchyV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.LanguageFilter");
    private List<LanguageObject> languageObjects;

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
        if (!(browseHierarchyV2 instanceof LanguageFilter)) {
            return 1;
        }
        List<LanguageObject> languageObjects = getLanguageObjects();
        List<LanguageObject> languageObjects2 = ((LanguageFilter) browseHierarchyV2).getLanguageObjects();
        if (languageObjects != languageObjects2) {
            if (languageObjects == null) {
                return -1;
            }
            if (languageObjects2 == null) {
                return 1;
            }
            if (languageObjects instanceof Comparable) {
                int iCompareTo = ((Comparable) languageObjects).compareTo(languageObjects2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!languageObjects.equals(languageObjects2)) {
                int iHashCode = languageObjects.hashCode();
                int iHashCode2 = languageObjects2.hashCode();
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
        if (obj instanceof LanguageFilter) {
            return super.equals(obj) && internalEqualityCheck(getLanguageObjects(), ((LanguageFilter) obj).getLanguageObjects());
        }
        return false;
    }

    public List<LanguageObject> getLanguageObjects() {
        return this.languageObjects;
    }

    @Override // com.amazon.musicensembleservice.BrowseHierarchyV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLanguageObjects());
    }

    public void setLanguageObjects(List<LanguageObject> list) {
        this.languageObjects = list;
    }
}
