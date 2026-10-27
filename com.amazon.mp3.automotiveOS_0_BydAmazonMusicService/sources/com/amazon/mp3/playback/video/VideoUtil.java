package com.amazon.mp3.playback.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.fragment.VideoPlaylistDetailModel;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.TrackLookup;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.TrackItem;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt__StringsJVMKt;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: VideoUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b?\u0010@J0\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u001c\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0007J&\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\u0006\u0010\u0007\u001a\u00020\u0006H\u0007JB\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\bH\u0007J\u000e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002J:\u0010$\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\f0\u001f2\u0016\b\u0002\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\f\u0018\u00010\u001fJ\u0014\u0010'\u001a\u00020\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0016J&\u0010/\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020)2\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-JD\u0010/\u001a\u00020\f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010*\u001a\u00020)2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001d2\u0006\u0010,\u001a\u00020+2\b\b\u0002\u0010.\u001a\u00020-2\n\b\u0002\u00102\u001a\u0004\u0018\u000101H\u0007J\u000e\u00103\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u00104\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002J\u0012\u00107\u001a\u00020\b2\b\u00106\u001a\u0004\u0018\u000105H\u0007J\u0014\u00109\u001a\u00020\f2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0016J\u0016\u0010;\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010:\u001a\u00020\u001dR\u001c\u0010=\u001a\n <*\u0004\u0018\u00010\u001d0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>¨\u0006A"}, d2 = {"Lcom/amazon/mp3/playback/video/VideoUtil;", "", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "playlistUri", "", "position", "", "isShuffled", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "mMetricInformation", "", "startPlayQueueSequencerAndNowPlaying", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "primeTrack", "updateTrackDurationToMilliSeconds", "Landroid/os/Bundle;", "extras", "playVideo", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "", "primeTracks", "playlistTracks", "shuffle", "isUserInitiated", "playVideoPlaylist", "launchNowPlayingActivity", "", "asin", "Lkotlin/Function1;", "Lcom/amazon/music/storeservice/model/PlaylistItem;", "onContentReceived", "Lcom/amazon/mp3/util/coroutines/LookupError;", "onError", "fetchVideoPlaylistItem", "Lcom/amazon/music/models/AssetQuality;", "assetQualities", "getDefaultOrFirstAvailablePID", voMimeTypes.VOBASE_TYPE_VIDEO, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "sendUiClickMetric", "blockRef", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "pageSubType", "isConnectivityOtherThanWifi", "isVideoStreamingPreferenceWifi", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "isInPictureInPictureMode", "pidList", "cacheVideos", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "fetchTrack", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoUtil {
    public static final VideoUtil INSTANCE = new VideoUtil();
    private static final String TAG = VideoUtil.class.getSimpleName();

    @JvmOverloads
    public final void playVideo(PrimeTrack primeTrack) {
        Intrinsics.checkNotNullParameter(primeTrack, "primeTrack");
        playVideo$default(this, primeTrack, null, 2, null);
    }

    private VideoUtil() {
    }

    public static /* synthetic */ void playVideo$default(VideoUtil videoUtil, PrimeTrack primeTrack, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = null;
        }
        videoUtil.playVideo(primeTrack, bundle);
    }

    @JvmOverloads
    public final void playVideo(PrimeTrack primeTrack, Bundle extras) {
        Intrinsics.checkNotNullParameter(primeTrack, "primeTrack");
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        if (extras != null) {
            currentActivity.getIntent().putExtras(extras);
        }
        if (ActivityKt.isAvailable(currentActivity) && (currentActivity instanceof FragmentActivity)) {
            playVideo((FragmentActivity) currentActivity, CollectionsKt__CollectionsJVMKt.listOf(primeTrack), 0);
        }
    }

    @JvmOverloads
    public final void playVideo(final FragmentActivity fragmentActivity, final List<? extends PrimeTrack> primeTracks, final int position) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        Intrinsics.checkNotNullParameter(primeTracks, "primeTracks");
        if (CastingUtil.isCasting()) {
            CastingUtil.showCastingContentUnavailable(fragmentActivity);
            return;
        }
        final long jNow = Clock.now();
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).stop(ChangeReason.NEW_SOURCE);
        PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoUtil.m1507playVideo$lambda0();
            }
        });
        final PrimeTrack primeTrack = primeTracks.get(position);
        DefaultTrackStateProvider defaultTrackStateProvider = new DefaultTrackStateProvider(fragmentActivity);
        final ContentAccessUtil.CatalogActionListener catalogActionListener = new ContentAccessUtil.CatalogActionListener() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
            public final void continueCatalogAction(CatalogContent catalogContent, boolean z) {
                VideoUtil.m1508playVideo$lambda1(fragmentActivity, primeTracks, position, jNow, (PrimeTrack) catalogContent, z);
            }
        };
        StateProvider.Listener<PrimeTrack> listener = new StateProvider.Listener() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda2
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i, int i2) {
                VideoUtil.m1509playVideo$lambda2(primeTracks, fragmentActivity, primeTrack, catalogActionListener, (PrimeTrack) obj, i, i2);
            }
        };
        for (PrimeTrack primeTrack2 : primeTracks) {
            if (Intrinsics.areEqual(primeTrack, primeTrack2)) {
                defaultTrackStateProvider.requestState(primeTrack2, listener);
            } else {
                defaultTrackStateProvider.requestState(primeTrack2, (StateProvider.Listener<PrimeTrack>) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playVideo$lambda-0, reason: not valid java name */
    public static final void m1507playVideo$lambda0() {
        PlayQueueSequencer.getInstance().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playVideo$lambda-1, reason: not valid java name */
    public static final void m1508playVideo$lambda1(FragmentActivity fragmentActivity, List primeTracks, int i, long j, PrimeTrack primeTrack, boolean z) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$fragmentActivity");
        Intrinsics.checkNotNullParameter(primeTracks, "$primeTracks");
        Intrinsics.checkNotNullParameter(primeTrack, "primeTrack");
        if (z) {
            PrimeTracksLoadedCallbacks primeTracksLoadedCallbacks = new PrimeTracksLoadedCallbacks(fragmentActivity, (List<PrimeTrack>) primeTracks, i, PlaybackPageType.PRIME_BROWSE_SEARCH, j);
            LoaderManager supportLoaderManager = fragmentActivity.getSupportLoaderManager();
            if (supportLoaderManager == null) {
                return;
            }
            supportLoaderManager.initLoader(0, null, primeTracksLoadedCallbacks);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playVideo$lambda-2, reason: not valid java name */
    public static final void m1509playVideo$lambda2(List primeTracks, FragmentActivity fragmentActivity, PrimeTrack firstTrack, ContentAccessUtil.CatalogActionListener catalogActionListener, PrimeTrack primeTrack, int i, int i2) {
        Intrinsics.checkNotNullParameter(primeTracks, "$primeTracks");
        Intrinsics.checkNotNullParameter(fragmentActivity, "$fragmentActivity");
        Intrinsics.checkNotNullParameter(firstTrack, "$firstTrack");
        Intrinsics.checkNotNullParameter(catalogActionListener, "$catalogActionListener");
        if (primeTracks.isEmpty()) {
            return;
        }
        ContentAccessUtil.checkCanContinueCatalogAction(fragmentActivity, firstTrack, ContentAccessUtil.ContentAccessOperation.STREAM, catalogActionListener);
    }

    public static /* synthetic */ void playVideoPlaylist$default(VideoUtil videoUtil, Context context, Uri uri, List list, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z = false;
        }
        boolean z3 = z;
        if ((i2 & 32) != 0) {
            z2 = true;
        }
        videoUtil.playVideoPlaylist(context, uri, list, i, z3, z2);
    }

    @JvmOverloads
    public final void playVideoPlaylist(final Context context, final Uri playlistUri, final List<? extends PrimeTrack> playlistTracks, final int position, final boolean shuffle, final boolean isUserInitiated) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        Intrinsics.checkNotNullParameter(playlistTracks, "playlistTracks");
        if (CastingUtil.isCasting()) {
            CastingUtil.showCastingContentUnavailable(context);
            return;
        }
        PrimeTrack primeTrack = playlistTracks.get(position);
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        if (!shuffle && MediaItemHelper.isCurrentlyPlayingItem(primeTrack.getAsin())) {
            controller.play();
            launchNowPlayingActivity(context);
            return;
        }
        final PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, PlaybackPageType.PRIME_PLAYLIST_DETAIL);
        PrimeTrack primeTrack2 = playlistTracks.get(position);
        controller.stop(ChangeReason.NEW_SOURCE);
        ContentAccessUtil.checkCanContinueCatalogAction((FragmentActivity) context, primeTrack2, ContentAccessUtil.ContentAccessOperation.STREAM, new ContentAccessUtil.CatalogActionListener() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda3
            @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
            public final void continueCatalogAction(CatalogContent catalogContent, boolean z) {
                VideoUtil.m1510playVideoPlaylist$lambda4(context, playlistTracks, shuffle, position, playlistUri, isUserInitiated, playbackMetricInformation, (PrimeTrack) catalogContent, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playVideoPlaylist$lambda-4, reason: not valid java name */
    public static final void m1510playVideoPlaylist$lambda4(final Context context, List playlistTracks, final boolean z, final int i, final Uri playlistUri, boolean z2, final PlaybackMetricInformation mMetricInformation, PrimeTrack primeTrack, boolean z3) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(playlistTracks, "$playlistTracks");
        Intrinsics.checkNotNullParameter(playlistUri, "$playlistUri");
        Intrinsics.checkNotNullParameter(mMetricInformation, "$mMetricInformation");
        Intrinsics.checkNotNullParameter(primeTrack, "primeTrack");
        if (z3) {
            if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
                CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
                int iNextInt = z ? Random.INSTANCE.nextInt(playlistTracks.size()) : i;
                PlaybackPageType playbackPageType = PlaybackPageType.PRIME_PLAYLIST_DETAIL;
                ControlSource APP_UI = ControlSource.APP_UI;
                Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
                CloudQueuePlaybackUtil.Companion.startQueueInteractionWithPrimeTracks$default(companion, "", context, playlistTracks, iNextInt, z, null, playlistUri, playbackPageType, APP_UI, false, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.playback.video.VideoUtil$playVideoPlaylist$catalogActionListener$1$1
                    @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                    public void handlePlayback() {
                        VideoUtil.INSTANCE.startPlayQueueSequencerAndNowPlaying(context, playlistUri, i, z, mMetricInformation);
                    }
                }, z2, 512, null);
                return;
            }
            INSTANCE.startPlayQueueSequencerAndNowPlaying(context, playlistUri, i, z, mMetricInformation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlayQueueSequencerAndNowPlaying(Context context, Uri playlistUri, int position, boolean isShuffled, PlaybackMetricInformation mMetricInformation) {
        PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, playlistUri, null, null, null, isShuffled ? -2 : position, isShuffled, mMetricInformation, null, Clock.now());
        launchNowPlayingActivity(context);
    }

    public final void launchNowPlayingActivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent(LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void fetchVideoPlaylistItem(final String asin, final Function1<? super PlaylistItem, Unit> onContentReceived, final Function1<? super LookupError, Unit> onError) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(onContentReceived, "onContentReceived");
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoUtil.m1504fetchVideoPlaylistItem$lambda5(asin, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoUtil.m1505fetchVideoPlaylistItem$lambda6(onContentReceived, (PlaylistItem) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda6
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoUtil.m1506fetchVideoPlaylistItem$lambda7(asin, onError, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchVideoPlaylistItem$lambda-5, reason: not valid java name */
    public static final void m1504fetchVideoPlaylistItem$lambda5(String asin, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        subscriber.onNext(VideoPlaylistDetailModel.INSTANCE.getPlaylistContent(asin));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchVideoPlaylistItem$lambda-6, reason: not valid java name */
    public static final void m1505fetchVideoPlaylistItem$lambda6(Function1 onContentReceived, PlaylistItem it) {
        Intrinsics.checkNotNullParameter(onContentReceived, "$onContentReceived");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        onContentReceived.invoke(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchVideoPlaylistItem$lambda-7, reason: not valid java name */
    public static final void m1506fetchVideoPlaylistItem$lambda7(String asin, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        Log.error(TAG, Intrinsics.stringPlus("Error fetching video playlist content for asin: ", asin));
        if ((th instanceof BrowseException) && ((BrowseException) th).getReason() == BrowseException.Reason.NETWORK_ERROR) {
            if (function1 == null) {
                return;
            }
            function1.invoke(LookupError.NetworkError.INSTANCE);
        } else {
            if (function1 == null) {
                return;
            }
            function1.invoke(LookupError.UnknownError.INSTANCE);
        }
    }

    public final String getDefaultOrFirstAvailablePID(List<AssetQuality> assetQualities) {
        Intrinsics.checkNotNullParameter(assetQualities, "assetQualities");
        if (assetQualities.isEmpty()) {
            return "";
        }
        for (AssetQuality assetQuality : assetQualities) {
            if (Intrinsics.areEqual("DEFAULT", assetQuality.getQuality()) && !StringsKt__StringsJVMKt.isBlank(assetQuality.getPid())) {
                return assetQuality.getPid();
            }
        }
        for (AssetQuality assetQuality2 : assetQualities) {
            if (!StringsKt__StringsJVMKt.isBlank(assetQuality2.getPid())) {
                return assetQuality2.getPid();
            }
        }
        return "";
    }

    public final void sendUiClickMetric(PrimeTrack video, ActionType actionType, PageType pageType, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(video, "video");
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        sendUiClickMetric$default(this, video.getAsin(), actionType, video.getBlockRef(), pageType, interactionType, null, 32, null);
    }

    public static /* synthetic */ void sendUiClickMetric$default(VideoUtil videoUtil, String str, ActionType actionType, String str2, PageType pageType, InteractionType interactionType, PageSubType pageSubType, int i, Object obj) {
        videoUtil.sendUiClickMetric(str, actionType, (i & 4) != 0 ? null : str2, pageType, (i & 16) != 0 ? InteractionType.TAP : interactionType, (i & 32) != 0 ? null : pageSubType);
    }

    @JvmOverloads
    public final void sendUiClickMetric(String asin, ActionType actionType, String blockRef, PageType pageType, InteractionType interactionType, PageSubType pageSubType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        UiClickEvent.Builder builderWithEntityType = UiClickEvent.builder(actionType).withPageType(pageType).withInteractionType(interactionType).withEventTime(System.currentTimeMillis()).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withEntityType(EntityType.VIDEO);
        if (blockRef != null) {
            builderWithEntityType.withBlockRef(blockRef);
        }
        if (pageSubType != null) {
            builderWithEntityType.withPageSubType(pageSubType.toString());
        }
        UiClickEvent uiClickEventBuild = builderWithEntityType.build();
        Intrinsics.checkNotNullExpressionValue(uiClickEventBuild, "builder(actionType)\n    …\n                .build()");
        MetricsHolder.getManager().handleEvent(uiClickEventBuild);
    }

    public final boolean isConnectivityOtherThanWifi(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean zIsWifiConnected = ConnectivityUtil.isWifiConnected(context);
        boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection(context);
        Log.debug(TAG, "isWifiConnected = %s, hasAnyInternetConnection = %s", Boolean.valueOf(zIsWifiConnected), Boolean.valueOf(zHasAnyInternetConnection));
        return zHasAnyInternetConnection && !zIsWifiConnected;
    }

    public final boolean isVideoStreamingPreferenceWifi(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Intrinsics.areEqual(SettingsUtil.getStreamingNetworkPreference(context, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO), "WIFI_ONLY");
    }

    @SuppressLint({"NewApi"})
    public final boolean isInPictureInPictureMode(Activity activity) {
        if (AmazonApplication.getCapabilities().isPipEnabled()) {
            if ((activity != null && ActivityKt.isAvailable(activity)) && activity.isInPictureInPictureMode()) {
                return true;
            }
        }
        return false;
    }

    public final void cacheVideos(final List<String> pidList) {
        Intrinsics.checkNotNullParameter(pidList, "pidList");
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.video.VideoUtil$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoUtil.m1503cacheVideos$lambda14(pidList, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: cacheVideos$lambda-14, reason: not valid java name */
    public static final void m1503cacheVideos$lambda14(List pidList, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(pidList, "$pidList");
        Player primeVideoPlayer = Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer();
        PrimeVideoPlayer primeVideoPlayer2 = primeVideoPlayer instanceof PrimeVideoPlayer ? (PrimeVideoPlayer) primeVideoPlayer : null;
        if (primeVideoPlayer2 != null) {
            primeVideoPlayer2.cacheVideos(pidList);
        }
        subscriber.onCompleted();
    }

    private final void updateTrackDurationToMilliSeconds(PrimeTrack primeTrack) {
        primeTrack.setDuration(Integer.valueOf((int) TimeUnit.MILLISECONDS.convert(primeTrack.getDuration() == null ? 0L : r1.intValue(), TimeUnit.SECONDS)));
    }

    public final PrimeTrack fetchTrack(Context context, String trackAsin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        BrowseService browseServiceCreateBrowseService = BrowseFactory.createBrowseService(context);
        Intrinsics.checkNotNullExpressionValue(browseServiceCreateBrowseService, "createBrowseService(context)");
        List<TrackItem> list = new TrackLookup(browseServiceCreateBrowseService).get(new TrackLookup.Request(trackAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(context), AccountCredentialStorage.get(context).getDeviceId(), AccountCredentialStorage.get(context).getDeviceType(), new BrowseLanguageProvider().getLocale(context), AmazonApplication.getCapabilities().isVideosEnabled(), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context))).tracks;
        PrimeTrack primeTrack = PrimeItemsTransformationUtil.toPrimeTrack(list == null ? null : list.get(0));
        Intrinsics.checkNotNullExpressionValue(primeTrack, "primeTrack");
        updateTrackDurationToMilliSeconds(primeTrack);
        return primeTrack;
    }
}
