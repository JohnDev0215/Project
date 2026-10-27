package com.amazon.music.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class TrackShareProvider extends ShareProvider {
    private final String mAlbumAsin;
    private final String mArtistName;
    private final List<Bitmap> mBackgroundBitmaps;
    private final List<String> mLyrics;
    private final PublishSubject<Integer> mOpenLyricsCardSelectionFragmentSubject;
    private final String mTrackAsin;
    private final String mTrackTitle;

    public TrackShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        this(context, str, str2, str3, str4, str5, str6, null);
    }

    public TrackShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6, List<String> list) {
        this(context, str, str2, str3, str4, str5, str6, null, list, null, null);
    }

    public TrackShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6, List<String> list, PublishSubject<Integer> publishSubject, List<Bitmap> list2) {
        this(context, str, str2, str3, str4, str5, str6, null, list, publishSubject, list2);
    }

    public TrackShareProvider(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, List<String> list, PublishSubject<Integer> publishSubject, List<Bitmap> list2) {
        super(context, str, R$string.dmusic_context_share_track, str6, str7);
        this.mAlbumAsin = str2;
        this.mTrackAsin = str3;
        this.mTrackTitle = str4;
        this.mArtistName = str5;
        this.mCustomShareSupported = true;
        this.mLyrics = list;
        this.mOpenLyricsCardSelectionFragmentSubject = publishSubject;
        this.mBackgroundBitmaps = list2;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(getBaseUrl().buildUpon().appendPath("albums").appendPath(this.mAlbumAsin).appendQueryParameter(ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, this.mTrackAsin).appendQueryParameter("do", PageUriUtils.DO_PLAY_VALUE).build());
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
        return ShareProvider.ShareProviderType.TRACK;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return String.format(getString(R$string.dmusic_subtitle_share_track), this.mArtistName);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mTrackTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_song);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected List<String> getLyrics() {
        return this.mLyrics;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected List<Bitmap> getBackgroundBitmaps() {
        return this.mBackgroundBitmaps;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected PublishSubject<Integer> getOpenLyricsCardSelectionFragmentSubject() {
        return this.mOpenLyricsCardSelectionFragmentSubject;
    }
}
