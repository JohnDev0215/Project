package b.d.a.a.h0;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class g extends b.d.a.a.e0.g {
    public int A;
    public final Paint y;
    public final RectF z;

    public g() {
        this(null);
    }

    public g(b.d.a.a.e0.j jVar) {
        super(jVar == null ? new b.d.a.a.e0.j() : jVar);
        Paint paint = new Paint(1);
        this.y = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.y.setColor(-1);
        this.y.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.z = new RectF();
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (callback instanceof View) {
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, null);
            }
        } else {
            this.A = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        }
        super.draw(canvas);
        canvas.drawRect(this.z, this.y);
        if (getCallback() instanceof View) {
            return;
        }
        canvas.restoreToCount(this.A);
    }

    public void y(float f2, float f3, float f4, float f5) {
        RectF rectF = this.z;
        if (f2 == rectF.left && f3 == rectF.top && f4 == rectF.right && f5 == rectF.bottom) {
            return;
        }
        this.z.set(f2, f3, f4, f5);
        invalidateSelf();
    }
}
