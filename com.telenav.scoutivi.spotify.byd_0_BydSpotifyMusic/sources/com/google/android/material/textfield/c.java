package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import e3.k;

/* JADX INFO: compiled from: CutoutDrawable.java */
/* JADX INFO: loaded from: classes.dex */
class c extends e3.g {
    private final Paint D;
    private final RectF E;
    private int F;

    c() {
        this(null);
    }

    private void j0(Canvas canvas) {
        if (q0(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.F);
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
        this.F = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
    }

    private void p0() {
        this.D.setStyle(Paint.Style.FILL_AND_STROKE);
        this.D.setColor(-1);
        this.D.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private boolean q0(Drawable.Callback callback) {
        return callback instanceof View;
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        k0(canvas);
        super.draw(canvas);
        canvas.drawRect(this.E, this.D);
        j0(canvas);
    }

    boolean i0() {
        return !this.E.isEmpty();
    }

    void l0() {
        n0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void n0(float f7, float f8, float f9, float f10) {
        RectF rectF = this.E;
        if (f7 == rectF.left && f8 == rectF.top && f9 == rectF.right && f10 == rectF.bottom) {
            return;
        }
        rectF.set(f7, f8, f9, f10);
        invalidateSelf();
    }

    void o0(RectF rectF) {
        n0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    c(k kVar) {
        super(kVar == null ? new k() : kVar);
        this.D = new Paint(1);
        p0();
        this.E = new RectF();
    }
}
