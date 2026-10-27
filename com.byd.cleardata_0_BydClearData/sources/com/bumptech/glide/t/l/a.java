package com.bumptech.glide.t.l;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final g<Object> f2557a = new C0089a();

    /* JADX INFO: renamed from: com.bumptech.glide.t.l.a$a, reason: collision with other inner class name */
    class C0089a implements g<Object> {
        C0089a() {
        }

        @Override // com.bumptech.glide.t.l.a.g
        public void a(Object obj) {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    class b<T> implements d<List<T>> {
        b() {
        }

        @Override // com.bumptech.glide.t.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public List<T> a() {
            return new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    class c<T> implements g<List<T>> {
        c() {
        }

        @Override // com.bumptech.glide.t.l.a.g
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(List<T> list) {
            list.clear();
        }
    }

    public interface d<T> {
        T a();
    }

    private static final class e<T> implements a.g.j.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d<T> f2558a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final g<T> f2559b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a.g.j.e<T> f2560c;

        e(a.g.j.e<T> eVar, d<T> dVar, g<T> gVar) {
            this.f2560c = eVar;
            this.f2558a = dVar;
            this.f2559b = gVar;
        }

        @Override // a.g.j.e
        public boolean a(T t) {
            if (t instanceof f) {
                ((f) t).e().b(true);
            }
            this.f2559b.a(t);
            return this.f2560c.a(t);
        }

        @Override // a.g.j.e
        public T b() {
            T tB = this.f2560c.b();
            if (tB == null) {
                tB = this.f2558a.a();
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

    public interface f {
        com.bumptech.glide.t.l.c e();
    }

    public interface g<T> {
        void a(T t);
    }

    private static <T extends f> a.g.j.e<T> a(a.g.j.e<T> eVar, d<T> dVar) {
        return b(eVar, dVar, c());
    }

    private static <T> a.g.j.e<T> b(a.g.j.e<T> eVar, d<T> dVar, g<T> gVar) {
        return new e(eVar, dVar, gVar);
    }

    private static <T> g<T> c() {
        return (g<T>) f2557a;
    }

    public static <T extends f> a.g.j.e<T> d(int i, d<T> dVar) {
        return a(new a.g.j.g(i), dVar);
    }

    public static <T> a.g.j.e<List<T>> e() {
        return f(20);
    }

    public static <T> a.g.j.e<List<T>> f(int i) {
        return b(new a.g.j.g(i), new b(), new c());
    }
}
