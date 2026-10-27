package n6;

import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a<E> extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f6552a = new C0095a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<E> f6553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o<E> f6554c;

    /* JADX INFO: renamed from: n6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    public static class C0095a implements p {
        @Override // k6.p
        public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
            Type typeF = aVar.f();
            if (!(typeF instanceof GenericArrayType) && (!(typeF instanceof Class) || !((Class) typeF).isArray())) {
                return null;
            }
            Type typeG = C$Gson$Types.g(typeF);
            return new a(dVar, dVar.k(p6.a.b(typeG)), C$Gson$Types.k(typeG));
        }
    }

    public a(k6.d dVar, o<E> oVar, Class<E> cls) {
        this.f6554c = new m(dVar, oVar, cls);
        this.f6553b = cls;
    }

    @Override // k6.o
    public Object b(q6.a aVar) throws IOException {
        if (aVar.R() == JsonToken.NULL) {
            aVar.M();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.g();
        while (aVar.t()) {
            arrayList.add(this.f6554c.b(aVar));
        }
        aVar.o();
        Object objNewInstance = Array.newInstance((Class<?>) this.f6553b, arrayList.size());
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            Array.set(objNewInstance, i8, arrayList.get(i8));
        }
        return objNewInstance;
    }

    @Override // k6.o
    public void d(q6.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.B();
            return;
        }
        bVar.i();
        int length = Array.getLength(obj);
        for (int i8 = 0; i8 < length; i8++) {
            this.f6554c.d(bVar, (E) Array.get(obj, i8));
        }
        bVar.n();
    }
}
