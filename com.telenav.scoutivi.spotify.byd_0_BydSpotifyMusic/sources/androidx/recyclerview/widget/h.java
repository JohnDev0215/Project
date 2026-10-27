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
final class h implements Runnable {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static final ThreadLocal<h> f3131j = new ThreadLocal<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static Comparator<c> f3132k = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    long f3134g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    long f3135h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<RecyclerView> f3133e = new ArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ArrayList<c> f3136i = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f3144d;
            if ((recyclerView == null) != (cVar2.f3144d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z6 = cVar.f3141a;
            if (z6 != cVar2.f3141a) {
                return z6 ? -1 : 1;
            }
            int i7 = cVar2.f3142b - cVar.f3142b;
            if (i7 != 0) {
                return i7;
            }
            int i8 = cVar.f3143c - cVar2.f3143c;
            if (i8 != 0) {
                return i8;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3138b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int[] f3139c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3140d;

        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i7, int i8) {
            if (i7 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i8 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i9 = this.f3140d * 2;
            int[] iArr = this.f3139c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f3139c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i9 >= iArr.length) {
                int[] iArr3 = new int[i9 * 2];
                this.f3139c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f3139c;
            iArr4[i9] = i7;
            iArr4[i9 + 1] = i8;
            this.f3140d++;
        }

        void b() {
            int[] iArr = this.f3139c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f3140d = 0;
        }

        void c(RecyclerView recyclerView, boolean z6) {
            this.f3140d = 0;
            int[] iArr = this.f3139c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.f2828r;
            if (recyclerView.f2826q == null || oVar == null || !oVar.u0()) {
                return;
            }
            if (z6) {
                if (!recyclerView.f2810i.p()) {
                    oVar.p(recyclerView.f2826q.e(), this);
                }
            } else if (!recyclerView.n0()) {
                oVar.o(this.f3137a, this.f3138b, recyclerView.f2819m0, this);
            }
            int i7 = this.f3140d;
            if (i7 > oVar.f2903m) {
                oVar.f2903m = i7;
                oVar.f2904n = z6;
                recyclerView.f2806g.K();
            }
        }

        boolean d(int i7) {
            if (this.f3139c != null) {
                int i8 = this.f3140d * 2;
                for (int i9 = 0; i9 < i8; i9 += 2) {
                    if (this.f3139c[i9] == i7) {
                        return true;
                    }
                }
            }
            return false;
        }

        void e(int i7, int i8) {
            this.f3137a = i7;
            this.f3138b = i8;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3141a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3142b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3143c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f3144d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3145e;

        c() {
        }

        public void a() {
            this.f3141a = false;
            this.f3142b = 0;
            this.f3143c = 0;
            this.f3144d = null;
            this.f3145e = 0;
        }
    }

    h() {
    }

    private void b() {
        c cVar;
        int size = this.f3133e.size();
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            RecyclerView recyclerView = this.f3133e.get(i8);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.f2817l0.c(recyclerView, false);
                i7 += recyclerView.f2817l0.f3140d;
            }
        }
        this.f3136i.ensureCapacity(i7);
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            RecyclerView recyclerView2 = this.f3133e.get(i10);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.f2817l0;
                int iAbs = Math.abs(bVar.f3137a) + Math.abs(bVar.f3138b);
                for (int i11 = 0; i11 < bVar.f3140d * 2; i11 += 2) {
                    if (i9 >= this.f3136i.size()) {
                        cVar = new c();
                        this.f3136i.add(cVar);
                    } else {
                        cVar = this.f3136i.get(i9);
                    }
                    int[] iArr = bVar.f3139c;
                    int i12 = iArr[i11 + 1];
                    cVar.f3141a = i12 <= iAbs;
                    cVar.f3142b = iAbs;
                    cVar.f3143c = i12;
                    cVar.f3144d = recyclerView2;
                    cVar.f3145e = iArr[i11];
                    i9++;
                }
            }
        }
        Collections.sort(this.f3136i, f3132k);
    }

    private void c(c cVar, long j7) {
        RecyclerView.c0 c0VarI = i(cVar.f3144d, cVar.f3145e, cVar.f3141a ? Long.MAX_VALUE : j7);
        if (c0VarI == null || c0VarI.f2858b == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f2858b.get(), j7);
    }

    private void d(long j7) {
        for (int i7 = 0; i7 < this.f3136i.size(); i7++) {
            c cVar = this.f3136i.get(i7);
            if (cVar.f3144d == null) {
                return;
            }
            c(cVar, j7);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i7) {
        int iJ = recyclerView.f2812j.j();
        for (int i8 = 0; i8 < iJ; i8++) {
            RecyclerView.c0 c0VarG0 = RecyclerView.g0(recyclerView.f2812j.i(i8));
            if (c0VarG0.f2859c == i7 && !c0VarG0.t()) {
                return true;
            }
        }
        return false;
    }

    private void h(RecyclerView recyclerView, long j7) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.I && recyclerView.f2812j.j() != 0) {
            recyclerView.V0();
        }
        b bVar = recyclerView.f2817l0;
        bVar.c(recyclerView, true);
        if (bVar.f3140d != 0) {
            try {
                d0.c.a("RV Nested Prefetch");
                recyclerView.f2819m0.f(recyclerView.f2826q);
                for (int i7 = 0; i7 < bVar.f3140d * 2; i7 += 2) {
                    i(recyclerView, bVar.f3139c[i7], j7);
                }
            } finally {
                d0.c.b();
            }
        }
    }

    private RecyclerView.c0 i(RecyclerView recyclerView, int i7, long j7) {
        if (e(recyclerView, i7)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.f2806g;
        try {
            recyclerView.H0();
            RecyclerView.c0 c0VarI = vVar.I(i7, false, j7);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f2857a);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.J0(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f3133e.add(recyclerView);
    }

    void f(RecyclerView recyclerView, int i7, int i8) {
        if (recyclerView.isAttachedToWindow() && this.f3134g == 0) {
            this.f3134g = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.f2817l0.e(i7, i8);
    }

    void g(long j7) {
        b();
        d(j7);
    }

    public void j(RecyclerView recyclerView) {
        this.f3133e.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            d0.c.a("RV Prefetch");
            if (!this.f3133e.isEmpty()) {
                int size = this.f3133e.size();
                long jMax = 0;
                for (int i7 = 0; i7 < size; i7++) {
                    RecyclerView recyclerView = this.f3133e.get(i7);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f3135h);
                }
            }
        } finally {
            this.f3134g = 0L;
            d0.c.b();
        }
    }
}
