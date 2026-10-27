package b.o;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class v implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f2926a;

    v(ViewGroup viewGroup) {
        this.f2926a = viewGroup.getOverlay();
    }

    @Override // b.o.b0
    public void a(Drawable drawable) {
        this.f2926a.add(drawable);
    }

    @Override // b.o.b0
    public void b(Drawable drawable) {
        this.f2926a.remove(drawable);
    }

    @Override // b.o.w
    public void c(View view) {
        this.f2926a.add(view);
    }

    @Override // b.o.w
    public void d(View view) {
        this.f2926a.remove(view);
    }
}
