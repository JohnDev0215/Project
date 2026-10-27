package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.Entity;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MusicEntity extends Entity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.MusicEntity");
    private String label;
    private String primaryTitle;
    private String secondaryTitle;
    private String tertiaryTitle;

    public static class Builder extends Entity.Builder {
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof MusicEntity)) {
            return 1;
        }
        MusicEntity musicEntity = (MusicEntity) entity;
        String primaryTitle = getPrimaryTitle();
        String primaryTitle2 = musicEntity.getPrimaryTitle();
        if (primaryTitle != primaryTitle2) {
            if (primaryTitle == null) {
                return -1;
            }
            if (primaryTitle2 == null) {
                return 1;
            }
            int iCompareTo = primaryTitle.compareTo(primaryTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String tertiaryTitle = getTertiaryTitle();
        String tertiaryTitle2 = musicEntity.getTertiaryTitle();
        if (tertiaryTitle != tertiaryTitle2) {
            if (tertiaryTitle == null) {
                return -1;
            }
            if (tertiaryTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = tertiaryTitle.compareTo(tertiaryTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String secondaryTitle = getSecondaryTitle();
        String secondaryTitle2 = musicEntity.getSecondaryTitle();
        if (secondaryTitle != secondaryTitle2) {
            if (secondaryTitle == null) {
                return -1;
            }
            if (secondaryTitle2 == null) {
                return 1;
            }
            int iCompareTo3 = secondaryTitle.compareTo(secondaryTitle2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String label = getLabel();
        String label2 = musicEntity.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo4 = label.compareTo(label2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof MusicEntity)) {
            return false;
        }
        MusicEntity musicEntity = (MusicEntity) obj;
        return super.equals(obj) && internalEqualityCheck(getPrimaryTitle(), musicEntity.getPrimaryTitle()) && internalEqualityCheck(getTertiaryTitle(), musicEntity.getTertiaryTitle()) && internalEqualityCheck(getSecondaryTitle(), musicEntity.getSecondaryTitle()) && internalEqualityCheck(getLabel(), musicEntity.getLabel());
    }

    public String getLabel() {
        return this.label;
    }

    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPrimaryTitle(), getTertiaryTitle(), getSecondaryTitle(), getLabel());
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setPrimaryTitle(String str) {
        this.primaryTitle = str;
    }

    public void setSecondaryTitle(String str) {
        this.secondaryTitle = str;
    }

    public void setTertiaryTitle(String str) {
        this.tertiaryTitle = str;
    }
}
