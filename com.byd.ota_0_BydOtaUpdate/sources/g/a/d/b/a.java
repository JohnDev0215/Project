package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements g {
    @Override // g.a.d.b.g
    public h a(h hVar, BigInteger bigInteger) {
        int iSignum = bigInteger.signum();
        if (iSignum == 0 || hVar.u()) {
            return hVar.i().v();
        }
        h hVarC = c(hVar, bigInteger.abs());
        if (iSignum <= 0) {
            hVarC = hVarC.z();
        }
        return b(hVarC);
    }

    public h b(h hVar) {
        return b.b(hVar);
    }

    public abstract h c(h hVar, BigInteger bigInteger);
}
