package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class r implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5058c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5059d;
    public BigInteger s;
    public u x;

    public r(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f5058c = bigInteger3;
        this.s = bigInteger;
        this.f5059d = bigInteger2;
    }

    public r(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, u uVar) {
        this.f5058c = bigInteger3;
        this.s = bigInteger;
        this.f5059d = bigInteger2;
        this.x = uVar;
    }

    public BigInteger a() {
        return this.f5058c;
    }

    public BigInteger b() {
        return this.s;
    }

    public BigInteger c() {
        return this.f5059d;
    }

    public u d() {
        return this.x;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return rVar.b().equals(this.s) && rVar.c().equals(this.f5059d) && rVar.a().equals(this.f5058c);
    }

    public int hashCode() {
        return (b().hashCode() ^ c().hashCode()) ^ a().hashCode();
    }
}
