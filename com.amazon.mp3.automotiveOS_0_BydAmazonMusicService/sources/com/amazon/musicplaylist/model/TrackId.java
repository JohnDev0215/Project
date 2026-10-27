package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TrackId implements Comparable<TrackId> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.TrackId");

    public static abstract class Builder {
        protected void populate(TrackId trackId) {
        }
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackId trackId) {
        return trackId == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof TrackId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
