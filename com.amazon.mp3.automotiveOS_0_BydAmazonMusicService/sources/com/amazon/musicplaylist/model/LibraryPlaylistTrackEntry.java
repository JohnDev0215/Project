package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LibraryPlaylistTrackEntry extends AbstractPlaylistTrackEntry {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.LibraryPlaylistTrackEntry");
    private LibraryTrackMetadata metadata;
    private LibraryTrackId trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylistTrackEntry abstractPlaylistTrackEntry) {
        if (abstractPlaylistTrackEntry == null) {
            return -1;
        }
        if (abstractPlaylistTrackEntry == this) {
            return 0;
        }
        if (!(abstractPlaylistTrackEntry instanceof LibraryPlaylistTrackEntry)) {
            return 1;
        }
        LibraryPlaylistTrackEntry libraryPlaylistTrackEntry = (LibraryPlaylistTrackEntry) abstractPlaylistTrackEntry;
        LibraryTrackId trackId = getTrackId();
        LibraryTrackId trackId2 = libraryPlaylistTrackEntry.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo = trackId.compareTo(trackId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        LibraryTrackMetadata metadata = getMetadata();
        LibraryTrackMetadata metadata2 = libraryPlaylistTrackEntry.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo2 = metadata.compareTo(metadata2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(abstractPlaylistTrackEntry);
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryPlaylistTrackEntry)) {
            return false;
        }
        LibraryPlaylistTrackEntry libraryPlaylistTrackEntry = (LibraryPlaylistTrackEntry) obj;
        return super.equals(obj) && internalEqualityCheck(getTrackId(), libraryPlaylistTrackEntry.getTrackId()) && internalEqualityCheck(getMetadata(), libraryPlaylistTrackEntry.getMetadata());
    }

    public LibraryTrackMetadata getMetadata() {
        return this.metadata;
    }

    public LibraryTrackId getTrackId() {
        return this.trackId;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrackId(), getMetadata());
    }

    public void setMetadata(LibraryTrackMetadata libraryTrackMetadata) {
        this.metadata = libraryTrackMetadata;
    }

    public void setTrackId(LibraryTrackId libraryTrackId) {
        this.trackId = libraryTrackId;
    }
}
