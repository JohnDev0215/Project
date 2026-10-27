package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.navigation.ESCMixDestinationHandler;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.destination.AllArtistMerchDestination;
import com.amazon.music.destination.ArtistDestination;
import com.amazon.music.destination.ArtistDetailDestination;
import com.amazon.music.destination.LiveEventDetailDestination;
import com.amazon.music.destination.LiveStreamPlayerDestination;
import com.amazon.music.destination.LiveStreamsDestination;
import com.amazon.music.destination.MerchDetailDestination;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.PodcastDestination;
import com.amazon.music.destination.PopularDestination;
import com.amazon.music.destination.Target;
import com.amazon.music.destination.TargetType;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.destination.parser.UpsellDeeplinkParser;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.freetier.destination.CreateAndPlayMixDestination;
import com.amazon.music.freetier.destination.TrackDetailDestination;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.router.Router;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: DMSFNavigationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\"\u0018\u0000 .2\u00020\u0001:\u0001.B\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b,\u0010-J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001a\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J<\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016J\u001c\u0010\u0016\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\u001a\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\"\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0016J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0002H\u0016J\u0018\u0010&\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u0002H\u0016J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0002H\u0016R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010)\u001a\u0004\b*\u0010+¨\u0006/"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFNavigationProvider;", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "", "asin", "", "openArtistReleasesDetailPage", "albumAsin", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "openAlbumDetail", "openArtistDetail", "openTrackDetail", "selectedTags", "openESCMix", "Landroid/content/Context;", "context", PageUriUtils.FILTER_QUERY_PARAM, "name", "followerCount", "artistId", "openLibraryArtistDetail", ParserUtil.GENRE_SEGMENT_NAME, "widgetId", "openChartsPage", "openPlaylistDetail", "uri", "openSeeMore", "openLiveStreams", "artistAsin", "category", "openAllArtistMerchPage", ContentUtils.KEY_EVENT_ID, "openLiveEventDetail", "openArtistSpotlight", "experienceId", "openMusicExperience", "streamId", "openLiveStream", "merchAsin", "openMerchDetail", ImagesContract.URL, "openPodcastDetail", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DMSFNavigationProvider implements NavigationProvider {
    private final Context context;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = DMSFNavigationProvider.class.getSimpleName();
    private static final String LIBRARY_FILTER_KEY = "Library";
    private static final String DOWNLOADED_FILTER_KEY = "Downloaded";

    @JvmStatic
    public static final boolean openArtistDetailPage(String str, Long l, String str2, Runnable runnable) {
        return INSTANCE.openArtistDetailPage(str, l, str2, runnable);
    }

    public DMSFNavigationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: compiled from: DMSFNavigationProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J;\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFNavigationProvider$Companion;", "", "()V", "DOWNLOADED_FILTER_KEY", "", "LIBRARY_FILTER_KEY", "TAG", "kotlin.jvm.PlatformType", "openArtistDetailPage", "", "asin", "artistId", "", "storyId", "callback", "Ljava/lang/Runnable;", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Runnable;)Z", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ boolean openArtistDetailPage$default(Companion companion, String str, Long l, String str2, Runnable runnable, int i, Object obj) {
            if ((i & 2) != 0) {
                l = null;
            }
            if ((i & 4) != 0) {
                str2 = null;
            }
            if ((i & 8) != 0) {
                runnable = null;
            }
            return companion.openArtistDetailPage(str, l, str2, runnable);
        }

        @JvmStatic
        public final boolean openArtistDetailPage(String asin, Long artistId, String storyId, Runnable callback) {
            if (asin == null) {
                return false;
            }
            try {
                Router.get(AmazonApplication.getApplication().getApplicationContext()).navigateTo(new ArtistDetailDestination(asin, artistId == null ? null : artistId.toString(), storyId, null, null, false, 32, null), callback);
                return true;
            } catch (UnknownDestinationException e) {
                Log.error(DMSFNavigationProvider.TAG, "Failed to navigate to SkyFire artist detail", e);
                return false;
            }
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openArtistReleasesDetailPage(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        try {
            Router.get(this.context).navigateTo(new ArtistDetailDestination(asin, null, null, null, null, true));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to artist releases detail page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openAlbumDetail(String albumAsin, String trackAsin) {
        Intrinsics.checkNotNullParameter(albumAsin, "albumAsin");
        AlbumDestination albumDestination = new AlbumDestination(albumAsin, trackAsin, null, null, null);
        try {
            MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.ALBUM_DETAIL);
            Router.get(this.context).navigateTo(albumDestination);
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to album detail page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openArtistDetail(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        try {
            Router.get(this.context).navigateTo(new ArtistDestination(new Target(TargetType.ARTIST, asin), asin, null, null, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to artist detail page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openTrackDetail(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        try {
            Router.get(this.context).navigateTo(new TrackDetailDestination(asin, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to track detail page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openESCMix(String selectedTags) {
        Intrinsics.checkNotNullParameter(selectedTags, "selectedTags");
        try {
            new ESCMixDestinationHandler().navigateTo(this.context, new CreateAndPlayMixDestination(selectedTags, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to ESC Mix page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openLibraryArtistDetail(Context context, String filter, String asin, String name, String followerCount, String artistId) {
        FragmentController fragmentController;
        Long longOrNull;
        FragmentController fragmentController2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(name, "name");
        Fragment currentFragment = null;
        MusicHomeActivity musicHomeActivity = context instanceof MusicHomeActivity ? (MusicHomeActivity) context : null;
        if (musicHomeActivity != null && (fragmentController2 = musicHomeActivity.getFragmentController()) != null) {
            currentFragment = fragmentController2.getCurrentFragment();
        }
        LibraryArtistDetailFragment.Companion companion = LibraryArtistDetailFragment.INSTANCE;
        Bundle bundle = new Bundle();
        bundle.putString("ARTIST_ASIN", asin);
        if (artistId != null && (longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(artistId)) != null) {
            bundle.putLong(Contexts.LibraryArtistDetail.artistId, longOrNull.longValue());
        }
        bundle.putString("ARTIST_FOLLOWER_COUNT", followerCount);
        bundle.putString("ARTIST_NAME", name);
        if (Intrinsics.areEqual(filter, LIBRARY_FILTER_KEY)) {
            bundle.putString("SELECTED_FILTER", RefinementFilterType.LIBRARY.name());
        } else if (Intrinsics.areEqual(filter, DOWNLOADED_FILTER_KEY)) {
            bundle.putString("SELECTED_FILTER", RefinementFilterType.DOWNLOADED.name());
        }
        LibraryArtistDetailFragment libraryArtistDetailFragmentNewInstance = companion.newInstance(bundle, currentFragment);
        if (musicHomeActivity != null && (fragmentController = musicHomeActivity.getFragmentController()) != null) {
            fragmentController.popBackStack();
        }
        if (musicHomeActivity == null) {
            return;
        }
        musicHomeActivity.changeScreenFragment(libraryArtistDetailFragmentNewInstance, true, true, false);
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openChartsPage(String genre, String widgetId) {
        try {
            Router.get(this.context).navigateTo(new PopularDestination(genre, widgetId, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to charts page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openPlaylistDetail(String asin, String trackAsin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        PlaylistDestination playlistDestination = new PlaylistDestination(asin, trackAsin, trackAsin != null ? Action.PLAY : null, null, null, null);
        try {
            MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.PLAYLIST_DETAIL);
            Router.get(this.context).navigateTo(playlistDestination);
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to playlist detail page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openSeeMore(Context context, String uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BrushFragment brushFragment = new BrushFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("navigation_source_extra", false);
        bundle.putString("brush_uri_extra", uri);
        brushFragment.setArguments(bundle);
        brushFragment.setIsArtistUpsell(StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) UpsellDeeplinkParser.UPSELL_SEGMENT_NAME, false, 2, (Object) null));
        ((MusicHomeActivity) context).changeScreenFragment(brushFragment, true, true, true);
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openLiveStreams() {
        try {
            Router.get(this.context).navigateTo(new LiveStreamsDestination(null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to live streams page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openAllArtistMerchPage(String artistAsin, String category) {
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        try {
            Router.get(this.context).navigateTo(new AllArtistMerchDestination(artistAsin, category, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to artist merch page", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openLiveEventDetail(String eventId) {
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        try {
            Router.get(this.context).navigateTo(new LiveEventDetailDestination(eventId, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to LiveEvent detail", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openArtistSpotlight(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intent intent = new Intent(this.context, (Class<?>) NowPlayingFragmentActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("com.amazon.mp3.fragment.extra", NowPlayingFragment.class.getSimpleName());
        intent.putExtra("artistAsin", asin);
        intent.putExtra(PlaybackFragment.class.getSimpleName(), true);
        this.context.startActivity(intent);
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openMusicExperience(String asin, String experienceId) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(experienceId, "experienceId");
        Intent intent = new Intent(this.context, (Class<?>) NowPlayingFragmentActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("com.amazon.mp3.fragment.extra", NowPlayingFragment.class.getSimpleName());
        intent.putExtra("artistAsin", asin);
        intent.putExtra("experienceId", experienceId);
        intent.putExtra(PlaybackFragment.class.getSimpleName(), true);
        this.context.startActivity(intent);
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openLiveStream(String streamId) {
        Intrinsics.checkNotNullParameter(streamId, "streamId");
        try {
            Router.get(this.context).navigateTo(new LiveStreamPlayerDestination(streamId, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to LiveStream player", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openMerchDetail(String artistAsin, String merchAsin) {
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        Intrinsics.checkNotNullParameter(merchAsin, "merchAsin");
        try {
            Router.get(this.context).navigateTo(new MerchDetailDestination(artistAsin, merchAsin, null, null));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to Merch detail", e);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.NavigationProvider
    public void openPodcastDetail(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            Router.get(this.context).navigateTo(new PodcastDestination(null, null, url));
        } catch (UnknownDestinationException e) {
            Log.error(TAG, "Failed to navigate to podcast detail page", e);
        }
    }
}
