package g.a.b.g0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4798a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4799b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4800c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4801d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.e f4802e;

    public j(g.a.b.e eVar, int i2) {
        this.f4802e = null;
        this.f4802e = eVar;
        this.f4801d = i2 / 8;
        this.f4798a = new byte[eVar.b()];
        this.f4799b = new byte[eVar.b()];
        this.f4800c = new byte[eVar.b()];
    }

    public String a() {
        return this.f4802e.getAlgorithmName() + "/CFB" + (this.f4801d * 8);
    }

    public int b() {
        return this.f4801d;
    }

    public void c(byte[] bArr) {
        this.f4802e.a(this.f4799b, 0, bArr, 0);
    }

    public void d(g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            int length = bArrA.length;
            byte[] bArr = this.f4798a;
            if (length < bArr.length) {
                System.arraycopy(bArrA, 0, bArr, bArr.length - bArrA.length, bArrA.length);
            } else {
                System.arraycopy(bArrA, 0, bArr, 0, bArr.length);
            }
            f();
            eVar = this.f4802e;
            iVar = w0Var.b();
        } else {
            f();
            eVar = this.f4802e;
        }
        eVar.init(true, iVar);
    }

    public int e(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = this.f4801d;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.f4802e.a(this.f4799b, 0, this.f4800c, 0);
        int i5 = 0;
        while (true) {
            int i6 = this.f4801d;
            if (i5 >= i6) {
                byte[] bArr3 = this.f4799b;
                System.arraycopy(bArr3, i6, bArr3, 0, bArr3.length - i6);
                byte[] bArr4 = this.f4799b;
                int length = bArr4.length;
                int i7 = this.f4801d;
                System.arraycopy(bArr2, i3, bArr4, length - i7, i7);
                return this.f4801d;
            }
            bArr2[i3 + i5] = (byte) (this.f4800c[i5] ^ bArr[i2 + i5]);
            i5++;
        }
    }

    public void f() {
        byte[] bArr = this.f4798a;
        System.arraycopy(bArr, 0, this.f4799b, 0, bArr.length);
        this.f4802e.reset();
    }
}
