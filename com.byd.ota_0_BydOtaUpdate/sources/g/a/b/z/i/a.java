package g.a.b.z.i;

import g.a.b.j0.r0;
import g.a.b.l;
import g.a.b.m;
import g.a.b.n;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class a implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n f5212a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f5213b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5214c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5215d;

    public a(n nVar) {
        this.f5212a = nVar;
        this.f5215d = nVar.getDigestSize();
    }

    @Override // g.a.b.l
    public void a(m mVar) {
        if (!(mVar instanceof r0)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        r0 r0Var = (r0) mVar;
        this.f5213b = r0Var.b();
        this.f5214c = r0Var.a();
    }

    @Override // g.a.b.l
    public int b(byte[] bArr, int i2, int i3) throws DataLengthException, IllegalArgumentException {
        int i4;
        int i5;
        if (bArr.length - i3 < i2) {
            throw new OutputLengthException("output buffer too small");
        }
        byte[] bArr2 = new byte[this.f5215d];
        byte[] bArr3 = new byte[4];
        this.f5212a.reset();
        int i6 = 1;
        if (i3 > this.f5215d) {
            i4 = 0;
            while (true) {
                c(i6, bArr3);
                this.f5212a.update(bArr3, 0, 4);
                n nVar = this.f5212a;
                byte[] bArr4 = this.f5213b;
                nVar.update(bArr4, 0, bArr4.length);
                n nVar2 = this.f5212a;
                byte[] bArr5 = this.f5214c;
                nVar2.update(bArr5, 0, bArr5.length);
                this.f5212a.doFinal(bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i2 + i4, this.f5215d);
                int i7 = this.f5215d;
                i4 += i7;
                i5 = i6 + 1;
                if (i6 >= i3 / i7) {
                    break;
                }
                i6 = i5;
            }
            i6 = i5;
        } else {
            i4 = 0;
        }
        if (i4 < i3) {
            c(i6, bArr3);
            this.f5212a.update(bArr3, 0, 4);
            n nVar3 = this.f5212a;
            byte[] bArr6 = this.f5213b;
            nVar3.update(bArr6, 0, bArr6.length);
            n nVar4 = this.f5212a;
            byte[] bArr7 = this.f5214c;
            nVar4.update(bArr7, 0, bArr7.length);
            this.f5212a.doFinal(bArr2, 0);
            System.arraycopy(bArr2, 0, bArr, i2 + i4, i3 - i4);
        }
        return i3;
    }

    public final void c(int i2, byte[] bArr) {
        bArr[0] = (byte) (i2 >>> 24);
        bArr[1] = (byte) (i2 >>> 16);
        bArr[2] = (byte) (i2 >>> 8);
        bArr[3] = (byte) (i2 >>> 0);
    }
}
