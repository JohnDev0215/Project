package c.b.a.a.q;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f1811a;

    public n(View view) {
        this.f1811a = view.getOverlay();
    }

    @Override // c.b.a.a.q.o
    public void a(Drawable drawable) {
        this.f1811a.add(drawable);
    }

    @Override // c.b.a.a.q.o
    public void b(Drawable drawable) {
        this.f1811a.remove(drawable);
    }
}
