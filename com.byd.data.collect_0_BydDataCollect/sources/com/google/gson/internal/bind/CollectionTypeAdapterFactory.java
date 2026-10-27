package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.c;
import com.google.gson.internal.h;
import com.google.gson.t;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionTypeAdapterFactory implements t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4457b;

    private static final class Adapter<E> extends TypeAdapter<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TypeAdapter<E> f4458a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h<? extends Collection<E>> f4459b;

        public Adapter(Gson gson, Type type, TypeAdapter<E> typeAdapter, h<? extends Collection<E>> hVar) {
            this.f4458a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.f4459b = hVar;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Collection<E> read(com.google.gson.w.a aVar) throws IOException {
            if (aVar.j0() == com.google.gson.w.b.NULL) {
                aVar.f0();
                return null;
            }
            Collection<E> collectionA = this.f4459b.a();
            aVar.a();
            while (aVar.V()) {
                collectionA.add(this.f4458a.read(aVar));
            }
            aVar.R();
            return collectionA;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void write(com.google.gson.w.c cVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                cVar.Y();
                return;
            }
            cVar.j();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f4458a.write(cVar, it.next());
            }
            cVar.R();
        }
    }

    public CollectionTypeAdapterFactory(c cVar) {
        this.f4457b = cVar;
    }

    @Override // com.google.gson.t
    public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
        Type typeE = aVar.e();
        Class<? super T> clsC = aVar.c();
        if (!Collection.class.isAssignableFrom(clsC)) {
            return null;
        }
        Type typeH = com.google.gson.internal.b.h(typeE, clsC);
        return new Adapter(gson, typeH, gson.k(com.google.gson.v.a.b(typeH)), this.f4457b.a(aVar));
    }
}
