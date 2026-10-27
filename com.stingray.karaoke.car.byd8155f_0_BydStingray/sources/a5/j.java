package a5;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Y4.g {

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Paint f11153G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final RectF f11154H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f11155I;

    public j(Y4.k kVar) {
        super(kVar == null ? new Y4.k() : kVar);
        Paint paint = new Paint(1);
        this.f11153G = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f11154H = new RectF();
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (callback instanceof View) {
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, null);
            }
        } else {
            this.f11155I = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        }
        super.draw(canvas);
        canvas.drawRect(this.f11154H, this.f11153G);
        if (getCallback() instanceof View) {
            return;
        }
        canvas.restoreToCount(this.f11155I);
    }

    public final void l(float f10, float f11, float f12, float f13) {
        RectF rectF = this.f11154H;
        if (f10 == rectF.left && f11 == rectF.top && f12 == rectF.right && f13 == rectF.bottom) {
            return;
        }
        rectF.set(f10, f11, f12, f13);
        invalidateSelf();
    }
}
