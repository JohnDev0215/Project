package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.rxrouter.RxRouter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceMethodImpl implements ServiceMethod {
    private final RequestAtom requestAtom;
    private final ReturnTypeConverter returnTypeConverter;
    private final ResponseTransformer returnTypeTransformer;

    public ServiceMethodImpl(Method method, RequestTransformers requestTransformers, ResponseTransformers responseTransformers) {
        v03.j(method, "method");
        v03.j(requestTransformers, "requestTransformers");
        v03.j(responseTransformers, "typeTransformers");
        this.requestAtom = new RequestAtom(method, requestTransformers);
        Type genericReturnType = method.getGenericReturnType();
        v03.i(genericReturnType, "method.getGenericReturnType()");
        this.returnTypeTransformer = responseTransformers.create(genericReturnType);
        this.returnTypeConverter = ReturnTypeConverters.Companion.find(method);
    }

    @Override // com.spotify.cosmos.cosmonautatoms.ServiceMethod
    public Object create(RxRouter rxRouter, Object[] objArr) {
        v03.j(rxRouter, "resolver");
        return this.returnTypeConverter.convert(this.returnTypeTransformer, rxRouter.resolve(this.requestAtom.call(objArr)));
    }
}
