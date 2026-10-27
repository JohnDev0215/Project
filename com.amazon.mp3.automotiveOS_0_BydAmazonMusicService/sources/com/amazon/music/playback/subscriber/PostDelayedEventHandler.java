package com.amazon.music.playback.subscriber;

import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.definition.playback.PlaybackTerminatedEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.playback.event.StreamingStartEvent;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class PostDelayedEventHandler {
    public static final long WAIT_FOR_STREAMING_INITIATED_EVENT_MS = TimeUnit.SECONDS.toMillis(30);
    private final MetricsManager metricsManager;
    private PlaybackTerminatedEvent pendingPlaybackTerminatedEvent;
    private TerminationReason pendingTerminationReason;
    private Thread submitEventThread;
    private final Logger LOG = LoggerFactory.getLogger(PostDelayedEventHandler.class.getSimpleName());
    private long timeToWaitForStreamingInitiatedEventMs = WAIT_FOR_STREAMING_INITIATED_EVENT_MS;

    public PostDelayedEventHandler(MetricsManager metricsManager) {
        this.metricsManager = metricsManager;
    }

    public boolean shouldBePostDelayed(TerminationReason terminationReason, boolean z) {
        return TerminationReason.USER_STOP == terminationReason && z;
    }

    public void submit(PlaybackTerminatedEvent playbackTerminatedEvent, TerminationReason terminationReason, boolean z) {
        if (!shouldBePostDelayed(terminationReason, z)) {
            this.metricsManager.handleEvent(playbackTerminatedEvent);
            return;
        }
        submitPendingStreamingTerminatedEvent();
        this.pendingPlaybackTerminatedEvent = playbackTerminatedEvent;
        this.pendingTerminationReason = terminationReason;
        Thread thread = new Thread() { // from class: com.amazon.music.playback.subscriber.PostDelayedEventHandler.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                PostDelayedEventHandler.this.LOG.debug("Pending " + PostDelayedEventHandler.this.pendingPlaybackTerminatedEvent);
                try {
                    Thread.sleep(PostDelayedEventHandler.this.timeToWaitForStreamingInitiatedEventMs);
                    PostDelayedEventHandler.this.metricsManager.handleEvent(PostDelayedEventHandler.this.pendingPlaybackTerminatedEvent);
                    PostDelayedEventHandler.this.reset();
                } catch (InterruptedException unused) {
                }
            }
        };
        this.submitEventThread = thread;
        thread.start();
    }

    public void updateTerminationReason(StreamingStartEvent streamingStartEvent) {
        if (!isEventHandlingPending()) {
            this.LOG.debug("Ignore StreamingStartEvent if no pending streamingTerminatedEvent.");
            return;
        }
        throw null;
    }

    boolean isEventHandlingPending() {
        return this.pendingPlaybackTerminatedEvent != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.pendingPlaybackTerminatedEvent = null;
        this.pendingTerminationReason = null;
        Thread thread = this.submitEventThread;
        if (thread != null && thread.isAlive()) {
            this.submitEventThread.interrupt();
        }
        this.submitEventThread = null;
    }

    private void submitPendingStreamingTerminatedEvent() {
        if (this.pendingPlaybackTerminatedEvent != null) {
            Thread thread = this.submitEventThread;
            if (thread != null && thread.isAlive()) {
                this.submitEventThread.interrupt();
            }
            this.LOG.debug("Submitting: " + this.pendingPlaybackTerminatedEvent);
            this.metricsManager.handleEvent(this.pendingPlaybackTerminatedEvent);
            reset();
        }
    }
}
