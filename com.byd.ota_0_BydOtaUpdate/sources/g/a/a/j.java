package g.a.a;

import java.io.IOException;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class j extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f3877c;

    public j(long j) {
        this.f3877c = BigInteger.valueOf(j).toByteArray();
    }

    public j(BigInteger bigInteger) {
        this.f3877c = bigInteger.toByteArray();
    }

    public j(byte[] bArr) {
        this(bArr, true);
    }

    public j(byte[] bArr, boolean z) {
        if (!g.a.f.g.d("org.bouncycastle.asn1.allow_unsafe_integer") && r(bArr)) {
            throw new IllegalArgumentException("malformed integer");
        }
        this.f3877c = z ? g.a.f.a.f(bArr) : bArr;
    }

    public static j n(Object obj) {
        if (obj == null || (obj instanceof j)) {
            return (j) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (j) q.j((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static j o(x xVar, boolean z) {
        q qVarP = xVar.p();
        return (z || (qVarP instanceof j)) ? n(qVarP) : new j(n.n(qVarP).p());
    }

    public static boolean r(byte[] bArr) {
        if (bArr.length > 1) {
            if (bArr[0] == 0 && (bArr[1] & 128) == 0) {
                return true;
            }
            if (bArr[0] == -1 && (bArr[1] & 128) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof j) {
            return g.a.f.a.a(this.f3877c, ((j) qVar).f3877c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(2, this.f3877c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f3877c;
            if (i2 == bArr.length) {
                return i3;
            }
            i3 ^= (bArr[i2] & 255) << (i2 % 4);
            i2++;
        }
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3877c.length) + 1 + this.f3877c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public BigInteger p() {
        return new BigInteger(1, this.f3877c);
    }

    public BigInteger q() {
        return new BigInteger(this.f3877c);
    }

    public String toString() {
        return q().toString();
    }
}
