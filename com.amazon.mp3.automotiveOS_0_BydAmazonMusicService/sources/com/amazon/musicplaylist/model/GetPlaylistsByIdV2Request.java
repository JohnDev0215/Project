package com.amazon.musicplaylist.model;

import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetPlaylistsByIdV2Request extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetPlaylistsByIdV2Request");
    private ParentalControls allowedParentalControls;
    private boolean contentEncoding;
    private List<String> featureSet;
    private boolean includeUnreleasedPlaylists;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private String musicRequestIdentityContextToken;
    private String pageToken;
    private List<String> playlistIds;
    private List<String> requestedMetadata;

    public static class Builder extends AuthorizationRequiredRequest.Builder {
        protected ParentalControls allowedParentalControls;
        protected boolean contentEncoding;
        protected List<String> featureSet;
        protected boolean includeUnreleasedPlaylists;
        protected MusicRequestIdentityContext musicRequestIdentityContext;
        protected String musicRequestIdentityContextToken;
        protected String pageToken;
        protected List<String> playlistIds;
        protected List<String> requestedMetadata;

        public GetPlaylistsByIdV2Request build() {
            GetPlaylistsByIdV2Request getPlaylistsByIdV2Request = new GetPlaylistsByIdV2Request();
            populate(getPlaylistsByIdV2Request);
            return getPlaylistsByIdV2Request;
        }

        protected void populate(GetPlaylistsByIdV2Request getPlaylistsByIdV2Request) {
            super.populate((AuthorizationRequiredRequest) getPlaylistsByIdV2Request);
            getPlaylistsByIdV2Request.setAllowedParentalControls(this.allowedParentalControls);
            getPlaylistsByIdV2Request.setMusicRequestIdentityContextToken(this.musicRequestIdentityContextToken);
            getPlaylistsByIdV2Request.setMusicRequestIdentityContext(this.musicRequestIdentityContext);
            getPlaylistsByIdV2Request.setRequestedMetadata(this.requestedMetadata);
            getPlaylistsByIdV2Request.setPageToken(this.pageToken);
            getPlaylistsByIdV2Request.setFeatureSet(this.featureSet);
            getPlaylistsByIdV2Request.setContentEncoding(this.contentEncoding);
            getPlaylistsByIdV2Request.setPlaylistIds(this.playlistIds);
            getPlaylistsByIdV2Request.setIncludeUnreleasedPlaylists(this.includeUnreleasedPlaylists);
        }

        public Builder withPageToken(String str) {
            this.pageToken = str;
            return this;
        }

        public Builder withPlaylistIds(List<String> list) {
            this.playlistIds = list;
            return this;
        }

        public Builder withRequestedMetadata(List<String> list) {
            this.requestedMetadata = list;
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
        if (!(authorizationRequiredRequest instanceof GetPlaylistsByIdV2Request)) {
            return 1;
        }
        GetPlaylistsByIdV2Request getPlaylistsByIdV2Request = (GetPlaylistsByIdV2Request) authorizationRequiredRequest;
        ParentalControls allowedParentalControls = getAllowedParentalControls();
        ParentalControls allowedParentalControls2 = getPlaylistsByIdV2Request.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String musicRequestIdentityContextToken = getMusicRequestIdentityContextToken();
        String musicRequestIdentityContextToken2 = getPlaylistsByIdV2Request.getMusicRequestIdentityContextToken();
        if (musicRequestIdentityContextToken != musicRequestIdentityContextToken2) {
            if (musicRequestIdentityContextToken == null) {
                return -1;
            }
            if (musicRequestIdentityContextToken2 == null) {
                return 1;
            }
            int iCompareTo2 = musicRequestIdentityContextToken.compareTo(musicRequestIdentityContextToken2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = getPlaylistsByIdV2Request.getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo3 = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> requestedMetadata = getRequestedMetadata();
        List<String> requestedMetadata2 = getPlaylistsByIdV2Request.getRequestedMetadata();
        if (requestedMetadata != requestedMetadata2) {
            if (requestedMetadata == null) {
                return -1;
            }
            if (requestedMetadata2 == null) {
                return 1;
            }
            if (requestedMetadata instanceof Comparable) {
                int iCompareTo4 = ((Comparable) requestedMetadata).compareTo(requestedMetadata2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!requestedMetadata.equals(requestedMetadata2)) {
                int iHashCode = requestedMetadata.hashCode();
                int iHashCode2 = requestedMetadata2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = getPlaylistsByIdV2Request.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo5 = pageToken.compareTo(pageToken2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> featureSet = getFeatureSet();
        List<String> featureSet2 = getPlaylistsByIdV2Request.getFeatureSet();
        if (featureSet != featureSet2) {
            if (featureSet == null) {
                return -1;
            }
            if (featureSet2 == null) {
                return 1;
            }
            if (featureSet instanceof Comparable) {
                int iCompareTo6 = ((Comparable) featureSet).compareTo(featureSet2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!featureSet.equals(featureSet2)) {
                int iHashCode3 = featureSet.hashCode();
                int iHashCode4 = featureSet2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        if (!isContentEncoding() && getPlaylistsByIdV2Request.isContentEncoding()) {
            return -1;
        }
        if (isContentEncoding() && !getPlaylistsByIdV2Request.isContentEncoding()) {
            return 1;
        }
        List<String> playlistIds = getPlaylistIds();
        List<String> playlistIds2 = getPlaylistsByIdV2Request.getPlaylistIds();
        if (playlistIds != playlistIds2) {
            if (playlistIds == null) {
                return -1;
            }
            if (playlistIds2 == null) {
                return 1;
            }
            if (playlistIds instanceof Comparable) {
                int iCompareTo7 = ((Comparable) playlistIds).compareTo(playlistIds2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!playlistIds.equals(playlistIds2)) {
                int iHashCode5 = playlistIds.hashCode();
                int iHashCode6 = playlistIds2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        if (!isIncludeUnreleasedPlaylists() && getPlaylistsByIdV2Request.isIncludeUnreleasedPlaylists()) {
            return -1;
        }
        if (!isIncludeUnreleasedPlaylists() || getPlaylistsByIdV2Request.isIncludeUnreleasedPlaylists()) {
            return super.compareTo(authorizationRequiredRequest);
        }
        return 1;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetPlaylistsByIdV2Request)) {
            return false;
        }
        GetPlaylistsByIdV2Request getPlaylistsByIdV2Request = (GetPlaylistsByIdV2Request) obj;
        return super.equals(obj) && internalEqualityCheck(getAllowedParentalControls(), getPlaylistsByIdV2Request.getAllowedParentalControls()) && internalEqualityCheck(getMusicRequestIdentityContextToken(), getPlaylistsByIdV2Request.getMusicRequestIdentityContextToken()) && internalEqualityCheck(getMusicRequestIdentityContext(), getPlaylistsByIdV2Request.getMusicRequestIdentityContext()) && internalEqualityCheck(getRequestedMetadata(), getPlaylistsByIdV2Request.getRequestedMetadata()) && internalEqualityCheck(getPageToken(), getPlaylistsByIdV2Request.getPageToken()) && internalEqualityCheck(getFeatureSet(), getPlaylistsByIdV2Request.getFeatureSet()) && internalEqualityCheck(Boolean.valueOf(isContentEncoding()), Boolean.valueOf(getPlaylistsByIdV2Request.isContentEncoding())) && internalEqualityCheck(getPlaylistIds(), getPlaylistsByIdV2Request.getPlaylistIds()) && internalEqualityCheck(Boolean.valueOf(isIncludeUnreleasedPlaylists()), Boolean.valueOf(getPlaylistsByIdV2Request.isIncludeUnreleasedPlaylists()));
    }

    public ParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public List<String> getFeatureSet() {
        return this.featureSet;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public List<String> getPlaylistIds() {
        return this.playlistIds;
    }

    public List<String> getRequestedMetadata() {
        return this.requestedMetadata;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAllowedParentalControls(), getMusicRequestIdentityContextToken(), getMusicRequestIdentityContext(), getRequestedMetadata(), getPageToken(), getFeatureSet(), Boolean.valueOf(isContentEncoding()), getPlaylistIds(), Boolean.valueOf(isIncludeUnreleasedPlaylists()));
    }

    public boolean isContentEncoding() {
        return this.contentEncoding;
    }

    public boolean isIncludeUnreleasedPlaylists() {
        return this.includeUnreleasedPlaylists;
    }

    public void setAllowedParentalControls(ParentalControls parentalControls) {
        this.allowedParentalControls = parentalControls;
    }

    public void setContentEncoding(boolean z) {
        this.contentEncoding = z;
    }

    public void setFeatureSet(List<String> list) {
        this.featureSet = list;
    }

    public void setIncludeUnreleasedPlaylists(boolean z) {
        this.includeUnreleasedPlaylists = z;
    }

    public void setMusicRequestIdentityContext(MusicRequestIdentityContext musicRequestIdentityContext) {
        this.musicRequestIdentityContext = musicRequestIdentityContext;
    }

    public void setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }

    public void setPlaylistIds(List<String> list) {
        this.playlistIds = list;
    }

    public void setRequestedMetadata(List<String> list) {
        this.requestedMetadata = list;
    }
}
