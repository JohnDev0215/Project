package com.amazon.musicplayqueueservice.client.v2.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractIdentifier implements Comparable<AbstractIdentifier> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier");

    public static abstract class Builder {
        protected void populate(AbstractIdentifier abstractIdentifier) {
        }
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractIdentifier abstractIdentifier) {
        return abstractIdentifier == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof AbstractIdentifier;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
