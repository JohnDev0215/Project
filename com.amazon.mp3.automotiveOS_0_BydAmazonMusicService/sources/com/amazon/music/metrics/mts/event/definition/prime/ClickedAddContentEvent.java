package com.amazon.music.metrics.mts.event.definition.prime;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class ClickedAddContentEvent extends MTSEvent {
    public ClickedAddContentEvent(LinkType linkType, String str, SelectionSourceInfo selectionSourceInfo, ResourceType resourceType, String str2, String str3) {
        super("clickedAddContent", 1L);
        addEventAttributes(linkType, str, selectionSourceInfo, resourceType, str2, str3);
    }

    private void addEventAttributes(LinkType linkType, String str, SelectionSourceInfo selectionSourceInfo, ResourceType resourceType, String str2, String str3) {
        addAttribute("linkType", linkType.getMetricsValue());
        addAttribute("asin", str);
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("resourceType", resourceType.getMetricsValue());
        addAttribute("selectionSourceSessionId", selectionSourceInfo.getSelectionSourceSessionId());
        addAttribute("contentSubscriptionMode", str2);
        if (str3 != null) {
            addAttribute("subContentSubscriptionMode", str3);
        }
    }
}
