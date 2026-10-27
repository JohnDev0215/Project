package com.amazon.mp3.cloudqueue.metrics;

import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;

/* JADX INFO: loaded from: classes3.dex */
public class CloudQueueUIMetricsManager {
    private static volatile CloudQueueUIMetricsManager instance;

    public static CloudQueueUIMetricsManager get() {
        if (instance == null) {
            instance = new CloudQueueUIMetricsManager();
        }
        return instance;
    }

    public void sendNowPlayingAdUIContentViewEvent(AdMediaItem adMediaItem) {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.NOW_PLAYING).withBlockRef(adMediaItem.getAdBlockRef()).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    public void sendMiniPlayerAdUIContentViewEvent(AdMediaItem adMediaItem) {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.MINI_TRANSPORT).withBlockRef(adMediaItem.getAdBlockRef()).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    public void sendAdClickingEvent(AdMediaItem adMediaItem) {
        UserInteractionAppEvent.builder("clickAdImage").withInteractionType(InteractionType.TAP).withBlockRef(adMediaItem.getAdBlockRef()).publish();
    }

    public void sendAdSkipClickingEvent(AdMediaItem adMediaItem) {
        UserInteractionAppEvent.builder("clickSkipInAd").withBlockRef(adMediaItem.getAdBlockRef()).withInteractionType(InteractionType.TAP).publish();
    }
}
