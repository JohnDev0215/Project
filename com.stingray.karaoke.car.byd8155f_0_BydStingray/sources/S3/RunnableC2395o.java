package s3;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import k3.C1648i0;

/* JADX INFO: renamed from: s3.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2395o implements Runnable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ThreadLocal f22325p = new ThreadLocal();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final E5.y f22326q = new E5.y(10);
    public ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f22327m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f22328n;
    public ArrayList o;

    public static U c(RecyclerView recyclerView, int i7, long j3) {
        int iA = recyclerView.f12143q.A();
        for (int i10 = 0; i10 < iA; i10++) {
            U uG = RecyclerView.G(recyclerView.f12143q.z(i10));
            if (uG.c == i7 && !uG.g()) {
                return null;
            }
        }
        C1648i0 c1648i0 = recyclerView.f12138n;
        try {
            recyclerView.N();
            U uK = c1648i0.k(j3, i7);
            if (uK != null) {
                if (!uK.f() || uK.g()) {
                    c1648i0.a(uK, false);
                } else {
                    c1648i0.h(uK.f22219a);
                }
            }
            recyclerView.O(false);
            return uK;
        } catch (Throwable th) {
            recyclerView.O(false);
            throw th;
        }
    }

    public final void a(RecyclerView recyclerView, int i7, int i10) {
        if (recyclerView.f12099C && this.f22327m == 0) {
            this.f22327m = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        C2393m c2393m = recyclerView.f12139n0;
        c2393m.f22321b = i7;
        c2393m.c = i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(long r16) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.RunnableC2395o.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Trace.beginSection("RV Prefetch");
            ArrayList arrayList = this.l;
            if (arrayList.isEmpty()) {
                return;
            }
            int size = arrayList.size();
            long jMax = 0;
            for (int i7 = 0; i7 < size; i7++) {
                RecyclerView recyclerView = (RecyclerView) arrayList.get(i7);
                if (recyclerView.getWindowVisibility() == 0) {
                    jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                }
            }
            if (jMax == 0) {
                return;
            }
            b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f22328n);
        } finally {
            this.f22327m = 0L;
            Trace.endSection();
        }
    }
}
