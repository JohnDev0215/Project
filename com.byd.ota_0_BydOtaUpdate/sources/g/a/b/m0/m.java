package g.a.b.m0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class m implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5166a = BigInteger.valueOf(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BigInteger f5167b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SecureRandom f5168c;

    @Override // g.a.b.m0.a
    public BigInteger a() {
        int iBitLength = this.f5167b.bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(iBitLength, this.f5168c);
            if (!bigInteger.equals(f5166a) && bigInteger.compareTo(this.f5167b) < 0) {
                return bigInteger;
            }
        }
    }

    @Override // g.a.b.m0.a
    public void b(BigInteger bigInteger, SecureRandom secureRandom) {
        this.f5167b = bigInteger;
        this.f5168c = secureRandom;
    }

    @Override // g.a.b.m0.a
    public boolean c() {
        return false;
    }

    @Override // g.a.b.m0.a
    public void d(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        throw new IllegalStateException("Operation not supported");
    }
}
