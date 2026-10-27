package l2;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FactoryPools.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final g<Object> f7943a = new C0115a();

    /* JADX INFO: renamed from: l2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FactoryPools.java */
    class C0115a implements g<Object> {
        C0115a() {
        }

        @Override // l2.a.g
        public void a(Object obj) {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: FactoryPools.java */
    class b<T> implements d<List<T>> {
        b() {
        }

        @Override // l2.a.d
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

        @Override // l2.a.g
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
    private static final class e<T> implements h0.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d<T> f7944a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final g<T> f7945b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h0.e<T> f7946c;

        e(h0.e<T> eVar, d<T> dVar, g<T> gVar) {
            this.f7946c = eVar;
            this.f7944a = dVar;
            this.f7945b = gVar;
        }

        @Override // h0.e
        public boolean a(T t6) {
            if (t6 instanceof f) {
                ((f) t6).d().b(true);
            }
            this.f7945b.a(t6);
            return this.f7946c.a(t6);
        }

        @Override // h0.e
        public T b() {
            T tB = this.f7946c.b();
            if (tB == null) {
                tB = this.f7944a.a();
                if (Log.isLoggable("FactoryPools", 2)) {
                    Log.v("FactoryPools", "Created new " + tB.getClass());
                }
            }
            if (tB instanceof f) {
                ((f) tB).d().b(false);
            }
            return tB;
        }
    }

    /* JADX INFO: compiled from: FactoryPools.java */
    public interface f {
        l2.c d();
    }

    /* JADX INFO: compiled from: FactoryPools.java */
    public interface g<T> {
        void a(T t6);
    }

    private static <T extends f> h0.e<T> a(h0.e<T> eVar, d<T> dVar) {
        return b(eVar, dVar, c());
    }

    private static <T> h0.e<T> b(h0.e<T> eVar, d<T> dVar, g<T> gVar) {
        return new e(eVar, dVar, gVar);
    }

    private static <T> g<T> c() {
        return (g<T>) f7943a;
    }

    public static <T extends f> h0.e<T> d(int i7, d<T> dVar) {
        return a(new h0.g(i7), dVar);
    }

    public static <T> h0.e<List<T>> e() {
        return f(20);
    }

    public static <T> h0.e<List<T>> f(int i7) {
        return b(new h0.g(i7), new b(), new c());
    }
}
