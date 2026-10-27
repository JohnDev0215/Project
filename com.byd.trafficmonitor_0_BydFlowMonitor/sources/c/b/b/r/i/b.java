package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b.r.b f4225b;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    public static final class a<E> extends o<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<E> f4226a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c.b.b.r.e<? extends Collection<E>> f4227b;

        public a(c.b.b.d dVar, Type type, o<E> oVar, c.b.b.r.e<? extends Collection<E>> eVar) {
            this.f4226a = new m(dVar, oVar, type);
            this.f4227b = eVar;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            Collection<E> collectionA = this.f4227b.a();
            aVar.z();
            while (aVar.W()) {
                collectionA.add(this.f4226a.b(aVar));
            }
            aVar.S();
            return collectionA;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.Z();
                return;
            }
            bVar.O();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f4226a.d(bVar, it.next());
            }
            bVar.R();
        }
    }

    public b(c.b.b.r.b bVar) {
        this.f4225b = bVar;
    }

    @Override // c.b.b.p
    public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        if (!Collection.class.isAssignableFrom(clsD)) {
            return null;
        }
        Type typeH = C$Gson$Types.h(typeF, clsD);
        return new a(dVar, typeH, dVar.k(c.b.b.s.a.b(typeH)), this.f4225b.a(aVar));
    }
}
