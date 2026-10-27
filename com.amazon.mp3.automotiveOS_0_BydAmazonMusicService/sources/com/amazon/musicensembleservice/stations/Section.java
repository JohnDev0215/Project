package com.amazon.musicensembleservice.stations;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Section implements Comparable<Section> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.stations.Section");
    private List<String> categoryMapIds;
    private String sectionId;
    private String sectionTitle;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Section section) {
        if (section == null) {
            return -1;
        }
        if (section == this) {
            return 0;
        }
        String sectionTitle = getSectionTitle();
        String sectionTitle2 = section.getSectionTitle();
        if (sectionTitle != sectionTitle2) {
            if (sectionTitle == null) {
                return -1;
            }
            if (sectionTitle2 == null) {
                return 1;
            }
            int iCompareTo = sectionTitle.compareTo(sectionTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> categoryMapIds = getCategoryMapIds();
        List<String> categoryMapIds2 = section.getCategoryMapIds();
        if (categoryMapIds != categoryMapIds2) {
            if (categoryMapIds == null) {
                return -1;
            }
            if (categoryMapIds2 == null) {
                return 1;
            }
            if (categoryMapIds instanceof Comparable) {
                int iCompareTo2 = ((Comparable) categoryMapIds).compareTo(categoryMapIds2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!categoryMapIds.equals(categoryMapIds2)) {
                int iHashCode = categoryMapIds.hashCode();
                int iHashCode2 = categoryMapIds2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String sectionId = getSectionId();
        String sectionId2 = section.getSectionId();
        if (sectionId != sectionId2) {
            if (sectionId == null) {
                return -1;
            }
            if (sectionId2 == null) {
                return 1;
            }
            int iCompareTo3 = sectionId.compareTo(sectionId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Section)) {
            return false;
        }
        Section section = (Section) obj;
        return internalEqualityCheck(getSectionTitle(), section.getSectionTitle()) && internalEqualityCheck(getCategoryMapIds(), section.getCategoryMapIds()) && internalEqualityCheck(getSectionId(), section.getSectionId());
    }

    public List<String> getCategoryMapIds() {
        return this.categoryMapIds;
    }

    public String getSectionId() {
        return this.sectionId;
    }

    public String getSectionTitle() {
        return this.sectionTitle;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSectionTitle(), getCategoryMapIds(), getSectionId());
    }

    public void setCategoryMapIds(List<String> list) {
        this.categoryMapIds = list;
    }

    public void setSectionId(String str) {
        this.sectionId = str;
    }

    public void setSectionTitle(String str) {
        this.sectionTitle = str;
    }
}
