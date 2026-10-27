package com.amazon.mp3.ftu;

import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;

/* JADX INFO: loaded from: classes3.dex */
public class FTUMetricsSingleton {
    private static volatile FTUMetricsSingleton instance;
    private int numMusicStoreEventsSent = 0;

    public static FTUMetricsSingleton get() {
        if (instance == null) {
            instance = new FTUMetricsSingleton();
        }
        return instance;
    }

    public void sendFlexEventUserLaunchedApp() {
        sendFlexEvent(FlexEvent.builder("userLaunchedApp").build());
    }

    public void sendFlexEventUserEnteredMusicStore() {
        if (this.numMusicStoreEventsSent < 1) {
            sendFlexEvent(FlexEvent.builder("userEnteredMusicStore").build());
            this.numMusicStoreEventsSent++;
        }
    }

    private void sendFlexEvent(FlexEvent flexEvent) {
        MetricsLogger.sendEvent(flexEvent);
    }
}
