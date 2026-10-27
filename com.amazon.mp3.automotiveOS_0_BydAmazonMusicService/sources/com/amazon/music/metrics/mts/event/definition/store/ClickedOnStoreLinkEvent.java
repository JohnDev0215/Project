package com.amazon.music.metrics.mts.event.definition.store;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;

/* JADX INFO: loaded from: classes4.dex */
public class ClickedOnStoreLinkEvent extends MTSEvent {
    public ClickedOnStoreLinkEvent() {
        super("clickedOnStoreLink");
    }

    public ClickedOnStoreLinkEvent(StoreLinkType storeLinkType) {
        this();
        addEventAttributes(storeLinkType);
    }

    private void addEventAttributes(StoreLinkType storeLinkType) {
        if (storeLinkType != null) {
            addAttribute("linkType", storeLinkType.getMetricValue());
        }
    }
}
