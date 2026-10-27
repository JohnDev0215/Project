package p;

import androidx.work.impl.WorkDatabase;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class tp extends vp {
    public final /* synthetic */ wx2 e;
    public final /* synthetic */ UUID k;

    public tp(wx2 wx2Var, UUID uuid) {
        this.e = wx2Var;
        this.k = uuid;
    }

    @Override // p.vp
    public final void b() {
        wx2 wx2Var = this.e;
        WorkDatabase workDatabase = wx2Var.i;
        workDatabase.c();
        try {
            vp.a(wx2Var, this.k.toString());
            workDatabase.o();
            workDatabase.k();
            h72.b(wx2Var.h, wx2Var.i, wx2Var.k);
        } catch (Throwable th) {
            workDatabase.k();
            throw th;
        }
    }
}
