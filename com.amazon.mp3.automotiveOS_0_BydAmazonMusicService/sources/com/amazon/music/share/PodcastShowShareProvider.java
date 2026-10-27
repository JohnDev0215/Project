package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastShowShareProvider extends ShareProvider {
    private final String mDeeplinkUrl;
    private final String mDescription;
    private final String mLabel;
    private final String mMetricsEntityType;
    private final String mPodcastId;
    private final String mPodcastTitle;
    private final String mPromptMessage;

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return null;
    }

    public PodcastShowShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        super(context, str, R$string.dmusic_context_share_podcast_show, str8, null);
        this.mPodcastId = str2;
        this.mPodcastTitle = str3;
        this.mDescription = str4;
        this.mLabel = str5;
        this.mPromptMessage = str6;
        this.mDeeplinkUrl = str7;
        this.mMetricsEntityType = str9;
        this.mCustomShareSupported = true;
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
        return EntityIdType.PODCAST_SHOW_ID;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mPodcastId;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.PODCAST_SHOW;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mPodcastTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_podcast_show);
    }
}
