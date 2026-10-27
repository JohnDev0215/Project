package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetFollowedPlaylistsInLibraryResponse implements Comparable<GetFollowedPlaylistsInLibraryResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetFollowedPlaylistsInLibraryResponse");
    private String nextPageToken;
    private int numberOfFollowedPlaylists;
    private List<PlaylistMetadata> playlists;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetFollowedPlaylistsInLibraryResponse getFollowedPlaylistsInLibraryResponse) {
        if (getFollowedPlaylistsInLibraryResponse == null) {
            return -1;
        }
        if (getFollowedPlaylistsInLibraryResponse == this) {
            return 0;
        }
        String nextPageToken = getNextPageToken();
        String nextPageToken2 = getFollowedPlaylistsInLibraryResponse.getNextPageToken();
        if (nextPageToken != nextPageToken2) {
            if (nextPageToken == null) {
                return -1;
            }
            if (nextPageToken2 == null) {
                return 1;
            }
            int iCompareTo = nextPageToken.compareTo(nextPageToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (getNumberOfFollowedPlaylists() < getFollowedPlaylistsInLibraryResponse.getNumberOfFollowedPlaylists()) {
            return -1;
        }
        if (getNumberOfFollowedPlaylists() > getFollowedPlaylistsInLibraryResponse.getNumberOfFollowedPlaylists()) {
            return 1;
        }
        List<PlaylistMetadata> playlists = getPlaylists();
        List<PlaylistMetadata> playlists2 = getFollowedPlaylistsInLibraryResponse.getPlaylists();
        if (playlists != playlists2) {
            if (playlists == null) {
                return -1;
            }
            if (playlists2 == null) {
                return 1;
            }
            if (playlists instanceof Comparable) {
                int iCompareTo2 = ((Comparable) playlists).compareTo(playlists2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!playlists.equals(playlists2)) {
                int iHashCode = playlists.hashCode();
                int iHashCode2 = playlists2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetFollowedPlaylistsInLibraryResponse)) {
            return false;
        }
        GetFollowedPlaylistsInLibraryResponse getFollowedPlaylistsInLibraryResponse = (GetFollowedPlaylistsInLibraryResponse) obj;
        return internalEqualityCheck(getNextPageToken(), getFollowedPlaylistsInLibraryResponse.getNextPageToken()) && internalEqualityCheck(Integer.valueOf(getNumberOfFollowedPlaylists()), Integer.valueOf(getFollowedPlaylistsInLibraryResponse.getNumberOfFollowedPlaylists())) && internalEqualityCheck(getPlaylists(), getFollowedPlaylistsInLibraryResponse.getPlaylists());
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public int getNumberOfFollowedPlaylists() {
        return this.numberOfFollowedPlaylists;
    }

    public List<PlaylistMetadata> getPlaylists() {
        return this.playlists;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getNextPageToken(), Integer.valueOf(getNumberOfFollowedPlaylists()), getPlaylists());
    }

    public void setNextPageToken(String str) {
        this.nextPageToken = str;
    }

    public void setNumberOfFollowedPlaylists(int i) {
        this.numberOfFollowedPlaylists = i;
    }

    public void setPlaylists(List<PlaylistMetadata> list) {
        this.playlists = list;
    }
}
