package b.q;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class z implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f1529a;

    public z(View view) {
        this.f1529a = view.getOverlay();
    }

    @Override // b.q.a0
    public void a(Drawable drawable) {
        this.f1529a.add(drawable);
    }

    @Override // b.q.a0
    public void b(Drawable drawable) {
        this.f1529a.remove(drawable);
    }
}
