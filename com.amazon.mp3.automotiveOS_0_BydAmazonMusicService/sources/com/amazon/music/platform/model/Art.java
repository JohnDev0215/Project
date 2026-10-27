package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Art implements Comparable<Art> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Art");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Art art) {
        return art == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof Art;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
