package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetFollowedPlaylistsInLibraryRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetFollowedPlaylistsInLibraryRequest");
    private Integer entryOffset;
    private boolean optIntoSharedPlaylists;
    private Integer pageSize;
    private String pageToken;
    private String sortBy;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AuthorizationRequiredRequest authorizationRequiredRequest) {
        if (authorizationRequiredRequest == null) {
            return -1;
        }
        if (authorizationRequiredRequest == this) {
            return 0;
        }
        if (!(authorizationRequiredRequest instanceof GetFollowedPlaylistsInLibraryRequest)) {
            return 1;
        }
        GetFollowedPlaylistsInLibraryRequest getFollowedPlaylistsInLibraryRequest = (GetFollowedPlaylistsInLibraryRequest) authorizationRequiredRequest;
        String sortBy = getSortBy();
        String sortBy2 = getFollowedPlaylistsInLibraryRequest.getSortBy();
        if (sortBy != sortBy2) {
            if (sortBy == null) {
                return -1;
            }
            if (sortBy2 == null) {
                return 1;
            }
            int iCompareTo = sortBy.compareTo(sortBy2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (!isOptIntoSharedPlaylists() && getFollowedPlaylistsInLibraryRequest.isOptIntoSharedPlaylists()) {
            return -1;
        }
        if (isOptIntoSharedPlaylists() && !getFollowedPlaylistsInLibraryRequest.isOptIntoSharedPlaylists()) {
            return 1;
        }
        Integer pageSize = getPageSize();
        Integer pageSize2 = getFollowedPlaylistsInLibraryRequest.getPageSize();
        if (pageSize != pageSize2) {
            if (pageSize == null) {
                return -1;
            }
            if (pageSize2 == null) {
                return 1;
            }
            int iCompareTo2 = pageSize.compareTo(pageSize2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = getFollowedPlaylistsInLibraryRequest.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo3 = pageToken.compareTo(pageToken2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer entryOffset = getEntryOffset();
        Integer entryOffset2 = getFollowedPlaylistsInLibraryRequest.getEntryOffset();
        if (entryOffset != entryOffset2) {
            if (entryOffset == null) {
                return -1;
            }
            if (entryOffset2 == null) {
                return 1;
            }
            int iCompareTo4 = entryOffset.compareTo(entryOffset2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetFollowedPlaylistsInLibraryRequest)) {
            return false;
        }
        GetFollowedPlaylistsInLibraryRequest getFollowedPlaylistsInLibraryRequest = (GetFollowedPlaylistsInLibraryRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getSortBy(), getFollowedPlaylistsInLibraryRequest.getSortBy()) && internalEqualityCheck(Boolean.valueOf(isOptIntoSharedPlaylists()), Boolean.valueOf(getFollowedPlaylistsInLibraryRequest.isOptIntoSharedPlaylists())) && internalEqualityCheck(getPageSize(), getFollowedPlaylistsInLibraryRequest.getPageSize()) && internalEqualityCheck(getPageToken(), getFollowedPlaylistsInLibraryRequest.getPageToken()) && internalEqualityCheck(getEntryOffset(), getFollowedPlaylistsInLibraryRequest.getEntryOffset());
    }

    public Integer getEntryOffset() {
        return this.entryOffset;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSortBy(), Boolean.valueOf(isOptIntoSharedPlaylists()), getPageSize(), getPageToken(), getEntryOffset());
    }

    public boolean isOptIntoSharedPlaylists() {
        return this.optIntoSharedPlaylists;
    }
}
