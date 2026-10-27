package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.EntityType;
import com.amazon.music.metrics.mts.event.types.InitiationReason;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.MediaType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class SportStreamingInitiationFailedEvent extends MTSEvent {
    public SportStreamingInitiationFailedEvent(String str, EntityIdType entityIdType, EntityType entityType, String str2, MediaType mediaType, String str3, String str4, BitRate bitRate, String str5, InitiationReason initiationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, String str6) {
        super("sportStreamingInitiationFailed", 2L);
        addAttribute(ContextMappingConstants.ENTITY_ID, str);
        addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, entityIdType != null ? entityIdType.getMetricsValue() : "");
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, entityType);
        addAttribute("mediaContentId", str2);
        addAttribute("mediaType", mediaType);
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str3);
        addAttribute("contentSubscriptionMode", str4 != null ? str4 : "");
        addAttribute("bitrates", bitRate != null ? bitRate.getMetricsValue() : "");
        addAttribute("errorMessage", str5);
        addAttribute("initiationReason", initiationReason != null ? initiationReason.getMetricsValue() : "");
        addAttribute("selectionSourceId", selectionSourceInfo != null ? selectionSourceInfo.getSelectionSourceId() : "");
        addAttribute("selectionSourceIdType", (selectionSourceInfo == null || selectionSourceInfo.getSelectionSourceType() == null) ? "" : selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("streamOrDRMTech", mediaPlayerType != null ? mediaPlayerType.getMetricsValue() : "");
        addAttribute("mediaPlayerName", str6);
    }
}
