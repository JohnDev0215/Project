package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a<E> extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f2250a = new C0092a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<E> f2251b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o<E> f2252c;

    /* JADX INFO: renamed from: b.c.a.r.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    public static class C0092a implements p {
        @Override // b.c.a.p
        public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            Type typeF = aVar.f();
            if (!(typeF instanceof GenericArrayType) && (!(typeF instanceof Class) || !((Class) typeF).isArray())) {
                return null;
            }
            Type typeG = C$Gson$Types.g(typeF);
            return new a(dVar, dVar.k(b.c.a.s.a.b(typeG)), C$Gson$Types.k(typeG));
        }
    }

    public a(b.c.a.d dVar, o<E> oVar, Class<E> cls) {
        this.f2252c = new m(dVar, oVar, cls);
        this.f2251b = cls;
    }

    @Override // b.c.a.o
    public Object b(b.c.a.t.a aVar) throws IOException {
        if (aVar.E() == JsonToken.NULL) {
            aVar.z();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.c();
        while (aVar.n()) {
            arrayList.add(this.f2252c.b(aVar));
        }
        aVar.i();
        Object objNewInstance = Array.newInstance((Class<?>) this.f2251b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // b.c.a.o
    public void d(b.c.a.t.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.s();
            return;
        }
        bVar.e();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f2252c.d(bVar, (E) Array.get(obj, i));
        }
        bVar.h();
    }
}
