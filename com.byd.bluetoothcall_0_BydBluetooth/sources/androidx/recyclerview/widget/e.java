package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GapWorker.java */
/* JADX INFO: loaded from: classes.dex */
final class e implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final ThreadLocal<e> f1781b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static Comparator<c> f1782c = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f1784e;
    long f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<RecyclerView> f1783d = new ArrayList<>();
    private ArrayList<c> g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f1792d;
            if ((recyclerView == null) != (cVar2.f1792d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f1789a;
            if (z != cVar2.f1789a) {
                return z ? -1 : 1;
            }
            int i = cVar2.f1790b - cVar.f1790b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.f1791c - cVar2.f1791c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1785a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1786b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int[] f1787c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1788d;

        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.f1788d * 2;
            int[] iArr = this.f1787c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1787c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.f1787c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1787c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.f1788d++;
        }

        void b() {
            int[] iArr = this.f1787c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1788d = 0;
        }

        void c(RecyclerView recyclerView, boolean z) {
            this.f1788d = 0;
            int[] iArr = this.f1787c;
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
            } else if (!recyclerView.p0()) {
                oVar.o(this.f1785a, this.f1786b, recyclerView.o0, this);
            }
            int i = this.f1788d;
            if (i > oVar.m) {
                oVar.m = i;
                oVar.n = z;
                recyclerView.l.K();
            }
        }

        boolean d(int i) {
            if (this.f1787c != null) {
                int i2 = this.f1788d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1787c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i, int i2) {
            this.f1785a = i;
            this.f1786b = i2;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1789a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1790b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1791c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f1792d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1793e;

        c() {
        }

        public void a() {
            this.f1789a = false;
            this.f1790b = 0;
            this.f1791c = 0;
            this.f1792d = null;
            this.f1793e = 0;
        }
    }

    e() {
    }

    private void b() {
        c cVar;
        int size = this.f1783d.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f1783d.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.n0.c(recyclerView, false);
                i += recyclerView.n0.f1788d;
            }
        }
        this.g.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f1783d.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.n0;
                int iAbs = Math.abs(bVar.f1785a) + Math.abs(bVar.f1786b);
                for (int i5 = 0; i5 < bVar.f1788d * 2; i5 += 2) {
                    if (i3 >= this.g.size()) {
                        cVar = new c();
                        this.g.add(cVar);
                    } else {
                        cVar = this.g.get(i3);
                    }
                    int[] iArr = bVar.f1787c;
                    int i6 = iArr[i5 + 1];
                    cVar.f1789a = i6 <= iAbs;
                    cVar.f1790b = iAbs;
                    cVar.f1791c = i6;
                    cVar.f1792d = recyclerView2;
                    cVar.f1793e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.g, f1782c);
    }

    private void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.f1792d, cVar.f1793e, cVar.f1789a ? Long.MAX_VALUE : j);
        if (c0VarI == null || c0VarI.f1638c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f1638c.get(), j);
    }

    private void d(long j) {
        for (int i = 0; i < this.g.size(); i++) {
            c cVar = this.g.get(i);
            if (cVar.f1792d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i) {
        int iJ = recyclerView.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            RecyclerView.c0 c0VarI0 = RecyclerView.i0(recyclerView.o.i(i2));
            if (c0VarI0.f1639d == i && !c0VarI0.t()) {
                return true;
            }
        }
        return false;
    }

    private void h(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.N && recyclerView.o.j() != 0) {
            recyclerView.X0();
        }
        b bVar = recyclerView.n0;
        bVar.c(recyclerView, true);
        if (bVar.f1788d != 0) {
            try {
                b.g.g.a.a("RV Nested Prefetch");
                recyclerView.o0.f(recyclerView.v);
                for (int i = 0; i < bVar.f1788d * 2; i += 2) {
                    i(recyclerView, bVar.f1787c[i], j);
                }
            } finally {
                b.g.g.a.b();
            }
        }
    }

    private RecyclerView.c0 i(RecyclerView recyclerView, int i, long j) {
        if (e(recyclerView, i)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.l;
        try {
            recyclerView.J0();
            RecyclerView.c0 c0VarI = vVar.I(i, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f1637b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.L0(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f1783d.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.f1784e == 0) {
            this.f1784e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.n0.e(i, i2);
    }

    void g(long j) {
        b();
        d(j);
    }

    public void j(RecyclerView recyclerView) {
        this.f1783d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.g.g.a.a("RV Prefetch");
            if (!this.f1783d.isEmpty()) {
                int size = this.f1783d.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f1783d.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f);
                }
            }
        } finally {
            this.f1784e = 0L;
            b.g.g.a.b();
        }
    }
}
