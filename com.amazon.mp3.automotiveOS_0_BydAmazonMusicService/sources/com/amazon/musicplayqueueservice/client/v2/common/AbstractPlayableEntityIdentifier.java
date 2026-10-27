package com.amazon.musicplayqueueservice.client.v2.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractPlayableEntityIdentifier extends AbstractIdentifier {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractIdentifier abstractIdentifier) {
        if (abstractIdentifier == null) {
            return -1;
        }
        if (abstractIdentifier == this) {
            return 0;
        }
        if (abstractIdentifier instanceof AbstractPlayableEntityIdentifier) {
            return super.compareTo(abstractIdentifier);
        }
        return 1;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public boolean equals(Object obj) {
        if (obj instanceof AbstractPlayableEntityIdentifier) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
