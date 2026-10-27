package defpackage;

import android.view.View;
import androidx.core.view.accessibility.a;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: compiled from: BottomSheetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class hi implements a {
    public final /* synthetic */ int e;
    public final /* synthetic */ BottomSheetBehavior f;

    public hi(BottomSheetBehavior bottomSheetBehavior, int i) {
        this.f = bottomSheetBehavior;
        this.e = i;
    }

    @Override // androidx.core.view.accessibility.a
    public final boolean f(View view) {
        this.f.C(this.e);
        return true;
    }
}
