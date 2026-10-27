package g.a.d.b.b0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BigInteger f5461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final BigInteger f5462b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BigInteger f5463c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BigInteger f5464d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final BigInteger f5465e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final BigInteger f5466f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final BigInteger f5467g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final BigInteger f5468h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f5469i;

    public d(BigInteger bigInteger, BigInteger bigInteger2, BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2, BigInteger bigInteger3, BigInteger bigInteger4, int i2) {
        a(bigIntegerArr, "v1");
        a(bigIntegerArr2, "v2");
        this.f5461a = bigInteger;
        this.f5462b = bigInteger2;
        this.f5463c = bigIntegerArr[0];
        this.f5464d = bigIntegerArr[1];
        this.f5465e = bigIntegerArr2[0];
        this.f5466f = bigIntegerArr2[1];
        this.f5467g = bigInteger3;
        this.f5468h = bigInteger4;
        this.f5469i = i2;
    }

    public static void a(BigInteger[] bigIntegerArr, String str) {
        if (bigIntegerArr == null || bigIntegerArr.length != 2 || bigIntegerArr[0] == null || bigIntegerArr[1] == null) {
            throw new IllegalArgumentException("'" + str + "' must consist of exactly 2 (non-null) values");
        }
    }

    public BigInteger b() {
        return this.f5461a;
    }

    public int c() {
        return this.f5469i;
    }

    public BigInteger d() {
        return this.f5467g;
    }

    public BigInteger e() {
        return this.f5468h;
    }

    public BigInteger f() {
        return this.f5463c;
    }

    public BigInteger g() {
        return this.f5464d;
    }

    public BigInteger h() {
        return this.f5465e;
    }

    public BigInteger i() {
        return this.f5466f;
    }
}
