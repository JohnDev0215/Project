package b.o.a;

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
    public static final ThreadLocal<e> f1386b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Comparator<c> f1387c = new a();
    public long e;
    public long f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<RecyclerView> f1388d = new ArrayList<>();
    public ArrayList<c> g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f1396d;
            if ((recyclerView == null) != (cVar2.f1396d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f1393a;
            if (z != cVar2.f1393a) {
                return z ? -1 : 1;
            }
            int i = cVar2.f1394b - cVar.f1394b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.f1395c - cVar2.f1395c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1389a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1390b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f1391c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1392d;

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.f1392d * 2;
            int[] iArr = this.f1391c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1391c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.f1391c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1391c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.f1392d++;
        }

        public void b() {
            int[] iArr = this.f1391c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1392d = 0;
        }

        public void c(RecyclerView recyclerView, boolean z) {
            this.f1392d = 0;
            int[] iArr = this.f1391c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || oVar == null || !oVar.u0()) {
                return;
            }
            if (z) {
                if (!recyclerView.mAdapterHelper.p()) {
                    oVar.p(recyclerView.mAdapter.c(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                oVar.o(this.f1389a, this.f1390b, recyclerView.mState, this);
            }
            int i = this.f1392d;
            if (i > oVar.m) {
                oVar.m = i;
                oVar.n = z;
                recyclerView.mRecycler.K();
            }
        }

        public boolean d(int i) {
            if (this.f1391c != null) {
                int i2 = this.f1392d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1391c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void e(int i, int i2) {
            this.f1389a = i;
            this.f1390b = i2;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1393a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1394b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1395c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f1396d;
        public int e;

        public void a() {
            this.f1393a = false;
            this.f1394b = 0;
            this.f1395c = 0;
            this.f1396d = null;
            this.e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i) {
        int iJ = recyclerView.mChildHelper.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            RecyclerView.c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.i(i2));
            if (childViewHolderInt.f445d == i && !childViewHolderInt.t()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.f1388d.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.f1388d.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f1388d.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.c(recyclerView, false);
                i += recyclerView.mPrefetchRegistry.f1392d;
            }
        }
        this.g.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f1388d.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.mPrefetchRegistry;
                int iAbs = Math.abs(bVar.f1389a) + Math.abs(bVar.f1390b);
                for (int i5 = 0; i5 < bVar.f1392d * 2; i5 += 2) {
                    if (i3 >= this.g.size()) {
                        cVar = new c();
                        this.g.add(cVar);
                    } else {
                        cVar = this.g.get(i3);
                    }
                    int[] iArr = bVar.f1391c;
                    int i6 = iArr[i5 + 1];
                    cVar.f1393a = i6 <= iAbs;
                    cVar.f1394b = iAbs;
                    cVar.f1395c = i6;
                    cVar.f1396d = recyclerView2;
                    cVar.e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.g, f1387c);
    }

    public final void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.f1396d, cVar.e, cVar.f1393a ? RecyclerView.FOREVER_NS : j);
        if (c0VarI == null || c0VarI.f444c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f444c.get(), j);
    }

    public final void d(long j) {
        for (int i = 0; i < this.g.size(); i++) {
            c cVar = this.g.get(i);
            if (cVar.f1396d == null) {
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
        recyclerView.mPrefetchRegistry.e(i, i2);
    }

    public void g(long j) {
        b();
        d(j);
    }

    public final void h(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.j() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        b bVar = recyclerView.mPrefetchRegistry;
        bVar.c(recyclerView, true);
        if (bVar.f1392d != 0) {
            try {
                b.g.f.a.a(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
                recyclerView.mState.f(recyclerView.mAdapter);
                for (int i = 0; i < bVar.f1392d * 2; i += 2) {
                    i(recyclerView, bVar.f1391c[i], j);
                }
            } finally {
                b.g.f.a.b();
            }
        }
    }

    public final RecyclerView.c0 i(RecyclerView recyclerView, int i, long j) {
        if (e(recyclerView, i)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.c0 c0VarI = vVar.I(i, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f443b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.f1388d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.g.f.a.a(RecyclerView.TRACE_PREFETCH_TAG);
            if (!this.f1388d.isEmpty()) {
                int size = this.f1388d.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f1388d.get(i);
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
            b.g.f.a.b();
        }
    }
}
