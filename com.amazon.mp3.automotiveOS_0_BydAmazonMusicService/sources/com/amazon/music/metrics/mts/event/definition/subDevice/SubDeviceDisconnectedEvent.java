package com.amazon.music.metrics.mts.event.definition.subDevice;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class SubDeviceDisconnectedEvent extends MTSEvent {
    public SubDeviceDisconnectedEvent(String str, String str2) {
        super("subDeviceDisconnected", 1L);
        addAttribute("subDeviceType", str);
        addAttribute("playbackInstanceId", str2);
    }
}
