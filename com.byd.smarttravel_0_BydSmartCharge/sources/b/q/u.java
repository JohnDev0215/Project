package b.q;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroupOverlay f1519a;

    public u(ViewGroup viewGroup) {
        this.f1519a = viewGroup.getOverlay();
    }

    @Override // b.q.a0
    public void a(Drawable drawable) {
        this.f1519a.add(drawable);
    }

    @Override // b.q.a0
    public void b(Drawable drawable) {
        this.f1519a.remove(drawable);
    }

    @Override // b.q.v
    public void c(View view) {
        this.f1519a.add(view);
    }

    @Override // b.q.v
    public void d(View view) {
        this.f1519a.remove(view);
    }
}
