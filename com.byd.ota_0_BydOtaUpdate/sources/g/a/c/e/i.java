package g.a.c.e;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class i implements AlgorithmParameterSpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BigInteger f5249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BigInteger f5250b;

    public i(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f5249a = bigInteger;
        this.f5250b = bigInteger2;
    }

    public BigInteger a() {
        return this.f5250b;
    }

    public BigInteger b() {
        return this.f5249a;
    }
}
