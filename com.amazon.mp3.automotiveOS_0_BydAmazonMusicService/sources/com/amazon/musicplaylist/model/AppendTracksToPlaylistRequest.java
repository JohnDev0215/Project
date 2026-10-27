package com.amazon.musicplaylist.model;

import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AppendTracksToPlaylistRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.AppendTracksToPlaylistRequest");
    private List<String> featureSet;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private String playlistId;
    private boolean rejectDuplicateTracks;
    private List<TrackId> tracks;
    private String version;

    public static class Builder extends AuthorizationRequiredRequest.Builder {
        protected List<String> featureSet;
        protected MusicRequestIdentityContext musicRequestIdentityContext;
        protected String playlistId;
        protected boolean rejectDuplicateTracks;
        protected List<TrackId> tracks;
        protected String version;

        public AppendTracksToPlaylistRequest build() {
            AppendTracksToPlaylistRequest appendTracksToPlaylistRequest = new AppendTracksToPlaylistRequest();
            populate(appendTracksToPlaylistRequest);
            return appendTracksToPlaylistRequest;
        }

        protected void populate(AppendTracksToPlaylistRequest appendTracksToPlaylistRequest) {
            super.populate((AuthorizationRequiredRequest) appendTracksToPlaylistRequest);
            appendTracksToPlaylistRequest.setRejectDuplicateTracks(this.rejectDuplicateTracks);
            appendTracksToPlaylistRequest.setFeatureSet(this.featureSet);
            appendTracksToPlaylistRequest.setMusicRequestIdentityContext(this.musicRequestIdentityContext);
            appendTracksToPlaylistRequest.setVersion(this.version);
            appendTracksToPlaylistRequest.setPlaylistId(this.playlistId);
            appendTracksToPlaylistRequest.setTracks(this.tracks);
        }

        @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest.Builder
        public Builder withApiRevision(int i) {
            super.withApiRevision(i);
            return this;
        }

        @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest.Builder
        public Builder withDeviceId(String str) {
            super.withDeviceId(str);
            return this;
        }

        @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest.Builder
        public Builder withDeviceType(String str) {
            super.withDeviceType(str);
            return this;
        }

        public Builder withFeatureSet(List<String> list) {
            this.featureSet = list;
            return this;
        }

        public Builder withPlaylistId(String str) {
            this.playlistId = str;
            return this;
        }

        public Builder withRejectDuplicateTracks(boolean z) {
            this.rejectDuplicateTracks = z;
            return this;
        }

        public Builder withTracks(List<TrackId> list) {
            this.tracks = list;
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
        if (!(authorizationRequiredRequest instanceof AppendTracksToPlaylistRequest)) {
            return 1;
        }
        AppendTracksToPlaylistRequest appendTracksToPlaylistRequest = (AppendTracksToPlaylistRequest) authorizationRequiredRequest;
        if (!isRejectDuplicateTracks() && appendTracksToPlaylistRequest.isRejectDuplicateTracks()) {
            return -1;
        }
        if (isRejectDuplicateTracks() && !appendTracksToPlaylistRequest.isRejectDuplicateTracks()) {
            return 1;
        }
        List<String> featureSet = getFeatureSet();
        List<String> featureSet2 = appendTracksToPlaylistRequest.getFeatureSet();
        if (featureSet != featureSet2) {
            if (featureSet == null) {
                return -1;
            }
            if (featureSet2 == null) {
                return 1;
            }
            if (featureSet instanceof Comparable) {
                int iCompareTo = ((Comparable) featureSet).compareTo(featureSet2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!featureSet.equals(featureSet2)) {
                int iHashCode = featureSet.hashCode();
                int iHashCode2 = featureSet2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = appendTracksToPlaylistRequest.getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo2 = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String version = getVersion();
        String version2 = appendTracksToPlaylistRequest.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo3 = version.compareTo(version2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = appendTracksToPlaylistRequest.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo4 = playlistId.compareTo(playlistId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<TrackId> tracks = getTracks();
        List<TrackId> tracks2 = appendTracksToPlaylistRequest.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo5 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode3 = tracks.hashCode();
                int iHashCode4 = tracks2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof AppendTracksToPlaylistRequest)) {
            return false;
        }
        AppendTracksToPlaylistRequest appendTracksToPlaylistRequest = (AppendTracksToPlaylistRequest) obj;
        return super.equals(obj) && internalEqualityCheck(Boolean.valueOf(isRejectDuplicateTracks()), Boolean.valueOf(appendTracksToPlaylistRequest.isRejectDuplicateTracks())) && internalEqualityCheck(getFeatureSet(), appendTracksToPlaylistRequest.getFeatureSet()) && internalEqualityCheck(getMusicRequestIdentityContext(), appendTracksToPlaylistRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getVersion(), appendTracksToPlaylistRequest.getVersion()) && internalEqualityCheck(getPlaylistId(), appendTracksToPlaylistRequest.getPlaylistId()) && internalEqualityCheck(getTracks(), appendTracksToPlaylistRequest.getTracks());
    }

    public List<String> getFeatureSet() {
        return this.featureSet;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public List<TrackId> getTracks() {
        return this.tracks;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Boolean.valueOf(isRejectDuplicateTracks()), getFeatureSet(), getMusicRequestIdentityContext(), getVersion(), getPlaylistId(), getTracks());
    }

    public boolean isRejectDuplicateTracks() {
        return this.rejectDuplicateTracks;
    }

    public void setFeatureSet(List<String> list) {
        this.featureSet = list;
    }

    public void setMusicRequestIdentityContext(MusicRequestIdentityContext musicRequestIdentityContext) {
        this.musicRequestIdentityContext = musicRequestIdentityContext;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setRejectDuplicateTracks(boolean z) {
        this.rejectDuplicateTracks = z;
    }

    public void setTracks(List<TrackId> list) {
        this.tracks = list;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
