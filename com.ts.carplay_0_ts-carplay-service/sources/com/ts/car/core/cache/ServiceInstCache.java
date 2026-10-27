package com.ts.car.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ServiceInstCache {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ServiceInstCache gInstance;
    private Map<Class<?>, Object> mServiceInstCache = new ConcurrentHashMap();

    public static synchronized ServiceInstCache getInstance() {
        if (gInstance == null) {
            gInstance = new ServiceInstCache();
        }
        return gInstance;
    }

    private ServiceInstCache() {
    }

    public Object getServiceInstance(Class<?> clazz) {
        if (!this.mServiceInstCache.containsKey(clazz)) {
            return null;
        }
        return this.mServiceInstCache.get(clazz);
    }

    public void setServiceInstance(Class<?> clazz, Object instance) {
        this.mServiceInstCache.put(clazz, instance);
    }
}
