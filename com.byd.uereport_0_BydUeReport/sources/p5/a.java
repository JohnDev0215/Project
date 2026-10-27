package p5;

import android.graphics.Canvas;
import android.os.Build;

/* JADX INFO: compiled from: CanvasCompat.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static int a(Canvas canvas, float f8, float f9, float f10, float f11, int i8) {
        return Build.VERSION.SDK_INT > 21 ? canvas.saveLayerAlpha(f8, f9, f10, f11, i8) : canvas.saveLayerAlpha(f8, f9, f10, f11, i8, 31);
    }
}
