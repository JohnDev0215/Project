package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogLyric extends Lyric {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogLyric");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Lyric, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (document instanceof CatalogLyric) {
            return super.compareTo(document);
        }
        return 1;
    }

    @Override // com.amazon.music.platform.model.Lyric, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (obj instanceof CatalogLyric) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.music.platform.model.Lyric, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
