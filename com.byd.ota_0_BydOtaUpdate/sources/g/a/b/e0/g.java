package g.a.b.e0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f4693a = BigInteger.valueOf(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4694b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4695c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SecureRandom f4696d;

    public g.a.b.j0.i a() {
        BigInteger[] bigIntegerArrA = h.a(this.f4694b, this.f4695c, this.f4696d);
        BigInteger bigInteger = bigIntegerArrA[0];
        BigInteger bigInteger2 = bigIntegerArrA[1];
        return new g.a.b.j0.i(bigInteger, h.b(bigInteger, bigInteger2, this.f4696d), bigInteger2, f4693a, null);
    }

    public void b(int i2, int i3, SecureRandom secureRandom) {
        this.f4694b = i2;
        this.f4695c = i3;
        this.f4696d = secureRandom;
    }
}
