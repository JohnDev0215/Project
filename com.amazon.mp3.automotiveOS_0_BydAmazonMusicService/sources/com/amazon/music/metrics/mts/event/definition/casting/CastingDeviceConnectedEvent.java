package com.amazon.music.metrics.mts.event.definition.casting;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDeviceConnectedEvent extends CastingEvent {
    public CastingDeviceConnectedEvent(String str, String str2, String str3, String str4, String str5, long j, long j2) {
        super("castingInitiated", 2L, str4, j2);
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
        if (str5 != null) {
            addAttribute("castingConnectionType", str5);
        }
        addAttribute("castingConnectedLatencyInMS", j);
    }
}
