package g.a.b.e0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;

/* JADX INFO: loaded from: classes.dex */
public class u extends g.a.b.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.n f4728d;

    public u(g.a.b.n nVar) {
        this.f4728d = nVar;
    }

    @Override // g.a.b.u
    public g.a.b.i d(int i2) {
        return e(i2);
    }

    @Override // g.a.b.u
    public g.a.b.i e(int i2) {
        int i3 = i2 / 8;
        if (i3 <= this.f4728d.getDigestSize()) {
            return new s0(h(), 0, i3);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i3 + " bytes long.");
    }

    @Override // g.a.b.u
    public g.a.b.i f(int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i3 / 8;
        int i6 = i4 + i5;
        if (i6 <= this.f4728d.getDigestSize()) {
            byte[] bArrH = h();
            return new w0(new s0(bArrH, 0, i4), bArrH, i4, i5);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i6 + " bytes long.");
    }

    public final byte[] h() {
        int digestSize = this.f4728d.getDigestSize();
        byte[] bArr = new byte[digestSize];
        g.a.b.n nVar = this.f4728d;
        byte[] bArr2 = this.f5195a;
        nVar.update(bArr2, 0, bArr2.length);
        g.a.b.n nVar2 = this.f4728d;
        byte[] bArr3 = this.f5196b;
        nVar2.update(bArr3, 0, bArr3.length);
        this.f4728d.doFinal(bArr, 0);
        for (int i2 = 1; i2 < this.f5197c; i2++) {
            this.f4728d.update(bArr, 0, digestSize);
            this.f4728d.doFinal(bArr, 0);
        }
        return bArr;
    }
}
