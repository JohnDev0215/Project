package com.amazon.music.metrics.mts.event.definition.casting;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDeviceConnectFailedEvent extends CastingEvent {
    public CastingDeviceConnectFailedEvent(String str, CastingConnectFailedType castingConnectFailedType, long j) {
        super("castingConnectFailed", 2L, str, j);
        addAttribute("castingConnectFailedType", castingConnectFailedType.name());
    }
}
