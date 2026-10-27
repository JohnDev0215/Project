package com.google.gson.internal.k;

import com.google.gson.p;
import com.google.gson.q;

/* JADX INFO: compiled from: JsonAdapterAnnotationTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.internal.b f4065b;

    public e(com.google.gson.internal.b bVar) {
        this.f4065b = bVar;
    }

    @Override // com.google.gson.q
    public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
        com.google.gson.r.b bVar = (com.google.gson.r.b) aVar.c().getAnnotation(com.google.gson.r.b.class);
        if (bVar == null) {
            return null;
        }
        return (p<T>) b(this.f4065b, dVar, aVar, bVar);
    }

    p<?> b(com.google.gson.internal.b bVar, com.google.gson.d dVar, com.google.gson.s.a<?> aVar, com.google.gson.r.b bVar2) {
        p<?> lVar;
        Object objA = bVar.a(com.google.gson.s.a.a(bVar2.value())).a();
        if (objA instanceof p) {
            lVar = (p) objA;
        } else if (objA instanceof q) {
            lVar = ((q) objA).a(dVar, aVar);
        } else {
            boolean z = objA instanceof com.google.gson.n;
            if (!z && !(objA instanceof com.google.gson.h)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            lVar = new l<>(z ? (com.google.gson.n) objA : null, objA instanceof com.google.gson.h ? (com.google.gson.h) objA : null, dVar, aVar, null);
        }
        return (lVar == null || !bVar2.nullSafe()) ? lVar : lVar.a();
    }
}
