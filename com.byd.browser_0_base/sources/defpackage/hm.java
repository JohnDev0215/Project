package defpackage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/* JADX INFO: compiled from: CaptureUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hm {
    public static boolean a(Canvas canvas, View view, Rect rect, float f, boolean z, fm fmVar) {
        if (!(z || view.isAttachedToWindow())) {
            return false;
        }
        fmVar.d(canvas, rect.isEmpty() ? null : rect);
        if (!rect.isEmpty()) {
            canvas.clipRect(rect);
        }
        canvas.save();
        canvas.scale(f, f);
        view.draw(canvas);
        canvas.restore();
        fmVar.e();
        return true;
    }
}
