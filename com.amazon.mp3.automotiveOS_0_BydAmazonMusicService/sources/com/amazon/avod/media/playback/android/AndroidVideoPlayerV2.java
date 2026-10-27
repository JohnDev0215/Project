package com.amazon.avod.media.playback.android;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache;
import com.amazon.avod.media.ads.internal.CacheStatus;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.framework.uriproxy.EnhancedURI;
import com.amazon.avod.media.framework.util.UrlUtils;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.SurfaceHandlingMode;
import com.amazon.avod.media.playback.VideoPlayerBase;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.media.playback.util.VideoRegion;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.subtitles.SubtitlesListener;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter$EventListener;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.CharMatcher;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidVideoPlayerV2 extends VideoPlayerBase {
    private final AndroidVideoPlayerV2Cache mAndroidVideoPlayerV2Cache;
    private final AndroidVideoPlayerV2Config mConfig;
    private final Context mContext;
    private final DeviceConfiguration mDeviceConfiguration;
    private final ExoPlayerStateListener mExoPlayerStateListener;
    private final PlaybackExperienceController mExperienceController;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private PlaybackDataSource mInitialDataSource;
    private final boolean mIsAndroidVideoPlayerV2CacheEnabled;
    private final boolean mIsDynamicVideoSurfaceResizeEnabled;
    private volatile boolean mIsPrepared;
    private volatile boolean mIsTerminated;
    private LayoutChangeListener mLayoutChangeListener;
    private ViewGroup mParentViewGroup;
    private final SimpleExoPlayer mPlayer;
    private final QOSCommunicationService mQOSCommunicationService;
    private VideoRegion mRegion;
    private final AndroidVideoSurface mSurface;
    protected final AndroidVideoSurface.CreationListener mSurfaceCreationListener;
    private VideoConfig mVideoConfig;
    private volatile VideoSpecification mVideoSpecification;
    private final ZoomCalculator mZoomCalculator;
    private PlaybackZoomLevel mZoomLevel;

    public AndroidVideoPlayerV2(@Nonnull Context context, @Nonnull SimpleExoPlayer simpleExoPlayer, @Nonnull HandlerThread handlerThread, @Nonnull Handler handler, @Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull DeviceConfiguration deviceConfiguration, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nullable QOSCommunicationService qOSCommunicationService, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache, @Nonnull AndroidVideoPlayerV2Config androidVideoPlayerV2Config) {
        super(playbackListenerProxy);
        this.mZoomLevel = PlaybackZoomLevel.NATIVE;
        this.mSurfaceCreationListener = new AndroidVideoSurface.CreationListener() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayerV2.1
            @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
            public void onCreated(SurfaceHolder surfaceHolder) {
                AndroidVideoPlayerV2.this.mPlayer.setVideoSurface(surfaceHolder.getSurface());
            }

            @Override // com.amazon.avod.media.playback.android.AndroidVideoSurface.CreationListener
            public void onDestroyed(SurfaceHolder surfaceHolder) {
                AndroidVideoPlayerV2.this.mPlayer.clearVideoSurface(surfaceHolder.getSurface());
            }
        };
        this.mExperienceController = new PlaybackExperienceController() { // from class: com.amazon.avod.media.playback.android.AndroidVideoPlayerV2.2
            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void deregisterSubtitleListener(@Nonnull SubtitlesListener subtitlesListener) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nullable
            public PlaybackMediaEventReporters getAloysiusReporter() {
                return null;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public boolean isStreamingSubtitlesSupported() {
                return false;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public boolean isVideoTrackRecreationSupported() {
                return true;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void onSubtitlesCallbackComplete() {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void registerSubtitleListener(@Nonnull SubtitlesListener subtitlesListener) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setFailoverZigZagSpeed(int i) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void startSubtitleDownload(String str) {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void stopSubtitleDownload() {
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public Optional<String> getAudioTrackId() {
                DLog.warnf("getAudioTrackId() is unimplemented in AndroidVideoPlayerV2.");
                return Optional.absent();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setRestrictPlaybackToBufferedContent(boolean z) {
                DLog.warnf("setRestrictPlaybackToBufferedContent() is unimplemented in AndroidVideoPlayerV2.");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setZoomLevel(@Nonnull PlaybackZoomLevel playbackZoomLevel) {
                AndroidVideoPlayerV2.this.mZoomLevel = (PlaybackZoomLevel) Preconditions.checkNotNull(playbackZoomLevel, "zoomLevel");
                AndroidVideoPlayerV2.this.applyVideoDimensions();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public PlaybackZoomLevel getZoomLevel() {
                return AndroidVideoPlayerV2.this.mZoomLevel;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setPlaybackSpeed(float f) {
                DLog.warnf("setPlaybackSpeed not implemented in AndroidVideoPlayerV2");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void scaleVolume(float f) {
                float fMax = Math.max(0.0f, Math.min(1.0f, f));
                DLog.logf("Scaling volume to %s", Float.valueOf(fMax));
                AndroidVideoPlayerV2.this.mPlayer.setVolume(fMax);
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public Set<String> getAvailableSubtitleLanguageCodes() {
                return Collections.emptySet();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public Set<String> getAvailableAudioLanguages() {
                return Collections.EMPTY_SET;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public Optional<String> getCurrentAudioLanguage() {
                return Optional.absent();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public long getLiveTimeWindowStartMillis() {
                throw new UnsupportedOperationException("getLiveTimeWindowStartMillis() not supported");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public long getLiveTimeWindowEndMillis() {
                throw new UnsupportedOperationException("getLiveTimeWindowEndMillis() not supported");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() throws IllegalPlayerStateException {
                return ImmutableList.of();
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            @Nonnull
            public VideoConfig getVideoConfig() throws IllegalPlayerStateException {
                Preconditions.checkState(AndroidVideoPlayerV2.this.mVideoConfig != null, "This method must be called after preparation");
                return AndroidVideoPlayerV2.this.mVideoConfig;
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void setVideoPlayerInBackground(boolean z) {
                throw new UnsupportedOperationException("setVideoPlayerInBackground() is not supported on the AndroidVideoPlayerV2. do so through the AdPlaybackStateMachine");
            }

            @Override // com.amazon.avod.playback.PlaybackExperienceController
            public void changeOutputDisplay(@Nonnull VideoRenderingSettings videoRenderingSettings) throws PlaybackException {
                Preconditions2.checkNotMainThread();
                AndroidVideoPlayerV2.this.mPlayer.clearVideoSurface();
                AndroidVideoPlayerV2.this.setRenderingSettings(videoRenderingSettings);
            }
        };
        Context context2 = (Context) Preconditions.checkNotNull(context, "context");
        this.mContext = context2;
        SimpleExoPlayer simpleExoPlayer2 = (SimpleExoPlayer) Preconditions.checkNotNull(simpleExoPlayer, "player");
        this.mPlayer = simpleExoPlayer2;
        this.mHandlerThread = (HandlerThread) Preconditions.checkNotNull(handlerThread, "handlerThread");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "handler");
        DeviceConfiguration deviceConfiguration2 = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration, "deviceConfiguration");
        this.mDeviceConfiguration = deviceConfiguration2;
        this.mQOSCommunicationService = qOSCommunicationService;
        this.mAndroidVideoPlayerV2Cache = (AndroidVideoPlayerV2Cache) Preconditions.checkNotNull(androidVideoPlayerV2Cache, "exoPlayerCache");
        AndroidVideoPlayerV2Config androidVideoPlayerV2Config2 = (AndroidVideoPlayerV2Config) Preconditions.checkNotNull(androidVideoPlayerV2Config, "androidVideoPlayerV2Config");
        this.mConfig = androidVideoPlayerV2Config2;
        this.mIsAndroidVideoPlayerV2CacheEnabled = androidVideoPlayerV2Config2.isAndroidVideoPlayerV2CacheEnabled();
        ExoPlayerStateListener exoPlayerStateListener = new ExoPlayerStateListener();
        this.mExoPlayerStateListener = exoPlayerStateListener;
        simpleExoPlayer2.addListener(exoPlayerStateListener);
        simpleExoPlayer2.addVideoListener(exoPlayerStateListener);
        this.mSurface = new AndroidVideoSurface(context2);
        ZoomCalculator zoomCalculator = new ZoomCalculator(deviceConfiguration2);
        this.mZoomCalculator = zoomCalculator;
        this.mIsDynamicVideoSurfaceResizeEnabled = ((MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration")).isDynamicVideoSurfaceResizeEnabled();
        this.mRegion = zoomCalculator.getVideoRegionForScreenSize();
        VideoResolution.ResolutionBand resolutionBand = VideoResolution.ResolutionBand.HD_1080P;
        VideoResolution videoResolution = new VideoResolution(resolutionBand.getMinWidth(), resolutionBand.getMinHeight(), ((double) resolutionBand.getMinWidth()) / ((double) resolutionBand.getMinHeight()));
        this.mVideoConfig = new VideoConfig("unkn", videoResolution, videoResolution, null, false, false, videoResolution.getAspectRatio());
    }

    public class ExoPlayerStateListener implements Player.EventListener, BandwidthMeter$EventListener, SimpleExoPlayer.VideoListener, AdaptiveMediaSourceEventListener {
        private PlaybackBufferEventType mBufferEventType;
        private boolean mHasFinishedInitialLoading;
        private boolean mHasInitialPlaybackStarted;
        private volatile boolean mIsSeeking;
        private final PlaybackListenerProxy mPlaybackListenerProxy;

        @Override // com.google.android.exoplayer2.upstream.BandwidthMeter$EventListener
        public void onBandwidthSample(int i, long j, long j2) {
        }

        @Override // com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener
        public void onDownstreamFormatChanged(int i, Format format, int i2, Object obj, long j) {
        }

        @Override // com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener
        public void onLoadCanceled(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        }

        @Override // com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener
        public void onLoadCompleted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        }

        @Override // com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener
        public void onLoadError(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
        }

        @Override // com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener
        public void onLoadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onLoadingChanged(boolean z) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity() {
        }

        @Override // com.google.android.exoplayer2.SimpleExoPlayer.VideoListener
        public void onRenderedFirstFrame() {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onRepeatModeChanged(int i) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onTimelineChanged(Timeline timeline, Object obj) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        }

        public ExoPlayerStateListener() {
            this.mPlaybackListenerProxy = AndroidVideoPlayerV2.this.getEventProxy();
        }

        public void seekTo(@Nonnegative long j) {
            this.mIsSeeking = true;
            this.mPlaybackListenerProxy.onSeekStart(TimeSpan.fromMilliseconds(j), AndroidVideoPlayerV2.this.createPlaybackEventContext());
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
            if (i == 2) {
                handleBuffering();
            } else if (i == 3) {
                handleReady(z);
            } else {
                if (i != 4) {
                    return;
                }
                handleEnded();
            }
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            if (AndroidVideoPlayerV2.this.mQOSCommunicationService != null) {
                AndroidVideoPlayerV2.this.mQOSCommunicationService.getEventTransport().postEvent(new RetriablePlaybackErrorEvent(TimeSpan.fromMilliseconds(AndroidVideoPlayerV2.this.getCurrentPosition()), new ContentException(ContentException.ContentError.UNKNOWN_ERROR, String.format(Locale.US, "onPlayerError: %s", exoPlaybackException))));
            }
            AndroidVideoPlayerV2.this.notifyError(StandardErrorCode.ANDROID_MEDIAPLAYER_ERROR);
        }

        @Override // com.google.android.exoplayer2.SimpleExoPlayer.VideoListener
        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            double d = i2 > 0 ? ((double) i) / ((double) i2) : -1.0d;
            VideoResolution videoResolution = new VideoResolution(i, i2, d);
            AndroidVideoPlayerV2.this.mVideoConfig = new VideoConfig(getFourCC(), videoResolution, videoResolution, null, false, false, d);
            AndroidVideoPlayerV2.this.applyVideoDimensions();
        }

        @Nonnull
        private String getFourCC() {
            String str = AndroidVideoPlayerV2.this.mPlayer.getVideoFormat().containerMimeType;
            return (str != null && str.length() == 4 && CharMatcher.ascii().matchesAllOf(str)) ? str : "unkn";
        }

        private void handleBuffering() {
            if (!this.mHasFinishedInitialLoading) {
                this.mBufferEventType = PlaybackBufferEventType.INITIAL_LOADING;
            } else if (this.mIsSeeking) {
                this.mBufferEventType = PlaybackBufferEventType.SEEK;
            } else {
                this.mBufferEventType = PlaybackBufferEventType.UNEXPECTED;
            }
            this.mPlaybackListenerProxy.onAdPlaybackPaused();
            this.mPlaybackListenerProxy.sendBufferStartEvent(this.mBufferEventType, AndroidVideoPlayerV2.this.createPlaybackEventContext(), null);
        }

        private void handleReady(boolean z) {
            PlaybackEventContext playbackEventContextCreatePlaybackEventContext = AndroidVideoPlayerV2.this.createPlaybackEventContext();
            if (!this.mHasFinishedInitialLoading) {
                this.mHasFinishedInitialLoading = true;
                this.mPlaybackListenerProxy.onAdPlaybackLoaded();
                this.mPlaybackListenerProxy.onContentDownloaded();
                AndroidVideoPlayerV2 androidVideoPlayerV2 = AndroidVideoPlayerV2.this;
                androidVideoPlayerV2.notifyPrepared(androidVideoPlayerV2.mInitialDataSource);
            }
            PlaybackBufferEventType playbackBufferEventType = this.mBufferEventType;
            if (playbackBufferEventType != null) {
                this.mPlaybackListenerProxy.sendBufferEndEvent(playbackBufferEventType, playbackEventContextCreatePlaybackEventContext);
                this.mBufferEventType = null;
            }
            if (this.mIsSeeking) {
                this.mPlaybackListenerProxy.onSeekEnd(playbackEventContextCreatePlaybackEventContext);
                this.mIsSeeking = false;
            }
            if (this.mHasInitialPlaybackStarted) {
                if (z) {
                    this.mPlaybackListenerProxy.onAdPlaybackPlaying();
                    this.mPlaybackListenerProxy.onResume(playbackEventContextCreatePlaybackEventContext);
                    return;
                }
                return;
            }
            if (z) {
                this.mPlaybackListenerProxy.onAdPlaybackPlaying();
                this.mPlaybackListenerProxy.onStart(playbackEventContextCreatePlaybackEventContext);
                this.mPlaybackListenerProxy.onStarted(AndroidVideoPlayerV2.this.mInitialDataSource);
                this.mHasInitialPlaybackStarted = true;
            }
        }

        private void handleEnded() {
            this.mPlaybackListenerProxy.onAdPlaybackCompleted();
            AndroidVideoPlayerV2.this.notifyCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    public PlaybackEventContext createPlaybackEventContext() {
        return new PlaybackEventContext(this.mPlayer.getCurrentPosition());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(@Nonnull VideoSpecification videoSpecification, @Nullable File file) {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring PrepareAsync call after Terminate for %s", this);
            return;
        }
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "spec");
        DLog.logf("Prepare Async %s", this);
        String url = videoSpecification.getUrl();
        String adIdentifier = videoSpecification.getAdIdentifier();
        Preconditions.checkArgument(!url.isEmpty(), "url string should not be empty");
        Uri uri = Uri.parse(url);
        this.mPlayer.prepare(buildMediaSource(uri));
        this.mIsPrepared = true;
        if (this.mIsAndroidVideoPlayerV2CacheEnabled) {
            try {
                CacheStatus cacheStatus = this.mAndroidVideoPlayerV2Cache.getCacheStatus(new EnhancedURI(new URI(url), adIdentifier));
                if (cacheStatus.isFullyCached()) {
                    this.mInitialDataSource = PlaybackDataSource.DOWNLOADED;
                } else if (cacheStatus.isUncached()) {
                    this.mInitialDataSource = PlaybackDataSource.STREAMING;
                } else {
                    this.mInitialDataSource = PlaybackDataSource.PARTIALLY_CACHED;
                }
                DLog.logf("AndroidVideoPlayerV2 prepareAsync called for url: %s cacheStatus: %s", url, cacheStatus);
                return;
            } catch (URISyntaxException e) {
                DLog.warnf("Failed to get cache status for url: %s, adIdentifier: %s, error: %s", url, adIdentifier, e);
                this.mInitialDataSource = PlaybackDataSource.STREAMING;
                return;
            }
        }
        if (UrlUtils.isLocal(uri)) {
            this.mInitialDataSource = PlaybackDataSource.DOWNLOADED;
        } else {
            this.mInitialDataSource = PlaybackDataSource.STREAMING;
        }
    }

    @Nonnull
    private MediaSource buildMediaSource(@Nonnull Uri uri) {
        int iInferContentType = Util.inferContentType(uri);
        Context context = this.mContext;
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "AmazonVideo"));
        if (iInferContentType == 3) {
            return new ExtractorMediaSource(uri, (!this.mIsAndroidVideoPlayerV2CacheEnabled || this.mAndroidVideoPlayerV2Cache.getSimpleCache() == null) ? defaultDataSourceFactory : new CacheDataSourceFactory(this.mAndroidVideoPlayerV2Cache.getSimpleCache(), defaultDataSourceFactory), new DefaultExtractorsFactory(), null, null);
        }
        throw new IllegalStateException("AmazonVideoPlayerV2 Unsupported type: " + iInferContentType);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        int i = AnonymousClass3.$SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[videoRenderingSettings.getSurfaceHandlingMode().ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.mPlayer.setVideoSurface(videoRenderingSettings.getSurface());
                return;
            } else {
                if (i == 3) {
                    DLog.warnf("AndroidVideoPlayerV2: Audio only surface handling mode, starting without a playback surface");
                    return;
                }
                throw new IllegalStateException(String.format(Locale.US, "Unknown surface handling mode %s", videoRenderingSettings.getSurfaceHandlingMode().name()));
            }
        }
        ViewGroup parentView = videoRenderingSettings.getParentView();
        this.mParentViewGroup = parentView;
        if (this.mIsDynamicVideoSurfaceResizeEnabled) {
            this.mZoomCalculator.setParentLayoutOverride(parentView);
            LayoutChangeListener layoutChangeListener = new LayoutChangeListener();
            this.mLayoutChangeListener = layoutChangeListener;
            this.mParentViewGroup.addOnLayoutChangeListener(layoutChangeListener);
        }
        this.mSurface.createAsync(this.mParentViewGroup, this.mSurfaceCreationListener, videoRenderingSettings.isMediaOverlay(), videoRenderingSettings.getSystemUIFlags());
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.android.AndroidVideoPlayerV2$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode;

        static {
            int[] iArr = new int[SurfaceHandlingMode.values().length];
            $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode = iArr;
            try {
                iArr[SurfaceHandlingMode.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.ATTACH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$SurfaceHandlingMode[SurfaceHandlingMode.AUDIO_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private class LayoutChangeListener implements View.OnLayoutChangeListener {
        private LayoutChangeListener() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int width = view.getWidth();
            int i9 = i7 - i5;
            int height = view.getHeight();
            int i10 = i8 - i6;
            if (width == i9 && height == i10) {
                return;
            }
            DLog.logf("Video surface parent layout size changed to (w=%s h=%s), Resizing video surface.", Integer.valueOf(width), Integer.valueOf(height));
            AndroidVideoPlayerV2.this.applyVideoDimensions();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyVideoDimensions() {
        VideoConfig videoConfig = this.mVideoConfig;
        if (videoConfig == null) {
            return;
        }
        VideoResolution maxVideoResolution = videoConfig.getMaxVideoResolution();
        int width = maxVideoResolution.getWidth();
        int height = maxVideoResolution.getHeight();
        DLog.logf("AndroidVideoPlayerV2 content width=%d height=%d", Integer.valueOf(width), Integer.valueOf(height));
        this.mZoomCalculator.setVideoResolution(new VideoResolution(width, height));
        VideoRegion videoRegion = this.mZoomCalculator.getVideoRegion(this.mZoomLevel);
        int width2 = videoRegion.getWidth();
        int height2 = videoRegion.getHeight();
        DLog.logf("AndroidVideoPlayerV2 zoomed to width=%d height=%d zoomLevel=%s", Integer.valueOf(width2), Integer.valueOf(height2), this.mZoomLevel.getZoomLevel());
        this.mSurface.setDimensions(width2, height2);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        Preconditions.checkState(this.mIsPrepared, "Start cannot be called before prepare");
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring Start call after Terminate for %s", this);
        } else {
            DLog.logf("Start %s", this);
            this.mPlayer.setPlayWhenReady(true);
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        Preconditions.checkState(this.mIsPrepared, "Pause cannot be called before prepare");
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring Pause call after Terminate for %s", this);
            return;
        }
        DLog.logf("Pause %s", this);
        this.mPlayer.setPlayWhenReady(false);
        getEventProxy().onPause(createPlaybackEventContext());
        getEventProxy().onAdPlaybackPaused();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(@Nonnegative long j) {
        Preconditions.checkState(this.mIsPrepared, "SeekTo cannot be called before prepare");
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring SeekTo call after Terminate for %s", this);
            return;
        }
        Preconditions2.checkNonNegative(j, "positionMillis");
        DLog.logf("Seek %s to position: %s millis", this, Long.valueOf(j));
        this.mExoPlayerStateListener.seekTo(j);
        this.mPlayer.seekTo(j);
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long j) {
        throw new UnsupportedOperationException("seekToUTC() is not supported in AndroidVideoPlayerV2");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        return this.mPlayer.getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return this.mPlayer.getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        throw new UnsupportedOperationException("getCurrentPositionUTC() is not supported in AndroidVideoPlayerV2");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    public int getBufferPercentage() {
        return this.mPlayer.getBufferedPercentage();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        return this.mPlayer.getDuration();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nonnull
    public PlayerStatistics getPlayerStatistics() {
        return PlayerStatistics.EMPTY_PLAYER_STATS;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        return this.mExperienceController;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase
    protected void terminatePlayback(boolean z, @Nullable MediaException mediaException) {
        if (this.mIsTerminated) {
            DLog.warnf("Ignoring duplicate Terminate call for %s", this);
            return;
        }
        DLog.logf("Terminate %s", this);
        this.mIsTerminated = true;
        getEventProxy().onStop(createPlaybackEventContext());
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.getLooper().quit();
        this.mHandlerThread.quit();
        this.mPlayer.stop();
        this.mPlayer.removeListener(this.mExoPlayerStateListener);
        this.mPlayer.removeVideoListener(this.mExoPlayerStateListener);
        this.mPlayer.release();
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        objArr[0] = this.mVideoSpecification != null ? this.mVideoSpecification : "(Unspecified content)";
        return String.format(locale, "[AndroidVideoPlayerV2 for %s]", objArr);
    }

    public static class Factory {
        private final AndroidVideoPlayerV2Cache mAndroidVideoPlayerV2Cache;
        private final AndroidVideoPlayerV2Config mConfig;
        private final MediaSystemSharedContext mContext;
        private final QOSCommunicationService mQosCommunicationService;

        public Factory(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull AndroidVideoPlayerV2Config androidVideoPlayerV2Config, @Nullable QOSCommunicationService qOSCommunicationService, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache) {
            this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
            this.mConfig = (AndroidVideoPlayerV2Config) Preconditions.checkNotNull(androidVideoPlayerV2Config, "exoplayerVideoConfig");
            this.mQosCommunicationService = qOSCommunicationService;
            this.mAndroidVideoPlayerV2Cache = (AndroidVideoPlayerV2Cache) Preconditions.checkNotNull(androidVideoPlayerV2Cache, "exoPlayerCache");
        }

        @Nonnull
        public AndroidVideoPlayerV2 create() {
            PlaybackListenerProxy playbackListenerProxy = new PlaybackListenerProxy();
            HandlerThread handlerThread = new HandlerThread("AndroidVideoPlayerV2");
            SimpleExoPlayer simpleExoPlayerNewSimpleInstance = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this.mContext.getAppContext()), new DefaultTrackSelector(), new DefaultLoadControl(new DefaultAllocator(true, this.mConfig.getPlayerBufferSegmentSizeBytes()), this.mConfig.getPlayerMinBufferMillis(), this.mConfig.getPlayerMaxBufferMillis(), this.mConfig.getPlayerMinBufferMillis(), this.mConfig.getPlayerMinRebufferMillis()), handlerThread.getLooper());
            handlerThread.start();
            return new AndroidVideoPlayerV2(this.mContext.getAppContext(), simpleExoPlayerNewSimpleInstance, handlerThread, new Handler(handlerThread.getLooper()), playbackListenerProxy, this.mContext.getDeviceConfiguration(), MediaDefaultConfiguration.getInstance(), this.mQosCommunicationService, this.mAndroidVideoPlayerV2Cache, this.mConfig);
        }
    }
}
