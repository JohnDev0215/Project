package p;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class ox2 implements Runnable {
    public static final /* synthetic */ int o = 0;
    public final androidx.work.impl.utils.futures.b c = new androidx.work.impl.utils.futures.b();
    public final Context e;
    public final ky2 k;
    public final qb1 l;
    public final kw0 m;
    public final yx2 n;

    static {
        vc1.d("WorkForegroundRunnable");
    }

    public ox2(Context context, ky2 ky2Var, qb1 qb1Var, qx2 qx2Var, yx2 yx2Var) {
        this.e = context;
        this.k = ky2Var;
        this.l = qb1Var;
        this.m = qx2Var;
        this.n = yx2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.k.q || Build.VERSION.SDK_INT >= 31) {
            this.c.j(null);
            return;
        }
        androidx.work.impl.utils.futures.b bVar = new androidx.work.impl.utils.futures.b();
        yx2 yx2Var = this.n;
        yx2Var.d.execute(new ua(this, 11, bVar));
        bVar.a(new yf1(this, 12, bVar), yx2Var.d);
    }
}
