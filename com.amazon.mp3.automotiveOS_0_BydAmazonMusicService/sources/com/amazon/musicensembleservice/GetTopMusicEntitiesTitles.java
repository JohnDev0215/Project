package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetTopMusicEntitiesTitles implements Comparable<GetTopMusicEntitiesTitles> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetTopMusicEntitiesTitles");
    private String albumList;
    private String playlistList;
    private String trackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetTopMusicEntitiesTitles getTopMusicEntitiesTitles) {
        if (getTopMusicEntitiesTitles == null) {
            return -1;
        }
        if (getTopMusicEntitiesTitles == this) {
            return 0;
        }
        String playlistList = getPlaylistList();
        String playlistList2 = getTopMusicEntitiesTitles.getPlaylistList();
        if (playlistList != playlistList2) {
            if (playlistList == null) {
                return -1;
            }
            if (playlistList2 == null) {
                return 1;
            }
            int iCompareTo = playlistList.compareTo(playlistList2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String albumList = getAlbumList();
        String albumList2 = getTopMusicEntitiesTitles.getAlbumList();
        if (albumList != albumList2) {
            if (albumList == null) {
                return -1;
            }
            if (albumList2 == null) {
                return 1;
            }
            int iCompareTo2 = albumList.compareTo(albumList2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String trackList = getTrackList();
        String trackList2 = getTopMusicEntitiesTitles.getTrackList();
        if (trackList != trackList2) {
            if (trackList == null) {
                return -1;
            }
            if (trackList2 == null) {
                return 1;
            }
            int iCompareTo3 = trackList.compareTo(trackList2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetTopMusicEntitiesTitles)) {
            return false;
        }
        GetTopMusicEntitiesTitles getTopMusicEntitiesTitles = (GetTopMusicEntitiesTitles) obj;
        return internalEqualityCheck(getPlaylistList(), getTopMusicEntitiesTitles.getPlaylistList()) && internalEqualityCheck(getAlbumList(), getTopMusicEntitiesTitles.getAlbumList()) && internalEqualityCheck(getTrackList(), getTopMusicEntitiesTitles.getTrackList());
    }

    public String getAlbumList() {
        return this.albumList;
    }

    public String getPlaylistList() {
        return this.playlistList;
    }

    public String getTrackList() {
        return this.trackList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlaylistList(), getAlbumList(), getTrackList());
    }

    public void setAlbumList(String str) {
        this.albumList = str;
    }

    public void setPlaylistList(String str) {
        this.playlistList = str;
    }

    public void setTrackList(String str) {
        this.trackList = str;
    }
}
