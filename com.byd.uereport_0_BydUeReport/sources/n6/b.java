package n6;

import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m6.b f6555d;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    public static final class a<E> extends o<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<E> f6556a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final m6.e<? extends Collection<E>> f6557b;

        public a(k6.d dVar, Type type, o<E> oVar, m6.e<? extends Collection<E>> eVar) {
            this.f6556a = new m(dVar, oVar, type);
            this.f6557b = eVar;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            Collection<E> collectionA = this.f6557b.a();
            aVar.g();
            while (aVar.t()) {
                collectionA.add(this.f6556a.b(aVar));
            }
            aVar.o();
            return collectionA;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.B();
                return;
            }
            bVar.i();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f6556a.d(bVar, it.next());
            }
            bVar.n();
        }
    }

    public b(m6.b bVar) {
        this.f6555d = bVar;
    }

    @Override // k6.p
    public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        if (!Collection.class.isAssignableFrom(clsD)) {
            return null;
        }
        Type typeH = C$Gson$Types.h(typeF, clsD);
        return new a(dVar, typeH, dVar.k(p6.a.b(typeH)), this.f6555d.a(aVar));
    }
}
