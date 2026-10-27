package com.amazon.music.playback.subscriber;

import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.playback.event.HLSRequiredRebufferingEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class HLSRequiredRebufferingEventSubscriber extends EventSubscriber {
    private static String TAG = "HLSRequiredRebufferingEventSubscriber";
    private final Logger slf4jLogger;

    public HLSRequiredRebufferingEventSubscriber(MetricsManager metricsManager) {
        super(metricsManager);
        this.slf4jLogger = LoggerFactory.getLogger(TAG);
    }

    @Subscribe
    public void onEvent(HLSRequiredRebufferingEvent hLSRequiredRebufferingEvent) throws Exception {
        throw null;
    }
}
