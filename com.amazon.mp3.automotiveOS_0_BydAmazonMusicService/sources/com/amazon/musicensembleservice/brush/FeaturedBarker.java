package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.AbstractBarker;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class FeaturedBarker extends AbstractBarker {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.FeaturedBarker");
    private String subtitle;

    public static class Builder extends AbstractBarker.Builder {
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
    @Override // com.amazon.musicensembleservice.brush.AbstractBarker, com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof FeaturedBarker)) {
            return 1;
        }
        String subtitle = getSubtitle();
        String subtitle2 = ((FeaturedBarker) entity).getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo = subtitle.compareTo(subtitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.AbstractBarker, com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (obj instanceof FeaturedBarker) {
            return super.equals(obj) && internalEqualityCheck(getSubtitle(), ((FeaturedBarker) obj).getSubtitle());
        }
        return false;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    @Override // com.amazon.musicensembleservice.brush.AbstractBarker, com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubtitle());
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }
}
