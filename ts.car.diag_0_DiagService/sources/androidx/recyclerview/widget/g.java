package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
final class g implements Runnable {
    static final ThreadLocal<g> g = new ThreadLocal<>();
    static Comparator<c> h = new a();
    long d;
    long e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<RecyclerView> f1207c = new ArrayList<>();
    private ArrayList<c> f = new ArrayList<>();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            if ((cVar.d == null) != (cVar2.d == null)) {
                return cVar.d == null ? 1 : -1;
            }
            boolean z = cVar.f1211a;
            if (z != cVar2.f1211a) {
                return z ? -1 : 1;
            }
            int i = cVar2.f1212b - cVar.f1212b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.f1213c - cVar2.f1213c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"VisibleForTests"})
    static class b implements RecyclerView.m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1208a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1209b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int[] f1210c;
        int d;

        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.m.c
        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.d * 2;
            int[] iArr = this.f1210c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1210c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.f1210c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1210c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.d++;
        }

        void b() {
            int[] iArr = this.f1210c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.d = 0;
        }

        void c(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            int[] iArr = this.f1210c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.m mVar = recyclerView.mLayout;
            if (recyclerView.n == null || mVar == null || !mVar.s0()) {
                return;
            }
            if (z) {
                if (!recyclerView.f.p()) {
                    mVar.o(recyclerView.n.f(), this);
                }
            } else if (!recyclerView.p0()) {
                mVar.n(this.f1208a, this.f1209b, recyclerView.h0, this);
            }
            int i = this.d;
            if (i > mVar.m) {
                mVar.m = i;
                mVar.n = z;
                recyclerView.d.H();
            }
        }

        boolean d(int i) {
            if (this.f1210c != null) {
                int i2 = this.d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1210c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i, int i2) {
            this.f1208a = i;
            this.f1209b = i2;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1213c;
        public RecyclerView d;
        public int e;

        c() {
        }

        public void a() {
            this.f1211a = false;
            this.f1212b = 0;
            this.f1213c = 0;
            this.d = null;
            this.e = 0;
        }
    }

    g() {
    }

    private void b() {
        c cVar;
        int size = this.f1207c.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f1207c.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.g0.c(recyclerView, false);
                i += recyclerView.g0.d;
            }
        }
        this.f.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f1207c.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.g0;
                int iAbs = Math.abs(bVar.f1208a) + Math.abs(bVar.f1209b);
                for (int i5 = 0; i5 < bVar.d * 2; i5 += 2) {
                    if (i3 >= this.f.size()) {
                        cVar = new c();
                        this.f.add(cVar);
                    } else {
                        cVar = this.f.get(i3);
                    }
                    int i6 = bVar.f1210c[i5 + 1];
                    cVar.f1211a = i6 <= iAbs;
                    cVar.f1212b = iAbs;
                    cVar.f1213c = i6;
                    cVar.d = recyclerView2;
                    cVar.e = bVar.f1210c[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.f, h);
    }

    private void c(c cVar, long j) {
        RecyclerView.A aI = i(cVar.d, cVar.e, cVar.f1211a ? Long.MAX_VALUE : j);
        if (aI == null || aI.f1093b == null || !aI.s() || aI.t()) {
            return;
        }
        h(aI.f1093b.get(), j);
    }

    private void d(long j) {
        for (int i = 0; i < this.f.size(); i++) {
            c cVar = this.f.get(i);
            if (cVar.d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i) {
        int iJ = recyclerView.g.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            RecyclerView.A aF0 = RecyclerView.f0(recyclerView.g.i(i2));
            if (aF0.f1094c == i && !aF0.t()) {
                return true;
            }
        }
        return false;
    }

    private void h(@Nullable RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.D && recyclerView.g.j() != 0) {
            recyclerView.X0();
        }
        b bVar = recyclerView.g0;
        bVar.c(recyclerView, true);
        if (bVar.d != 0) {
            try {
                androidx.core.os.c.a("RV Nested Prefetch");
                recyclerView.h0.f(recyclerView.n);
                for (int i = 0; i < bVar.d * 2; i += 2) {
                    i(recyclerView, bVar.f1210c[i], j);
                }
            } finally {
                androidx.core.os.c.b();
            }
        }
    }

    private RecyclerView.A i(RecyclerView recyclerView, int i, long j) {
        if (e(recyclerView, i)) {
            return null;
        }
        RecyclerView.t tVar = recyclerView.d;
        try {
            recyclerView.J0();
            RecyclerView.A aF = tVar.F(i, false, j);
            if (aF != null) {
                if (!aF.s() || aF.t()) {
                    tVar.a(aF, false);
                } else {
                    tVar.B(aF.f1092a);
                }
            }
            return aF;
        } finally {
            recyclerView.L0(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f1207c.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.d == 0) {
            this.d = recyclerView.l0();
            recyclerView.post(this);
        }
        recyclerView.g0.e(i, i2);
    }

    void g(long j) {
        b();
        d(j);
    }

    public void j(RecyclerView recyclerView) {
        this.f1207c.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            androidx.core.os.c.a("RV Prefetch");
            if (!this.f1207c.isEmpty()) {
                int size = this.f1207c.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f1207c.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.e);
                }
            }
        } finally {
            this.d = 0L;
            androidx.core.os.c.b();
        }
    }
}
