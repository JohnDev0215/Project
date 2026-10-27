package p;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zl extends pw0 {
    public final /* synthetic */ int e = 1;
    public Object k;

    public zl(ko koVar) {
        super(koVar);
    }

    @Override // p.pw0, p.de2
    public final long t(fo foVar, long j) throws Exception {
        switch (this.e) {
            case 0:
                try {
                    return super.t(foVar, j);
                } catch (Exception e) {
                    this.k = e;
                    throw e;
                }
            default:
                try {
                    return super.t(foVar, j);
                } catch (IOException e2) {
                    ((gp1) this.k).m = e2;
                    throw e2;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zl(gp1 gp1Var, ko koVar) {
        super(koVar);
        this.k = gp1Var;
    }
}
