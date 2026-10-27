package com.spotify.cosmos.servicebasedrouter;

import com.spotify.cosmos.rxrouter.RxRouter;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class RxRouterClient {
    private Listener listener;
    private boolean started;

    public interface Listener {
        void onConnected(RxRouter rxRouter);

        void onDisconnected();
    }

    public void connect() {
        if (!(!this.started)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.started = true;
    }

    public void disconnect() {
        if (!this.started) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.started = false;
        notifyOnDisconnected();
    }

    public final boolean isStarted() {
        return this.started;
    }

    public void notifyOnConnected(RxRouter rxRouter) {
        v03.j(rxRouter, "rxRouter");
        Listener listener = this.listener;
        if (listener != null) {
            listener.onConnected(rxRouter);
        }
    }

    public void notifyOnDisconnected() {
        Listener listener = this.listener;
        if (listener != null) {
            listener.onDisconnected();
        }
    }

    public void setListener(Listener listener) {
        v03.j(listener, "l");
        this.listener = listener;
    }
}
