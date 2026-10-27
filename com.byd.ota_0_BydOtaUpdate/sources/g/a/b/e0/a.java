package g.a.b.e0;

import g.a.b.j0.q0;
import g.a.b.j0.r0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class a implements g.a.b.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4684a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.n f4685b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4686c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4687d;

    public a(int i2, g.a.b.n nVar) {
        this.f4684a = i2;
        this.f4685b = nVar;
    }

    @Override // g.a.b.l
    public void a(g.a.b.m mVar) {
        if (mVar instanceof r0) {
            r0 r0Var = (r0) mVar;
            this.f4686c = r0Var.b();
            this.f4687d = r0Var.a();
        } else {
            if (!(mVar instanceof q0)) {
                throw new IllegalArgumentException("KDF parameters required for generator");
            }
            this.f4686c = ((q0) mVar).a();
            this.f4687d = null;
        }
    }

    @Override // g.a.b.l
    public int b(byte[] bArr, int i2, int i3) throws DataLengthException, IllegalArgumentException {
        int i4 = i3;
        int i5 = i2;
        if (bArr.length - i4 < i5) {
            throw new OutputLengthException("output buffer too small");
        }
        long j = i4;
        int digestSize = this.f4685b.getDigestSize();
        if (j > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        long j2 = digestSize;
        int i6 = (int) (((j + j2) - 1) / j2);
        byte[] bArr2 = new byte[this.f4685b.getDigestSize()];
        byte[] bArr3 = new byte[4];
        g.a.f.f.d(this.f4684a, bArr3, 0);
        int i7 = this.f4684a & (-256);
        for (int i8 = 0; i8 < i6; i8++) {
            g.a.b.n nVar = this.f4685b;
            byte[] bArr4 = this.f4686c;
            nVar.update(bArr4, 0, bArr4.length);
            this.f4685b.update(bArr3, 0, 4);
            byte[] bArr5 = this.f4687d;
            if (bArr5 != null) {
                this.f4685b.update(bArr5, 0, bArr5.length);
            }
            this.f4685b.doFinal(bArr2, 0);
            if (i4 > digestSize) {
                System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                i5 += digestSize;
                i4 -= digestSize;
            } else {
                System.arraycopy(bArr2, 0, bArr, i5, i4);
            }
            byte b2 = (byte) (bArr3[3] + 1);
            bArr3[3] = b2;
            if (b2 == 0) {
                i7 += 256;
                g.a.f.f.d(i7, bArr3, 0);
            }
        }
        this.f4685b.reset();
        return (int) j;
    }
}
