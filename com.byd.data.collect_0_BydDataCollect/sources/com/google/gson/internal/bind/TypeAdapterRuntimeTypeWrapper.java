package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.w.c;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes.dex */
final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Gson f4505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypeAdapter<T> f4506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Type f4507c;

    TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.f4505a = gson;
        this.f4506b = typeAdapter;
        this.f4507c = type;
    }

    private Type a(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(com.google.gson.w.a aVar) {
        return this.f4506b.read(aVar);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(c cVar, T t) {
        TypeAdapter<T> typeAdapterK = this.f4506b;
        Type typeA = a(this.f4507c, t);
        if (typeA != this.f4507c) {
            typeAdapterK = this.f4505a.k(com.google.gson.v.a.b(typeA));
            if (typeAdapterK instanceof ReflectiveTypeAdapterFactory.Adapter) {
                TypeAdapter<T> typeAdapter = this.f4506b;
                if (!(typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                    typeAdapterK = typeAdapter;
                }
            }
        }
        typeAdapterK.write(cVar, t);
    }
}
