package com.bumptech.glide.manager;

/* JADX INFO: loaded from: classes5.dex */
public interface ConnectivityMonitor extends LifecycleListener {

    public interface ConnectivityListener {
        void onConnectivityChanged(boolean z);
    }
}
