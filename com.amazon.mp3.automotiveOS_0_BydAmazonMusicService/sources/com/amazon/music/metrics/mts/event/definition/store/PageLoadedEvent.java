package com.amazon.music.metrics.mts.event.definition.store;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.StorePageType;

/* JADX INFO: loaded from: classes4.dex */
public class PageLoadedEvent extends MTSEvent {
    public PageLoadedEvent(StorePageType storePageType, long j) {
        super("pageLoaded");
        addEventAttributes(storePageType, j);
    }

    private void addEventAttributes(StorePageType storePageType, long j) {
        addAttribute("pageType", storePageType.getMetricValue());
        addAttribute("loadTimeMilliseconds", j);
    }
}
