package com.amazon.musicplaylist.model;

import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class GetOwnedPlaylistsInLibraryRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryRequest");
    private Integer entryOffset;
    private Map<String, List<String>> filters;
    private Integer pageSize;
    private String pageToken;
    private String sortBy;

    public static class Builder extends AuthorizationRequiredRequest.Builder {
        protected Integer entryOffset;
        protected Map<String, List<String>> filters;
        protected Integer pageSize;
        protected String pageToken;
        protected String sortBy;

        public GetOwnedPlaylistsInLibraryRequest build() {
            GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest = new GetOwnedPlaylistsInLibraryRequest();
            populate(getOwnedPlaylistsInLibraryRequest);
            return getOwnedPlaylistsInLibraryRequest;
        }

        protected void populate(GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest) {
            super.populate((AuthorizationRequiredRequest) getOwnedPlaylistsInLibraryRequest);
            getOwnedPlaylistsInLibraryRequest.setSortBy(this.sortBy);
            getOwnedPlaylistsInLibraryRequest.setEntryOffset(this.entryOffset);
            getOwnedPlaylistsInLibraryRequest.setPageToken(this.pageToken);
            getOwnedPlaylistsInLibraryRequest.setFilters(this.filters);
            getOwnedPlaylistsInLibraryRequest.setPageSize(this.pageSize);
        }

        public Builder withPageSize(Integer num) {
            this.pageSize = num;
            return this;
        }

        public Builder withPageToken(String str) {
            this.pageToken = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

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
        if (!(authorizationRequiredRequest instanceof GetOwnedPlaylistsInLibraryRequest)) {
            return 1;
        }
        GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest = (GetOwnedPlaylistsInLibraryRequest) authorizationRequiredRequest;
        String sortBy = getSortBy();
        String sortBy2 = getOwnedPlaylistsInLibraryRequest.getSortBy();
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
        Integer entryOffset = getEntryOffset();
        Integer entryOffset2 = getOwnedPlaylistsInLibraryRequest.getEntryOffset();
        if (entryOffset != entryOffset2) {
            if (entryOffset == null) {
                return -1;
            }
            if (entryOffset2 == null) {
                return 1;
            }
            int iCompareTo2 = entryOffset.compareTo(entryOffset2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = getOwnedPlaylistsInLibraryRequest.getPageToken();
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
        Map<String, List<String>> filters = getFilters();
        Map<String, List<String>> filters2 = getOwnedPlaylistsInLibraryRequest.getFilters();
        if (filters != filters2) {
            if (filters == null) {
                return -1;
            }
            if (filters2 == null) {
                return 1;
            }
            if (filters instanceof Comparable) {
                int iCompareTo4 = ((Comparable) filters).compareTo(filters2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!filters.equals(filters2)) {
                int iHashCode = filters.hashCode();
                int iHashCode2 = filters2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Integer pageSize = getPageSize();
        Integer pageSize2 = getOwnedPlaylistsInLibraryRequest.getPageSize();
        if (pageSize != pageSize2) {
            if (pageSize == null) {
                return -1;
            }
            if (pageSize2 == null) {
                return 1;
            }
            int iCompareTo5 = pageSize.compareTo(pageSize2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetOwnedPlaylistsInLibraryRequest)) {
            return false;
        }
        GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest = (GetOwnedPlaylistsInLibraryRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getSortBy(), getOwnedPlaylistsInLibraryRequest.getSortBy()) && internalEqualityCheck(getEntryOffset(), getOwnedPlaylistsInLibraryRequest.getEntryOffset()) && internalEqualityCheck(getPageToken(), getOwnedPlaylistsInLibraryRequest.getPageToken()) && internalEqualityCheck(getFilters(), getOwnedPlaylistsInLibraryRequest.getFilters()) && internalEqualityCheck(getPageSize(), getOwnedPlaylistsInLibraryRequest.getPageSize());
    }

    public Integer getEntryOffset() {
        return this.entryOffset;
    }

    public Map<String, List<String>> getFilters() {
        return this.filters;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSortBy(), getEntryOffset(), getPageToken(), getFilters(), getPageSize());
    }

    public void setEntryOffset(Integer num) {
        this.entryOffset = num;
    }

    public void setFilters(Map<String, List<String>> map) {
        this.filters = map;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }

    public void setSortBy(String str) {
        this.sortBy = str;
    }
}
