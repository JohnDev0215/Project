package g.a.b.m0;

import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x0;
import g.a.b.j0.y;
import g.a.b.j0.z;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class g implements g.a.b.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5137g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public z f5138h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SecureRandom f5139i;

    @Override // g.a.b.k
    public BigInteger[] a(byte[] bArr) {
        g.a.b.b bVarA;
        BigInteger bigIntegerMod;
        if (!this.f5137g) {
            throw new IllegalStateException("not initialised for signing");
        }
        BigInteger bigIntegerE = ((b0) this.f5138h).b().e();
        int iBitLength = bigIntegerE.bitLength();
        BigInteger bigInteger = new BigInteger(1, bArr);
        int iBitLength2 = bigInteger.bitLength();
        b0 b0Var = (b0) this.f5138h;
        if (iBitLength2 > iBitLength) {
            throw new DataLengthException("input too large for ECNR key.");
        }
        do {
            g.a.b.e0.l lVar = new g.a.b.e0.l();
            lVar.c(new y(b0Var.b(), this.f5139i));
            bVarA = lVar.a();
            bigIntegerMod = ((c0) bVarA.b()).c().f().t().add(bigInteger).mod(bigIntegerE);
        } while (bigIntegerMod.equals(g.a.d.b.c.f5470a));
        return new BigInteger[]{bigIntegerMod, ((b0) bVarA.a()).c().subtract(bigIntegerMod.multiply(b0Var.c())).mod(bigIntegerE)};
    }

    @Override // g.a.b.k
    public boolean b(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (this.f5137g) {
            throw new IllegalStateException("not initialised for verifying");
        }
        c0 c0Var = (c0) this.f5138h;
        BigInteger bigIntegerE = c0Var.b().e();
        int iBitLength = bigIntegerE.bitLength();
        BigInteger bigInteger3 = new BigInteger(1, bArr);
        if (bigInteger3.bitLength() > iBitLength) {
            throw new DataLengthException("input too large for ECNR key.");
        }
        if (bigInteger.compareTo(g.a.d.b.c.f5471b) < 0 || bigInteger.compareTo(bigIntegerE) >= 0 || bigInteger2.compareTo(g.a.d.b.c.f5470a) < 0 || bigInteger2.compareTo(bigIntegerE) >= 0) {
            return false;
        }
        g.a.d.b.h hVarA = g.a.d.b.b.q(c0Var.b().b(), bigInteger2, c0Var.c(), bigInteger).A();
        if (hVarA.u()) {
            return false;
        }
        return bigInteger.subtract(hVarA.f().t()).mod(bigIntegerE).equals(bigInteger3);
    }

    @Override // g.a.b.k
    public void init(boolean z, g.a.b.i iVar) {
        z zVar;
        this.f5137g = z;
        if (!z) {
            zVar = (c0) iVar;
        } else {
            if (iVar instanceof x0) {
                x0 x0Var = (x0) iVar;
                this.f5139i = x0Var.b();
                this.f5138h = (b0) x0Var.a();
                return;
            }
            this.f5139i = g.a.b.j.b();
            zVar = (b0) iVar;
        }
        this.f5138h = zVar;
    }
}
