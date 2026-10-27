package com.bumptech.glide.load.o;

import com.bumptech.glide.i;
import com.bumptech.glide.load.o.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c f2262a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final n<Object, Object> f2263b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<b<?, ?>> f2264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f2265d;
    private final Set<b<?, ?>> e;
    private final a.g.j.e<List<Throwable>> f;

    private static class a implements n<Object, Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.o.n
        public n.a<Object> a(Object obj, int i, int i2, com.bumptech.glide.load.i iVar) {
            return null;
        }

        @Override // com.bumptech.glide.load.o.n
        public boolean b(Object obj) {
            return false;
        }
    }

    private static class b<Model, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<Model> f2266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<Data> f2267b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final o<? extends Model, ? extends Data> f2268c;

        public b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
            this.f2266a = cls;
            this.f2267b = cls2;
            this.f2268c = oVar;
        }

        public boolean a(Class<?> cls) {
            return this.f2266a.isAssignableFrom(cls);
        }

        public boolean b(Class<?> cls, Class<?> cls2) {
            return a(cls) && this.f2267b.isAssignableFrom(cls2);
        }
    }

    static class c {
        c() {
        }

        public <Model, Data> q<Model, Data> a(List<n<Model, Data>> list, a.g.j.e<List<Throwable>> eVar) {
            return new q<>(list, eVar);
        }
    }

    public r(a.g.j.e<List<Throwable>> eVar) {
        this(eVar, f2262a);
    }

    r(a.g.j.e<List<Throwable>> eVar, c cVar) {
        this.f2264c = new ArrayList();
        this.e = new HashSet();
        this.f = eVar;
        this.f2265d = cVar;
    }

    private <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar, boolean z) {
        b<?, ?> bVar = new b<>(cls, cls2, oVar);
        List<b<?, ?>> list = this.f2264c;
        list.add(z ? list.size() : 0, bVar);
    }

    private <Model, Data> n<Model, Data> c(b<?, ?> bVar) {
        return (n) com.bumptech.glide.t.j.d(bVar.f2268c.b(this));
    }

    private static <Model, Data> n<Model, Data> f() {
        return (n<Model, Data>) f2263b;
    }

    synchronized <Model, Data> void b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        a(cls, cls2, oVar, true);
    }

    public synchronized <Model, Data> n<Model, Data> d(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (b<?, ?> bVar : this.f2264c) {
                if (this.e.contains(bVar)) {
                    z = true;
                } else if (bVar.b(cls, cls2)) {
                    this.e.add(bVar);
                    arrayList.add(c(bVar));
                    this.e.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.f2265d.a(arrayList, this.f);
            }
            if (arrayList.size() == 1) {
                return (n) arrayList.get(0);
            }
            if (!z) {
                throw new i.c((Class<?>) cls, (Class<?>) cls2);
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
            for (b<?, ?> bVar : this.f2264c) {
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
        for (b<?, ?> bVar : this.f2264c) {
            if (!arrayList.contains(bVar.f2267b) && bVar.a(cls)) {
                arrayList.add(bVar.f2267b);
            }
        }
        return arrayList;
    }

    synchronized <Model, Data> void h(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        a(cls, cls2, oVar, false);
    }
}
