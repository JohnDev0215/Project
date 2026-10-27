package g.a.b.d0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class k0 implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.h0.b f4517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.j0.w0 f4518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SecureRandom f4520d;

    public k0(g.a.b.e eVar) {
        this.f4517a = new g.a.b.h0.b(eVar);
    }

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        if (!this.f4519c) {
            throw new IllegalStateException("not set for wrapping");
        }
        this.f4517a.init(true, this.f4518b);
        int iB = this.f4517a.b();
        int i4 = i3 + 4;
        int i5 = iB * 2;
        if (i4 >= i5) {
            i5 = i4 % iB == 0 ? i4 : ((i4 / iB) + 1) * iB;
        }
        byte[] bArr2 = new byte[i5];
        bArr2[0] = (byte) i3;
        bArr2[1] = (byte) (~bArr[i2]);
        bArr2[2] = (byte) (~bArr[i2 + 1]);
        bArr2[3] = (byte) (~bArr[i2 + 2]);
        System.arraycopy(bArr, i2, bArr2, 4, i3);
        int length = bArr2.length - i4;
        byte[] bArr3 = new byte[length];
        this.f4520d.nextBytes(bArr3);
        System.arraycopy(bArr3, 0, bArr2, i4, length);
        for (int i6 = 0; i6 < bArr2.length; i6 += iB) {
            this.f4517a.a(bArr2, i6, bArr2, i6);
        }
        for (int i7 = 0; i7 < bArr2.length; i7 += iB) {
            this.f4517a.a(bArr2, i7, bArr2, i7);
        }
        return bArr2;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4519c) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int iB = this.f4517a.b();
        if (i3 < iB * 2) {
            throw new InvalidCipherTextException("input too short");
        }
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[iB];
        int i4 = 0;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        System.arraycopy(bArr, i2, bArr3, 0, iB);
        this.f4517a.init(false, new g.a.b.j0.w0(this.f4518b.b(), bArr3));
        for (int i5 = iB; i5 < i3; i5 += iB) {
            this.f4517a.a(bArr2, i5, bArr2, i5);
        }
        System.arraycopy(bArr2, i3 - iB, bArr3, 0, iB);
        this.f4517a.init(false, new g.a.b.j0.w0(this.f4518b.b(), bArr3));
        this.f4517a.a(bArr2, 0, bArr2, 0);
        this.f4517a.init(false, this.f4518b);
        for (int i6 = 0; i6 < i3; i6 += iB) {
            this.f4517a.a(bArr2, i6, bArr2, i6);
        }
        if ((bArr2[0] & 255) > i3 - 4) {
            throw new InvalidCipherTextException("wrapped key corrupted");
        }
        byte[] bArr4 = new byte[bArr2[0] & 255];
        System.arraycopy(bArr2, 4, bArr4, 0, bArr2[0]);
        int i7 = 0;
        while (i4 != 3) {
            int i8 = i4 + 1;
            i7 |= ((byte) (~bArr2[i8])) ^ bArr4[i4];
            i4 = i8;
        }
        if (i7 == 0) {
            return bArr4;
        }
        throw new InvalidCipherTextException("wrapped key fails checksum");
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return this.f4517a.e().getAlgorithmName() + "/RFC3211Wrap";
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        this.f4519c = z;
        if (iVar instanceof g.a.b.j0.x0) {
            g.a.b.j0.x0 x0Var = (g.a.b.j0.x0) iVar;
            this.f4520d = x0Var.b();
            this.f4518b = (g.a.b.j0.w0) x0Var.a();
        } else {
            if (z) {
                this.f4520d = g.a.b.j.b();
            }
            this.f4518b = (g.a.b.j0.w0) iVar;
        }
    }
}
