package com.ts.carplay.app.service.proxy.common;

import android.os.HandlerThread;
import android.os.Looper;
import com.ts.carplay.app.service.common.IProxy;
import com.ts.carplay.app.service.common.IProxyFactory;
import com.ts.carplay.app.service.proxy.media.MediaProxy;
import com.ts.carplay.app.service.proxy.navigation.NavigationProxy;
import com.ts.carplay.app.service.proxy.telephony.TelephonyProxy;

/* JADX INFO: loaded from: classes.dex */
public class ProxyFactory implements IProxyFactory {
    private static final String THREAD_NAME = "ProxyFactory";
    private HandlerThread mHandlerThread;
    private Looper mLooper;
    private IProxy[] mProxies = {new MediaProxy(getLooper()), new NavigationProxy(getLooper()), new TelephonyProxy(getLooper())};

    @Override // com.ts.carplay.app.service.common.IProxyFactory
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
