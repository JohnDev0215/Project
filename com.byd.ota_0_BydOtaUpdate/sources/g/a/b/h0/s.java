package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class s extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4960c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4961d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4962e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4963f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g.a.b.e f4964g;

    public s(g.a.b.e eVar, int i2) {
        super(eVar);
        this.f4964g = eVar;
        this.f4963f = i2 / 8;
        this.f4960c = new byte[eVar.b()];
        this.f4961d = new byte[eVar.b()];
        this.f4962e = new byte[eVar.b()];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4963f, bArr2, i3);
        return this.f4963f;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4963f;
    }

    @Override // g.a.b.v
    public byte c(byte b2) throws IllegalStateException, DataLengthException {
        if (this.f4959b == 0) {
            this.f4964g.a(this.f4961d, 0, this.f4962e, 0);
        }
        byte[] bArr = this.f4962e;
        int i2 = this.f4959b;
        int i3 = i2 + 1;
        this.f4959b = i3;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        int i4 = this.f4963f;
        if (i3 == i4) {
            this.f4959b = 0;
            byte[] bArr2 = this.f4961d;
            System.arraycopy(bArr2, i4, bArr2, 0, bArr2.length - i4);
            byte[] bArr3 = this.f4962e;
            byte[] bArr4 = this.f4961d;
            int length = bArr4.length;
            int i5 = this.f4963f;
            System.arraycopy(bArr3, 0, bArr4, length - i5, i5);
        }
        return b3;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4964g.getAlgorithmName() + "/OFB" + (this.f4963f * 8);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            int length = bArrA.length;
            byte[] bArr = this.f4960c;
            if (length < bArr.length) {
                System.arraycopy(bArrA, 0, bArr, bArr.length - bArrA.length, bArrA.length);
                int i2 = 0;
                while (true) {
                    byte[] bArr2 = this.f4960c;
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
            eVar = this.f4964g;
            iVar = w0Var.b();
        } else {
            reset();
            if (iVar == null) {
                return;
            } else {
                eVar = this.f4964g;
            }
        }
        eVar.init(true, iVar);
    }

    @Override // g.a.b.e
    public void reset() {
        byte[] bArr = this.f4960c;
        System.arraycopy(bArr, 0, this.f4961d, 0, bArr.length);
        this.f4959b = 0;
        this.f4964g.reset();
    }
}
