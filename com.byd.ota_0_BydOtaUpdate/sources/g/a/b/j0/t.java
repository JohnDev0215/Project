package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class t extends p {
    public static final BigInteger s = BigInteger.valueOf(1);
    public static final BigInteger x = BigInteger.valueOf(2);
    public BigInteger y;

    public t(BigInteger bigInteger, r rVar) {
        super(false, rVar);
        this.y = d(bigInteger, rVar);
    }

    public BigInteger c() {
        return this.y;
    }

    public final BigInteger d(BigInteger bigInteger, r rVar) {
        if (rVar == null) {
            return bigInteger;
        }
        BigInteger bigInteger2 = x;
        if (bigInteger2.compareTo(bigInteger) > 0 || rVar.b().subtract(bigInteger2).compareTo(bigInteger) < 0 || !s.equals(bigInteger.modPow(rVar.c(), rVar.b()))) {
            throw new IllegalArgumentException("y value does not appear to be in correct group");
        }
        return bigInteger;
    }
}
