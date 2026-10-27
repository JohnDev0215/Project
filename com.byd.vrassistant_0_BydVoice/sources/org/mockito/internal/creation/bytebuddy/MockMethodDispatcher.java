package org.mockito.internal.creation.bytebuddy;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: com.byd.vrassistant_0_BydVoice.apk:org/mockito/internal/creation/bytebuddy/MockMethodDispatcher.raw */
public abstract class MockMethodDispatcher {
    private static final ConcurrentMap<String, MockMethodDispatcher> INSTANCE = new ConcurrentHashMap();

    public abstract Callable<?> handle(Object obj, Method method, Object[] objArr) throws Throwable;

    public abstract boolean isMock(Object obj);

    public abstract boolean isMocked(Object obj);

    public abstract boolean isOverridden(Object obj, Method method);

    public static MockMethodDispatcher get(String identifier, Object mock) {
        if (mock == INSTANCE) {
            return null;
        }
        return INSTANCE.get(identifier);
    }

    public static void set(String identifier, MockMethodDispatcher dispatcher) {
        INSTANCE.putIfAbsent(identifier, dispatcher);
    }
}
