package h6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import e6.m;

/* JADX INFO: compiled from: CutoutDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends e6.h {
    public final Paint B;
    public final RectF C;
    public int D;

    public c() {
        this(null);
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        p0(canvas);
        super.draw(canvas);
        canvas.drawRect(this.C, this.B);
        o0(canvas);
    }

    public boolean n0() {
        return !this.C.isEmpty();
    }

    public final void o0(Canvas canvas) {
        if (v0(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.D);
    }

    public final void p0(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (!v0(callback)) {
            r0(canvas);
            return;
        }
        View view = (View) callback;
        if (view.getLayerType() != 2) {
            view.setLayerType(2, null);
        }
    }

    public void q0() {
        s0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public final void r0(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.D = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        } else {
            this.D = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
        }
    }

    public void s0(float f8, float f9, float f10, float f11) {
        RectF rectF = this.C;
        if (f8 == rectF.left && f9 == rectF.top && f10 == rectF.right && f11 == rectF.bottom) {
            return;
        }
        rectF.set(f8, f9, f10, f11);
        invalidateSelf();
    }

    public void t0(RectF rectF) {
        s0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public final void u0() {
        this.B.setStyle(Paint.Style.FILL_AND_STROKE);
        this.B.setColor(-1);
        this.B.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public final boolean v0(Drawable.Callback callback) {
        return callback instanceof View;
    }

    public c(m mVar) {
        super(mVar == null ? new m() : mVar);
        this.B = new Paint(1);
        u0();
        this.C = new RectF();
    }
}
