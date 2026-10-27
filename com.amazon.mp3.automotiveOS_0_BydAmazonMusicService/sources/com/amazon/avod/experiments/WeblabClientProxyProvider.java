package com.amazon.avod.experiments;

import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes2.dex */
public class WeblabClientProxyProvider {

    @GuardedBy("mClientProxyLock")
    private WeblabClientProxy mClientProxy;
    private final InitializationLatch mInitLatch = new InitializationLatch(this);
    private final Object mClientProxyLock = new Object();

    private static final class Holder {
        static final WeblabClientProxyProvider INSTANCE = new WeblabClientProxyProvider();

        private Holder() {
        }
    }

    public static WeblabClientProxyProvider getInstance() {
        return Holder.INSTANCE;
    }

    WeblabClientProxyProvider() {
    }

    public void initialize(@Nonnull WeblabClientProxy weblabClientProxy) {
        this.mInitLatch.start(30L, TimeUnit.SECONDS);
        synchronized (this.mClientProxyLock) {
            this.mClientProxy = (WeblabClientProxy) Preconditions.checkNotNull(weblabClientProxy, "clientProxy");
        }
        this.mInitLatch.complete();
    }

    public boolean isInitialized() {
        return this.mInitLatch.isInitialized();
    }

    @Nonnull
    public WeblabClientProxy getClientProxy() {
        WeblabClientProxy weblabClientProxy;
        synchronized (this.mClientProxyLock) {
            weblabClientProxy = this.mClientProxy;
        }
        return weblabClientProxy;
    }
}
