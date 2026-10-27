package g.a.a.x2;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g extends g.a.a.l implements m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f4186c = BigInteger.valueOf(1);
    public BigInteger E3;
    public byte[] F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f4187d;
    public g.a.d.b.d s;
    public i x;
    public BigInteger y;

    public g(r rVar) {
        if (!(rVar.q(0) instanceof g.a.a.j) || !((g.a.a.j) rVar.q(0)).q().equals(f4186c)) {
            throw new IllegalArgumentException("bad version in X9ECParameters");
        }
        this.y = ((g.a.a.j) rVar.q(4)).q();
        if (rVar.size() == 6) {
            this.E3 = ((g.a.a.j) rVar.q(5)).q();
        }
        f fVar = new f(k.h(rVar.q(1)), this.y, this.E3, r.n(rVar.q(2)));
        this.s = fVar.g();
        g.a.a.e eVarQ = rVar.q(3);
        if (eVarQ instanceof i) {
            this.x = (i) eVarQ;
        } else {
            this.x = new i(this.s, (n) eVarQ);
        }
        this.F3 = fVar.h();
    }

    public g(g.a.d.b.d dVar, i iVar, BigInteger bigInteger, BigInteger bigInteger2) {
        this(dVar, iVar, bigInteger, bigInteger2, (byte[]) null);
    }

    public g(g.a.d.b.d dVar, i iVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        k kVar;
        this.s = dVar;
        this.x = iVar;
        this.y = bigInteger;
        this.E3 = bigInteger2;
        this.F3 = bArr;
        if (g.a.d.b.b.m(dVar)) {
            kVar = new k(dVar.t().c());
        } else {
            if (!g.a.d.b.b.k(dVar)) {
                throw new IllegalArgumentException("'curve' is of an unsupported type");
            }
            int[] iArrA = ((g.a.d.c.f) dVar.t()).a().a();
            if (iArrA.length == 3) {
                kVar = new k(iArrA[2], iArrA[1]);
            } else {
                if (iArrA.length != 5) {
                    throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
                }
                kVar = new k(iArrA[4], iArrA[1], iArrA[2], iArrA[3]);
            }
        }
        this.f4187d = kVar;
    }

    public g(g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this(dVar, new i(hVar), bigInteger, bigInteger2, bArr);
    }

    public static g j(Object obj) {
        if (obj instanceof g) {
            return (g) obj;
        }
        if (obj != null) {
            return new g(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(f4186c));
        fVar.a(this.f4187d);
        fVar.a(new f(this.s, this.F3));
        fVar.a(this.x);
        fVar.a(new g.a.a.j(this.y));
        BigInteger bigInteger = this.E3;
        if (bigInteger != null) {
            fVar.a(new g.a.a.j(bigInteger));
        }
        return new c1(fVar);
    }

    public g.a.d.b.d g() {
        return this.s;
    }

    public g.a.d.b.h h() {
        return this.x.g();
    }

    public BigInteger i() {
        return this.E3;
    }

    public BigInteger k() {
        return this.y;
    }

    public byte[] l() {
        return this.F3;
    }
}
