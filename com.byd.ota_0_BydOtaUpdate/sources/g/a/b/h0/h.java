package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class h extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4878b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4879c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4880d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4881e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4882f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.e f4883g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4884h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4885i;
    public byte[] j;
    public byte[] k;
    public int l;

    public h(g.a.b.e eVar) {
        this(eVar, eVar.b() * 8);
    }

    public h(g.a.b.e eVar, int i2) {
        super(eVar);
        this.f4885i = false;
        if (i2 < 0 || i2 > eVar.b() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (eVar.b() * 8));
        }
        this.f4880d = eVar.b();
        this.f4883g = eVar;
        this.f4878b = i2 / 8;
        this.k = new byte[b()];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, b(), bArr2, i3);
        return b();
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4878b;
    }

    @Override // g.a.b.v
    public byte c(byte b2) {
        if (this.l == 0) {
            this.j = e();
        }
        byte[] bArr = this.j;
        int i2 = this.l;
        byte b3 = (byte) (bArr[i2] ^ b2);
        byte[] bArr2 = this.k;
        int i3 = i2 + 1;
        this.l = i3;
        if (this.f4884h) {
            b2 = b3;
        }
        bArr2[i2] = b2;
        if (i3 == b()) {
            this.l = 0;
            f(this.k);
        }
        return b3;
    }

    public byte[] e() {
        byte[] bArrB = n.b(this.f4881e, this.f4880d);
        byte[] bArr = new byte[bArrB.length];
        this.f4883g.a(bArrB, 0, bArr, 0);
        return n.b(bArr, this.f4878b);
    }

    public void f(byte[] bArr) {
        byte[] bArrA = n.a(this.f4881e, this.f4879c - this.f4878b);
        System.arraycopy(bArrA, 0, this.f4881e, 0, bArrA.length);
        System.arraycopy(bArr, 0, this.f4881e, bArrA.length, this.f4879c - bArrA.length);
    }

    public final void g() {
        int i2 = this.f4879c;
        this.f4881e = new byte[i2];
        this.f4882f = new byte[i2];
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4883g.getAlgorithmName() + "/CFB" + (this.f4880d * 8);
    }

    public final void h() {
        this.f4879c = this.f4880d * 2;
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        this.f4884h = z;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            if (bArrA.length < this.f4880d) {
                throw new IllegalArgumentException("Parameter m must blockSize <= m");
            }
            this.f4879c = bArrA.length;
            g();
            byte[] bArrF = g.a.f.a.f(bArrA);
            this.f4882f = bArrF;
            System.arraycopy(bArrF, 0, this.f4881e, 0, bArrF.length);
            if (w0Var.b() != null) {
                eVar = this.f4883g;
                iVar = w0Var.b();
                eVar.init(true, iVar);
            }
        } else {
            h();
            g();
            byte[] bArr = this.f4882f;
            System.arraycopy(bArr, 0, this.f4881e, 0, bArr.length);
            if (iVar != null) {
                eVar = this.f4883g;
                eVar.init(true, iVar);
            }
        }
        this.f4885i = true;
    }

    @Override // g.a.b.e
    public void reset() {
        this.l = 0;
        g.a.f.a.e(this.k);
        g.a.f.a.e(this.j);
        if (this.f4885i) {
            byte[] bArr = this.f4882f;
            System.arraycopy(bArr, 0, this.f4881e, 0, bArr.length);
            this.f4883g.reset();
        }
    }
}
