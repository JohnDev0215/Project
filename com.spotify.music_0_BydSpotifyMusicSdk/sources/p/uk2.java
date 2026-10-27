package p;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class uk2 {
    public final q70 a;
    public final tx2 b;
    public final long c;
    public final Object d;
    public final LinkedHashMap e;

    public uk2(q70 q70Var, tx2 tx2Var) {
        v03.j(q70Var, "runnableScheduler");
        long millis = TimeUnit.MINUTES.toMillis(90L);
        this.a = q70Var;
        this.b = tx2Var;
        this.c = millis;
        this.d = new Object();
        this.e = new LinkedHashMap();
    }

    public final void a(xf2 xf2Var) {
        Runnable runnable;
        v03.j(xf2Var, "token");
        synchronized (this.d) {
            runnable = (Runnable) this.e.remove(xf2Var);
        }
        if (runnable != null) {
            this.a.a.removeCallbacks(runnable);
        }
    }

    public final void b(xf2 xf2Var) {
        ua uaVar = new ua(this, 9, xf2Var);
        synchronized (this.d) {
        }
        q70 q70Var = this.a;
        q70Var.a.postDelayed(uaVar, this.c);
    }
}
