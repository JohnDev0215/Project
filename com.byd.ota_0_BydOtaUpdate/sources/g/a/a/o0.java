package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class o0 extends b {
    public o0(e eVar) throws IOException {
        super(eVar.c().f("DER"), 0);
    }

    public o0(byte[] bArr) {
        this(bArr, 0);
    }

    public o0(byte[] bArr, int i2) {
        super(bArr, i2);
    }

    public static o0 s(byte[] bArr) {
        if (bArr.length < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        byte b2 = bArr[0];
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (length != 0) {
            System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
        }
        return new o0(bArr2, b2);
    }

    public static o0 t(Object obj) {
        if (obj == null || (obj instanceof o0)) {
            return (o0) obj;
        }
        if (obj instanceof m1) {
            m1 m1Var = (m1) obj;
            return new o0(m1Var.f3768d, m1Var.s);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (o0) q.j((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static o0 u(x xVar, boolean z) {
        q qVarP = xVar.p();
        return (z || (qVarP instanceof o0)) ? t(qVarP) : s(((n) qVarP).p());
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        byte[] bArrN = b.n(this.f3768d, this.s);
        int length = bArrN.length + 1;
        byte[] bArr = new byte[length];
        bArr[0] = (byte) r();
        System.arraycopy(bArrN, 0, bArr, 1, length - 1);
        pVar.g(3, bArr);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3768d.length + 1) + 1 + this.f3768d.length + 1;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }
}
