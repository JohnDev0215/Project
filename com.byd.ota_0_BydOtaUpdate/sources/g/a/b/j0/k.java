package g.a.b.j0;

import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class k extends f {
    public static final BigInteger s = BigInteger.valueOf(1);
    public static final BigInteger x = BigInteger.valueOf(2);
    public BigInteger y;

    public k(BigInteger bigInteger, i iVar) {
        super(false, iVar);
        this.y = d(bigInteger, iVar);
    }

    public BigInteger c() {
        return this.y;
    }

    public final BigInteger d(BigInteger bigInteger, i iVar) {
        Objects.requireNonNull(bigInteger, "y value cannot be null");
        BigInteger bigInteger2 = x;
        if (bigInteger.compareTo(bigInteger2) < 0 || bigInteger.compareTo(iVar.f().subtract(bigInteger2)) > 0) {
            throw new IllegalArgumentException("invalid DH public key");
        }
        if (iVar.g() == null || s.equals(bigInteger.modPow(iVar.g(), iVar.f()))) {
            return bigInteger;
        }
        throw new IllegalArgumentException("Y value does not appear to be in correct group");
    }

    @Override // g.a.b.j0.f
    public boolean equals(Object obj) {
        return (obj instanceof k) && ((k) obj).c().equals(this.y) && super.equals(obj);
    }

    @Override // g.a.b.j0.f
    public int hashCode() {
        return this.y.hashCode() ^ super.hashCode();
    }
}
