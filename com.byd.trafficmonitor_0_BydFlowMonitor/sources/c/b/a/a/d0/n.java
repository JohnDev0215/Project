package c.b.a.a.d0;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f3801a;

    public n(View view) {
        this.f3801a = view.getOverlay();
    }

    @Override // c.b.a.a.d0.o
    public void a(Drawable drawable) {
        this.f3801a.add(drawable);
    }

    @Override // c.b.a.a.d0.o
    public void b(Drawable drawable) {
        this.f3801a.remove(drawable);
    }
}
