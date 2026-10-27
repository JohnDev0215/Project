package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class StationShareProvider extends ShareProvider {
    private final String mStationKey;
    private final String mStationTitle;

    public StationShareProvider(Context context, String str, String str2, String str3, String str4) {
        super(context, str, R$string.dmusic_context_share_station, str4, null);
        this.mStationKey = str2;
        this.mStationTitle = str3;
        this.mCustomShareSupported = true;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(getBaseUrl().buildUpon().appendPath("stations").appendPath(this.mStationKey).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(getString(R$string.dmusic_message_share_station), this.mStationTitle, getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return String.format(getString(R$string.dmusic_subject_share_station), this.mStationTitle);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.STATION_KEY;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mStationKey;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.STATION;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return getString(R$string.dmusic_subtitle_share_station);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mStationTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_station);
    }
}
