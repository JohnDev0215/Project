package P4;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.bumptech.glide.c {
    public final /* synthetic */ BottomSheetBehavior d;

    public c(BottomSheetBehavior bottomSheetBehavior) {
        this.d = bottomSheetBehavior;
    }

    @Override // com.bumptech.glide.c
    public final boolean E0(View view, int i7) {
        BottomSheetBehavior bottomSheetBehavior = this.d;
        int i10 = bottomSheetBehavior.f12755F;
        if (i10 == 1 || bottomSheetBehavior.f12769T) {
            return false;
        }
        if (i10 == 3 && bottomSheetBehavior.f12767R == i7) {
            WeakReference weakReference = bottomSheetBehavior.f12764O;
            View view2 = weakReference != null ? (View) weakReference.get() : null;
            if (view2 != null && view2.canScrollVertically(-1)) {
                return false;
            }
        }
        WeakReference weakReference2 = bottomSheetBehavior.f12763N;
        return weakReference2 != null && weakReference2.get() == view;
    }

    @Override // com.bumptech.glide.c
    public final int M(View view, int i7) {
        return view.getLeft();
    }

    @Override // com.bumptech.glide.c
    public final int N(View view, int i7) {
        BottomSheetBehavior bottomSheetBehavior = this.d;
        int iW = bottomSheetBehavior.w();
        int i10 = bottomSheetBehavior.f12752C ? bottomSheetBehavior.f12762M : bottomSheetBehavior.f12751A;
        return i7 < iW ? iW : i7 > i10 ? i10 : i7;
    }

    @Override // com.bumptech.glide.c
    public final int l0() {
        BottomSheetBehavior bottomSheetBehavior = this.d;
        return bottomSheetBehavior.f12752C ? bottomSheetBehavior.f12762M : bottomSheetBehavior.f12751A;
    }

    @Override // com.bumptech.glide.c
    public final void t0(int i7) {
        if (i7 == 1) {
            BottomSheetBehavior bottomSheetBehavior = this.d;
            if (bottomSheetBehavior.f12754E) {
                bottomSheetBehavior.z(1);
            }
        }
    }

    @Override // com.bumptech.glide.c
    public final void u0(View view, int i7, int i10) {
        this.d.u(i10);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // com.bumptech.glide.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v0(android.view.View r5, float r6, float r7) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P4.c.v0(android.view.View, float, float):void");
    }
}
