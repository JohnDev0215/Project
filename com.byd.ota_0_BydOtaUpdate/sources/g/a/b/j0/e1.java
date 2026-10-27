package g.a.b.j0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e1 extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final BigInteger f5020d = BigInteger.valueOf(1);
    public BigInteger s;
    public BigInteger x;

    public e1(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        super(z);
        if (!z && (bigInteger2.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA publicExponent is even");
        }
        this.s = d(bigInteger);
        this.x = bigInteger2;
    }

    public BigInteger b() {
        return this.x;
    }

    public BigInteger c() {
        return this.s;
    }

    public final BigInteger d(BigInteger bigInteger) {
        if ((bigInteger.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA modulus is even");
        }
        if (bigInteger.gcd(new BigInteger("1451887755777639901511587432083070202422614380984889313550570919659315177065956574359078912654149167643992684236991305777574330831666511589145701059710742276692757882915756220901998212975756543223550490431013061082131040808010565293748926901442915057819663730454818359472391642885328171302299245556663073719855")).equals(f5020d)) {
            return bigInteger;
        }
        throw new IllegalArgumentException("RSA modulus has a small prime factor");
    }
}
