package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class k0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5036c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5037d;
    public BigInteger s;
    public n0 x;

    public k0(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f5036c = bigInteger;
        this.f5037d = bigInteger2;
        this.s = bigInteger3;
    }

    public k0(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, n0 n0Var) {
        this.s = bigInteger3;
        this.f5036c = bigInteger;
        this.f5037d = bigInteger2;
        this.x = n0Var;
    }

    public BigInteger a() {
        return this.s;
    }

    public BigInteger b() {
        return this.f5036c;
    }

    public BigInteger c() {
        return this.f5037d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k0)) {
            return false;
        }
        k0 k0Var = (k0) obj;
        return k0Var.b().equals(this.f5036c) && k0Var.c().equals(this.f5037d) && k0Var.a().equals(this.s);
    }

    public int hashCode() {
        return (this.f5036c.hashCode() ^ this.f5037d.hashCode()) ^ this.s.hashCode();
    }
}
