package g.a.b.d0;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.d f4434a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.l f4435b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.t f4436c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.f f4437d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4438e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4439f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.i f4440g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g.a.b.i f4441h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g.a.b.j0.o0 f4442i;
    public byte[] j;
    public g.a.b.e0.o k;
    public g.a.b.s l;
    public byte[] m;

    public c0(g.a.b.d dVar, g.a.b.l lVar, g.a.b.t tVar) {
        this.f4434a = dVar;
        this.f4435b = lVar;
        this.f4436c = tVar;
        this.f4438e = new byte[tVar.getMacSize()];
        this.f4437d = null;
    }

    public c0(g.a.b.d dVar, g.a.b.l lVar, g.a.b.t tVar, g.a.b.f fVar) {
        this.f4434a = dVar;
        this.f4435b = lVar;
        this.f4436c = tVar;
        this.f4438e = new byte[tVar.getMacSize()];
        this.f4437d = fVar;
    }

    public final byte[] a(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int iH;
        if (i3 < this.j.length + this.f4436c.getMacSize()) {
            throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
        }
        if (this.f4437d == null) {
            int length = (i3 - this.j.length) - this.f4436c.getMacSize();
            byte[] bArr4 = new byte[length];
            int iC = this.f4442i.c() / 8;
            bArr2 = new byte[iC];
            int i4 = length + iC;
            byte[] bArr5 = new byte[i4];
            this.f4435b.b(bArr5, 0, i4);
            if (this.j.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, iC);
                System.arraycopy(bArr5, iC, bArr4, 0, length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, length);
                System.arraycopy(bArr5, length, bArr2, 0, iC);
            }
            bArr3 = new byte[length];
            for (int i5 = 0; i5 != length; i5++) {
                bArr3[i5] = (byte) (bArr[(this.j.length + i2) + i5] ^ bArr4[i5]);
            }
            iH = 0;
        } else {
            int iD = ((g.a.b.j0.p0) this.f4442i).d() / 8;
            byte[] bArr6 = new byte[iD];
            int iC2 = this.f4442i.c() / 8;
            bArr2 = new byte[iC2];
            int i6 = iD + iC2;
            byte[] bArr7 = new byte[i6];
            this.f4435b.b(bArr7, 0, i6);
            System.arraycopy(bArr7, 0, bArr6, 0, iD);
            System.arraycopy(bArr7, iD, bArr2, 0, iC2);
            g.a.b.i s0Var = new g.a.b.j0.s0(bArr6);
            byte[] bArr8 = this.m;
            if (bArr8 != null) {
                s0Var = new g.a.b.j0.w0(s0Var, bArr8);
            }
            this.f4437d.f(false, s0Var);
            bArr3 = new byte[this.f4437d.c((i3 - this.j.length) - this.f4436c.getMacSize())];
            g.a.b.f fVar = this.f4437d;
            byte[] bArr9 = this.j;
            iH = fVar.h(bArr, i2 + bArr9.length, (i3 - bArr9.length) - this.f4436c.getMacSize(), bArr3, 0);
        }
        byte[] bArrB = this.f4442i.b();
        byte[] bArrE = this.j.length != 0 ? e(bArrB) : null;
        int i7 = i2 + i3;
        byte[] bArrS = g.a.f.a.s(bArr, i7 - this.f4436c.getMacSize(), i7);
        int length2 = bArrS.length;
        byte[] bArr10 = new byte[length2];
        this.f4436c.init(new g.a.b.j0.s0(bArr2));
        g.a.b.t tVar = this.f4436c;
        byte[] bArr11 = this.j;
        tVar.update(bArr, i2 + bArr11.length, (i3 - bArr11.length) - length2);
        if (bArrB != null) {
            this.f4436c.update(bArrB, 0, bArrB.length);
        }
        if (this.j.length != 0) {
            this.f4436c.update(bArrE, 0, bArrE.length);
        }
        this.f4436c.doFinal(bArr10, 0);
        if (!g.a.f.a.q(bArrS, bArr10)) {
            throw new InvalidCipherTextException("invalid MAC");
        }
        g.a.b.f fVar2 = this.f4437d;
        return fVar2 == null ? bArr3 : g.a.f.a.s(bArr3, 0, iH + fVar2.a(bArr3, iH));
    }

    public final byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        g.a.b.f fVar;
        g.a.b.i s0Var;
        byte[] bArr2;
        byte[] bArr3;
        if (this.f4437d == null) {
            byte[] bArr4 = new byte[i3];
            int iC = this.f4442i.c() / 8;
            bArr3 = new byte[iC];
            int i4 = i3 + iC;
            byte[] bArr5 = new byte[i4];
            this.f4435b.b(bArr5, 0, i4);
            if (this.j.length != 0) {
                System.arraycopy(bArr5, 0, bArr3, 0, iC);
                System.arraycopy(bArr5, iC, bArr4, 0, i3);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, i3);
                System.arraycopy(bArr5, i3, bArr3, 0, iC);
            }
            bArr2 = new byte[i3];
            for (int i5 = 0; i5 != i3; i5++) {
                bArr2[i5] = (byte) (bArr[i2 + i5] ^ bArr4[i5]);
            }
        } else {
            int iD = ((g.a.b.j0.p0) this.f4442i).d() / 8;
            byte[] bArr6 = new byte[iD];
            int iC2 = this.f4442i.c() / 8;
            byte[] bArr7 = new byte[iC2];
            int i6 = iD + iC2;
            byte[] bArr8 = new byte[i6];
            this.f4435b.b(bArr8, 0, i6);
            System.arraycopy(bArr8, 0, bArr6, 0, iD);
            System.arraycopy(bArr8, iD, bArr7, 0, iC2);
            if (this.m != null) {
                fVar = this.f4437d;
                s0Var = new g.a.b.j0.w0(new g.a.b.j0.s0(bArr6), this.m);
            } else {
                fVar = this.f4437d;
                s0Var = new g.a.b.j0.s0(bArr6);
            }
            fVar.f(true, s0Var);
            bArr2 = new byte[this.f4437d.c(i3)];
            int iH = this.f4437d.h(bArr, i2, i3, bArr2, 0);
            i3 = iH + this.f4437d.a(bArr2, iH);
            bArr3 = bArr7;
        }
        byte[] bArrB = this.f4442i.b();
        byte[] bArrE = this.j.length != 0 ? e(bArrB) : null;
        int macSize = this.f4436c.getMacSize();
        byte[] bArr9 = new byte[macSize];
        this.f4436c.init(new g.a.b.j0.s0(bArr3));
        this.f4436c.update(bArr2, 0, bArr2.length);
        if (bArrB != null) {
            this.f4436c.update(bArrB, 0, bArrB.length);
        }
        if (this.j.length != 0) {
            this.f4436c.update(bArrE, 0, bArrE.length);
        }
        this.f4436c.doFinal(bArr9, 0);
        byte[] bArr10 = this.j;
        byte[] bArr11 = new byte[bArr10.length + i3 + macSize];
        System.arraycopy(bArr10, 0, bArr11, 0, bArr10.length);
        System.arraycopy(bArr2, 0, bArr11, this.j.length, i3);
        System.arraycopy(bArr9, 0, bArr11, this.j.length + i3, macSize);
        return bArr11;
    }

    public final void c(g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.w0) {
            g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
            this.m = w0Var.a();
            iVar = w0Var.b();
        } else {
            this.m = null;
        }
        this.f4442i = (g.a.b.j0.o0) iVar;
    }

    public g.a.b.f d() {
        return this.f4437d;
    }

    public byte[] e(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            g.a.f.f.q(((long) bArr.length) * 8, bArr2, 0);
        }
        return bArr2;
    }

    public g.a.b.t f() {
        return this.f4436c;
    }

    public void g(g.a.b.j0.b bVar, g.a.b.i iVar, g.a.b.s sVar) {
        this.f4439f = false;
        this.f4440g = bVar;
        this.l = sVar;
        c(iVar);
    }

    public void h(g.a.b.j0.b bVar, g.a.b.i iVar, g.a.b.e0.o oVar) {
        this.f4439f = true;
        this.f4441h = bVar;
        this.k = oVar;
        c(iVar);
    }

    public void i(boolean z, g.a.b.i iVar, g.a.b.i iVar2, g.a.b.i iVar3) {
        this.f4439f = z;
        this.f4440g = iVar;
        this.f4441h = iVar2;
        this.j = new byte[0];
        c(iVar3);
    }

    public byte[] j(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4439f) {
            g.a.b.e0.o oVar = this.k;
            if (oVar != null) {
                g.a.b.o oVarA = oVar.a();
                this.f4440g = oVarA.b().a();
                this.j = oVarA.a();
            }
        } else if (this.l != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i3);
            try {
                this.f4441h = this.l.a(byteArrayInputStream);
                this.j = g.a.f.a.s(bArr, i2, (i3 - byteArrayInputStream.available()) + i2);
            } catch (IOException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            } catch (IllegalArgumentException e3) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e3.getMessage(), e3);
            }
        }
        this.f4434a.init(this.f4440g);
        byte[] bArrA = g.a.f.b.a(this.f4434a.a(), this.f4434a.b(this.f4441h));
        byte[] bArr2 = this.j;
        if (bArr2.length != 0) {
            byte[] bArrM = g.a.f.a.m(bArr2, bArrA);
            g.a.f.a.u(bArrA, (byte) 0);
            bArrA = bArrM;
        }
        try {
            this.f4435b.a(new g.a.b.j0.r0(bArrA, this.f4442i.a()));
            return this.f4439f ? b(bArr, i2, i3) : a(bArr, i2, i3);
        } finally {
            g.a.f.a.u(bArrA, (byte) 0);
        }
    }
}
