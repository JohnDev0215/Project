package com.amazon.musicplayqueueservice.client.v2.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LibraryAlbumIdentifier extends AbstractPlayableEntityIdentifier {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.common.LibraryAlbumIdentifier");
    private String albumName;
    private String artistName;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier, com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractIdentifier abstractIdentifier) {
        if (abstractIdentifier == null) {
            return -1;
        }
        if (abstractIdentifier == this) {
            return 0;
        }
        if (!(abstractIdentifier instanceof LibraryAlbumIdentifier)) {
            return 1;
        }
        LibraryAlbumIdentifier libraryAlbumIdentifier = (LibraryAlbumIdentifier) abstractIdentifier;
        String artistName = getArtistName();
        String artistName2 = libraryAlbumIdentifier.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo = artistName.compareTo(artistName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = libraryAlbumIdentifier.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo2 = albumName.compareTo(albumName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(abstractIdentifier);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier, com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryAlbumIdentifier)) {
            return false;
        }
        LibraryAlbumIdentifier libraryAlbumIdentifier = (LibraryAlbumIdentifier) obj;
        return super.equals(obj) && internalEqualityCheck(getArtistName(), libraryAlbumIdentifier.getArtistName()) && internalEqualityCheck(getAlbumName(), libraryAlbumIdentifier.getAlbumName());
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier, com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getArtistName(), getAlbumName());
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }
}
