package com.amazon.avod.playback.session.workflow;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturer;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestUploaderFactory;
import com.amazon.avod.content.smoothstream.manifest.acquisition.NoOpManifestCapturer;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.config.PlayerLifecycleConfig;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.session.workflow.scheduler.DAGTaskScheduler;
import com.amazon.avod.playback.session.workflow.scheduler.Task;
import com.amazon.avod.playback.session.workflow.scheduler.TaskScheduler;
import com.amazon.avod.playback.session.workflow.tasks.AdPlayerTriggeredOnPrepared;
import com.amazon.avod.playback.session.workflow.tasks.ConsumePrerolls;
import com.amazon.avod.playback.session.workflow.tasks.ExceptionCallback;
import com.amazon.avod.playback.session.workflow.tasks.MainPlayerTriggeredOnPrepared;
import com.amazon.avod.playback.session.workflow.tasks.MonitorPerformanceEvents;
import com.amazon.avod.playback.session.workflow.tasks.PreloadPrerollBreaks;
import com.amazon.avod.playback.session.workflow.tasks.PrepareAdPlan;
import com.amazon.avod.playback.session.workflow.tasks.PrepareContentSession;
import com.amazon.avod.playback.session.workflow.tasks.PrepareDRMScheme;
import com.amazon.avod.playback.session.workflow.tasks.PrepareDownloadResources;
import com.amazon.avod.playback.session.workflow.tasks.PrepareLicense;
import com.amazon.avod.playback.session.workflow.tasks.PreparePlaybackSession;
import com.amazon.avod.playback.session.workflow.tasks.PreparePlayerEngine;
import com.amazon.avod.playback.session.workflow.tasks.PrepareRenderScheme;
import com.amazon.avod.playback.session.workflow.tasks.PrepareStreamingResources;
import com.amazon.avod.playback.session.workflow.tasks.RestartPlaybackSessionTask;
import com.amazon.avod.playback.session.workflow.tasks.StartPlayback;
import com.amazon.avod.playback.session.workflow.tasks.StartPlaybackSignal;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.eventbus.EventBus;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerLifecycleWorkflow {
    private final long mDAGTerminationTimeoutInMillis;
    private final boolean mEnforceWaitUntilDAGTermination;
    private final EventBus mEventBus;
    private final Object mSessionLock = new Object();
    private final TaskScheduler mTaskScheduler;

    public PlayerLifecycleWorkflow(@Nonnull PlayerLifecycleConfig playerLifecycleConfig) {
        EventBus eventBus = new EventBus("PlayerLifecycleWorkflowEventBus");
        this.mEventBus = eventBus;
        int dAGMaximumConcurrentTasks = playerLifecycleConfig.getDAGMaximumConcurrentTasks();
        this.mTaskScheduler = new DAGTaskScheduler(eventBus, dAGMaximumConcurrentTasks, ScheduledExecutorBuilder.newBuilderFor(DAGTaskScheduler.class, new String[0]).withFixedThreadPoolSize(dAGMaximumConcurrentTasks).build());
        this.mEnforceWaitUntilDAGTermination = playerLifecycleConfig.shouldEnforceWaitUntilDAGTermination();
        this.mDAGTerminationTimeoutInMillis = playerLifecycleConfig.getDAGTerminationTimeout().getTotalMilliseconds();
    }

    private void createNewAdEnabledExecutionPlan(PlaybackSession playbackSession) {
        ManifestCapturerInterface manifestCapturer;
        PlaybackSessionContext context = playbackSession.getContext();
        PlaybackSessionResources resources = playbackSession.getResources();
        ExceptionCallback exceptionCallback = getExceptionCallback(playbackSession);
        VideoSpecification videoSpec = playbackSession.getContext().getVideoSpec();
        if (videoSpec == null) {
            manifestCapturer = new NoOpManifestCapturer();
        } else {
            manifestCapturer = new ManifestCapturer(new ManifestUploaderFactory().createManifestUploader(), videoSpec);
        }
        PreparePlaybackSession preparePlaybackSession = new PreparePlaybackSession(playbackSession, this.mEventBus, exceptionCallback);
        StartPlaybackSignal startPlaybackSignal = new StartPlaybackSignal(playbackSession, this.mEventBus, exceptionCallback);
        PrepareDownloadResources prepareDownloadResources = new PrepareDownloadResources(this.mEventBus, exceptionCallback, resources, context);
        PrepareStreamingResources prepareStreamingResources = new PrepareStreamingResources(this.mEventBus, exceptionCallback, resources, context);
        PrepareRenderScheme prepareRenderScheme = new PrepareRenderScheme(this.mEventBus, exceptionCallback, resources, context);
        PrepareDRMScheme prepareDRMScheme = new PrepareDRMScheme(this.mEventBus, exceptionCallback, resources, context);
        PrepareContentSession prepareContentSession = new PrepareContentSession(playbackSession, this.mEventBus, exceptionCallback, manifestCapturer);
        PreparePlayerEngine preparePlayerEngine = new PreparePlayerEngine(this.mEventBus, exceptionCallback, resources, context, manifestCapturer);
        PrepareLicense prepareLicense = new PrepareLicense(this.mEventBus, exceptionCallback, resources, context);
        StartPlayback startPlayback = new StartPlayback(this.mEventBus, exceptionCallback, resources, context);
        MonitorPerformanceEvents monitorPerformanceEvents = new MonitorPerformanceEvents(playbackSession, this.mEventBus, exceptionCallback);
        PrepareAdPlan prepareAdPlan = new PrepareAdPlan(this.mEventBus, exceptionCallback, playbackSession, context, manifestCapturer);
        PreloadPrerollBreaks preloadPrerollBreaks = new PreloadPrerollBreaks(this.mEventBus, exceptionCallback, context);
        MainPlayerTriggeredOnPrepared mainPlayerTriggeredOnPrepared = new MainPlayerTriggeredOnPrepared(this.mEventBus, exceptionCallback, resources, context);
        AdPlayerTriggeredOnPrepared adPlayerTriggeredOnPrepared = new AdPlayerTriggeredOnPrepared(this.mEventBus, exceptionCallback, resources, context);
        ConsumePrerolls consumePrerolls = new ConsumePrerolls(this.mEventBus, exceptionCallback, resources, context);
        this.mTaskScheduler.addTask(preparePlaybackSession, new Task[0]);
        this.mTaskScheduler.addTask(startPlaybackSignal, preparePlaybackSession);
        this.mTaskScheduler.addTask(prepareDownloadResources, preparePlaybackSession);
        this.mTaskScheduler.addTask(prepareStreamingResources, preparePlaybackSession);
        this.mTaskScheduler.addTask(prepareAdPlan, preparePlaybackSession);
        this.mTaskScheduler.addTask(preloadPrerollBreaks, prepareAdPlan, prepareContentSession);
        this.mTaskScheduler.addTask(prepareRenderScheme, prepareDownloadResources, prepareStreamingResources);
        this.mTaskScheduler.addTask(prepareDRMScheme, prepareDownloadResources, prepareStreamingResources);
        this.mTaskScheduler.addTask(prepareContentSession, prepareRenderScheme, prepareDRMScheme);
        this.mTaskScheduler.addTask(preparePlayerEngine, prepareContentSession);
        this.mTaskScheduler.addTask(prepareLicense, prepareContentSession);
        this.mTaskScheduler.addTask(mainPlayerTriggeredOnPrepared, preparePlayerEngine, prepareLicense, preloadPrerollBreaks);
        this.mTaskScheduler.addTask(adPlayerTriggeredOnPrepared, preloadPrerollBreaks);
        this.mTaskScheduler.addTask(consumePrerolls, startPlaybackSignal, adPlayerTriggeredOnPrepared);
        this.mTaskScheduler.addTask(startPlayback, consumePrerolls, mainPlayerTriggeredOnPrepared);
        this.mTaskScheduler.addTask(monitorPerformanceEvents, startPlayback);
    }

    private void createRestartExecutionPlan(PlaybackSession playbackSession, PlaybackRestartEvent playbackRestartEvent) {
        ManifestCapturerInterface manifestCapturer;
        PlaybackSessionContext context = playbackSession.getContext();
        VideoSpecification videoSpec = context.getVideoSpec();
        context.setAudioVideoUrls(playbackRestartEvent.getAudioVideoUrls());
        PlaybackSessionResources resources = playbackSession.getResources();
        ExceptionCallback exceptionCallback = getExceptionCallback(playbackSession);
        if (videoSpec == null) {
            manifestCapturer = new NoOpManifestCapturer();
        } else {
            manifestCapturer = new ManifestCapturer(new ManifestUploaderFactory().createManifestUploader(), videoSpec);
        }
        PreparePlaybackSession preparePlaybackSession = new PreparePlaybackSession(playbackSession, this.mEventBus, exceptionCallback);
        StartPlaybackSignal startPlaybackSignal = new StartPlaybackSignal(playbackSession, this.mEventBus, exceptionCallback);
        PrepareDownloadResources prepareDownloadResources = new PrepareDownloadResources(this.mEventBus, exceptionCallback, resources, context);
        PrepareStreamingResources prepareStreamingResources = new PrepareStreamingResources(this.mEventBus, exceptionCallback, resources, context);
        PrepareRenderScheme prepareRenderScheme = new PrepareRenderScheme(this.mEventBus, exceptionCallback, resources, context);
        PrepareDRMScheme prepareDRMScheme = new PrepareDRMScheme(this.mEventBus, exceptionCallback, resources, context);
        PrepareContentSession prepareContentSession = new PrepareContentSession(playbackSession, this.mEventBus, exceptionCallback, playbackRestartEvent.getContentUrlSetId(), manifestCapturer);
        PreparePlayerEngine preparePlayerEngine = new PreparePlayerEngine(this.mEventBus, exceptionCallback, resources, context, manifestCapturer);
        PrepareLicense prepareLicense = new PrepareLicense(this.mEventBus, exceptionCallback, resources, context);
        StartPlayback startPlayback = new StartPlayback(this.mEventBus, exceptionCallback, resources, context);
        MonitorPerformanceEvents monitorPerformanceEvents = new MonitorPerformanceEvents(playbackSession, this.mEventBus, exceptionCallback);
        this.mTaskScheduler.addTask(preparePlaybackSession, new Task[0]);
        this.mTaskScheduler.addTask(startPlaybackSignal, preparePlaybackSession);
        this.mTaskScheduler.addTask(prepareDownloadResources, preparePlaybackSession);
        this.mTaskScheduler.addTask(prepareStreamingResources, preparePlaybackSession);
        this.mTaskScheduler.addTask(prepareRenderScheme, prepareDownloadResources, prepareStreamingResources);
        this.mTaskScheduler.addTask(prepareDRMScheme, prepareDownloadResources, prepareStreamingResources);
        this.mTaskScheduler.addTask(prepareContentSession, prepareRenderScheme, prepareDRMScheme);
        this.mTaskScheduler.addTask(preparePlayerEngine, prepareContentSession);
        this.mTaskScheduler.addTask(prepareLicense, prepareContentSession);
        this.mTaskScheduler.addTask(startPlayback, preparePlayerEngine, prepareLicense, startPlaybackSignal);
        this.mTaskScheduler.addTask(monitorPerformanceEvents, startPlayback);
    }

    public void preparePlaybackSession(PlaybackSession playbackSession) {
        synchronized (this.mSessionLock) {
            DLog.logf("PlayerLifecycleWorkflow - preparePlaybackSession for %s", playbackSession.getContext().getVideoSpec().getTitleId());
            createNewAdEnabledExecutionPlan(playbackSession);
            this.mTaskScheduler.startProcessing();
        }
    }

    public void restartPlaybackSession(PlaybackSession playbackSession, PlaybackRestartEvent playbackRestartEvent) {
        synchronized (this.mSessionLock) {
            PlaybackSessionContext context = playbackSession.getContext();
            VideoSpecification videoSpec = context.getVideoSpec();
            DLog.logf("PlayerLifecycleWorkflow - restartPlaybackSession for %s", videoSpec.getTitleId());
            this.mTaskScheduler.stopProcessingAndExecuteTask(new RestartPlaybackSessionTask(this.mEventBus, getExceptionCallback(playbackSession), playbackSession.getResources(), context, videoSpec, playbackRestartEvent));
            createRestartExecutionPlan(playbackSession, playbackRestartEvent);
            this.mTaskScheduler.startProcessing();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0063 A[Catch: all -> 0x0065, DONT_GENERATE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0030, B:8:0x0037, B:10:0x0045, B:13:0x0056, B:14:0x0063), top: B:21:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void terminate(com.amazon.avod.playback.session.PlaybackSession r11, boolean r12, @javax.annotation.Nullable com.amazon.avod.media.framework.error.MediaException r13) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.mSessionLock
            monitor-enter(r0)
            com.amazon.avod.playback.session.PlaybackSessionContext r8 = r11.getContext()     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.media.playback.VideoSpecification r1 = r8.getVideoSpec()     // Catch: java.lang.Throwable -> L65
            java.lang.String r2 = "PlayerLifecycleWorkflow - terminate for %s"
            java.lang.String r1 = r1.getTitleId()     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.util.DLog.logf(r2, r1)     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.playback.session.workflow.tasks.ShutdownPlaybackSessionTask r9 = new com.amazon.avod.playback.session.workflow.tasks.ShutdownPlaybackSessionTask     // Catch: java.lang.Throwable -> L65
            com.google.common.eventbus.EventBus r2 = r10.mEventBus     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.playback.session.workflow.tasks.ExceptionCallback r3 = r10.getExceptionCallback(r11)     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.playback.session.PlaybackSessionResources r4 = r11.getResources()     // Catch: java.lang.Throwable -> L65
            r1 = r9
            r5 = r8
            r6 = r12
            r7 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.playback.session.workflow.scheduler.TaskScheduler r11 = r10.mTaskScheduler     // Catch: java.lang.Throwable -> L65
            r11.stopProcessingAndExecuteTask(r9)     // Catch: java.lang.Throwable -> L65
            boolean r11 = r10.mEnforceWaitUntilDAGTermination     // Catch: java.lang.Throwable -> L65
            if (r11 == 0) goto L63
            java.lang.String r11 = "DAG: Waiting on playback session termination"
            com.amazon.avod.util.DLog.devf(r11)     // Catch: java.lang.Throwable -> L65
            r11 = 0
            r12 = 1
            java.util.concurrent.CountDownLatch r13 = r8.getTerminationLatch()     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            long r1 = r10.mDAGTerminationTimeoutInMillis     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            boolean r13 = r13.await(r1, r3)     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            if (r13 != 0) goto L63
            java.lang.String r13 = "DAG: Failed to terminate player in the allotted time: %s ms"
            java.lang.Object[] r1 = new java.lang.Object[r12]     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            long r2 = r10.mDAGTerminationTimeoutInMillis     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            r1[r11] = r2     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            com.amazon.avod.util.DLog.warnf(r13, r1)     // Catch: java.lang.InterruptedException -> L55 java.lang.Throwable -> L65
            goto L63
        L55:
            r13 = move-exception
            java.lang.String r1 = "DAG: Interrupted while trying to terminate: %s"
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L65
            java.lang.String r13 = r13.getMessage()     // Catch: java.lang.Throwable -> L65
            r12[r11] = r13     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.util.DLog.warnf(r1, r12)     // Catch: java.lang.Throwable -> L65
        L63:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            return
        L65:
            r11 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.session.workflow.PlayerLifecycleWorkflow.terminate(com.amazon.avod.playback.session.PlaybackSession, boolean, com.amazon.avod.media.framework.error.MediaException):void");
    }

    private ExceptionCallback getExceptionCallback(@Nonnull final PlaybackSession playbackSession) {
        return new ExceptionCallback() { // from class: com.amazon.avod.playback.session.workflow.PlayerLifecycleWorkflow$$ExternalSyntheticLambda0
            @Override // com.amazon.avod.playback.session.workflow.tasks.ExceptionCallback
            public final void call(MediaException mediaException) {
                PlayerLifecycleWorkflow.lambda$getExceptionCallback$0(playbackSession, mediaException);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getExceptionCallback$0(PlaybackSession playbackSession, MediaException mediaException) {
        DLog.logf("PlayerLifecycleWorkflow - exception being handled by ExceptionCallback");
        if (playbackSession.getResources().getRendererSchemeController().notifyAutoEvalCanaryPlaybackFailureEvent(mediaException)) {
            DLog.warnf("Restarting the player as auto eval canary has failed due to: %s", mediaException);
            VideoPlaybackEngine playbackEngine = playbackSession.getPlaybackEngine();
            playbackSession.getResources().getPlaybackEventTransport().postEvent(PlaybackRestartEvent.newAutoEvalCanaryFailedRestartEvent(playbackEngine != null ? TimeUnit.NANOSECONDS.toMillis(playbackEngine.getCurrentPositionInNanos()) : 0L, mediaException.getErrorCode().getExternalCode().getName()));
            playbackSession.getResources().getEventReporter().getPlaybackReporter().reportMetric(QoSMetric.Information.toString(), "PlayerAutoEvalCanaryFailed", TimeSpan.ZERO, String.format(Locale.US, "Failure cause: %s", mediaException), null);
            return;
        }
        ContentSession contentSession = playbackSession.getContentSession();
        playbackSession.getResources().getPlaybackEventTransport().postEvent(new FatalPlaybackErrorEvent(TimeSpan.ZERO, mediaException, contentSession == null ? null : contentSession.getConsumptionId()));
        playbackSession.terminate(false, mediaException);
    }
}
