package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class h0 extends e0 {
    public BigInteger s;

    public h0(BigInteger bigInteger, f0 f0Var) {
        super(false, f0Var);
        this.s = bigInteger;
    }

    public BigInteger c() {
        return this.s;
    }

    @Override // g.a.b.j0.e0
    public boolean equals(Object obj) {
        return (obj instanceof h0) && ((h0) obj).c().equals(this.s) && super.equals(obj);
    }

    @Override // g.a.b.j0.e0
    public int hashCode() {
        return this.s.hashCode() ^ super.hashCode();
    }
}
