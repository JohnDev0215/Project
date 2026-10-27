package b.n.d;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GapWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ThreadLocal<e> f2080c = new ThreadLocal<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Comparator<c> f2081d = new a();
    public long x;
    public long y;
    public ArrayList<RecyclerView> s = new ArrayList<>();
    public ArrayList<c> E3 = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f2089d;
            if ((recyclerView == null) != (cVar2.f2089d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f2086a;
            if (z != cVar2.f2086a) {
                return z ? -1 : 1;
            }
            int i2 = cVar2.f2087b - cVar.f2087b;
            if (i2 != 0) {
                return i2;
            }
            int i3 = cVar.f2088c - cVar2.f2088c;
            if (i3 != 0) {
                return i3;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2082a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2083b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f2084c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2085d;

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i2, int i3) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i3 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i4 = this.f2085d * 2;
            int[] iArr = this.f2084c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f2084c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i4 >= iArr.length) {
                int[] iArr3 = new int[i4 * 2];
                this.f2084c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f2084c;
            iArr4[i4] = i2;
            iArr4[i4 + 1] = i3;
            this.f2085d++;
        }

        public void b() {
            int[] iArr = this.f2084c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f2085d = 0;
        }

        public void c(RecyclerView recyclerView, boolean z) {
            this.f2085d = 0;
            int[] iArr = this.f2084c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.U3;
            if (recyclerView.T3 == null || oVar == null || !oVar.s0()) {
                return;
            }
            if (z) {
                if (!recyclerView.L3.p()) {
                    oVar.o(recyclerView.T3.c(), this);
                }
            } else if (!recyclerView.j0()) {
                oVar.n(this.f2082a, this.f2083b, recyclerView.M4, this);
            }
            int i2 = this.f2085d;
            if (i2 > oVar.m) {
                oVar.m = i2;
                oVar.n = z;
                recyclerView.J3.K();
            }
        }

        public boolean d(int i2) {
            if (this.f2084c != null) {
                int i3 = this.f2085d * 2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.f2084c[i4] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void e(int i2, int i3) {
            this.f2082a = i2;
            this.f2083b = i3;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2086a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2087b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2088c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f2089d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2090e;

        public void a() {
            this.f2086a = false;
            this.f2087b = 0;
            this.f2088c = 0;
            this.f2089d = null;
            this.f2090e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i2) {
        int iJ = recyclerView.M3.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            RecyclerView.c0 c0VarD0 = RecyclerView.d0(recyclerView.M3.i(i3));
            if (c0VarD0.f582d == i2 && !c0VarD0.t()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.s.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.s.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView recyclerView = this.s.get(i3);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.L4.c(recyclerView, false);
                i2 += recyclerView.L4.f2085d;
            }
        }
        this.E3.ensureCapacity(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = this.s.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.L4;
                int iAbs = Math.abs(bVar.f2082a) + Math.abs(bVar.f2083b);
                for (int i6 = 0; i6 < bVar.f2085d * 2; i6 += 2) {
                    if (i4 >= this.E3.size()) {
                        cVar = new c();
                        this.E3.add(cVar);
                    } else {
                        cVar = this.E3.get(i4);
                    }
                    int[] iArr = bVar.f2084c;
                    int i7 = iArr[i6 + 1];
                    cVar.f2086a = i7 <= iAbs;
                    cVar.f2087b = iAbs;
                    cVar.f2088c = i7;
                    cVar.f2089d = recyclerView2;
                    cVar.f2090e = iArr[i6];
                    i4++;
                }
            }
        }
        Collections.sort(this.E3, f2081d);
    }

    public final void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.f2089d, cVar.f2090e, cVar.f2086a ? Long.MAX_VALUE : j);
        if (c0VarI == null || c0VarI.f581c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f581c.get(), j);
    }

    public final void d(long j) {
        for (int i2 = 0; i2 < this.E3.size(); i2++) {
            c cVar = this.E3.get(i2);
            if (cVar.f2089d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    public void f(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow() && this.x == 0) {
            this.x = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.L4.e(i2, i3);
    }

    public void g(long j) {
        b();
        d(j);
    }

    public final void h(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.l4 && recyclerView.M3.j() != 0) {
            recyclerView.Q0();
        }
        b bVar = recyclerView.L4;
        bVar.c(recyclerView, true);
        if (bVar.f2085d != 0) {
            try {
                b.f.i.a.a("RV Nested Prefetch");
                recyclerView.M4.f(recyclerView.T3);
                for (int i2 = 0; i2 < bVar.f2085d * 2; i2 += 2) {
                    i(recyclerView, bVar.f2084c[i2], j);
                }
            } finally {
                b.f.i.a.b();
            }
        }
    }

    public final RecyclerView.c0 i(RecyclerView recyclerView, int i2, long j) {
        if (e(recyclerView, i2)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.J3;
        try {
            recyclerView.C0();
            RecyclerView.c0 c0VarI = vVar.I(i2, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f580b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.E0(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.s.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.f.i.a.a("RV Prefetch");
            if (!this.s.isEmpty()) {
                int size = this.s.size();
                long jMax = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = this.s.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.y);
                }
            }
        } finally {
            this.x = 0L;
            b.f.i.a.b();
        }
    }
}
