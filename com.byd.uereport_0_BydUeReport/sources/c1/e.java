package c1;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal<e> f2244d = new ThreadLocal<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Comparator<c> f2245e = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f2247g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f2248h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<RecyclerView> f2246f = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<c> f2249i = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f2257d;
            if ((recyclerView == null) != (cVar2.f2257d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z7 = cVar.f2254a;
            if (z7 != cVar2.f2254a) {
                return z7 ? -1 : 1;
            }
            int i8 = cVar2.f2255b - cVar.f2255b;
            if (i8 != 0) {
                return i8;
            }
            int i9 = cVar.f2256c - cVar2.f2256c;
            if (i9 != 0) {
                return i9;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2250a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2251b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f2252c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2253d;

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i8, int i9) {
            if (i8 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i9 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i10 = this.f2253d * 2;
            int[] iArr = this.f2252c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f2252c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i10 >= iArr.length) {
                int[] iArr3 = new int[i10 * 2];
                this.f2252c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f2252c;
            iArr4[i10] = i8;
            iArr4[i10 + 1] = i9;
            this.f2253d++;
        }

        public void b() {
            int[] iArr = this.f2252c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f2253d = 0;
        }

        public void c(RecyclerView recyclerView, boolean z7) {
            this.f2253d = 0;
            int[] iArr = this.f2252c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || oVar == null || !oVar.isItemPrefetchEnabled()) {
                return;
            }
            if (z7) {
                if (!recyclerView.mAdapterHelper.p()) {
                    oVar.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                oVar.collectAdjacentPrefetchPositions(this.f2250a, this.f2251b, recyclerView.mState, this);
            }
            int i8 = this.f2253d;
            if (i8 > oVar.mPrefetchMaxCountObserved) {
                oVar.mPrefetchMaxCountObserved = i8;
                oVar.mPrefetchMaxObservedInInitialPrefetch = z7;
                recyclerView.mRecycler.K();
            }
        }

        public boolean d(int i8) {
            if (this.f2252c != null) {
                int i9 = this.f2253d * 2;
                for (int i10 = 0; i10 < i9; i10 += 2) {
                    if (this.f2252c[i10] == i8) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void e(int i8, int i9) {
            this.f2250a = i8;
            this.f2251b = i9;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2254a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2255b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2256c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f2257d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2258e;

        public void a() {
            this.f2254a = false;
            this.f2255b = 0;
            this.f2256c = 0;
            this.f2257d = null;
            this.f2258e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i8) {
        int iJ = recyclerView.mChildHelper.j();
        for (int i9 = 0; i9 < iJ; i9++) {
            RecyclerView.c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.i(i9));
            if (childViewHolderInt.mPosition == i8 && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.f2246f.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.f2246f.size();
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            RecyclerView recyclerView = this.f2246f.get(i9);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.c(recyclerView, false);
                i8 += recyclerView.mPrefetchRegistry.f2253d;
            }
        }
        this.f2249i.ensureCapacity(i8);
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            RecyclerView recyclerView2 = this.f2246f.get(i11);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.mPrefetchRegistry;
                int iAbs = Math.abs(bVar.f2250a) + Math.abs(bVar.f2251b);
                for (int i12 = 0; i12 < bVar.f2253d * 2; i12 += 2) {
                    if (i10 >= this.f2249i.size()) {
                        cVar = new c();
                        this.f2249i.add(cVar);
                    } else {
                        cVar = this.f2249i.get(i10);
                    }
                    int[] iArr = bVar.f2252c;
                    int i13 = iArr[i12 + 1];
                    cVar.f2254a = i13 <= iAbs;
                    cVar.f2255b = iAbs;
                    cVar.f2256c = i13;
                    cVar.f2257d = recyclerView2;
                    cVar.f2258e = iArr[i12];
                    i10++;
                }
            }
        }
        Collections.sort(this.f2249i, f2245e);
    }

    public final void c(c cVar, long j8) {
        RecyclerView.c0 c0VarI = i(cVar.f2257d, cVar.f2258e, cVar.f2254a ? RecyclerView.FOREVER_NS : j8);
        if (c0VarI == null || c0VarI.mNestedRecyclerView == null || !c0VarI.isBound() || c0VarI.isInvalid()) {
            return;
        }
        h(c0VarI.mNestedRecyclerView.get(), j8);
    }

    public final void d(long j8) {
        for (int i8 = 0; i8 < this.f2249i.size(); i8++) {
            c cVar = this.f2249i.get(i8);
            if (cVar.f2257d == null) {
                return;
            }
            c(cVar, j8);
            cVar.a();
        }
    }

    public void f(RecyclerView recyclerView, int i8, int i9) {
        if (recyclerView.isAttachedToWindow() && this.f2247g == 0) {
            this.f2247g = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.e(i8, i9);
    }

    public void g(long j8) {
        b();
        d(j8);
    }

    public final void h(RecyclerView recyclerView, long j8) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.j() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        b bVar = recyclerView.mPrefetchRegistry;
        bVar.c(recyclerView, true);
        if (bVar.f2253d != 0) {
            try {
                k0.a.a(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
                recyclerView.mState.f(recyclerView.mAdapter);
                for (int i8 = 0; i8 < bVar.f2253d * 2; i8 += 2) {
                    i(recyclerView, bVar.f2252c[i8], j8);
                }
            } finally {
                k0.a.b();
            }
        }
    }

    public final RecyclerView.c0 i(RecyclerView recyclerView, int i8, long j8) {
        if (e(recyclerView, i8)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.c0 c0VarI = vVar.I(i8, false, j8);
            if (c0VarI != null) {
                if (!c0VarI.isBound() || c0VarI.isInvalid()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.itemView);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.f2246f.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            k0.a.a(RecyclerView.TRACE_PREFETCH_TAG);
            if (!this.f2246f.isEmpty()) {
                int size = this.f2246f.size();
                long jMax = 0;
                for (int i8 = 0; i8 < size; i8++) {
                    RecyclerView recyclerView = this.f2246f.get(i8);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f2248h);
                }
            }
        } finally {
            this.f2247g = 0L;
            k0.a.b();
        }
    }
}
