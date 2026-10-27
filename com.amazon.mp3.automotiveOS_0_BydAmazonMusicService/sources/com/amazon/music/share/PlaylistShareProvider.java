package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistShareProvider extends ShareProvider {
    private final String mPlaylistAsin;
    private final String mPlaylistTitle;

    public PlaylistShareProvider(Context context, String str, String str2, String str3, String str4) {
        super(context, str, R$string.dmusic_context_share_playlist, str4, null);
        this.mPlaylistAsin = str2;
        this.mPlaylistTitle = str3;
        this.mCustomShareSupported = true;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(getBaseUrl().buildUpon().appendPath(this.mPlaylistAsin.length() > 10 && !this.mPlaylistAsin.startsWith("PP") ? "user-playlists" : "playlists").appendPath(this.mPlaylistAsin).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(getString(R$string.dmusic_message_share_playlist), this.mPlaylistTitle, getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return String.format(getString(R$string.dmusic_subject_share_playlist), this.mPlaylistTitle);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.ASIN;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mPlaylistAsin;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.PLAYLIST;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return getString(R$string.dmusic_subtitle_share_playlist);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mPlaylistTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_playlist);
    }
}
