package defpackage;

import defpackage.ft2;
import java.util.Iterator;

/* JADX INFO: compiled from: StatusIndicatorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lt2 extends vl {
    public final /* synthetic */ qt2 f;

    public lt2(qt2 qt2Var) {
        this.f = qt2Var;
    }

    @Override // defpackage.vl
    public final void b() {
        qt2 qt2Var = this.f;
        qt2Var.q = null;
        Iterator<ft2.a> it = qt2Var.g.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
    }

    @Override // defpackage.vl
    public final void c() {
        this.f.o.run();
    }
}
