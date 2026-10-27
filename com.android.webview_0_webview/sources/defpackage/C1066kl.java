package defpackage;

import java.util.List;

/* JADX INFO: renamed from: kl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1066kl implements InterfaceC1123lm {
    public final AbstractC1010jl a;
    public int b;
    public int c;
    public int d = 0;

    public C1066kl(AbstractC1010jl abstractC1010jl) {
        AbstractC0119Gl.b(abstractC1010jl, "input");
        this.a = abstractC1010jl;
        abstractC1010jl.d = this;
    }

    @Override // defpackage.InterfaceC1123lm
    public void A(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 1) {
            do {
                list.add(Long.valueOf(this.a.s()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iY = this.a.y();
        U(iY);
        int iD = this.a.d() + iY;
        do {
            list.add(Long.valueOf(this.a.s()));
        } while (this.a.d() < iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public String B() throws C0137Hl {
        S(2);
        return this.a.v();
    }

    @Override // defpackage.InterfaceC1123lm
    public long C() throws C0137Hl {
        S(0);
        return this.a.q();
    }

    @Override // defpackage.InterfaceC1123lm
    public String D() throws C0137Hl {
        S(2);
        return this.a.w();
    }

    @Override // defpackage.InterfaceC1123lm
    public int E() throws C0137Hl {
        S(5);
        return this.a.m();
    }

    @Override // defpackage.InterfaceC1123lm
    public void F(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 1) {
            do {
                list.add(Long.valueOf(this.a.n()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iY = this.a.y();
        U(iY);
        int iD = this.a.d() + iY;
        do {
            list.add(Long.valueOf(this.a.n()));
        } while (this.a.d() < iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public void G(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(this.a.p()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Integer.valueOf(this.a.p()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public boolean H() throws C0137Hl {
        S(0);
        return this.a.i();
    }

    @Override // defpackage.InterfaceC1123lm
    public void I(List list, InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) throws C0137Hl {
        int iX;
        int i = this.b;
        if ((i & 7) != 2) {
            throw C0155Il.c();
        }
        do {
            list.add(P(interfaceC1178mm, c1506sl));
            if (this.a.e() || this.d != 0) {
                return;
            } else {
                iX = this.a.x();
            }
        } while (iX == i);
        this.d = iX;
    }

    @Override // defpackage.InterfaceC1123lm
    public void J(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(this.a.l()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Integer.valueOf(this.a.l()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public int K() {
        int i = this.d;
        if (i != 0) {
            this.b = i;
            this.d = 0;
        } else {
            this.b = this.a.x();
        }
        int i2 = this.b;
        if (i2 == 0 || i2 == this.c) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // defpackage.InterfaceC1123lm
    public void L(List list) throws C0137Hl {
        Q(list, false);
    }

    @Override // defpackage.InterfaceC1123lm
    public long M() throws C0137Hl {
        S(1);
        return this.a.s();
    }

    @Override // defpackage.InterfaceC1123lm
    public Object N(InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) throws C0137Hl {
        S(2);
        return P(interfaceC1178mm, c1506sl);
    }

    public final Object O(InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) {
        int i = this.c;
        this.c = ((this.b >>> 3) << 3) | 4;
        try {
            Object objF = interfaceC1178mm.f();
            interfaceC1178mm.c(objF, this, c1506sl);
            interfaceC1178mm.h(objF);
            if (this.b == this.c) {
                return objF;
            }
            throw C0155Il.f();
        } finally {
            this.c = i;
        }
    }

    public final Object P(InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) throws C0155Il {
        int iY = this.a.y();
        AbstractC1010jl abstractC1010jl = this.a;
        if (abstractC1010jl.a >= abstractC1010jl.b) {
            throw new C0155Il("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iH = abstractC1010jl.h(iY);
        Object objF = interfaceC1178mm.f();
        this.a.a++;
        interfaceC1178mm.c(objF, this, c1506sl);
        interfaceC1178mm.h(objF);
        this.a.a(0);
        r5.a--;
        this.a.g(iH);
        return objF;
    }

    public void Q(List list, boolean z) throws C0137Hl {
        int iX;
        int iX2;
        if ((this.b & 7) != 2) {
            throw C0155Il.c();
        }
        if (!(list instanceof InterfaceC0209Ll) || z) {
            do {
                list.add(z ? D() : B());
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        InterfaceC0209Ll interfaceC0209Ll = (InterfaceC0209Ll) list;
        do {
            interfaceC0209Ll.c(d());
            if (this.a.e()) {
                return;
            } else {
                iX2 = this.a.x();
            }
        } while (iX2 == this.b);
        this.d = iX2;
    }

    public final void R(int i) throws C0155Il {
        if (this.a.d() != i) {
            throw C0155Il.g();
        }
    }

    public final void S(int i) throws C0137Hl {
        if ((this.b & 7) != i) {
            throw C0155Il.c();
        }
    }

    public final void T(int i) throws C0155Il {
        if ((i & 3) != 0) {
            throw C0155Il.f();
        }
    }

    public final void U(int i) throws C0155Il {
        if ((i & 7) != 0) {
            throw C0155Il.f();
        }
    }

    @Override // defpackage.InterfaceC1123lm
    public void a(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Long.valueOf(this.a.z()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Long.valueOf(this.a.z()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public void b(List list) throws C0137Hl {
        Q(list, true);
    }

    @Override // defpackage.InterfaceC1123lm
    public Object c(InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) throws C0137Hl {
        S(3);
        return O(interfaceC1178mm, c1506sl);
    }

    @Override // defpackage.InterfaceC1123lm
    public AbstractC0731el d() throws C0137Hl {
        S(2);
        return this.a.j();
    }

    @Override // defpackage.InterfaceC1123lm
    public void e(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(this.a.t()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Integer.valueOf(this.a.t()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public int f() throws C0137Hl {
        S(0);
        return this.a.l();
    }

    @Override // defpackage.InterfaceC1123lm
    public void g(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 2) {
            int iY = this.a.y();
            T(iY);
            int iD = this.a.d() + iY;
            do {
                list.add(Float.valueOf(this.a.o()));
            } while (this.a.d() < iD);
            return;
        }
        if (i != 5) {
            throw C0155Il.c();
        }
        do {
            list.add(Float.valueOf(this.a.o()));
            if (this.a.e()) {
                return;
            } else {
                iX = this.a.x();
            }
        } while (iX == this.b);
        this.d = iX;
    }

    @Override // defpackage.InterfaceC1123lm
    public int h() throws C0137Hl {
        S(0);
        return this.a.y();
    }

    @Override // defpackage.InterfaceC1123lm
    public int i() throws C0137Hl {
        S(0);
        return this.a.p();
    }

    @Override // defpackage.InterfaceC1123lm
    public int j() {
        return this.b;
    }

    @Override // defpackage.InterfaceC1123lm
    public long k() throws C0137Hl {
        S(0);
        return this.a.z();
    }

    @Override // defpackage.InterfaceC1123lm
    public void l(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 2) {
            int iY = this.a.y();
            T(iY);
            int iD = this.a.d() + iY;
            do {
                list.add(Integer.valueOf(this.a.m()));
            } while (this.a.d() < iD);
            return;
        }
        if (i != 5) {
            throw C0155Il.c();
        }
        do {
            list.add(Integer.valueOf(this.a.m()));
            if (this.a.e()) {
                return;
            } else {
                iX = this.a.x();
            }
        } while (iX == this.b);
        this.d = iX;
    }

    @Override // defpackage.InterfaceC1123lm
    public long m() throws C0137Hl {
        S(1);
        return this.a.n();
    }

    @Override // defpackage.InterfaceC1123lm
    public double n() throws C0137Hl {
        S(1);
        return this.a.k();
    }

    @Override // defpackage.InterfaceC1123lm
    public void o(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 2) {
            int iY = this.a.y();
            T(iY);
            int iD = this.a.d() + iY;
            do {
                list.add(Integer.valueOf(this.a.r()));
            } while (this.a.d() < iD);
            return;
        }
        if (i != 5) {
            throw C0155Il.c();
        }
        do {
            list.add(Integer.valueOf(this.a.r()));
            if (this.a.e()) {
                return;
            } else {
                iX = this.a.x();
            }
        } while (iX == this.b);
        this.d = iX;
    }

    @Override // defpackage.InterfaceC1123lm
    public int p() throws C0137Hl {
        S(0);
        return this.a.t();
    }

    @Override // defpackage.InterfaceC1123lm
    public void q(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Long.valueOf(this.a.u()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Long.valueOf(this.a.u()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public void r(List list, InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) throws C0137Hl {
        int iX;
        int i = this.b;
        if ((i & 7) != 3) {
            throw C0155Il.c();
        }
        do {
            list.add(O(interfaceC1178mm, c1506sl));
            if (this.a.e() || this.d != 0) {
                return;
            } else {
                iX = this.a.x();
            }
        } while (iX == i);
        this.d = iX;
    }

    @Override // defpackage.InterfaceC1123lm
    public float s() throws C0137Hl {
        S(5);
        return this.a.o();
    }

    @Override // defpackage.InterfaceC1123lm
    public long t() throws C0137Hl {
        S(0);
        return this.a.u();
    }

    @Override // defpackage.InterfaceC1123lm
    public int u() throws C0137Hl {
        S(5);
        return this.a.r();
    }

    @Override // defpackage.InterfaceC1123lm
    public void v(List list) throws C0137Hl {
        int iX;
        if ((this.b & 7) != 2) {
            throw C0155Il.c();
        }
        do {
            list.add(d());
            if (this.a.e()) {
                return;
            } else {
                iX = this.a.x();
            }
        } while (iX == this.b);
        this.d = iX;
    }

    @Override // defpackage.InterfaceC1123lm
    public void w(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(this.a.y()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Integer.valueOf(this.a.y()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public void x(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 1) {
            do {
                list.add(Double.valueOf(this.a.k()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iY = this.a.y();
        U(iY);
        int iD = this.a.d() + iY;
        do {
            list.add(Double.valueOf(this.a.k()));
        } while (this.a.d() < iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public void y(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Long.valueOf(this.a.q()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Long.valueOf(this.a.q()));
        } while (this.a.d() < iD);
        R(iD);
    }

    @Override // defpackage.InterfaceC1123lm
    public void z(List list) throws C0155Il {
        int iX;
        int i = this.b & 7;
        if (i == 0) {
            do {
                list.add(Boolean.valueOf(this.a.i()));
                if (this.a.e()) {
                    return;
                } else {
                    iX = this.a.x();
                }
            } while (iX == this.b);
            this.d = iX;
            return;
        }
        if (i != 2) {
            throw C0155Il.c();
        }
        int iD = this.a.d() + this.a.y();
        do {
            list.add(Boolean.valueOf(this.a.i()));
        } while (this.a.d() < iD);
        R(iD);
    }
}
