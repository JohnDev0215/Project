package p;

import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class n52 implements tb1 {
    public final xb1 a;

    public n52(xb1 xb1Var) {
        this.a = xb1Var;
    }

    @Override // p.tb1
    public final Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        com.spotify.navigation.identifier.a aVar = bv2.b;
        xb1 xb1Var = this.a;
        return Single.just(he1.E(xb1Var.b(aVar).d(), xb1Var.b(bv2.c).d(), xb1Var.b(bv2.d).d(), xb1Var.b(bv2.e).d()));
    }
}
