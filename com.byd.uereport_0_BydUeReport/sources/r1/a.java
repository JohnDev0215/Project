package r1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<K, A> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d<K> f7743c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b2.c<A> f7745e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<b> f7741a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f7742b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f7744d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public A f7746f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f7747g = -1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f7748h = -1.0f;

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public interface b {
        void c();
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public static final class c<T> implements d<T> {
        public c() {
        }

        @Override // r1.a.d
        public float a() {
            return 1.0f;
        }

        @Override // r1.a.d
        public boolean b(float f8) {
            throw new IllegalStateException("not implemented");
        }

        @Override // r1.a.d
        public float c() {
            return 0.0f;
        }

        @Override // r1.a.d
        public b2.a<T> d() {
            throw new IllegalStateException("not implemented");
        }

        @Override // r1.a.d
        public boolean e(float f8) {
            return false;
        }

        @Override // r1.a.d
        public boolean isEmpty() {
            return true;
        }
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public interface d<T> {
        float a();

        boolean b(float f8);

        float c();

        b2.a<T> d();

        boolean e(float f8);

        boolean isEmpty();
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public static final class e<T> implements d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<? extends b2.a<T>> f7749a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b2.a<T> f7751c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f7752d = -1.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b2.a<T> f7750b = f(0.0f);

        public e(List<? extends b2.a<T>> list) {
            this.f7749a = list;
        }

        @Override // r1.a.d
        public float a() {
            return this.f7749a.get(r0.size() - 1).b();
        }

        @Override // r1.a.d
        public boolean b(float f8) {
            b2.a<T> aVar = this.f7751c;
            b2.a<T> aVar2 = this.f7750b;
            if (aVar == aVar2 && this.f7752d == f8) {
                return true;
            }
            this.f7751c = aVar2;
            this.f7752d = f8;
            return false;
        }

        @Override // r1.a.d
        public float c() {
            return this.f7749a.get(0).e();
        }

        @Override // r1.a.d
        public b2.a<T> d() {
            return this.f7750b;
        }

        @Override // r1.a.d
        public boolean e(float f8) {
            if (this.f7750b.a(f8)) {
                return !this.f7750b.h();
            }
            this.f7750b = f(f8);
            return true;
        }

        public final b2.a<T> f(float f8) {
            List<? extends b2.a<T>> list = this.f7749a;
            b2.a<T> aVar = list.get(list.size() - 1);
            if (f8 >= aVar.e()) {
                return aVar;
            }
            for (int size = this.f7749a.size() - 2; size >= 1; size--) {
                b2.a<T> aVar2 = this.f7749a.get(size);
                if (this.f7750b != aVar2 && aVar2.a(f8)) {
                    return aVar2;
                }
            }
            return this.f7749a.get(0);
        }

        @Override // r1.a.d
        public boolean isEmpty() {
            return false;
        }
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public static final class f<T> implements d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b2.a<T> f7753a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f7754b = -1.0f;

        public f(List<? extends b2.a<T>> list) {
            this.f7753a = list.get(0);
        }

        @Override // r1.a.d
        public float a() {
            return this.f7753a.b();
        }

        @Override // r1.a.d
        public boolean b(float f8) {
            if (this.f7754b == f8) {
                return true;
            }
            this.f7754b = f8;
            return false;
        }

        @Override // r1.a.d
        public float c() {
            return this.f7753a.e();
        }

        @Override // r1.a.d
        public b2.a<T> d() {
            return this.f7753a;
        }

        @Override // r1.a.d
        public boolean e(float f8) {
            return !this.f7753a.h();
        }

        @Override // r1.a.d
        public boolean isEmpty() {
            return false;
        }
    }

    public a(List<? extends b2.a<K>> list) {
        this.f7743c = n(list);
    }

    public static <T> d<T> n(List<? extends b2.a<T>> list) {
        return list.isEmpty() ? new c() : list.size() == 1 ? new f(list) : new e(list);
    }

    public void a(b bVar) {
        this.f7741a.add(bVar);
    }

    public b2.a<K> b() {
        o1.d.a("BaseKeyframeAnimation#getCurrentKeyframe");
        b2.a<K> aVarD = this.f7743c.d();
        o1.d.b("BaseKeyframeAnimation#getCurrentKeyframe");
        return aVarD;
    }

    public float c() {
        if (this.f7748h == -1.0f) {
            this.f7748h = this.f7743c.a();
        }
        return this.f7748h;
    }

    public float d() {
        b2.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return aVarB.f2047d.getInterpolation(e());
    }

    public float e() {
        if (this.f7742b) {
            return 0.0f;
        }
        b2.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return (this.f7744d - aVarB.e()) / (aVarB.b() - aVarB.e());
    }

    public float f() {
        return this.f7744d;
    }

    public final float g() {
        if (this.f7747g == -1.0f) {
            this.f7747g = this.f7743c.c();
        }
        return this.f7747g;
    }

    public A h() {
        float fD = d();
        if (this.f7745e == null && this.f7743c.b(fD)) {
            return this.f7746f;
        }
        A aI = i(b(), fD);
        this.f7746f = aI;
        return aI;
    }

    public abstract A i(b2.a<K> aVar, float f8);

    public void j() {
        for (int i8 = 0; i8 < this.f7741a.size(); i8++) {
            this.f7741a.get(i8).c();
        }
    }

    public void k() {
        this.f7742b = true;
    }

    public void l(float f8) {
        if (this.f7743c.isEmpty()) {
            return;
        }
        if (f8 < g()) {
            f8 = g();
        } else if (f8 > c()) {
            f8 = c();
        }
        if (f8 == this.f7744d) {
            return;
        }
        this.f7744d = f8;
        if (this.f7743c.e(f8)) {
            j();
        }
    }

    public void m(b2.c<A> cVar) {
        b2.c<A> cVar2 = this.f7745e;
        if (cVar2 != null) {
            cVar2.c(null);
        }
        this.f7745e = cVar;
        if (cVar != null) {
            cVar.c(this);
        }
    }
}
