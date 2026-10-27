package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    public int[] o = null;
    public int[] p = null;
    public int[] q = null;
    public boolean r;

    @Override // g.a.b.d0.o, g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = this.o;
        if (iArr == null) {
            throw new IllegalStateException("DESede engine not initialised");
        }
        if (i2 + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = new byte[8];
        if (this.r) {
            c(iArr, bArr, i2, bArr3, 0);
            c(this.p, bArr3, 0, bArr3, 0);
            c(this.q, bArr3, 0, bArr2, i3);
        } else {
            c(this.q, bArr, i2, bArr3, 0);
            c(this.p, bArr3, 0, bArr3, 0);
            c(this.o, bArr3, 0, bArr2, i3);
        }
        return 8;
    }

    @Override // g.a.b.d0.o, g.a.b.e
    public int b() {
        return 8;
    }

    @Override // g.a.b.d0.o, g.a.b.e
    public String getAlgorithmName() {
        return "DESede";
    }

    @Override // g.a.b.d0.o, g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (!(iVar instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("invalid parameter passed to DESede init - " + iVar.getClass().getName());
        }
        byte[] bArrA = ((g.a.b.j0.s0) iVar).a();
        if (bArrA.length != 24 && bArrA.length != 16) {
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        this.r = z;
        byte[] bArr = new byte[8];
        System.arraycopy(bArrA, 0, bArr, 0, 8);
        this.o = d(z, bArr);
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArrA, 8, bArr2, 0, 8);
        this.p = d(!z, bArr2);
        if (bArrA.length != 24) {
            this.q = this.o;
            return;
        }
        byte[] bArr3 = new byte[8];
        System.arraycopy(bArrA, 16, bArr3, 0, 8);
        this.q = d(z, bArr3);
    }

    @Override // g.a.b.d0.o, g.a.b.e
    public void reset() {
    }
}
