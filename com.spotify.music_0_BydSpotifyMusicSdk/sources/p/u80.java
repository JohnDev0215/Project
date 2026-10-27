package p;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u80 implements y80 {
    public final /* synthetic */ int c;
    public final /* synthetic */ x80 e;
    public final /* synthetic */ Runnable k;
    public final /* synthetic */ long l;
    public final /* synthetic */ long m;
    public final /* synthetic */ TimeUnit n;

    public /* synthetic */ u80(x80 x80Var, Runnable runnable, long j, long j2, TimeUnit timeUnit, int i) {
        this.c = i;
        this.e = x80Var;
        this.k = runnable;
        this.l = j;
        this.m = j2;
        this.n = timeUnit;
    }

    @Override // p.y80
    public final ScheduledFuture a(oh1 oh1Var) {
        int i = this.c;
        Runnable runnable = this.k;
        x80 x80Var = this.e;
        switch (i) {
            case 0:
                return x80Var.e.scheduleWithFixedDelay(new v80(x80Var, runnable, oh1Var, 1), this.l, this.m, this.n);
            default:
                return x80Var.e.scheduleAtFixedRate(new v80(x80Var, runnable, oh1Var, 0), this.l, this.m, this.n);
        }
    }
}
