package b.m;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class v implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f2529a;

    v(ViewGroup viewGroup) {
        this.f2529a = viewGroup.getOverlay();
    }

    @Override // b.m.b0
    public void a(Drawable drawable) {
        this.f2529a.add(drawable);
    }

    @Override // b.m.b0
    public void b(Drawable drawable) {
        this.f2529a.remove(drawable);
    }

    @Override // b.m.w
    public void c(View view) {
        this.f2529a.add(view);
    }

    @Override // b.m.w
    public void d(View view) {
        this.f2529a.remove(view);
    }
}
