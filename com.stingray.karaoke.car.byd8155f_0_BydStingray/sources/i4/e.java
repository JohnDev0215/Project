package i4;

import A4.m;
import g4.l;
import g4.x;

/* JADX INFO: loaded from: classes.dex */
public final class e extends m {
    public l d;

    @Override // A4.m
    public final int b(Object obj) {
        x xVar = (x) obj;
        if (xVar == null) {
            return 1;
        }
        return xVar.b();
    }

    @Override // A4.m
    public final void c(Object obj, Object obj2) {
        x xVar = (x) obj2;
        l lVar = this.d;
        if (lVar == null || xVar == null) {
            return;
        }
        lVar.f14912e.p(xVar, true);
    }
}
