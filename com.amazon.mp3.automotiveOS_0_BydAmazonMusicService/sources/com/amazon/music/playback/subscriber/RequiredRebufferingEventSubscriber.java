package com.amazon.music.playback.subscriber;

import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.playback.event.RequiredRebufferingEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class RequiredRebufferingEventSubscriber extends EventSubscriber {
    private static String TAG = "RequiredRebufferingEventSubscriber";
    private final Logger slf4jLogger;

    public RequiredRebufferingEventSubscriber(MetricsManager metricsManager) {
        super(metricsManager);
        this.slf4jLogger = LoggerFactory.getLogger(TAG);
    }

    @Subscribe
    public void onEvent(RequiredRebufferingEvent requiredRebufferingEvent) throws CirrusInvalidDataException {
        throw null;
    }
}
