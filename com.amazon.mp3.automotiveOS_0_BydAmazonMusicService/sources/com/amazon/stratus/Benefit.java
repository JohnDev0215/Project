package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Benefit implements Comparable<Benefit> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.Benefit");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Benefit benefit) {
        return benefit == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof Benefit;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
