package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LatestTermsOfUseUnacceptedError extends PlaylistServiceError {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.LatestTermsOfUseUnacceptedError");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.PlaylistServiceError, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaylistServiceError playlistServiceError) {
        if (playlistServiceError == null) {
            return -1;
        }
        if (playlistServiceError == this) {
            return 0;
        }
        if (playlistServiceError instanceof LatestTermsOfUseUnacceptedError) {
            return super.compareTo(playlistServiceError);
        }
        return 1;
    }

    @Override // com.amazon.musicplaylist.model.PlaylistServiceError
    public boolean equals(Object obj) {
        if (obj instanceof LatestTermsOfUseUnacceptedError) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicplaylist.model.PlaylistServiceError
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
