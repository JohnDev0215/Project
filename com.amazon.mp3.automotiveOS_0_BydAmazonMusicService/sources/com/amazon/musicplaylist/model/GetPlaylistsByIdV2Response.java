package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class GetPlaylistsByIdV2Response implements Comparable<GetPlaylistsByIdV2Response> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetPlaylistsByIdV2Response");
    private Map<String, PlaylistServiceError> errors;
    private boolean isLastPlaylistSplit;
    private String nextPageToken;
    private List<AbstractPlaylist> playlists;
    private boolean resyncLastPlaylistOnPreviousPage;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetPlaylistsByIdV2Response getPlaylistsByIdV2Response) {
        if (getPlaylistsByIdV2Response == null) {
            return -1;
        }
        if (getPlaylistsByIdV2Response == this) {
            return 0;
        }
        Map<String, PlaylistServiceError> errors = getErrors();
        Map<String, PlaylistServiceError> errors2 = getPlaylistsByIdV2Response.getErrors();
        if (errors != errors2) {
            if (errors == null) {
                return -1;
            }
            if (errors2 == null) {
                return 1;
            }
            if (errors instanceof Comparable) {
                int iCompareTo = ((Comparable) errors).compareTo(errors2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!errors.equals(errors2)) {
                int iHashCode = errors.hashCode();
                int iHashCode2 = errors2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<AbstractPlaylist> playlists = getPlaylists();
        List<AbstractPlaylist> playlists2 = getPlaylistsByIdV2Response.getPlaylists();
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
                int iHashCode3 = playlists.hashCode();
                int iHashCode4 = playlists2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        if (!isResyncLastPlaylistOnPreviousPage() && getPlaylistsByIdV2Response.isResyncLastPlaylistOnPreviousPage()) {
            return -1;
        }
        if (isResyncLastPlaylistOnPreviousPage() && !getPlaylistsByIdV2Response.isResyncLastPlaylistOnPreviousPage()) {
            return 1;
        }
        String nextPageToken = getNextPageToken();
        String nextPageToken2 = getPlaylistsByIdV2Response.getNextPageToken();
        if (nextPageToken != nextPageToken2) {
            if (nextPageToken == null) {
                return -1;
            }
            if (nextPageToken2 == null) {
                return 1;
            }
            int iCompareTo3 = nextPageToken.compareTo(nextPageToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (isIsLastPlaylistSplit() || !getPlaylistsByIdV2Response.isIsLastPlaylistSplit()) {
            return (!isIsLastPlaylistSplit() || getPlaylistsByIdV2Response.isIsLastPlaylistSplit()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetPlaylistsByIdV2Response)) {
            return false;
        }
        GetPlaylistsByIdV2Response getPlaylistsByIdV2Response = (GetPlaylistsByIdV2Response) obj;
        return internalEqualityCheck(getErrors(), getPlaylistsByIdV2Response.getErrors()) && internalEqualityCheck(getPlaylists(), getPlaylistsByIdV2Response.getPlaylists()) && internalEqualityCheck(Boolean.valueOf(isResyncLastPlaylistOnPreviousPage()), Boolean.valueOf(getPlaylistsByIdV2Response.isResyncLastPlaylistOnPreviousPage())) && internalEqualityCheck(getNextPageToken(), getPlaylistsByIdV2Response.getNextPageToken()) && internalEqualityCheck(Boolean.valueOf(isIsLastPlaylistSplit()), Boolean.valueOf(getPlaylistsByIdV2Response.isIsLastPlaylistSplit()));
    }

    public Map<String, PlaylistServiceError> getErrors() {
        return this.errors;
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public List<AbstractPlaylist> getPlaylists() {
        return this.playlists;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getErrors(), getPlaylists(), Boolean.valueOf(isResyncLastPlaylistOnPreviousPage()), getNextPageToken(), Boolean.valueOf(isIsLastPlaylistSplit()));
    }

    public boolean isIsLastPlaylistSplit() {
        return this.isLastPlaylistSplit;
    }

    public boolean isResyncLastPlaylistOnPreviousPage() {
        return this.resyncLastPlaylistOnPreviousPage;
    }

    public void setErrors(Map<String, PlaylistServiceError> map) {
        this.errors = map;
    }

    public void setIsLastPlaylistSplit(boolean z) {
        this.isLastPlaylistSplit = z;
    }

    public void setNextPageToken(String str) {
        this.nextPageToken = str;
    }

    public void setPlaylists(List<AbstractPlaylist> list) {
        this.playlists = list;
    }

    public void setResyncLastPlaylistOnPreviousPage(boolean z) {
        this.resyncLastPlaylistOnPreviousPage = z;
    }
}
