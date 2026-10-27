package com.amazon.avod.playback.smoothstream.diagnostics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import androidx.core.internal.view.SupportMenu;
import com.amazon.avod.media.TimeSpan;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
abstract class GraphPlotter {
    protected float mFirstXLocation;
    private int mHeight;
    protected float mMediumStrokeWidth;
    protected PointF mOrigin;
    private int mWidth;
    protected Paint mAxisPaint = null;
    protected Paint mXAxisTextPaint = null;
    protected Paint mYAxisTextPaint = null;
    protected Paint mLightAxisPaint = null;

    public abstract void draw(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector);

    GraphPlotter() {
    }

    protected void initBase(PointF pointF, PointF pointF2, int i) {
        this.mOrigin = pointF;
        this.mWidth = (int) Math.abs(pointF.x - pointF2.x);
        this.mHeight = (int) Math.abs(pointF.y - pointF2.y);
        PointF pointFCalculateAxesOrigin = calculateAxesOrigin(pointF, 50, i);
        this.mOrigin = pointFCalculateAxesOrigin;
        this.mFirstXLocation = pointFCalculateAxesOrigin.x + 5.0f;
        Paint paint = new Paint();
        this.mAxisPaint = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.mAxisPaint.setStrokeWidth(2.0f);
        Paint paint2 = new Paint();
        this.mXAxisTextPaint = paint2;
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        this.mXAxisTextPaint.setTextSize(14.0f);
        this.mXAxisTextPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint3 = new Paint();
        this.mYAxisTextPaint = paint3;
        paint3.setColor(SupportMenu.CATEGORY_MASK);
        this.mYAxisTextPaint.setTextSize(14.0f);
        this.mYAxisTextPaint.setTextAlign(Paint.Align.RIGHT);
        Paint paint4 = new Paint();
        this.mLightAxisPaint = paint4;
        paint4.setColor(-7829368);
        this.mLightAxisPaint.setStrokeWidth(2.0f);
        this.mMediumStrokeWidth = getHeight() * 0.005f;
    }

    protected int getWidth() {
        return this.mWidth;
    }

    protected int getHeight() {
        return this.mHeight;
    }

    protected PointF calculateAxesOrigin(PointF pointF, int i, int i2) {
        return new PointF(pointF.x + i, pointF.y - i2);
    }

    protected void drawAxis(Canvas canvas, int i, int i2) {
        drawHorizontalAxis(canvas, this.mOrigin, "0", "", this.mAxisPaint, this.mYAxisTextPaint);
        PointF pointF = this.mOrigin;
        float f = pointF.x;
        float f2 = pointF.y;
        float f3 = i2;
        canvas.drawLine(f, f2, f, f2 - f3, this.mAxisPaint);
        PointF pointF2 = this.mOrigin;
        drawHorizontalAxis(canvas, new PointF(pointF2.x, pointF2.y - f3), String.format(Locale.US, "%d", Integer.valueOf(i)), "", this.mAxisPaint, this.mYAxisTextPaint);
    }

    protected void drawHorizontalAxis(Canvas canvas, PointF pointF, String str, String str2, Paint paint, Paint paint2) {
        paint2.getTextBounds(str2, 0, str2.length(), new Rect());
        float f = pointF.x;
        float width = (getWidth() - 50) - 0;
        canvas.drawLine(f, pointF.y, ((f + width) - Math.abs(r11.right - r11.left)) - 3.0f, pointF.y, paint);
        canvas.drawText(str2, pointF.x + width, pointF.y + (Math.abs(r11.top - r11.bottom) / 2.0f), paint2);
        paint2.getTextBounds(str, 0, str.length(), new Rect());
        canvas.drawText(str, pointF.x - 3.0f, pointF.y + (Math.abs(r0.top - r0.bottom) / 2.0f), paint2);
    }

    protected void drawXAxisLabel(Canvas canvas, PointF pointF, String str, Paint paint) {
        this.mXAxisTextPaint.getTextBounds(str, 0, str.length(), new Rect());
        float f = pointF.x;
        float f2 = pointF.y;
        canvas.drawLine(f, f2, f, f2 + 3.0f, paint);
        canvas.drawText(str, pointF.x, pointF.y + 7.0f + Math.abs(r0.top - r0.bottom), paint);
    }

    protected <T extends DataPoint> Iterator<T> findStartingIterator(TreeSet<T> treeSet, TimeSpan timeSpan) {
        Iterator<T> itDescendingIterator = treeSet.descendingIterator();
        Iterator<T> it = treeSet.iterator();
        long totalMilliseconds = itDescendingIterator.next().getTimeStamp().getTotalMilliseconds() - 270000;
        int size = treeSet.size();
        do {
            size--;
            if (!itDescendingIterator.hasNext()) {
                break;
            }
        } while (totalMilliseconds <= itDescendingIterator.next().getTimeStamp().getTotalMilliseconds());
        while (itDescendingIterator.hasNext()) {
            if (timeSpan.getTotalMilliseconds() > itDescendingIterator.next().getTimeStamp().getTotalMilliseconds()) {
                break;
            }
            size--;
        }
        for (int i = 0; i < size - 1; i++) {
            it.next();
        }
        return it;
    }

    protected PointF drawLineSegment(Canvas canvas, PointF pointF, PointF pointF2, Paint paint, Paint paint2) {
        if (paint2 != null) {
            canvas.drawCircle(pointF.x, pointF.y, this.mMediumStrokeWidth, paint2);
        }
        if (paint != null && pointF2 != null) {
            canvas.drawLine(pointF2.x, pointF2.y, pointF.x, pointF.y, paint);
        }
        return pointF;
    }

    protected float getXLocationFromTimeStamp(TimeSpan timeSpan, long j) {
        return this.mFirstXLocation + ((((getWidth() - 50) + 0) - 5.0f) * ((timeSpan.getTotalMilliseconds() - j) / 300000.0f));
    }
}
