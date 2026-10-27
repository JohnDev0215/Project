package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes6.dex */
class CutoutDrawable extends GradientDrawable {
    private final RectF cutoutBounds;
    private final Paint cutoutPaint = new Paint(1);
    private int savedLayer;

    CutoutDrawable() {
        setPaintStyles();
        this.cutoutBounds = new RectF();
    }

    private void setPaintStyles() {
        this.cutoutPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.cutoutPaint.setColor(-1);
        this.cutoutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    boolean hasCutout() {
        return !this.cutoutBounds.isEmpty();
    }

    void setCutout(float f, float f2, float f3, float f4) {
        RectF rectF = this.cutoutBounds;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }

    void setCutout(RectF rectF) {
        setCutout(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    void removeCutout() {
        setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        preDraw(canvas);
        super.draw(canvas);
        canvas.drawRect(this.cutoutBounds, this.cutoutPaint);
        postDraw(canvas);
    }

    private void preDraw(@NonNull Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (useHardwareLayer(callback)) {
            ((View) callback).setLayerType(2, null);
        } else {
            saveCanvasLayer(canvas);
        }
    }

    private void saveCanvasLayer(@NonNull Canvas canvas) {
        this.savedLayer = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
    }

    private void postDraw(@NonNull Canvas canvas) {
        if (useHardwareLayer(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.savedLayer);
    }

    private boolean useHardwareLayer(Drawable.Callback callback) {
        return callback instanceof View;
    }
}
