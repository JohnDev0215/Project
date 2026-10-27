package b.x;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f3202a;

    public a0(View view) {
        this.f3202a = view.getOverlay();
    }

    @Override // b.x.b0
    public void a(Drawable drawable) {
        this.f3202a.add(drawable);
    }

    @Override // b.x.b0
    public void b(Drawable drawable) {
        this.f3202a.remove(drawable);
    }
}
