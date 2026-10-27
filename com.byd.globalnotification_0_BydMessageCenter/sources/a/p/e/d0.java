package a.p.e;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class d0 extends RecyclerView.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RecyclerView f1151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RecyclerView.r f1152b = new a();

    public class a extends RecyclerView.r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1153a = false;

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0 && this.f1153a) {
                this.f1153a = false;
                d0.this.b();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void b(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.f1153a = true;
        }
    }

    public abstract int[] a(RecyclerView.m mVar, View view);

    /* JADX WARN: Removed duplicated region for block: B:17:0x002c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b() {
        /*
            r10 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r10.f1151a
            if (r0 != 0) goto L5
            return
        L5:
            androidx.recyclerview.widget.RecyclerView$m r0 = r0.getLayoutManager()
            if (r0 != 0) goto Lc
            return
        Lc:
            r1 = r10
            a.p.e.y r1 = (a.p.e.y) r1
            boolean r2 = r0.g()
            if (r2 == 0) goto L1a
            a.p.e.w r2 = r1.f(r0)
            goto L24
        L1a:
            boolean r2 = r0.f()
            if (r2 == 0) goto L29
            a.p.e.w r2 = r1.e(r0)
        L24:
            android.view.View r1 = r1.d(r0, r2)
            goto L2a
        L29:
            r1 = 0
        L2a:
            if (r1 != 0) goto L2d
            return
        L2d:
            int[] r0 = r10.a(r0, r1)
            r1 = 0
            r2 = r0[r1]
            r3 = 1
            if (r2 != 0) goto L3b
            r2 = r0[r3]
            if (r2 == 0) goto L48
        L3b:
            androidx.recyclerview.widget.RecyclerView r4 = r10.f1151a
            r5 = r0[r1]
            r6 = r0[r3]
            r9 = 0
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 0
            r4.m0(r5, r6, r7, r8, r9)
        L48:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.p.e.d0.b():void");
    }
}
