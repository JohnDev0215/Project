package b.d.a.a.p;

import a.h.k.x.d;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public class c implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ BottomSheetBehavior f2043b;

    public c(BottomSheetBehavior bottomSheetBehavior, int i) {
        this.f2043b = bottomSheetBehavior;
        this.f2042a = i;
    }

    @Override // a.h.k.x.d
    public boolean a(View view, d.a aVar) {
        this.f2043b.J(this.f2042a);
        return true;
    }
}
