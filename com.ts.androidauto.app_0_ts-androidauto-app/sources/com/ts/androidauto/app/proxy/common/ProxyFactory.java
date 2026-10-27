package com.ts.androidauto.app.proxy.common;

import android.os.HandlerThread;
import android.os.Looper;
import com.ts.androidauto.app.common.IProxy;
import com.ts.androidauto.app.common.IProxyFactory;
import com.ts.androidauto.app.proxy.dialog.DialogProxy;
import com.ts.androidauto.app.proxy.media.MediaProxy;
import com.ts.androidauto.app.proxy.navigation.NavigationProxy;

/* JADX INFO: loaded from: classes2.dex */
public class ProxyFactory implements IProxyFactory {
    private static final String THREAD_NAME = "ProxyFactory";
    private HandlerThread mHandlerThread;
    private Looper mLooper;
    private IProxy[] mProxies = {new MediaProxy(getLooper()), new NavigationProxy(getLooper()), new DialogProxy(getLooper())};

    @Override // com.ts.androidauto.app.common.IProxyFactory
    public IProxy[] createProxies() {
        return this.mProxies;
    }

    private synchronized Looper getLooper() {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mLooper = this.mHandlerThread.getLooper();
        }
        return this.mLooper;
    }
}
