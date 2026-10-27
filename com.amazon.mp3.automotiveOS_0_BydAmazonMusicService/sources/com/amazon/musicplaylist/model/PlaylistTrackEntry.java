package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistTrackEntry implements Comparable<PlaylistTrackEntry> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.PlaylistTrackEntry");
    private String entryId;
    private PlaylistTrackEntryMetadata metadata;
    private boolean newSinceLastMajorVersion;
    private TrackId trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaylistTrackEntry playlistTrackEntry) {
        if (playlistTrackEntry == null) {
            return -1;
        }
        if (playlistTrackEntry == this) {
            return 0;
        }
        String entryId = getEntryId();
        String entryId2 = playlistTrackEntry.getEntryId();
        if (entryId != entryId2) {
            if (entryId == null) {
                return -1;
            }
            if (entryId2 == null) {
                return 1;
            }
            int iCompareTo = entryId.compareTo(entryId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        TrackId trackId = getTrackId();
        TrackId trackId2 = playlistTrackEntry.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo2 = trackId.compareTo(trackId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        PlaylistTrackEntryMetadata metadata = getMetadata();
        PlaylistTrackEntryMetadata metadata2 = playlistTrackEntry.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo3 = metadata.compareTo(metadata2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (isNewSinceLastMajorVersion() || !playlistTrackEntry.isNewSinceLastMajorVersion()) {
            return (!isNewSinceLastMajorVersion() || playlistTrackEntry.isNewSinceLastMajorVersion()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistTrackEntry)) {
            return false;
        }
        PlaylistTrackEntry playlistTrackEntry = (PlaylistTrackEntry) obj;
        return internalEqualityCheck(getEntryId(), playlistTrackEntry.getEntryId()) && internalEqualityCheck(getTrackId(), playlistTrackEntry.getTrackId()) && internalEqualityCheck(getMetadata(), playlistTrackEntry.getMetadata()) && internalEqualityCheck(Boolean.valueOf(isNewSinceLastMajorVersion()), Boolean.valueOf(playlistTrackEntry.isNewSinceLastMajorVersion()));
    }

    public String getEntryId() {
        return this.entryId;
    }

    public PlaylistTrackEntryMetadata getMetadata() {
        return this.metadata;
    }

    public TrackId getTrackId() {
        return this.trackId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEntryId(), getTrackId(), getMetadata(), Boolean.valueOf(isNewSinceLastMajorVersion()));
    }

    public boolean isNewSinceLastMajorVersion() {
        return this.newSinceLastMajorVersion;
    }

    public void setEntryId(String str) {
        this.entryId = str;
    }

    public void setMetadata(PlaylistTrackEntryMetadata playlistTrackEntryMetadata) {
        this.metadata = playlistTrackEntryMetadata;
    }

    public void setNewSinceLastMajorVersion(boolean z) {
        this.newSinceLastMajorVersion = z;
    }

    public void setTrackId(TrackId trackId) {
        this.trackId = trackId;
    }
}
