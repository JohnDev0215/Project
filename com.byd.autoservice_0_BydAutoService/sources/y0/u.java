package y0;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroupOverlay f5987a;

    public u(ViewGroup viewGroup) {
        this.f5987a = viewGroup.getOverlay();
    }

    @Override // y0.a0
    public void a(Drawable drawable) {
        this.f5987a.add(drawable);
    }

    @Override // y0.a0
    public void b(Drawable drawable) {
        this.f5987a.remove(drawable);
    }

    @Override // y0.v
    public void c(View view) {
        this.f5987a.add(view);
    }

    @Override // y0.v
    public void d(View view) {
        this.f5987a.remove(view);
    }
}
