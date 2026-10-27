package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastBiteShareProvider extends ShareProvider {
    private final String mBiteId;
    private final String mDeeplinkUrl;
    private final String mDescription;
    private final String mEpisodeTitle;
    private final String mImageUrl;
    private final String mLabel;
    private final String mMetricsEntityType;
    private final String mPromptMessage;

    public PodcastBiteShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        super(context, str, R$string.dmusic_context_share_podcast_episode);
        this.mBiteId = str2;
        this.mEpisodeTitle = str3;
        this.mDescription = str4;
        this.mLabel = str5;
        this.mPromptMessage = str6;
        this.mDeeplinkUrl = str7;
        this.mImageUrl = str8;
        this.mMetricsEntityType = str9;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(Uri.parse(this.mDeeplinkUrl));
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(this.mDescription + "\n%s", getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return this.mDescription;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.PODCAST_BITE_ID;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mBiteId;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.PODCAST_BITE;
    }
}
