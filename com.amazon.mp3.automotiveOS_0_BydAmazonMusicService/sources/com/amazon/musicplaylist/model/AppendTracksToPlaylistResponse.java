package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AppendTracksToPlaylistResponse extends EditResponse {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.AppendTracksToPlaylistResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.EditResponse, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EditResponse editResponse) {
        if (editResponse == null) {
            return -1;
        }
        if (editResponse == this) {
            return 0;
        }
        if (editResponse instanceof AppendTracksToPlaylistResponse) {
            return super.compareTo(editResponse);
        }
        return 1;
    }

    @Override // com.amazon.musicplaylist.model.EditResponse
    public boolean equals(Object obj) {
        if (obj instanceof AppendTracksToPlaylistResponse) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicplaylist.model.EditResponse
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
