package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumShareProvider extends ShareProvider {
    private final String mAlbumAsin;
    private final String mAlbumTitle;
    private final String mArtistName;

    public AlbumShareProvider(Context context, String str, String str2, String str3, String str4, String str5) {
        super(context, str, R$string.dmusic_context_share_album, str5, null);
        this.mAlbumAsin = str2;
        this.mAlbumTitle = str3;
        this.mArtistName = str4;
        this.mCustomShareSupported = true;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(getBaseUrl().buildUpon().appendPath("albums").appendPath(this.mAlbumAsin).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(getString(R$string.dmusic_message_share_album), this.mAlbumTitle, this.mArtistName, getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return String.format(getString(R$string.dmusic_subject_share_album), this.mAlbumTitle, this.mArtistName);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.ASIN;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mAlbumAsin;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.ALBUM;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return String.format(getString(R$string.dmusic_subtitle_share_album), this.mArtistName);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mAlbumTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_album);
    }
}
