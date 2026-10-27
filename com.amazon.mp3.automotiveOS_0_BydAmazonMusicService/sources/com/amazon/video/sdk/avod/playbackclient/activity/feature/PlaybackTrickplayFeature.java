package com.amazon.video.sdk.avod.playbackclient.activity.feature;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.connectivity.NetworkType;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.image.TrickplayImage;
import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.content.image.TrickplayManifest;
import com.amazon.avod.content.smoothstream.ImageDownloader;
import com.amazon.avod.core.constants.UrlType;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutor;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorFactory;
import com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorListener;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener;
import com.amazon.avod.playbackclient.reporting.MetricEventReporter;
import com.amazon.avod.playbackclient.trickplay.TrickplayConfig;
import com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse;
import com.amazon.avod.playbackclient.trickplay.internal.TrickplayEventDataBuilder;
import com.amazon.avod.playbackclient.trickplay.internal.TrickplayEventReporter;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatch;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatchFactory;
import com.amazon.video.sdk.avod.playbackclient.control.states.PlaybackControllerContext;
import com.amazon.video.sdk.avod.playbackclient.trickplay.TrickplayController;
import com.amazon.video.sdk.avod.playbackclient.trickplay.TrickplayImageLoader;
import com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPlugin;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class PlaybackTrickplayFeature {
    private PlaybackMediaEventReporters mAloysiusReporters;
    private final NetworkConnectionManager mConnectionManager;
    private ContentFetcherPluginContext mContentFetcherPluginContent;
    private ContentFetcherPluginExecutor mContentFetcherPluginExecutor;
    private Context mContext;
    private final DownloadService mDownloadService;
    private final TrickplayEventReporter mEventReporter;
    private boolean mHasPlaybackStarted;
    private AtomicBoolean mIsFeatureDestroyed;
    private boolean mIsImageDownloadEnabled;
    private PlaybackControllerContext mPlaybackControllerContext;
    private PlaybackEventDispatch mPlaybackEventDispatch;
    private final PlaybackStateEventListener mPlaybackStartListener;
    private MetricEventReporter mQosReporter;
    private TrickplaySeekThumbHandler mSeekThumbHandler;
    private final TrickplayConfig mTrickplayConfig;
    private TrickplayController mTrickplayController;
    private final Stopwatch mTrickplayFetchTimer;
    private TrickplayImageLoader.ImageLoaderListener mTrickplayImageLoaderListener;
    private TrickplayIndex mTrickplayIndex;
    private TrickplayPlugin mTrickplayPlugin;
    private VideoPlayer mVideoPlayer;

    public PlaybackTrickplayFeature(@Nonnull Context context) {
        this(context, TrickplayConfig.getInstance(), NetworkConnectionManager.getInstance(), new TrickplayEventReporter(new TrickplayEventDataBuilder()));
    }

    PlaybackTrickplayFeature(@Nonnull Context context, @Nonnull TrickplayConfig trickplayConfig, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull TrickplayEventReporter trickplayEventReporter) {
        this.mTrickplayFetchTimer = Stopwatch.createUnstarted(Tickers.androidTicker());
        this.mIsFeatureDestroyed = new AtomicBoolean();
        this.mPlaybackStartListener = new BasePlaybackStateEventListener() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackTrickplayFeature.1
            @Override // com.amazon.avod.playbackclient.listeners.BasePlaybackStateEventListener, com.amazon.avod.playback.PlaybackStateEventListener
            public void onStart(PlaybackEventContext playbackEventContext) {
                PlaybackTrickplayFeature.this.mHasPlaybackStarted = true;
                PlaybackTrickplayFeature.this.onPlaybackStarting();
            }
        };
        this.mContext = context;
        this.mConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "connectionManager");
        this.mTrickplayConfig = (TrickplayConfig) Preconditions.checkNotNull(trickplayConfig, "trickplayConfig");
        this.mEventReporter = (TrickplayEventReporter) Preconditions.checkNotNull(trickplayEventReporter, "eventReporter");
        this.mDownloadService = MediaSystem.getInstance().getDownloadService();
    }

    public void initialize(@Nullable TrickplayImageLoader.ImageLoaderListener imageLoaderListener) {
        this.mTrickplayImageLoaderListener = imageLoaderListener;
    }

    public void prepareForPlayback(@Nonnull final VideoPresentation videoPresentation) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackTrickplayFeature$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$prepareForPlayback$0(videoPresentation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareForPlayback$0(VideoPresentation videoPresentation) {
        if (this.mIsFeatureDestroyed.get()) {
            return;
        }
        this.mQosReporter = new MetricEventReporter.Factory().createMetricEventReporter(videoPresentation.getReporter());
        VideoSpecification specification = videoPresentation.getSpecification();
        this.mContentFetcherPluginContent = new ContentFetcherPluginContext(specification, new File(StorageHelper.getInstance().getGeneralFileDir(), String.format("streaming-plugins/%s", specification.getTitleId())), videoPresentation.getReporter(), ContentFetcherPluginContext.PluginSessionType.PLAYBACK, true, getLiveTrickplayManifest(videoPresentation), videoPresentation.getVideoOptions().getSessionContext(), null);
        TrickplayPlugin trickplayPlugin = new TrickplayPlugin.PluginProvider(this.mContext, this.mDownloadService).get();
        this.mTrickplayPlugin = trickplayPlugin;
        trickplayPlugin.init(this.mContentFetcherPluginContent);
        VideoPlayer player = videoPresentation.getPlayer();
        this.mVideoPlayer = player;
        PlaybackExperienceController playbackExperienceController = player.getPlaybackExperienceController();
        this.mAloysiusReporters = playbackExperienceController == null ? null : playbackExperienceController.getAloysiusReporter();
        this.mPlaybackControllerContext = new PlaybackControllerContext("Main2");
        PlaybackEventDispatch playbackEventDispatchCreateAndAttachDispatch = new PlaybackEventDispatchFactory().createAndAttachDispatch(this.mVideoPlayer, this.mPlaybackControllerContext);
        this.mPlaybackEventDispatch = playbackEventDispatchCreateAndAttachDispatch;
        playbackEventDispatchCreateAndAttachDispatch.initialize();
        this.mPlaybackEventDispatch.addPlaybackStateEventListener(this.mPlaybackStartListener);
        registerForTrickplayDataIfNeeded();
    }

    @Nullable
    private TrickplayManifest getLiveTrickplayManifest(@Nonnull VideoPresentation videoPresentation) {
        ImageDownloader imageDownloader;
        ContentSession contentSession = videoPresentation.getPlayer().getContentSession();
        if (contentSession == null || !contentSession.isActive() || (imageDownloader = contentSession.getProtocol().getImageDownloader()) == null) {
            return null;
        }
        return imageDownloader.getTrickplayManifest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlaybackStarting() {
        if (this.mIsFeatureDestroyed.get()) {
            return;
        }
        this.mEventReporter.bindEventReporter(this.mQosReporter);
        PlaybackMediaEventReporters playbackMediaEventReporters = this.mAloysiusReporters;
        if (playbackMediaEventReporters != null) {
            this.mEventReporter.bindAloysiusReporters(playbackMediaEventReporters);
        }
        TrickplaySeekThumbHandler trickplaySeekThumbHandler = new TrickplaySeekThumbHandler();
        this.mSeekThumbHandler = trickplaySeekThumbHandler;
        TrickplayController trickplayController = new TrickplayController(this.mTrickplayImageLoaderListener, this.mVideoPlayer, this.mPlaybackEventDispatch, trickplaySeekThumbHandler);
        this.mTrickplayController = trickplayController;
        trickplayController.enable();
        if (isImageDownloadEnabled()) {
            DLog.devf("Starting timer for Trickplay data fetching.");
            this.mTrickplayFetchTimer.reset();
            this.mTrickplayFetchTimer.start();
        }
        ContentFetcherPluginExecutor contentFetcherPluginExecutorNewPluginExecutor = new ContentFetcherPluginExecutorFactory().newPluginExecutor(this.mContentFetcherPluginContent, ImmutableSet.of(this.mTrickplayPlugin));
        this.mContentFetcherPluginExecutor = contentFetcherPluginExecutorNewPluginExecutor;
        contentFetcherPluginExecutorNewPluginExecutor.addPluginListener(new AnonymousClass2());
        this.mContentFetcherPluginExecutor.start();
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackTrickplayFeature$2, reason: invalid class name */
    class AnonymousClass2 implements ContentFetcherPluginExecutorListener {
        AnonymousClass2() {
        }

        @Override // com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorListener
        public void onDownloadStart(@Nonnull ContentFetcherPlugin contentFetcherPlugin) {
            DLog.devf("onDownloadStart plugin: %s", contentFetcherPlugin);
        }

        @Override // com.amazon.avod.media.download.plugin.ContentFetcherPluginExecutorListener
        public void onDownloadComplete(@Nonnull ContentFetcherPlugin contentFetcherPlugin, @Nonnull TimeSpan timeSpan) {
            DLog.errorf("onDownloadComplete plugin: %s timespan: %s ", contentFetcherPlugin, timeSpan);
            if (PlaybackTrickplayFeature.this.mIsFeatureDestroyed.get()) {
                return;
            }
            TimeSpan timeSpan2 = new TimeSpan(PlaybackTrickplayFeature.this.mTrickplayFetchTimer.isRunning() ? PlaybackTrickplayFeature.this.mTrickplayFetchTimer.stop() : PlaybackTrickplayFeature.this.mTrickplayFetchTimer);
            TrickplayLoadingResponse result = ((TrickplayPlugin) contentFetcherPlugin).getResult();
            Optional<TrickplayIndex> trickplayIndex = result.getTrickplayIndex();
            if (trickplayIndex.isPresent()) {
                PlaybackTrickplayFeature.this.mTrickplayIndex = trickplayIndex.get();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackTrickplayFeature$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onDownloadComplete$0();
                    }
                });
            } else {
                DLog.logf("No trickplay data; images will not be shown");
                PlaybackTrickplayFeature.this.mEventReporter.reportTrickplayUnavailable(result, timeSpan2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onDownloadComplete$0() {
            if (PlaybackTrickplayFeature.this.mTrickplayController != null) {
                PlaybackTrickplayFeature.this.mTrickplayController.registerForImageUpdates(PlaybackTrickplayFeature.this.mTrickplayIndex);
            }
        }
    }

    public void requestTrickplay(long j) {
        TrickplaySeekThumbHandler trickplaySeekThumbHandler;
        if (this.mIsFeatureDestroyed.get() || this.mVideoPlayer == null || (trickplaySeekThumbHandler = this.mSeekThumbHandler) == null) {
            return;
        }
        trickplaySeekThumbHandler.updateSeekTimecode(j);
        this.mPlaybackControllerContext.setThumbPosition(this.mSeekThumbHandler.getThumbPositionForScrubbing());
        this.mPlaybackControllerContext.notifyProgressChanged();
    }

    private void registerForTrickplayDataIfNeeded() {
        if (!UrlType.isContent(UrlType.CONTENT)) {
            DLog.logf("Trickplay is not applicable to trailers.");
            return;
        }
        if (!this.mTrickplayConfig.isTrickplayEnabled()) {
            DLog.logf("Trickplay is disabled for all titles from the server.");
        } else if (!this.mTrickplayConfig.isTrickplayEnabledOverWAN() && this.mConnectionManager.getNetworkType() == NetworkType.WAN) {
            DLog.logf("Trickplay is disabled during WAN streaming from the server.");
        } else {
            this.mIsImageDownloadEnabled = true;
        }
    }

    public void reset() {
        if (hasPlaybackStarted()) {
            this.mTrickplayController.disable();
            this.mTrickplayController = null;
        }
        PlaybackEventDispatch playbackEventDispatch = this.mPlaybackEventDispatch;
        if (playbackEventDispatch != null) {
            playbackEventDispatch.removePlaybackStateEventListener(this.mPlaybackStartListener);
        }
        this.mHasPlaybackStarted = false;
        this.mIsImageDownloadEnabled = false;
        TrickplayPlugin trickplayPlugin = this.mTrickplayPlugin;
        if (trickplayPlugin != null) {
            trickplayPlugin.clear();
        }
        this.mVideoPlayer = null;
    }

    public void destroy() {
        this.mIsFeatureDestroyed.set(true);
    }

    private boolean isImageDownloadEnabled() {
        return this.mIsImageDownloadEnabled;
    }

    private boolean hasPlaybackStarted() {
        return this.mHasPlaybackStarted;
    }

    public static class TrickplaySeekThumbHandler {
        private long mLastSeekTimecode = -1;
        private TrickplayIndex mTrickplayIndex;

        public void initialize(@Nonnull TrickplayIndex trickplayIndex) {
            this.mTrickplayIndex = (TrickplayIndex) Preconditions.checkNotNull(trickplayIndex, "Must supply a way to navigate the trickplay manifest.");
        }

        public long getThumbPositionForScrubbing() {
            return this.mLastSeekTimecode;
        }

        public void updateSeekTimecode(long j) {
            TrickplayIndex trickplayIndex = this.mTrickplayIndex;
            TrickplayImage trickplayImageFindClosestImage = trickplayIndex != null ? trickplayIndex.findClosestImage(j) : null;
            if (trickplayImageFindClosestImage != null) {
                j = trickplayImageFindClosestImage.getTimecodeMillis();
            }
            this.mLastSeekTimecode = j;
        }
    }
}
