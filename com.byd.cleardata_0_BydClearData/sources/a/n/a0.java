package a.n;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: loaded from: classes.dex */
class a0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewOverlay f501a;

    a0(View view) {
        this.f501a = view.getOverlay();
    }

    @Override // a.n.b0
    public void b(Drawable drawable) {
        this.f501a.add(drawable);
    }

    @Override // a.n.b0
    public void d(Drawable drawable) {
        this.f501a.remove(drawable);
    }
}
