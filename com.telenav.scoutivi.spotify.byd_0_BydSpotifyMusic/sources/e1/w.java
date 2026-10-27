package e1;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
class w implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f6169a;

    w(View view) {
        this.f6169a = view.getOverlay();
    }

    @Override // e1.x
    public void b(Drawable drawable) {
        this.f6169a.add(drawable);
    }

    @Override // e1.x
    public void d(Drawable drawable) {
        this.f6169a.remove(drawable);
    }
}
