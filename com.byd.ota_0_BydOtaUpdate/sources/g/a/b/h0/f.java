package g.a.b.h0;

import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class f implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v f4862a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4863b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4864c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.t f4865d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4866e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4867f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4868g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4869h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4870i;
    public int j;
    public boolean k;
    public byte[] l;

    public f(g.a.b.e eVar) {
        this.f4864c = eVar.b();
        g.a.b.g0.c cVar = new g.a.b.g0.c(eVar);
        this.f4865d = cVar;
        this.f4868g = new byte[this.f4864c];
        this.f4867f = new byte[cVar.getMacSize()];
        this.f4866e = new byte[this.f4865d.getMacSize()];
        this.f4862a = new v(eVar);
    }

    @Override // g.a.b.h0.a
    public void a(byte[] bArr, int i2, int i3) {
        if (this.k) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.f4865d.update(bArr, i2, i3);
    }

    @Override // g.a.b.h0.a
    public byte[] b() {
        int i2 = this.f4869h;
        byte[] bArr = new byte[i2];
        System.arraycopy(this.f4868g, 0, bArr, 0, i2);
        return bArr;
    }

    public final void c() {
        byte[] bArr = new byte[this.f4864c];
        int i2 = 0;
        this.f4865d.doFinal(bArr, 0);
        while (true) {
            byte[] bArr2 = this.f4868g;
            if (i2 >= bArr2.length) {
                return;
            }
            bArr2[i2] = (byte) ((this.f4866e[i2] ^ this.f4867f[i2]) ^ bArr[i2]);
            i2++;
        }
    }

    public final void d() {
        if (this.k) {
            return;
        }
        this.k = true;
        this.f4865d.doFinal(this.f4867f, 0);
        int i2 = this.f4864c;
        byte[] bArr = new byte[i2];
        bArr[i2 - 1] = 2;
        this.f4865d.update(bArr, 0, i2);
    }

    @Override // g.a.b.h0.a
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException {
        d();
        int i3 = this.j;
        byte[] bArr2 = this.f4870i;
        byte[] bArr3 = new byte[bArr2.length];
        this.j = 0;
        if (this.f4863b) {
            int i4 = i2 + i3;
            if (bArr.length < this.f4869h + i4) {
                throw new OutputLengthException("Output buffer too short");
            }
            this.f4862a.a(bArr2, 0, bArr3, 0);
            System.arraycopy(bArr3, 0, bArr, i2, i3);
            this.f4865d.update(bArr3, 0, i3);
            c();
            System.arraycopy(this.f4868g, 0, bArr, i4, this.f4869h);
            g(false);
            return i3 + this.f4869h;
        }
        int i5 = this.f4869h;
        if (i3 < i5) {
            throw new InvalidCipherTextException("data too short");
        }
        if (bArr.length < (i2 + i3) - i5) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (i3 > i5) {
            this.f4865d.update(bArr2, 0, i3 - i5);
            this.f4862a.a(this.f4870i, 0, bArr3, 0);
            System.arraycopy(bArr3, 0, bArr, i2, i3 - this.f4869h);
        }
        c();
        if (!h(this.f4870i, i3 - this.f4869h)) {
            throw new InvalidCipherTextException("mac check in EAX failed");
        }
        g(false);
        return i3 - this.f4869h;
    }

    public final int e(byte b2, byte[] bArr, int i2) {
        int iA;
        byte[] bArr2 = this.f4870i;
        int i3 = this.j;
        int i4 = i3 + 1;
        this.j = i4;
        bArr2[i3] = b2;
        if (i4 != bArr2.length) {
            return 0;
        }
        int length = bArr.length;
        int i5 = this.f4864c;
        if (length < i2 + i5) {
            throw new OutputLengthException("Output buffer is too short");
        }
        if (this.f4863b) {
            iA = this.f4862a.a(bArr2, 0, bArr, i2);
            this.f4865d.update(bArr, i2, this.f4864c);
        } else {
            this.f4865d.update(bArr2, 0, i5);
            iA = this.f4862a.a(this.f4870i, 0, bArr, i2);
        }
        this.j = 0;
        if (!this.f4863b) {
            byte[] bArr3 = this.f4870i;
            System.arraycopy(bArr3, this.f4864c, bArr3, 0, this.f4869h);
            this.j = this.f4869h;
        }
        return iA;
    }

    public void f() {
        g(true);
    }

    public final void g(boolean z) {
        this.f4862a.reset();
        this.f4865d.reset();
        this.j = 0;
        g.a.f.a.u(this.f4870i, (byte) 0);
        if (z) {
            g.a.f.a.u(this.f4868g, (byte) 0);
        }
        int i2 = this.f4864c;
        byte[] bArr = new byte[i2];
        bArr[i2 - 1] = 1;
        this.f4865d.update(bArr, 0, i2);
        this.k = false;
        byte[] bArr2 = this.l;
        if (bArr2 != null) {
            a(bArr2, 0, bArr2.length);
        }
    }

    @Override // g.a.b.h0.a
    public int getOutputSize(int i2) {
        int i3 = i2 + this.j;
        if (this.f4863b) {
            return i3 + this.f4869h;
        }
        int i4 = this.f4869h;
        if (i3 < i4) {
            return 0;
        }
        return i3 - i4;
    }

    @Override // g.a.b.h0.a
    public g.a.b.e getUnderlyingCipher() {
        return this.f4862a.d();
    }

    @Override // g.a.b.h0.a
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.j;
        if (!this.f4863b) {
            int i4 = this.f4869h;
            if (i3 < i4) {
                return 0;
            }
            i3 -= i4;
        }
        return i3 - (i3 % this.f4864c);
    }

    public final boolean h(byte[] bArr, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < this.f4869h; i4++) {
            i3 |= this.f4868g[i4] ^ bArr[i2 + i4];
        }
        return i3 == 0;
    }

    @Override // g.a.b.h0.a
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        byte[] bArrA;
        g.a.b.i iVarB;
        this.f4863b = z;
        if (iVar instanceof g.a.b.j0.a) {
            g.a.b.j0.a aVar = (g.a.b.j0.a) iVar;
            bArrA = aVar.d();
            this.l = aVar.a();
            this.f4869h = aVar.c() / 8;
            iVarB = aVar.b();
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("invalid parameters passed to EAX");
            }
            w0 w0Var = (w0) iVar;
            bArrA = w0Var.a();
            this.l = null;
            this.f4869h = this.f4865d.getMacSize() / 2;
            iVarB = w0Var.b();
        }
        this.f4870i = new byte[z ? this.f4864c : this.f4864c + this.f4869h];
        byte[] bArr = new byte[this.f4864c];
        this.f4865d.init(iVarB);
        int i2 = this.f4864c;
        bArr[i2 - 1] = 0;
        this.f4865d.update(bArr, 0, i2);
        this.f4865d.update(bArrA, 0, bArrA.length);
        this.f4865d.doFinal(this.f4866e, 0);
        this.f4862a.init(true, new w0(null, this.f4866e));
        f();
    }

    @Override // g.a.b.h0.a
    public int processByte(byte b2, byte[] bArr, int i2) throws DataLengthException {
        d();
        return e(b2, bArr, i2);
    }

    @Override // g.a.b.h0.a
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        d();
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("Input buffer too short");
        }
        int iE = 0;
        for (int i5 = 0; i5 != i3; i5++) {
            iE += e(bArr[i2 + i5], bArr2, i4 + iE);
        }
        return iE;
    }
}
