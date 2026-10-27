package b.m;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class a0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f2423a;

    a0(View view) {
        this.f2423a = view.getOverlay();
    }

    @Override // b.m.b0
    public void a(Drawable drawable) {
        this.f2423a.add(drawable);
    }

    @Override // b.m.b0
    public void b(Drawable drawable) {
        this.f2423a.remove(drawable);
    }
}
