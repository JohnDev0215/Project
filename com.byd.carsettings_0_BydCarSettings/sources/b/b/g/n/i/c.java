package b.b.g.n.i;

import b.b.g.n.q.m;

/* JADX INFO: compiled from: RadioButton2Presenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b.b.m.e.g<b.b.g.q.f.a.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1031a = "c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m f1032b;

    public int c(int i) {
        int i2 = i - 1;
        return (i2 == 0 || i2 != 1) ? 0 : 1;
    }

    public int d() {
        return this.f1032b.I() == 1 ? 0 : 1;
    }

    public int e() {
        return this.f1032b.L() == 2 ? 0 : 1;
    }

    public int f() {
        return this.f1032b.s1() == 1 ? 0 : 1;
    }

    public int g() {
        return c(this.f1032b.M());
    }

    public void h() {
        this.f1032b = m.p0();
    }

    public void i(int i) {
        this.f1032b.p2(i == 0 ? 1 : 2);
    }

    public void j(int i) {
        this.f1032b.q2(i == 0 ? 2 : 3);
    }

    public void l(int i) {
        this.f1032b.u3(i == 0 ? 1 : 3);
    }

    public void m(int i) {
        int i2 = 0;
        b.b.m.c.a.a(f1031a, "----setSimulationEngineSounByIndex------index = " + i);
        if (i != 0) {
            if (i == 1) {
                i2 = 1;
            } else if (i == 2) {
                i2 = 2;
            }
        }
        this.f1032b.r2(i2 + 1);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1032b = null;
    }
}
