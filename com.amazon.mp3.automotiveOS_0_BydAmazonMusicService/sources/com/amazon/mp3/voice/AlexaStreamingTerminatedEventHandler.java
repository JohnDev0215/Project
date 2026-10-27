package com.amazon.mp3.voice;

import android.os.Handler;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.metrics.mts.event.definition.playback.StreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaStreamingTerminatedEventHandler {
    private static final String TAG = "AlexaStreamingTerminatedEventHandler";
    private static final long WAIT_FOR_STREAMING_INITIATED_EVENT_MS = TimeUnit.SECONDS.toMillis(30);
    private StreamingTerminatedEvent mPendingStreamingTerminatedEvent;
    private final MTSMetricsSender mSender;
    private final Runnable mEventTask = new Runnable() { // from class: com.amazon.mp3.voice.AlexaStreamingTerminatedEventHandler.1
        @Override // java.lang.Runnable
        public void run() {
            if (AlexaStreamingTerminatedEventHandler.this.mPendingStreamingTerminatedEvent != null) {
                AlexaStreamingTerminatedEventHandler.this.mSender.sendMetricsEvent(AlexaStreamingTerminatedEventHandler.this.mPendingStreamingTerminatedEvent);
            }
            AlexaStreamingTerminatedEventHandler.this.reset();
        }
    };
    private final Handler mTaskHandler = AmazonApplication.getBackgroundHandler();

    public AlexaStreamingTerminatedEventHandler(final MTSMetricsSender sender) {
        this.mSender = sender;
    }

    public void submit(final StreamingTerminatedEvent event) {
        if (event == null) {
            Log.debug(TAG, "event is null, nothing to send");
            return;
        }
        submitPendingStreamingTerminatedEvent();
        if (!TerminationReason.SYSTEM_STOP.getMetricsValue().equals(event.getEventAttributes().get("terminationReason"))) {
            this.mSender.sendMetricsEvent(event);
        } else {
            this.mPendingStreamingTerminatedEvent = event;
            this.mTaskHandler.postDelayed(this.mEventTask, WAIT_FOR_STREAMING_INITIATED_EVENT_MS);
        }
    }

    public void updateTerminationReason(final AlexaMediaItem alexaMediaItem) {
        if (this.mPendingStreamingTerminatedEvent == null) {
            return;
        }
        if (alexaMediaItem.isFirstTrackInQueue()) {
            this.mPendingStreamingTerminatedEvent.setTerminationReason(TerminationReason.USER_STOP);
        } else {
            this.mPendingStreamingTerminatedEvent.setTerminationReason(TerminationReason.USER_NEXT);
        }
        submitPendingStreamingTerminatedEvent();
    }

    private void submitPendingStreamingTerminatedEvent() {
        if (this.mPendingStreamingTerminatedEvent != null) {
            this.mTaskHandler.removeCallbacks(this.mEventTask);
            this.mSender.sendMetricsEvent(this.mPendingStreamingTerminatedEvent);
            reset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.mPendingStreamingTerminatedEvent = null;
    }
}
