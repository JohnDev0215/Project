package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.c.a.r.b f2253a;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    public static final class a<E> extends o<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<E> f2254a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.c.a.r.e<? extends Collection<E>> f2255b;

        public a(b.c.a.d dVar, Type type, o<E> oVar, b.c.a.r.e<? extends Collection<E>> eVar) {
            this.f2254a = new m(dVar, oVar, type);
            this.f2255b = eVar;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            Collection<E> collectionA = this.f2255b.a();
            aVar.c();
            while (aVar.n()) {
                collectionA.add(this.f2254a.b(aVar));
            }
            aVar.i();
            return collectionA;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.s();
                return;
            }
            bVar.e();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f2254a.d(bVar, it.next());
            }
            bVar.h();
        }
    }

    public b(b.c.a.r.b bVar) {
        this.f2253a = bVar;
    }

    @Override // b.c.a.p
    public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        if (!Collection.class.isAssignableFrom(clsD)) {
            return null;
        }
        Type typeH = C$Gson$Types.h(typeF, clsD);
        return new a(dVar, typeH, dVar.k(b.c.a.s.a.b(typeH)), this.f2253a.a(aVar));
    }
}
