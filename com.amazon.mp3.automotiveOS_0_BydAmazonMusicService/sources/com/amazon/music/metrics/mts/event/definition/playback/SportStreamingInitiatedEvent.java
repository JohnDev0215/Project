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

/* JADX INFO: loaded from: classes4.dex */
public class SportStreamingInitiatedEvent extends MTSEvent {
    public SportStreamingInitiatedEvent(String str, EntityIdType entityIdType, EntityType entityType, String str2, MediaType mediaType, String str3, String str4, long j, InitiationReason initiationReason, BitRate bitRate, MediaPlayerType mediaPlayerType, String str5) {
        super("sportStreamingInitiated", 2L);
        addAttribute(ContextMappingConstants.ENTITY_ID, str);
        addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, entityIdType != null ? entityIdType.getMetricsValue() : "");
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, entityType);
        addAttribute("mediaContentId", str2);
        addAttribute("mediaType", mediaType);
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str3);
        addAttribute("contentSubscriptionMode", str4 != null ? str4 : "");
        addAttribute("initialPlaybackDelayMilliseconds", j);
        addAttribute("initiationReason", initiationReason != null ? initiationReason.getMetricsValue() : "");
        addAttribute("bitrates", bitRate != null ? bitRate.getMetricsValue() : "");
        addAttribute("streamOrDRMTech", mediaPlayerType != null ? mediaPlayerType.getMetricsValue() : "");
        addAttribute("mediaPlayerName", str5);
    }
}
