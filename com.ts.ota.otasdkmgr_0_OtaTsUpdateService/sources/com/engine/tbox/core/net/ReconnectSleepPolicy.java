package com.engine.tbox.core.net;

/* JADX INFO: loaded from: classes.dex */
public interface ReconnectSleepPolicy {
    default long sleepTime(int count) {
        return 1000L;
    }
}
