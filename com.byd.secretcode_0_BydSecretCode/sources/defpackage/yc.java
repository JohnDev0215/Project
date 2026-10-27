package defpackage;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GapWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class yc implements Runnable {
    public static final ThreadLocal<yc> b = new ThreadLocal<>();
    public static Comparator<c> c = new a();
    public long e;
    public long f;
    public ArrayList<RecyclerView> d = new ArrayList<>();
    public ArrayList<c> g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.d;
            if ((recyclerView == null) != (cVar2.d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.a;
            if (z != cVar2.a) {
                return z ? -1 : 1;
            }
            int i = cVar2.b - cVar.b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.c - cVar2.c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.o.c {
        public int a;
        public int b;
        public int[] c;
        public int d;

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.d * 2;
            int[] iArr = this.c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.d++;
        }

        public void b() {
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.d = 0;
        }

        public void c(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.w;
            if (recyclerView.v == null || oVar == null || !oVar.u0()) {
                return;
            }
            if (z) {
                if (!recyclerView.n.p()) {
                    oVar.p(recyclerView.v.c(), this);
                }
            } else if (!recyclerView.n0()) {
                oVar.o(this.a, this.b, recyclerView.o0, this);
            }
            int i = this.d;
            if (i > oVar.m) {
                oVar.m = i;
                oVar.n = z;
                recyclerView.l.K();
            }
        }

        public boolean d(int i) {
            if (this.c != null) {
                int i2 = this.d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {
        public boolean a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;

        public void a() {
            this.a = false;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i) {
        int iJ = recyclerView.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            RecyclerView.c0 c0VarG0 = RecyclerView.g0(recyclerView.o.i(i2));
            if (c0VarG0.d == i && !c0VarG0.t()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.d.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.d.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.d.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.n0.c(recyclerView, false);
                i += recyclerView.n0.d;
            }
        }
        this.g.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.d.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.n0;
                int iAbs = Math.abs(bVar.a) + Math.abs(bVar.b);
                for (int i5 = 0; i5 < bVar.d * 2; i5 += 2) {
                    if (i3 >= this.g.size()) {
                        cVar = new c();
                        this.g.add(cVar);
                    } else {
                        cVar = this.g.get(i3);
                    }
                    int[] iArr = bVar.c;
                    int i6 = iArr[i5 + 1];
                    cVar.a = i6 <= iAbs;
                    cVar.b = iAbs;
                    cVar.c = i6;
                    cVar.d = recyclerView2;
                    cVar.e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.g, c);
    }

    public final void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.d, cVar.e, cVar.a ? Long.MAX_VALUE : j);
        if (c0VarI == null || c0VarI.c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.c.get(), j);
    }

    public final void d(long j) {
        for (int i = 0; i < this.g.size(); i++) {
            c cVar = this.g.get(i);
            if (cVar.d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    public void f(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.e == 0) {
            this.e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.n0.e(i, i2);
    }

    public void g(long j) {
        b();
        d(j);
    }

    public final void h(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.N && recyclerView.o.j() != 0) {
            recyclerView.V0();
        }
        b bVar = recyclerView.n0;
        bVar.c(recyclerView, true);
        if (bVar.d != 0) {
            try {
                i8.a("RV Nested Prefetch");
                recyclerView.o0.f(recyclerView.v);
                for (int i = 0; i < bVar.d * 2; i += 2) {
                    i(recyclerView, bVar.c[i], j);
                }
            } finally {
                i8.b();
            }
        }
    }

    public final RecyclerView.c0 i(RecyclerView recyclerView, int i, long j) {
        if (e(recyclerView, i)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.l;
        try {
            recyclerView.H0();
            RecyclerView.c0 c0VarI = vVar.I(i, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.J0(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            i8.a("RV Prefetch");
            if (!this.d.isEmpty()) {
                int size = this.d.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.d.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f);
                }
            }
        } finally {
            this.e = 0L;
            i8.b();
        }
    }
}
