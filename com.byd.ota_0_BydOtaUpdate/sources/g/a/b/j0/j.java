package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class j extends f {
    public BigInteger s;

    public j(BigInteger bigInteger, i iVar) {
        super(true, iVar);
        this.s = bigInteger;
    }

    public BigInteger c() {
        return this.s;
    }

    @Override // g.a.b.j0.f
    public boolean equals(Object obj) {
        return (obj instanceof j) && ((j) obj).c().equals(this.s) && super.equals(obj);
    }

    @Override // g.a.b.j0.f
    public int hashCode() {
        return this.s.hashCode() ^ super.hashCode();
    }
}
