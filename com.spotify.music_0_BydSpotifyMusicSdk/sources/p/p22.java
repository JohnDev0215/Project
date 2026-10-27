package p;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class p22 extends o2 {
    public final RecyclerView d;
    public final o22 e;

    public p22(RecyclerView recyclerView) {
        this.d = recyclerView;
        o22 o22Var = this.e;
        if (o22Var != null) {
            this.e = o22Var;
        } else {
            this.e = new o22(this);
        }
    }

    @Override // p.o2
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || this.d.K()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().O(accessibilityEvent);
        }
    }

    @Override // p.o2
    public final void d(View view, c3 c3Var) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        RecyclerView recyclerView = this.d;
        if (recyclerView.K() || recyclerView.getLayoutManager() == null) {
            return;
        }
        y12 layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.b;
        f22 f22Var = recyclerView2.e;
        if (recyclerView2.canScrollVertically(-1) || layoutManager.b.canScrollHorizontally(-1)) {
            c3Var.a(8192);
            accessibilityNodeInfo.setScrollable(true);
        }
        if (layoutManager.b.canScrollVertically(1) || layoutManager.b.canScrollHorizontally(1)) {
            c3Var.a(4096);
            accessibilityNodeInfo.setScrollable(true);
        }
        k22 k22Var = recyclerView2.k0;
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(layoutManager.F(f22Var, k22Var), layoutManager.x(f22Var, k22Var), false, 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0056 A[PHI: r0
  0x0056: PHI (r0v8 int) = (r0v4 int), (r0v12 int) binds: [B:27:0x0073, B:19:0x0046] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // p.o2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean g(android.view.View r4, int r5, android.os.Bundle r6) {
        /*
            r3 = this;
            boolean r4 = super.g(r4, r5, r6)
            r6 = 1
            if (r4 == 0) goto L8
            return r6
        L8:
            androidx.recyclerview.widget.RecyclerView r4 = r3.d
            boolean r0 = r4.K()
            r1 = 0
            if (r0 != 0) goto L8d
            p.y12 r0 = r4.getLayoutManager()
            if (r0 == 0) goto L8d
            p.y12 r4 = r4.getLayoutManager()
            androidx.recyclerview.widget.RecyclerView r0 = r4.b
            p.f22 r2 = r0.e
            r2 = 4096(0x1000, float:5.74E-42)
            if (r5 == r2) goto L58
            r2 = 8192(0x2000, float:1.148E-41)
            if (r5 == r2) goto L2a
            r5 = r1
            r0 = r5
            goto L81
        L2a:
            r5 = -1
            boolean r0 = r0.canScrollVertically(r5)
            if (r0 == 0) goto L3f
            int r0 = r4.o
            int r2 = r4.C()
            int r0 = r0 - r2
            int r2 = r4.z()
            int r0 = r0 - r2
            int r0 = -r0
            goto L40
        L3f:
            r0 = r1
        L40:
            androidx.recyclerview.widget.RecyclerView r2 = r4.b
            boolean r5 = r2.canScrollHorizontally(r5)
            if (r5 == 0) goto L56
            int r5 = r4.n
            int r2 = r4.A()
            int r5 = r5 - r2
            int r2 = r4.B()
            int r5 = r5 - r2
            int r5 = -r5
            goto L81
        L56:
            r5 = r1
            goto L81
        L58:
            boolean r5 = r0.canScrollVertically(r6)
            if (r5 == 0) goto L6c
            int r5 = r4.o
            int r0 = r4.C()
            int r5 = r5 - r0
            int r0 = r4.z()
            int r5 = r5 - r0
            r0 = r5
            goto L6d
        L6c:
            r0 = r1
        L6d:
            androidx.recyclerview.widget.RecyclerView r5 = r4.b
            boolean r5 = r5.canScrollHorizontally(r6)
            if (r5 == 0) goto L56
            int r5 = r4.n
            int r2 = r4.A()
            int r5 = r5 - r2
            int r2 = r4.B()
            int r5 = r5 - r2
        L81:
            if (r0 != 0) goto L87
            if (r5 != 0) goto L87
            r6 = r1
            goto L8c
        L87:
            androidx.recyclerview.widget.RecyclerView r4 = r4.b
            r4.b0(r5, r0, r6)
        L8c:
            return r6
        L8d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.p22.g(android.view.View, int, android.os.Bundle):boolean");
    }
}
