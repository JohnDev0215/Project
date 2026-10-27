package com.amazon.avod.servicetypes;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceTypesProxy {
    private AtomicBoolean mIsInitialized = new AtomicBoolean(false);
    private ServiceTypesProxyDelegate mServiceTypesProxyDelegate;

    public interface ServiceTypesProxyDelegate {
        Object beginTrace(@Nullable String str);

        Object beginTrace(@Nullable String str, @Nullable Object obj);

        void endTrace(@Nullable Object obj);

        void exception(@Nonnull Throwable th, @Nullable String str, @Nullable Object... objArr);
    }

    static class SingletonHolder {
        static final ServiceTypesProxy INSTANCE = new ServiceTypesProxy();

        SingletonHolder() {
        }
    }

    @Nonnull
    public static ServiceTypesProxy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    ServiceTypesProxy() {
    }

    public void initialize(@Nonnull ServiceTypesProxyDelegate serviceTypesProxyDelegate) {
        synchronized (this) {
            boolean z = true;
            if (this.mIsInitialized.getAndSet(true)) {
                z = false;
            }
            Preconditions.checkState(z, "Initialization of %s cannot be invoked more than once", getClass().getSimpleName());
            this.mServiceTypesProxyDelegate = (ServiceTypesProxyDelegate) Preconditions.checkNotNull(serviceTypesProxyDelegate, "serviceTypesProxyDelegate");
        }
    }

    @Nullable
    public Object beginTrace(@Nullable String str) {
        if (this.mIsInitialized.get()) {
            return this.mServiceTypesProxyDelegate.beginTrace(str);
        }
        return null;
    }

    @Nullable
    public Object beginTrace(@Nullable String str, @Nullable Object obj) {
        if (this.mIsInitialized.get()) {
            return this.mServiceTypesProxyDelegate.beginTrace(str, obj);
        }
        return null;
    }

    public void endTrace(@Nullable Object obj) {
        if (this.mIsInitialized.get()) {
            this.mServiceTypesProxyDelegate.endTrace(obj);
        }
    }

    public void exception(@Nonnull Throwable th, @Nullable String str, @Nullable Object... objArr) {
        if (this.mIsInitialized.get()) {
            this.mServiceTypesProxyDelegate.exception(th, str, objArr);
        }
    }
}
