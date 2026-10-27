package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistTrackEntryMetadata implements Comparable<PlaylistTrackEntryMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.PlaylistTrackEntryMetadata");
    private boolean musicSubscription;
    private boolean newSinceLastMajorVersion;
    private boolean prime;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaylistTrackEntryMetadata playlistTrackEntryMetadata) {
        if (playlistTrackEntryMetadata == null) {
            return -1;
        }
        if (playlistTrackEntryMetadata == this) {
            return 0;
        }
        if (!isNewSinceLastMajorVersion() && playlistTrackEntryMetadata.isNewSinceLastMajorVersion()) {
            return -1;
        }
        if (isNewSinceLastMajorVersion() && !playlistTrackEntryMetadata.isNewSinceLastMajorVersion()) {
            return 1;
        }
        if (!isMusicSubscription() && playlistTrackEntryMetadata.isMusicSubscription()) {
            return -1;
        }
        if (isMusicSubscription() && !playlistTrackEntryMetadata.isMusicSubscription()) {
            return 1;
        }
        if (isPrime() || !playlistTrackEntryMetadata.isPrime()) {
            return (!isPrime() || playlistTrackEntryMetadata.isPrime()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistTrackEntryMetadata)) {
            return false;
        }
        PlaylistTrackEntryMetadata playlistTrackEntryMetadata = (PlaylistTrackEntryMetadata) obj;
        return internalEqualityCheck(Boolean.valueOf(isNewSinceLastMajorVersion()), Boolean.valueOf(playlistTrackEntryMetadata.isNewSinceLastMajorVersion())) && internalEqualityCheck(Boolean.valueOf(isMusicSubscription()), Boolean.valueOf(playlistTrackEntryMetadata.isMusicSubscription())) && internalEqualityCheck(Boolean.valueOf(isPrime()), Boolean.valueOf(playlistTrackEntryMetadata.isPrime()));
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Boolean.valueOf(isNewSinceLastMajorVersion()), Boolean.valueOf(isMusicSubscription()), Boolean.valueOf(isPrime()));
    }

    public boolean isMusicSubscription() {
        return this.musicSubscription;
    }

    public boolean isNewSinceLastMajorVersion() {
        return this.newSinceLastMajorVersion;
    }

    public boolean isPrime() {
        return this.prime;
    }

    public void setMusicSubscription(boolean z) {
        this.musicSubscription = z;
    }

    public void setNewSinceLastMajorVersion(boolean z) {
        this.newSinceLastMajorVersion = z;
    }

    public void setPrime(boolean z) {
        this.prime = z;
    }
}
