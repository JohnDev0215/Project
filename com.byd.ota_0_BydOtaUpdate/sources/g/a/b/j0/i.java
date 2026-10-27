package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class i implements g.a.b.i {
    public int E3;
    public n F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5032c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5033d;
    public BigInteger s;
    public BigInteger x;
    public int y;

    public i(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    public i(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, a(i2), i2, null, null);
    }

    public i(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2, int i3, BigInteger bigInteger4, n nVar) {
        if (i3 != 0) {
            if (i3 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i3 < i2) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (i2 > bigInteger.bitLength()) {
            throw new IllegalArgumentException("unsafe p value so small specific l required");
        }
        this.f5032c = bigInteger2;
        this.f5033d = bigInteger;
        this.s = bigInteger3;
        this.y = i2;
        this.E3 = i3;
        this.x = bigInteger4;
        this.F3 = nVar;
    }

    public i(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, n nVar) {
        this(bigInteger, bigInteger2, bigInteger3, 160, 0, bigInteger4, nVar);
    }

    public static int a(int i2) {
        if (i2 != 0 && i2 < 160) {
            return i2;
        }
        return 160;
    }

    public BigInteger b() {
        return this.f5032c;
    }

    public BigInteger c() {
        return this.x;
    }

    public int d() {
        return this.E3;
    }

    public int e() {
        return this.y;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (g() != null) {
            if (!g().equals(iVar.g())) {
                return false;
            }
        } else if (iVar.g() != null) {
            return false;
        }
        return iVar.f().equals(this.f5033d) && iVar.b().equals(this.f5032c);
    }

    public BigInteger f() {
        return this.f5033d;
    }

    public BigInteger g() {
        return this.s;
    }

    public n h() {
        return this.F3;
    }

    public int hashCode() {
        return (f().hashCode() ^ b().hashCode()) ^ (g() != null ? g().hashCode() : 0);
    }
}
