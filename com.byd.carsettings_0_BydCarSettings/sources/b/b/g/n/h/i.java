package b.b.g.n.h;

import com.byd.carsettings.ui.fragment.carsettings.FreezerFragment;

/* JADX INFO: compiled from: FreezerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends b.b.m.e.g<FreezerFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.d.g f1015a = new b.b.g.d.g();

    public void A(int i) {
        b.b.g.n.q.m.p0().t2(i);
    }

    public void B(int i) {
        b.b.g.n.q.m.p0().h3(i);
    }

    public void C(int i, int i2) {
        b.b.g.n.q.m.p0().i3(i2);
    }

    public void D(int i) {
        b.b.g.n.q.m.p0().j3(i);
    }

    public int E(int i) {
        return b.b.g.n.q.m.p0().w3(i);
    }

    public boolean c() {
        return b.b.g.n.q.m.p0().A() == 1;
    }

    public int d() {
        return Math.max(i() == 2 ? b.b.g.n.q.m.p0().z() : b.b.g.n.q.m.p0().B(), 1);
    }

    public int e(boolean z, boolean z2) {
        return z ? z2 ? 13 : 1 : z2 ? 22 : 1;
    }

    public int f() {
        return this.f1015a.b();
    }

    public int g() {
        return this.f1015a.e();
    }

    public int h() {
        return b.b.g.n.q.m.p0().Q();
    }

    public int i() {
        return this.f1015a.f();
    }

    public int j() {
        return b.b.g.n.q.m.p0().T();
    }

    public int l() {
        return b.b.g.n.q.m.p0().a1();
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m(int r8) {
        /*
            r7 = this;
            int r0 = r7.q()
            r1 = 0
            r2 = 1
            if (r0 != r2) goto La
            r0 = r2
            goto Lb
        La:
            r0 = r1
        Lb:
            if (r8 == r2) goto L81
            r3 = 2
            r4 = 28
            if (r8 == r3) goto L17
            r3 = 4
            if (r8 == r3) goto L4d
            goto L80
        L17:
            b.b.g.n.q.m r8 = b.b.g.n.q.m.p0()
            int r8 = r8.b1()
            r3 = 35
            r5 = 95
            if (r0 == 0) goto L27
            r6 = r3
            goto L28
        L27:
            r6 = r5
        L28:
            if (r8 < r6) goto L39
            if (r0 == 0) goto L2f
            r6 = 50
            goto L31
        L2f:
            r6 = 122(0x7a, float:1.71E-43)
        L31:
            if (r8 > r6) goto L39
            if (r0 == 0) goto L36
            goto L37
        L36:
            r3 = r5
        L37:
            int r8 = r8 - r3
            return r8
        L39:
            b.b.g.n.q.m r8 = b.b.g.n.q.m.p0()
            int r8 = r8.X()
            if (r8 < r2) goto L4d
            if (r0 == 0) goto L48
            r3 = 16
            goto L49
        L48:
            r3 = r4
        L49:
            if (r8 > r3) goto L4d
            int r8 = r8 - r2
            return r8
        L4d:
            b.b.g.n.q.m r8 = b.b.g.n.q.m.p0()
            int r8 = r8.b1()
            r3 = 26
            r5 = 45
            if (r0 == 0) goto L5d
            r6 = r3
            goto L5e
        L5d:
            r6 = r5
        L5e:
            if (r8 < r6) goto L6d
            if (r0 == 0) goto L63
            goto L65
        L63:
            r4 = 49
        L65:
            if (r8 > r4) goto L6d
            if (r0 == 0) goto L6a
            goto L6b
        L6a:
            r3 = r5
        L6b:
            int r8 = r8 - r3
            return r8
        L6d:
            b.b.g.n.q.m r8 = b.b.g.n.q.m.p0()
            int r8 = r8.Y()
            if (r8 < r2) goto L80
            if (r0 == 0) goto L7b
            r0 = 3
            goto L7c
        L7b:
            r0 = 5
        L7c:
            if (r8 > r0) goto L80
            int r8 = r8 - r2
            return r8
        L80:
            return r1
        L81:
            int r8 = r7.f()
            if (r8 != r2) goto L88
            r1 = r2
        L88:
            b.b.g.n.q.m r8 = b.b.g.n.q.m.p0()
            int r8 = r8.b1()
            int r2 = r7.e(r0, r1)
            r3 = 25
            r4 = 44
            if (r8 < r2) goto La7
            if (r0 == 0) goto L9e
            r2 = r3
            goto L9f
        L9e:
            r2 = r4
        L9f:
            if (r8 > r2) goto La7
            int r0 = r7.e(r0, r1)
        La5:
            int r8 = r8 - r0
            return r8
        La7:
            b.b.g.n.q.m r8 = b.b.g.n.q.m.p0()
            int r8 = r8.W()
            int r2 = r7.e(r0, r1)
            if (r8 < r2) goto Lc0
            if (r0 == 0) goto Lb8
            goto Lb9
        Lb8:
            r3 = r4
        Lb9:
            if (r8 > r3) goto Lc0
            int r0 = r7.e(r0, r1)
            goto La5
        Lc0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.g.n.h.i.m(int):int");
    }

    public int n() {
        return b.b.g.n.q.m.p0().c1();
    }

    public int o() {
        return b.b.g.n.q.m.p0().v1();
    }

    public int q() {
        return b.b.g.n.q.a.a().b();
    }

    public int r() {
        return b.b.g.n.q.m.p0().H1();
    }

    public boolean s() {
        return this.f1015a.g();
    }

    public boolean t() {
        return this.f1015a.h();
    }

    public boolean u() {
        return this.f1015a.i();
    }

    public boolean v() {
        return this.f1015a.j();
    }

    public boolean w() {
        return this.f1015a.k();
    }

    public boolean x() {
        return this.f1015a.l();
    }

    public void y(int i) {
        b.b.g.n.q.m.p0().j2(i);
    }

    public void z(int i) {
        b.b.g.n.q.m.p0().s2(i);
    }
}
