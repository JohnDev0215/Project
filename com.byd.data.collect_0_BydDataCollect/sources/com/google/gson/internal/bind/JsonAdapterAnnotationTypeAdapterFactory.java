package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.i;
import com.google.gson.internal.c;
import com.google.gson.q;
import com.google.gson.t;

/* JADX INFO: loaded from: classes.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4462b;

    public JsonAdapterAnnotationTypeAdapterFactory(c cVar) {
        this.f4462b = cVar;
    }

    TypeAdapter<?> a(c cVar, Gson gson, com.google.gson.v.a<?> aVar, com.google.gson.u.b bVar) {
        TypeAdapter<?> treeTypeAdapter;
        Object objA = cVar.a(com.google.gson.v.a.a(bVar.value())).a();
        if (objA instanceof TypeAdapter) {
            treeTypeAdapter = (TypeAdapter) objA;
        } else if (objA instanceof t) {
            treeTypeAdapter = ((t) objA).b(gson, aVar);
        } else {
            boolean z = objA instanceof q;
            if (!z && !(objA instanceof i)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            treeTypeAdapter = new TreeTypeAdapter<>(z ? (q) objA : null, objA instanceof i ? (i) objA : null, gson, aVar, null);
        }
        return (treeTypeAdapter == null || !bVar.nullSafe()) ? treeTypeAdapter : treeTypeAdapter.nullSafe();
    }

    @Override // com.google.gson.t
    public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
        com.google.gson.u.b bVar = (com.google.gson.u.b) aVar.c().getAnnotation(com.google.gson.u.b.class);
        if (bVar == null) {
            return null;
        }
        return (TypeAdapter<T>) a(this.f4462b, gson, aVar, bVar);
    }
}
