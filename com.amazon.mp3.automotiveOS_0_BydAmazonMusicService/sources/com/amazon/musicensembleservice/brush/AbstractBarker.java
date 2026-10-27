package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.NavigationEntity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AbstractBarker extends NavigationEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.AbstractBarker");
    private String altDescription;
    private List<Image> images;

    public static class Builder extends NavigationEntity.Builder {
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
    @Override // com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof AbstractBarker)) {
            return 1;
        }
        AbstractBarker abstractBarker = (AbstractBarker) entity;
        List<Image> images = getImages();
        List<Image> images2 = abstractBarker.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo = ((Comparable) images).compareTo(images2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!images.equals(images2)) {
                int iHashCode = images.hashCode();
                int iHashCode2 = images2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String altDescription = getAltDescription();
        String altDescription2 = abstractBarker.getAltDescription();
        if (altDescription != altDescription2) {
            if (altDescription == null) {
                return -1;
            }
            if (altDescription2 == null) {
                return 1;
            }
            int iCompareTo2 = altDescription.compareTo(altDescription2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractBarker)) {
            return false;
        }
        AbstractBarker abstractBarker = (AbstractBarker) obj;
        return super.equals(obj) && internalEqualityCheck(getImages(), abstractBarker.getImages()) && internalEqualityCheck(getAltDescription(), abstractBarker.getAltDescription());
    }

    public String getAltDescription() {
        return this.altDescription;
    }

    public List<Image> getImages() {
        return this.images;
    }

    @Override // com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImages(), getAltDescription());
    }

    public void setAltDescription(String str) {
        this.altDescription = str;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }
}
