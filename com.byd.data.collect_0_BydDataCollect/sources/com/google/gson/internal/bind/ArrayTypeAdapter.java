package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.t;
import com.google.gson.w.c;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f4454a = new t() { // from class: com.google.gson.internal.bind.ArrayTypeAdapter.1
        @Override // com.google.gson.t
        public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
            Type typeE = aVar.e();
            if (!(typeE instanceof GenericArrayType) && (!(typeE instanceof Class) || !((Class) typeE).isArray())) {
                return null;
            }
            Type typeG = com.google.gson.internal.b.g(typeE);
            return new ArrayTypeAdapter(gson, gson.k(com.google.gson.v.a.b(typeG)), com.google.gson.internal.b.k(typeG));
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Class<E> f4455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TypeAdapter<E> f4456c;

    public ArrayTypeAdapter(Gson gson, TypeAdapter<E> typeAdapter, Class<E> cls) {
        this.f4456c = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, cls);
        this.f4455b = cls;
    }

    @Override // com.google.gson.TypeAdapter
    public Object read(com.google.gson.w.a aVar) throws IOException {
        if (aVar.j0() == com.google.gson.w.b.NULL) {
            aVar.f0();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.V()) {
            arrayList.add(this.f4456c.read(aVar));
        }
        aVar.R();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.f4455b, size);
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(objNewInstance, i2, arrayList.get(i2));
        }
        return objNewInstance;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.Y();
            return;
        }
        cVar.j();
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            this.f4456c.write(cVar, (E) Array.get(obj, i2));
        }
        cVar.R();
    }
}
