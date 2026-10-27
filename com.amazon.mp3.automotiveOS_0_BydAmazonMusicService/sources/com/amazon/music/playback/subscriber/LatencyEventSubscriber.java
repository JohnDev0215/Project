package com.amazon.music.playback.subscriber;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.LatencyInfoEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class LatencyEventSubscriber extends EventSubscriber {
    private final Logger LOG;

    public LatencyEventSubscriber(MetricsManager metricsManager) {
        super(metricsManager);
        this.LOG = LoggerFactory.getLogger(LatencyEventSubscriber.class.getSimpleName());
    }

    @Subscribe
    public void onEvent(LatencyInfoEvent latencyInfoEvent) {
        sendEvent(latencyInfoEvent);
    }

    private void sendEvent(MTSEvent mTSEvent) {
        if (mTSEvent == null) {
            this.LOG.warn("Not reporting a metric from " + LatencyEventSubscriber.class.getSimpleName() + " because it is null");
            return;
        }
        MetricsHolder.getManager().handleEvent(mTSEvent);
    }
}
