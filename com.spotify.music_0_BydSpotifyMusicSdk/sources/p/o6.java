package p;

import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o6 implements mn, q6, i80 {
    public final /* synthetic */ p6 c;

    public /* synthetic */ o6(p6 p6Var) {
        this.c = p6Var;
    }

    @Override // p.i80
    public final void e(yy1 yy1Var) {
        this.c.getClass();
        Log.isLoggable("FirebaseCrashlytics", 3);
        u4.z(yy1Var.get());
        throw null;
    }

    @Override // p.mn
    public final void f(g30 g30Var) {
        p6 p6Var = this.c;
        synchronized (p6Var) {
            try {
                if (p6Var.b instanceof v90) {
                    p6Var.c.add(g30Var);
                }
                p6Var.b.f(g30Var);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.q6
    public final void i(Bundle bundle) {
        this.c.a.i(bundle);
    }
}
