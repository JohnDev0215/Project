package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmonaut.Converter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceMethodFactory {
    private final RequestTransformers requestTransformers;
    private final ResponseTransformers responseTransformers;
    private final ConcurrentHashMap<Method, ServiceMethod> serviceMethodCache;

    public ServiceMethodFactory(List<? extends Converter.Factory> list) {
        v03.j(list, "factories");
        this.serviceMethodCache = new ConcurrentHashMap<>();
        this.requestTransformers = new RequestTransformers(list);
        this.responseTransformers = new ResponseTransformers(list);
    }

    public final ServiceMethod loadServiceMethod(Method method) {
        v03.j(method, "method");
        ServiceMethod serviceMethod = this.serviceMethodCache.get(method);
        if (serviceMethod != null) {
            return serviceMethod;
        }
        synchronized (this.serviceMethodCache) {
            ServiceMethod serviceMethod2 = this.serviceMethodCache.get(method);
            if (serviceMethod2 != null) {
                return serviceMethod2;
            }
            ServiceMethodImpl serviceMethodImpl = new ServiceMethodImpl(method, this.requestTransformers, this.responseTransformers);
            this.serviceMethodCache.put(method, serviceMethodImpl);
            return serviceMethodImpl;
        }
    }
}
