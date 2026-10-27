package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a<E> extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f434a = new C0030a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<E> f435b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o<E> f436c;

    /* JADX INFO: renamed from: b.b.a.r.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    public static class C0030a implements p {
        @Override // b.b.a.p
        public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            Type typeF = aVar.f();
            if (!(typeF instanceof GenericArrayType) && (!(typeF instanceof Class) || !((Class) typeF).isArray())) {
                return null;
            }
            Type typeG = C$Gson$Types.g(typeF);
            return new a(dVar, dVar.k(b.b.a.s.a.b(typeG)), C$Gson$Types.k(typeG));
        }
    }

    public a(b.b.a.d dVar, o<E> oVar, Class<E> cls) {
        this.f436c = new m(dVar, oVar, cls);
        this.f435b = cls;
    }

    @Override // b.b.a.o
    public Object b(b.b.a.t.a aVar) throws IOException {
        if (aVar.y() == JsonToken.NULL) {
            aVar.u();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.b();
        while (aVar.k()) {
            arrayList.add(this.f436c.b(aVar));
        }
        aVar.g();
        Object objNewInstance = Array.newInstance((Class<?>) this.f435b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // b.b.a.o
    public void d(b.b.a.t.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.n();
            return;
        }
        bVar.c();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f436c.d(bVar, (E) Array.get(obj, i));
        }
        bVar.f();
    }
}
