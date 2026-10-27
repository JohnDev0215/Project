package P4;

import F1.H;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {
    public final View l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5339m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5340n;
    public final /* synthetic */ BottomSheetBehavior o;

    public e(BottomSheetBehavior bottomSheetBehavior, View view, int i7) {
        this.o = bottomSheetBehavior;
        this.l = view;
        this.f5340n = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BottomSheetBehavior bottomSheetBehavior = this.o;
        L1.d dVar = bottomSheetBehavior.f12756G;
        if (dVar == null || !dVar.f()) {
            bottomSheetBehavior.z(this.f5340n);
        } else {
            int[] iArr = H.f2303a;
            this.l.postOnAnimation(this);
        }
        this.f5339m = false;
    }
}
