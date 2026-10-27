package com.amazon.music.metrics.mts.event.definition.casting;

import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDeviceDisconnectedEvent extends CastingEvent {
    public CastingDeviceDisconnectedEvent(String str, String str2, String str3, String str4, TerminationReason terminationReason, long j) {
        super("castingTerminated", 2L, str4, j);
        if (str == null) {
            addAttribute("subDeviceName", "");
        } else {
            addAttribute("subDeviceName", str);
        }
        if (str2 != null) {
            addAttribute("targetDeviceModel", str2);
        }
        if (str3 != null) {
            addAttribute("targetDeviceType", str3);
        }
        addAttribute("terminationReason", terminationReason.getMetricValue());
    }
}
