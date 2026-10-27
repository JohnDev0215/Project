package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

/* JADX INFO: compiled from: LinearDrawingDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class i71 extends xd0<LinearProgressIndicatorSpec> {
    public float c;
    public float d;
    public float e;

    public i71(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
        this.c = 300.0f;
    }

    @Override // defpackage.xd0
    public final void a(Canvas canvas, float f) {
        Rect clipBounds = canvas.getClipBounds();
        this.c = clipBounds.width();
        Object obj = this.a;
        float f2 = ((LinearProgressIndicatorSpec) obj).a;
        canvas.translate((clipBounds.width() / 2.0f) + clipBounds.left, Math.max(0.0f, (clipBounds.height() - ((LinearProgressIndicatorSpec) obj).a) / 2.0f) + (clipBounds.height() / 2.0f) + clipBounds.top);
        if (((LinearProgressIndicatorSpec) obj).i) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.b.e() && ((LinearProgressIndicatorSpec) obj).e == 1) || (this.b.d() && ((LinearProgressIndicatorSpec) obj).f == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.b.e() || this.b.d()) {
            canvas.translate(0.0f, ((f - 1.0f) * ((LinearProgressIndicatorSpec) obj).a) / 2.0f);
        }
        float f3 = this.c;
        canvas.clipRect((-f3) / 2.0f, (-f2) / 2.0f, f3 / 2.0f, f2 / 2.0f);
        this.d = ((LinearProgressIndicatorSpec) obj).a * f;
        this.e = ((LinearProgressIndicatorSpec) obj).b * f;
    }

    @Override // defpackage.xd0
    public final void b(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f == f2) {
            return;
        }
        float f3 = this.c;
        float f4 = (-f3) / 2.0f;
        float f5 = this.e * 2.0f;
        float f6 = f3 - f5;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(i);
        float f7 = this.d;
        RectF rectF = new RectF((f * f6) + f4, (-f7) / 2.0f, (f6 * f2) + f4 + f5, f7 / 2.0f);
        float f8 = this.e;
        canvas.drawRoundRect(rectF, f8, f8, paint);
    }

    @Override // defpackage.xd0
    public final void c(Canvas canvas, Paint paint) {
        int iE = oj2.e(((LinearProgressIndicatorSpec) this.a).d, this.b.n);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(iE);
        float f = this.c;
        float f2 = this.d;
        RectF rectF = new RectF((-f) / 2.0f, (-f2) / 2.0f, f / 2.0f, f2 / 2.0f);
        float f3 = this.e;
        canvas.drawRoundRect(rectF, f3, f3, paint);
    }

    @Override // defpackage.xd0
    public final int d() {
        return ((LinearProgressIndicatorSpec) this.a).a;
    }

    @Override // defpackage.xd0
    public final int e() {
        return -1;
    }
}
