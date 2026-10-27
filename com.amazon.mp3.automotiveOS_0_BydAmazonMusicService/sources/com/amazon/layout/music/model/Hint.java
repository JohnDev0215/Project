package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Hint implements Comparable<Hint> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Hint");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        return hint == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof Hint;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
