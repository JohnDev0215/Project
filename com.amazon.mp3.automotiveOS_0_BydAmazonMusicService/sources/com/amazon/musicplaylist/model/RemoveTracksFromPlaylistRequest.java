package com.amazon.musicplaylist.model;

import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveTracksFromPlaylistRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.RemoveTracksFromPlaylistRequest");
    private List<String> entryIds;
    private String playlistId;
    private String version;

    public static class Builder extends AuthorizationRequiredRequest.Builder {
        protected List<String> entryIds;
        protected String playlistId;
        protected String version;

        public RemoveTracksFromPlaylistRequest build() {
            RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest = new RemoveTracksFromPlaylistRequest();
            populate(removeTracksFromPlaylistRequest);
            return removeTracksFromPlaylistRequest;
        }

        protected void populate(RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest) {
            super.populate((AuthorizationRequiredRequest) removeTracksFromPlaylistRequest);
            removeTracksFromPlaylistRequest.setVersion(this.version);
            removeTracksFromPlaylistRequest.setEntryIds(this.entryIds);
            removeTracksFromPlaylistRequest.setPlaylistId(this.playlistId);
        }

        public Builder withEntryIds(List<String> list) {
            this.entryIds = list;
            return this;
        }

        public Builder withPlaylistId(String str) {
            this.playlistId = str;
            return this;
        }

        public Builder withVersion(String str) {
            this.version = str;
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
        if (!(authorizationRequiredRequest instanceof RemoveTracksFromPlaylistRequest)) {
            return 1;
        }
        RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest = (RemoveTracksFromPlaylistRequest) authorizationRequiredRequest;
        String version = getVersion();
        String version2 = removeTracksFromPlaylistRequest.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo = version.compareTo(version2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> entryIds = getEntryIds();
        List<String> entryIds2 = removeTracksFromPlaylistRequest.getEntryIds();
        if (entryIds != entryIds2) {
            if (entryIds == null) {
                return -1;
            }
            if (entryIds2 == null) {
                return 1;
            }
            if (entryIds instanceof Comparable) {
                int iCompareTo2 = ((Comparable) entryIds).compareTo(entryIds2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!entryIds.equals(entryIds2)) {
                int iHashCode = entryIds.hashCode();
                int iHashCode2 = entryIds2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = removeTracksFromPlaylistRequest.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo3 = playlistId.compareTo(playlistId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof RemoveTracksFromPlaylistRequest)) {
            return false;
        }
        RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest = (RemoveTracksFromPlaylistRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getVersion(), removeTracksFromPlaylistRequest.getVersion()) && internalEqualityCheck(getEntryIds(), removeTracksFromPlaylistRequest.getEntryIds()) && internalEqualityCheck(getPlaylistId(), removeTracksFromPlaylistRequest.getPlaylistId());
    }

    public List<String> getEntryIds() {
        return this.entryIds;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getVersion(), getEntryIds(), getPlaylistId());
    }

    public void setEntryIds(List<String> list) {
        this.entryIds = list;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
