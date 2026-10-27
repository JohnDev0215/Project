package g.a.b.e0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f4697a = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f4698b = BigInteger.valueOf(2);

    public static BigInteger[] a(int i2, int i3, SecureRandom secureRandom) {
        int i4 = i2 - 1;
        int i5 = i2 >>> 2;
        while (true) {
            BigInteger bigInteger = new BigInteger(i4, 2, secureRandom);
            BigInteger bigIntegerAdd = bigInteger.shiftLeft(1).add(f4697a);
            if (bigIntegerAdd.isProbablePrime(i3) && (i3 <= 2 || bigInteger.isProbablePrime(i3 - 2))) {
                if (g.a.d.b.w.g(bigIntegerAdd) >= i5) {
                    return new BigInteger[]{bigIntegerAdd, bigInteger};
                }
            }
        }
    }

    public static BigInteger b(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger bigIntegerModPow;
        BigInteger bigIntegerSubtract = bigInteger.subtract(f4698b);
        do {
            BigInteger bigInteger3 = f4698b;
            bigIntegerModPow = g.a.f.b.c(bigInteger3, bigIntegerSubtract, secureRandom).modPow(bigInteger3, bigInteger);
        } while (bigIntegerModPow.equals(f4697a));
        return bigIntegerModPow;
    }
}
