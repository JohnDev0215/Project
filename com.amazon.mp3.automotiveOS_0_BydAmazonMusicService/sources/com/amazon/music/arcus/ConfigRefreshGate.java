package com.amazon.music.arcus;

/* JADX INFO: loaded from: classes4.dex */
interface ConfigRefreshGate {

    public enum ConfigRefreshGateState {
        REFRESH_BLOCKED,
        REFRESH_NEEDED,
        REFRESH_ALLOWED
    }

    ConfigRefreshGateState getGateState();
}
