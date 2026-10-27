package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogGenre extends Genre {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogGenre");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Genre, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (document instanceof CatalogGenre) {
            return super.compareTo(document);
        }
        return 1;
    }

    @Override // com.amazon.music.platform.model.Genre, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (obj instanceof CatalogGenre) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.music.platform.model.Genre, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
