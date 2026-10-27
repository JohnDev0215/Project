package k1;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroupOverlay f5829a;

    public u(ViewGroup viewGroup) {
        this.f5829a = viewGroup.getOverlay();
    }

    @Override // k1.a0
    public void a(Drawable drawable) {
        this.f5829a.add(drawable);
    }

    @Override // k1.a0
    public void b(Drawable drawable) {
        this.f5829a.remove(drawable);
    }

    @Override // k1.v
    public void c(View view) {
        this.f5829a.add(view);
    }

    @Override // k1.v
    public void d(View view) {
        this.f5829a.remove(view);
    }
}
