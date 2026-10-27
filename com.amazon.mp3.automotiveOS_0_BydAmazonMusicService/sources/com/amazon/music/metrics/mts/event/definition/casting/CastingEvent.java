package com.amazon.music.metrics.mts.event.definition.casting;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CastingEvent extends MTSEvent {
    public CastingEvent(String str, long j, String str2, long j2) {
        super(str, j);
        addAttribute("castingProtocol", str2);
        addAttribute("castingVersion", j2);
    }
}
