package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class mj implements nj {
    public final ViewOverlay a;

    public mj(View view) {
        this.a = view.getOverlay();
    }

    @Override // defpackage.nj
    public void a(Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // defpackage.nj
    public void b(Drawable drawable) {
        this.a.remove(drawable);
    }
}
