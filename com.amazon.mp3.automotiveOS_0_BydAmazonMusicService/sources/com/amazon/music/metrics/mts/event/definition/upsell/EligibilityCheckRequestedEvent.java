package com.amazon.music.metrics.mts.event.definition.upsell;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class EligibilityCheckRequestedEvent extends MTSEvent {
    public EligibilityCheckRequestedEvent(String str, String str2, String str3, String str4, String str5) {
        super("eligibilityCheckRequested", 3L);
        addAttribute("responseReason", str5);
        addAttribute("checkType", str);
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, str2);
        addAttribute(ContextMappingConstants.ENTITY_ID, str3);
        addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, str4);
    }
}
