package g.a.b.e0;

import g.a.b.j0.f0;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4713a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4714b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SecureRandom f4715c;

    public f0 a() {
        BigInteger[] bigIntegerArrA = h.a(this.f4713a, this.f4714b, this.f4715c);
        BigInteger bigInteger = bigIntegerArrA[0];
        return new f0(bigInteger, h.b(bigInteger, bigIntegerArrA[1], this.f4715c));
    }

    public void b(int i2, int i3, SecureRandom secureRandom) {
        this.f4713a = i2;
        this.f4714b = i3;
        this.f4715c = secureRandom;
    }
}
