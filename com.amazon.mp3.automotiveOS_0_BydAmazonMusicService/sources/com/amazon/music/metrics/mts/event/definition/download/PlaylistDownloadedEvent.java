package com.amazon.music.metrics.mts.event.definition.download;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistDownloadedEvent extends MTSEvent {
    public PlaylistDownloadedEvent(String str, String str2, String str3) {
        super("playlistDownloaded", 1L);
        addEventAttributes(str, str2, str3);
    }

    private void addEventAttributes(String str, String str2, String str3) {
        addAttribute("asin", str);
        addAttribute(ContextMappingConstants.ENTITY_ID, str);
        addAttribute("contentSubscriptionMode", str2);
        if (str3 != null) {
            addAttribute("subContentSubscriptionMode", str3);
        }
    }
}
