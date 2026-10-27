package g.a.b.j0;

import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class x implements g.a.d.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.d.b.d f5078g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f5079h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g.a.d.b.h f5080i;
    public BigInteger j;
    public BigInteger k;
    public BigInteger l;

    public x(g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger) {
        this(dVar, hVar, bigInteger, g.a.d.b.c.f5471b, null);
    }

    public x(g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger, BigInteger bigInteger2) {
        this(dVar, hVar, bigInteger, bigInteger2, null);
    }

    public x(g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.l = null;
        Objects.requireNonNull(dVar, "curve");
        Objects.requireNonNull(bigInteger, "n");
        this.f5078g = dVar;
        this.f5080i = g(dVar, hVar);
        this.j = bigInteger;
        this.k = bigInteger2;
        this.f5079h = bArr;
    }

    public static g.a.d.b.h g(g.a.d.b.d dVar, g.a.d.b.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("point has null value");
        }
        if (hVar.u()) {
            throw new IllegalArgumentException("point at infinity");
        }
        g.a.d.b.h hVarA = hVar.A();
        if (hVarA.w()) {
            return g.a.d.b.b.j(dVar, hVarA);
        }
        throw new IllegalArgumentException("point not on curve");
    }

    public g.a.d.b.d a() {
        return this.f5078g;
    }

    public g.a.d.b.h b() {
        return this.f5080i;
    }

    public BigInteger c() {
        return this.k;
    }

    public synchronized BigInteger d() {
        if (this.l == null) {
            this.l = this.k.modInverse(this.j);
        }
        return this.l;
    }

    public BigInteger e() {
        return this.j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f5078g.m(xVar.f5078g) && this.f5080i.e(xVar.f5080i) && this.j.equals(xVar.j) && this.k.equals(xVar.k);
    }

    public byte[] f() {
        return g.a.f.a.f(this.f5079h);
    }

    public int hashCode() {
        return (((((this.f5078g.hashCode() * 37) ^ this.f5080i.hashCode()) * 37) ^ this.j.hashCode()) * 37) ^ this.k.hashCode();
    }
}
