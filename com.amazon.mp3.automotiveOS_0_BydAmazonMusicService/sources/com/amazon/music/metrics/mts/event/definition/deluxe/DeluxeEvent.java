package com.amazon.music.metrics.mts.event.definition.deluxe;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
abstract class DeluxeEvent extends MTSEvent {
    public DeluxeEvent(String str, String str2, String str3) {
        super(str);
        addEventAttributes(str2, str3);
    }

    private void addEventAttributes(String str, String str2) {
        addAttribute("cdObjectId", str);
        addAttribute("asin", str2);
    }
}
