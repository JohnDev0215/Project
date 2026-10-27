package b.o;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class a0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f2802a;

    a0(View view) {
        this.f2802a = view.getOverlay();
    }

    @Override // b.o.b0
    public void a(Drawable drawable) {
        this.f2802a.add(drawable);
    }

    @Override // b.o.b0
    public void b(Drawable drawable) {
        this.f2802a.remove(drawable);
    }
}
