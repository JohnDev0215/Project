package com.amazon.avod.events.proxy;

import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes2.dex */
public class RetroactiveEventListenerProxyFactory {
    public static <T> T createProxy(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new EventListenerProxy());
    }
}
