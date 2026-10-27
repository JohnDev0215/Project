package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class b implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4838b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4839c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4840d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.e f4841e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4842f;

    public b(g.a.b.e eVar) {
        this.f4841e = null;
        this.f4841e = eVar;
        int iB = eVar.b();
        this.f4840d = iB;
        this.f4837a = new byte[iB];
        this.f4838b = new byte[iB];
        this.f4839c = new byte[iB];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        return this.f4842f ? d(bArr, i2, bArr2, i3) : c(bArr, i2, bArr2, i3);
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4841e.b();
    }

    public final int c(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = this.f4840d;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        System.arraycopy(bArr, i2, this.f4839c, 0, i4);
        int iA = this.f4841e.a(bArr, i2, bArr2, i3);
        for (int i5 = 0; i5 < this.f4840d; i5++) {
            int i6 = i3 + i5;
            bArr2[i6] = (byte) (bArr2[i6] ^ this.f4838b[i5]);
        }
        byte[] bArr3 = this.f4838b;
        this.f4838b = this.f4839c;
        this.f4839c = bArr3;
        return iA;
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        if (this.f4840d + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i4 = 0; i4 < this.f4840d; i4++) {
            byte[] bArr3 = this.f4838b;
            bArr3[i4] = (byte) (bArr3[i4] ^ bArr[i2 + i4]);
        }
        int iA = this.f4841e.a(this.f4838b, 0, bArr2, i3);
        byte[] bArr4 = this.f4838b;
        System.arraycopy(bArr2, i3, bArr4, 0, bArr4.length);
        return iA;
    }

    public g.a.b.e e() {
        return this.f4841e;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4841e.getAlgorithmName() + "/CBC";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        boolean z2 = this.f4842f;
        this.f4842f = z;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            if (bArrA.length != this.f4840d) {
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            }
            System.arraycopy(bArrA, 0, this.f4837a, 0, bArrA.length);
            reset();
            if (w0Var.b() == null) {
                if (z2 != z) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
                return;
            } else {
                eVar = this.f4841e;
                iVar = w0Var.b();
            }
        } else {
            reset();
            if (iVar == null) {
                if (z2 != z) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
                return;
            }
            eVar = this.f4841e;
        }
        eVar.init(z, iVar);
    }

    @Override // g.a.b.e
    public void reset() {
        byte[] bArr = this.f4837a;
        System.arraycopy(bArr, 0, this.f4838b, 0, bArr.length);
        g.a.f.a.u(this.f4839c, (byte) 0);
        this.f4841e.reset();
    }
}
