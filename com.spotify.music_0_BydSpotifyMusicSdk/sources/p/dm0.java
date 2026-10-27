package p;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class dm0 extends c22 {
    public final /* synthetic */ gm0 a;

    public dm0(gm0 gm0Var) {
        this.a = gm0Var;
    }

    @Override // p.c22
    public final void b(RecyclerView recyclerView, int i, int i2) {
        int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        gm0 gm0Var = this.a;
        int iComputeVerticalScrollRange = gm0Var.s.computeVerticalScrollRange();
        int i3 = gm0Var.r;
        int i4 = iComputeVerticalScrollRange - i3;
        int i5 = gm0Var.a;
        gm0Var.t = i4 > 0 && i3 >= i5;
        int iComputeHorizontalScrollRange = gm0Var.s.computeHorizontalScrollRange();
        int i6 = gm0Var.q;
        boolean z = iComputeHorizontalScrollRange - i6 > 0 && i6 >= i5;
        gm0Var.u = z;
        boolean z2 = gm0Var.t;
        if (!z2 && !z) {
            if (gm0Var.v != 0) {
                gm0Var.f(0);
                return;
            }
            return;
        }
        if (z2) {
            float f = i3;
            gm0Var.l = (int) ((((f / 2.0f) + iComputeVerticalScrollOffset) * f) / iComputeVerticalScrollRange);
            gm0Var.k = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
        }
        if (gm0Var.u) {
            float f2 = iComputeHorizontalScrollOffset;
            float f3 = i6;
            gm0Var.o = (int) ((((f3 / 2.0f) + f2) * f3) / iComputeHorizontalScrollRange);
            gm0Var.n = Math.min(i6, (i6 * i6) / iComputeHorizontalScrollRange);
        }
        int i7 = gm0Var.v;
        if (i7 == 0 || i7 == 1) {
            gm0Var.f(1);
        }
    }
}
