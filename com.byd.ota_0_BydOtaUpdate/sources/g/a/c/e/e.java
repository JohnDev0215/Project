package g.a.c.e;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class e implements AlgorithmParameterSpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.d.b.d f5241a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f5242b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.d.b.h f5243c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5244d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BigInteger f5245e;

    public e(g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger) {
        this.f5241a = dVar;
        this.f5243c = hVar.A();
        this.f5244d = bigInteger;
        this.f5245e = BigInteger.valueOf(1L);
        this.f5242b = null;
    }

    public e(g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f5241a = dVar;
        this.f5243c = hVar.A();
        this.f5244d = bigInteger;
        this.f5245e = bigInteger2;
        this.f5242b = bArr;
    }

    public g.a.d.b.d a() {
        return this.f5241a;
    }

    public g.a.d.b.h b() {
        return this.f5243c;
    }

    public BigInteger c() {
        return this.f5245e;
    }

    public BigInteger d() {
        return this.f5244d;
    }

    public byte[] e() {
        return this.f5242b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return a().m(eVar.a()) && b().e(eVar.b());
    }

    public int hashCode() {
        return a().hashCode() ^ b().hashCode();
    }
}
