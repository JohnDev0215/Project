package k1;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class z implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f5843a;

    public z(View view) {
        this.f5843a = view.getOverlay();
    }

    @Override // k1.a0
    public void a(Drawable drawable) {
        this.f5843a.add(drawable);
    }

    @Override // k1.a0
    public void b(Drawable drawable) {
        this.f5843a.remove(drawable);
    }
}
