package p;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hr2 implements vh2, y80, i80 {
    public final /* synthetic */ int c;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ Object m;

    public /* synthetic */ hr2(Object obj, Object obj2, long j, Object obj3, int i) {
        this.c = i;
        this.k = obj;
        this.l = obj2;
        this.e = j;
        this.m = obj3;
    }

    @Override // p.y80
    public final ScheduledFuture a(oh1 oh1Var) {
        int i = this.c;
        long j = this.e;
        Object obj = this.m;
        Object obj2 = this.l;
        Object obj3 = this.k;
        switch (i) {
            case 1:
                x80 x80Var = (x80) obj3;
                x80Var.getClass();
                ox1 ox1Var = new ox1(x80Var, (Callable) obj2, oh1Var, 1);
                return x80Var.e.schedule(ox1Var, j, (TimeUnit) obj);
            default:
                x80 x80Var2 = (x80) obj3;
                x80Var2.getClass();
                v80 v80Var = new v80(x80Var2, (Runnable) obj2, oh1Var, 2);
                return x80Var2.e.schedule(v80Var, j, (TimeUnit) obj);
        }
    }

    @Override // p.vh2
    public final Object b() {
        jr2 jr2Var = (jr2) this.k;
        Iterable iterable = (Iterable) this.l;
        wi wiVar = (wi) this.m;
        j62 j62Var = (j62) jr2Var.c;
        j62Var.getClass();
        if (iterable.iterator().hasNext()) {
            j62Var.i(new r70(j62Var, "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + j62.X(iterable), "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name", 1));
        }
        j62Var.i(new e62(((lr2) jr2Var.g).a() + this.e, wiVar));
        return null;
    }

    @Override // p.i80
    public final void e(yy1 yy1Var) {
        ((j30) yy1Var.get()).c((String) this.k, (String) this.l, this.e, (ri) this.m);
    }

    public /* synthetic */ hr2(jr2 jr2Var, Iterable iterable, wi wiVar, long j) {
        this.c = 0;
        this.k = jr2Var;
        this.l = iterable;
        this.m = wiVar;
        this.e = j;
    }
}
