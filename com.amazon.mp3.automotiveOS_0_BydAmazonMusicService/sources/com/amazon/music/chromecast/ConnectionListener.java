package com.amazon.music.chromecast;

/* JADX INFO: loaded from: classes4.dex */
public interface ConnectionListener {
    void onConnected();

    void onDevicesAvailable();

    void onDisconnected();
}
