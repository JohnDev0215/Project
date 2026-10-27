package com.amazon.avod.events.proxy;

import android.os.Handler;
import android.os.Looper;
import com.google.common.base.Preconditions;
import java.lang.reflect.Proxy;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class HandlerBasedListenerProxyFactory {
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    @Nonnull
    public <T> T createUiProxy(@Nonnull Class<T> cls) {
        return (T) createProxy(cls, this.mMainThreadHandler);
    }

    @Nonnull
    public <T> T createUiProxy(@Nonnull Class<T> cls, @Nonnull T t) {
        return (T) createProxy(cls, t, this.mMainThreadHandler);
    }

    @Nonnull
    public <T> T createProxy(@Nonnull Class<T> cls, @Nonnull T t, @Nonnull Handler handler) {
        T t2 = (T) createProxy(cls, handler);
        EventListenerProxy.addListener(t2, t);
        return t2;
    }

    public static <T> T createProxy(@Nonnull Class<T> cls, @Nonnull Handler handler) {
        Preconditions.checkNotNull(handler);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new EventListenerProxy(handler));
    }
}
