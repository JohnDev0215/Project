package c.b.a.a.l0;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import c.b.a.a.i0.m;

/* JADX INFO: compiled from: CutoutDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends c.b.a.a.i0.h {
    public final RectF A;
    public int B;
    public final Paint z;

    public c() {
        this(null);
    }

    @Override // c.b.a.a.i0.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        p0(canvas);
        super.draw(canvas);
        canvas.drawRect(this.A, this.z);
        o0(canvas);
    }

    public boolean n0() {
        return !this.A.isEmpty();
    }

    public final void o0(Canvas canvas) {
        if (v0(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.B);
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
            this.B = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        } else {
            this.B = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
        }
    }

    public void s0(float f2, float f3, float f4, float f5) {
        RectF rectF = this.A;
        if (f2 == rectF.left && f3 == rectF.top && f4 == rectF.right && f5 == rectF.bottom) {
            return;
        }
        rectF.set(f2, f3, f4, f5);
        invalidateSelf();
    }

    public void t0(RectF rectF) {
        s0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public final void u0() {
        this.z.setStyle(Paint.Style.FILL_AND_STROKE);
        this.z.setColor(-1);
        this.z.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public final boolean v0(Drawable.Callback callback) {
        return callback instanceof View;
    }

    public c(m mVar) {
        super(mVar == null ? new m() : mVar);
        this.z = new Paint(1);
        u0();
        this.A = new RectF();
    }
}
