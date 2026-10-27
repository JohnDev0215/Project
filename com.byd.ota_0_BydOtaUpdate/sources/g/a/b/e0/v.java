package g.a.b.e0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;

/* JADX INFO: loaded from: classes.dex */
public class v extends g.a.b.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.t f4729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4730e;

    public v(g.a.b.n nVar) {
        g.a.b.g0.g gVar = new g.a.b.g0.g(nVar);
        this.f4729d = gVar;
        this.f4730e = new byte[gVar.getMacSize()];
    }

    @Override // g.a.b.u
    public g.a.b.i d(int i2) {
        return e(i2);
    }

    @Override // g.a.b.u
    public g.a.b.i e(int i2) {
        int i3 = i2 / 8;
        return new s0(g.a.f.a.s(i(i3), 0, i3), 0, i3);
    }

    @Override // g.a.b.u
    public g.a.b.i f(int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i3 / 8;
        byte[] bArrI = i(i4 + i5);
        return new w0(new s0(bArrI, 0, i4), bArrI, i4, i5);
    }

    public final void h(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, int i3) {
        if (i2 == 0) {
            throw new IllegalArgumentException("iteration count must be at least 1.");
        }
        if (bArr != null) {
            this.f4729d.update(bArr, 0, bArr.length);
        }
        this.f4729d.update(bArr2, 0, bArr2.length);
        this.f4729d.doFinal(this.f4730e, 0);
        byte[] bArr4 = this.f4730e;
        System.arraycopy(bArr4, 0, bArr3, i3, bArr4.length);
        for (int i4 = 1; i4 < i2; i4++) {
            g.a.b.t tVar = this.f4729d;
            byte[] bArr5 = this.f4730e;
            tVar.update(bArr5, 0, bArr5.length);
            this.f4729d.doFinal(this.f4730e, 0);
            int i5 = 0;
            while (true) {
                byte[] bArr6 = this.f4730e;
                if (i5 != bArr6.length) {
                    int i6 = i3 + i5;
                    bArr3[i6] = (byte) (bArr6[i5] ^ bArr3[i6]);
                    i5++;
                }
            }
        }
    }

    public final byte[] i(int i2) {
        int i3;
        int macSize = this.f4729d.getMacSize();
        int i4 = ((i2 + macSize) - 1) / macSize;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[i4 * macSize];
        this.f4729d.init(new s0(this.f5195a));
        int i5 = 0;
        for (int i6 = 1; i6 <= i4; i6++) {
            while (true) {
                byte b2 = (byte) (bArr[i3] + 1);
                bArr[i3] = b2;
                i3 = b2 == 0 ? i3 - 1 : 3;
            }
            h(this.f5196b, this.f5197c, bArr, bArr2, i5);
            i5 += macSize;
        }
        return bArr2;
    }
}
