package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Button extends NavigationEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Button");
    private List<Image> images;

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
        if (!(entity instanceof Button)) {
            return 1;
        }
        List<Image> images = getImages();
        List<Image> images2 = ((Button) entity).getImages();
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
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (obj instanceof Button) {
            return super.equals(obj) && internalEqualityCheck(getImages(), ((Button) obj).getImages());
        }
        return false;
    }

    public List<Image> getImages() {
        return this.images;
    }

    @Override // com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImages());
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }
}
