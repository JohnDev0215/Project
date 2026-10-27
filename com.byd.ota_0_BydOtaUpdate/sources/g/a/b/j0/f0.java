package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class f0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5022c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5023d;
    public int s;

    public f0(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public f0(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        this.f5022c = bigInteger2;
        this.f5023d = bigInteger;
        this.s = i2;
    }

    public BigInteger a() {
        return this.f5022c;
    }

    public int b() {
        return this.s;
    }

    public BigInteger c() {
        return this.f5023d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        return f0Var.c().equals(this.f5023d) && f0Var.a().equals(this.f5022c) && f0Var.b() == this.s;
    }

    public int hashCode() {
        return (c().hashCode() ^ a().hashCode()) + this.s;
    }
}
