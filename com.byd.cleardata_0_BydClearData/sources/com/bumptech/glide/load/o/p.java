package com.bumptech.glide.load.o;

import com.bumptech.glide.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r f2253a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f2254b;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<Class<?>, C0077a<?>> f2255a = new HashMap();

        /* JADX INFO: renamed from: com.bumptech.glide.load.o.p$a$a, reason: collision with other inner class name */
        private static class C0077a<Model> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final List<n<Model, ?>> f2256a;

            public C0077a(List<n<Model, ?>> list) {
                this.f2256a = list;
            }
        }

        a() {
        }

        public void a() {
            this.f2255a.clear();
        }

        public <Model> List<n<Model, ?>> b(Class<Model> cls) {
            C0077a<?> c0077a = this.f2255a.get(cls);
            if (c0077a == null) {
                return null;
            }
            return (List<n<Model, ?>>) c0077a.f2256a;
        }

        public <Model> void c(Class<Model> cls, List<n<Model, ?>> list) {
            if (this.f2255a.put(cls, new C0077a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public p(a.g.j.e<List<Throwable>> eVar) {
        this(new r(eVar));
    }

    private p(r rVar) {
        this.f2254b = new a();
        this.f2253a = rVar;
    }

    private static <A> Class<A> b(A a2) {
        return (Class<A>) a2.getClass();
    }

    private synchronized <A> List<n<A, ?>> e(Class<A> cls) {
        List<n<A, ?>> listB;
        listB = this.f2254b.b(cls);
        if (listB == null) {
            listB = Collections.unmodifiableList(this.f2253a.e(cls));
            this.f2254b.c(cls, listB);
        }
        return listB;
    }

    public synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f2253a.b(cls, cls2, oVar);
        this.f2254b.a();
    }

    public synchronized List<Class<?>> c(Class<?> cls) {
        return this.f2253a.g(cls);
    }

    public <A> List<n<A, ?>> d(A a2) {
        List<n<A, ?>> listE = e(b(a2));
        if (listE.isEmpty()) {
            throw new i.c(a2);
        }
        int size = listE.size();
        List<n<A, ?>> listEmptyList = Collections.emptyList();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            n<A, ?> nVar = listE.get(i);
            if (nVar.b(a2)) {
                if (z) {
                    listEmptyList = new ArrayList<>(size - i);
                    z = false;
                }
                listEmptyList.add(nVar);
            }
        }
        if (listEmptyList.isEmpty()) {
            throw new i.c(a2, listE);
        }
        return listEmptyList;
    }

    public synchronized <Model, Data> void f(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f2253a.h(cls, cls2, oVar);
        this.f2254b.a();
    }
}
