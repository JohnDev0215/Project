package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetPersonalizedPlaylistsResponse implements Comparable<GetPersonalizedPlaylistsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetPersonalizedPlaylistsResponse");
    private List<PersonalizedPlaylist> playlists;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetPersonalizedPlaylistsResponse getPersonalizedPlaylistsResponse) {
        List<PersonalizedPlaylist> playlists;
        List<PersonalizedPlaylist> playlists2;
        if (getPersonalizedPlaylistsResponse == null) {
            return -1;
        }
        if (getPersonalizedPlaylistsResponse != this && (playlists = getPlaylists()) != (playlists2 = getPersonalizedPlaylistsResponse.getPlaylists())) {
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GetPersonalizedPlaylistsResponse) {
            return internalEqualityCheck(getPlaylists(), ((GetPersonalizedPlaylistsResponse) obj).getPlaylists());
        }
        return false;
    }

    public List<PersonalizedPlaylist> getPlaylists() {
        return this.playlists;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlaylists());
    }

    public void setPlaylists(List<PersonalizedPlaylist> list) {
        this.playlists = list;
    }
}
