package com.amazon.avod.playback.smoothstream.diagnostics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import androidx.compose.runtime.ComposerKt;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DataUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CdnGraphPlotter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 A2\u00020\u0001:\u0003?@AB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\tH\u0002J\u0018\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\tH\u0002J\u0018\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0013H\u0002J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J&\u0010)\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002J\u0018\u0010/\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u00100\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u00101\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u00102\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u000204H\u0002J&\u00105\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002J\u0018\u00106\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J&\u00107\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002J\u0018\u00108\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u00109\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J \u0010:\u001a\u00020#2\u0006\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020>H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/CdnGraphPlotter;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/GraphPlotter;", "()V", "axisLinePaint", "Landroid/graphics/Paint;", "axisTextPaint", "bandwidthPaint", "bandwidthPointPaint", "cdnGraphHeight", "", "cdnGraphWidth", "cdnPaint", "config", "Lcom/amazon/avod/playback/smoothstream/diagnostics/CdnGraphPlotter$CdnGraphConfig;", "latencyPaint", "latencyPointPaint", "maxBandwidth", "maxLatency", "maxQoe", "", "minBandwidth", "minLatency", "minQoe", "qoePaint", "qoePointPaint", "rectPaint", "createBandwidthPoint", "Landroid/graphics/PointF;", "xPointLocation", "bandwidth", "createLatencyPoint", "latency", "createQoePoint", "qoeValue", "draw", "", "canvas", "Landroid/graphics/Canvas;", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "drawAxes", "drawBandwidthGraph", "playHeadTimeStamp", "Lcom/amazon/avod/media/TimeSpan;", "aggregatedDataPoints", "Ljava/util/TreeSet;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/AggregatedDataPoint;", "drawBandwidthText", "drawCdnText", "drawGraphs", "drawLabels", "originMilliseconds", "", "drawLatencyGraph", "drawLatencyText", "drawQoeGraph", "drawQoeText", "drawText", "init", "lowerLeft", "upperRight", "isCompactViewEnabled", "", "CdnGraphConfig", "CdnGraphType", "Companion", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CdnGraphPlotter extends GraphPlotter {
    private static final int COLOR_BANDWIDTH;
    private static final int COLOR_LATENCY;
    private static final int COLOR_QOE;
    private static final int COLOR_SKY_BLUE;
    private static final int COLOR_VIOLET;
    private Paint axisLinePaint;
    private Paint axisTextPaint;
    private Paint bandwidthPaint;
    private Paint bandwidthPointPaint;
    private int cdnGraphHeight;
    private int cdnGraphWidth;
    private Paint cdnPaint;
    private final CdnGraphConfig config;
    private Paint latencyPaint;
    private Paint latencyPointPaint;
    private final int maxBandwidth;
    private final int maxLatency;
    private final float maxQoe;
    private final int minBandwidth;
    private final int minLatency;
    private final float minQoe;
    private Paint qoePaint;
    private Paint qoePointPaint;
    private Paint rectPaint;

    /* JADX INFO: compiled from: CdnGraphPlotter.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/CdnGraphPlotter$CdnGraphType;", "", "(Ljava/lang/String;I)V", "SHOW_QOE", "SHOW_BANDWIDTH", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum CdnGraphType {
        SHOW_QOE,
        SHOW_BANDWIDTH
    }

    public CdnGraphPlotter() {
        CdnGraphConfig cdnGraphConfig = CdnGraphConfig.INSTANCE;
        this.config = cdnGraphConfig;
        this.maxLatency = cdnGraphConfig.getMaxLatency();
        this.minLatency = cdnGraphConfig.getMinLatency();
        this.maxBandwidth = cdnGraphConfig.getMaxBandwidth();
        this.minBandwidth = cdnGraphConfig.getMinBandwidth();
        this.maxQoe = cdnGraphConfig.getMaxQoe();
        this.minQoe = cdnGraphConfig.getMinQoe();
    }

    public void init(PointF lowerLeft, PointF upperRight, boolean isCompactViewEnabled) {
        Intrinsics.checkNotNullParameter(lowerLeft, "lowerLeft");
        Intrinsics.checkNotNullParameter(upperRight, "upperRight");
        super.initBase(lowerLeft, upperRight, 30);
        this.cdnGraphHeight = (getHeight() - 30) - 100;
        this.cdnGraphWidth = (getWidth() - 50) + 0;
        Paint paint = new Paint();
        this.latencyPaint = paint;
        int i = COLOR_LATENCY;
        paint.setColor(i);
        Paint paint2 = this.latencyPaint;
        Paint paint3 = null;
        if (paint2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("latencyPaint");
            paint2 = null;
        }
        paint2.setTypeface(Typeface.MONOSPACE);
        Paint paint4 = this.latencyPaint;
        if (paint4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("latencyPaint");
            paint4 = null;
        }
        paint4.setTextSize(20.0f);
        Paint paint5 = new Paint();
        this.latencyPointPaint = paint5;
        paint5.setColor(i);
        Paint paint6 = this.latencyPointPaint;
        if (paint6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("latencyPointPaint");
            paint6 = null;
        }
        paint6.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint7 = new Paint();
        this.bandwidthPaint = paint7;
        int i2 = COLOR_BANDWIDTH;
        paint7.setColor(i2);
        Paint paint8 = this.bandwidthPaint;
        if (paint8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bandwidthPaint");
            paint8 = null;
        }
        paint8.setTypeface(Typeface.MONOSPACE);
        Paint paint9 = this.bandwidthPaint;
        if (paint9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bandwidthPaint");
            paint9 = null;
        }
        paint9.setTextSize(20.0f);
        Paint paint10 = new Paint();
        this.bandwidthPointPaint = paint10;
        paint10.setColor(i2);
        Paint paint11 = this.bandwidthPointPaint;
        if (paint11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bandwidthPointPaint");
            paint11 = null;
        }
        paint11.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint12 = new Paint();
        this.qoePaint = paint12;
        int i3 = COLOR_QOE;
        paint12.setColor(i3);
        Paint paint13 = this.qoePaint;
        if (paint13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qoePaint");
            paint13 = null;
        }
        paint13.setTypeface(Typeface.MONOSPACE);
        Paint paint14 = this.qoePaint;
        if (paint14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qoePaint");
            paint14 = null;
        }
        paint14.setTextSize(20.0f);
        Paint paint15 = new Paint();
        this.qoePointPaint = paint15;
        paint15.setColor(i3);
        Paint paint16 = this.qoePointPaint;
        if (paint16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qoePointPaint");
            paint16 = null;
        }
        paint16.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint17 = new Paint();
        this.axisTextPaint = paint17;
        paint17.setColor(-1);
        Paint paint18 = this.axisTextPaint;
        if (paint18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
            paint18 = null;
        }
        paint18.setTextSize(14.0f);
        Paint paint19 = new Paint();
        this.rectPaint = paint19;
        paint19.setColor(ViewCompat.MEASURED_STATE_MASK);
        Paint paint20 = this.rectPaint;
        if (paint20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rectPaint");
            paint20 = null;
        }
        paint20.setAlpha(100);
        Paint paint21 = this.rectPaint;
        if (paint21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rectPaint");
            paint21 = null;
        }
        paint21.setStyle(Paint.Style.FILL);
        Paint paint22 = new Paint();
        this.axisLinePaint = paint22;
        paint22.setColor(-7829368);
        Paint paint23 = this.axisLinePaint;
        if (paint23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisLinePaint");
            paint23 = null;
        }
        paint23.setStrokeWidth(2.0f);
        Paint paint24 = new Paint();
        this.cdnPaint = paint24;
        paint24.setColor(-1);
        Paint paint25 = this.cdnPaint;
        if (paint25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdnPaint");
            paint25 = null;
        }
        paint25.setTextSize(25.0f);
        Paint paint26 = this.cdnPaint;
        if (paint26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdnPaint");
        } else {
            paint3 = paint26;
        }
        paint3.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.GraphPlotter
    public void draw(Canvas canvas, DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(collector, "collector");
        drawAxes(canvas);
        drawText(canvas, collector);
        drawGraphs(canvas, collector);
    }

    private final void drawGraphs(Canvas canvas, DiagnosticDataCollector collector) {
        TimeSpan timeSpan = new TimeSpan(collector.getPlayHeadInNanos());
        TreeSet<AggregatedDataPoint> aggregatedDataPoints = collector.getAggregatedDataPoints();
        Intrinsics.checkNotNullExpressionValue(aggregatedDataPoints, "aggregatedDataPoints");
        synchronized (aggregatedDataPoints) {
            if (!aggregatedDataPoints.isEmpty()) {
                if (this.config.getGraphType() == CdnGraphType.SHOW_BANDWIDTH) {
                    drawBandwidthGraph(canvas, timeSpan, aggregatedDataPoints);
                } else {
                    drawQoeGraph(canvas, timeSpan, aggregatedDataPoints);
                }
                drawLatencyGraph(canvas, timeSpan, aggregatedDataPoints);
                drawLabels(canvas, ((AggregatedDataPoint) findStartingIterator(aggregatedDataPoints, timeSpan).next()).getTimeStamp().getTotalMilliseconds());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void drawQoeGraph(Canvas canvas, TimeSpan playHeadTimeStamp, TreeSet<AggregatedDataPoint> aggregatedDataPoints) {
        Paint paint;
        Paint paint2;
        Iterator iterator = findStartingIterator(aggregatedDataPoints, playHeadTimeStamp);
        AggregatedDataPoint aggregatedDataPoint = (AggregatedDataPoint) iterator.next();
        long totalMilliseconds = aggregatedDataPoint.getTimeStamp().getTotalMilliseconds();
        PointF pointFCreateQoePoint = createQoePoint(getXLocationFromTimeStamp(aggregatedDataPoint.getTimeStamp(), totalMilliseconds), aggregatedDataPoint.getQoeValue());
        Intrinsics.checkNotNullExpressionValue(iterator, "iterator");
        while (true) {
            PointF pointF = pointFCreateQoePoint;
            while (iterator.hasNext()) {
                AggregatedDataPoint aggregatedDataPoint2 = (AggregatedDataPoint) iterator.next();
                float xLocationFromTimeStamp = getXLocationFromTimeStamp(aggregatedDataPoint2.getTimeStamp(), totalMilliseconds);
                float f = this.mFirstXLocation;
                if (xLocationFromTimeStamp > f && xLocationFromTimeStamp < f + this.cdnGraphWidth) {
                    PointF pointFCreateQoePoint2 = createQoePoint(xLocationFromTimeStamp, aggregatedDataPoint2.getQoeValue());
                    Paint paint3 = this.qoePaint;
                    if (paint3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qoePaint");
                        paint = null;
                    } else {
                        paint = paint3;
                    }
                    Paint paint4 = this.qoePointPaint;
                    if (paint4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qoePointPaint");
                        paint2 = null;
                    } else {
                        paint2 = paint4;
                    }
                    pointFCreateQoePoint = drawLineSegment(canvas, pointFCreateQoePoint2, pointF, paint, paint2);
                    Intrinsics.checkNotNullExpressionValue(pointFCreateQoePoint, "drawLineSegment(canvas, … qoePaint, qoePointPaint)");
                }
            }
            return;
        }
    }

    private final void drawLatencyGraph(Canvas canvas, TimeSpan playHeadTimeStamp, TreeSet<AggregatedDataPoint> aggregatedDataPoints) {
        Paint paint;
        Paint paint2;
        Iterator iterator = findStartingIterator(aggregatedDataPoints, playHeadTimeStamp);
        AggregatedDataPoint aggregatedDataPoint = (AggregatedDataPoint) iterator.next();
        long totalMilliseconds = aggregatedDataPoint.getTimeStamp().getTotalMilliseconds();
        PointF pointFCreateLatencyPoint = createLatencyPoint(getXLocationFromTimeStamp(aggregatedDataPoint.getTimeStamp(), totalMilliseconds), aggregatedDataPoint.getAggregatedLatencyMillis());
        Intrinsics.checkNotNullExpressionValue(iterator, "iterator");
        while (true) {
            PointF pointF = pointFCreateLatencyPoint;
            while (iterator.hasNext()) {
                AggregatedDataPoint aggregatedDataPoint2 = (AggregatedDataPoint) iterator.next();
                float xLocationFromTimeStamp = getXLocationFromTimeStamp(aggregatedDataPoint2.getTimeStamp(), totalMilliseconds);
                float f = this.mFirstXLocation;
                if (xLocationFromTimeStamp > f && xLocationFromTimeStamp < f + this.cdnGraphWidth) {
                    PointF pointFCreateLatencyPoint2 = createLatencyPoint(xLocationFromTimeStamp, aggregatedDataPoint2.getAggregatedLatencyMillis());
                    Paint paint3 = this.latencyPaint;
                    if (paint3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("latencyPaint");
                        paint = null;
                    } else {
                        paint = paint3;
                    }
                    Paint paint4 = this.latencyPointPaint;
                    if (paint4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("latencyPointPaint");
                        paint2 = null;
                    } else {
                        paint2 = paint4;
                    }
                    pointFCreateLatencyPoint = drawLineSegment(canvas, pointFCreateLatencyPoint2, pointF, paint, paint2);
                    Intrinsics.checkNotNullExpressionValue(pointFCreateLatencyPoint, "drawLineSegment(canvas, …Paint, latencyPointPaint)");
                }
            }
            return;
        }
    }

    private final void drawBandwidthGraph(Canvas canvas, TimeSpan playHeadTimeStamp, TreeSet<AggregatedDataPoint> aggregatedDataPoints) {
        Paint paint;
        Paint paint2;
        Iterator iterator = findStartingIterator(aggregatedDataPoints, playHeadTimeStamp);
        AggregatedDataPoint aggregatedDataPoint = (AggregatedDataPoint) iterator.next();
        long totalMilliseconds = aggregatedDataPoint.getTimeStamp().getTotalMilliseconds();
        PointF pointFCreateBandwidthPoint = createBandwidthPoint(getXLocationFromTimeStamp(aggregatedDataPoint.getTimeStamp(), totalMilliseconds), aggregatedDataPoint.getAggregatedBandwidthBps());
        Intrinsics.checkNotNullExpressionValue(iterator, "iterator");
        while (true) {
            PointF pointF = pointFCreateBandwidthPoint;
            while (iterator.hasNext()) {
                AggregatedDataPoint aggregatedDataPoint2 = (AggregatedDataPoint) iterator.next();
                float xLocationFromTimeStamp = getXLocationFromTimeStamp(aggregatedDataPoint2.getTimeStamp(), totalMilliseconds);
                float f = this.mFirstXLocation;
                if (xLocationFromTimeStamp > f && xLocationFromTimeStamp < f + this.cdnGraphWidth) {
                    PointF pointFCreateBandwidthPoint2 = createBandwidthPoint(xLocationFromTimeStamp, aggregatedDataPoint2.getAggregatedBandwidthBps());
                    Paint paint3 = this.bandwidthPaint;
                    if (paint3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bandwidthPaint");
                        paint = null;
                    } else {
                        paint = paint3;
                    }
                    Paint paint4 = this.bandwidthPointPaint;
                    if (paint4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bandwidthPointPaint");
                        paint2 = null;
                    } else {
                        paint2 = paint4;
                    }
                    pointFCreateBandwidthPoint = drawLineSegment(canvas, pointFCreateBandwidthPoint2, pointF, paint, paint2);
                    Intrinsics.checkNotNullExpressionValue(pointFCreateBandwidthPoint, "drawLineSegment(canvas, …int, bandwidthPointPaint)");
                }
            }
            return;
        }
    }

    private final PointF createBandwidthPoint(float xPointLocation, int bandwidth) {
        int iMin = Math.min(this.maxBandwidth, (int) DataUnit.BITS.toKiloBits(bandwidth));
        float f = this.mOrigin.y;
        int i = this.minBandwidth;
        return new PointF(xPointLocation, f - (((iMin - i) / (this.maxBandwidth - i)) * this.cdnGraphHeight));
    }

    private final PointF createLatencyPoint(float xPointLocation, int latency) {
        int iMin = Math.min(this.maxLatency, latency);
        float f = this.mOrigin.y;
        int i = this.minLatency;
        return new PointF(xPointLocation, f - (((iMin - i) / (this.maxLatency - i)) * this.cdnGraphHeight));
    }

    private final PointF createQoePoint(float xPointLocation, float qoeValue) {
        float fMin = Math.min(this.maxQoe, qoeValue);
        float f = this.mOrigin.y;
        float f2 = this.minQoe;
        return new PointF(xPointLocation, f - (((fMin - f2) / (this.maxQoe - f2)) * this.cdnGraphHeight));
    }

    private final void drawAxes(Canvas canvas) {
        Paint paint;
        Paint paint2;
        Paint paint3;
        Paint paint4;
        Paint paint5;
        PointF pointF = this.mOrigin;
        float f = 50;
        float f2 = pointF.x - f;
        float f3 = 30;
        float height = (pointF.y + f3) - getHeight();
        float width = (this.mOrigin.x - f) + getWidth();
        float f4 = this.mOrigin.y + f3;
        Paint paint6 = this.rectPaint;
        if (paint6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rectPaint");
            paint = null;
        } else {
            paint = paint6;
        }
        canvas.drawRect(f2, height, width, f4, paint);
        Paint paint7 = this.axisTextPaint;
        if (paint7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
            paint7 = null;
        }
        paint7.setTextAlign(Paint.Align.RIGHT);
        int i = 0;
        while (i < 11) {
            int i2 = i + 1;
            int i3 = this.minLatency;
            int i4 = i3 + (((this.maxLatency - i3) / 10) * i);
            float f5 = this.minQoe;
            float f6 = 10;
            float f7 = i;
            float f8 = f5 + (((this.maxQoe - f5) / f6) * f7);
            int i5 = this.minBandwidth;
            int i6 = i5 + (((this.maxBandwidth - i5) / 10) * i);
            String strValueOf = String.valueOf(i4);
            String str = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(f8)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
            String strValueOf2 = String.valueOf(i6);
            PointF pointF2 = new PointF(this.mOrigin.x, this.mOrigin.y - ((f7 / f6) * this.cdnGraphHeight));
            if (this.config.getGraphType() == CdnGraphType.SHOW_BANDWIDTH) {
                Paint paint8 = this.axisLinePaint;
                if (paint8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("axisLinePaint");
                    paint4 = null;
                } else {
                    paint4 = paint8;
                }
                Paint paint9 = this.axisTextPaint;
                if (paint9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
                    paint5 = null;
                } else {
                    paint5 = paint9;
                }
                drawHorizontalAxis(canvas, pointF2, strValueOf2, strValueOf, paint4, paint5);
            } else {
                Paint paint10 = this.axisLinePaint;
                if (paint10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("axisLinePaint");
                    paint2 = null;
                } else {
                    paint2 = paint10;
                }
                Paint paint11 = this.axisTextPaint;
                if (paint11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
                    paint3 = null;
                } else {
                    paint3 = paint11;
                }
                drawHorizontalAxis(canvas, pointF2, str, strValueOf, paint2, paint3);
            }
            i = i2;
        }
    }

    private final void drawText(Canvas canvas, DiagnosticDataCollector collector) {
        if (this.config.getGraphType() == CdnGraphType.SHOW_BANDWIDTH) {
            drawBandwidthText(canvas, collector);
        } else {
            drawQoeText(canvas, collector);
        }
        drawLatencyText(canvas, collector);
        drawCdnText(canvas, collector);
    }

    private final void drawQoeText(Canvas canvas, DiagnosticDataCollector collector) {
        String str = "QoE Value: ";
        if (!collector.getAggregatedDataPoints().isEmpty()) {
            str = String.format("%s%.2f", Arrays.copyOf(new Object[]{"QoE Value: ", Float.valueOf(collector.getAggregatedDataPoints().last().getQoeValue())}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        }
        PointF pointF = this.mOrigin;
        float f = pointF.x;
        float f2 = (pointF.y - this.cdnGraphHeight) - 20.0f;
        Paint paint = this.qoePaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qoePaint");
            paint = null;
        }
        canvas.drawText(str, f, f2, paint);
    }

    private final void drawBandwidthText(Canvas canvas, DiagnosticDataCollector collector) {
        String str = "Avg Bandwidth: ";
        if (!collector.getAggregatedDataPoints().isEmpty()) {
            str = String.format("%s%s Kbps", Arrays.copyOf(new Object[]{"Avg Bandwidth: ", Float.valueOf(DataUnit.BITS.toKiloBits(collector.getAggregatedDataPoints().last().getAggregatedBandwidthBps()))}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        }
        PointF pointF = this.mOrigin;
        float f = pointF.x;
        float f2 = (pointF.y - this.cdnGraphHeight) - 20.0f;
        Paint paint = this.bandwidthPaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bandwidthPaint");
            paint = null;
        }
        canvas.drawText(str, f, f2, paint);
    }

    private final void drawLatencyText(Canvas canvas, DiagnosticDataCollector collector) {
        String str = "Avg Latency: ";
        if (!collector.getAggregatedDataPoints().isEmpty()) {
            str = String.format("%s%s ms", Arrays.copyOf(new Object[]{"Avg Latency: ", Integer.valueOf(collector.getAggregatedDataPoints().last().getAggregatedLatencyMillis())}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        }
        Rect rect = new Rect();
        Paint paint = this.latencyPaint;
        Paint paint2 = null;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("latencyPaint");
            paint = null;
        }
        paint.getTextBounds(str, 0, str.length(), rect);
        float fAbs = ((this.mOrigin.x + this.cdnGraphWidth) - Math.abs(rect.left - rect.right)) - 3.0f;
        float f = (this.mOrigin.y - this.cdnGraphHeight) - 20.0f;
        Paint paint3 = this.latencyPaint;
        if (paint3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("latencyPaint");
        } else {
            paint2 = paint3;
        }
        canvas.drawText(str, fAbs, f, paint2);
    }

    private final void drawCdnText(Canvas canvas, DiagnosticDataCollector collector) {
        ContentUrlSelector contentUrlSelector = collector.getContext().getContentUrlSelector();
        String strStringPlus = contentUrlSelector != null ? Intrinsics.stringPlus("CDN: ", contentUrlSelector.getCurrentContentUrl().getCdnName()) : "CDN: ";
        Rect rect = new Rect();
        Paint paint = this.cdnPaint;
        Paint paint2 = null;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdnPaint");
            paint = null;
        }
        paint.getTextBounds(strStringPlus, 0, strStringPlus.length(), rect);
        float fAbs = (this.mOrigin.x + (this.cdnGraphWidth / 2)) - (Math.abs(rect.left - rect.right) / 2);
        float f = (this.mOrigin.y - this.cdnGraphHeight) - 40.0f;
        Paint paint3 = this.cdnPaint;
        if (paint3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdnPaint");
        } else {
            paint2 = paint3;
        }
        canvas.drawText(strStringPlus, fAbs, f, paint2);
    }

    private final void drawLabels(Canvas canvas, long originMilliseconds) {
        float f = this.mOrigin.x;
        Paint paint = this.axisTextPaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
            paint = null;
        }
        paint.setTextAlign(Paint.Align.CENTER);
        int i = 0;
        while (i < 10) {
            i++;
            PointF pointF = new PointF(f, this.mOrigin.y);
            String string = TimeSpan.fromMilliseconds(originMilliseconds).toString(TimeUnit.SECONDS);
            Paint paint2 = this.axisTextPaint;
            if (paint2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
                paint2 = null;
            }
            drawXAxisLabel(canvas, pointF, string, paint2);
            originMilliseconds += 30000;
            f += (this.cdnGraphWidth - 5.0f) / 10;
        }
    }

    static {
        int iRgb = Color.rgb(238, 130, 238);
        COLOR_VIOLET = iRgb;
        int iRgb2 = Color.rgb(135, ComposerKt.referenceKey, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        COLOR_SKY_BLUE = iRgb2;
        COLOR_LATENCY = iRgb;
        COLOR_BANDWIDTH = iRgb2;
        COLOR_QOE = iRgb2;
    }

    /* JADX INFO: compiled from: CdnGraphPlotter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/CdnGraphPlotter$CdnGraphConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "()V", "graphType", "Lcom/amazon/avod/playback/smoothstream/diagnostics/CdnGraphPlotter$CdnGraphType;", "getGraphType", "()Lcom/amazon/avod/playback/smoothstream/diagnostics/CdnGraphPlotter$CdnGraphType;", "maxBandwidth", "", "getMaxBandwidth", "()I", "maxLatency", "getMaxLatency", "maxQoe", "", "getMaxQoe", "()F", "minBandwidth", "getMinBandwidth", "minLatency", "getMinLatency", "minQoe", "getMinQoe", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class CdnGraphConfig extends MediaConfigBase {
        public static final CdnGraphConfig INSTANCE;
        private static final CdnGraphType graphType;
        private static final int maxBandwidth;
        private static final int maxLatency;
        private static final float maxQoe;
        private static final int minBandwidth;
        private static final int minLatency;
        private static final float minQoe;

        private CdnGraphConfig() {
        }

        static {
            CdnGraphConfig cdnGraphConfig = new CdnGraphConfig();
            INSTANCE = cdnGraphConfig;
            Object value = cdnGraphConfig.newEnumConfigValue("cdnGraph_graphType", CdnGraphType.SHOW_QOE, CdnGraphType.class).getValue();
            Intrinsics.checkNotNullExpressionValue(value, "newEnumConfigValue(\"cdnG…phType::class.java).value");
            graphType = (CdnGraphType) value;
            Integer value2 = cdnGraphConfig.newIntConfigValue("cdnGraph_maxLatency", 500).getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "newIntConfigValue(\"cdnGr…ency\", MAX_LATENCY).value");
            maxLatency = value2.intValue();
            Integer value3 = cdnGraphConfig.newIntConfigValue("cdnGraph_maxLatency", 0).getValue();
            Intrinsics.checkNotNullExpressionValue(value3, "newIntConfigValue(\"cdnGr…ency\", MIN_LATENCY).value");
            minLatency = value3.intValue();
            Integer value4 = cdnGraphConfig.newIntConfigValue("cdnGraph_maxBandwidth", 50000).getValue();
            Intrinsics.checkNotNullExpressionValue(value4, "newIntConfigValue(\"cdnGr…th\", MAX_BANDWIDTH).value");
            maxBandwidth = value4.intValue();
            Integer value5 = cdnGraphConfig.newIntConfigValue("cdnGraph_minBandwidth", 0).getValue();
            Intrinsics.checkNotNullExpressionValue(value5, "newIntConfigValue(\"cdnGr…th\", MIN_BANDWIDTH).value");
            minBandwidth = value5.intValue();
            Float value6 = cdnGraphConfig.newFloatConfigValue("cdnGraph_maxQoe", 3.0f).getValue();
            Intrinsics.checkNotNullExpressionValue(value6, "newFloatConfigValue(\"cdn…h_maxQoe\", MAX_QOE).value");
            maxQoe = value6.floatValue();
            Float value7 = cdnGraphConfig.newFloatConfigValue("cdnGraph_minQoe", -3.0f).getValue();
            Intrinsics.checkNotNullExpressionValue(value7, "newFloatConfigValue(\"cdn…h_minQoe\", MIN_QOE).value");
            minQoe = value7.floatValue();
        }

        public final CdnGraphType getGraphType() {
            return graphType;
        }

        public final int getMaxLatency() {
            return maxLatency;
        }

        public final int getMinLatency() {
            return minLatency;
        }

        public final int getMaxBandwidth() {
            return maxBandwidth;
        }

        public final int getMinBandwidth() {
            return minBandwidth;
        }

        public final float getMaxQoe() {
            return maxQoe;
        }

        public final float getMinQoe() {
            return minQoe;
        }
    }
}
