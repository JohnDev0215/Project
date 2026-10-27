package com.bumptech.glide.load.i;

import com.bumptech.glide.Registry;
import com.bumptech.glide.load.i.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: MultiModelLoaderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c f3234a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final n<Object, Object> f3235b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<b<?, ?>> f3236c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f3237d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Set<b<?, ?>> f3238e;
    private final b.g.j.e<List<Throwable>> f;

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    private static class a implements n<Object, Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.i.n
        public n.a<Object> a(Object obj, int i, int i2, com.bumptech.glide.load.e eVar) {
            return null;
        }

        @Override // com.bumptech.glide.load.i.n
        public boolean b(Object obj) {
            return false;
        }
    }

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    private static class b<Model, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<Model> f3239a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<Data> f3240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final o<? extends Model, ? extends Data> f3241c;

        public b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
            this.f3239a = cls;
            this.f3240b = cls2;
            this.f3241c = oVar;
        }

        public boolean a(Class<?> cls) {
            return this.f3239a.isAssignableFrom(cls);
        }

        public boolean b(Class<?> cls, Class<?> cls2) {
            return a(cls) && this.f3240b.isAssignableFrom(cls2);
        }
    }

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    static class c {
        c() {
        }

        public <Model, Data> q<Model, Data> a(List<n<Model, Data>> list, b.g.j.e<List<Throwable>> eVar) {
            return new q<>(list, eVar);
        }
    }

    public r(b.g.j.e<List<Throwable>> eVar) {
        this(eVar, f3234a);
    }

    private <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar, boolean z) {
        b<?, ?> bVar = new b<>(cls, cls2, oVar);
        List<b<?, ?>> list = this.f3236c;
        list.add(z ? list.size() : 0, bVar);
    }

    private <Model, Data> n<Model, Data> c(b<?, ?> bVar) {
        return (n) com.bumptech.glide.o.j.d(bVar.f3241c.b(this));
    }

    private static <Model, Data> n<Model, Data> f() {
        return (n<Model, Data>) f3235b;
    }

    synchronized <Model, Data> void b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        a(cls, cls2, oVar, true);
    }

    public synchronized <Model, Data> n<Model, Data> d(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (b<?, ?> bVar : this.f3236c) {
                if (this.f3238e.contains(bVar)) {
                    z = true;
                } else if (bVar.b(cls, cls2)) {
                    this.f3238e.add(bVar);
                    arrayList.add(c(bVar));
                    this.f3238e.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.f3237d.a(arrayList, this.f);
            }
            if (arrayList.size() == 1) {
                return (n) arrayList.get(0);
            }
            if (!z) {
                throw new Registry.NoModelLoaderAvailableException((Class<?>) cls, (Class<?>) cls2);
            }
            return f();
        } catch (Throwable th) {
            this.f3238e.clear();
            throw th;
        }
    }

    synchronized <Model> List<n<Model, ?>> e(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.f3236c) {
                if (!this.f3238e.contains(bVar) && bVar.a(cls)) {
                    this.f3238e.add(bVar);
                    arrayList.add(c(bVar));
                    this.f3238e.remove(bVar);
                }
            }
        } catch (Throwable th) {
            this.f3238e.clear();
            throw th;
        }
        return arrayList;
    }

    synchronized List<Class<?>> g(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.f3236c) {
            if (!arrayList.contains(bVar.f3240b) && bVar.a(cls)) {
                arrayList.add(bVar.f3240b);
            }
        }
        return arrayList;
    }

    synchronized <Model, Data> void h(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        a(cls, cls2, oVar, false);
    }

    r(b.g.j.e<List<Throwable>> eVar, c cVar) {
        this.f3236c = new ArrayList();
        this.f3238e = new HashSet();
        this.f = eVar;
        this.f3237d = cVar;
    }
}
