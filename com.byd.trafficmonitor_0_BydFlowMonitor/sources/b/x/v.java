package b.x;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class v implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroupOverlay f3322a;

    public v(ViewGroup viewGroup) {
        this.f3322a = viewGroup.getOverlay();
    }

    @Override // b.x.b0
    public void a(Drawable drawable) {
        this.f3322a.add(drawable);
    }

    @Override // b.x.b0
    public void b(Drawable drawable) {
        this.f3322a.remove(drawable);
    }

    @Override // b.x.w
    public void c(View view) {
        this.f3322a.add(view);
    }

    @Override // b.x.w
    public void d(View view) {
        this.f3322a.remove(view);
    }
}
