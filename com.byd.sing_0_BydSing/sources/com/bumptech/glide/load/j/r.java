package com.bumptech.glide.load.j;

import com.bumptech.glide.Registry;
import com.bumptech.glide.load.j.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: MultiModelLoaderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c f3008a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final n<Object, Object> f3009b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<b<?, ?>> f3010c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f3011d;
    private final Set<b<?, ?>> e;
    private final c.g.j.e<List<Throwable>> f;

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    private static class a implements n<Object, Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.j.n
        public n.a<Object> a(Object obj, int i, int i2, com.bumptech.glide.load.e eVar) {
            return null;
        }

        @Override // com.bumptech.glide.load.j.n
        public boolean b(Object obj) {
            return false;
        }
    }

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    private static class b<Model, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<Model> f3012a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<Data> f3013b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final o<? extends Model, ? extends Data> f3014c;

        public b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
            this.f3012a = cls;
            this.f3013b = cls2;
            this.f3014c = oVar;
        }

        public boolean a(Class<?> cls) {
            return this.f3012a.isAssignableFrom(cls);
        }

        public boolean b(Class<?> cls, Class<?> cls2) {
            return a(cls) && this.f3013b.isAssignableFrom(cls2);
        }
    }

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    static class c {
        c() {
        }

        public <Model, Data> q<Model, Data> a(List<n<Model, Data>> list, c.g.j.e<List<Throwable>> eVar) {
            return new q<>(list, eVar);
        }
    }

    public r(c.g.j.e<List<Throwable>> eVar) {
        this(eVar, f3008a);
    }

    private <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar, boolean z) {
        b<?, ?> bVar = new b<>(cls, cls2, oVar);
        List<b<?, ?>> list = this.f3010c;
        list.add(z ? list.size() : 0, bVar);
    }

    private <Model, Data> n<Model, Data> c(b<?, ?> bVar) {
        return (n) com.bumptech.glide.m.j.d(bVar.f3014c.b(this));
    }

    private static <Model, Data> n<Model, Data> f() {
        return (n<Model, Data>) f3009b;
    }

    synchronized <Model, Data> void b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        a(cls, cls2, oVar, true);
    }

    public synchronized <Model, Data> n<Model, Data> d(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (b<?, ?> bVar : this.f3010c) {
                if (this.e.contains(bVar)) {
                    z = true;
                } else if (bVar.b(cls, cls2)) {
                    this.e.add(bVar);
                    arrayList.add(c(bVar));
                    this.e.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.f3011d.a(arrayList, this.f);
            }
            if (arrayList.size() == 1) {
                return (n) arrayList.get(0);
            }
            if (!z) {
                throw new Registry.NoModelLoaderAvailableException((Class<?>) cls, (Class<?>) cls2);
            }
            return f();
        } catch (Throwable th) {
            this.e.clear();
            throw th;
        }
    }

    synchronized <Model> List<n<Model, ?>> e(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.f3010c) {
                if (!this.e.contains(bVar) && bVar.a(cls)) {
                    this.e.add(bVar);
                    arrayList.add(c(bVar));
                    this.e.remove(bVar);
                }
            }
        } catch (Throwable th) {
            this.e.clear();
            throw th;
        }
        return arrayList;
    }

    synchronized List<Class<?>> g(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.f3010c) {
            if (!arrayList.contains(bVar.f3013b) && bVar.a(cls)) {
                arrayList.add(bVar.f3013b);
            }
        }
        return arrayList;
    }

    r(c.g.j.e<List<Throwable>> eVar, c cVar) {
        this.f3010c = new ArrayList();
        this.e = new HashSet();
        this.f = eVar;
        this.f3011d = cVar;
    }
}
