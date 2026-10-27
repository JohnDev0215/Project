package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TrackItem extends Item {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.TrackItem");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.libraryservice.v3.Item, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Item item) {
        if (item == null) {
            return -1;
        }
        if (item == this) {
            return 0;
        }
        if (item instanceof TrackItem) {
            return super.compareTo(item);
        }
        return 1;
    }

    @Override // com.amazon.cirrus.libraryservice.v3.Item
    public boolean equals(Object obj) {
        if (obj instanceof TrackItem) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.cirrus.libraryservice.v3.Item
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
