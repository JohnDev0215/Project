package g.a.b;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f4736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4738f;

    public f() {
    }

    public f(e eVar) {
        this.f4736d = eVar;
        this.f4733a = new byte[eVar.b()];
        boolean z = false;
        this.f4734b = 0;
        String algorithmName = eVar.getAlgorithmName();
        int iIndexOf = algorithmName.indexOf(47) + 1;
        boolean z2 = iIndexOf > 0 && algorithmName.startsWith("PGP", iIndexOf);
        this.f4738f = z2;
        if (z2 || (eVar instanceof w)) {
            this.f4737e = true;
            return;
        }
        if (iIndexOf > 0 && algorithmName.startsWith("OpenPGP", iIndexOf)) {
            z = true;
        }
        this.f4737e = z;
    }

    public int a(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        try {
            int i3 = this.f4734b;
            if (i2 + i3 > bArr.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }
            int i4 = 0;
            if (i3 != 0) {
                if (!this.f4737e) {
                    throw new DataLengthException("data not block size aligned");
                }
                e eVar = this.f4736d;
                byte[] bArr2 = this.f4733a;
                eVar.a(bArr2, 0, bArr2, 0);
                int i5 = this.f4734b;
                this.f4734b = 0;
                System.arraycopy(this.f4733a, 0, bArr, i2, i5);
                i4 = i5;
            }
            return i4;
        } finally {
            i();
        }
    }

    public int b() {
        return this.f4736d.b();
    }

    public int c(int i2) {
        return i2 + this.f4734b;
    }

    public e d() {
        return this.f4736d;
    }

    public int e(int i2) {
        int length;
        int i3 = i2 + this.f4734b;
        if (this.f4738f && this.f4735c) {
            length = (i3 % this.f4733a.length) - (this.f4736d.b() + 2);
            return i3 - length;
        }
        int length2 = this.f4733a.length;
        length = i3 % length2;
        return i3 - length;
    }

    public void f(boolean z, i iVar) throws IllegalArgumentException {
        this.f4735c = z;
        i();
        this.f4736d.init(z, iVar);
    }

    public int g(byte b2, byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        byte[] bArr2 = this.f4733a;
        int i3 = this.f4734b;
        int i4 = i3 + 1;
        this.f4734b = i4;
        bArr2[i3] = b2;
        if (i4 != bArr2.length) {
            return 0;
        }
        int iA = this.f4736d.a(bArr2, 0, bArr, i2);
        this.f4734b = 0;
        return iA;
    }

    public int h(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, DataLengthException {
        int iA;
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
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            iA = this.f4736d.a(this.f4733a, 0, bArr2, i4) + 0;
            this.f4734b = 0;
            i3 -= i6;
            i2 += i6;
            while (i3 > this.f4733a.length) {
                iA += this.f4736d.a(bArr, i2, bArr2, i4 + iA);
                i3 -= iB;
                i2 += iB;
            }
        } else {
            iA = 0;
        }
        System.arraycopy(bArr, i2, this.f4733a, this.f4734b, i3);
        int i7 = this.f4734b + i3;
        this.f4734b = i7;
        byte[] bArr4 = this.f4733a;
        if (i7 != bArr4.length) {
            return iA;
        }
        int iA2 = iA + this.f4736d.a(bArr4, 0, bArr2, i4 + iA);
        this.f4734b = 0;
        return iA2;
    }

    public void i() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4733a;
            if (i2 >= bArr.length) {
                this.f4734b = 0;
                this.f4736d.reset();
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }
}
