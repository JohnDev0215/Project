package com.google.gson.internal.k;

import com.google.gson.internal.C$Gson$Types;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<E> extends p<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f4055a = new C0076a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Class<E> f4056b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final p<E> f4057c;

    /* JADX INFO: renamed from: com.google.gson.internal.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    class C0076a implements q {
        C0076a() {
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            Type typeE = aVar.e();
            if (!(typeE instanceof GenericArrayType) && (!(typeE instanceof Class) || !((Class) typeE).isArray())) {
                return null;
            }
            Type typeG = C$Gson$Types.g(typeE);
            return new a(dVar, dVar.k(com.google.gson.s.a.b(typeG)), C$Gson$Types.k(typeG));
        }
    }

    public a(com.google.gson.d dVar, p<E> pVar, Class<E> cls) {
        this.f4057c = new m(dVar, pVar, cls);
        this.f4056b = cls;
    }

    @Override // com.google.gson.p
    public Object b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.x() == JsonToken.NULL) {
            aVar.t();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.j()) {
            arrayList.add(this.f4057c.b(aVar));
        }
        aVar.f();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.f4056b, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // com.google.gson.p
    public void d(com.google.gson.stream.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.m();
            return;
        }
        bVar.c();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f4057c.d(bVar, (E) Array.get(obj, i));
        }
        bVar.f();
    }
}
