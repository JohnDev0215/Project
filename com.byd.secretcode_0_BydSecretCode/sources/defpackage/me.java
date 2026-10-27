package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class me implements ne {
    public final ViewOverlay a;

    public me(View view) {
        this.a = view.getOverlay();
    }

    @Override // defpackage.ne
    public void a(Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // defpackage.ne
    public void b(Drawable drawable) {
        this.a.remove(drawable);
    }
}
