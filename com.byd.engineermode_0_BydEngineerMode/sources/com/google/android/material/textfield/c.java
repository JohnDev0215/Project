package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import c.b.a.a.w.m;

/* JADX INFO: compiled from: CutoutDrawable.java */
/* JADX INFO: loaded from: classes.dex */
class c extends c.b.a.a.w.h {
    private final RectF A;
    private int B;
    private final Paint z;

    c() {
        this(null);
    }

    private void o0(Canvas canvas) {
        if (v0(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.B);
    }

    private void p0(Canvas canvas) {
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

    private void r0(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.B = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        } else {
            this.B = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
        }
    }

    private void u0() {
        this.z.setStyle(Paint.Style.FILL_AND_STROKE);
        this.z.setColor(-1);
        this.z.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private boolean v0(Drawable.Callback callback) {
        return callback instanceof View;
    }

    @Override // c.b.a.a.w.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        p0(canvas);
        super.draw(canvas);
        canvas.drawRect(this.A, this.z);
        o0(canvas);
    }

    boolean n0() {
        return !this.A.isEmpty();
    }

    void q0() {
        s0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void s0(float f, float f2, float f3, float f4) {
        RectF rectF = this.A;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }

    void t0(RectF rectF) {
        s0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    c(m mVar) {
        super(mVar == null ? new m() : mVar);
        this.z = new Paint(1);
        u0();
        this.A = new RectF();
    }
}
