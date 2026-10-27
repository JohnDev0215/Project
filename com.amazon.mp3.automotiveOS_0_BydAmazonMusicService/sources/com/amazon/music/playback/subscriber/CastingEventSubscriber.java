package com.amazon.music.playback.subscriber;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.casting.CastingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.casting.CastingTerminatedEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class CastingEventSubscriber extends EventSubscriber {
    private final Logger LOG;

    public CastingEventSubscriber(MetricsManager metricsManager) {
        super(metricsManager);
        this.LOG = LoggerFactory.getLogger(CastingEventSubscriber.class.getSimpleName());
    }

    @Subscribe
    public void onEvent(CastingInitiatedEvent castingInitiatedEvent) {
        sendEvent(castingInitiatedEvent);
    }

    @Subscribe
    public void onEvent(CastingTerminatedEvent castingTerminatedEvent) {
        sendEvent(castingTerminatedEvent);
    }

    private void sendEvent(MTSEvent mTSEvent) {
        if (mTSEvent != null) {
            MetricsHolder.getManager().handleEvent(mTSEvent);
        } else {
            this.LOG.warn("Not reporting a metric because it is null");
        }
    }
}
