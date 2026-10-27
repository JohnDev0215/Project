package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PersonalizedPlaylist implements Comparable<PersonalizedPlaylist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.PersonalizedPlaylist");
    private PlaylistMetadata metadata;
    private String playlistId;
    private String playlistType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PersonalizedPlaylist personalizedPlaylist) {
        if (personalizedPlaylist == null) {
            return -1;
        }
        if (personalizedPlaylist == this) {
            return 0;
        }
        String playlistId = getPlaylistId();
        String playlistId2 = personalizedPlaylist.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo = playlistId.compareTo(playlistId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        PlaylistMetadata metadata = getMetadata();
        PlaylistMetadata metadata2 = personalizedPlaylist.getMetadata();
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
        String playlistType = getPlaylistType();
        String playlistType2 = personalizedPlaylist.getPlaylistType();
        if (playlistType != playlistType2) {
            if (playlistType == null) {
                return -1;
            }
            if (playlistType2 == null) {
                return 1;
            }
            int iCompareTo3 = playlistType.compareTo(playlistType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PersonalizedPlaylist)) {
            return false;
        }
        PersonalizedPlaylist personalizedPlaylist = (PersonalizedPlaylist) obj;
        return internalEqualityCheck(getPlaylistId(), personalizedPlaylist.getPlaylistId()) && internalEqualityCheck(getMetadata(), personalizedPlaylist.getMetadata()) && internalEqualityCheck(getPlaylistType(), personalizedPlaylist.getPlaylistType());
    }

    public PlaylistMetadata getMetadata() {
        return this.metadata;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getPlaylistType() {
        return this.playlistType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlaylistId(), getMetadata(), getPlaylistType());
    }

    public void setMetadata(PlaylistMetadata playlistMetadata) {
        this.metadata = playlistMetadata;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setPlaylistType(String str) {
        this.playlistType = str;
    }
}
