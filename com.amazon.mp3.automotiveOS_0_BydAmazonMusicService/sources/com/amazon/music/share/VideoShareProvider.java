package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class VideoShareProvider extends ShareProvider {
    private final String mArtistName;
    private final String mTrackAsin;
    private final String mTrackTitle;

    public VideoShareProvider(Context context, String str, String str2, String str3, String str4) {
        super(context, str, R$string.dmusic_context_share_track);
        this.mTrackAsin = str2;
        this.mTrackTitle = str3;
        this.mArtistName = str4;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(getBaseUrl().buildUpon().appendPath("videos").appendPath(this.mTrackAsin).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(getString(R$string.dmusic_message_share_track), this.mTrackTitle, this.mArtistName, getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return String.format(getString(R$string.dmusic_subject_share_track), this.mTrackTitle, this.mArtistName);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.ASIN;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mTrackAsin;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.VIDEO;
    }
}
