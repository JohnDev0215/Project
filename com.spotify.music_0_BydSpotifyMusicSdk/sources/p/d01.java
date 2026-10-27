package p;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class d01 implements Runnable {
    public static final ThreadLocal m = new ThreadLocal();
    public static final p00 n = new p00(1);
    public ArrayList c;
    public long e;
    public long k;
    public ArrayList l;

    public static n22 c(RecyclerView recyclerView, int i, long j) {
        int iH = recyclerView.m.h();
        for (int i2 = 0; i2 < iH; i2++) {
            n22 n22VarI = RecyclerView.I(recyclerView.m.g(i2));
            if (n22VarI.c == i && !n22VarI.h()) {
                return null;
            }
        }
        f22 f22Var = recyclerView.e;
        try {
            recyclerView.P();
            n22 n22VarI2 = f22Var.i(i, j);
            if (n22VarI2 != null) {
                if (!n22VarI2.g() || n22VarI2.h()) {
                    f22Var.a(n22VarI2, false);
                } else {
                    f22Var.f(n22VarI2.a);
                }
            }
            recyclerView.Q(false);
            return n22VarI2;
        } catch (Throwable th) {
            recyclerView.Q(false);
            throw th;
        }
    }

    public final void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.e == 0) {
            this.e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        b01 b01Var = recyclerView.j0;
        b01Var.a = i;
        b01Var.b = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x013b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(long r17) {
        /*
            Method dump skipped, instruction units count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.d01.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i = lm2.a;
            km2.a("RV Prefetch");
            ArrayList arrayList = this.c;
            if (arrayList.isEmpty()) {
                this.e = 0L;
                km2.b();
                return;
            }
            int size = arrayList.size();
            long jMax = 0;
            for (int i2 = 0; i2 < size; i2++) {
                RecyclerView recyclerView = (RecyclerView) arrayList.get(i2);
                if (recyclerView.getWindowVisibility() == 0) {
                    jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                }
            }
            if (jMax == 0) {
                this.e = 0L;
                km2.b();
            } else {
                b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.k);
                this.e = 0L;
                km2.b();
            }
        } catch (Throwable th) {
            this.e = 0L;
            int i3 = lm2.a;
            km2.b();
            throw th;
        }
    }
}
