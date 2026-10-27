package com.amazon.avod.playback.smoothstream.diagnostics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class BufferGraphPlotter extends GraphPlotter {
    private int mBarGraphHeight;
    private final DebugGraphViewConfig mDebugGraphViewConfig;
    private boolean mIsCompactViewEnabled;
    private final int mMaxBufferSeconds;
    private Paint mMemoryBarPaint;
    private Paint mNativeBarPaint;
    private Paint mRectPaint;

    public BufferGraphPlotter() {
        this(DebugGraphViewConfig.INSTANCE);
    }

    public BufferGraphPlotter(@Nonnull DebugGraphViewConfig debugGraphViewConfig) {
        this.mBarGraphHeight = 0;
        this.mNativeBarPaint = null;
        this.mMemoryBarPaint = null;
        this.mRectPaint = null;
        DebugGraphViewConfig debugGraphViewConfig2 = (DebugGraphViewConfig) Preconditions.checkNotNull(debugGraphViewConfig, "debugGraphViewConfig");
        this.mDebugGraphViewConfig = debugGraphViewConfig2;
        this.mMaxBufferSeconds = debugGraphViewConfig2.getMaxBufferDuration().getTotalSeconds();
    }

    public void init(PointF pointF, PointF pointF2, boolean z) {
        super.initBase(pointF, pointF2, 0);
        this.mIsCompactViewEnabled = z;
        this.mBarGraphHeight = (getHeight() - 0) - 40;
        Paint paint = new Paint();
        this.mMemoryBarPaint = paint;
        paint.setColor(-29696);
        this.mMemoryBarPaint.setStrokeWidth(5.0f);
        this.mMemoryBarPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mMemoryBarPaint.setTextSize(16.0f);
        this.mMemoryBarPaint.setAlpha(164);
        Paint paint2 = new Paint();
        this.mNativeBarPaint = paint2;
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        this.mNativeBarPaint.setStrokeWidth(5.0f);
        this.mNativeBarPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mNativeBarPaint.setTextSize(16.0f);
        this.mNativeBarPaint.setAlpha(164);
        if (this.mIsCompactViewEnabled) {
            Paint paint3 = new Paint();
            this.mRectPaint = paint3;
            paint3.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mRectPaint.setAlpha(100);
            this.mRectPaint.setStyle(Paint.Style.FILL);
        }
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.GraphPlotter
    public void draw(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector) {
        drawAxis(canvas, this.mMaxBufferSeconds);
        drawKey(canvas);
        drawData(canvas, new TimeSpan(diagnosticDataCollector.getPlayHeadInNanos()), diagnosticDataCollector.getDiagnosticDataPoints());
    }

    private void drawAxis(Canvas canvas, int i) {
        super.drawAxis(canvas, i, this.mBarGraphHeight);
        if (this.mIsCompactViewEnabled) {
            PointF pointF = this.mOrigin;
            canvas.drawRect(pointF.x - 50.0f, (pointF.y + 0.0f) - getHeight(), (this.mOrigin.x - 50.0f) + getWidth(), this.mOrigin.y + 0.0f, this.mRectPaint);
        }
        int i2 = this.mMaxBufferSeconds;
        for (int i3 = 30; i3 < i2; i3 += 30) {
            PointF pointF2 = this.mOrigin;
            drawHorizontalAxis(canvas, createPoint(pointF2, pointF2.x, i3, i, this.mBarGraphHeight), String.valueOf(i3), "", this.mLightAxisPaint, this.mYAxisTextPaint);
        }
    }

    private void drawKey(Canvas canvas) {
        PointF pointF = this.mOrigin;
        writeText(canvas, "Memory Buffer", writeText(canvas, "Native Buffer", new PointF(pointF.x + 5.0f, (pointF.y - this.mBarGraphHeight) - 5.0f), this.mNativeBarPaint), this.mMemoryBarPaint);
    }

    private PointF writeText(Canvas canvas, String str, PointF pointF, Paint paint) {
        canvas.drawText(str, pointF.x, pointF.y, paint);
        pointF.x += paint.measureText(str) + 5.0f;
        return pointF;
    }

    private void drawData(Canvas canvas, TimeSpan timeSpan, TreeSet<DiagnosticDataPoint> treeSet) {
        Iterator it;
        long j;
        synchronized (treeSet) {
            if (treeSet.isEmpty()) {
                return;
            }
            Iterator itFindStartingIterator = findStartingIterator(treeSet, timeSpan);
            DiagnosticDataPoint diagnosticDataPoint = (DiagnosticDataPoint) itFindStartingIterator.next();
            long totalMilliseconds = diagnosticDataPoint.getTimeStamp().getTotalMilliseconds();
            float xLocationFromTimeStamp = getXLocationFromTimeStamp(diagnosticDataPoint.getTimeStamp(), totalMilliseconds);
            Path path = new Path();
            path.moveTo(xLocationFromTimeStamp, this.mOrigin.y);
            Path path2 = new Path();
            path2.moveTo(xLocationFromTimeStamp, this.mOrigin.y);
            ArrayDeque arrayDeque = new ArrayDeque();
            ArrayDeque arrayDeque2 = new ArrayDeque();
            DiagnosticDataPoint diagnosticDataPoint2 = diagnosticDataPoint;
            while (itFindStartingIterator.hasNext()) {
                if (diagnosticDataPoint2.getStreamType() != StreamType.VIDEO || diagnosticDataPoint2.getFragmentType() == FragmentType.UPGRADE) {
                    it = itFindStartingIterator;
                    j = totalMilliseconds;
                } else {
                    float xLocationFromTimeStamp2 = getXLocationFromTimeStamp(diagnosticDataPoint2.getTimeStamp(), totalMilliseconds);
                    PointF pointF = this.mOrigin;
                    float millis = TimeUnit.NANOSECONDS.toMillis(diagnosticDataPoint2.getNativeBufferTimeInNanoseconds());
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    j = totalMilliseconds;
                    it = itFindStartingIterator;
                    PointF pointFCreatePoint = createPoint(pointF, xLocationFromTimeStamp2, millis, timeUnit.toMillis(this.mMaxBufferSeconds), this.mBarGraphHeight);
                    arrayDeque.push(pointFCreatePoint);
                    path.lineTo(xLocationFromTimeStamp2, this.mOrigin.y);
                    path2.lineTo(pointFCreatePoint.x, pointFCreatePoint.y);
                    arrayDeque2.push(createPoint(pointFCreatePoint, xLocationFromTimeStamp2, r5.toMillis(diagnosticDataPoint2.getInMemoryBufferTimeInNanoseconds()), timeUnit.toMillis(this.mMaxBufferSeconds), this.mBarGraphHeight - ((int) Math.abs((this.mOrigin.y - 1.0f) - pointFCreatePoint.y))));
                }
                diagnosticDataPoint2 = (DiagnosticDataPoint) it.next();
                itFindStartingIterator = it;
                totalMilliseconds = j;
            }
            addPointsToPath(arrayDeque, path);
            addPointsToPath(arrayDeque2, path2);
            canvas.drawPath(path, this.mNativeBarPaint);
            canvas.drawPath(path2, this.mMemoryBarPaint);
        }
    }

    private static void addPointsToPath(Deque<PointF> deque, Path path) {
        while (!deque.isEmpty()) {
            PointF pointFPop = deque.pop();
            path.lineTo(pointFPop.x, pointFPop.y);
        }
    }

    private PointF createPoint(PointF pointF, float f, float f2, float f3, int i) {
        return new PointF(f, pointF.y - ((f2 / f3) * i));
    }
}
