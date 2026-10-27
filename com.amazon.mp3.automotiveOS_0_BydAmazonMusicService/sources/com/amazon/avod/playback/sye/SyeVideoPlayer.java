package com.amazon.avod.playback.sye;

import android.app.Activity;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.RelativeLayout;
import com.amazon.avod.content.urlvending.TimeShiftPolicy;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoPlayerBase;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.sye.listeners.ISeekActionListener;
import com.amazon.avod.playback.sye.listeners.SyeTimelineHolder;
import com.amazon.avod.playback.sye.trickplay.TrickplayManager;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.netinsight.sye.syeClient.ISyeMetrics;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.SyeDomain;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.view.SyeClosedCaptionView;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SyeVideoPlayer extends VideoPlayerBase {
    private final Activity mActivity;
    private final SyeAuditPingTracker mAuditPingTracker;
    private final SyeConfig mConfig;
    private MatchParentOnLayoutChange mLayoutChangeListener;
    private final ObjectMapper mObjectMapper;
    private final PlaybackExperienceController mPlaybackExperienceController;
    private final AloysiusReporterHolder mReportersHolder;
    private final ISeekActionListener mSeekActionListener;
    private final SyeDomainVendingMachine mSyeDomainVendingMachine;
    private final ISyePlayer mSyePlayer;
    private final SystemTimeProvider mSystemTimeProvider;
    private final TimeShiftPolicy mTimeShiftPolicy;
    private final SyeTimelineHolder mTimelineHolder;
    private final TrickplayManager mTrickplayManager;
    private final Handler mUIHandler;
    private final SyeViewHolder mViewHolder;

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    public int getBufferPercentage() {
        return 0;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        return 2147483647L;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase, com.amazon.avod.media.playback.VideoPlayer
    public void notifyUserLaunchedPlayback() {
    }

    private static final class MatchParentOnLayoutChange implements View.OnLayoutChangeListener {
        private final View mSubtitleView;
        private final View mVideoView;

        private MatchParentOnLayoutChange(View view, View view2) {
            this.mVideoView = view;
            this.mSubtitleView = view2;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (i == i5 && i3 == i7 && i2 == i6 && i4 == i8) {
                return;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13, -1);
            this.mVideoView.setLayoutParams(layoutParams);
            this.mSubtitleView.setLayoutParams(layoutParams);
        }
    }

    SyeVideoPlayer(@Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull Activity activity, @Nonnull ISyePlayer iSyePlayer, @Nonnull SyeTimelineHolder syeTimelineHolder, @Nonnull SyeViewHolder syeViewHolder, @Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull AloysiusReporterHolder aloysiusReporterHolder, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, @Nonnull SyeConfig syeConfig, @Nonnull SyeAuditPingTracker syeAuditPingTracker, @Nonnull TrickplayManager trickplayManager, @Nonnull TimeShiftPolicy timeShiftPolicy, @Nonnull ISeekActionListener iSeekActionListener) {
        this(playbackListenerProxy, activity, iSyePlayer, syeTimelineHolder, syeViewHolder, playbackExperienceController, aloysiusReporterHolder, syeDomainVendingMachine, syeConfig, syeAuditPingTracker, trickplayManager, timeShiftPolicy, iSeekActionListener, new SystemTimeProvider());
    }

    SyeVideoPlayer(@Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull Activity activity, @Nonnull ISyePlayer iSyePlayer, @Nonnull SyeTimelineHolder syeTimelineHolder, @Nonnull SyeViewHolder syeViewHolder, @Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull AloysiusReporterHolder aloysiusReporterHolder, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, @Nonnull SyeConfig syeConfig, @Nonnull SyeAuditPingTracker syeAuditPingTracker, @Nonnull TrickplayManager trickplayManager, @Nonnull TimeShiftPolicy timeShiftPolicy, @Nonnull ISeekActionListener iSeekActionListener, @Nonnull SystemTimeProvider systemTimeProvider) {
        super(playbackListenerProxy);
        this.mLayoutChangeListener = null;
        Activity activity2 = (Activity) Preconditions.checkNotNull(activity, MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        this.mActivity = activity2;
        this.mSyePlayer = (ISyePlayer) Preconditions.checkNotNull(iSyePlayer, "syePlayer");
        this.mTimelineHolder = (SyeTimelineHolder) Preconditions.checkNotNull(syeTimelineHolder, "timelineHolder");
        this.mReportersHolder = (AloysiusReporterHolder) Preconditions.checkNotNull(aloysiusReporterHolder, "reportersHolder");
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
        this.mConfig = (SyeConfig) Preconditions.checkNotNull(syeConfig, "config");
        this.mViewHolder = (SyeViewHolder) Preconditions.checkNotNull(syeViewHolder, "viewHolder");
        this.mPlaybackExperienceController = (PlaybackExperienceController) Preconditions.checkNotNull(playbackExperienceController, "syeController");
        this.mAuditPingTracker = (SyeAuditPingTracker) Preconditions.checkNotNull(syeAuditPingTracker, "auditPingTracker");
        this.mTrickplayManager = (TrickplayManager) Preconditions.checkNotNull(trickplayManager, "syeTrickplayManager");
        this.mTimeShiftPolicy = (TimeShiftPolicy) Preconditions.checkNotNull(timeShiftPolicy, "timeShiftPolicy");
        this.mSeekActionListener = (ISeekActionListener) Preconditions.checkNotNull(iSeekActionListener, "seekActionListener");
        this.mSystemTimeProvider = (SystemTimeProvider) Preconditions.checkNotNull(systemTimeProvider, "systemTimeProvider");
        this.mObjectMapper = JacksonCache.OBJECT_MAPPER;
        this.mUIHandler = new Handler(activity2.getMainLooper());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void prepareAsync(VideoSpecification videoSpecification, File file) throws MediaException {
        DLog.logf("Preparing SyePlayer for spec %s", videoSpecification);
        long systemCurrentTimeMillis = this.mSystemTimeProvider.getSystemCurrentTimeMillis();
        long totalMilliseconds = videoSpecification.getStartTime().getTotalMilliseconds();
        SyeDomainHolder syeDomainHolderCurrentSyeDomain = this.mSyeDomainVendingMachine.currentSyeDomain();
        boolean z = totalMilliseconds != 0 && totalMilliseconds > systemCurrentTimeMillis - this.mConfig.getLiveRangeMillis();
        this.mTrickplayManager.startCaching();
        DLog.logf("Preparing SyePlayer, currentTime %d, resumeTime %d", Long.valueOf(systemCurrentTimeMillis), Long.valueOf(totalMilliseconds));
        if (z) {
            DLog.logf("Preparing SyePlayer, playing from live");
            this.mSyePlayer.playFromLive(syeDomainHolderCurrentSyeDomain.getChannelId());
        } else {
            long startUtcMillis = syeDomainHolderCurrentSyeDomain.getStartUtcMillis();
            long jMin = Math.min(syeDomainHolderCurrentSyeDomain.getDurationMillis(), syeDomainHolderCurrentSyeDomain.getDvrDurationMillis());
            long jMax = Math.max(systemCurrentTimeMillis - jMin, startUtcMillis);
            long jMax2 = Math.max(totalMilliseconds, jMax);
            DLog.logf("Preparing SyePlayer, streamStart %d, liveLookBack %d, earliestAvailable %d", Long.valueOf(startUtcMillis), Long.valueOf(jMin), Long.valueOf(jMax));
            DLog.logf("Preparing SyePlayer, playing from UTC time %d", Long.valueOf(jMax2));
            this.mSyePlayer.playFromUtcTime(syeDomainHolderCurrentSyeDomain.getChannelId(), jMax2);
        }
        getEventProxy().onContentMetadataAvailable(new VideoResolution[]{new VideoResolution(this.mConfig.getMaxHFRDisplayWidth(), this.mConfig.getMaxHFRDisplayHeight())}, this.mConfig.getDefaultFrameRate());
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void setRenderingSettings(@Nonnull final VideoRenderingSettings videoRenderingSettings) {
        this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.playback.sye.SyeVideoPlayer.1
            @Override // java.lang.Runnable
            public void run() {
                SurfaceView surfaceView = new SurfaceView(SyeVideoPlayer.this.mActivity);
                ViewGroup parentView = videoRenderingSettings.getParentView();
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13, -1);
                parentView.addView(surfaceView, layoutParams);
                SyeVideoPlayer.this.mSyePlayer.attach(surfaceView);
                SyeClosedCaptionView syeClosedCaptionView = new SyeClosedCaptionView(SyeVideoPlayer.this.mActivity);
                parentView.addView(syeClosedCaptionView, layoutParams);
                syeClosedCaptionView.setVideoClientView(surfaceView);
                SyeVideoPlayer.this.mSyePlayer.attach(syeClosedCaptionView);
                SyeVideoPlayer.this.mLayoutChangeListener = new MatchParentOnLayoutChange(surfaceView, syeClosedCaptionView);
                parentView.addOnLayoutChangeListener(SyeVideoPlayer.this.mLayoutChangeListener);
                SyeVideoPlayer.this.mViewHolder.setVideoView(surfaceView);
                SyeVideoPlayer.this.mViewHolder.setSubtitleView(syeClosedCaptionView);
                SyeVideoPlayer.this.mViewHolder.setParent(parentView);
            }
        });
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        DLog.logf("SyePlayer::start");
        if (this.mSyePlayer.getPlayerState() == SyePlayerState.Playing || this.mSyePlayer.getPlayerState() == SyePlayerState.Loading) {
            return;
        }
        DLog.logf("SyePlayer::playResume");
        this.mTrickplayManager.startCaching();
        this.mSyePlayer.playResume();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        DLog.logf("SyePlayer::pause");
        this.mSyePlayer.pause();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long j) {
        throw new UnsupportedOperationException("SyePlayer doesn't support seek");
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToUTC(long j) {
        DLog.logf("SyePlayer::seekToUTC %d", Long.valueOf(j));
        if (this.mTimeShiftPolicy.isSeekBackwardEnabled() || this.mTimeShiftPolicy.isSeekForwardEnabled()) {
            SyeDomainHolder syeDomainHolderCurrentSyeDomain = this.mSyeDomainVendingMachine.currentSyeDomain();
            long systemCurrentTimeMillis = this.mSystemTimeProvider.getSystemCurrentTimeMillis();
            long jMax = Math.max(j, (this.mTimelineHolder.getLivePosUtcMillis() - ((long) syeDomainHolderCurrentSyeDomain.getDvrDurationMillis())) + this.mConfig.getSeekPaddingMillis());
            this.mTrickplayManager.startCaching();
            if (jMax >= systemCurrentTimeMillis - this.mConfig.getLiveRangeMillis()) {
                this.mSeekActionListener.onSeekStart(systemCurrentTimeMillis);
                DLog.logf("SyePlayer::seekToUTC, playFromLive");
                this.mSyePlayer.playFromLive(syeDomainHolderCurrentSyeDomain.getChannelId());
            } else {
                this.mSeekActionListener.onSeekStart(jMax);
                DLog.logf("SyePlayer::seekToUTC, playFromUtcTime %d", Long.valueOf(jMax));
                this.mSyePlayer.playFromUtcTime(syeDomainHolderCurrentSyeDomain.getChannelId(), jMax);
                this.mTimelineHolder.setSeekingPosUtcMillis(jMax);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        return this.mTimelineHolder.getCurrentPosUtcMillis();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPositionUTC() {
        return this.mTimelineHolder.getCurrentPosUtcMillis();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    @Nonnull
    public PlayerStatistics getPlayerStatistics() {
        RendererPerformanceData rendererPerformanceData = getRendererPerformanceData();
        PlayerStatistics playerStatistics = PlayerStatistics.EMPTY_PLAYER_STATS;
        return new PlayerStatistics(rendererPerformanceData, playerStatistics.getFrontBuffers(), playerStatistics.getFrontBuffers());
    }

    private RendererPerformanceData getRendererPerformanceData() {
        try {
            ISyeMetrics metrics = this.mSyePlayer.getMetrics();
            if (metrics == null) {
                return RendererPerformanceData.EMPTY_PERF_DATA;
            }
            int videoFramesDropped = metrics.getVideoFramesDropped();
            DLog.devf("SyeRendererPerformanceData totalDroppedFrames : %s", Integer.valueOf(videoFramesDropped));
            return new RendererPerformanceData(0.0d, videoFramesDropped, 0, ImmutableList.of());
        } catch (Exception unused) {
            return RendererPerformanceData.EMPTY_PERF_DATA;
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public PlaybackExperienceController getPlaybackExperienceController() {
        return this.mPlaybackExperienceController;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerBase
    protected void terminatePlayback(boolean z, @Nullable MediaException mediaException) {
        this.mSyePlayer.stop();
        try {
            this.mSyePlayer.teardown();
        } catch (Exception e) {
            DLog.logf("failed to teardown sye player: %s", e.getMessage());
        }
        if (this.mViewHolder.getVideoView() != null) {
            this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.playback.sye.SyeVideoPlayer.2
                @Override // java.lang.Runnable
                public void run() {
                    ViewGroup parent = SyeVideoPlayer.this.mViewHolder.getParent();
                    SurfaceView videoView = SyeVideoPlayer.this.mViewHolder.getVideoView();
                    SyeClosedCaptionView subtitleView = SyeVideoPlayer.this.mViewHolder.getSubtitleView();
                    parent.removeView(videoView);
                    parent.removeView(subtitleView);
                    parent.removeOnLayoutChangeListener(SyeVideoPlayer.this.mLayoutChangeListener);
                    parent.invalidate();
                    if (videoView == null || videoView.getHolder() == null || videoView.getHolder().getSurface() == null) {
                        return;
                    }
                    SyeVideoPlayer.this.mViewHolder.getVideoView().getHolder().getSurface().release();
                }
            });
        }
        this.mReportersHolder.getMediaEventReporters().terminate();
        this.mAuditPingTracker.stop();
        List<SyeDomain> listAllDomains = this.mSyeDomainVendingMachine.allDomains();
        CookieManager cookieManager = CookieManager.getInstance();
        Iterator<SyeDomain> it = listAllDomains.iterator();
        while (it.hasNext()) {
            cookieManager.setCookie(it.next().getBaseUrl(), "");
        }
    }
}
