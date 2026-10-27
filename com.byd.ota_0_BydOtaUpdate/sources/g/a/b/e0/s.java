package g.a.b.e0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;

/* JADX INFO: loaded from: classes.dex */
public class s extends g.a.b.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.n f4724d = g.a.b.n0.a.a();

    @Override // g.a.b.u
    public g.a.b.i d(int i2) {
        return e(i2);
    }

    @Override // g.a.b.u
    public g.a.b.i e(int i2) {
        int i3 = i2 / 8;
        return new s0(h(i3), 0, i3);
    }

    @Override // g.a.b.u
    public g.a.b.i f(int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i3 / 8;
        byte[] bArrH = h(i4 + i5);
        return new w0(new s0(bArrH, 0, i4), bArrH, i4, i5);
    }

    public final byte[] h(int i2) {
        int digestSize = this.f4724d.getDigestSize();
        byte[] bArr = new byte[digestSize];
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        while (true) {
            g.a.b.n nVar = this.f4724d;
            byte[] bArr3 = this.f5195a;
            nVar.update(bArr3, 0, bArr3.length);
            g.a.b.n nVar2 = this.f4724d;
            byte[] bArr4 = this.f5196b;
            nVar2.update(bArr4, 0, bArr4.length);
            this.f4724d.doFinal(bArr, 0);
            int i4 = i2 > digestSize ? digestSize : i2;
            System.arraycopy(bArr, 0, bArr2, i3, i4);
            i3 += i4;
            i2 -= i4;
            if (i2 == 0) {
                return bArr2;
            }
            this.f4724d.reset();
            this.f4724d.update(bArr, 0, digestSize);
        }
    }

    public void i(byte[] bArr, byte[] bArr2) {
        super.g(bArr, bArr2, 1);
    }
}
