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
    static final ThreadLocal<e> f1975b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static Comparator<c> f1976c = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f1978e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    long f1979f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<RecyclerView> f1977d = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<c> f1980g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f1988d;
            if ((recyclerView == null) != (cVar2.f1988d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f1985a;
            if (z != cVar2.f1985a) {
                return z ? -1 : 1;
            }
            int i2 = cVar2.f1986b - cVar.f1986b;
            if (i2 != 0) {
                return i2;
            }
            int i3 = cVar.f1987c - cVar2.f1987c;
            if (i3 != 0) {
                return i3;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1981a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1982b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int[] f1983c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1984d;

        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i2, int i3) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i3 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i4 = this.f1984d * 2;
            int[] iArr = this.f1983c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1983c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i4 >= iArr.length) {
                int[] iArr3 = new int[i4 * 2];
                this.f1983c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1983c;
            iArr4[i4] = i2;
            iArr4[i4 + 1] = i3;
            this.f1984d++;
        }

        void b() {
            int[] iArr = this.f1983c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1984d = 0;
        }

        void c(RecyclerView recyclerView, boolean z) {
            this.f1984d = 0;
            int[] iArr = this.f1983c;
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
            } else if (!recyclerView.m0()) {
                oVar.o(this.f1981a, this.f1982b, recyclerView.o0, this);
            }
            int i2 = this.f1984d;
            if (i2 > oVar.m) {
                oVar.m = i2;
                oVar.n = z;
                recyclerView.l.K();
            }
        }

        boolean d(int i2) {
            if (this.f1983c != null) {
                int i3 = this.f1984d * 2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.f1983c[i4] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i2, int i3) {
            this.f1981a = i2;
            this.f1982b = i3;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1985a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1986b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1987c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f1988d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1989e;

        c() {
        }

        public void a() {
            this.f1985a = false;
            this.f1986b = 0;
            this.f1987c = 0;
            this.f1988d = null;
            this.f1989e = 0;
        }
    }

    e() {
    }

    private void b() {
        c cVar;
        int size = this.f1977d.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView recyclerView = this.f1977d.get(i3);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.n0.c(recyclerView, false);
                i2 += recyclerView.n0.f1984d;
            }
        }
        this.f1980g.ensureCapacity(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = this.f1977d.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.n0;
                int iAbs = Math.abs(bVar.f1981a) + Math.abs(bVar.f1982b);
                for (int i6 = 0; i6 < bVar.f1984d * 2; i6 += 2) {
                    if (i4 >= this.f1980g.size()) {
                        cVar = new c();
                        this.f1980g.add(cVar);
                    } else {
                        cVar = this.f1980g.get(i4);
                    }
                    int[] iArr = bVar.f1983c;
                    int i7 = iArr[i6 + 1];
                    cVar.f1985a = i7 <= iAbs;
                    cVar.f1986b = iAbs;
                    cVar.f1987c = i7;
                    cVar.f1988d = recyclerView2;
                    cVar.f1989e = iArr[i6];
                    i4++;
                }
            }
        }
        Collections.sort(this.f1980g, f1976c);
    }

    private void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.f1988d, cVar.f1989e, cVar.f1985a ? Long.MAX_VALUE : j);
        if (c0VarI == null || c0VarI.f1791c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f1791c.get(), j);
    }

    private void d(long j) {
        for (int i2 = 0; i2 < this.f1980g.size(); i2++) {
            c cVar = this.f1980g.get(i2);
            if (cVar.f1988d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i2) {
        int iJ = recyclerView.o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            RecyclerView.c0 c0VarG0 = RecyclerView.g0(recyclerView.o.i(i3));
            if (c0VarG0.f1792d == i2 && !c0VarG0.t()) {
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
            recyclerView.U0();
        }
        b bVar = recyclerView.n0;
        bVar.c(recyclerView, true);
        if (bVar.f1984d != 0) {
            try {
                b.g.g.b.a("RV Nested Prefetch");
                recyclerView.o0.f(recyclerView.v);
                for (int i2 = 0; i2 < bVar.f1984d * 2; i2 += 2) {
                    i(recyclerView, bVar.f1983c[i2], j);
                }
            } finally {
                b.g.g.b.b();
            }
        }
    }

    private RecyclerView.c0 i(RecyclerView recyclerView, int i2, long j) {
        if (e(recyclerView, i2)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.l;
        try {
            recyclerView.G0();
            RecyclerView.c0 c0VarI = vVar.I(i2, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f1790b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.I0(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f1977d.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow() && this.f1978e == 0) {
            this.f1978e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.n0.e(i2, i3);
    }

    void g(long j) {
        b();
        d(j);
    }

    public void j(RecyclerView recyclerView) {
        this.f1977d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.g.g.b.a("RV Prefetch");
            if (!this.f1977d.isEmpty()) {
                int size = this.f1977d.size();
                long jMax = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = this.f1977d.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f1979f);
                }
            }
        } finally {
            this.f1978e = 0L;
            b.g.g.b.b();
        }
    }
}
