package com.amazon.avod.playback.smoothstream.diagnostics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QualityScoreView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScoreView;", "Landroid/widget/ImageView;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticUpdateListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "qualityScorePlotter", "Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScorePlotter;", "(Landroid/content/Context;Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScorePlotter;)V", "mCanvas", "Landroid/graphics/Canvas;", "diagnosticDataUpdated", "", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "draw", "canvas", "init", "screenWidth", "", "screenHeight", "Companion", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QualityScoreView extends ImageView implements DiagnosticUpdateListener {
    private Canvas mCanvas;
    private final QualityScorePlotter qualityScorePlotter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QualityScoreView(Context context, QualityScorePlotter qualityScorePlotter) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(qualityScorePlotter, "qualityScorePlotter");
        this.qualityScorePlotter = qualityScorePlotter;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QualityScoreView(Context context) {
        this(context, new QualityScorePlotter());
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void init(int screenWidth, int screenHeight) {
        setImageAlpha(0);
        float f = screenHeight;
        float f2 = 0.99f * f;
        float f3 = screenWidth;
        float f4 = 0.35f * f3;
        float f5 = f3 - 50.0f;
        this.qualityScorePlotter.init(new PointF(f5 - f4, f2), new PointF(f5, f2 - (f * 0.35f)), false);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setDensity(0);
        this.mCanvas = new Canvas(bitmapCreateBitmap);
        setBackground(new BitmapDrawable(getResources(), bitmapCreateBitmap));
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticUpdateListener
    public void diagnosticDataUpdated(DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        Canvas canvas = this.mCanvas;
        Canvas canvas2 = null;
        if (canvas == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanvas");
            canvas = null;
        }
        synchronized (canvas) {
            Canvas canvas3 = this.mCanvas;
            if (canvas3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanvas");
            } else {
                canvas2 = canvas3;
            }
            draw(canvas2, collector);
            invalidate();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void draw(Canvas canvas, DiagnosticDataCollector collector) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.qualityScorePlotter.draw(canvas, collector);
    }
}
