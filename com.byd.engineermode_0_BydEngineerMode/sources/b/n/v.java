package b.n;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class v implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f2021a;

    v(ViewGroup viewGroup) {
        this.f2021a = viewGroup.getOverlay();
    }

    @Override // b.n.b0
    public void a(Drawable drawable) {
        this.f2021a.add(drawable);
    }

    @Override // b.n.b0
    public void b(Drawable drawable) {
        this.f2021a.remove(drawable);
    }

    @Override // b.n.w
    public void c(View view) {
        this.f2021a.add(view);
    }

    @Override // b.n.w
    public void d(View view) {
        this.f2021a.remove(view);
    }
}
