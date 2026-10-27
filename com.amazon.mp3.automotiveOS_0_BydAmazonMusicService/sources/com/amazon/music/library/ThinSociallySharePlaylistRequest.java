package com.amazon.music.library;

import com.amazon.music.library.SociallySharePlaylistRequest;

/* JADX INFO: loaded from: classes4.dex */
class ThinSociallySharePlaylistRequest extends ThinLibraryRequest<com.amazon.musicplaylist.model.SociallySharePlaylistRequest> implements SociallySharePlaylistRequest {
    public ThinSociallySharePlaylistRequest(com.amazon.musicplaylist.model.SociallySharePlaylistRequest sociallySharePlaylistRequest) {
        super(sociallySharePlaylistRequest);
    }

    static final class Builder implements SociallySharePlaylistRequest.Builder {
        private String mPlaylistId;
        private boolean mRejectForUnshareable;
        private String mVersion;

        Builder() {
        }

        @Override // com.amazon.music.library.SociallySharePlaylistRequest.Builder
        public Builder withOwnedPlaylist(String str, String str2) {
            this.mPlaylistId = IdGenerator.getCirrusIdFromOwnedPlaylistId(str);
            this.mVersion = str2;
            return this;
        }

        @Override // com.amazon.music.library.SociallySharePlaylistRequest.Builder
        public Builder rejectForUnshareableTracks() {
            this.mRejectForUnshareable = true;
            return this;
        }

        @Override // com.amazon.music.library.SociallySharePlaylistRequest.Builder
        public SociallySharePlaylistRequest build() {
            com.amazon.musicplaylist.model.SociallySharePlaylistRequest sociallySharePlaylistRequest = new com.amazon.musicplaylist.model.SociallySharePlaylistRequest();
            sociallySharePlaylistRequest.setPlaylistId(this.mPlaylistId);
            sociallySharePlaylistRequest.setPlaylistVersion(this.mVersion);
            sociallySharePlaylistRequest.setRejectForUnshareableTracks(this.mRejectForUnshareable);
            return new ThinSociallySharePlaylistRequest(sociallySharePlaylistRequest);
        }
    }
}
