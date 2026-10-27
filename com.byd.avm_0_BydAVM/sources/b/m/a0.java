package b.m;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class a0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f1821a;

    a0(View view) {
        this.f1821a = view.getOverlay();
    }

    @Override // b.m.b0
    public void a(Drawable drawable) {
        this.f1821a.add(drawable);
    }

    @Override // b.m.b0
    public void b(Drawable drawable) {
        this.f1821a.remove(drawable);
    }
}
