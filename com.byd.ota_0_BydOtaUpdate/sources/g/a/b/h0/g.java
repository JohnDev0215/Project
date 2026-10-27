package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class g implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4872b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4873c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4874d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.e f4875e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4876f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4877g;

    public g(g.a.b.e eVar) {
        this.f4872b = eVar.b();
        this.f4875e = eVar;
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        return this.f4877g ? d(bArr, i2, bArr2, i3) : c(bArr, i2, bArr2, i3);
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4872b;
    }

    public final int c(byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[] bArrB = n.b(this.f4873c, this.f4872b);
        byte[] bArrC = n.c(bArr, this.f4872b, i2);
        byte[] bArr3 = new byte[bArrC.length];
        this.f4875e.a(bArrC, 0, bArr3, 0);
        byte[] bArrD = n.d(bArr3, bArrB);
        System.arraycopy(bArrD, 0, bArr2, i3, bArrD.length);
        if (bArr2.length > i3 + bArrD.length) {
            e(bArrC);
        }
        return bArrD.length;
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[] bArrD = n.d(n.c(bArr, this.f4872b, i2), n.b(this.f4873c, this.f4872b));
        int length = bArrD.length;
        byte[] bArr3 = new byte[length];
        this.f4875e.a(bArrD, 0, bArr3, 0);
        System.arraycopy(bArr3, 0, bArr2, i3, length);
        if (bArr2.length > i3 + bArrD.length) {
            e(bArr3);
        }
        return length;
    }

    public final void e(byte[] bArr) {
        byte[] bArrA = n.a(this.f4873c, this.f4871a - this.f4872b);
        System.arraycopy(bArrA, 0, this.f4873c, 0, bArrA.length);
        System.arraycopy(bArr, 0, this.f4873c, bArrA.length, this.f4871a - bArrA.length);
    }

    public final void f() {
        int i2 = this.f4871a;
        this.f4873c = new byte[i2];
        this.f4874d = new byte[i2];
    }

    public final void g() {
        this.f4871a = this.f4872b;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4875e.getAlgorithmName() + "/CBC";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        this.f4877g = z;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            if (bArrA.length < this.f4872b) {
                throw new IllegalArgumentException("Parameter m must blockSize <= m");
            }
            this.f4871a = bArrA.length;
            f();
            byte[] bArrF = g.a.f.a.f(bArrA);
            this.f4874d = bArrF;
            System.arraycopy(bArrF, 0, this.f4873c, 0, bArrF.length);
            if (w0Var.b() != null) {
                eVar = this.f4875e;
                iVar = w0Var.b();
                eVar.init(z, iVar);
            }
        } else {
            g();
            f();
            byte[] bArr = this.f4874d;
            System.arraycopy(bArr, 0, this.f4873c, 0, bArr.length);
            if (iVar != null) {
                eVar = this.f4875e;
                eVar.init(z, iVar);
            }
        }
        this.f4876f = true;
    }

    @Override // g.a.b.e
    public void reset() {
        if (this.f4876f) {
            byte[] bArr = this.f4874d;
            System.arraycopy(bArr, 0, this.f4873c, 0, bArr.length);
            this.f4875e.reset();
        }
    }
}
