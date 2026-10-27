package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/* JADX INFO: compiled from: CutoutDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class r50 extends sc1 {
    public final RectF B;

    public r50() {
        this(null);
    }

    @Override // defpackage.sc1
    public final void g(Canvas canvas) {
        RectF rectF = this.B;
        if (rectF.isEmpty()) {
            super.g(canvas);
            return;
        }
        canvas.save();
        canvas.clipOutRect(rectF);
        super.g(canvas);
        canvas.restore();
    }

    public final void w(float f, float f2, float f3, float f4) {
        RectF rectF = this.B;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }

    public r50(uj2 uj2Var) {
        super(uj2Var == null ? new uj2() : uj2Var);
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.B = new RectF();
    }
}
