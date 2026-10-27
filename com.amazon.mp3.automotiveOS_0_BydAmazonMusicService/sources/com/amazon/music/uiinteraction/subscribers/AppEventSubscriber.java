package com.amazon.music.uiinteraction.subscribers;

import com.amazon.music.events.ContentViewedAppEvent;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.uiinteraction.mapping.LocalMapper;
import com.amazon.music.uiinteraction.mapping.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AppEventSubscriber {
    private final Logger LOG = LoggerFactory.getLogger(AppEventSubscriber.class.getSimpleName());
    private final ContentViewDedupeCache contentViewCache = new ContentViewDedupeCache();
    private final Mapper mapper;

    public AppEventSubscriber(ObjectMapper objectMapper, InputStream inputStream) {
        this.mapper = new LocalMapper(objectMapper, inputStream);
    }

    @Subscribe
    public void onEvent(ContentViewedAppEvent contentViewedAppEvent) {
        if (this.contentViewCache.add(contentViewedAppEvent)) {
            sendEvent(this.mapper.mapUiContentView(contentViewedAppEvent));
        } else {
            this.LOG.debug("Skipping duplicate content view event.");
        }
    }

    @Subscribe
    public void onEvent(UserInteractionAppEvent userInteractionAppEvent) {
        sendEvent(this.mapper.mapUiClick(userInteractionAppEvent));
    }

    @Subscribe
    public void onEvent(NavigationAppEvent navigationAppEvent) {
        sendEvent(this.mapper.mapUiPageView(navigationAppEvent));
    }

    private void sendEvent(MTSEvent mTSEvent) {
        if (mTSEvent != null) {
            MetricsHolder.getManager().handleEvent(mTSEvent);
        } else {
            this.LOG.warn("Not reporting a metric because it is null");
        }
    }
}
