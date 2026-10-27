package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import b.a.a.a.b0.k;

/* JADX INFO: loaded from: classes.dex */
class c extends b.a.a.a.b0.g {
    private final RectF A;
    private int B;
    private final Paint z;

    c() {
        this(null);
    }

    c(k kVar) {
        super(kVar == null ? new k() : kVar);
        this.z = new Paint(1);
        p0();
        this.A = new RectF();
    }

    private void j0(Canvas canvas) {
        if (q0(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.B);
    }

    private void k0(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (!q0(callback)) {
            m0(canvas);
            return;
        }
        View view = (View) callback;
        if (view.getLayerType() != 2) {
            view.setLayerType(2, null);
        }
    }

    private void m0(Canvas canvas) {
        this.B = Build.VERSION.SDK_INT >= 21 ? canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null) : canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
    }

    private void p0() {
        this.z.setStyle(Paint.Style.FILL_AND_STROKE);
        this.z.setColor(-1);
        this.z.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private boolean q0(Drawable.Callback callback) {
        return callback instanceof View;
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        k0(canvas);
        super.draw(canvas);
        canvas.drawRect(this.A, this.z);
        j0(canvas);
    }

    boolean i0() {
        return !this.A.isEmpty();
    }

    void l0() {
        n0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void n0(float f, float f2, float f3, float f4) {
        RectF rectF = this.A;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }

    void o0(RectF rectF) {
        n0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }
}
