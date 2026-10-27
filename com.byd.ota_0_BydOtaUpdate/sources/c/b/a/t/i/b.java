package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.a.t.b f2962c;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    public static final class a<E> extends q<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q<E> f2963a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c.b.a.t.e<? extends Collection<E>> f2964b;

        public a(c.b.a.e eVar, Type type, q<E> qVar, c.b.a.t.e<? extends Collection<E>> eVar2) {
            this.f2963a = new m(eVar, qVar, type);
            this.f2964b = eVar2;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            Collection<E> collectionA = this.f2964b.a();
            aVar.d();
            while (aVar.N()) {
                collectionA.add(this.f2963a.b(aVar));
            }
            aVar.J();
            return collectionA;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.P();
                return;
            }
            bVar.s();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f2963a.d(bVar, it.next());
            }
            bVar.I();
        }
    }

    public b(c.b.a.t.b bVar) {
        this.f2962c = bVar;
    }

    @Override // c.b.a.r
    public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
        Type typeE = aVar.e();
        Class<? super T> clsC = aVar.c();
        if (!Collection.class.isAssignableFrom(clsC)) {
            return null;
        }
        Type typeH = C$Gson$Types.h(typeE, clsC);
        return new a(eVar, typeH, eVar.m(c.b.a.u.a.b(typeH)), this.f2962c.a(aVar));
    }
}
