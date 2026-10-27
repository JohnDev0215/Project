package defpackage;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: u5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1582u5 implements Runnable {
    public static final ThreadLocal w = new ThreadLocal();
    public static Comparator x = new C1417r5();
    public long t;
    public long u;
    public ArrayList s = new ArrayList();
    public ArrayList v = new ArrayList();

    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.t == 0) {
            this.t = recyclerView.r();
            recyclerView.post(this);
        }
        C1472s5 c1472s5 = recyclerView.q0;
        c1472s5.a = i;
        c1472s5.b = i2;
    }

    public void b(long j) {
        C1527t5 c1527t5;
        int size = this.s.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = (RecyclerView) this.s.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                C1472s5 c1472s5 = recyclerView.q0;
                c1472s5.d = 0;
                int[] iArr = c1472s5.c;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                i += recyclerView.q0.d;
            }
        }
        this.v.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = (RecyclerView) this.s.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                C1472s5 c1472s52 = recyclerView2.q0;
                int iAbs = Math.abs(c1472s52.b) + Math.abs(c1472s52.a);
                for (int i5 = 0; i5 < c1472s52.d * 2; i5 += 2) {
                    if (i3 >= this.v.size()) {
                        c1527t5 = new C1527t5();
                        this.v.add(c1527t5);
                    } else {
                        c1527t5 = (C1527t5) this.v.get(i3);
                    }
                    int i6 = c1472s52.c[i5 + 1];
                    c1527t5.a = i6 <= iAbs;
                    c1527t5.b = iAbs;
                    c1527t5.c = i6;
                    c1527t5.d = recyclerView2;
                    c1527t5.e = c1472s52.c[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.v, x);
        if (this.v.size() > 0) {
            C1527t5 c1527t52 = (C1527t5) this.v.get(0);
            if (c1527t52.d == null) {
                return;
            }
            if (c1527t52.a) {
                j = Long.MAX_VALUE;
            }
            c(c1527t52.d, c1527t52.e, j);
            throw null;
        }
    }

    public final void c(RecyclerView recyclerView, int i, long j) {
        if (recyclerView.v.d() > 0) {
            RecyclerView.p(recyclerView.v.c(0));
            throw null;
        }
        C0474a6 c0474a6 = recyclerView.s;
        try {
            recyclerView.y();
            c0474a6.d(i, false, j);
            throw null;
        } catch (Throwable th) {
            recyclerView.z(false);
            throw th;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Trace.beginSection("RV Prefetch");
            if (this.s.isEmpty()) {
                return;
            }
            int size = this.s.size();
            long jMax = 0;
            for (int i = 0; i < size; i++) {
                RecyclerView recyclerView = (RecyclerView) this.s.get(i);
                if (recyclerView.getWindowVisibility() == 0) {
                    jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                }
            }
            if (jMax == 0) {
                return;
            }
            b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.u);
        } finally {
            this.t = 0L;
            Trace.endSection();
        }
    }
}
