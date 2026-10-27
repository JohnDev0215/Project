package g.a.b.j0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class d1 extends g.a.b.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5016c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5017d;

    public d1(BigInteger bigInteger, SecureRandom secureRandom, int i2, int i3) {
        super(secureRandom, i2);
        if (i2 < 12) {
            throw new IllegalArgumentException("key strength too small");
        }
        if (!bigInteger.testBit(0)) {
            throw new IllegalArgumentException("public exponent cannot be even");
        }
        this.f5016c = bigInteger;
        this.f5017d = i3;
    }

    public int c() {
        return this.f5017d;
    }

    public BigInteger d() {
        return this.f5016c;
    }
}
