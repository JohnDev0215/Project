package com.ecarx.xui.adaptapi.policy;

/* JADX INFO: loaded from: classes2.dex */
public interface IActivityWatcherPolicy {
    void registerActivityWatcher(IActivityWatcherCallback iActivityWatcherCallback);

    void unregisterActivityWatcher(IActivityWatcherCallback iActivityWatcherCallback);
}
