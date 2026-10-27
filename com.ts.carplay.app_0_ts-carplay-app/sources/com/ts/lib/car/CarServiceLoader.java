package com.ts.lib.car;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class CarServiceLoader {
    private final CarConnectionCallbackProxy mCallback;
    private final Context mContext;
    private final Handler mEventHandler;

    public static abstract class CarConnectionCallbackProxy {
        public abstract void onConnected();

        public abstract void onDisconnected();
    }

    public abstract void connect() throws IllegalStateException;

    public abstract void disconnect();

    public abstract int getCarConnectionType() throws CarNotConnectedException;

    public abstract Object getCarManager(String str) throws CarNotConnectedException;

    public abstract boolean isConnected();

    public CarServiceLoader(Context context, CarConnectionCallbackProxy callback, Handler handler) {
        this.mContext = context;
        this.mCallback = callback;
        this.mEventHandler = handler;
    }

    protected Context getContext() {
        return this.mContext;
    }

    protected CarConnectionCallbackProxy getConnectionCallback() {
        return this.mCallback;
    }

    protected Handler getEventHandler() {
        return this.mEventHandler;
    }
}
