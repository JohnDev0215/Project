package c.n;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class v implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f2500a;

    v(ViewGroup viewGroup) {
        this.f2500a = viewGroup.getOverlay();
    }

    @Override // c.n.b0
    public void a(Drawable drawable) {
        this.f2500a.add(drawable);
    }

    @Override // c.n.b0
    public void b(Drawable drawable) {
        this.f2500a.remove(drawable);
    }

    @Override // c.n.w
    public void c(View view) {
        this.f2500a.add(view);
    }

    @Override // c.n.w
    public void d(View view) {
        this.f2500a.remove(view);
    }
}
