package z5;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes2.dex */
public class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f9609a;

    public n(View view) {
        this.f9609a = view.getOverlay();
    }

    @Override // z5.o
    public void a(Drawable drawable) {
        this.f9609a.add(drawable);
    }

    @Override // z5.o
    public void b(Drawable drawable) {
        this.f9609a.remove(drawable);
    }
}
