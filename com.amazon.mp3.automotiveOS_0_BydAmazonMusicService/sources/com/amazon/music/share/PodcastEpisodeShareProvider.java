package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastEpisodeShareProvider extends ShareProvider {
    private final String mDeeplinkUrl;
    private final String mDescription;
    private final String mEpisodeId;
    private final String mEpisodeSubtitle;
    private final String mEpisodeTitle;
    private final String mLabel;
    private final String mMetricsEntityType;
    private final String mPromptMessage;

    public PodcastEpisodeShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        super(context, str, R$string.dmusic_context_share_podcast_episode, str9, null);
        this.mEpisodeId = str2;
        this.mEpisodeTitle = str3;
        this.mEpisodeSubtitle = str4;
        this.mDescription = str5;
        this.mLabel = str6;
        this.mPromptMessage = str7;
        this.mDeeplinkUrl = str8;
        this.mMetricsEntityType = str10;
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
        return EntityIdType.PODCAST_EPISODE_ID;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mEpisodeId;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.PODCAST_EPISODE;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return this.mEpisodeSubtitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mEpisodeTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_podcast_episode);
    }
}
