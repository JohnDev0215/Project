package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<E> extends q<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f2959a = new C0069a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<E> f2960b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q<E> f2961c;

    /* JADX INFO: renamed from: c.b.a.t.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    public static class C0069a implements r {
        @Override // c.b.a.r
        public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            Type typeE = aVar.e();
            if (!(typeE instanceof GenericArrayType) && (!(typeE instanceof Class) || !((Class) typeE).isArray())) {
                return null;
            }
            Type typeG = C$Gson$Types.g(typeE);
            return new a(eVar, eVar.m(c.b.a.u.a.b(typeG)), C$Gson$Types.k(typeG));
        }
    }

    public a(c.b.a.e eVar, q<E> qVar, Class<E> cls) {
        this.f2961c = new m(eVar, qVar, cls);
        this.f2960b = cls;
    }

    @Override // c.b.a.q
    public Object b(c.b.a.v.a aVar) throws IOException {
        if (aVar.b0() == JsonToken.NULL) {
            aVar.X();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.d();
        while (aVar.N()) {
            arrayList.add(this.f2961c.b(aVar));
        }
        aVar.J();
        Object objNewInstance = Array.newInstance((Class<?>) this.f2960b, arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Array.set(objNewInstance, i2, arrayList.get(i2));
        }
        return objNewInstance;
    }

    @Override // c.b.a.q
    public void d(c.b.a.v.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.P();
            return;
        }
        bVar.s();
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            this.f2961c.d(bVar, (E) Array.get(obj, i2));
        }
        bVar.I();
    }
}
