package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractActionStrategy implements Comparable<AbstractActionStrategy> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy");

    public static abstract class Builder {
        protected void populate(AbstractActionStrategy abstractActionStrategy) {
        }
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractActionStrategy abstractActionStrategy) {
        return abstractActionStrategy == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof AbstractActionStrategy;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
