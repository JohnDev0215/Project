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
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QualityScorePlotter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 /2\u00020\u0001:\u0002/0B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!H\u0002J&\u0010\"\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J\u0018\u0010(\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010)\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J$\u0010*\u001a\u00020\u00182\b\u0010+\u001a\u0004\u0018\u00010\u00132\b\u0010,\u001a\u0004\u0018\u00010\u00132\u0006\u0010-\u001a\u00020.H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScorePlotter;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/GraphPlotter;", "()V", "avgBitratePaint", "Landroid/graphics/Paint;", "axisLinePaint", "axisTextPaint", "config", "Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScorePlotter$QualityScoreConfig;", "maxQualityScore", "", "minQualityScore", "qualityScoreHeight", "", "qualityScorePaint", "qualityScorePointPaint", "qualityScoreWidth", "rectPaint", "createQualityScorePoint", "Landroid/graphics/PointF;", "xPointLocation", "", "qualityScore", "draw", "", "canvas", "Landroid/graphics/Canvas;", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "drawAxes", "drawGraphs", "drawLabels", "originMilliseconds", "", "drawQualityScore", "playHeadTimeStamp", "Lcom/amazon/avod/media/TimeSpan;", "qualityScoreDataPoints", "Ljava/util/TreeSet;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScoreDataPoint;", "drawTitle", "drawVideoBitrateText", "init", "lowerLeft", "upperRight", "isCompactViewEnabled", "", "Companion", "QualityScoreConfig", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QualityScorePlotter extends GraphPlotter {
    private static final int COLOR_QUALITY_SCORE;
    private static final int COLOR_SKY_BLUE;
    private static final int COLOR_VIOLET;
    private Paint avgBitratePaint;
    private Paint axisLinePaint;
    private Paint axisTextPaint;
    private final QualityScoreConfig config = QualityScoreConfig.INSTANCE;
    private final double maxQualityScore;
    private final double minQualityScore;
    private int qualityScoreHeight;
    private Paint qualityScorePaint;
    private Paint qualityScorePointPaint;
    private int qualityScoreWidth;
    private Paint rectPaint;

    public QualityScorePlotter() {
        this.maxQualityScore = r0.getMaxQualityScore();
        this.minQualityScore = r0.getMinQualityScore();
    }

    public void init(PointF lowerLeft, PointF upperRight, boolean isCompactViewEnabled) {
        super.initBase(lowerLeft, upperRight, 30);
        this.qualityScoreHeight = (getHeight() - 30) - 100;
        this.qualityScoreWidth = (getWidth() - 50) + 0;
        Paint paint = new Paint();
        this.qualityScorePaint = paint;
        paint.setColor(-1);
        Paint paint2 = this.qualityScorePaint;
        Paint paint3 = null;
        if (paint2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qualityScorePaint");
            paint2 = null;
        }
        paint2.setTextSize(25.0f);
        Paint paint4 = this.qualityScorePaint;
        if (paint4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qualityScorePaint");
            paint4 = null;
        }
        paint4.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        Paint paint5 = new Paint();
        this.qualityScorePointPaint = paint5;
        paint5.setColor(COLOR_QUALITY_SCORE);
        Paint paint6 = this.qualityScorePointPaint;
        if (paint6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qualityScorePointPaint");
            paint6 = null;
        }
        paint6.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint7 = new Paint();
        this.axisTextPaint = paint7;
        paint7.setColor(-1);
        Paint paint8 = this.axisTextPaint;
        if (paint8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
            paint8 = null;
        }
        paint8.setTextSize(14.0f);
        Paint paint9 = new Paint();
        this.rectPaint = paint9;
        paint9.setColor(ViewCompat.MEASURED_STATE_MASK);
        Paint paint10 = this.rectPaint;
        if (paint10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rectPaint");
            paint10 = null;
        }
        paint10.setAlpha(100);
        Paint paint11 = this.rectPaint;
        if (paint11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rectPaint");
            paint11 = null;
        }
        paint11.setStyle(Paint.Style.FILL);
        Paint paint12 = new Paint();
        this.axisLinePaint = paint12;
        paint12.setColor(-7829368);
        Paint paint13 = this.axisLinePaint;
        if (paint13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisLinePaint");
            paint13 = null;
        }
        paint13.setStrokeWidth(2.0f);
        Paint paint14 = new Paint();
        this.avgBitratePaint = paint14;
        paint14.setColor(COLOR_VIOLET);
        Paint paint15 = this.avgBitratePaint;
        if (paint15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("avgBitratePaint");
            paint15 = null;
        }
        paint15.setTypeface(Typeface.MONOSPACE);
        Paint paint16 = this.avgBitratePaint;
        if (paint16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("avgBitratePaint");
        } else {
            paint3 = paint16;
        }
        paint3.setTextSize(20.0f);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.GraphPlotter
    public void draw(Canvas canvas, DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        drawAxes(canvas);
        if (collector == null) {
            return;
        }
        drawTitle(canvas, collector);
        drawVideoBitrateText(canvas, collector);
        drawGraphs(canvas, collector);
    }

    private final void drawGraphs(Canvas canvas, DiagnosticDataCollector collector) {
        TimeSpan timeSpan = new TimeSpan(collector.getPlayHeadInNanos());
        TreeSet<QualityScoreDataPoint> qualityScoreDataPoints = collector.getQualityScoreDataPoints();
        Intrinsics.checkNotNullExpressionValue(qualityScoreDataPoints, "qualityScoreDataPoints");
        synchronized (qualityScoreDataPoints) {
            if (!qualityScoreDataPoints.isEmpty()) {
                drawQualityScore(canvas, timeSpan, qualityScoreDataPoints);
                drawLabels(canvas, ((QualityScoreDataPoint) findStartingIterator(qualityScoreDataPoints, timeSpan).next()).getTimeStamp().getTotalMilliseconds());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void drawQualityScore(Canvas canvas, TimeSpan playHeadTimeStamp, TreeSet<QualityScoreDataPoint> qualityScoreDataPoints) {
        Paint paint;
        Paint paint2;
        Iterator iterator = findStartingIterator(qualityScoreDataPoints, playHeadTimeStamp);
        QualityScoreDataPoint qualityScoreDataPoint = (QualityScoreDataPoint) iterator.next();
        long totalMilliseconds = qualityScoreDataPoint.getTimeStamp().getTotalMilliseconds();
        PointF pointFCreateQualityScorePoint = createQualityScorePoint(getXLocationFromTimeStamp(qualityScoreDataPoint.getTimeStamp(), totalMilliseconds), qualityScoreDataPoint.getQualityScore());
        Intrinsics.checkNotNullExpressionValue(iterator, "iterator");
        while (true) {
            PointF pointF = pointFCreateQualityScorePoint;
            while (iterator.hasNext()) {
                QualityScoreDataPoint qualityScoreDataPoint2 = (QualityScoreDataPoint) iterator.next();
                float xLocationFromTimeStamp = getXLocationFromTimeStamp(qualityScoreDataPoint2.getTimeStamp(), totalMilliseconds);
                float f = this.mFirstXLocation;
                if (xLocationFromTimeStamp > f && xLocationFromTimeStamp < f + this.qualityScoreWidth) {
                    PointF pointFCreateQualityScorePoint2 = createQualityScorePoint(xLocationFromTimeStamp, qualityScoreDataPoint2.getQualityScore());
                    Paint paint3 = this.qualityScorePaint;
                    if (paint3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qualityScorePaint");
                        paint = null;
                    } else {
                        paint = paint3;
                    }
                    Paint paint4 = this.qualityScorePointPaint;
                    if (paint4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qualityScorePointPaint");
                        paint2 = null;
                    } else {
                        paint2 = paint4;
                    }
                    pointFCreateQualityScorePoint = drawLineSegment(canvas, pointFCreateQualityScorePoint2, pointF, paint, paint2);
                    Intrinsics.checkNotNullExpressionValue(pointFCreateQualityScorePoint, "drawLineSegment(canvas, …, qualityScorePointPaint)");
                }
            }
            return;
        }
    }

    private final PointF createQualityScorePoint(float xPointLocation, double qualityScore) {
        double dMin = Math.min(this.maxQualityScore, qualityScore);
        double d = this.mOrigin.y;
        double d2 = this.minQualityScore;
        return new PointF(xPointLocation, (float) (d - ((((double) ((float) (dMin - d2))) / (this.maxQualityScore - d2)) * ((double) this.qualityScoreHeight))));
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
            f += (this.qualityScoreWidth - 5.0f) / 10;
        }
    }

    private final void drawTitle(Canvas canvas, DiagnosticDataCollector collector) {
        String strStringPlus = Intrinsics.stringPlus("Target Quality Score:", Double.valueOf(collector.getMaxQualityScore()));
        Rect rect = new Rect();
        Paint paint = this.qualityScorePaint;
        Paint paint2 = null;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qualityScorePaint");
            paint = null;
        }
        paint.getTextBounds(strStringPlus, 0, strStringPlus.length(), rect);
        float fAbs = (this.mOrigin.x + (this.qualityScoreWidth / 2)) - (Math.abs(rect.left - rect.right) / 2);
        float f = (this.mOrigin.y - this.qualityScoreHeight) - 50.0f;
        Paint paint3 = this.qualityScorePaint;
        if (paint3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qualityScorePaint");
        } else {
            paint2 = paint3;
        }
        canvas.drawText(strStringPlus, fAbs, f, paint2);
    }

    private final void drawVideoBitrateText(Canvas canvas, DiagnosticDataCollector collector) {
        String str = "Avg Media Bitrate";
        if (!collector.getQualityScoreDataPoints().isEmpty()) {
            str = String.format("%s: %s Mbps", Arrays.copyOf(new Object[]{"Avg Media Bitrate", Float.valueOf(collector.getQualityScoreDataPoints().last().getAvgMediaBitrateMbps())}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        }
        PointF pointF = this.mOrigin;
        float f = pointF.x;
        float f2 = (pointF.y - this.qualityScoreHeight) - 20.0f;
        Paint paint = this.avgBitratePaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("avgBitratePaint");
            paint = null;
        }
        canvas.drawText(str, f, f2, paint);
    }

    private final void drawAxes(Canvas canvas) {
        Paint paint;
        Paint paint2;
        Paint paint3;
        PointF pointF = this.mOrigin;
        float f = 50;
        float f2 = pointF.x - f;
        float f3 = 30;
        float height = (pointF.y + f3) - getHeight();
        float width = (this.mOrigin.x - f) + getWidth();
        float f4 = this.mOrigin.y + f3;
        Paint paint4 = this.rectPaint;
        if (paint4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rectPaint");
            paint = null;
        } else {
            paint = paint4;
        }
        canvas.drawRect(f2, height, width, f4, paint);
        Paint paint5 = this.axisTextPaint;
        if (paint5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
            paint5 = null;
        }
        paint5.setTextAlign(Paint.Align.RIGHT);
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            double d = this.minQualityScore;
            String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((float) (d + (((this.maxQualityScore - d) / ((double) 15)) * ((double) i))))}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
            PointF pointF2 = new PointF(this.mOrigin.x, this.mOrigin.y - ((i / 15) * this.qualityScoreHeight));
            Paint paint6 = this.axisLinePaint;
            if (paint6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("axisLinePaint");
                paint2 = null;
            } else {
                paint2 = paint6;
            }
            Paint paint7 = this.axisTextPaint;
            if (paint7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("axisTextPaint");
                paint3 = null;
            } else {
                paint3 = paint7;
            }
            drawHorizontalAxis(canvas, pointF2, str, "", paint2, paint3);
            i = i2;
        }
    }

    static {
        int iRgb = Color.rgb(135, ComposerKt.referenceKey, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        COLOR_SKY_BLUE = iRgb;
        COLOR_QUALITY_SCORE = iRgb;
        COLOR_VIOLET = Color.rgb(238, 130, 238);
    }

    /* JADX INFO: compiled from: QualityScorePlotter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScorePlotter$QualityScoreConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "()V", "maxQualityScore", "", "getMaxQualityScore", "()F", "minQualityScore", "getMinQualityScore", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class QualityScoreConfig extends MediaConfigBase {
        public static final QualityScoreConfig INSTANCE;
        private static final float maxQualityScore;
        private static final float minQualityScore;

        private QualityScoreConfig() {
        }

        static {
            QualityScoreConfig qualityScoreConfig = new QualityScoreConfig();
            INSTANCE = qualityScoreConfig;
            Float value = qualityScoreConfig.newFloatConfigValue("qualityScore_maxScore", 100.0f).getValue();
            Intrinsics.checkNotNullExpressionValue(value, "newFloatConfigValue(\"qua… MAX_QUALITY_SCORE).value");
            maxQualityScore = value.floatValue();
            Float value2 = qualityScoreConfig.newFloatConfigValue("qualityScore_minScore", 80.0f).getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "newFloatConfigValue(\"qua… MIN_QUALITY_SCORE).value");
            minQualityScore = value2.floatValue();
        }

        public final float getMaxQualityScore() {
            return maxQualityScore;
        }

        public final float getMinQualityScore() {
            return minQualityScore;
        }
    }
}
