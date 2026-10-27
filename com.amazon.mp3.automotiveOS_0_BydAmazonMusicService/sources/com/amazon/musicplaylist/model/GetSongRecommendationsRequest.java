package com.amazon.musicplaylist.model;

import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetSongRecommendationsRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.GetSongRecommendationsRequest");
    private boolean contentEncoding;
    private String contentSubscriptionMode;
    private int numTracksRequested;
    private ParentalControlSettings parentalControlSettings;
    private String playlistId;

    public static class Builder extends AuthorizationRequiredRequest.Builder {
        protected boolean contentEncoding;
        protected String contentSubscriptionMode;
        protected int numTracksRequested;
        protected ParentalControlSettings parentalControlSettings;
        protected String playlistId;

        public GetSongRecommendationsRequest build() {
            GetSongRecommendationsRequest getSongRecommendationsRequest = new GetSongRecommendationsRequest();
            populate(getSongRecommendationsRequest);
            return getSongRecommendationsRequest;
        }

        protected void populate(GetSongRecommendationsRequest getSongRecommendationsRequest) {
            super.populate((AuthorizationRequiredRequest) getSongRecommendationsRequest);
            getSongRecommendationsRequest.setContentEncoding(this.contentEncoding);
            getSongRecommendationsRequest.setNumTracksRequested(this.numTracksRequested);
            getSongRecommendationsRequest.setPlaylistId(this.playlistId);
            getSongRecommendationsRequest.setParentalControlSettings(this.parentalControlSettings);
            getSongRecommendationsRequest.setContentSubscriptionMode(this.contentSubscriptionMode);
        }

        public Builder withContentSubscriptionMode(String str) {
            this.contentSubscriptionMode = str;
            return this;
        }

        public Builder withNumTracksRequested(int i) {
            this.numTracksRequested = i;
            return this;
        }

        public Builder withPlaylistId(String str) {
            this.playlistId = str;
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
        if (!(authorizationRequiredRequest instanceof GetSongRecommendationsRequest)) {
            return 1;
        }
        GetSongRecommendationsRequest getSongRecommendationsRequest = (GetSongRecommendationsRequest) authorizationRequiredRequest;
        if (!isContentEncoding() && getSongRecommendationsRequest.isContentEncoding()) {
            return -1;
        }
        if (isContentEncoding() && !getSongRecommendationsRequest.isContentEncoding()) {
            return 1;
        }
        if (getNumTracksRequested() < getSongRecommendationsRequest.getNumTracksRequested()) {
            return -1;
        }
        if (getNumTracksRequested() > getSongRecommendationsRequest.getNumTracksRequested()) {
            return 1;
        }
        String playlistId = getPlaylistId();
        String playlistId2 = getSongRecommendationsRequest.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo = playlistId.compareTo(playlistId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        ParentalControlSettings parentalControlSettings = getParentalControlSettings();
        ParentalControlSettings parentalControlSettings2 = getSongRecommendationsRequest.getParentalControlSettings();
        if (parentalControlSettings != parentalControlSettings2) {
            if (parentalControlSettings == null) {
                return -1;
            }
            if (parentalControlSettings2 == null) {
                return 1;
            }
            int iCompareTo2 = parentalControlSettings.compareTo(parentalControlSettings2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String contentSubscriptionMode = getContentSubscriptionMode();
        String contentSubscriptionMode2 = getSongRecommendationsRequest.getContentSubscriptionMode();
        if (contentSubscriptionMode != contentSubscriptionMode2) {
            if (contentSubscriptionMode == null) {
                return -1;
            }
            if (contentSubscriptionMode2 == null) {
                return 1;
            }
            int iCompareTo3 = contentSubscriptionMode.compareTo(contentSubscriptionMode2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetSongRecommendationsRequest)) {
            return false;
        }
        GetSongRecommendationsRequest getSongRecommendationsRequest = (GetSongRecommendationsRequest) obj;
        return super.equals(obj) && internalEqualityCheck(Boolean.valueOf(isContentEncoding()), Boolean.valueOf(getSongRecommendationsRequest.isContentEncoding())) && internalEqualityCheck(Integer.valueOf(getNumTracksRequested()), Integer.valueOf(getSongRecommendationsRequest.getNumTracksRequested())) && internalEqualityCheck(getPlaylistId(), getSongRecommendationsRequest.getPlaylistId()) && internalEqualityCheck(getParentalControlSettings(), getSongRecommendationsRequest.getParentalControlSettings()) && internalEqualityCheck(getContentSubscriptionMode(), getSongRecommendationsRequest.getContentSubscriptionMode());
    }

    public String getContentSubscriptionMode() {
        return this.contentSubscriptionMode;
    }

    public int getNumTracksRequested() {
        return this.numTracksRequested;
    }

    public ParentalControlSettings getParentalControlSettings() {
        return this.parentalControlSettings;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Boolean.valueOf(isContentEncoding()), Integer.valueOf(getNumTracksRequested()), getPlaylistId(), getParentalControlSettings(), getContentSubscriptionMode());
    }

    public boolean isContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(boolean z) {
        this.contentEncoding = z;
    }

    public void setContentSubscriptionMode(String str) {
        this.contentSubscriptionMode = str;
    }

    public void setNumTracksRequested(int i) {
        this.numTracksRequested = i;
    }

    public void setParentalControlSettings(ParentalControlSettings parentalControlSettings) {
        this.parentalControlSettings = parentalControlSettings;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }
}
