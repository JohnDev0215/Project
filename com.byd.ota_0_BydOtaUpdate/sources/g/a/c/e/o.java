package g.a.c.e;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class o implements KeySpec {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5262c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5263d;
    public BigInteger s;
    public BigInteger x;

    public o(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f5262c = bigInteger;
        this.f5263d = bigInteger2;
        this.s = bigInteger3;
        this.x = bigInteger4;
    }

    public BigInteger a() {
        return this.x;
    }

    public BigInteger b() {
        return this.f5263d;
    }

    public BigInteger c() {
        return this.s;
    }

    public BigInteger d() {
        return this.f5262c;
    }
}
