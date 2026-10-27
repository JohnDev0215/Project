package com.amazon.musicplaylist.model;

import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetPersonalizedPlaylistsRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetPersonalizedPlaylistsRequest");
    private List<String> playlistTypes;

    public static class Builder extends AuthorizationRequiredRequest.Builder {
        protected List<String> playlistTypes;

        public GetPersonalizedPlaylistsRequest build() {
            GetPersonalizedPlaylistsRequest getPersonalizedPlaylistsRequest = new GetPersonalizedPlaylistsRequest();
            populate(getPersonalizedPlaylistsRequest);
            return getPersonalizedPlaylistsRequest;
        }

        protected void populate(GetPersonalizedPlaylistsRequest getPersonalizedPlaylistsRequest) {
            super.populate((AuthorizationRequiredRequest) getPersonalizedPlaylistsRequest);
            getPersonalizedPlaylistsRequest.setPlaylistTypes(this.playlistTypes);
        }

        public Builder withPlaylistTypes(List<String> list) {
            this.playlistTypes = list;
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
        if (!(authorizationRequiredRequest instanceof GetPersonalizedPlaylistsRequest)) {
            return 1;
        }
        List<String> playlistTypes = getPlaylistTypes();
        List<String> playlistTypes2 = ((GetPersonalizedPlaylistsRequest) authorizationRequiredRequest).getPlaylistTypes();
        if (playlistTypes != playlistTypes2) {
            if (playlistTypes == null) {
                return -1;
            }
            if (playlistTypes2 == null) {
                return 1;
            }
            if (playlistTypes instanceof Comparable) {
                int iCompareTo = ((Comparable) playlistTypes).compareTo(playlistTypes2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!playlistTypes.equals(playlistTypes2)) {
                int iHashCode = playlistTypes.hashCode();
                int iHashCode2 = playlistTypes2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (obj instanceof GetPersonalizedPlaylistsRequest) {
            return super.equals(obj) && internalEqualityCheck(getPlaylistTypes(), ((GetPersonalizedPlaylistsRequest) obj).getPlaylistTypes());
        }
        return false;
    }

    public List<String> getPlaylistTypes() {
        return this.playlistTypes;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPlaylistTypes());
    }

    public void setPlaylistTypes(List<String> list) {
        this.playlistTypes = list;
    }
}
