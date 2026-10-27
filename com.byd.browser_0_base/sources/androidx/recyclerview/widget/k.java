package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GapWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements Runnable {
    public static final ThreadLocal<k> i = new ThreadLocal<>();
    public static final a j = new a();
    public long f;
    public long g;
    public final ArrayList<RecyclerView> e = new ArrayList<>();
    public final ArrayList<c> h = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public class a implements Comparator<c> {
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        
            if (r5 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:
        
            if (r5 != false) goto L14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0037, code lost:
        
            return 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
        
            return -1;
         */
        @Override // java.util.Comparator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int compare(androidx.recyclerview.widget.k.c r6, androidx.recyclerview.widget.k.c r7) {
            /*
                r5 = this;
                androidx.recyclerview.widget.k$c r6 = (androidx.recyclerview.widget.k.c) r6
                androidx.recyclerview.widget.k$c r7 = (androidx.recyclerview.widget.k.c) r7
                androidx.recyclerview.widget.RecyclerView r5 = r6.d
                r0 = 0
                r1 = 1
                if (r5 != 0) goto Lc
                r2 = r1
                goto Ld
            Lc:
                r2 = r0
            Ld:
                androidx.recyclerview.widget.RecyclerView r3 = r7.d
                if (r3 != 0) goto L13
                r3 = r1
                goto L14
            L13:
                r3 = r0
            L14:
                r4 = -1
                if (r2 == r3) goto L1d
                if (r5 != 0) goto L1b
            L19:
                r0 = r1
                goto L37
            L1b:
                r0 = r4
                goto L37
            L1d:
                boolean r5 = r6.a
                boolean r2 = r7.a
                if (r5 == r2) goto L26
                if (r5 == 0) goto L19
                goto L1b
            L26:
                int r5 = r7.b
                int r1 = r6.b
                int r5 = r5 - r1
                if (r5 == 0) goto L2f
            L2d:
                r0 = r5
                goto L37
            L2f:
                int r5 = r6.c
                int r6 = r7.c
                int r5 = r5 - r6
                if (r5 == 0) goto L37
                goto L2d
            L37:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.k.a.compare(java.lang.Object, java.lang.Object):int");
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    @SuppressLint({"VisibleForTests"})
    public static class b implements RecyclerView.m.c {
        public int a;
        public int b;
        public int[] c;
        public int d;

        public final void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i3 = this.d * 2;
            int[] iArr = this.c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[i3 * 2];
                this.c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.d++;
        }

        public final void b(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.m mVar = recyclerView.r;
            if (recyclerView.q == null || mVar == null || !mVar.i) {
                return;
            }
            if (!z) {
                if (!(!recyclerView.z || recyclerView.I || recyclerView.i.g())) {
                    mVar.l(this.a, this.b, recyclerView.m0, this);
                }
            } else if (!recyclerView.i.g()) {
                mVar.m(recyclerView.q.k(), this);
            }
            int i = this.d;
            if (i > mVar.j) {
                mVar.j = i;
                mVar.k = z;
                recyclerView.g.m();
            }
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {
        public boolean a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;
    }

    public static RecyclerView.a0 c(RecyclerView recyclerView, int i2, long j2) {
        boolean z;
        int iH = recyclerView.j.h();
        int i3 = 0;
        while (true) {
            if (i3 >= iH) {
                z = false;
                break;
            }
            RecyclerView.a0 a0VarK = RecyclerView.K(recyclerView.j.g(i3));
            if (a0VarK.g == i2 && !a0VarK.k()) {
                z = true;
                break;
            }
            i3++;
        }
        if (z) {
            return null;
        }
        RecyclerView.s sVar = recyclerView.g;
        try {
            recyclerView.S();
            RecyclerView.a0 a0VarK2 = sVar.k(j2, i2);
            if (a0VarK2 != null) {
                if (!a0VarK2.j() || a0VarK2.k()) {
                    sVar.a(a0VarK2, false);
                } else {
                    sVar.h(a0VarK2.e);
                }
            }
            return a0VarK2;
        } finally {
            recyclerView.T(false);
        }
    }

    public final void a(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow()) {
            if (RecyclerView.F0 && !this.e.contains(recyclerView)) {
                throw new IllegalStateException("attempting to post unregistered view!");
            }
            if (this.f == 0) {
                this.f = recyclerView.getNanoTime();
                recyclerView.post(this);
            }
        }
        b bVar = recyclerView.l0;
        bVar.a = i2;
        bVar.b = i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(long r16) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.k.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Trace.beginSection("RV Prefetch");
            ArrayList<RecyclerView> arrayList = this.e;
            if (arrayList.isEmpty()) {
                return;
            }
            int size = arrayList.size();
            long jMax = 0;
            for (int i2 = 0; i2 < size; i2++) {
                RecyclerView recyclerView = arrayList.get(i2);
                if (recyclerView.getWindowVisibility() == 0) {
                    jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                }
            }
            if (jMax == 0) {
                return;
            }
            b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.g);
        } finally {
            this.f = 0L;
            Trace.endSection();
        }
    }
}
