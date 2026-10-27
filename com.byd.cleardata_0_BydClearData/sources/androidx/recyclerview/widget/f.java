package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class f implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final ThreadLocal<f> f1580b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static Comparator<c> f1581c = new a();
    long e;
    long f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<RecyclerView> f1582d = new ArrayList<>();
    private ArrayList<c> g = new ArrayList<>();

    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f1590d;
            if ((recyclerView == null) != (cVar2.f1590d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f1587a;
            if (z != cVar2.f1587a) {
                return z ? -1 : 1;
            }
            int i = cVar2.f1588b - cVar.f1588b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.f1589c - cVar2.f1589c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    @SuppressLint({"VisibleForTests"})
    static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1583a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1584b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int[] f1585c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1586d;

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
            int i3 = this.f1586d * 2;
            int[] iArr = this.f1585c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1585c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.f1585c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1585c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.f1586d++;
        }

        void b() {
            int[] iArr = this.f1585c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1586d = 0;
        }

        void c(RecyclerView recyclerView, boolean z) {
            this.f1586d = 0;
            int[] iArr = this.f1585c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.w;
            if (recyclerView.v == null || oVar == null || !oVar.M0()) {
                return;
            }
            if (z) {
                if (!recyclerView.n.p()) {
                    oVar.L(recyclerView.v.c(), this);
                }
            } else if (!recyclerView.n0()) {
                oVar.K(this.f1583a, this.f1584b, recyclerView.o0, this);
            }
            int i = this.f1586d;
            if (i > oVar.m) {
                oVar.m = i;
                oVar.n = z;
                recyclerView.l.K();
            }
        }

        boolean d(int i) {
            if (this.f1585c != null) {
                int i2 = this.f1586d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1585c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i, int i2) {
            this.f1583a = i;
            this.f1584b = i2;
        }
    }

    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1588b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1589c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f1590d;
        public int e;

        c() {
        }

        public void a() {
            this.f1587a = false;
            this.f1588b = 0;
            this.f1589c = 0;
            this.f1590d = null;
            this.e = 0;
        }
    }

    f() {
    }

    private void b() {
        c cVar;
        int size = this.f1582d.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f1582d.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.n0.c(recyclerView, false);
                i += recyclerView.n0.f1586d;
            }
        }
        this.g.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f1582d.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.n0;
                int iAbs = Math.abs(bVar.f1583a) + Math.abs(bVar.f1584b);
                for (int i5 = 0; i5 < bVar.f1586d * 2; i5 += 2) {
                    if (i3 >= this.g.size()) {
                        cVar = new c();
                        this.g.add(cVar);
                    } else {
                        cVar = this.g.get(i3);
                    }
                    int[] iArr = bVar.f1585c;
                    int i6 = iArr[i5 + 1];
                    cVar.f1587a = i6 <= iAbs;
                    cVar.f1588b = iAbs;
                    cVar.f1589c = i6;
                    cVar.f1590d = recyclerView2;
                    cVar.e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.g, f1581c);
    }

    private void c(c cVar, long j) {
        RecyclerView.d0 d0VarI = i(cVar.f1590d, cVar.e, cVar.f1587a ? Long.MAX_VALUE : j);
        if (d0VarI == null || d0VarI.f1455c == null || !d0VarI.s() || d0VarI.t()) {
            return;
        }
        h(d0VarI.f1455c.get(), j);
    }

    private void d(long j) {
        for (int i = 0; i < this.g.size(); i++) {
            c cVar = this.g.get(i);
            if (cVar.f1590d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i) {
        int iJ = recyclerView.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            RecyclerView.d0 d0VarG0 = RecyclerView.g0(recyclerView.o.i(i2));
            if (d0VarG0.f1456d == i && !d0VarG0.t()) {
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
            recyclerView.V0();
        }
        b bVar = recyclerView.n0;
        bVar.c(recyclerView, true);
        if (bVar.f1586d != 0) {
            try {
                a.g.g.a.a("RV Nested Prefetch");
                recyclerView.o0.f(recyclerView.v);
                for (int i = 0; i < bVar.f1586d * 2; i += 2) {
                    i(recyclerView, bVar.f1585c[i], j);
                }
            } finally {
                a.g.g.a.b();
            }
        }
    }

    private RecyclerView.d0 i(RecyclerView recyclerView, int i, long j) {
        if (e(recyclerView, i)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.l;
        try {
            recyclerView.H0();
            RecyclerView.d0 d0VarI = vVar.I(i, false, j);
            if (d0VarI != null) {
                if (!d0VarI.s() || d0VarI.t()) {
                    vVar.a(d0VarI, false);
                } else {
                    vVar.B(d0VarI.f1454b);
                }
            }
            return d0VarI;
        } finally {
            recyclerView.J0(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f1582d.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.e == 0) {
            this.e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.n0.e(i, i2);
    }

    void g(long j) {
        b();
        d(j);
    }

    public void j(RecyclerView recyclerView) {
        this.f1582d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a.g.g.a.a("RV Prefetch");
            if (!this.f1582d.isEmpty()) {
                int size = this.f1582d.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f1582d.get(i);
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
            a.g.g.a.b();
        }
    }
}
