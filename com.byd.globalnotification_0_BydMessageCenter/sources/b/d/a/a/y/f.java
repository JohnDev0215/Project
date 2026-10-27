package b.d.a.a.y;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public class f implements ViewTreeObserver.OnPreDrawListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f2159a;

    public f(e eVar) {
        this.f2159a = eVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        e eVar = this.f2159a;
        float rotation = eVar.t.getRotation();
        if (eVar.n == rotation) {
            return true;
        }
        eVar.n = rotation;
        eVar.s();
        return true;
    }
}
