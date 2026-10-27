package com.google.gson.internal.k;

import com.google.gson.internal.k.k;
import com.google.gson.p;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
final class m<T> extends p<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.gson.d f4104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p<T> f4105b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Type f4106c;

    m(com.google.gson.d dVar, p<T> pVar, Type type) {
        this.f4104a = dVar;
        this.f4105b = pVar;
        this.f4106c = type;
    }

    private Type e(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }

    @Override // com.google.gson.p
    public T b(com.google.gson.stream.a aVar) throws IOException {
        return this.f4105b.b(aVar);
    }

    @Override // com.google.gson.p
    public void d(com.google.gson.stream.b bVar, T t) throws IOException {
        p<T> pVarK = this.f4105b;
        Type typeE = e(this.f4106c, t);
        if (typeE != this.f4106c) {
            pVarK = this.f4104a.k(com.google.gson.s.a.b(typeE));
            if (pVarK instanceof k.b) {
                p<T> pVar = this.f4105b;
                if (!(pVar instanceof k.b)) {
                    pVarK = pVar;
                }
            }
        }
        pVarK.d(bVar, t);
    }
}
