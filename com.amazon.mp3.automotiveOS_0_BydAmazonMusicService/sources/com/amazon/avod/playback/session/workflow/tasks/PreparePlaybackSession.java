package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.playback.event.playback.InitialLoadingEvent;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PreparePlaybackSession extends SimpleTask {
    private final PlaybackConfig mPlaybackConfig;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public PreparePlaybackSession(@Nonnull PlaybackSession playbackSession, @Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback) {
        this(playbackSession.getResources(), playbackSession.getContext(), eventBus, exceptionCallback, PlaybackConfig.INSTANCE);
    }

    PreparePlaybackSession(@Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackConfig playbackConfig) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        Profiler.reportTimerMetric(new SimpleTimerMetric("DAG-PreparePlaybackSession-StartTimestamp", System.currentTimeMillis(), 0L));
        this.mPlaybackSessionResources.getLifecycleProfiler().start(LifecycleProfilerMetrics.DAG_PREPARE);
        final VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
        boolean z = videoSpec.doNotUseStoredAssets() && this.mPlaybackConfig.canDisableCache();
        PlaybackEventTransport playbackEventTransport = this.mPlaybackSessionResources.getPlaybackEventTransport();
        if (!videoSpec.isEmbedded() && !z) {
            this.mPlaybackSessionContext.setCacheRecordSupplier(Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.playback.session.workflow.tasks.PreparePlaybackSession$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return this.f$0.lambda$execute$0(videoSpec);
                }
            }));
        }
        if (videoSpec.getAudioFormat().isSurroundSound() && !this.mPlaybackSessionResources.getPlaybackSupportEvaluator().isDolbyDigitalPlusSupported(null)) {
            DLog.warnf("Requested AC-3 (Dolby 5.1) audio not supported by this device.  Falling back to STEREO.");
            videoSpec = VideoSpecification.newBuilder(videoSpec).setAudioFormat(AudioFormat.STEREO).build();
        }
        this.mPlaybackSessionContext.setVideoSpec(videoSpec);
        this.mPlaybackSessionResources.getEventReporter().bindForPlayback(playbackEventTransport);
        PlayableContent playableContentNewPlayableContent = PlayableContent.newPlayableContent(videoSpec);
        playbackEventTransport.postEvent(new InitialLoadingEvent(videoSpec.getStartTime(), playableContentNewPlayableContent.getTitleId(), playableContentNewPlayableContent.getUniqueIdentifier()));
        VideoSpecification videoSpec2 = this.mPlaybackSessionContext.getVideoSpec();
        PVDownloadManagerPlayerShim downloadManager = this.mPlaybackSessionResources.getDownloadManager();
        if (this.mPlaybackSessionResources.getIdentity().getCurrentUser().orNull() == null) {
            throw new DrmLicensingException(LicenseError.AIV_DRM_USERID_NOT_PROVIDED);
        }
        Optional<PVDownloadPlayerShim> offlineDownloadIfPresent = downloadManager.getOfflineDownloadIfPresent(videoSpec2, this.mPlaybackSessionContext.getVideoOptions().getSessionContext());
        if (offlineDownloadIfPresent.isPresent()) {
            this.mPlaybackSessionContext.setDownload(offlineDownloadIfPresent.get());
        } else {
            this.mPlaybackSessionContext.setDownload(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CacheRecord lambda$execute$0(VideoSpecification videoSpecification) {
        return this.mPlaybackSessionResources.getCacheManager().queryFor(videoSpecification);
    }
}
