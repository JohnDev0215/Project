package com.ts.carplay.lib.common;

import com.ts.carplay.linkadapter.device.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public interface EventListener {
    void onConnected(LinkDevice linkDevice);

    void onDisconnected(LinkDevice linkDevice);

    void onSessionDestoryed(LinkDevice linkDevice);

    void onSessionEstablished(LinkDevice linkDevice);
}
