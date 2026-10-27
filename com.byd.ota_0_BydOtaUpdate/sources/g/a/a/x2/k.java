package g.a.a.x2;

import g.a.a.c1;
import g.a.a.q;
import g.a.a.r;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class k extends g.a.a.l implements m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.m f4193c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public q f4194d;

    public k(int i2, int i3) {
        this(i2, i3, 0, 0);
    }

    public k(int i2, int i3, int i4, int i5) {
        this.f4193c = m.s2;
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(i2));
        if (i4 == 0) {
            if (i5 != 0) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            fVar.a(m.u2);
            fVar.a(new g.a.a.j(i3));
        } else {
            if (i4 <= i3 || i5 <= i4) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            fVar.a(m.v2);
            g.a.a.f fVar2 = new g.a.a.f();
            fVar2.a(new g.a.a.j(i3));
            fVar2.a(new g.a.a.j(i4));
            fVar2.a(new g.a.a.j(i5));
            fVar.a(new c1(fVar2));
        }
        this.f4194d = new c1(fVar);
    }

    public k(r rVar) {
        this.f4193c = g.a.a.m.s(rVar.q(0));
        this.f4194d = rVar.q(1).c();
    }

    public k(BigInteger bigInteger) {
        this.f4193c = m.r2;
        this.f4194d = new g.a.a.j(bigInteger);
    }

    public static k h(Object obj) {
        if (obj instanceof k) {
            return (k) obj;
        }
        if (obj != null) {
            return new k(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4193c);
        fVar.a(this.f4194d);
        return new c1(fVar);
    }

    public g.a.a.m g() {
        return this.f4193c;
    }

    public q i() {
        return this.f4194d;
    }
}
