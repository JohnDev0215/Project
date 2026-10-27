package b.d.a.a.n;

import a.h.k.q;
import a.h.k.x.d;
import android.view.View;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* JADX INFO: loaded from: classes.dex */
public class a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SwipeDismissBehavior f2018a;

    public a(SwipeDismissBehavior swipeDismissBehavior) {
        this.f2018a = swipeDismissBehavior;
    }

    @Override // a.h.k.x.d
    public boolean a(View view, d.a aVar) {
        boolean z = false;
        if (!this.f2018a.B(view)) {
            return false;
        }
        boolean z2 = q.p(view) == 1;
        if ((this.f2018a.f2370e == 0 && z2) || (this.f2018a.f2370e == 1 && !z2)) {
            z = true;
        }
        int width = view.getWidth();
        if (z) {
            width = -width;
        }
        view.offsetLeftAndRight(width);
        view.setAlpha(0.0f);
        SwipeDismissBehavior.b bVar = this.f2018a.f2367b;
        if (bVar != null) {
            bVar.a(view);
        }
        return true;
    }
}
