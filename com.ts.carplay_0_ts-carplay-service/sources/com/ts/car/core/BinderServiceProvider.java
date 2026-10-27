package com.ts.car.core;

import android.content.Context;
import android.os.IBinder;
import com.ts.car.core.cache.ServiceInstCache;
import com.ts.car.core.provider.IServiceProvider;
import com.ts.car.core.provider.ServiceProviderFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class BinderServiceProvider {
    public static <T> T get(Class<T> cls, Context context, IBinder iBinder) {
        if (cls == null) {
            return null;
        }
        ServiceInstCache serviceInstCache = ServiceInstCache.getInstance();
        Object serviceInstance = serviceInstCache.getServiceInstance(cls);
        if (serviceInstance != null) {
            return cls.cast(serviceInstance);
        }
        IServiceProvider serviceProvider = ServiceProviderFactory.getServiceProvider();
        if (serviceProvider == null) {
            return null;
        }
        T t = (T) serviceProvider.getService(cls, context, iBinder);
        if (t != null) {
            serviceInstCache.setServiceInstance(cls, t);
        }
        return t;
    }
}
