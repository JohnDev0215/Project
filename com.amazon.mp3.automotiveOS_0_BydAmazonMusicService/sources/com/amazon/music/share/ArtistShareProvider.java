package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class ArtistShareProvider extends ShareProvider {
    private final String mArtistAsin;
    private final String mArtistName;

    public ArtistShareProvider(Context context, String str, String str2, String str3, String str4) {
        super(context, str, R$string.dmusic_context_share_artist, str4, null);
        this.mArtistAsin = str2;
        this.mArtistName = str3;
        this.mCustomShareSupported = true;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(getBaseUrl().buildUpon().appendPath("artists").appendPath(this.mArtistAsin).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(getString(R$string.dmusic_message_share_artist), this.mArtistName, getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return String.format(getString(R$string.dmusic_subject_share_artist), this.mArtistName);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.ASIN;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mArtistAsin;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.ARTIST;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return getString(R$string.dmusic_subtitle_share_artist);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mArtistName;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_artist);
    }
}
