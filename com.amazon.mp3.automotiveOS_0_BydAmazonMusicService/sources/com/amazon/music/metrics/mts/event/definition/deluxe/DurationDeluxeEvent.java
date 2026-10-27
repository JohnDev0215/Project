package com.amazon.music.metrics.mts.event.definition.deluxe;

import com.amazon.music.metrics.mts.event.EventTimeConverter;

/* JADX INFO: loaded from: classes4.dex */
abstract class DurationDeluxeEvent extends DeluxeEvent {
    public DurationDeluxeEvent(String str, String str2, String str3, long j) {
        super(str, str2, str3);
        addEventAttributes(j);
    }

    private void addEventAttributes(long j) {
        addAttribute("durationSeconds", new EventTimeConverter().convertToRoundedSeconds(j));
    }
}
