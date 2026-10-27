package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class j extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4897e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4898f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.e f4899g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4900h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4901i;

    public j(g.a.b.e eVar) {
        super(eVar);
        this.f4901i = false;
        int iB = eVar.b();
        this.f4895c = iB;
        this.f4899g = eVar;
        this.f4898f = new byte[iB];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4895c, bArr2, i3);
        return this.f4895c;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4895c;
    }

    @Override // g.a.b.v
    public byte c(byte b2) {
        if (this.f4900h == 0) {
            f();
        }
        byte[] bArr = this.f4898f;
        int i2 = this.f4900h;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        int i3 = i2 + 1;
        this.f4900h = i3;
        if (i3 == b()) {
            this.f4900h = 0;
            e();
        }
        return b3;
    }

    public final void e() {
        byte[] bArrA = n.a(this.f4896d, this.f4894b - this.f4895c);
        System.arraycopy(bArrA, 0, this.f4896d, 0, bArrA.length);
        System.arraycopy(this.f4898f, 0, this.f4896d, bArrA.length, this.f4894b - bArrA.length);
    }

    public final void f() {
        this.f4899g.a(n.b(this.f4896d, this.f4895c), 0, this.f4898f, 0);
    }

    public final void g() {
        int i2 = this.f4894b;
        this.f4896d = new byte[i2];
        this.f4897e = new byte[i2];
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4899g.getAlgorithmName() + "/OFB";
    }

    public final void h() {
        this.f4894b = this.f4895c * 2;
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            if (bArrA.length < this.f4895c) {
                throw new IllegalArgumentException("Parameter m must blockSize <= m");
            }
            this.f4894b = bArrA.length;
            g();
            byte[] bArrF = g.a.f.a.f(bArrA);
            this.f4897e = bArrF;
            System.arraycopy(bArrF, 0, this.f4896d, 0, bArrF.length);
            if (w0Var.b() != null) {
                eVar = this.f4899g;
                iVar = w0Var.b();
                eVar.init(true, iVar);
            }
        } else {
            h();
            g();
            byte[] bArr = this.f4897e;
            System.arraycopy(bArr, 0, this.f4896d, 0, bArr.length);
            if (iVar != null) {
                eVar = this.f4899g;
                eVar.init(true, iVar);
            }
        }
        this.f4901i = true;
    }

    @Override // g.a.b.e
    public void reset() {
        if (this.f4901i) {
            byte[] bArr = this.f4897e;
            System.arraycopy(bArr, 0, this.f4896d, 0, bArr.length);
            g.a.f.a.e(this.f4898f);
            this.f4900h = 0;
            this.f4899g.reset();
        }
    }
}
