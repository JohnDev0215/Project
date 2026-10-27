package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import defpackage.p72;

/* JADX INFO: compiled from: PulseDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o72 implements p72.e {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;

    public o72(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // p72.e
    public final void a(p72 p72Var, float f) {
        p72Var.setAlpha((int) ((f * 63.0f) + 12.0f));
    }

    @Override // p72.e
    public final void b(p72 p72Var, Paint paint, Canvas canvas, float f) {
        Rect bounds = p72Var.getBounds();
        int i = this.a;
        if (i != 0) {
            bounds = new Rect(bounds.left - i, bounds.top - i, bounds.right + i, bounds.bottom + i);
        }
        RectF rectF = new RectF(bounds);
        float f2 = this.b;
        canvas.drawRoundRect(rectF, f2, f2, paint);
    }
}
