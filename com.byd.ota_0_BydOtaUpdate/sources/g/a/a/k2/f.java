package g.a.a.k2;

import g.a.a.c1;
import g.a.a.j;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class f extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f3900c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BigInteger f3901d;
    public final BigInteger s;
    public final BigInteger x;
    public final BigInteger y;

    public f(r rVar) {
        if (rVar.size() != 4 && rVar.size() != 5) {
            throw new IllegalArgumentException("invalid sequence: size = " + rVar.size());
        }
        this.f3900c = g.a.f.a.f(n.n(rVar.q(0)).p());
        this.f3901d = j.n(rVar.q(1)).q();
        this.s = j.n(rVar.q(2)).q();
        this.x = j.n(rVar.q(3)).q();
        this.y = rVar.size() == 5 ? j.n(rVar.q(4)).q() : null;
    }

    public f(byte[] bArr, int i2, int i3, int i4, int i5) {
        this(bArr, BigInteger.valueOf(i2), BigInteger.valueOf(i3), BigInteger.valueOf(i4), BigInteger.valueOf(i5));
    }

    public f(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f3900c = g.a.f.a.f(bArr);
        this.f3901d = bigInteger;
        this.s = bigInteger2;
        this.x = bigInteger3;
        this.y = bigInteger4;
    }

    public static f i(Object obj) {
        if (obj instanceof f) {
            return (f) obj;
        }
        if (obj != null) {
            return new f(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new y0(this.f3900c));
        fVar.a(new j(this.f3901d));
        fVar.a(new j(this.s));
        fVar.a(new j(this.x));
        BigInteger bigInteger = this.y;
        if (bigInteger != null) {
            fVar.a(new j(bigInteger));
        }
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.s;
    }

    public BigInteger h() {
        return this.f3901d;
    }

    public BigInteger j() {
        return this.y;
    }

    public BigInteger k() {
        return this.x;
    }

    public byte[] l() {
        return g.a.f.a.f(this.f3900c);
    }
}
