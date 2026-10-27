package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetOwnedPlaylistsInLibraryResponse implements Comparable<GetOwnedPlaylistsInLibraryResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryResponse");
    private String nextPageToken;
    private int numberOfOwnedPlaylists;
    private List<PlaylistMetadata> playlists;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetOwnedPlaylistsInLibraryResponse getOwnedPlaylistsInLibraryResponse) {
        if (getOwnedPlaylistsInLibraryResponse == null) {
            return -1;
        }
        if (getOwnedPlaylistsInLibraryResponse == this) {
            return 0;
        }
        if (getNumberOfOwnedPlaylists() < getOwnedPlaylistsInLibraryResponse.getNumberOfOwnedPlaylists()) {
            return -1;
        }
        if (getNumberOfOwnedPlaylists() > getOwnedPlaylistsInLibraryResponse.getNumberOfOwnedPlaylists()) {
            return 1;
        }
        List<PlaylistMetadata> playlists = getPlaylists();
        List<PlaylistMetadata> playlists2 = getOwnedPlaylistsInLibraryResponse.getPlaylists();
        if (playlists != playlists2) {
            if (playlists == null) {
                return -1;
            }
            if (playlists2 == null) {
                return 1;
            }
            if (playlists instanceof Comparable) {
                int iCompareTo = ((Comparable) playlists).compareTo(playlists2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        String nextPageToken = getNextPageToken();
        String nextPageToken2 = getOwnedPlaylistsInLibraryResponse.getNextPageToken();
        if (nextPageToken != nextPageToken2) {
            if (nextPageToken == null) {
                return -1;
            }
            if (nextPageToken2 == null) {
                return 1;
            }
            int iCompareTo2 = nextPageToken.compareTo(nextPageToken2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetOwnedPlaylistsInLibraryResponse)) {
            return false;
        }
        GetOwnedPlaylistsInLibraryResponse getOwnedPlaylistsInLibraryResponse = (GetOwnedPlaylistsInLibraryResponse) obj;
        return internalEqualityCheck(Integer.valueOf(getNumberOfOwnedPlaylists()), Integer.valueOf(getOwnedPlaylistsInLibraryResponse.getNumberOfOwnedPlaylists())) && internalEqualityCheck(getPlaylists(), getOwnedPlaylistsInLibraryResponse.getPlaylists()) && internalEqualityCheck(getNextPageToken(), getOwnedPlaylistsInLibraryResponse.getNextPageToken());
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public int getNumberOfOwnedPlaylists() {
        return this.numberOfOwnedPlaylists;
    }

    public List<PlaylistMetadata> getPlaylists() {
        return this.playlists;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getNumberOfOwnedPlaylists()), getPlaylists(), getNextPageToken());
    }

    public void setNextPageToken(String str) {
        this.nextPageToken = str;
    }

    public void setNumberOfOwnedPlaylists(int i) {
        this.numberOfOwnedPlaylists = i;
    }

    public void setPlaylists(List<PlaylistMetadata> list) {
        this.playlists = list;
    }
}
