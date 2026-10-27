package com.amazon.music.events;

import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AppEventPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(AppEventPublisher.class.getSimpleName());

    public void publish(AppEvent appEvent) {
        LOG.debug("Received an AppEvent");
        EventBus.getDefault().post(appEvent);
    }
}
