package com.bumptech.glide.load.i;

import com.bumptech.glide.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ModelLoaderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r f3224a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f3225b;

    /* JADX INFO: compiled from: ModelLoaderRegistry.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<Class<?>, C0073a<?>> f3226a = new HashMap();

        /* JADX INFO: renamed from: com.bumptech.glide.load.i.p$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ModelLoaderRegistry.java */
        private static class C0073a<Model> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final List<n<Model, ?>> f3227a;

            public C0073a(List<n<Model, ?>> list) {
                this.f3227a = list;
            }
        }

        a() {
        }

        public void a() {
            this.f3226a.clear();
        }

        public <Model> List<n<Model, ?>> b(Class<Model> cls) {
            C0073a<?> c0073a = this.f3226a.get(cls);
            if (c0073a == null) {
                return null;
            }
            return (List<n<Model, ?>>) c0073a.f3227a;
        }

        public <Model> void c(Class<Model> cls, List<n<Model, ?>> list) {
            if (this.f3226a.put(cls, new C0073a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public p(b.g.j.e<List<Throwable>> eVar) {
        this(new r(eVar));
    }

    private static <A> Class<A> b(A a2) {
        return (Class<A>) a2.getClass();
    }

    private synchronized <A> List<n<A, ?>> e(Class<A> cls) {
        List<n<A, ?>> listB;
        listB = this.f3225b.b(cls);
        if (listB == null) {
            listB = Collections.unmodifiableList(this.f3224a.e(cls));
            this.f3225b.c(cls, listB);
        }
        return listB;
    }

    public synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f3224a.b(cls, cls2, oVar);
        this.f3225b.a();
    }

    public synchronized List<Class<?>> c(Class<?> cls) {
        return this.f3224a.g(cls);
    }

    public <A> List<n<A, ?>> d(A a2) {
        List<n<A, ?>> listE = e(b(a2));
        if (listE.isEmpty()) {
            throw new Registry.NoModelLoaderAvailableException(a2);
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
            throw new Registry.NoModelLoaderAvailableException(a2, listE);
        }
        return listEmptyList;
    }

    public synchronized <Model, Data> void f(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f3224a.h(cls, cls2, oVar);
        this.f3225b.a();
    }

    private p(r rVar) {
        this.f3225b = new a();
        this.f3224a = rVar;
    }
}
