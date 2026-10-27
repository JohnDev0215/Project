package p;

import androidx.work.impl.WorkDatabase;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class up extends vp {
    public final /* synthetic */ wx2 e;
    public final /* synthetic */ String k = null;
    public final /* synthetic */ boolean l = false;

    public up(wx2 wx2Var) {
        this.e = wx2Var;
    }

    @Override // p.vp
    public final void b() {
        wx2 wx2Var = this.e;
        WorkDatabase workDatabase = wx2Var.i;
        workDatabase.c();
        try {
            Iterator it = workDatabase.v().g(this.k).iterator();
            while (it.hasNext()) {
                vp.a(wx2Var, (String) it.next());
            }
            workDatabase.o();
            workDatabase.k();
            if (this.l) {
                h72.b(wx2Var.h, wx2Var.i, wx2Var.k);
            }
        } catch (Throwable th) {
            workDatabase.k();
            throw th;
        }
    }
}
