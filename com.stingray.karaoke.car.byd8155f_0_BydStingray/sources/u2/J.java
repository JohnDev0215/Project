package u2;

import e2.C1208p;

/* JADX INFO: loaded from: classes.dex */
public final class J implements w2.q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w2.q f22673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e2.g0 f22674b;

    public J(w2.q qVar, e2.g0 g0Var) {
        this.f22673a = qVar;
        this.f22674b = g0Var;
    }

    @Override // w2.q
    public final void a(boolean z6) {
        this.f22673a.a(z6);
    }

    @Override // w2.q
    public final C1208p b(int i7) {
        return this.f22674b.d[this.f22673a.d(i7)];
    }

    @Override // w2.q
    public final void c() {
        this.f22673a.c();
    }

    @Override // w2.q
    public final int d(int i7) {
        return this.f22673a.d(i7);
    }

    @Override // w2.q
    public final void e() {
        this.f22673a.e();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof J)) {
            return false;
        }
        J j3 = (J) obj;
        return this.f22673a.equals(j3.f22673a) && this.f22674b.equals(j3.f22674b);
    }

    @Override // w2.q
    public final int f() {
        return this.f22673a.f();
    }

    @Override // w2.q
    public final e2.g0 g() {
        return this.f22674b;
    }

    @Override // w2.q
    public final C1208p h() {
        return this.f22674b.d[this.f22673a.f()];
    }

    public final int hashCode() {
        return this.f22673a.hashCode() + ((this.f22674b.hashCode() + 527) * 31);
    }

    @Override // w2.q
    public final void i(float f10) {
        this.f22673a.i(f10);
    }

    @Override // w2.q
    public final void j() {
        this.f22673a.j();
    }

    @Override // w2.q
    public final void k() {
        this.f22673a.k();
    }

    @Override // w2.q
    public final int l(int i7) {
        return this.f22673a.l(i7);
    }

    @Override // w2.q
    public final int length() {
        return this.f22673a.length();
    }
}
