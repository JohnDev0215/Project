package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmonaut.Converter;
import com.spotify.cosmos.rxrouter.RxRouter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CosmonautHandler {
    private final ServiceMethodFactory serviceMethodFactory;

    public CosmonautHandler(List<? extends Converter.Factory> list) {
        v03.j(list, "factories");
        this.serviceMethodFactory = new ServiceMethodFactory(list);
    }

    public final <T> T create(Class<T> cls, final RxRouter rxRouter) {
        v03.j(cls, "service");
        v03.j(rxRouter, "rxRouter");
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.spotify.cosmos.cosmonautatoms.CosmonautHandler.create.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                v03.j(obj, "o");
                v03.j(method, "method");
                return CosmonautHandler.this.handle$src_main_java_com_spotify_cosmos_cosmonautatoms_cosmonautatoms(rxRouter, method, objArr);
            }
        });
    }

    public final Object handle$src_main_java_com_spotify_cosmos_cosmonautatoms_cosmonautatoms(RxRouter rxRouter, Method method, Object[] objArr) {
        v03.j(rxRouter, "rxRouter");
        v03.j(method, "method");
        return v03.b(method.getDeclaringClass(), Object.class) ? method.invoke(this, objArr) : this.serviceMethodFactory.loadServiceMethod(method).create(rxRouter, objArr);
    }
}
