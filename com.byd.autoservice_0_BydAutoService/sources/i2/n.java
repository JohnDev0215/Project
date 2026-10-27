package i2;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f3378a;

    public n(View view) {
        this.f3378a = view.getOverlay();
    }

    @Override // i2.o
    public void a(Drawable drawable) {
        this.f3378a.add(drawable);
    }

    @Override // i2.o
    public void b(Drawable drawable) {
        this.f3378a.remove(drawable);
    }
}
