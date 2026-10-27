package g.a.b.e0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f4689a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f4690b = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f4691c = BigInteger.valueOf(2);

    public BigInteger a(g.a.b.j0.i iVar, SecureRandom secureRandom) {
        BigInteger bigIntegerC;
        BigInteger bit;
        int iD = iVar.d();
        if (iD != 0) {
            int i2 = iD >>> 2;
            do {
                bit = new BigInteger(iD, secureRandom).setBit(iD - 1);
            } while (g.a.d.b.w.g(bit) < i2);
            return bit;
        }
        BigInteger bigInteger = f4691c;
        int iE = iVar.e();
        BigInteger bigIntegerShiftLeft = iE != 0 ? f4690b.shiftLeft(iE - 1) : bigInteger;
        BigInteger bigIntegerG = iVar.g();
        if (bigIntegerG == null) {
            bigIntegerG = iVar.f();
        }
        BigInteger bigIntegerSubtract = bigIntegerG.subtract(bigInteger);
        int iBitLength = bigIntegerSubtract.bitLength() >>> 2;
        do {
            bigIntegerC = g.a.f.b.c(bigIntegerShiftLeft, bigIntegerSubtract, secureRandom);
        } while (g.a.d.b.w.g(bigIntegerC) < iBitLength);
        return bigIntegerC;
    }

    public BigInteger b(g.a.b.j0.i iVar, BigInteger bigInteger) {
        return iVar.b().modPow(bigInteger, iVar.f());
    }
}
