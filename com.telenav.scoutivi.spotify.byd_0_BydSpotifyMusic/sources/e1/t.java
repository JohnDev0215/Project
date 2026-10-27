package e1;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class t implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f6167a;

    t(ViewGroup viewGroup) {
        this.f6167a = viewGroup.getOverlay();
    }

    @Override // e1.u
    public void a(View view) {
        this.f6167a.add(view);
    }

    @Override // e1.x
    public void b(Drawable drawable) {
        this.f6167a.add(drawable);
    }

    @Override // e1.u
    public void c(View view) {
        this.f6167a.remove(view);
    }

    @Override // e1.x
    public void d(Drawable drawable) {
        this.f6167a.remove(drawable);
    }
}
