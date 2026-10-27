package g.a.b.h0;

import android.R;
import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class m extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4916b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4917c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4918d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4919e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4920f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g.a.b.e f4921g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4922h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4923i;
    public int j;

    public m(g.a.b.e eVar) {
        super(eVar);
        this.f4922h = true;
        this.f4921g = eVar;
        int iB = eVar.b();
        this.f4920f = iB;
        if (iB != 8) {
            throw new IllegalArgumentException("GCTR only for 64 bit block ciphers");
        }
        this.f4916b = new byte[eVar.b()];
        this.f4917c = new byte[eVar.b()];
        this.f4918d = new byte[eVar.b()];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4920f, bArr2, i3);
        return this.f4920f;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4920f;
    }

    @Override // g.a.b.v
    public byte c(byte b2) {
        if (this.f4919e == 0) {
            if (this.f4922h) {
                this.f4922h = false;
                this.f4921g.a(this.f4917c, 0, this.f4918d, 0);
                this.f4923i = e(this.f4918d, 0);
                this.j = e(this.f4918d, 4);
            }
            int i2 = this.f4923i + R.attr.cacheColorHint;
            this.f4923i = i2;
            int i3 = this.j + R.attr.hand_minute;
            this.j = i3;
            if (i3 < 16843012 && i3 > 0) {
                this.j = i3 + 1;
            }
            f(i2, this.f4917c, 0);
            f(this.j, this.f4917c, 4);
            this.f4921g.a(this.f4917c, 0, this.f4918d, 0);
        }
        byte[] bArr = this.f4918d;
        int i4 = this.f4919e;
        int i5 = i4 + 1;
        this.f4919e = i5;
        byte b3 = (byte) (b2 ^ bArr[i4]);
        int i6 = this.f4920f;
        if (i5 == i6) {
            this.f4919e = 0;
            byte[] bArr2 = this.f4917c;
            System.arraycopy(bArr2, i6, bArr2, 0, bArr2.length - i6);
            byte[] bArr3 = this.f4918d;
            byte[] bArr4 = this.f4917c;
            int length = bArr4.length;
            int i7 = this.f4920f;
            System.arraycopy(bArr3, 0, bArr4, length - i7, i7);
        }
        return b3;
    }

    public final int e(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] << 24) & (-16777216)) + ((bArr[i2 + 2] << 16) & 16711680) + ((bArr[i2 + 1] << 8) & 65280) + (bArr[i2] & 255);
    }

    public final void f(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >>> 24);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3] = (byte) i2;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4921g.getAlgorithmName() + "/GCTR";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        this.f4922h = true;
        this.f4923i = 0;
        this.j = 0;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            int length = bArrA.length;
            byte[] bArr = this.f4916b;
            if (length < bArr.length) {
                System.arraycopy(bArrA, 0, bArr, bArr.length - bArrA.length, bArrA.length);
                int i2 = 0;
                while (true) {
                    byte[] bArr2 = this.f4916b;
                    if (i2 >= bArr2.length - bArrA.length) {
                        break;
                    }
                    bArr2[i2] = 0;
                    i2++;
                }
            } else {
                System.arraycopy(bArrA, 0, bArr, 0, bArr.length);
            }
            reset();
            if (w0Var.b() == null) {
                return;
            }
            eVar = this.f4921g;
            iVar = w0Var.b();
        } else {
            reset();
            if (iVar == null) {
                return;
            } else {
                eVar = this.f4921g;
            }
        }
        eVar.init(true, iVar);
    }

    @Override // g.a.b.e
    public void reset() {
        this.f4922h = true;
        this.f4923i = 0;
        this.j = 0;
        byte[] bArr = this.f4916b;
        System.arraycopy(bArr, 0, this.f4917c, 0, bArr.length);
        this.f4919e = 0;
        this.f4921g.reset();
    }
}
