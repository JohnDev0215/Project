package b.r.d;

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
    public static final ThreadLocal<e> f3087b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Comparator<c> f3088c = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3090e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f3091f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<RecyclerView> f3089d = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<c> f3092g = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f3100d;
            if ((recyclerView == null) != (cVar2.f3100d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f3097a;
            if (z != cVar2.f3097a) {
                return z ? -1 : 1;
            }
            int i2 = cVar2.f3098b - cVar.f3098b;
            if (i2 != 0) {
                return i2;
            }
            int i3 = cVar.f3099c - cVar2.f3099c;
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
        public int f3093a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3094b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f3095c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3096d;

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i2, int i3) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i3 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i4 = this.f3096d * 2;
            int[] iArr = this.f3095c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f3095c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i4 >= iArr.length) {
                int[] iArr3 = new int[i4 * 2];
                this.f3095c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f3095c;
            iArr4[i4] = i2;
            iArr4[i4 + 1] = i3;
            this.f3096d++;
        }

        public void b() {
            int[] iArr = this.f3095c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f3096d = 0;
        }

        public void c(RecyclerView recyclerView, boolean z) {
            this.f3096d = 0;
            int[] iArr = this.f3095c;
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
            } else if (!recyclerView.n0()) {
                oVar.o(this.f3093a, this.f3094b, recyclerView.o0, this);
            }
            int i2 = this.f3096d;
            if (i2 > oVar.m) {
                oVar.m = i2;
                oVar.n = z;
                recyclerView.l.K();
            }
        }

        public boolean d(int i2) {
            if (this.f3095c != null) {
                int i3 = this.f3096d * 2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.f3095c[i4] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void e(int i2, int i3) {
            this.f3093a = i2;
            this.f3094b = i3;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3098b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3099c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f3100d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3101e;

        public void a() {
            this.f3097a = false;
            this.f3098b = 0;
            this.f3099c = 0;
            this.f3100d = null;
            this.f3101e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i2) {
        int iJ = recyclerView.o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            RecyclerView.c0 c0VarH0 = RecyclerView.h0(recyclerView.o.i(i3));
            if (c0VarH0.f1187d == i2 && !c0VarH0.t()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.f3089d.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.f3089d.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView recyclerView = this.f3089d.get(i3);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.n0.c(recyclerView, false);
                i2 += recyclerView.n0.f3096d;
            }
        }
        this.f3092g.ensureCapacity(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = this.f3089d.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.n0;
                int iAbs = Math.abs(bVar.f3093a) + Math.abs(bVar.f3094b);
                for (int i6 = 0; i6 < bVar.f3096d * 2; i6 += 2) {
                    if (i4 >= this.f3092g.size()) {
                        cVar = new c();
                        this.f3092g.add(cVar);
                    } else {
                        cVar = this.f3092g.get(i4);
                    }
                    int[] iArr = bVar.f3095c;
                    int i7 = iArr[i6 + 1];
                    cVar.f3097a = i7 <= iAbs;
                    cVar.f3098b = iAbs;
                    cVar.f3099c = i7;
                    cVar.f3100d = recyclerView2;
                    cVar.f3101e = iArr[i6];
                    i4++;
                }
            }
        }
        Collections.sort(this.f3092g, f3088c);
    }

    public final void c(c cVar, long j) {
        RecyclerView.c0 c0VarI = i(cVar.f3100d, cVar.f3101e, cVar.f3097a ? Long.MAX_VALUE : j);
        if (c0VarI == null || c0VarI.f1186c == null || !c0VarI.s() || c0VarI.t()) {
            return;
        }
        h(c0VarI.f1186c.get(), j);
    }

    public final void d(long j) {
        for (int i2 = 0; i2 < this.f3092g.size(); i2++) {
            c cVar = this.f3092g.get(i2);
            if (cVar.f3100d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    public void f(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow() && this.f3090e == 0) {
            this.f3090e = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.n0.e(i2, i3);
    }

    public void g(long j) {
        b();
        d(j);
    }

    public final void h(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.N && recyclerView.o.j() != 0) {
            recyclerView.V0();
        }
        b bVar = recyclerView.n0;
        bVar.c(recyclerView, true);
        if (bVar.f3096d != 0) {
            try {
                b.h.i.b.a("RV Nested Prefetch");
                recyclerView.o0.f(recyclerView.v);
                for (int i2 = 0; i2 < bVar.f3096d * 2; i2 += 2) {
                    i(recyclerView, bVar.f3095c[i2], j);
                }
            } finally {
                b.h.i.b.b();
            }
        }
    }

    public final RecyclerView.c0 i(RecyclerView recyclerView, int i2, long j) {
        if (e(recyclerView, i2)) {
            return null;
        }
        RecyclerView.v vVar = recyclerView.l;
        try {
            recyclerView.H0();
            RecyclerView.c0 c0VarI = vVar.I(i2, false, j);
            if (c0VarI != null) {
                if (!c0VarI.s() || c0VarI.t()) {
                    vVar.a(c0VarI, false);
                } else {
                    vVar.B(c0VarI.f1185b);
                }
            }
            return c0VarI;
        } finally {
            recyclerView.J0(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.f3089d.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.h.i.b.a("RV Prefetch");
            if (!this.f3089d.isEmpty()) {
                int size = this.f3089d.size();
                long jMax = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = this.f3089d.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f3091f);
                }
            }
        } finally {
            this.f3090e = 0L;
            b.h.i.b.b();
        }
    }
}
