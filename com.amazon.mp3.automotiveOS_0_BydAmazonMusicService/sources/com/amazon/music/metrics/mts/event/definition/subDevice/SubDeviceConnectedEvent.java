package com.amazon.music.metrics.mts.event.definition.subDevice;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class SubDeviceConnectedEvent extends MTSEvent {
    public SubDeviceConnectedEvent(String str, String str2) {
        super("subDeviceConnected", 1L);
        addAttribute("subDeviceType", str);
        addAttribute("playbackInstanceId", str2);
    }
}
