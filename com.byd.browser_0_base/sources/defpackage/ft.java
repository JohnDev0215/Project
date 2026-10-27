package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;

/* JADX INFO: compiled from: CircularDrawingDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class ft extends xd0<CircularProgressIndicatorSpec> {
    public int c;
    public float d;
    public float e;
    public float f;

    public ft(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
        this.c = 1;
    }

    @Override // defpackage.xd0
    public final void a(Canvas canvas, float f) {
        Object obj = this.a;
        float f2 = (((CircularProgressIndicatorSpec) obj).g / 2.0f) + ((CircularProgressIndicatorSpec) obj).h;
        canvas.translate(f2, f2);
        canvas.rotate(-90.0f);
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        this.c = ((CircularProgressIndicatorSpec) obj).i == 0 ? 1 : -1;
        this.d = ((CircularProgressIndicatorSpec) obj).a * f;
        this.e = ((CircularProgressIndicatorSpec) obj).b * f;
        this.f = (((CircularProgressIndicatorSpec) obj).g - ((CircularProgressIndicatorSpec) obj).a) / 2.0f;
        if ((this.b.e() && ((CircularProgressIndicatorSpec) obj).e == 2) || (this.b.d() && ((CircularProgressIndicatorSpec) obj).f == 1)) {
            this.f = (((1.0f - f) * ((CircularProgressIndicatorSpec) obj).a) / 2.0f) + this.f;
        } else if ((this.b.e() && ((CircularProgressIndicatorSpec) obj).e == 1) || (this.b.d() && ((CircularProgressIndicatorSpec) obj).f == 2)) {
            this.f -= ((1.0f - f) * ((CircularProgressIndicatorSpec) obj).a) / 2.0f;
        }
    }

    @Override // defpackage.xd0
    public final void b(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f == f2) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStrokeWidth(this.d);
        float f3 = this.c;
        float f4 = f * 360.0f * f3;
        if (f2 < f) {
            f2 += 1.0f;
        }
        float f5 = (f2 - f) * 360.0f * f3;
        float f6 = this.f;
        float f7 = -f6;
        canvas.drawArc(new RectF(f7, f7, f6, f6), f4, f5, false, paint);
        if (this.e <= 0.0f || Math.abs(f5) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        float f8 = this.d;
        float f9 = this.e;
        canvas.save();
        canvas.rotate(f4);
        float f10 = this.f;
        float f11 = f8 / 2.0f;
        canvas.drawRoundRect(new RectF(f10 - f11, f9, f10 + f11, -f9), f9, f9, paint);
        canvas.restore();
        float f12 = this.d;
        float f13 = this.e;
        canvas.save();
        canvas.rotate(f4 + f5);
        float f14 = this.f;
        float f15 = f12 / 2.0f;
        canvas.drawRoundRect(new RectF(f14 - f15, f13, f14 + f15, -f13), f13, f13, paint);
        canvas.restore();
    }

    @Override // defpackage.xd0
    public final void c(Canvas canvas, Paint paint) {
        int iE = oj2.e(((CircularProgressIndicatorSpec) this.a).d, this.b.n);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(iE);
        paint.setStrokeWidth(this.d);
        float f = this.f;
        canvas.drawArc(new RectF(-f, -f, f, f), 0.0f, 360.0f, false, paint);
    }

    @Override // defpackage.xd0
    public final int d() {
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.a;
        return (circularProgressIndicatorSpec.h * 2) + circularProgressIndicatorSpec.g;
    }

    @Override // defpackage.xd0
    public final int e() {
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.a;
        return (circularProgressIndicatorSpec.h * 2) + circularProgressIndicatorSpec.g;
    }
}
