package b.a.a.i.o;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f936a;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements g.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ e f937a;

        public a(e eVar) {
            this.f937a = eVar;
        }

        @Override // b.a.a.i.o.d.g.b
        public void a() {
            this.f937a.a(d.this);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b implements g.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f939a;

        public b(c cVar) {
            this.f939a = cVar;
        }

        @Override // b.a.a.i.o.d.g.a
        public void a() {
            this.f939a.b(d.this);
        }

        @Override // b.a.a.i.o.d.g.a
        public void onAnimationCancel() {
            this.f939a.c(d.this);
        }

        @Override // b.a.a.i.o.d.g.a
        public void onAnimationEnd() {
            this.f939a.a(d.this);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        void a(d dVar);

        void b(d dVar);

        void c(d dVar);
    }

    /* JADX INFO: renamed from: b.a.a.i.o.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static class C0007d implements c {
        @Override // b.a.a.i.o.d.c
        public void b(d dVar) {
        }

        @Override // b.a.a.i.o.d.c
        public void c(d dVar) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface e {
        void a(d dVar);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface f {
        d createAnimator();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class g {

        /* JADX INFO: compiled from: caradapter-bug */
        public interface a {
            void a();

            void onAnimationCancel();

            void onAnimationEnd();
        }

        /* JADX INFO: compiled from: caradapter-bug */
        public interface b {
            void a();
        }

        public abstract void a();

        public abstract float b();

        public abstract int c();

        public abstract long d();

        public abstract boolean e();

        public abstract void f(int i);

        public abstract void g(float f, float f2);

        public abstract void h(int i, int i2);

        public abstract void i(Interpolator interpolator);

        public abstract void j(a aVar);

        public abstract void k(b bVar);

        public abstract void l();
    }

    public d(g gVar) {
        this.f936a = gVar;
    }

    public void a() {
        this.f936a.a();
    }

    public float b() {
        return this.f936a.b();
    }

    public int c() {
        return this.f936a.c();
    }

    public long d() {
        return this.f936a.d();
    }

    public boolean e() {
        return this.f936a.e();
    }

    public void f(int i) {
        this.f936a.f(i);
    }

    public void g(float f2, float f3) {
        this.f936a.g(f2, f3);
    }

    public void h(int i, int i2) {
        this.f936a.h(i, i2);
    }

    public void i(Interpolator interpolator) {
        this.f936a.i(interpolator);
    }

    public void j(c cVar) {
        if (cVar != null) {
            this.f936a.j(new b(cVar));
        } else {
            this.f936a.j(null);
        }
    }

    public void k(e eVar) {
        if (eVar != null) {
            this.f936a.k(new a(eVar));
        } else {
            this.f936a.k(null);
        }
    }

    public void l() {
        this.f936a.l();
    }
}
