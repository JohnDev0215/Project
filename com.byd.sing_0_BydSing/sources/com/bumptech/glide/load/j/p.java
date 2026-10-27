package com.bumptech.glide.load.j;

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
    private final r f2999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f3000b;

    /* JADX INFO: compiled from: ModelLoaderRegistry.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<Class<?>, C0097a<?>> f3001a = new HashMap();

        /* JADX INFO: renamed from: com.bumptech.glide.load.j.p$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ModelLoaderRegistry.java */
        private static class C0097a<Model> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final List<n<Model, ?>> f3002a;

            public C0097a(List<n<Model, ?>> list) {
                this.f3002a = list;
            }
        }

        a() {
        }

        public void a() {
            this.f3001a.clear();
        }

        public <Model> List<n<Model, ?>> b(Class<Model> cls) {
            C0097a<?> c0097a = this.f3001a.get(cls);
            if (c0097a == null) {
                return null;
            }
            return (List<n<Model, ?>>) c0097a.f3002a;
        }

        public <Model> void c(Class<Model> cls, List<n<Model, ?>> list) {
            if (this.f3001a.put(cls, new C0097a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public p(c.g.j.e<List<Throwable>> eVar) {
        this(new r(eVar));
    }

    private static <A> Class<A> b(A a2) {
        return (Class<A>) a2.getClass();
    }

    private synchronized <A> List<n<A, ?>> e(Class<A> cls) {
        List<n<A, ?>> listB;
        listB = this.f3000b.b(cls);
        if (listB == null) {
            listB = Collections.unmodifiableList(this.f2999a.e(cls));
            this.f3000b.c(cls, listB);
        }
        return listB;
    }

    public synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f2999a.b(cls, cls2, oVar);
        this.f3000b.a();
    }

    public synchronized List<Class<?>> c(Class<?> cls) {
        return this.f2999a.g(cls);
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

    private p(r rVar) {
        this.f3000b = new a();
        this.f2999a = rVar;
    }
}
