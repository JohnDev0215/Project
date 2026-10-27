package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<E> extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f4222a = new C0097a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<E> f4223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o<E> f4224c;

    /* JADX INFO: renamed from: c.b.b.r.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    public static class C0097a implements p {
        @Override // c.b.b.p
        public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            Type typeF = aVar.f();
            if (!(typeF instanceof GenericArrayType) && (!(typeF instanceof Class) || !((Class) typeF).isArray())) {
                return null;
            }
            Type typeG = C$Gson$Types.g(typeF);
            return new a(dVar, dVar.k(c.b.b.s.a.b(typeG)), C$Gson$Types.k(typeG));
        }
    }

    public a(c.b.b.d dVar, o<E> oVar, Class<E> cls) {
        this.f4224c = new m(dVar, oVar, cls);
        this.f4223b = cls;
    }

    @Override // c.b.b.o
    public Object b(c.b.b.t.a aVar) throws IOException {
        if (aVar.k0() == JsonToken.NULL) {
            aVar.g0();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.z();
        while (aVar.W()) {
            arrayList.add(this.f4224c.b(aVar));
        }
        aVar.S();
        Object objNewInstance = Array.newInstance((Class<?>) this.f4223b, arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Array.set(objNewInstance, i2, arrayList.get(i2));
        }
        return objNewInstance;
    }

    @Override // c.b.b.o
    public void d(c.b.b.t.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.Z();
            return;
        }
        bVar.O();
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            this.f4224c.d(bVar, (E) Array.get(obj, i2));
        }
        bVar.R();
    }
}
