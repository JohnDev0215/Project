package a.n;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: loaded from: classes.dex */
class v implements w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroupOverlay f597a;

    v(ViewGroup viewGroup) {
        this.f597a = viewGroup.getOverlay();
    }

    @Override // a.n.w
    public void a(View view) {
        this.f597a.add(view);
    }

    @Override // a.n.b0
    public void b(Drawable drawable) {
        this.f597a.add(drawable);
    }

    @Override // a.n.w
    public void c(View view) {
        this.f597a.remove(view);
    }

    @Override // a.n.b0
    public void d(Drawable drawable) {
        this.f597a.remove(drawable);
    }
}
