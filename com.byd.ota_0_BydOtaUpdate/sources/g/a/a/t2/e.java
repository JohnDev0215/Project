package g.a.a.t2;

import g.a.d.b.h;
import java.math.BigInteger;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static h a(g.a.d.b.d dVar, byte[] bArr) {
        g.a.d.b.e eVarN = dVar.n(BigInteger.valueOf(bArr[bArr.length - 1] & 1));
        g.a.d.b.e eVarN2 = dVar.n(new BigInteger(1, bArr));
        if (!d(eVarN2).equals(dVar.o())) {
            eVarN2 = eVarN2.b();
        }
        g.a.d.b.e eVarJ = null;
        if (eVarN2.i()) {
            eVarJ = dVar.p().n();
        } else {
            g.a.d.b.e eVarC = c(dVar, eVarN2.o().g().j(dVar.p()).a(dVar.o()).a(eVarN2));
            if (eVarC != null) {
                if (!d(eVarC).equals(eVarN)) {
                    eVarC = eVarC.b();
                }
                eVarJ = eVarN2.j(eVarC);
            }
        }
        if (eVarJ != null) {
            return dVar.F(eVarN2.t(), eVarJ.t());
        }
        throw new IllegalArgumentException("Invalid point compression");
    }

    public static byte[] b(h hVar) {
        h hVarA = hVar.A();
        g.a.d.b.e eVarF = hVarA.f();
        byte[] bArrE = eVarF.e();
        if (!eVarF.i()) {
            if (d(hVarA.g().d(eVarF)).h()) {
                int length = bArrE.length - 1;
                bArrE[length] = (byte) (bArrE[length] | 1);
            } else {
                int length2 = bArrE.length - 1;
                bArrE[length2] = (byte) (bArrE[length2] & 254);
            }
        }
        return bArrE;
    }

    public static g.a.d.b.e c(g.a.d.b.d dVar, g.a.d.b.e eVar) {
        g.a.d.b.e eVarA;
        if (eVar.i()) {
            return eVar;
        }
        g.a.d.b.e eVarN = dVar.n(g.a.d.b.c.f5470a);
        Random random = new Random();
        int iF = eVar.f();
        do {
            g.a.d.b.e eVarN2 = dVar.n(new BigInteger(iF, random));
            g.a.d.b.e eVarA2 = eVar;
            eVarA = eVarN;
            for (int i2 = 1; i2 <= iF - 1; i2++) {
                g.a.d.b.e eVarO = eVarA2.o();
                eVarA = eVarA.o().a(eVarO.j(eVarN2));
                eVarA2 = eVarO.a(eVar);
            }
            if (!eVarA2.i()) {
                return null;
            }
        } while (eVarA.o().a(eVarA).i());
        return eVarA;
    }

    public static g.a.d.b.e d(g.a.d.b.e eVar) {
        g.a.d.b.e eVarA = eVar;
        for (int i2 = 1; i2 < eVar.f(); i2++) {
            eVarA = eVarA.o().a(eVar);
        }
        return eVarA;
    }
}
