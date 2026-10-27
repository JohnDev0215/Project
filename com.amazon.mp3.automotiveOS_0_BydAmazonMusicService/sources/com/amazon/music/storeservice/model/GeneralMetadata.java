package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ContentInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class GeneralMetadata implements Comparable<GeneralMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.GeneralMetadata");
    private ContentInfo albumList;
    private ContentInfo artistList;
    private ContentInfo playlistList;
    private ContentInfo trackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GeneralMetadata generalMetadata) {
        if (generalMetadata == null) {
            return -1;
        }
        if (generalMetadata == this) {
            return 0;
        }
        ContentInfo albumList = getAlbumList();
        ContentInfo albumList2 = generalMetadata.getAlbumList();
        if (albumList != albumList2) {
            if (albumList == null) {
                return -1;
            }
            if (albumList2 == null) {
                return 1;
            }
            int iCompareTo = albumList.compareTo(albumList2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        ContentInfo trackList = getTrackList();
        ContentInfo trackList2 = generalMetadata.getTrackList();
        if (trackList != trackList2) {
            if (trackList == null) {
                return -1;
            }
            if (trackList2 == null) {
                return 1;
            }
            int iCompareTo2 = trackList.compareTo(trackList2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ContentInfo playlistList = getPlaylistList();
        ContentInfo playlistList2 = generalMetadata.getPlaylistList();
        if (playlistList != playlistList2) {
            if (playlistList == null) {
                return -1;
            }
            if (playlistList2 == null) {
                return 1;
            }
            int iCompareTo3 = playlistList.compareTo(playlistList2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ContentInfo artistList = getArtistList();
        ContentInfo artistList2 = generalMetadata.getArtistList();
        if (artistList != artistList2) {
            if (artistList == null) {
                return -1;
            }
            if (artistList2 == null) {
                return 1;
            }
            int iCompareTo4 = artistList.compareTo(artistList2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeneralMetadata)) {
            return false;
        }
        GeneralMetadata generalMetadata = (GeneralMetadata) obj;
        return internalEqualityCheck(getAlbumList(), generalMetadata.getAlbumList()) && internalEqualityCheck(getTrackList(), generalMetadata.getTrackList()) && internalEqualityCheck(getPlaylistList(), generalMetadata.getPlaylistList()) && internalEqualityCheck(getArtistList(), generalMetadata.getArtistList());
    }

    public ContentInfo getAlbumList() {
        return this.albumList;
    }

    public ContentInfo getArtistList() {
        return this.artistList;
    }

    public ContentInfo getPlaylistList() {
        return this.playlistList;
    }

    public ContentInfo getTrackList() {
        return this.trackList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAlbumList(), getTrackList(), getPlaylistList(), getArtistList());
    }

    public void setAlbumList(ContentInfo contentInfo) {
        this.albumList = contentInfo;
    }

    public void setArtistList(ContentInfo contentInfo) {
        this.artistList = contentInfo;
    }

    public void setPlaylistList(ContentInfo contentInfo) {
        this.playlistList = contentInfo;
    }

    public void setTrackList(ContentInfo contentInfo) {
        this.trackList = contentInfo;
    }
}
