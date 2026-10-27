package g.a.b.i0;

import g.a.b.i;
import g.a.b.j0.x0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class e extends g.a.b.f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f4994g;

    public e(g.a.b.e eVar) {
        this(eVar, new d());
    }

    public e(g.a.b.e eVar, a aVar) {
        this.f4736d = eVar;
        this.f4994g = aVar;
        this.f4733a = new byte[eVar.b()];
        this.f4734b = 0;
    }

    @Override // g.a.b.f
    public int a(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        int iA;
        int iA2;
        int iB = this.f4736d.b();
        if (this.f4735c) {
            if (this.f4734b != iB) {
                iA2 = 0;
            } else {
                if ((iB * 2) + i2 > bArr.length) {
                    i();
                    throw new OutputLengthException("output buffer too short");
                }
                iA2 = this.f4736d.a(this.f4733a, 0, bArr, i2);
                this.f4734b = 0;
            }
            this.f4994g.b(this.f4733a, this.f4734b);
            iA = iA2 + this.f4736d.a(this.f4733a, 0, bArr, i2 + iA2);
        } else {
            if (this.f4734b != iB) {
                i();
                throw new DataLengthException("last block incomplete in decryption");
            }
            g.a.b.e eVar = this.f4736d;
            byte[] bArr2 = this.f4733a;
            int iA3 = eVar.a(bArr2, 0, bArr2, 0);
            this.f4734b = 0;
            try {
                iA = iA3 - this.f4994g.a(this.f4733a);
                System.arraycopy(this.f4733a, 0, bArr, i2, iA);
            } finally {
                i();
            }
        }
        return iA;
    }

    @Override // g.a.b.f
    public int c(int i2) {
        int i3 = i2 + this.f4734b;
        byte[] bArr = this.f4733a;
        int length = i3 % bArr.length;
        if (length != 0) {
            i3 -= length;
        } else if (!this.f4735c) {
            return i3;
        }
        return i3 + bArr.length;
    }

    @Override // g.a.b.f
    public int e(int i2) {
        int i3 = i2 + this.f4734b;
        byte[] bArr = this.f4733a;
        int length = i3 % bArr.length;
        return length == 0 ? Math.max(0, i3 - bArr.length) : i3 - length;
    }

    @Override // g.a.b.f
    public void f(boolean z, i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        this.f4735c = z;
        i();
        if (iVar instanceof x0) {
            x0 x0Var = (x0) iVar;
            this.f4994g.c(x0Var.b());
            eVar = this.f4736d;
            iVar = x0Var.a();
        } else {
            this.f4994g.c(null);
            eVar = this.f4736d;
        }
        eVar.init(z, iVar);
    }

    @Override // g.a.b.f
    public int g(byte b2, byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        int i3 = this.f4734b;
        byte[] bArr2 = this.f4733a;
        int i4 = 0;
        if (i3 == bArr2.length) {
            int iA = this.f4736d.a(bArr2, 0, bArr, i2);
            this.f4734b = 0;
            i4 = iA;
        }
        byte[] bArr3 = this.f4733a;
        int i5 = this.f4734b;
        this.f4734b = i5 + 1;
        bArr3[i5] = b2;
        return i4;
    }

    @Override // g.a.b.f
    public int h(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, DataLengthException {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int iB = b();
        int iE = e(i3);
        if (iE > 0 && iE + i4 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = this.f4733a;
        int length = bArr3.length;
        int i5 = this.f4734b;
        int i6 = length - i5;
        int iA = 0;
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            int iA2 = this.f4736d.a(this.f4733a, 0, bArr2, i4) + 0;
            this.f4734b = 0;
            i3 -= i6;
            i2 += i6;
            iA = iA2;
            while (i3 > this.f4733a.length) {
                iA += this.f4736d.a(bArr, i2, bArr2, i4 + iA);
                i3 -= iB;
                i2 += iB;
            }
        }
        System.arraycopy(bArr, i2, this.f4733a, this.f4734b, i3);
        this.f4734b += i3;
        return iA;
    }
}
