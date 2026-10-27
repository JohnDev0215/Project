package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class EntityPlaylistMetadata extends SharedPlaylistMetadata {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.EntityPlaylistMetadata");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.SharedPlaylistMetadata, com.amazon.musicplaylist.model.AbstractPlaylistMetadata, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylistMetadata abstractPlaylistMetadata) {
        if (abstractPlaylistMetadata == null) {
            return -1;
        }
        if (abstractPlaylistMetadata == this) {
            return 0;
        }
        if (abstractPlaylistMetadata instanceof EntityPlaylistMetadata) {
            return super.compareTo(abstractPlaylistMetadata);
        }
        return 1;
    }

    @Override // com.amazon.musicplaylist.model.SharedPlaylistMetadata, com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public boolean equals(Object obj) {
        if (obj instanceof EntityPlaylistMetadata) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicplaylist.model.SharedPlaylistMetadata, com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
