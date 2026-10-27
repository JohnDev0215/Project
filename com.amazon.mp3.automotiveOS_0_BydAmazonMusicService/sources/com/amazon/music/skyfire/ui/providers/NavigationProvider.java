package com.amazon.music.skyfire.ui.providers;

import android.content.Context;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavigationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0014\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\u001c\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H&J<\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0005H&J\b\u0010\u001e\u001a\u00020\u0003H&J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H&J\u0018\u0010!\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0016J\u001a\u0010#\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0005H&J\u0018\u0010&\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u0005H&J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&¨\u0006)"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "", "openAlbumDetail", "", "albumAsin", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "openAllArtistMerchPage", "artistAsin", "category", "openArtistDetail", "asin", "openArtistReleasesDetailPage", "openArtistSpotlight", "openChartsPage", ParserUtil.GENRE_SEGMENT_NAME, "widgetId", "openESCMix", "selectedTags", "openLibraryArtistDetail", "context", "Landroid/content/Context;", PageUriUtils.FILTER_QUERY_PARAM, "name", "followerCount", "artistId", "openLiveEventDetail", ContentUtils.KEY_EVENT_ID, "openLiveStream", "streamId", "openLiveStreams", "openMerchDetail", "merchAsin", "openMusicExperience", "experienceId", "openPlaylistDetail", "openPodcastDetail", ImagesContract.URL, "openSeeMore", "uri", "openTrackDetail", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface NavigationProvider {

    /* JADX INFO: compiled from: NavigationProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void openMusicExperience(NavigationProvider navigationProvider, String asin, String experienceId) {
            Intrinsics.checkNotNullParameter(navigationProvider, "this");
            Intrinsics.checkNotNullParameter(asin, "asin");
            Intrinsics.checkNotNullParameter(experienceId, "experienceId");
        }
    }

    void openAlbumDetail(String albumAsin, String trackAsin);

    void openAllArtistMerchPage(String artistAsin, String category);

    void openArtistDetail(String asin);

    void openArtistReleasesDetailPage(String artistAsin);

    void openArtistSpotlight(String asin);

    void openChartsPage(String genre, String widgetId);

    void openESCMix(String selectedTags);

    void openLibraryArtistDetail(Context context, String filter, String asin, String name, String followerCount, String artistId);

    void openLiveEventDetail(String eventId);

    void openLiveStream(String streamId);

    void openLiveStreams();

    void openMerchDetail(String artistAsin, String merchAsin);

    void openMusicExperience(String asin, String experienceId);

    void openPlaylistDetail(String asin, String trackAsin);

    void openPodcastDetail(String url);

    void openSeeMore(Context context, String uri);

    void openTrackDetail(String asin);
}
