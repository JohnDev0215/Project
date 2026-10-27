package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractPlaylist implements Comparable<AbstractPlaylist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.AbstractPlaylist");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylist abstractPlaylist) {
        return abstractPlaylist == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof AbstractPlaylist;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
