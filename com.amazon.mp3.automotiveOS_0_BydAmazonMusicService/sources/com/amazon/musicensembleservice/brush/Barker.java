package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Barker extends AbstractBarker {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Barker");

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
        if (entity instanceof Barker) {
            return super.compareTo(entity);
        }
        return 1;
    }

    @Override // com.amazon.musicensembleservice.brush.AbstractBarker, com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (obj instanceof Barker) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicensembleservice.brush.AbstractBarker, com.amazon.musicensembleservice.brush.NavigationEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
