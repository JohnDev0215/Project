package s3;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: s3.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2390j extends AbstractC2370I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C2392l f22292a;

    public C2390j(C2392l c2392l) {
        this.f22292a = c2392l;
    }

    @Override // s3.AbstractC2370I
    public final void b(RecyclerView recyclerView, int i7, int i10) {
        int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        C2392l c2392l = this.f22292a;
        int iComputeVerticalScrollRange = c2392l.f22312s.computeVerticalScrollRange();
        int i11 = c2392l.f22311r;
        int i12 = iComputeVerticalScrollRange - i11;
        int i13 = c2392l.f22298a;
        c2392l.f22313t = i12 > 0 && i11 >= i13;
        int iComputeHorizontalScrollRange = c2392l.f22312s.computeHorizontalScrollRange();
        int i14 = c2392l.f22310q;
        boolean z6 = iComputeHorizontalScrollRange - i14 > 0 && i14 >= i13;
        c2392l.f22314u = z6;
        boolean z10 = c2392l.f22313t;
        if (!z10 && !z6) {
            if (c2392l.f22315v != 0) {
                c2392l.e(0);
                return;
            }
            return;
        }
        if (z10) {
            float f10 = i11;
            c2392l.l = (int) ((((f10 / 2.0f) + iComputeVerticalScrollOffset) * f10) / iComputeVerticalScrollRange);
            c2392l.f22306k = Math.min(i11, (i11 * i11) / iComputeVerticalScrollRange);
        }
        if (c2392l.f22314u) {
            float f11 = iComputeHorizontalScrollOffset;
            float f12 = i14;
            c2392l.o = (int) ((((f12 / 2.0f) + f11) * f12) / iComputeHorizontalScrollRange);
            c2392l.f22308n = Math.min(i14, (i14 * i14) / iComputeHorizontalScrollRange);
        }
        int i15 = c2392l.f22315v;
        if (i15 == 0 || i15 == 1) {
            c2392l.e(1);
        }
    }
}
