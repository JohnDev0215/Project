package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NetworkStatusProvider {
    public abstract boolean getNetworkReachability();

    public abstract String getNetworkSource();

    public abstract void initialize(NetworkReachabilityNotifier networkReachabilityNotifier);

    public abstract void shutdown();
}
