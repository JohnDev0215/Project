package com.amazon.video.sdk.player.playlist;

import com.amazon.avod.events.proxy.EventListenerProxy;
import com.amazon.avod.events.proxy.RetroactiveEventListenerProxyFactory;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.listener.VideoPresentationEventListenerSet;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playbackclient.presentation.PresentationCache;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.PVDownloadsPlayerShim;
import com.amazon.avod.util.SDKPlayerLogger;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.video.sdk.PlayerSdkConfig;
import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.player.AbstractVideoPresentationEventListener;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.player.FeatureConfigs;
import com.amazon.video.sdk.player.PlayerConfig;
import com.amazon.video.sdk.player.PlayerImpl;
import com.amazon.video.sdk.player.RenderingConfig;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.player.playlist.PlaylistEvent;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureImpl;
import com.amazon.video.sdk.player.util.VideoSpecUtil;
import com.amazon.video.sdk.stream.util.MultiTrackAudioUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistFeatureImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0010\u0018\u0000 r2\u00020\u00012\u00020\u0002:\u0003srtBy\u0012\u0006\u0010(\u001a\u00020'\u0012\u0006\u0010+\u001a\u00020*\u0012\u0006\u0010.\u001a\u00020-\u0012\u0006\u00101\u001a\u000200\u0012\u0006\u00104\u001a\u000203\u0012\b\b\u0002\u00107\u001a\u000206\u0012\b\b\u0002\u0010:\u001a\u000209\u0012\b\b\u0002\u0010=\u001a\u00020<\u0012\b\b\u0002\u0010@\u001a\u00020?\u0012\b\b\u0002\u0010B\u001a\u00020\t\u0012\u0016\b\u0002\u0010G\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010E\u0012\u0004\u0012\u00020F0D¢\u0006\u0004\bp\u0010qJ\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\f\u001a\u00020\u0007H\u0016J\u001a\u0010\u0010\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0016JG\u0010 \u001a\u00020\u0007\"\b\b\u0000\u0010\u001a*\u00020\u00192\u0006\u0010\u001b\u001a\u00028\u00002\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001c2\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001c¢\u0006\u0004\b \u0010!J\u001f\u0010&\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\"H\u0010¢\u0006\u0004\b$\u0010%R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\"\u0010G\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010E\u0012\u0004\u0012\u00020F0D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR \u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR \u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010KR \u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020M0\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010KR \u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020M0\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010KR \u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010KR \u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010KR$\u0010U\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u00060TR\u00020\u00000S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010VR\u001a\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00050W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR\u0018\u0010Z\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0018\u0010^\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010`\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u001a\u0010c\u001a\u00020b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bc\u0010d\u001a\u0004\be\u0010fR\u0016\u0010i\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bg\u0010hR$\u0010o\u001a\u00020\u00162\u0006\u0010j\u001a\u00020\u00168V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n¨\u0006u"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl;", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeature;", "", "", "index", "Lcom/amazon/video/sdk/player/ContentConfig;", "content", "", "updateCurrentContent", "Lcom/amazon/avod/media/TimeSpan;", "delay", "cachePresentation", "clear", "itemIndex", "Lcom/amazon/video/sdk/player/RenderingConfig;", "renderingConfig", PageUriUtils.DO_PLAY_VALUE, "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "onPrepared", "", "isDestroyed", "onTerminate", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent;", "E", "playlistEvent", "", "Lcom/amazon/video/sdk/player/playlist/PlaylistEventListener;", "playlistEventListenerSet", "playlistEventOneTimeListenerSet", "onPlaylistEvent", "(Lcom/amazon/video/sdk/player/playlist/PlaylistEvent;Ljava/util/Set;Ljava/util/Set;)V", "Lcom/amazon/avod/media/playback/VideoPresentationEventListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerStickyVideoPresentationEventListener$AmazonAndroidVideoPlayer_release", "(Lcom/amazon/video/sdk/player/ContentConfig;Lcom/amazon/avod/media/playback/VideoPresentationEventListener;)V", "registerStickyVideoPresentationEventListener", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "sdkConfig", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "Lcom/amazon/video/sdk/player/PlayerConfig;", "Lcom/amazon/video/sdk/content/LocalContentManager;", "localContentManager", "Lcom/amazon/video/sdk/content/LocalContentManager;", "Lcom/amazon/avod/playbackclient/presentation/PresentationCache;", "presentationCache", "Lcom/amazon/avod/playbackclient/presentation/PresentationCache;", "Lcom/amazon/video/sdk/player/PlayerImpl;", "player", "Lcom/amazon/video/sdk/player/PlayerImpl;", "Ljava/util/concurrent/ScheduledExecutorService;", "presentationCacheExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "Lcom/amazon/avod/userdownload/PVDownloadManagerPlayerShim;", "downloadManager", "Lcom/amazon/avod/userdownload/PVDownloadManagerPlayerShim;", "Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "videoSpecUtil", "Lcom/amazon/video/sdk/player/util/VideoSpecUtil;", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "mtaUtils", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "presentationCacheDeferralTime", "Lcom/amazon/avod/media/TimeSpan;", "Lkotlin/Function1;", "Lcom/amazon/avod/media/error/MediaErrorCode;", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "errorFromCode", "Lkotlin/jvm/functions/Function1;", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$PlaylistChange;", "playlistChangeEventListenerSet", "Ljava/util/Set;", "playlistChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$CurrentItemChange;", "currentItemChangeEventListenerSet", "currentItemChangeOneTimeEventListenerSet", "Lcom/amazon/video/sdk/player/playlist/PlaylistEvent$PlaylistError;", "playlistErrorEventListenerSet", "playlistErrorOneTimeEventListenerSet", "Ljava/util/concurrent/ConcurrentMap;", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl$CachedPresentationTracker;", "cachedPresentationToTrackerMap", "Ljava/util/concurrent/ConcurrentMap;", "", "playlistItems", "Ljava/util/List;", "_currentItemIndex", "Ljava/lang/Integer;", "_autoplay", "Z", "currentItem", "Lcom/amazon/video/sdk/player/ContentConfig;", "playlistMutex", "Ljava/lang/Object;", "Lcom/amazon/video/sdk/player/playlist/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/video/sdk/player/playlist/Playlist;", "getPlaylist", "()Lcom/amazon/video/sdk/player/playlist/Playlist;", "getCurrentItemIndex", "()Ljava/lang/Integer;", "currentItemIndex", "value", "getAutoPlay", "()Z", "setAutoPlay", "(Z)V", "autoPlay", "<init>", "(Lcom/amazon/video/sdk/PlayerSdkConfig;Lcom/amazon/video/sdk/player/PlayerConfig;Lcom/amazon/video/sdk/content/LocalContentManager;Lcom/amazon/avod/playbackclient/presentation/PresentationCache;Lcom/amazon/video/sdk/player/PlayerImpl;Ljava/util/concurrent/ScheduledExecutorService;Lcom/amazon/avod/userdownload/PVDownloadManagerPlayerShim;Lcom/amazon/video/sdk/player/util/VideoSpecUtil;Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;Lcom/amazon/avod/media/TimeSpan;Lkotlin/jvm/functions/Function1;)V", "Companion", "CachedPresentationTracker", "PlaylistErrorCode", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class PlaylistFeatureImpl implements PlaylistFeature {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final TimeConfigurationValue presentationCacheDeferralTimeConfig;
    private boolean _autoplay;
    private Integer _currentItemIndex;
    private final ConcurrentMap<ContentConfig, CachedPresentationTracker> cachedPresentationToTrackerMap;
    private final PlayerConfig config;
    private ContentConfig currentItem;
    private final Set<PlaylistEventListener<PlaylistEvent.CurrentItemChange>> currentItemChangeEventListenerSet;
    private final Set<PlaylistEventListener<PlaylistEvent.CurrentItemChange>> currentItemChangeOneTimeEventListenerSet;
    private final PVDownloadManagerPlayerShim downloadManager;
    private final Function1<MediaErrorCode, PlaybackError> errorFromCode;
    private final LocalContentManager localContentManager;
    private final MultiTrackAudioUtils mtaUtils;
    private final PlayerImpl player;
    private final Playlist playlist;
    private final Set<PlaylistEventListener<PlaylistEvent.PlaylistChange>> playlistChangeEventListenerSet;
    private final Set<PlaylistEventListener<PlaylistEvent.PlaylistChange>> playlistChangeOneTimeEventListenerSet;
    private final Set<PlaylistEventListener<PlaylistEvent.PlaylistError>> playlistErrorEventListenerSet;
    private final Set<PlaylistEventListener<PlaylistEvent.PlaylistError>> playlistErrorOneTimeEventListenerSet;
    private final List<ContentConfig> playlistItems;
    private final Object playlistMutex;
    private final PresentationCache presentationCache;
    private final TimeSpan presentationCacheDeferralTime;
    private final ScheduledExecutorService presentationCacheExecutor;
    private final PlayerSdkConfig sdkConfig;
    private final VideoSpecUtil videoSpecUtil;

    /* JADX WARN: Multi-variable type inference failed */
    public PlaylistFeatureImpl(PlayerSdkConfig sdkConfig, PlayerConfig config, LocalContentManager localContentManager, PresentationCache presentationCache, PlayerImpl player, ScheduledExecutorService presentationCacheExecutor, PVDownloadManagerPlayerShim downloadManager, VideoSpecUtil videoSpecUtil, MultiTrackAudioUtils mtaUtils, TimeSpan presentationCacheDeferralTime, Function1<? super MediaErrorCode, ? extends PlaybackError> errorFromCode) {
        PlaylistFeatureConfig playlist;
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(localContentManager, "localContentManager");
        Intrinsics.checkNotNullParameter(presentationCache, "presentationCache");
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(presentationCacheExecutor, "presentationCacheExecutor");
        Intrinsics.checkNotNullParameter(downloadManager, "downloadManager");
        Intrinsics.checkNotNullParameter(videoSpecUtil, "videoSpecUtil");
        Intrinsics.checkNotNullParameter(mtaUtils, "mtaUtils");
        Intrinsics.checkNotNullParameter(presentationCacheDeferralTime, "presentationCacheDeferralTime");
        Intrinsics.checkNotNullParameter(errorFromCode, "errorFromCode");
        this.sdkConfig = sdkConfig;
        this.config = config;
        this.localContentManager = localContentManager;
        this.presentationCache = presentationCache;
        this.player = player;
        this.presentationCacheExecutor = presentationCacheExecutor;
        this.downloadManager = downloadManager;
        this.videoSpecUtil = videoSpecUtil;
        this.mtaUtils = mtaUtils;
        this.presentationCacheDeferralTime = presentationCacheDeferralTime;
        this.errorFromCode = errorFromCode;
        this.playlistChangeEventListenerSet = new LinkedHashSet();
        this.playlistChangeOneTimeEventListenerSet = new LinkedHashSet();
        this.currentItemChangeEventListenerSet = new LinkedHashSet();
        this.currentItemChangeOneTimeEventListenerSet = new LinkedHashSet();
        this.playlistErrorEventListenerSet = new LinkedHashSet();
        this.playlistErrorOneTimeEventListenerSet = new LinkedHashSet();
        ConcurrentMap<ContentConfig, CachedPresentationTracker> concurrentMapNewConcurrentMap = Maps.newConcurrentMap();
        Intrinsics.checkNotNullExpressionValue(concurrentMapNewConcurrentMap, "newConcurrentMap()");
        this.cachedPresentationToTrackerMap = concurrentMapNewConcurrentMap;
        ArrayList arrayList = new ArrayList();
        this.playlistItems = arrayList;
        FeatureConfigs features = config.getFeatures();
        boolean autoPlay = true;
        if (features != null && (playlist = features.getPlaylist()) != null) {
            autoPlay = playlist.getAutoPlay();
        }
        this._autoplay = autoPlay;
        this.playlistMutex = new Object();
        this.playlist = new PlaylistImpl(arrayList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PlaylistFeatureImpl(PlayerSdkConfig playerSdkConfig, final PlayerConfig playerConfig, LocalContentManager localContentManager, PresentationCache presentationCache, PlayerImpl playerImpl, ScheduledExecutorService scheduledExecutorService, PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim, VideoSpecUtil videoSpecUtil, MultiTrackAudioUtils multiTrackAudioUtils, TimeSpan timeSpan, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ScheduledExecutorService scheduledExecutorService2;
        PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim2;
        TimeSpan timeSpan2;
        if ((i & 32) != 0) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor("PlaylistPresentationCaching", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.INFO).build();
            Intrinsics.checkNotNullExpressionValue(scheduledThreadPoolExecutorBuild, "newBuilderFor(\"PlaylistP…el.INFO)\n        .build()");
            scheduledExecutorService2 = scheduledThreadPoolExecutorBuild;
        } else {
            scheduledExecutorService2 = scheduledExecutorService;
        }
        if ((i & 64) != 0) {
            PVDownloadManagerPlayerShim playerDownloadManagerProxy = PVDownloadsPlayerShim.getInstance().getPlayerDownloadManagerProxy();
            Intrinsics.checkNotNullExpressionValue(playerDownloadManagerProxy, "getInstance().playerDownloadManagerProxy");
            pVDownloadManagerPlayerShim2 = playerDownloadManagerProxy;
        } else {
            pVDownloadManagerPlayerShim2 = pVDownloadManagerPlayerShim;
        }
        VideoSpecUtil videoSpecUtil2 = (i & 128) != 0 ? new VideoSpecUtil() : videoSpecUtil;
        MultiTrackAudioUtils multiTrackAudioUtils2 = (i & 256) != 0 ? new MultiTrackAudioUtils() : multiTrackAudioUtils;
        if ((i & 512) != 0) {
            TimeSpan value = presentationCacheDeferralTimeConfig.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "presentationCacheDeferralTimeConfig.value");
            timeSpan2 = value;
        } else {
            timeSpan2 = timeSpan;
        }
        this(playerSdkConfig, playerConfig, localContentManager, presentationCache, playerImpl, scheduledExecutorService2, pVDownloadManagerPlayerShim2, videoSpecUtil2, multiTrackAudioUtils2, timeSpan2, (i & 1024) != 0 ? new Function1<MediaErrorCode, com.amazon.video.sdk.player.error.PlaybackError>() { // from class: com.amazon.video.sdk.player.playlist.PlaylistFeatureImpl.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final com.amazon.video.sdk.player.error.PlaybackError invoke(MediaErrorCode mediaErrorCode) {
                return new com.amazon.video.sdk.player.error.PlaybackError(mediaErrorCode, playerConfig.getContext());
            }
        } : function1);
    }

    /* JADX INFO: compiled from: PlaylistFeatureImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl$Companion;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "()V", "presentationCacheDeferralTimeConfig", "Lcom/amazon/avod/media/framework/config/TimeConfigurationValue;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion extends MediaConfigBase {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TimeConfigurationValue timeConfigurationValueNewTimeConfigurationValue = companion.newTimeConfigurationValue("rothko_playlistPresentationCacheDeferralSeconds", TimeSpan.fromSeconds(60.0d), TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(timeConfigurationValueNewTimeConfigurationValue, "newTimeConfigurationValu…imeUnit.SECONDS\n        )");
        presentationCacheDeferralTimeConfig = timeConfigurationValueNewTimeConfigurationValue;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    /* JADX INFO: renamed from: getCurrentItemIndex, reason: from getter */
    public Integer get_currentItemIndex() {
        return this._currentItemIndex;
    }

    public void clear() {
        SDKPlayerLogger.log("PlaylistFeature.clear()");
        synchronized (this.playlistMutex) {
            if (this.playlistItems.isEmpty()) {
                return;
            }
            if (CollectionsKt___CollectionsKt.contains(this.playlistItems, this.player.getCurrentContent())) {
                this.player.unload();
            }
            ContentConfig contentConfig = this.currentItem;
            this.playlistItems.clear();
            this.currentItem = null;
            this._currentItemIndex = null;
            onPlaylistEvent(new PlaylistEvent.PlaylistChange(getPlaylist()), this.playlistChangeEventListenerSet, this.playlistChangeOneTimeEventListenerSet);
            if (contentConfig != null) {
                onPlaylistEvent(new PlaylistEvent.CurrentItemChange(null, null, contentConfig), this.currentItemChangeEventListenerSet, this.currentItemChangeOneTimeEventListenerSet);
            }
            Iterator<T> it = this.cachedPresentationToTrackerMap.values().iterator();
            while (it.hasNext()) {
                ((CachedPresentationTracker) it.next()).clear(false);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public void play(int itemIndex, RenderingConfig renderingConfig) {
        SDKPlayerLogger.log("PlaylistFeature.play(%s)", Integer.valueOf(itemIndex));
        synchronized (this.playlistMutex) {
            ContentConfig contentConfig = (ContentConfig) CollectionsKt___CollectionsKt.getOrNull(this.playlistItems, itemIndex);
            if (contentConfig == null) {
                onPlaylistEvent(new PlaylistEvent.PlaylistError(null, this.errorFromCode.invoke(PlaylistErrorCode.ATTEMPT_TO_PLAY_ITEM_NOT_IN_LIST)), this.playlistErrorEventListenerSet, this.playlistErrorOneTimeEventListenerSet);
                return;
            }
            PlayerImpl playerImpl = this.player;
            if (renderingConfig == null) {
                renderingConfig = playerImpl.getRenderingConfig();
            }
            playerImpl.start(contentConfig, renderingConfig);
            updateCurrentContent(itemIndex, contentConfig);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: getAutoPlay, reason: from getter */
    public boolean get_autoplay() {
        return this._autoplay;
    }

    public void onPrepared(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        synchronized (this.playlistMutex) {
            ContentConfig currentContent = this.player.getCurrentContent();
            if (currentContent != null) {
                if (currentContent.getVideoType().isLive$AmazonAndroidVideoPlayer_release()) {
                    clear();
                    return;
                }
                if (Intrinsics.areEqual(currentContent, this.currentItem)) {
                    return;
                }
                if (!this.playlistItems.contains(currentContent)) {
                    this.playlistItems.clear();
                    Iterator<T> it = this.cachedPresentationToTrackerMap.values().iterator();
                    while (it.hasNext()) {
                        ((CachedPresentationTracker) it.next()).clear(false);
                    }
                    this.playlistItems.add(currentContent);
                    onPlaylistEvent(new PlaylistEvent.PlaylistChange(getPlaylist()), this.playlistChangeEventListenerSet, this.playlistChangeOneTimeEventListenerSet);
                }
                updateCurrentContent(this.playlistItems.indexOf(currentContent), currentContent);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void updateCurrentContent(int index, ContentConfig content) {
        ContentConfig contentConfig = this.currentItem;
        this.currentItem = content;
        Integer numValueOf = Integer.valueOf(index);
        this._currentItemIndex = numValueOf;
        onPlaylistEvent(new PlaylistEvent.CurrentItemChange(numValueOf, content, contentConfig), this.currentItemChangeEventListenerSet, this.currentItemChangeOneTimeEventListenerSet);
        ContentConfig contentConfig2 = (ContentConfig) CollectionsKt___CollectionsKt.getOrNull(this.playlistItems, index + 1);
        if (contentConfig2 == null) {
            return;
        }
        cachePresentation(contentConfig2, this.presentationCacheDeferralTime);
    }

    public void onTerminate(boolean isDestroyed) {
        synchronized (this.playlistMutex) {
            if (!isDestroyed) {
                Unit unit = Unit.INSTANCE;
            } else {
                Iterator<T> it = this.cachedPresentationToTrackerMap.values().iterator();
                while (it.hasNext()) {
                    ((CachedPresentationTracker) it.next()).clear(isDestroyed);
                }
                Unit unit2 = Unit.INSTANCE;
            }
        }
    }

    public void registerStickyVideoPresentationEventListener$AmazonAndroidVideoPlayer_release(ContentConfig content, VideoPresentationEventListener listener) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(listener, "listener");
        CachedPresentationTracker cachedPresentationTracker = this.cachedPresentationToTrackerMap.get(content);
        if (cachedPresentationTracker == null) {
            return;
        }
        cachedPresentationTracker.registerStickyListener(listener);
    }

    private final void cachePresentation(ContentConfig content, TimeSpan delay) {
        CachedPresentationTracker cachedPresentationTracker = new CachedPresentationTracker(this, content);
        this.cachedPresentationToTrackerMap.put(content, cachedPresentationTracker);
        cachedPresentationTracker.cachePresentation(delay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: PlaylistFeatureImpl.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u0019\u0010\u0016\u001a\u00020\u00102\u000e\u0010\u0017\u001a\n \u0006*\u0004\u0018\u00010\b0\bH\u0096\u0001J\u001c\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001c\u0010\u001c\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u001d\u0010\u001f\u001a\u00020\u00102\b\b\u0001\u0010\u0017\u001a\u00020\b2\b\b\u0001\u0010 \u001a\u00020\u001eH\u0096\u0001J\b\u0010!\u001a\u00020\u0010H\u0016J\u000e\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0001R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl$CachedPresentationTracker;", "Lcom/amazon/avod/media/playback/VideoPresentationEventListener;", "content", "Lcom/amazon/video/sdk/player/ContentConfig;", "(Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl;Lcom/amazon/video/sdk/player/ContentConfig;)V", "bufferedListener", "kotlin.jvm.PlatformType", "cachedPresentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "listenerSet", "Lcom/amazon/avod/media/playback/internal/listener/VideoPresentationEventListenerSet;", "presentationCacheFuture", "Ljava/util/concurrent/ScheduledFuture;", "presentationCacheMutex", "", "cachePresentation", "", "delay", "Lcom/amazon/avod/media/TimeSpan;", "clear", "isDestroyed", "", "onCompletion", "p0", "onError", "presentation", "errorCode", "Lcom/amazon/avod/media/error/MediaErrorCode;", "onPrepared", "dataSource", "Lcom/amazon/avod/media/playback/PlaybackDataSource;", "onStarted", "p1", "onTerminated", "registerStickyListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class CachedPresentationTracker implements VideoPresentationEventListener {
        private final /* synthetic */ AbstractVideoPresentationEventListener $$delegate_0;
        private final VideoPresentationEventListener bufferedListener;
        private VideoPresentation cachedPresentation;
        private final ContentConfig content;
        private final VideoPresentationEventListenerSet listenerSet;
        private ScheduledFuture<?> presentationCacheFuture;
        private final Object presentationCacheMutex;
        final /* synthetic */ PlaylistFeatureImpl this$0;

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onCompletion(VideoPresentation p0) {
            this.$$delegate_0.onCompletion(p0);
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onStarted(@Nonnull VideoPresentation p0, @Nonnull PlaybackDataSource p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            this.$$delegate_0.onStarted(p0, p1);
        }

        public CachedPresentationTracker(PlaylistFeatureImpl this$0, ContentConfig content) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(content, "content");
            this.this$0 = this$0;
            this.content = content;
            this.$$delegate_0 = AbstractVideoPresentationEventListener.INSTANCE;
            this.presentationCacheMutex = new Object();
            this.listenerSet = new VideoPresentationEventListenerSet();
            this.bufferedListener = (VideoPresentationEventListener) RetroactiveEventListenerProxyFactory.createProxy(VideoPresentationEventListener.class);
        }

        public final void cachePresentation(TimeSpan delay) {
            Intrinsics.checkNotNullParameter(delay, "delay");
            Object obj = this.presentationCacheMutex;
            final PlaylistFeatureImpl playlistFeatureImpl = this.this$0;
            synchronized (obj) {
                ImmutableList<String> immutableListGenerateAudioTrackIdsFromStreamMatchers = playlistFeatureImpl.mtaUtils.generateAudioTrackIdsFromStreamMatchers(playlistFeatureImpl.player.getStreamFeature().getAudioStreamPreferences());
                final VideoSpecification videoSpecificationCreateVideoSpec$AmazonAndroidVideoPlayer_release = playlistFeatureImpl.videoSpecUtil.createVideoSpec$AmazonAndroidVideoPlayer_release(this.content, null, immutableListGenerateAudioTrackIdsFromStreamMatchers, playlistFeatureImpl.mtaUtils.getInitialAudioTrackId(this.content, immutableListGenerateAudioTrackIdsFromStreamMatchers), null, playlistFeatureImpl.sdkConfig.getEPrivacyConsentDataProvider().getEPrivacyConsentData());
                final VideoOptions videoOptionsCreateVideoOptions$AmazonAndroidVideoPlayer_release = playlistFeatureImpl.videoSpecUtil.createVideoOptions$AmazonAndroidVideoPlayer_release(this.content);
                final Optional<PVDownloadPlayerShim> offlineDownloadIfPresent = playlistFeatureImpl.downloadManager.getOfflineDownloadIfPresent(this.content.getTitleId(), this.content.getSessionContext());
                Intrinsics.checkNotNullExpressionValue(offlineDownloadIfPresent, "downloadManager.getOffli…, content.sessionContext)");
                this.listenerSet.addListener(this.bufferedListener);
                this.listenerSet.addListener(this);
                this.presentationCacheFuture = playlistFeatureImpl.presentationCacheExecutor.schedule(new Runnable() { // from class: com.amazon.video.sdk.player.playlist.PlaylistFeatureImpl$CachedPresentationTracker$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlaylistFeatureImpl.CachedPresentationTracker.m2489cachePresentation$lambda1$lambda0(playlistFeatureImpl, videoSpecificationCreateVideoSpec$AmazonAndroidVideoPlayer_release, videoOptionsCreateVideoOptions$AmazonAndroidVideoPlayer_release, offlineDownloadIfPresent, this);
                    }
                }, delay.getTotalMilliseconds(), TimeUnit.MILLISECONDS);
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: cachePresentation$lambda-1$lambda-0, reason: not valid java name */
        public static final void m2489cachePresentation$lambda1$lambda0(PlaylistFeatureImpl this$0, VideoSpecification videoSpecification, VideoOptions videoOptions, Optional preexistingDownload, CachedPresentationTracker this$1) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(videoSpecification, "$videoSpecification");
            Intrinsics.checkNotNullParameter(videoOptions, "$videoOptions");
            Intrinsics.checkNotNullParameter(preexistingDownload, "$preexistingDownload");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            PresentationCache presentationCache = this$0.presentationCache;
            PVDownloadPlayerShim pVDownloadPlayerShim = (PVDownloadPlayerShim) preexistingDownload.orNull();
            presentationCache.preparePresentationAsync(videoSpecification, videoOptions, pVDownloadPlayerShim == null ? null : pVDownloadPlayerShim.getStoragePath(), this$1.listenerSet);
        }

        public final void registerStickyListener(VideoPresentationEventListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            synchronized (this.presentationCacheMutex) {
                EventListenerProxy.addListener(this.bufferedListener, listener);
                EventListenerProxy.startDispatchingEvents(this.bufferedListener);
                this.listenerSet.removeListener(this);
                this.cachedPresentation = null;
                clear(false);
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onError(VideoPresentation presentation, MediaErrorCode errorCode) {
            Object obj = this.presentationCacheMutex;
            PlaylistFeatureImpl playlistFeatureImpl = this.this$0;
            synchronized (obj) {
                playlistFeatureImpl.onPlaylistEvent(new PlaylistEvent.PlaylistError(this.content, (PlaybackError) playlistFeatureImpl.errorFromCode.invoke(errorCode)), playlistFeatureImpl.playlistErrorEventListenerSet, playlistFeatureImpl.playlistErrorOneTimeEventListenerSet);
                clear(false);
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onPrepared(VideoPresentation presentation, PlaybackDataSource dataSource) {
            synchronized (this.presentationCacheMutex) {
                this.cachedPresentation = presentation;
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
        public void onTerminated() {
            clear(false);
        }

        public final void clear(boolean isDestroyed) {
            VideoSpecification specification;
            Object obj = this.presentationCacheMutex;
            PlaylistFeatureImpl playlistFeatureImpl = this.this$0;
            synchronized (obj) {
                ScheduledFuture<?> scheduledFuture = this.presentationCacheFuture;
                if (scheduledFuture != null) {
                    if (!scheduledFuture.isDone()) {
                        scheduledFuture.cancel(false);
                    }
                    this.presentationCacheFuture = null;
                }
                VideoPresentation videoPresentation = this.cachedPresentation;
                if (videoPresentation != null && (specification = videoPresentation.getSpecification()) != null) {
                    playlistFeatureImpl.presentationCache.destroyPresentationAsync(specification, isDestroyed ? false : true);
                }
                this.cachedPresentation = null;
            }
        }
    }

    /* JADX INFO: compiled from: PlaylistFeatureImpl.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl$PlaylistErrorCode;", "", "Lcom/amazon/avod/media/error/MediaErrorCode;", "numberCode", "", "(Ljava/lang/String;II)V", "getName", "", "getNumber", "toReportableString", "ATTEMPT_TO_ADD_LIVE_TITLE", "ATTEMPT_TO_REMOVE_ITEM_NOT_IN_LIST", "ATTEMPT_TO_REMOVE_CURRENTLY_PLAYING_ITEM", "ATTEMPT_TO_PLAY_ITEM_NOT_IN_LIST", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum PlaylistErrorCode implements MediaErrorCode {
        ATTEMPT_TO_ADD_LIVE_TITLE(6001),
        ATTEMPT_TO_REMOVE_ITEM_NOT_IN_LIST(6002),
        ATTEMPT_TO_REMOVE_CURRENTLY_PLAYING_ITEM(6003),
        ATTEMPT_TO_PLAY_ITEM_NOT_IN_LIST(6004);

        private final int numberCode;

        PlaylistErrorCode(int i) {
            this.numberCode = i;
        }

        @Override // com.amazon.avod.media.error.MediaErrorCode
        public String getName() {
            return name();
        }

        @Override // com.amazon.avod.media.error.MediaErrorCode
        /* JADX INFO: renamed from: getNumber, reason: from getter */
        public int getNumberCode() {
            return this.numberCode;
        }

        @Override // com.amazon.avod.media.error.MediaErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
        public String toReportableString() {
            return name();
        }
    }

    public final <E extends PlaylistEvent> void onPlaylistEvent(E playlistEvent, Set<PlaylistEventListener<E>> playlistEventListenerSet, Set<PlaylistEventListener<E>> playlistEventOneTimeListenerSet) {
        Intrinsics.checkNotNullParameter(playlistEvent, "playlistEvent");
        Intrinsics.checkNotNullParameter(playlistEventListenerSet, "playlistEventListenerSet");
        Intrinsics.checkNotNullParameter(playlistEventOneTimeListenerSet, "playlistEventOneTimeListenerSet");
        Iterator<T> it = playlistEventListenerSet.iterator();
        while (it.hasNext()) {
            ((PlaylistEventListener) it.next()).on(playlistEvent);
        }
        Iterator<T> it2 = playlistEventOneTimeListenerSet.iterator();
        while (it2.hasNext()) {
            ((PlaylistEventListener) it2.next()).on(playlistEvent);
        }
        playlistEventOneTimeListenerSet.clear();
    }
}
