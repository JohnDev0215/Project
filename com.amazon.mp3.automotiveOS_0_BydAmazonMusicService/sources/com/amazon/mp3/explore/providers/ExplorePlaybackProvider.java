package com.amazon.mp3.explore.providers;

import ExternalActionInterface.v1_0.Album;
import ExternalActionInterface.v1_0.Artist;
import ExternalActionInterface.v1_0.ArtistSpotlight;
import ExternalActionInterface.v1_0.MusicExperience;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.Playlist;
import ExternalActionInterface.v1_0.PodcastEpisode;
import ExternalActionInterface.v1_0.SetRepeatStateMethod;
import ExternalActionInterface.v1_0.SetShuffleStateMethod;
import ExternalActionInterface.v1_0.Station;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.Video;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.content.Loader;
import androidx.work.Data;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.explore.MusicEntityConverters;
import com.amazon.mp3.explore.playback.ExploreSequencer;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.navigation.playback.TracksLoadedHelper;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.handler.StationPlaybackHandler;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionResult;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.CtaAlbumRequester;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.data.AsinSubstitutionException;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.video.stories.VideoStoriesUtil;
import com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository;
import com.amazon.mp3.voice.deeplink.StationItemProvider;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.destination.PodcastDestination;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.router.Router;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.skyfire.ui.callback.PlaybackViewCallback;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.station.StationItem;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ExplorePlaybackProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ]2\u00020\u0001:\u0002]^B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J$\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001aH\u0002J\b\u0010\u001b\u001a\u00020\rH\u0002J\b\u0010\u001c\u001a\u00020\rH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\u001a\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0002J\u001a\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020'2\b\u0010!\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-H\u0002J\u001a\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u0002002\b\u0010!\u001a\u0004\u0018\u00010\u0003H\u0002J(\u00101\u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\u00032\f\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001a2\b\b\u0002\u00104\u001a\u00020\u0018J\u001e\u00101\u001a\u00020\r2\u0006\u00105\u001a\u0002062\f\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001aH\u0016J&\u00107\u001a\u00020\r2\u0006\u00105\u001a\u0002062\f\u00108\u001a\b\u0012\u0004\u0012\u0002090\u001a2\u0006\u00104\u001a\u00020\u0018H\u0016J\u001e\u0010:\u001a\u00020\r2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020 0\u001a2\u0006\u0010<\u001a\u00020\u0006H\u0016J\b\u0010=\u001a\u00020\rH\u0016J\b\u0010>\u001a\u00020\rH\u0002J\b\u0010?\u001a\u00020\rH\u0016J\u0010\u0010@\u001a\u00020\r2\u0006\u0010A\u001a\u00020\u0018H\u0016J0\u0010B\u001a\u00020\r2\b\u0010C\u001a\u0004\u0018\u00010\u00062\b\u0010D\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010\u00062\b\u0010F\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010G\u001a\u00020\r2\u0006\u0010H\u001a\u00020IH\u0016J\b\u0010J\u001a\u00020\rH\u0016J\b\u0010K\u001a\u00020\rH\u0016J*\u0010L\u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\u00032\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0003J\u001a\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020P2\b\b\u0002\u0010U\u001a\u00020\u0015H\u0002J\u0018\u0010V\u001a\u00020\r2\u0006\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020\u0015H\u0002J \u0010W\u001a\u00020\r2\u0006\u00105\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010[\u001a\u00020\r2\u0006\u0010/\u001a\u0002002\b\u0010!\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010\\\u001a\u00020\rH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExplorePlaybackProvider;", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "defaultTrackStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultTrackStateProvider;", "playbackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "changeRepeatMode", "", "state", "LExternalActionInterface/v1_0/SetRepeatStateMethod$State;", "changeShuffleMode", "LExternalActionInterface/v1_0/SetShuffleStateMethod$State;", "getSequencerPlaybackProvider", "Lcom/amazon/music/media/playback/SequencerPlaybackProvider;", "isIndexInRange", "", "E", "index", "", "list", "", "navigateToNowPlayingScreen", "nextTrack", "pause", PageUriUtils.DO_PLAY_VALUE, "playableContent", "LExternalActionInterface/v1_0/PlayableContent;", "context", "playAlbum", "album", "LExternalActionInterface/v1_0/Album;", "playArtist", "artist", "LExternalActionInterface/v1_0/Artist;", "playPlaylist", Environment.PLAYLIST_PATH, "LExternalActionInterface/v1_0/Playlist;", "playPodcastEpisode", "podcastEpisode", "LExternalActionInterface/v1_0/PodcastEpisode;", "playStation", "station", "LExternalActionInterface/v1_0/Station;", "playTracks", "tracks", "LExternalActionInterface/v1_0/Track;", "startingIndex", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "playVideoStories", "videos", "LExternalActionInterface/v1_0/Video;", "prefetchVideoStoriesForPlayback", "playableContents", ImagesContract.URL, "previousTrack", "removePlaybackListeners", "resume", "seekTo", "duration", "setNowPlayingScreenMetadata", "title", "primarySubtitle", "secondarySubtitle", "artworkUrl", "setPlaybackSpeed", "speed", "", "skipBackward", "skipForward", "startAugmentedShufflePlayback", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "contentUri", "Landroid/net/Uri;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "startPlayback", "uri", "shuffled", "startPlaybackNonCloudQueue", "startPlaybackTemplate", "Landroid/app/Activity;", "callback", "Lcom/amazon/music/skyfire/ui/callback/PlaybackViewCallback;", "startPlaybackWithStationSeed", "stopPlaybackAndClearQueue", "Companion", "ExploreCatalogActionListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExplorePlaybackProvider implements PlaybackProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Uri DEFAULT_URI = Uri.parse("");
    private static final Lazy<ExploreSequencer> exploreSequencer$delegate = LazyKt__LazyJVMKt.lazy(new Function0<ExploreSequencer>() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$Companion$exploreSequencer$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ExploreSequencer invoke() {
            return new ExploreSequencer();
        }
    });
    private static final Lazy<ActionValidatedPlaybackController> playbackController$delegate = LazyKt__LazyJVMKt.lazy(new Function0<ActionValidatedPlaybackController>() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$Companion$playbackController$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActionValidatedPlaybackController invoke() {
            return PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        }
    });
    private static final Lazy<Playback> podcastPlayback$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Playback>() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$Companion$podcastPlayback$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Playback invoke() {
            return Podcast.getPlayback();
        }
    });
    private final String TAG;
    private final Context applicationContext;
    private final DefaultTrackStateProvider defaultTrackStateProvider;
    private OnPlaybackEventListener playbackEventListener;

    /* JADX INFO: compiled from: ExplorePlaybackProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[SetRepeatStateMethod.State.values().length];
            iArr[SetRepeatStateMethod.State.ALL.ordinal()] = 1;
            iArr[SetRepeatStateMethod.State.ONE.ordinal()] = 2;
            iArr[SetRepeatStateMethod.State.OFF.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PodcastEpisode.PlaybackSource.values().length];
            iArr2[PodcastEpisode.PlaybackSource.CATALOG.ordinal()] = 1;
            iArr2[PodcastEpisode.PlaybackSource.PLAYLIST.ordinal()] = 2;
            iArr2[PodcastEpisode.PlaybackSource.LATEST.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public ExplorePlaybackProvider(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
        this.defaultTrackStateProvider = new DefaultTrackStateProvider(applicationContext);
        this.TAG = ExplorePlaybackProvider.class.getSimpleName();
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void playArtistSpotlight(Activity activity, PlaybackViewCallback playbackViewCallback, String str) {
        PlaybackProvider.DefaultImpls.playArtistSpotlight(this, activity, playbackViewCallback, str);
    }

    /* JADX INFO: compiled from: ExplorePlaybackProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExplorePlaybackProvider$Companion;", "", "()V", "DEFAULT_URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "exploreSequencer", "Lcom/amazon/mp3/explore/playback/ExploreSequencer;", "getExploreSequencer", "()Lcom/amazon/mp3/explore/playback/ExploreSequencer;", "exploreSequencer$delegate", "Lkotlin/Lazy;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "getPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController$delegate", "podcastPlayback", "Lcom/amazon/podcast/media/playback/Playback;", "getPodcastPlayback", "()Lcom/amazon/podcast/media/playback/Playback;", "podcastPlayback$delegate", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ExploreSequencer getExploreSequencer() {
            return (ExploreSequencer) ExplorePlaybackProvider.exploreSequencer$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ActionValidatedPlaybackController getPlaybackController() {
            return (ActionValidatedPlaybackController) ExplorePlaybackProvider.playbackController$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Playback getPodcastPlayback() {
            Object value = ExplorePlaybackProvider.podcastPlayback$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-podcastPlayback>(...)");
            return (Playback) value;
        }
    }

    /* JADX INFO: compiled from: ExplorePlaybackProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003B-\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b%\u0010&J\u001a\u0010\n\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016J,\u0010\u000e\u001a\u00020\t2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u000b2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001d\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R$\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010$¨\u0006'"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExplorePlaybackProvider$ExploreCatalogActionListener;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Landroidx/loader/content/Loader$OnLoadCompleteListener;", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "item", "", "canContinue", "", "continueCatalogAction", "Landroidx/loader/content/Loader;", "loader", "tracks", "onLoadComplete", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Ljava/util/List;", "getTracks", "()Ljava/util/List;", "", "startTime", "J", "getStartTime", "()J", "", "listPosition", "I", "getListPosition", "()I", "Lcom/amazon/mp3/explore/providers/ExploreTracksLoadedCallback;", "exploreTracksLoadedCallback", "Lcom/amazon/mp3/explore/providers/ExploreTracksLoadedCallback;", "Landroidx/loader/content/Loader;", "<init>", "(Landroid/content/Context;Ljava/util/List;JI)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    private static final class ExploreCatalogActionListener implements ContentAccessUtil.CatalogActionListener<PrimeTrack>, Loader.OnLoadCompleteListener<List<? extends MusicTrack>> {
        private final Context context;
        private ExploreTracksLoadedCallback exploreTracksLoadedCallback;
        private final int listPosition;
        private Loader<List<MusicTrack>> loader;
        private final long startTime;
        private final List<PrimeTrack> tracks;

        /* JADX WARN: Multi-variable type inference failed */
        public ExploreCatalogActionListener(Context context, List<? extends PrimeTrack> tracks, long j, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            this.context = context;
            this.tracks = tracks;
            this.startTime = j;
            this.listPosition = i;
            this.exploreTracksLoadedCallback = new ExploreTracksLoadedCallback(context, tracks, i, PlaybackPageType.SONGS_LIST, j);
        }

        @Override // androidx.loader.content.Loader.OnLoadCompleteListener
        public /* bridge */ /* synthetic */ void onLoadComplete(Loader<List<? extends MusicTrack>> loader, List<? extends MusicTrack> list) {
            onLoadComplete2((Loader<List<MusicTrack>>) loader, list);
        }

        public final int getListPosition() {
            return this.listPosition;
        }

        @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
        public void continueCatalogAction(PrimeTrack item, boolean canContinue) {
            if (canContinue) {
                Loader loaderOnCreateLoader = this.exploreTracksLoadedCallback.onCreateLoader(0, null);
                this.loader = loaderOnCreateLoader;
                if (loaderOnCreateLoader != null) {
                    loaderOnCreateLoader.registerListener(0, this);
                }
                Loader<List<MusicTrack>> loader = this.loader;
                if (loader == null) {
                    return;
                }
                loader.startLoading();
            }
        }

        /* JADX INFO: renamed from: onLoadComplete, reason: avoid collision after fix types in other method */
        public void onLoadComplete2(Loader<List<MusicTrack>> loader, List<? extends MusicTrack> tracks) {
            Intrinsics.checkNotNullParameter(loader, "loader");
            this.exploreTracksLoadedCallback.onLoadFinished2(loader, tracks);
            loader.unregisterListener(this);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void play(PlayableContent playableContent, Context context) {
        Intrinsics.checkNotNullParameter(playableContent, "playableContent");
        boolean z = playableContent instanceof PodcastEpisode;
        if (!z) {
            Companion companion = INSTANCE;
            if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
                companion.getPodcastPlayback().stopPodcastPlayback();
                companion.getPodcastPlayback().clear();
            }
        }
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (playableContent instanceof Album) {
            playAlbum((Album) playableContent);
            return;
        }
        if (playableContent instanceof Artist) {
            playArtist((Artist) playableContent, context);
            return;
        }
        if (playableContent instanceof Playlist) {
            playPlaylist((Playlist) playableContent);
            return;
        }
        if (playableContent instanceof Station) {
            playStation((Station) playableContent, context);
        } else if (playableContent instanceof Track) {
            playTracks$default(this, context, CollectionsKt__CollectionsJVMKt.listOf(playableContent), 0, 4, null);
        } else if (z) {
            playPodcastEpisode((PodcastEpisode) playableContent);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void setNowPlayingScreenMetadata(final String title, final String primarySubtitle, final String secondarySubtitle, final String artworkUrl) {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                ExplorePlaybackProvider.m983setNowPlayingScreenMetadata$lambda0(title, primarySubtitle, secondarySubtitle, artworkUrl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setNowPlayingScreenMetadata$lambda-0, reason: not valid java name */
    public static final void m983setNowPlayingScreenMetadata$lambda0(String str, String str2, String str3, String str4) {
        INSTANCE.getExploreSequencer().setNowPlayingScreenMetadata(str, str2, str3, str4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void startPlaybackTemplate(Activity activity, final PlaybackViewCallback callback, PlayableContent playableContent) {
        T tMusicExperiencePlaybackContent;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(playableContent, "playableContent");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            if (playableContent instanceof ArtistSpotlight) {
                tMusicExperiencePlaybackContent = ((ArtistSpotlight) playableContent).spotlightPlaybackContent();
            } else {
                if (!(playableContent instanceof MusicExperience)) {
                    throw new Exception("playable content type not supported in startPlaybackTemplate.");
                }
                tMusicExperiencePlaybackContent = ((MusicExperience) playableContent).musicExperiencePlaybackContent();
            }
            objectRef.element = tMusicExperiencePlaybackContent;
            activity.invalidateOptionsMenu();
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ExplorePlaybackProvider.m984startPlaybackTemplate$lambda2(objectRef, this, callback);
                }
            });
        } catch (Exception e) {
            Log.error(this.TAG, e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: startPlaybackTemplate$lambda-2, reason: not valid java name */
    public static final void m984startPlaybackTemplate$lambda2(Ref.ObjectRef url, ExplorePlaybackProvider this$0, final PlaybackViewCallback callback) {
        SequencerPlaybackProvider sequencerPlaybackProvider;
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Companion companion = INSTANCE;
        companion.getExploreSequencer().loadHttpsMediaItem((String) url.element);
        this$0.playbackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda8
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                ExplorePlaybackProvider.m985startPlaybackTemplate$lambda2$lambda1(callback, i, controlSource);
            }
        };
        companion.getPlaybackController().addOnPlaybackEventListener(this$0.playbackEventListener);
        if (callback.willBeRemoved() || (sequencerPlaybackProvider = this$0.getSequencerPlaybackProvider()) == null) {
            return;
        }
        sequencerPlaybackProvider.startSequencer(companion.getExploreSequencer(), companion.getPlaybackController().getControlSource(), Clock.now());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlaybackTemplate$lambda-2$lambda-1, reason: not valid java name */
    public static final void m985startPlaybackTemplate$lambda2$lambda1(PlaybackViewCallback callback, int i, ControlSource controlSource) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        if (i == 11) {
            callback.onAudioEnded();
        } else {
            if (i != 18) {
                return;
            }
            callback.startProgressBar(INSTANCE.getExploreSequencer().getDurationMillis());
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void playTracks(FragmentActivity activity, List<? extends Track> tracks) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.NOW_PLAYING);
        playTracks(activity, tracks, 0);
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void playVideoStories(FragmentActivity activity, List<? extends Video> videos, int startingIndex) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(videos, "videos");
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = videos.iterator();
        while (it.hasNext()) {
            String strAsin = ((Video) it.next()).asin();
            if (strAsin != null) {
                arrayList.add(strAsin);
            }
        }
        if (startingIndex < 0 || startingIndex >= videos.size()) {
            startingIndex = 0;
        }
        VideoStoriesUtil.INSTANCE.playVideoStories(activity, null, arrayList, (String) arrayList.get(startingIndex), CollectionsKt__CollectionsKt.emptyList(), jCurrentTimeMillis, null);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.PLAY_VIDEO).withBlockRef(null).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(PageType.BROWSE_ARTIST_DETAIL).withEntityId(EntityIdType.ASIN.toString()).build());
    }

    public static /* synthetic */ void playTracks$default(ExplorePlaybackProvider explorePlaybackProvider, Context context, List list, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        explorePlaybackProvider.playTracks(context, list, i);
    }

    public final void playTracks(final Context context, final List<? extends Track> tracks, final int startingIndex) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        if (tracks.isEmpty() || context == null) {
            return;
        }
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ExplorePlaybackProvider.m980playTracks$lambda7(context, this, tracks, startingIndex);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playTracks$lambda-7, reason: not valid java name */
    public static final void m980playTracks$lambda7(Context context, final ExplorePlaybackProvider this$0, List tracks, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tracks, "$tracks");
        if ((context instanceof Activity) && !ActivityKt.isAvailable((Activity) context)) {
            context = this$0.applicationContext;
        }
        final Context context2 = context;
        final long jNow = Clock.now();
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().stopPodcastPlayback();
            companion.getPodcastPlayback().clear();
        } else {
            this$0.removePlaybackListeners();
            PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    ExplorePlaybackProvider.m981playTracks$lambda7$lambda5();
                }
            });
        }
        int iMin = Math.min(PrimeTracksLoadedCallbacks.getMaxQueueSize(), tracks.size());
        final ArrayList arrayList = new ArrayList();
        StateProvider.Listener<PrimeTrack> listener = new StateProvider.Listener() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda7
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i2, int i3) {
                ExplorePlaybackProvider.m982playTracks$lambda7$lambda6(arrayList, this$0, i, context2, jNow, (PrimeTrack) obj, i2, i3);
            }
        };
        int i2 = 0;
        while (i2 < iMin) {
            int i3 = i2 + 1;
            PrimeTrack primeTrack = MusicEntityConverters.INSTANCE.toPrimeTrack((Track) tracks.get(i2));
            if (i2 == 0) {
                this$0.defaultTrackStateProvider.requestState(primeTrack, listener);
            } else {
                this$0.defaultTrackStateProvider.requestState(primeTrack, (StateProvider.Listener<PrimeTrack>) null);
            }
            arrayList.add(primeTrack);
            i2 = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playTracks$lambda-7$lambda-5, reason: not valid java name */
    public static final void m981playTracks$lambda7$lambda5() {
        PlayQueueSequencer.getInstance().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playTracks$lambda-7$lambda-6, reason: not valid java name */
    public static final void m982playTracks$lambda7$lambda6(List primeTracks, ExplorePlaybackProvider this$0, int i, Context availableContext, long j, PrimeTrack primeTrack, int i2, int i3) {
        Intrinsics.checkNotNullParameter(primeTracks, "$primeTracks");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(availableContext, "$availableContext");
        if (primeTracks.isEmpty() || !this$0.isIndexInRange(i, primeTracks)) {
            return;
        }
        ContentAccessUtil.checkCanContinueCatalogAction(availableContext, (SearchItem) primeTracks.get(i), ContentAccessUtil.ContentAccessOperation.STREAM, new ExploreCatalogActionListener(availableContext, primeTracks, j, i));
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void stopPlaybackAndClearQueue() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().stopPodcastPlayback();
            companion.getPodcastPlayback().clear();
        } else {
            removePlaybackListeners();
            companion.getPlaybackController().stop(ChangeReason.NEW_SOURCE);
            PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    ExplorePlaybackProvider.m986stopPlaybackAndClearQueue$lambda8();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopPlaybackAndClearQueue$lambda-8, reason: not valid java name */
    public static final void m986stopPlaybackAndClearQueue$lambda8() {
        PlayQueueSequencer.getInstance().clear();
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void pause() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().pause();
        } else {
            companion.getPlaybackController().pause();
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void resume() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            if (companion.getPodcastPlayback().getPlaybackState() == 2) {
                companion.getPodcastPlayback().playPause();
                return;
            }
            return;
        }
        companion.getPlaybackController().play();
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void seekTo(int duration) {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().seekTo(duration);
        } else {
            companion.getPlaybackController().seek(duration, 0);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void nextTrack() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().next();
        } else {
            companion.getPlaybackController().skipNextForResult();
            ActionResult actionResult = ActionResult.OK;
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void previousTrack() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().previous();
        } else {
            companion.getPlaybackController().previousOrRestart();
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void skipForward() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().fastForward();
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void prefetchVideoStoriesForPlayback(List<? extends PlayableContent> playableContents, String url) {
        String strAsin;
        Intrinsics.checkNotNullParameter(playableContents, "playableContents");
        Intrinsics.checkNotNullParameter(url, "url");
        ArrayList arrayList = new ArrayList();
        for (PlayableContent playableContent : playableContents) {
            Video video = playableContent instanceof Video ? (Video) playableContent : null;
            if (video != null && (strAsin = video.asin()) != null) {
                arrayList.add(strAsin);
            }
        }
        VideoStoryPlaybackDataRepository.INSTANCE.get().fetchAndStoreVideoStoriesTemplate(arrayList, url);
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void skipBackward() {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().rewind();
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void changeRepeatMode(SetRepeatStateMethod.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i == 1) {
            INSTANCE.getPlaybackController().setRepeatMode(RepeatMode.REPEAT_ALL);
        } else if (i == 2) {
            INSTANCE.getPlaybackController().setRepeatMode(RepeatMode.REPEAT_ONE);
        } else {
            if (i != 3) {
                return;
            }
            INSTANCE.getPlaybackController().setRepeatMode(RepeatMode.REPEAT_NONE);
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void changeShuffleMode(SetShuffleStateMethod.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        INSTANCE.getPlaybackController().setShuffled(state == SetShuffleStateMethod.State.ON);
    }

    @Override // com.amazon.music.skyfire.ui.providers.PlaybackProvider
    public void setPlaybackSpeed(float speed) {
        Companion companion = INSTANCE;
        if (companion.getPodcastPlayback().isPodcastLastPlayed()) {
            companion.getPodcastPlayback().setPlaybackSpeed(PlaybackSpeed.lookupByValue(speed));
        }
    }

    private final SequencerPlaybackProvider getSequencerPlaybackProvider() {
        com.amazon.music.media.playback.PlaybackProvider playbackProvider = com.amazon.music.media.playback.Playback.getInstance().getPlaybackProvider();
        if (playbackProvider instanceof SequencerPlaybackProvider) {
            return (SequencerPlaybackProvider) playbackProvider;
        }
        return null;
    }

    private final void playAlbum(final Album album) {
        MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.NOW_PLAYING);
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws ServiceException, BrowseException, AbstractHttpClient.HttpClientException, AsinSubstitutionException {
                ExplorePlaybackProvider.m978playAlbum$lambda13$lambda12(this.f$0, album);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playAlbum$lambda-13$lambda-12, reason: not valid java name */
    public static final void m978playAlbum$lambda13$lambda12(ExplorePlaybackProvider this$0, Album this_with) throws ServiceException, BrowseException, AbstractHttpClient.HttpClientException, AsinSubstitutionException {
        Long albumOrArtistIdByAsin;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        new CtaAlbumRequester(this$0.applicationContext, true).requestTracksForAsin(this_with.asin());
        Uri contentUri = MediaProvider.Albums.getContentUri("cirrus");
        if (contentUri != null && (albumOrArtistIdByAsin = CirrusMediaSource.getAlbumOrArtistIdByAsin(this$0.applicationContext, contentUri, this_with.asin())) != null) {
            Uri albumTracksUri = MediaProvider.Albums.Tracks.getContentUri("cirrus", albumOrArtistIdByAsin.longValue());
            Intrinsics.checkNotNullExpressionValue(albumTracksUri, "albumTracksUri");
            startPlayback$default(this$0, albumTracksUri, false, 2, null);
            return;
        }
        this$0.stopPlaybackAndClearQueue();
    }

    private final void playArtist(Artist artist, Context context) {
        PrimeCollectionTask.Task task;
        MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.NOW_PLAYING);
        if (AmazonApplication.getCapabilities().shouldOverrideAugmentedShuffleWithSfa()) {
            if (context == null) {
                return;
            }
            StationUtils.startSFA(context, artist.asin(), "ARTIST_SEED", PlaybackPageType.ARTIST_DETAIL, ControlSource.APP_UI);
        } else {
            if (context == null) {
                return;
            }
            if (artist.shuffle()) {
                task = PrimeCollectionTask.Task.PLAYBACK_SHUFFLE;
            } else {
                task = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
            }
            HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
            new PrimeArtistPlaybackHandler(context).startPlayback(MusicEntityConverters.INSTANCE.toPrimeArtist(artist), task);
        }
    }

    private final void playPlaylist(final Playlist playlist) {
        MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.NOW_PLAYING);
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
                ExplorePlaybackProvider.m979playPlaylist$lambda18$lambda17(playlist, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playPlaylist$lambda-18$lambda-17, reason: not valid java name */
    public static final void m979playPlaylist$lambda18$lambda17(Playlist this_with, ExplorePlaybackProvider this$0) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_with.asin() != null) {
            PrimePlaylistUtil.fetchPrimePlaylist(new PrimePlaylistDatabaseManager(this$0.applicationContext), 0, this_with.asin());
            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
            String strAsin = this_with.asin();
            Intrinsics.checkNotNull(strAsin);
            Intrinsics.checkNotNullExpressionValue(strAsin, "asin()!!");
            startPlayback$default(this$0, contentPlaybackUtils.getPlaylistUri(strAsin), false, 2, null);
            return;
        }
        if (this_with.luid() != null) {
            Uri uri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.UdoPlaylists.getContentUriFromLuid(this$0.applicationContext, "cirrus", this_with.luid()));
            Intrinsics.checkNotNullExpressionValue(uri, "uri");
            startPlayback$default(this$0, uri, false, 2, null);
        }
    }

    private final void playStation(Station station, Context context) {
        PrimeCollectionTask.Task task;
        Unit unit;
        MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.NOW_PLAYING);
        String strKey = station.key();
        if (strKey == null) {
            unit = null;
        } else {
            StationItemProvider stationItemProvider = new StationItemProvider();
            String strTitle = station.title();
            if (strTitle == null) {
                strTitle = "";
            }
            String strImage = station.image();
            StationItem stationItemWithKey = stationItemProvider.getStationItemWithKey(strKey, strTitle, strImage != null ? strImage : "");
            if (AmazonApplication.isApplicationForeground()) {
                task = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
            } else {
                task = PrimeCollectionTask.Task.PLAYBACK;
            }
            new StationPlaybackHandler(context, PlaybackPageType.UNKNOWN).checkCanStartPlayback(PrimeItemsTransformationUtil.toPrimeStation(stationItemWithKey), task);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            startPlaybackWithStationSeed(station, context);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void startPlaybackWithStationSeed(ExternalActionInterface.v1_0.Station r8, android.content.Context r9) {
        /*
            r7 = this;
            java.lang.String r0 = "TRACK_SEED"
            java.lang.String r1 = new java.lang.String
            r1.<init>()
            java.lang.String r2 = new java.lang.String
            r2.<init>()
            com.amazon.mp3.library.item.MusicTrack r3 = new com.amazon.mp3.library.item.MusicTrack
            r3.<init>()
            java.util.List r8 = r8.seed()     // Catch: java.lang.Exception -> L74
            java.lang.String r4 = "station.seed()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r4)     // Catch: java.lang.Exception -> L74
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Exception -> L74
            r4.<init>()     // Catch: java.lang.Exception -> L74
            java.util.Iterator r8 = r8.iterator()     // Catch: java.lang.Exception -> L74
        L24:
            boolean r5 = r8.hasNext()     // Catch: java.lang.Exception -> L74
            if (r5 == 0) goto L7e
            java.lang.Object r5 = r8.next()     // Catch: java.lang.Exception -> L74
            ExternalActionInterface.v1_0.PlayableContent r5 = (ExternalActionInterface.v1_0.PlayableContent) r5     // Catch: java.lang.Exception -> L74
            boolean r6 = r5 instanceof ExternalActionInterface.v1_0.Artist     // Catch: java.lang.Exception -> L74
            if (r6 == 0) goto L41
            ExternalActionInterface.v1_0.Artist r5 = (ExternalActionInterface.v1_0.Artist) r5     // Catch: java.lang.Exception -> L74
            java.lang.String r5 = r5.asin()     // Catch: java.lang.Exception -> L74
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch: java.lang.Exception -> L74
            java.lang.String r1 = "ARTIST_SEED"
            goto L5f
        L41:
            boolean r6 = r5 instanceof ExternalActionInterface.v1_0.Track     // Catch: java.lang.Exception -> L74
            if (r6 == 0) goto L68
            r6 = r5
            ExternalActionInterface.v1_0.Track r6 = (ExternalActionInterface.v1_0.Track) r6     // Catch: java.lang.Exception -> L74
            java.lang.String r6 = r6.asin()     // Catch: java.lang.Exception -> L74
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch: java.lang.Exception -> L74
            com.amazon.mp3.explore.MusicEntityConverters$Companion r1 = com.amazon.mp3.explore.MusicEntityConverters.INSTANCE     // Catch: java.lang.Exception -> L65
            java.lang.String r6 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch: java.lang.Exception -> L65
            ExternalActionInterface.v1_0.Track r5 = (ExternalActionInterface.v1_0.Track) r5     // Catch: java.lang.Exception -> L65
            com.amazon.mp3.library.item.MusicTrack r1 = r1.toMusicTrack(r5)     // Catch: java.lang.Exception -> L65
            r3 = r1
            r1 = r0
        L5f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Exception -> L74
            r4.add(r5)     // Catch: java.lang.Exception -> L74
            goto L24
        L65:
            r8 = move-exception
            r1 = r0
            goto L75
        L68:
            java.lang.Exception r8 = new java.lang.Exception     // Catch: java.lang.Exception -> L74
            java.lang.String r4 = "Unhandled PlayableContent for "
            java.lang.String r4 = kotlin.jvm.internal.Intrinsics.stringPlus(r4, r5)     // Catch: java.lang.Exception -> L74
            r8.<init>(r4)     // Catch: java.lang.Exception -> L74
            throw r8     // Catch: java.lang.Exception -> L74
        L74:
            r8 = move-exception
        L75:
            java.lang.String r4 = r7.TAG
            java.lang.String r8 = r8.toString()
            com.amazon.mp3.util.Log.error(r4, r8)
        L7e:
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r8 == 0) goto L95
            com.amazon.mp3.library.provider.MediaProvider$PrimeBrowseTracksCollection$ContentSource r8 = com.amazon.mp3.library.provider.MediaProvider.PrimeBrowseTracksCollection.ContentSource.RECOMMENDED
            android.net.Uri r8 = com.amazon.mp3.library.provider.MediaProvider.PrimeBrowseTracksCollection.FirstTrack.getContentUri(r8, r2)
            java.lang.String r0 = "contentUri"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
            com.amazon.music.metrics.mts.event.types.PlaybackPageType r0 = com.amazon.music.metrics.mts.event.types.PlaybackPageType.TRACK_DETAIL
            r7.startAugmentedShufflePlayback(r9, r3, r8, r0)
            return
        L95:
            if (r9 != 0) goto L98
            goto L9f
        L98:
            com.amazon.music.metrics.mts.event.types.PlaybackPageType r8 = com.amazon.music.metrics.mts.event.types.PlaybackPageType.TRACK_DETAIL
            com.amazon.music.media.playback.ControlSource r0 = com.amazon.music.media.playback.ControlSource.APP_UI
            com.amazon.mp3.card.prime.StationUtils.startSFA(r9, r2, r1, r8, r0)
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.explore.providers.ExplorePlaybackProvider.startPlaybackWithStationSeed(ExternalActionInterface.v1_0.Station, android.content.Context):void");
    }

    private final void playPodcastEpisode(PodcastEpisode podcastEpisode) {
        String strEpisodePlay;
        PodcastEpisode.PlaybackSource playbackSourceSource = podcastEpisode.source();
        int i = playbackSourceSource == null ? -1 : WhenMappings.$EnumSwitchMapping$1[playbackSourceSource.ordinal()];
        if (i == 1) {
            strEpisodePlay = Deeplinks.episodePlay(podcastEpisode.podcastId(), podcastEpisode.episodeId(), podcastEpisode.podcastTitle(), podcastEpisode.episodeTitle());
        } else {
            if (i != 2) {
                if (i != 3) {
                    Log.error(this.TAG, "Unknown podcast episode source");
                    return;
                }
                return;
            }
            strEpisodePlay = Deeplinks.savedEpisodePlay(podcastEpisode.episodeId());
        }
        try {
            Router.get(this.applicationContext).navigateTo(new PodcastDestination(null, null, strEpisodePlay));
        } catch (UnknownDestinationException e) {
            Log.error(this.TAG, "Failed to play podcast episode", e);
        }
    }

    static /* synthetic */ void startPlayback$default(ExplorePlaybackProvider explorePlaybackProvider, Uri uri, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        explorePlaybackProvider.startPlayback(uri, z);
    }

    private final void startPlayback(final Uri uri, final boolean shuffled) {
        if (Intrinsics.areEqual(uri, DEFAULT_URI)) {
            stopPlaybackAndClearQueue();
            return;
        }
        removePlaybackListeners();
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            Context context = this.applicationContext;
            Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(uri);
            Uri uri2 = nonTrackCollectionUri == null ? uri : nonTrackCollectionUri;
            Intrinsics.checkNotNullExpressionValue(uri2, "MediaProvider.Tracks.get…CollectionUri(uri) ?: uri");
            PlaybackPageType playbackPageType = PlaybackPageType.UNKNOWN;
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            StartPlaybackUtil.startCollectionPlayback(context, (Data.MAX_DATA_BYTES & 2) != 0 ? null : null, uri2, null, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : 0, (Data.MAX_DATA_BYTES & 32) != 0 ? false : shuffled, (Data.MAX_DATA_BYTES & 64) != 0 ? true : true, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, playbackPageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : false, APP_UI, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : null, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider.startPlayback.1
                @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                public void handlePlayback() {
                    ExplorePlaybackProvider.this.startPlaybackNonCloudQueue(uri, shuffled);
                }
            });
            return;
        }
        startPlaybackNonCloudQueue(uri, shuffled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlaybackNonCloudQueue(Uri uri, boolean shuffled) {
        PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, uri, null, null, null, 0, shuffled, new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, PlaybackPageType.UNKNOWN), null, Clock.now());
        navigateToNowPlayingScreen();
    }

    @Deprecated(message = "This function is only being used for TDP (TrackDetailsPage) experiment and will be cleaned up")
    private final void startAugmentedShufflePlayback(final Context context, final MusicTrack track, Uri contentUri, final PlaybackPageType playbackPageType) {
        if (context == null || track == null) {
            return;
        }
        List listSingletonList = Collections.singletonList(track);
        ControlSource APP_UI = ControlSource.APP_UI;
        Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
        StartPlaybackUtil.startMusicTracksPlayback(context, contentUri, "", listSingletonList, (14336 & 16) != 0 ? 0 : 0, (14336 & 32) != 0 ? false : false, (14336 & 64) != 0 ? true : true, playbackPageType, (14336 & 256) != 0 ? false : false, APP_UI, (14336 & 1024) != 0 ? null : null, (14336 & 2048) != 0 ? false : false, (14336 & 4096) != 0 ? null : null, (14336 & 8192) != 0 ? null : null, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.explore.providers.ExplorePlaybackProvider$startAugmentedShufflePlayback$1$1$1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                TracksLoadedHelper tracksLoadedHelper = new TracksLoadedHelper(context, 0, false, Clock.now(), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN, playbackPageType);
                List<? extends MusicTrack> listSingletonList2 = Collections.singletonList(track);
                Intrinsics.checkNotNullExpressionValue(listSingletonList2, "singletonList(track)");
                tracksLoadedHelper.onLoadedFinishedHelper(listSingletonList2);
            }
        });
    }

    private final void removePlaybackListeners() {
        if (this.playbackEventListener == null) {
            return;
        }
        INSTANCE.getPlaybackController().removeOnPlaybackEventListener(this.playbackEventListener);
        this.playbackEventListener = null;
    }

    private final void navigateToNowPlayingScreen() {
        if (AmazonApplication.isApplicationForeground()) {
            Intent intent = new Intent(this.applicationContext, (Class<?>) NowPlayingFragmentActivity.class);
            intent.setFlags(268435456);
            intent.putExtra("com.amazon.mp3.fragment.extra", NowPlayingFragment.class.getSimpleName());
            this.applicationContext.startActivity(intent);
        }
    }

    private final <E> boolean isIndexInRange(int index, List<? extends E> list) {
        return index >= 0 && index < list.size();
    }
}
