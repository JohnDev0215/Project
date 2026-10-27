package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Size;

/* JADX INFO: compiled from: PlayerFrameBitmapPainter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class t12 {
    public Size a;
    public Bitmap[][] b;
    public final Rect c = new Rect();
    public final Rect d = new Rect();
    public final Rect e = new Rect();
    public final Runnable f;
    public Runnable g;
    public boolean h;

    public t12(es3 es3Var, g22 g22Var) {
        new Handler();
        this.f = es3Var;
        this.g = g22Var;
    }
}
