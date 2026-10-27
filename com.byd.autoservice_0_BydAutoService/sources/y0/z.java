package y0;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class z implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f6001a;

    public z(View view) {
        this.f6001a = view.getOverlay();
    }

    @Override // y0.a0
    public void a(Drawable drawable) {
        this.f6001a.add(drawable);
    }

    @Override // y0.a0
    public void b(Drawable drawable) {
        this.f6001a.remove(drawable);
    }
}
