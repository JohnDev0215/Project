package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MoodsAndActivities extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.MoodsAndActivities");
    private String browseId;
    private Boolean selected;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof MoodsAndActivities)) {
            return 1;
        }
        MoodsAndActivities moodsAndActivities = (MoodsAndActivities) entity;
        Boolean boolIsSelected = isSelected();
        Boolean boolIsSelected2 = moodsAndActivities.isSelected();
        if (boolIsSelected != boolIsSelected2) {
            if (boolIsSelected == null) {
                return -1;
            }
            if (boolIsSelected2 == null) {
                return 1;
            }
            int iCompareTo = boolIsSelected.compareTo(boolIsSelected2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = moodsAndActivities.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String browseId = getBrowseId();
        String browseId2 = moodsAndActivities.getBrowseId();
        if (browseId != browseId2) {
            if (browseId == null) {
                return -1;
            }
            if (browseId2 == null) {
                return 1;
            }
            int iCompareTo3 = browseId.compareTo(browseId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof MoodsAndActivities)) {
            return false;
        }
        MoodsAndActivities moodsAndActivities = (MoodsAndActivities) obj;
        return super.equals(obj) && internalEqualityCheck(isSelected(), moodsAndActivities.isSelected()) && internalEqualityCheck(getTitle(), moodsAndActivities.getTitle()) && internalEqualityCheck(getBrowseId(), moodsAndActivities.getBrowseId());
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isSelected(), getTitle(), getBrowseId());
    }

    public Boolean isSelected() {
        return this.selected;
    }

    public void setBrowseId(String str) {
        this.browseId = str;
    }

    public void setSelected(Boolean bool) {
        this.selected = bool;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
