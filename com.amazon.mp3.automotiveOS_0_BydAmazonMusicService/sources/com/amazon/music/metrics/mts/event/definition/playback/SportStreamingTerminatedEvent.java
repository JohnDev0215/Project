package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.EntityIdType;
import com.amazon.music.metrics.mts.event.types.EntityType;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.MediaType;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class SportStreamingTerminatedEvent extends MTSEvent {
    public SportStreamingTerminatedEvent(String str, EntityIdType entityIdType, EntityType entityType, String str2, MediaType mediaType, String str3, String str4, BitRate bitRate, long j, MediaPlayerType mediaPlayerType, CacheHitStatus cacheHitStatus, TerminationReason terminationReason, long j2, long j3, String str5) {
        super("sportStreamingTerminated", 2L);
        addAttribute(ContextMappingConstants.ENTITY_ID, str);
        addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, entityIdType != null ? entityIdType.getMetricsValue() : "");
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, entityType);
        addAttribute("mediaContentId", str2);
        addAttribute("mediaType", mediaType);
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str3);
        addAttribute("contentSubscriptionMode", str4 != null ? str4 : "");
        addAttribute("bitrates", bitRate != null ? bitRate.getMetricsValue() : "");
        addAttribute("transferSpeedBPS", j);
        addAttribute("streamOrDRMTech", mediaPlayerType != null ? mediaPlayerType.getMetricsValue() : "");
        addAttribute("cacheHitStatus", cacheHitStatus != null ? cacheHitStatus.getMetricsValue() : "");
        addAttribute("terminationReason", terminationReason != null ? terminationReason.getMetricsValue() : "");
        addAttribute("durationSeconds", j2);
        addAttribute("rebufferCount", j3);
        addAttribute("mediaPlayerName", str5);
    }
}
