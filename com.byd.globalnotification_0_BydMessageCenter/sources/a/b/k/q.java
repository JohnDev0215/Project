package a.b.k;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class q extends a.h.k.u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f50a;

    public q(l lVar) {
        this.f50a = lVar;
    }

    @Override // a.h.k.t
    public void a(View view) {
        this.f50a.o.setAlpha(1.0f);
        this.f50a.r.d(null);
        this.f50a.r = null;
    }

    @Override // a.h.k.u, a.h.k.t
    public void b(View view) {
        this.f50a.o.setVisibility(0);
        this.f50a.o.sendAccessibilityEvent(32);
        if (this.f50a.o.getParent() instanceof View) {
            a.h.k.q.S((View) this.f50a.o.getParent());
        }
    }
}
