package com.amazon.music.library;

import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
final class ThinSociallySharePlaylistResponse implements SociallySharePlaylistResponse {
    private final String mShareBlurbKey;
    private final URL mUrl;

    public static final class Builder {
        private String mShareBlurbKey;
        private URL mUrl;

        public Builder withMetadata(com.amazon.musicplaylist.model.SociallySharePlaylistResponse sociallySharePlaylistResponse) throws MalformedURLException {
            this.mShareBlurbKey = sociallySharePlaylistResponse.getShareBlurb();
            this.mUrl = sociallySharePlaylistResponse.getUrl() != null ? new URL(sociallySharePlaylistResponse.getUrl()) : null;
            return this;
        }

        public ThinSociallySharePlaylistResponse build() {
            return new ThinSociallySharePlaylistResponse(this.mShareBlurbKey, this.mUrl);
        }
    }

    ThinSociallySharePlaylistResponse(String str, URL url) {
        this.mShareBlurbKey = str;
        this.mUrl = url;
    }

    @Override // com.amazon.music.library.SociallySharePlaylistResponse
    public URL getUrl() {
        return this.mUrl;
    }
}
