package g.a.a;

import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static g[] f3842c = new g[12];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f3843d;

    public g(int i2) {
        this.f3843d = BigInteger.valueOf(i2).toByteArray();
    }

    public g(byte[] bArr) {
        if (!g.a.f.g.d("org.bouncycastle.asn1.allow_unsafe_integer") && j.r(bArr)) {
            throw new IllegalArgumentException("malformed enumerated");
        }
        this.f3843d = g.a.f.a.f(bArr);
    }

    public static g n(byte[] bArr) {
        if (bArr.length > 1) {
            return new g(bArr);
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("ENUMERATED has zero length");
        }
        int i2 = bArr[0] & 255;
        g[] gVarArr = f3842c;
        if (i2 >= gVarArr.length) {
            return new g(g.a.f.a.f(bArr));
        }
        g gVar = gVarArr[i2];
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(g.a.f.a.f(bArr));
        gVarArr[i2] = gVar2;
        return gVar2;
    }

    public static g o(Object obj) {
        if (obj == null || (obj instanceof g)) {
            return (g) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (g) q.j((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof g) {
            return g.a.f.a.a(this.f3843d, ((g) qVar).f3843d);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(10, this.f3843d);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3843d);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3843d.length) + 1 + this.f3843d.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public BigInteger p() {
        return new BigInteger(this.f3843d);
    }
}
