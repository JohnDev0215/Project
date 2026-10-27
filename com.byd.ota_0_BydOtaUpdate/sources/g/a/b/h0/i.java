package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class i extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4886b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4887c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4888d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4889e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4890f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g.a.b.e f4891g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4892h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4893i;

    public i(g.a.b.e eVar) {
        this(eVar, eVar.b() * 8);
    }

    public i(g.a.b.e eVar, int i2) {
        super(eVar);
        this.f4892h = 0;
        if (i2 < 0 || i2 > eVar.b() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (eVar.b() * 8));
        }
        this.f4891g = eVar;
        int iB = eVar.b();
        this.f4890f = iB;
        this.f4886b = i2 / 8;
        this.f4887c = new byte[iB];
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4886b, bArr2, i3);
        return this.f4886b;
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4886b;
    }

    @Override // g.a.b.v
    public byte c(byte b2) {
        if (this.f4892h == 0) {
            this.f4889e = e();
        }
        byte[] bArr = this.f4889e;
        int i2 = this.f4892h;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        int i3 = i2 + 1;
        this.f4892h = i3;
        if (i3 == this.f4886b) {
            this.f4892h = 0;
            f();
        }
        return b3;
    }

    public final byte[] e() {
        byte[] bArr = this.f4887c;
        byte[] bArr2 = new byte[bArr.length];
        this.f4891g.a(bArr, 0, bArr2, 0);
        return n.b(bArr2, this.f4886b);
    }

    public final void f() {
        byte[] bArr = this.f4887c;
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + 1);
    }

    public final void g() {
        int i2 = this.f4890f;
        this.f4888d = new byte[i2 / 2];
        this.f4887c = new byte[i2];
        this.f4889e = new byte[this.f4886b];
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return this.f4891g.getAlgorithmName() + "/GCTR";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.e eVar;
        if (iVar instanceof w0) {
            w0 w0Var = (w0) iVar;
            g();
            byte[] bArrF = g.a.f.a.f(w0Var.a());
            this.f4888d = bArrF;
            if (bArrF.length != this.f4890f / 2) {
                throw new IllegalArgumentException("Parameter IV length must be == blockSize/2");
            }
            System.arraycopy(bArrF, 0, this.f4887c, 0, bArrF.length);
            for (int length = this.f4888d.length; length < this.f4890f; length++) {
                this.f4887c[length] = 0;
            }
            if (w0Var.b() != null) {
                eVar = this.f4891g;
                iVar = w0Var.b();
                eVar.init(true, iVar);
            }
        } else {
            g();
            if (iVar != null) {
                eVar = this.f4891g;
                eVar.init(true, iVar);
            }
        }
        this.f4893i = true;
    }

    @Override // g.a.b.e
    public void reset() {
        if (this.f4893i) {
            byte[] bArr = this.f4888d;
            System.arraycopy(bArr, 0, this.f4887c, 0, bArr.length);
            for (int length = this.f4888d.length; length < this.f4890f; length++) {
                this.f4887c[length] = 0;
            }
            this.f4892h = 0;
            this.f4891g.reset();
        }
    }
}
