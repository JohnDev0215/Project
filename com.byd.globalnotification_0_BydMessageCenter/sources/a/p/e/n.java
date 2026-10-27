package a.p.e;

import android.annotation.SuppressLint;
import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class n implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadLocal<n> f1216e = new ThreadLocal<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Comparator<c> f1217f = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1220c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<RecyclerView> f1218a = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<c> f1221d = new ArrayList<>();

    public static class a implements Comparator<c> {
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0019, code lost:
        
            if (r5.f1229d == null) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0025, code lost:
        
            if (r4 != false) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0039, code lost:
        
            return 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
        
            return -1;
         */
        @Override // java.util.Comparator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int compare(a.p.e.n.c r5, a.p.e.n.c r6) {
            /*
                r4 = this;
                a.p.e.n$c r5 = (a.p.e.n.c) r5
                a.p.e.n$c r6 = (a.p.e.n.c) r6
                androidx.recyclerview.widget.RecyclerView r4 = r5.f1229d
                r0 = 0
                r1 = 1
                if (r4 != 0) goto Lc
                r4 = r1
                goto Ld
            Lc:
                r4 = r0
            Ld:
                androidx.recyclerview.widget.RecyclerView r2 = r6.f1229d
                if (r2 != 0) goto L13
                r2 = r1
                goto L14
            L13:
                r2 = r0
            L14:
                r3 = -1
                if (r4 == r2) goto L1f
                androidx.recyclerview.widget.RecyclerView r4 = r5.f1229d
                if (r4 != 0) goto L1d
            L1b:
                r0 = r1
                goto L39
            L1d:
                r0 = r3
                goto L39
            L1f:
                boolean r4 = r5.f1226a
                boolean r2 = r6.f1226a
                if (r4 == r2) goto L28
                if (r4 == 0) goto L1b
                goto L1d
            L28:
                int r4 = r6.f1227b
                int r1 = r5.f1227b
                int r4 = r4 - r1
                if (r4 == 0) goto L31
            L2f:
                r0 = r4
                goto L39
            L31:
                int r4 = r5.f1228c
                int r5 = r6.f1228c
                int r4 = r4 - r5
                if (r4 == 0) goto L39
                goto L2f
            L39:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: a.p.e.n.a.compare(java.lang.Object, java.lang.Object):int");
        }
    }

    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.m.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1222a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1223b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int[] f1224c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1225d;

        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.f1225d * 2;
            int[] iArr = this.f1224c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1224c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.f1224c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1224c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.f1225d++;
        }

        public void b(RecyclerView recyclerView, boolean z) {
            this.f1225d = 0;
            int[] iArr = this.f1224c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.m mVar = recyclerView.m;
            if (recyclerView.l == null || mVar == null || !mVar.l) {
                return;
            }
            if (z) {
                if (!recyclerView.f1618d.g()) {
                    mVar.k(recyclerView.l.a(), this);
                }
            } else if (!recyclerView.N()) {
                mVar.j(this.f1222a, this.f1223b, recyclerView.e0, this);
            }
            int i = this.f1225d;
            if (i > mVar.m) {
                mVar.m = i;
                mVar.n = z;
                recyclerView.f1616b.l();
            }
        }

        public boolean c(int i) {
            if (this.f1224c != null) {
                int i2 = this.f1225d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.f1224c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1226a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1227b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1228c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView f1229d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1230e;
    }

    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.f1219b == 0) {
            this.f1219b = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        b bVar = recyclerView.d0;
        bVar.f1222a = i;
        bVar.f1223b = i2;
    }

    public void b(long j) {
        RecyclerView recyclerView;
        c cVar;
        int size = this.f1218a.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView2 = this.f1218a.get(i2);
            if (recyclerView2.getWindowVisibility() == 0) {
                recyclerView2.d0.b(recyclerView2, false);
                i += recyclerView2.d0.f1225d;
            }
        }
        this.f1221d.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView3 = this.f1218a.get(i4);
            if (recyclerView3.getWindowVisibility() == 0) {
                b bVar = recyclerView3.d0;
                int iAbs = Math.abs(bVar.f1223b) + Math.abs(bVar.f1222a);
                for (int i5 = 0; i5 < bVar.f1225d * 2; i5 += 2) {
                    if (i3 >= this.f1221d.size()) {
                        cVar = new c();
                        this.f1221d.add(cVar);
                    } else {
                        cVar = this.f1221d.get(i3);
                    }
                    int i6 = bVar.f1224c[i5 + 1];
                    cVar.f1226a = i6 <= iAbs;
                    cVar.f1227b = iAbs;
                    cVar.f1228c = i6;
                    cVar.f1229d = recyclerView3;
                    cVar.f1230e = bVar.f1224c[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.f1221d, f1217f);
        for (int i7 = 0; i7 < this.f1221d.size(); i7++) {
            c cVar2 = this.f1221d.get(i7);
            if (cVar2.f1229d == null) {
                return;
            }
            RecyclerView.b0 b0VarC = c(cVar2.f1229d, cVar2.f1230e, cVar2.f1226a ? Long.MAX_VALUE : j);
            if (b0VarC != null && b0VarC.f1630b != null && b0VarC.j() && !b0VarC.k() && (recyclerView = b0VarC.f1630b.get()) != null) {
                if (recyclerView.D && recyclerView.f1619e.h() != 0) {
                    recyclerView.e0();
                }
                b bVar2 = recyclerView.d0;
                bVar2.b(recyclerView, true);
                if (bVar2.f1225d != 0) {
                    try {
                        Trace.beginSection("RV Nested Prefetch");
                        RecyclerView.y yVar = recyclerView.e0;
                        RecyclerView.e eVar = recyclerView.l;
                        yVar.f1692d = 1;
                        yVar.f1693e = eVar.a();
                        yVar.g = false;
                        yVar.h = false;
                        yVar.i = false;
                        for (int i8 = 0; i8 < bVar2.f1225d * 2; i8 += 2) {
                            c(recyclerView, bVar2.f1224c[i8], j);
                        }
                    } finally {
                        Trace.endSection();
                    }
                } else {
                    continue;
                }
            }
            cVar2.f1226a = false;
            cVar2.f1227b = 0;
            cVar2.f1228c = 0;
            cVar2.f1229d = null;
            cVar2.f1230e = 0;
        }
    }

    public final RecyclerView.b0 c(RecyclerView recyclerView, int i, long j) {
        boolean z;
        int iH = recyclerView.f1619e.h();
        int i2 = 0;
        while (true) {
            if (i2 >= iH) {
                z = false;
                break;
            }
            RecyclerView.b0 b0VarK = RecyclerView.K(recyclerView.f1619e.g(i2));
            if (b0VarK.f1631c == i && !b0VarK.k()) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            return null;
        }
        RecyclerView.t tVar = recyclerView.f1616b;
        try {
            recyclerView.V();
            RecyclerView.b0 b0VarJ = tVar.j(i, false, j);
            if (b0VarJ != null) {
                if (!b0VarJ.j() || b0VarJ.k()) {
                    tVar.a(b0VarJ, false);
                } else {
                    tVar.g(b0VarJ.f1629a);
                }
            }
            return b0VarJ;
        } finally {
            recyclerView.W(false);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Trace.beginSection("RV Prefetch");
            if (this.f1218a.isEmpty()) {
                return;
            }
            int size = this.f1218a.size();
            long jMax = 0;
            for (int i = 0; i < size; i++) {
                RecyclerView recyclerView = this.f1218a.get(i);
                if (recyclerView.getWindowVisibility() == 0) {
                    jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                }
            }
            if (jMax == 0) {
                return;
            }
            b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.f1220c);
        } finally {
            this.f1219b = 0L;
            Trace.endSection();
        }
    }
}
