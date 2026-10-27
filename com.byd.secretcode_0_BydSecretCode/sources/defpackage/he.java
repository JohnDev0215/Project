package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class he implements ie {
    public final ViewGroupOverlay a;

    public he(ViewGroup viewGroup) {
        this.a = viewGroup.getOverlay();
    }

    @Override // defpackage.ne
    public void a(Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // defpackage.ne
    public void b(Drawable drawable) {
        this.a.remove(drawable);
    }

    @Override // defpackage.ie
    public void c(View view) {
        this.a.add(view);
    }

    @Override // defpackage.ie
    public void d(View view) {
        this.a.remove(view);
    }
}
