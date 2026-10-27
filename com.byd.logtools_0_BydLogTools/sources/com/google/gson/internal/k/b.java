package com.google.gson.internal.k;

import com.google.gson.internal.C$Gson$Types;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.internal.b f4058b;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    private static final class a<E> extends p<Collection<E>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final p<E> f4059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.google.gson.internal.f<? extends Collection<E>> f4060b;

        public a(com.google.gson.d dVar, Type type, p<E> pVar, com.google.gson.internal.f<? extends Collection<E>> fVar) {
            this.f4059a = new m(dVar, pVar, type);
            this.f4060b = fVar;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection<E> b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            Collection<E> collectionA = this.f4060b.a();
            aVar.a();
            while (aVar.j()) {
                collectionA.add(this.f4059a.b(aVar));
            }
            aVar.f();
            return collectionA;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.m();
                return;
            }
            bVar.c();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f4059a.d(bVar, it.next());
            }
            bVar.f();
        }
    }

    public b(com.google.gson.internal.b bVar) {
        this.f4058b = bVar;
    }

    @Override // com.google.gson.q
    public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
        Type typeE = aVar.e();
        Class<? super T> clsC = aVar.c();
        if (!Collection.class.isAssignableFrom(clsC)) {
            return null;
        }
        Type typeH = C$Gson$Types.h(typeE, clsC);
        return new a(dVar, typeH, dVar.k(com.google.gson.s.a.b(typeH)), this.f4058b.a(aVar));
    }
}
