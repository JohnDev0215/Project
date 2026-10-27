package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.a.r.b f437b;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    public static final class a<E> extends o<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<E> f438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.b.a.r.e<? extends Collection<E>> f439b;

        public a(b.b.a.d dVar, Type type, o<E> oVar, b.b.a.r.e<? extends Collection<E>> eVar) {
            this.f438a = new m(dVar, oVar, type);
            this.f439b = eVar;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            Collection<E> collectionA = this.f439b.a();
            aVar.b();
            while (aVar.k()) {
                collectionA.add(this.f438a.b(aVar));
            }
            aVar.g();
            return collectionA;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.n();
                return;
            }
            bVar.c();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f438a.d(bVar, it.next());
            }
            bVar.f();
        }
    }

    public b(b.b.a.r.b bVar) {
        this.f437b = bVar;
    }

    @Override // b.b.a.p
    public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        if (!Collection.class.isAssignableFrom(clsD)) {
            return null;
        }
        Type typeH = C$Gson$Types.h(typeF, clsD);
        return new a(dVar, typeH, dVar.k(b.b.a.s.a.b(typeH)), this.f437b.a(aVar));
    }
}
