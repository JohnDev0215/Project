package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g0 extends e0 {
    public BigInteger s;

    public g0(BigInteger bigInteger, f0 f0Var) {
        super(true, f0Var);
        this.s = bigInteger;
    }

    public BigInteger c() {
        return this.s;
    }

    @Override // g.a.b.j0.e0
    public boolean equals(Object obj) {
        if ((obj instanceof g0) && ((g0) obj).c().equals(this.s)) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // g.a.b.j0.e0
    public int hashCode() {
        return c().hashCode();
    }
}
