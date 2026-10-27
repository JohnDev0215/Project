package g.a.a.w2;

import g.a.a.o0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class w extends g.a.a.l {
    public x E3;
    public x F3;
    public g.a.a.v2.c G3;
    public u H3;
    public o0 I3;
    public o0 J3;
    public n K3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.r f4151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.j f4152d;
    public g.a.a.j s;
    public a x;
    public g.a.a.v2.c y;

    public w(g.a.a.r rVar) {
        int i2;
        boolean z;
        boolean z2;
        this.f4151c = rVar;
        if (rVar.q(0) instanceof g.a.a.x) {
            this.f4152d = g.a.a.j.o((g.a.a.x) rVar.q(0), true);
            i2 = 0;
        } else {
            this.f4152d = new g.a.a.j(0L);
            i2 = -1;
        }
        if (this.f4152d.q().equals(BigInteger.valueOf(0L))) {
            z2 = false;
            z = true;
        } else if (this.f4152d.q().equals(BigInteger.valueOf(1L))) {
            z = false;
            z2 = true;
        } else {
            if (!this.f4152d.q().equals(BigInteger.valueOf(2L))) {
                throw new IllegalArgumentException("version number not recognised");
            }
            z = false;
            z2 = false;
        }
        this.s = g.a.a.j.n(rVar.q(i2 + 1));
        this.x = a.h(rVar.q(i2 + 2));
        this.y = g.a.a.v2.c.g(rVar.q(i2 + 3));
        g.a.a.r rVar2 = (g.a.a.r) rVar.q(i2 + 4);
        this.E3 = x.h(rVar2.q(0));
        this.F3 = x.h(rVar2.q(1));
        this.G3 = g.a.a.v2.c.g(rVar.q(i2 + 5));
        int i3 = i2 + 6;
        this.H3 = u.i(rVar.q(i3));
        int size = (rVar.size() - i3) - 1;
        if (size != 0 && z) {
            throw new IllegalArgumentException("version 1 certificate contains extra data");
        }
        while (size > 0) {
            g.a.a.x xVar = (g.a.a.x) rVar.q(i3 + size);
            int iQ = xVar.q();
            if (iQ == 1) {
                this.I3 = o0.u(xVar, false);
            } else if (iQ == 2) {
                this.J3 = o0.u(xVar, false);
            } else if (iQ != 3) {
                continue;
            } else {
                if (z2) {
                    throw new IllegalArgumentException("version 2 certificate cannot contain extensions");
                }
                this.K3 = n.h(g.a.a.r.o(xVar, true));
            }
            size--;
        }
    }

    public static w i(Object obj) {
        if (obj instanceof w) {
            return (w) obj;
        }
        if (obj != null) {
            return new w(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f4151c;
    }

    public x g() {
        return this.F3;
    }

    public n h() {
        return this.K3;
    }

    public g.a.a.v2.c j() {
        return this.y;
    }

    public o0 k() {
        return this.I3;
    }

    public g.a.a.j l() {
        return this.s;
    }

    public a m() {
        return this.x;
    }

    public x n() {
        return this.E3;
    }

    public g.a.a.v2.c o() {
        return this.G3;
    }

    public u p() {
        return this.H3;
    }

    public o0 q() {
        return this.J3;
    }

    public int r() {
        return this.f4152d.q().intValue() + 1;
    }
}
