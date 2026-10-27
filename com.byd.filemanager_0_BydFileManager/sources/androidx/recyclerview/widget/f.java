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
final class f implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final ThreadLocal<f> f1446b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static Comparator<c> f1447c = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f1449e;
    long f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<RecyclerView> f1448d = new ArrayList<>();
    private ArrayList<c> g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f1457d;
            if ((recyclerView == null) != (cVar2.f1457d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f1454a;
            if (z != cVar2.f1454a) {
                return z ? -1 : 1;
            }
            int i = cVar2.f1455b - cVar.f1455b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.f1456c - cVar2.f1456c;
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
        int f1450a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1451b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int[] f1452c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1453d;

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
            int i3 = this.f1453d * 2;
            int[] iArr = this.f1452c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1452c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.f1452c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1452c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.f1453d++;
        }

        void b() {
            int[] iArr = this.f1452c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1453d = 0;
        }

        void c(RecyclerView recyclerView, boolean z) {
            this.f1453d = 0;
            int[] iArr = this.f1452c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.w;
            if (recyclerView.v == null || oVar == null || !oVar.A0()) {
                return;
            }
            if (z) {
                if (!recyclerView.n.p()) {
                    oVar.z(recyclerView.v.c(), this);
                }
            } else if (!recyclerView.o0()) {
                oVar.y(this.f1450a, this.f1451b, recyclerView.o0, this);
            }
            int i = this.f1453d;
            if (i > oVar.n) {
                oVar.n = i;
                oVar.o = z;
                recyclerView.l.K();
            }
        }

        boolean d(int i) {
            if (this.f1452c != null) {
                int i2 = this.f1453d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1452c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i, int i2) {
            this.f1450a = i;
            this.f1451b = i2;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1454a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1455b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1456c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f1457d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1458e;

        c() {
        }

        public void a() {
            this.f1454a = false;
            this.f1455b = 0;
            this.f1456c = 0;
            this.f1457d = null;
            this.f1458e = 0;
        }
    }

    f() {
    }

    private void b() {
        c cVar;
        int size = this.f1448d.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f1448d.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.n0.c(recyclerView, false);
                i += recyclerView.n0.f1453d;
            }
        }
        this.g.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f1448d.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.n0;
                int iAbs = Math.abs(bVar.f1450a) + Math.abs(bVar.f1451b);
                for (int i5 = 0; i5 < bVar.f1453d * 2; i5 += 2) {
                    if (i3 >= this.g.size()) {
                        cVar = new c();
                        this.g.add(cVar);
                    } else {
                        cVar = this.g.get(i3);
                    }
                    int[] iArr = bVar.f1452c;
                    int i6 = iArr[i5 + 1];
                    cVar.f1454a = i6 <= iAbs;
                    cVar.f1455b = iAbs;
                    cVar.f1456c = i6;
                    cVar.f1457d = recyclerView2;
                    cVar.f1458e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.g, f1447c);
    }

    private void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.f1457d, cVar.f1458e, cVar.f1454a ? Long.MAX_VALUE : j);
        if (c0VarI == null || c0VarI.f1301c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f1301c.get(), j);
    }

    private void d(long j) {
        for (int i = 0; i < this.g.size(); i++) {
            c cVar = this.g.get(i);
            if (cVar.f1457d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i) {
        int iJ = recyclerView.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            RecyclerView.c0 c0VarH0 = RecyclerView.h0(recyclerView.o.i(i2));
            if (c0VarH0.f1302d == i && !c0VarH0.t()) {
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
            recyclerView.W0();
        }
        b bVar = recyclerView.n0;
        bVar.c(recyclerView, true);
        if (bVar.f1453d != 0) {
            try {
                b.g.g.a.a("RV Nested Prefetch");
                recyclerView.o0.f(recyclerView.v);
                for (int i = 0; i < bVar.f1453d * 2; i += 2) {
                    i(recyclerView, bVar.f1452c[i], j);
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
            recyclerView.I0();
            RecyclerView.c0 c0VarI = vVar.I(i, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f1300b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.K0(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f1448d.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.f1449e == 0) {
            this.f1449e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.n0.e(i, i2);
    }

    void g(long j) {
        b();
        d(j);
    }

    public void j(RecyclerView recyclerView) {
        this.f1448d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.g.g.a.a("RV Prefetch");
            if (!this.f1448d.isEmpty()) {
                int size = this.f1448d.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f1448d.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f);
                }
            }
        } finally {
            this.f1449e = 0L;
            b.g.g.a.b();
        }
    }
}
