package p;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class c50 extends qf1 {
    public final Paint F;
    public final RectF G;
    public int H;

    public c50(oc2 oc2Var) {
        super(oc2Var == null ? new oc2() : oc2Var);
        Paint paint = new Paint(1);
        this.F = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.G = new RectF();
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (callback instanceof View) {
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, null);
            }
        } else {
            this.H = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        }
        super.draw(canvas);
        if (getCallback() instanceof View) {
            return;
        }
        canvas.restoreToCount(this.H);
    }

    @Override // p.qf1
    public final void f(Canvas canvas) {
        RectF rectF = this.G;
        if (rectF.isEmpty()) {
            super.f(canvas);
            return;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        super.f(canvas2);
        canvas2.drawRect(rectF, this.F);
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
    }

    public final void o(float f, float f2, float f3, float f4) {
        RectF rectF = this.G;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }
}
