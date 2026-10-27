package com.bumptech.glide.o.l;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FactoryPools.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final g<Object> f3471a = new C0081a();

    /* JADX INFO: renamed from: com.bumptech.glide.o.l.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FactoryPools.java */
    class C0081a implements g<Object> {
        C0081a() {
        }

        @Override // com.bumptech.glide.o.l.a.g
        public void a(Object obj) {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: FactoryPools.java */
    class b<T> implements d<List<T>> {
        b() {
        }

        @Override // com.bumptech.glide.o.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public List<T> a() {
            return new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: FactoryPools.java */
    class c<T> implements g<List<T>> {
        c() {
        }

        @Override // com.bumptech.glide.o.l.a.g
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(List<T> list) {
            list.clear();
        }
    }

    /* JADX INFO: compiled from: FactoryPools.java */
    public interface d<T> {
        T a();
    }

    /* JADX INFO: compiled from: FactoryPools.java */
    private static final class e<T> implements b.g.j.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d<T> f3472a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final g<T> f3473b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final b.g.j.e<T> f3474c;

        e(b.g.j.e<T> eVar, d<T> dVar, g<T> gVar) {
            this.f3474c = eVar;
            this.f3472a = dVar;
            this.f3473b = gVar;
        }

        @Override // b.g.j.e
        public boolean a(T t) {
            if (t instanceof f) {
                ((f) t).e().b(true);
            }
            this.f3473b.a(t);
            return this.f3474c.a(t);
        }

        @Override // b.g.j.e
        public T b() {
            T tB = this.f3474c.b();
            if (tB == null) {
                tB = this.f3472a.a();
                if (Log.isLoggable("FactoryPools", 2)) {
                    Log.v("FactoryPools", "Created new " + tB.getClass());
                }
            }
            if (tB instanceof f) {
                ((f) tB).e().b(false);
            }
            return tB;
        }
    }

    /* JADX INFO: compiled from: FactoryPools.java */
    public interface f {
        com.bumptech.glide.o.l.c e();
    }

    /* JADX INFO: compiled from: FactoryPools.java */
    public interface g<T> {
        void a(T t);
    }

    private static <T extends f> b.g.j.e<T> a(b.g.j.e<T> eVar, d<T> dVar) {
        return b(eVar, dVar, c());
    }

    private static <T> b.g.j.e<T> b(b.g.j.e<T> eVar, d<T> dVar, g<T> gVar) {
        return new e(eVar, dVar, gVar);
    }

    private static <T> g<T> c() {
        return (g<T>) f3471a;
    }

    public static <T extends f> b.g.j.e<T> d(int i, d<T> dVar) {
        return a(new b.g.j.g(i), dVar);
    }

    public static <T> b.g.j.e<List<T>> e() {
        return f(20);
    }

    public static <T> b.g.j.e<List<T>> f(int i) {
        return b(new b.g.j.g(i), new b(), new c());
    }
}
