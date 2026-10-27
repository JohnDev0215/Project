package com.android.inputmethod.keyboard.internal;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public final class RoundedLine {
    private static final double RADIAN_TO_DEGREE = 57.29577951308232d;
    private static final double RIGHT_ANGLE = 1.5707963267948966d;
    private final RectF mArc1 = new RectF();
    private final RectF mArc2 = new RectF();
    private final Path mPath = new Path();

    public Path makePath(final float p1x, final float p1y, final float r1, final float p2x, final float p2y, final float r2) {
        this.mPath.rewind();
        double d = p2x - p1x;
        double d2 = p2y - p1y;
        double dHypot = Math.hypot(d, d2);
        if (Double.compare(0.0d, dHypot) == 0) {
            return this.mPath;
        }
        double dAtan2 = Math.atan2(d2, d);
        double dAsin = Math.asin(((double) (r2 - r1)) / dHypot);
        double d3 = RIGHT_ANGLE + dAsin;
        double d4 = dAtan2 - d3;
        double d5 = dAtan2 + d3;
        float fCos = (float) Math.cos(d4);
        float fSin = (float) Math.sin(d4);
        float fCos2 = (float) Math.cos(d5);
        float fSin2 = (float) Math.sin(d5);
        float f = (r1 * fCos) + p1x;
        float f2 = (r1 * fSin) + p1y;
        float f3 = (fSin * r2) + p2y;
        float f4 = (float) (d4 * RADIAN_TO_DEGREE);
        float f5 = (float) (dAsin * 2.0d * RADIAN_TO_DEGREE);
        this.mArc1.set(p1x, p1y, p1x, p1y);
        float f6 = -r1;
        this.mArc1.inset(f6, f6);
        this.mArc2.set(p2x, p2y, p2x, p2y);
        float f7 = -r2;
        this.mArc2.inset(f7, f7);
        this.mPath.moveTo(p1x, p1y);
        this.mPath.arcTo(this.mArc1, f4, (-180.0f) + f5);
        this.mPath.moveTo(p2x, p2y);
        this.mPath.arcTo(this.mArc2, f4, f5 + 180.0f);
        this.mPath.moveTo(f, f2);
        this.mPath.lineTo(p1x, p1y);
        this.mPath.lineTo((r1 * fCos2) + p1x, (r1 * fSin2) + p1y);
        this.mPath.lineTo((fCos2 * r2) + p2x, (fSin2 * r2) + p2y);
        this.mPath.lineTo(p2x, p2y);
        this.mPath.lineTo((fCos * r2) + p2x, f3);
        this.mPath.close();
        return this.mPath;
    }

    public void getBounds(final Rect outBounds) {
        this.mPath.computeBounds(this.mArc1, true);
        this.mArc1.roundOut(outBounds);
    }
}
