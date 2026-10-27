package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.DownloadStoreType;
import com.amazon.avod.content.event.ContentEventSessionStarting;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.LiveLanguageConfig;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.TimelineUtils;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareContentSession extends SimpleTask {
    private final String mContentUrlSetId;
    private final ManifestCapturerInterface mManifestCapturer;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private final TimelineUtils mTimelineUtils;

    public PrepareContentSession(@Nonnull PlaybackSession playbackSession, @Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        this(eventBus, new TimelineUtils(), playbackSession.getResources(), playbackSession.getContext(), exceptionCallback, null, manifestCapturerInterface, MediaSystemSharedDependencies.getInstance());
    }

    public PrepareContentSession(@Nonnull PlaybackSession playbackSession, @Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nullable String str, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        this(eventBus, new TimelineUtils(), playbackSession.getResources(), playbackSession.getContext(), exceptionCallback, str, manifestCapturerInterface, MediaSystemSharedDependencies.getInstance());
    }

    PrepareContentSession(@Nonnull EventBus eventBus, @Nonnull TimelineUtils timelineUtils, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull ExceptionCallback exceptionCallback, @Nullable String str, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mTimelineUtils = (TimelineUtils) Preconditions.checkNotNull(timelineUtils, "timelineUtils");
        this.mContentUrlSetId = str;
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        ContentSession contentSessionNewContentSession;
        LifecycleProfiler lifecycleProfiler = this.mPlaybackSessionResources.getLifecycleProfiler();
        LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.CONTENT_SESSION_BEGIN;
        lifecycleProfiler.start(lifecycleProfilerMetrics);
        VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
        boolean z = false;
        if (this.mPlaybackSessionContext.isDashSession() && this.mPlaybackSessionContext.getDownload() != null && this.mPlaybackSessionContext.getDownload().getDownloadStoreType() == DownloadStoreType.SINGLE_FILE) {
            contentSessionNewContentSession = this.mPlaybackSessionResources.getContentSessionFactory().newSingleFileContentSession(videoSpec, this.mPlaybackSessionContext.getStoragePath(), this.mPlaybackSessionContext.getContentSessionType(), this.mPlaybackSessionContext.getAudioVideoUrls(), this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType(), lifecycleProfiler, this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter(), this.mManifestCapturer);
        } else if (this.mPlaybackSessionContext.getContentSession() != null && this.mPlaybackSessionContext.getContentSessionType() == ContentSessionType.LIVE_CACHE) {
            contentSessionNewContentSession = this.mPlaybackSessionContext.getContentSession();
            z = true;
        } else {
            contentSessionNewContentSession = this.mPlaybackSessionResources.getContentSessionFactory().newContentSession(videoSpec, this.mPlaybackSessionContext.getStoragePath(), this.mPlaybackSessionContext.getContentSessionType(), this.mPlaybackSessionContext.getAudioVideoUrls(), this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType(), lifecycleProfiler, this.mPlaybackSessionResources.getLiveWindowDuration(), this.mContentUrlSetId, this.mPlaybackSessionResources.getContentManagementEventBus(), this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter(), this.mManifestCapturer);
        }
        this.mPlaybackSessionContext.setContentSession(contentSessionNewContentSession);
        ContentManagementEventBus eventDispatcher = contentSessionNewContentSession.getEventDispatcher();
        this.mPlaybackSessionResources.getEventReporter().bindForContentSession(eventDispatcher);
        this.mPlaybackSessionResources.getContentEventAdapter().initialize(eventDispatcher);
        contentSessionNewContentSession.begin();
        if (!contentSessionNewContentSession.isActive()) {
            if (contentSessionNewContentSession.getLastFatalError() != null) {
                throw contentSessionNewContentSession.getLastFatalError();
            }
            throw new ContentException(ContentException.ContentError.SESSION_BEGIN_FAILED, "ContentSession in an error state after calling begin()!");
        }
        if (z) {
            eventDispatcher.postEvent(new ContentEventSessionStarting(contentSessionNewContentSession.getContext(), contentSessionNewContentSession.getProtocol(), contentSessionNewContentSession.getContentUrlSelector().getCurrentContentUrl()));
        }
        if (videoSpec.isLiveStream()) {
            this.mPlaybackSessionResources.getEventReporter().setAudioLanguage(LiveLanguageConfig.getInstance().getLanguageCodeMapping().get(contentSessionNewContentSession.getCurrentAudioLanguage()));
        }
        TimeSpan startTime = videoSpec.getStartTime();
        if (!ContentType.isLive(videoSpec.getContentType()) && this.mPlaybackSessionContext.getDataSource() == PlaybackDataSource.FULLY_CACHED && !contentSessionNewContentSession.verifyAudioCachedContent(startTime.getTotalNanoSeconds())) {
            this.mPlaybackSessionContext.setDataSource(PlaybackDataSource.FULLY_CACHED_NO_AUDIO);
        }
        if (!videoSpec.isLiveStream() && !this.mMediaSystemSharedDependencies.isSDKPlayer() && this.mTimelineUtils.getLastResumablePosition(contentSessionNewContentSession).compareTo(startTime) < 0) {
            this.mPlaybackSessionContext.setVideoSpec(VideoSpecification.newBuilder(videoSpec).setStartTime(TimeSpan.ZERO).build());
        }
        ContentSessionContext context = contentSessionNewContentSession.getContext();
        this.mPlaybackSessionResources.getBitrateChangeAdapter().initialize(context);
        this.mPlaybackSessionResources.getDataCollector().initialize(eventDispatcher, context);
        lifecycleProfiler.end(lifecycleProfilerMetrics);
    }
}
