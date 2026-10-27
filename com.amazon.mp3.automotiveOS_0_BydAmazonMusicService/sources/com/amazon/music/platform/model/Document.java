package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Document implements Comparable<Document> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Document");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        return document == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof Document;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
