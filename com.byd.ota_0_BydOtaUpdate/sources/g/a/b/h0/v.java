package g.a.b.h0;

import g.a.b.j0.w0;
import g.a.b.w;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class v extends g.a.b.v implements w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g.a.b.e f4981b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4982c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4983d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4984e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4985f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4986g;

    public v(g.a.b.e eVar) {
        super(eVar);
        this.f4981b = eVar;
        int iB = eVar.b();
        this.f4982c = iB;
        this.f4983d = new byte[iB];
        this.f4984e = new byte[iB];
        this.f4985f = new byte[iB];
        this.f4986g = 0;
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4982c, bArr2, i3);
        return this.f4982c;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4981b.b();
    }

    @Override // g.a.b.v
    public byte c(byte b2) throws IllegalStateException, DataLengthException {
        int i2 = this.f4986g;
        if (i2 == 0) {
            this.f4981b.a(this.f4984e, 0, this.f4985f, 0);
            byte[] bArr = this.f4985f;
            int i3 = this.f4986g;
            this.f4986g = i3 + 1;
            return (byte) (b2 ^ bArr[i3]);
        }
        byte[] bArr2 = this.f4985f;
        int i4 = i2 + 1;
        this.f4986g = i4;
        byte b3 = (byte) (b2 ^ bArr2[i2]);
        if (i4 == this.f4984e.length) {
            this.f4986g = 0;
            f(0);
            e();
        }
        return b3;
    }

    public final void e() {
        if (this.f4983d.length >= this.f4982c) {
            return;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4983d;
            if (i2 == bArr.length) {
                return;
            }
            if (this.f4984e[i2] != bArr[i2]) {
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
            i2++;
        }
    }

    public final void f(int i2) {
        byte b2;
        int length = this.f4984e.length - i2;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.f4984e;
            b2 = (byte) (bArr[length] + 1);
            bArr[length] = b2;
        } while (b2 == 0);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4981b.getAlgorithmName() + "/SIC";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof w0)) {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        w0 w0Var = (w0) iVar;
        byte[] bArrF = g.a.f.a.f(w0Var.a());
        this.f4983d = bArrF;
        int i2 = this.f4982c;
        if (i2 < bArrF.length) {
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.f4982c + " bytes.");
        }
        int i3 = 8 > i2 / 2 ? i2 / 2 : 8;
        if (i2 - bArrF.length <= i3) {
            if (w0Var.b() != null) {
                this.f4981b.init(true, w0Var.b());
            }
            reset();
        } else {
            throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.f4982c - i3) + " bytes.");
        }
    }

    @Override // g.a.b.e
    public void reset() {
        g.a.f.a.u(this.f4984e, (byte) 0);
        byte[] bArr = this.f4983d;
        System.arraycopy(bArr, 0, this.f4984e, 0, bArr.length);
        this.f4981b.reset();
        this.f4986g = 0;
    }
}
