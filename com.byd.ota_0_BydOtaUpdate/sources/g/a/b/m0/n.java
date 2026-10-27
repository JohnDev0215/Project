package g.a.b.m0;

import g.a.a.c1;
import g.a.a.q;
import g.a.a.r;
import g.a.b.a0.a0;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.v0;
import g.a.b.j0.x;
import g.a.b.j0.x0;
import g.a.b.j0.z;
import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoException;

/* JADX INFO: loaded from: classes.dex */
public class n implements g.a.d.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f5169g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a0 f5170h = new a0();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x f5171i;
    public g.a.d.b.h j;
    public z k;
    public byte[] l;

    public final void a(g.a.b.n nVar, g.a.d.b.e eVar) {
        byte[] bArrE = eVar.e();
        nVar.update(bArrE, 0, bArrE.length);
    }

    public final void b(g.a.b.n nVar, byte[] bArr) {
        int length = bArr.length * 8;
        nVar.update((byte) ((length >> 8) & 255));
        nVar.update((byte) (length & 255));
        nVar.update(bArr, 0, bArr.length);
    }

    public BigInteger c(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public g.a.d.b.g d() {
        return new g.a.d.b.j();
    }

    public BigInteger[] e(byte[] bArr) throws IOException {
        r rVarN = r.n(q.j(bArr));
        if (rVarN.size() != 2) {
            return null;
        }
        BigInteger bigIntegerQ = g.a.a.j.n(rVarN.q(0)).q();
        BigInteger bigIntegerQ2 = g.a.a.j.n(rVarN.q(1)).q();
        if (g.a.f.a.q(f(bigIntegerQ, bigIntegerQ2), bArr)) {
            return new BigInteger[]{bigIntegerQ, bigIntegerQ2};
        }
        return null;
    }

    public byte[] f(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(bigInteger));
        fVar.a(new g.a.a.j(bigInteger2));
        return new c1(fVar).f("DER");
    }

    public final byte[] g() {
        byte[] bArr = new byte[this.f5170h.getDigestSize()];
        this.f5170h.doFinal(bArr, 0);
        k();
        return bArr;
    }

    public byte[] h() throws CryptoException {
        byte[] bArrG = g();
        BigInteger bigIntegerE = this.f5171i.e();
        BigInteger bigIntegerC = c(bArrG);
        BigInteger bigIntegerC2 = ((b0) this.k).c();
        g.a.d.b.g gVarD = d();
        while (true) {
            BigInteger bigIntegerA = this.f5169g.a();
            BigInteger bigIntegerMod = bigIntegerC.add(gVarD.a(this.f5171i.b(), bigIntegerA).A().f().t()).mod(bigIntegerE);
            BigInteger bigInteger = g.a.d.b.c.f5470a;
            if (!bigIntegerMod.equals(bigInteger) && !bigIntegerMod.add(bigIntegerA).equals(bigIntegerE)) {
                BigInteger bigIntegerMod2 = bigIntegerC2.add(g.a.d.b.c.f5471b).modInverse(bigIntegerE).multiply(bigIntegerA.subtract(bigIntegerMod.multiply(bigIntegerC2)).mod(bigIntegerE)).mod(bigIntegerE);
                if (!bigIntegerMod2.equals(bigInteger)) {
                    try {
                        return f(bigIntegerMod, bigIntegerMod2);
                    } catch (IOException e2) {
                        throw new CryptoException("unable to encode signature: " + e2.getMessage(), e2);
                    }
                }
            }
        }
    }

    public final byte[] i(byte[] bArr) {
        this.f5170h.reset();
        b(this.f5170h, bArr);
        a(this.f5170h, this.f5171i.a().o());
        a(this.f5170h, this.f5171i.a().p());
        a(this.f5170h, this.f5171i.b().f());
        a(this.f5170h, this.f5171i.b().g());
        a(this.f5170h, this.j.f());
        a(this.f5170h, this.j.g());
        byte[] bArr2 = new byte[this.f5170h.getDigestSize()];
        this.f5170h.doFinal(bArr2, 0);
        return bArr2;
    }

    public void j(boolean z, g.a.b.i iVar) {
        byte[] bArrA;
        g.a.d.b.h hVarC;
        if (iVar instanceof v0) {
            v0 v0Var = (v0) iVar;
            g.a.b.i iVarB = v0Var.b();
            bArrA = v0Var.a();
            iVar = iVarB;
        } else {
            bArrA = g.a.f.j.d.a("31323334353637383132333435363738");
        }
        if (z) {
            if (iVar instanceof x0) {
                x0 x0Var = (x0) iVar;
                z zVar = (z) x0Var.a();
                this.k = zVar;
                x xVarB = zVar.b();
                this.f5171i = xVarB;
                this.f5169g.b(xVarB.e(), x0Var.b());
            } else {
                z zVar2 = (z) iVar;
                this.k = zVar2;
                x xVarB2 = zVar2.b();
                this.f5171i = xVarB2;
                this.f5169g.b(xVarB2.e(), g.a.b.j.b());
            }
            hVarC = d().a(this.f5171i.b(), ((b0) this.k).c()).A();
        } else {
            z zVar3 = (z) iVar;
            this.k = zVar3;
            this.f5171i = zVar3.b();
            hVarC = ((c0) this.k).c();
        }
        this.j = hVarC;
        byte[] bArrI = i(bArrA);
        this.l = bArrI;
        this.f5170h.update(bArrI, 0, bArrI.length);
    }

    public void k() {
        this.f5170h.reset();
        byte[] bArr = this.l;
        if (bArr != null) {
            this.f5170h.update(bArr, 0, bArr.length);
        }
    }

    public void l(byte b2) {
        this.f5170h.update(b2);
    }

    public void m(byte[] bArr, int i2, int i3) {
        this.f5170h.update(bArr, i2, i3);
    }

    public final boolean n(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigIntegerE = this.f5171i.e();
        BigInteger bigInteger3 = g.a.d.b.c.f5471b;
        if (bigInteger.compareTo(bigInteger3) < 0 || bigInteger.compareTo(bigIntegerE) >= 0 || bigInteger2.compareTo(bigInteger3) < 0 || bigInteger2.compareTo(bigIntegerE) >= 0) {
            return false;
        }
        BigInteger bigIntegerC = c(g());
        BigInteger bigIntegerMod = bigInteger.add(bigInteger2).mod(bigIntegerE);
        if (bigIntegerMod.equals(g.a.d.b.c.f5470a)) {
            return false;
        }
        g.a.d.b.h hVarA = g.a.d.b.b.q(this.f5171i.b(), bigInteger2, ((c0) this.k).c(), bigIntegerMod).A();
        if (hVarA.u()) {
            return false;
        }
        return bigIntegerC.add(hVarA.f().t()).mod(bigIntegerE).equals(bigInteger);
    }

    public boolean o(byte[] bArr) {
        try {
            BigInteger[] bigIntegerArrE = e(bArr);
            if (bigIntegerArrE != null) {
                return n(bigIntegerArrE[0], bigIntegerArrE[1]);
            }
        } catch (IOException unused) {
        }
        return false;
    }
}
