package g.a.a;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f3735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3736d;
    public final byte[] s;

    public a(boolean z, int i2, byte[] bArr) {
        this.f3735c = z;
        this.f3736d = i2;
        this.s = g.a.f.a.f(bArr);
    }

    public static a p(Object obj) {
        if (obj == null || (obj instanceof a)) {
            return (a) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        try {
            return p(q.j((byte[]) obj));
        } catch (IOException e2) {
            throw new IllegalArgumentException("Failed to construct object from byte[]: " + e2.getMessage());
        }
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (!(qVar instanceof a)) {
            return false;
        }
        a aVar = (a) qVar;
        return this.f3735c == aVar.f3735c && this.f3736d == aVar.f3736d && g.a.f.a.a(this.s, aVar.s);
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.f(this.f3735c ? 96 : 64, this.f3736d, this.s);
    }

    @Override // g.a.a.l
    public int hashCode() {
        boolean z = this.f3735c;
        return ((z ? 1 : 0) ^ this.f3736d) ^ g.a.f.a.z(this.s);
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        return y1.b(this.f3736d) + y1.a(this.s.length) + this.s.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return this.f3735c;
    }

    public int n() {
        return this.f3736d;
    }

    public byte[] o() {
        return g.a.f.a.f(this.s);
    }

    public q q(int i2) throws IOException {
        if (i2 >= 31) {
            throw new IOException("unsupported tag number");
        }
        byte[] bArrE = e();
        byte[] bArrR = r(i2, bArrE);
        if ((bArrE[0] & 32) != 0) {
            bArrR[0] = (byte) (bArrR[0] | 32);
        }
        return q.j(bArrR);
    }

    public final byte[] r(int i2, byte[] bArr) throws IOException {
        int i3;
        if ((bArr[0] & 31) == 31) {
            i3 = 2;
            int i4 = bArr[1] & 255;
            if ((i4 & 127) == 0) {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
            while (i4 >= 0 && (i4 & 128) != 0) {
                i4 = bArr[i3] & 255;
                i3++;
            }
        } else {
            i3 = 1;
        }
        int length = (bArr.length - i3) + 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i3, bArr2, 1, length - 1);
        bArr2[0] = (byte) i2;
        return bArr2;
    }
}
