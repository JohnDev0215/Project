package w0;

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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<e> f5616b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Comparator<c> f5617c = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f5619e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5620f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<RecyclerView> f5618d = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<c> f5621g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f5629d;
            if ((recyclerView == null) != (cVar2.f5629d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z3 = cVar.f5626a;
            if (z3 != cVar2.f5626a) {
                return z3 ? -1 : 1;
            }
            int i3 = cVar2.f5627b - cVar.f5627b;
            if (i3 != 0) {
                return i3;
            }
            int i4 = cVar.f5628c - cVar2.f5628c;
            if (i4 != 0) {
                return i4;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f5624c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5625d;

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i3, int i4) {
            if (i3 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i4 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i5 = this.f5625d * 2;
            int[] iArr = this.f5624c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f5624c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i5 >= iArr.length) {
                int[] iArr3 = new int[i5 * 2];
                this.f5624c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f5624c;
            iArr4[i5] = i3;
            iArr4[i5 + 1] = i4;
            this.f5625d++;
        }

        public void b() {
            int[] iArr = this.f5624c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f5625d = 0;
        }

        public void c(RecyclerView recyclerView, boolean z3) {
            this.f5625d = 0;
            int[] iArr = this.f5624c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.f1455w;
            if (recyclerView.f1453v == null || oVar == null || !oVar.u0()) {
                return;
            }
            if (z3) {
                if (!recyclerView.f1437n.p()) {
                    oVar.p(recyclerView.f1453v.c(), this);
                }
            } else if (!recyclerView.m0()) {
                oVar.o(this.f5622a, this.f5623b, recyclerView.f1440o0, this);
            }
            int i3 = this.f5625d;
            if (i3 > oVar.f1520m) {
                oVar.f1520m = i3;
                oVar.f1521n = z3;
                recyclerView.f1433l.K();
            }
        }

        public boolean d(int i3) {
            if (this.f5624c != null) {
                int i4 = this.f5625d * 2;
                for (int i5 = 0; i5 < i4; i5 += 2) {
                    if (this.f5624c[i5] == i3) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void e(int i3, int i4) {
            this.f5622a = i3;
            this.f5623b = i4;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5626a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5627b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5628c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f5629d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5630e;

        public void a() {
            this.f5626a = false;
            this.f5627b = 0;
            this.f5628c = 0;
            this.f5629d = null;
            this.f5630e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i3) {
        int iJ = recyclerView.f1439o.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            RecyclerView.c0 c0VarG0 = RecyclerView.g0(recyclerView.f1439o.i(i4));
            if (c0VarG0.f1476d == i3 && !c0VarG0.t()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.f5618d.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.f5618d.size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView = this.f5618d.get(i4);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.f1438n0.c(recyclerView, false);
                i3 += recyclerView.f1438n0.f5625d;
            }
        }
        this.f5621g.ensureCapacity(i3);
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            RecyclerView recyclerView2 = this.f5618d.get(i6);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.f1438n0;
                int iAbs = Math.abs(bVar.f5622a) + Math.abs(bVar.f5623b);
                for (int i7 = 0; i7 < bVar.f5625d * 2; i7 += 2) {
                    if (i5 >= this.f5621g.size()) {
                        cVar = new c();
                        this.f5621g.add(cVar);
                    } else {
                        cVar = this.f5621g.get(i5);
                    }
                    int[] iArr = bVar.f5624c;
                    int i8 = iArr[i7 + 1];
                    cVar.f5626a = i8 <= iAbs;
                    cVar.f5627b = iAbs;
                    cVar.f5628c = i8;
                    cVar.f5629d = recyclerView2;
                    cVar.f5630e = iArr[i7];
                    i5++;
                }
            }
        }
        Collections.sort(this.f5621g, f5617c);
    }

    public final void c(c cVar, long j3) {
        RecyclerView.c0 c0VarI = i(cVar.f5629d, cVar.f5630e, cVar.f5626a ? Long.MAX_VALUE : j3);
        if (c0VarI == null || c0VarI.f1475c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f1475c.get(), j3);
    }

    public final void d(long j3) {
        for (int i3 = 0; i3 < this.f5621g.size(); i3++) {
            c cVar = this.f5621g.get(i3);
            if (cVar.f5629d == null) {
                return;
            }
            c(cVar, j3);
            cVar.a();
        }
    }

    public void f(RecyclerView recyclerView, int i3, int i4) {
        if (recyclerView.isAttachedToWindow() && this.f5619e == 0) {
            this.f5619e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.f1438n0.e(i3, i4);
    }

    public void g(long j3) {
        b();
        d(j3);
    }

    public final void h(RecyclerView recyclerView, long j3) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.N && recyclerView.f1439o.j() != 0) {
            recyclerView.U0();
        }
        b bVar = recyclerView.f1438n0;
        bVar.c(recyclerView, true);
        if (bVar.f5625d != 0) {
            try {
                h0.a.a("RV Nested Prefetch");
                recyclerView.f1440o0.f(recyclerView.f1453v);
                for (int i3 = 0; i3 < bVar.f5625d * 2; i3 += 2) {
                    i(recyclerView, bVar.f5624c[i3], j3);
                }
            } finally {
                h0.a.b();
            }
        }
    }

    public final RecyclerView.c0 i(RecyclerView recyclerView, int i3, long j3) {
        if (e(recyclerView, i3)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.f1433l;
        try {
            recyclerView.G0();
            RecyclerView.c0 c0VarI = vVar.I(i3, false, j3);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f1474b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.I0(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.f5618d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            h0.a.a("RV Prefetch");
            if (!this.f5618d.isEmpty()) {
                int size = this.f5618d.size();
                long jMax = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    RecyclerView recyclerView = this.f5618d.get(i3);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f5620f);
                }
            }
        } finally {
            this.f5619e = 0L;
            h0.a.b();
        }
    }
}
