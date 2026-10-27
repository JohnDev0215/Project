package com.amazon.music.playback.subscriber;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class SubscriberExceptionEventSubscriber {
    private static String TAG = "SubscriberExceptionEventSubscriber";
    private final Logger slf4jLogger = LoggerFactory.getLogger(TAG);

    @Subscribe
    public void onEvent(SubscriberExceptionEvent subscriberExceptionEvent) {
        this.slf4jLogger.error("SubscriberExceptionEvent", subscriberExceptionEvent.throwable);
    }
}
