package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class p extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4934b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4935c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4936d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4937e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4938f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.e f4939g;

    public p(g.a.b.e eVar) {
        super(eVar);
        this.f4939g = eVar;
        this.f4934b = new byte[eVar.b()];
        this.f4935c = new byte[eVar.b()];
        this.f4936d = new byte[eVar.b()];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        if (bArr.length - i2 < b()) {
            throw new DataLengthException("input buffer too short");
        }
        if (bArr2.length - i3 < b()) {
            throw new OutputLengthException("output buffer too short");
        }
        processBytes(bArr, i2, b(), bArr2, i3);
        return b();
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4939g.b();
    }

    @Override // g.a.b.v
    public byte c(byte b2) {
        int i2 = this.f4937e;
        if (i2 == 0) {
            f(0);
            e();
            this.f4939g.a(this.f4935c, 0, this.f4936d, 0);
            byte[] bArr = this.f4936d;
            int i3 = this.f4937e;
            this.f4937e = i3 + 1;
            return (byte) (b2 ^ bArr[i3]);
        }
        byte[] bArr2 = this.f4936d;
        int i4 = i2 + 1;
        this.f4937e = i4;
        byte b3 = (byte) (b2 ^ bArr2[i2]);
        if (i4 == this.f4935c.length) {
            this.f4937e = 0;
        }
        return b3;
    }

    public final void e() {
    }

    public final void f(int i2) {
        while (true) {
            byte[] bArr = this.f4935c;
            if (i2 >= bArr.length) {
                return;
            }
            int i3 = i2 + 1;
            byte b2 = (byte) (bArr[i2] + 1);
            bArr[i2] = b2;
            if (b2 != 0) {
                return;
            } else {
                i2 = i3;
            }
        }
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4939g.getAlgorithmName() + "/KCTR";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        this.f4938f = true;
        if (!(iVar instanceof w0)) {
            throw new IllegalArgumentException("invalid parameter passed");
        }
        w0 w0Var = (w0) iVar;
        byte[] bArrA = w0Var.a();
        byte[] bArr = this.f4934b;
        int length = bArr.length - bArrA.length;
        g.a.f.a.u(bArr, (byte) 0);
        System.arraycopy(bArrA, 0, this.f4934b, length, bArrA.length);
        g.a.b.i iVarB = w0Var.b();
        if (iVarB != null) {
            this.f4939g.init(true, iVarB);
        }
        reset();
    }

    @Override // g.a.b.e
    public void reset() {
        if (this.f4938f) {
            this.f4939g.a(this.f4934b, 0, this.f4935c, 0);
        }
        this.f4939g.reset();
        this.f4937e = 0;
    }
}
