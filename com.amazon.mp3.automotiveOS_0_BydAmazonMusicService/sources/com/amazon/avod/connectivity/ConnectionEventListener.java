package com.amazon.avod.connectivity;

import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ConnectionEventListener extends ConnectionChangeListener {
    private ExecutorService mExecutor;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);

    protected abstract void onDisconnected();

    protected abstract void onWanConnected();

    protected abstract void onWifiConnected();

    @Override // com.amazon.avod.connectivity.ConnectionChangeListener
    public synchronized void initialize(@Nonnull ExecutorService executorService) {
        if (this.mInitializationLatch.isInitialized()) {
            return;
        }
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mInitializationLatch.complete();
    }

    @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
    public final void onConnectionChange(@Nonnull DetailedNetworkInfo detailedNetworkInfo, @Nonnull DetailedNetworkInfo detailedNetworkInfo2) {
        this.mInitializationLatch.checkInitialized();
        if (detailedNetworkInfo2.hasWifiConnection() && detailedNetworkInfo2.hasFullNetworkAccess()) {
            runWifiConnectedEvent();
            return;
        }
        if (detailedNetworkInfo2.hasWanConnection() && detailedNetworkInfo2.hasFullNetworkAccess()) {
            runWanConnectedEvent();
        } else {
            if (detailedNetworkInfo2.hasFullNetworkAccess()) {
                return;
            }
            runDisconnectedEvent();
        }
    }

    public void runWifiConnectedEvent() {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.connectivity.ConnectionEventListener.1WifiConnectedEvent
            @Override // java.lang.Runnable
            public void run() {
                ConnectionEventListener.this.onWifiConnected();
            }
        });
    }

    public void runWanConnectedEvent() {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.connectivity.ConnectionEventListener.1WanConnectedEvent
            @Override // java.lang.Runnable
            public void run() {
                ConnectionEventListener.this.onWanConnected();
            }
        });
    }

    public void runDisconnectedEvent() {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.connectivity.ConnectionEventListener.1DisconnectedEvent
            @Override // java.lang.Runnable
            public void run() {
                ConnectionEventListener.this.onDisconnected();
            }
        });
    }
}
