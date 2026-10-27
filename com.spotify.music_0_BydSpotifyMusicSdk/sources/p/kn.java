package p;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class kn extends he1 {
    public final /* synthetic */ BottomSheetBehavior k;

    public kn(BottomSheetBehavior bottomSheetBehavior) {
        this.k = bottomSheetBehavior;
    }

    @Override // p.he1
    public final void G(int i) {
        if (i == 1) {
            BottomSheetBehavior bottomSheetBehavior = this.k;
            if (bottomSheetBehavior.F) {
                bottomSheetBehavior.A(1);
            }
        }
    }

    @Override // p.he1
    public final void H(View view, int i, int i2) {
        this.k.u(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    @Override // p.he1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I(android.view.View r6, float r7, float r8) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.kn.I(android.view.View, float, float):void");
    }

    @Override // p.he1
    public final boolean W(View view, int i) {
        BottomSheetBehavior bottomSheetBehavior = this.k;
        int i2 = bottomSheetBehavior.G;
        if (i2 == 1 || bottomSheetBehavior.U) {
            return false;
        }
        if (i2 == 3 && bottomSheetBehavior.S == i) {
            WeakReference weakReference = bottomSheetBehavior.P;
            View view2 = weakReference != null ? (View) weakReference.get() : null;
            if (view2 != null && view2.canScrollVertically(-1)) {
                return false;
            }
        }
        System.currentTimeMillis();
        WeakReference weakReference2 = bottomSheetBehavior.O;
        return weakReference2 != null && weakReference2.get() == view;
    }

    @Override // p.he1
    public final int j(View view, int i) {
        return view.getLeft();
    }

    @Override // p.he1
    public final int k(View view, int i) {
        BottomSheetBehavior bottomSheetBehavior = this.k;
        int iX = bottomSheetBehavior.x();
        int i2 = bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B;
        return i < iX ? iX : i > i2 ? i2 : i;
    }

    @Override // p.he1
    public final int x() {
        BottomSheetBehavior bottomSheetBehavior = this.k;
        return bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B;
    }
}
