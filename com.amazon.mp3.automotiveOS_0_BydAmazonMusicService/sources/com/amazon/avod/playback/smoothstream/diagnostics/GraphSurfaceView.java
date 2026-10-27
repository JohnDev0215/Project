package com.amazon.avod.playback.smoothstream.diagnostics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class GraphSurfaceView extends ImageView implements DiagnosticUpdateListener {
    private final BandwidthGraphPlotter mBandwidthPlotter;
    private final BufferGraphPlotter mBufferPlotter;
    private Canvas mCanvas;
    private final DebugGraphViewConfig mDebugGraphViewConfig;
    private final List<GraphPlotter> mGraphList;

    public GraphSurfaceView(Context context) {
        this(context, new BandwidthGraphPlotter(), new BufferGraphPlotter(), DebugGraphViewConfig.INSTANCE);
    }

    GraphSurfaceView(@Nonnull Context context, @Nonnull BandwidthGraphPlotter bandwidthGraphPlotter, @Nonnull BufferGraphPlotter bufferGraphPlotter, @Nonnull DebugGraphViewConfig debugGraphViewConfig) {
        super(context);
        this.mGraphList = Lists.newArrayList();
        this.mBandwidthPlotter = (BandwidthGraphPlotter) Preconditions.checkNotNull(bandwidthGraphPlotter, "bandwidthPlotter");
        this.mBufferPlotter = (BufferGraphPlotter) Preconditions.checkNotNull(bufferGraphPlotter, "bufferPlotter");
        this.mDebugGraphViewConfig = (DebugGraphViewConfig) Preconditions.checkNotNull(debugGraphViewConfig, "debugGraphViewConfig");
    }

    public void init(int i, int i2, boolean z) {
        float f;
        float regularViewStartXOffset;
        float f2;
        float regularViewStartYOffset;
        float f3;
        float regularBandwidthGraphHeightFactor;
        float f4;
        float regularBufferGraphHeightFactor;
        setImageAlpha(0);
        if (z) {
            f = i;
            regularViewStartXOffset = this.mDebugGraphViewConfig.getCompactViewStartXOffset();
        } else {
            f = i;
            regularViewStartXOffset = this.mDebugGraphViewConfig.getRegularViewStartXOffset();
        }
        float f5 = f * regularViewStartXOffset;
        if (z) {
            f2 = i2;
            regularViewStartYOffset = this.mDebugGraphViewConfig.getCompactViewStartYOffset();
        } else {
            f2 = i2;
            regularViewStartYOffset = this.mDebugGraphViewConfig.getRegularViewStartYOffset();
        }
        float f6 = f2 * regularViewStartYOffset;
        if (z) {
            f3 = i2;
            regularBandwidthGraphHeightFactor = this.mDebugGraphViewConfig.getCompactBandwidthGraphHeightFactor();
        } else {
            f3 = i2;
            regularBandwidthGraphHeightFactor = this.mDebugGraphViewConfig.getRegularBandwidthGraphHeightFactor();
        }
        float f7 = f3 * regularBandwidthGraphHeightFactor;
        if (z) {
            f4 = i2;
            regularBufferGraphHeightFactor = this.mDebugGraphViewConfig.getCompactBufferGraphHeightFactor();
        } else {
            f4 = i2;
            regularBufferGraphHeightFactor = this.mDebugGraphViewConfig.getRegularBufferGraphHeightFactor();
        }
        float f8 = f4 * regularBufferGraphHeightFactor;
        float compactViewGraphWidthFactor = z ? i * this.mDebugGraphViewConfig.getCompactViewGraphWidthFactor() : i;
        BandwidthGraphPlotter bandwidthGraphPlotter = this.mBandwidthPlotter;
        PointF pointF = new PointF(f5, f6);
        float f9 = compactViewGraphWidthFactor + f5;
        float f10 = f6 - f7;
        bandwidthGraphPlotter.init(pointF, new PointF(f9, f10), z);
        this.mBufferPlotter.init(new PointF(f5, f10), new PointF(f9, f10 - f8), z);
        this.mGraphList.add(this.mBandwidthPlotter);
        this.mGraphList.add(this.mBufferPlotter);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setDensity(0);
        this.mCanvas = new Canvas(bitmapCreateBitmap);
        setBackground(new BitmapDrawable(getResources(), bitmapCreateBitmap));
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticUpdateListener
    public void diagnosticDataUpdated(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        Preconditions.checkNotNull(diagnosticDataCollector, "collector");
        synchronized (this.mCanvas) {
            drawLoop(this.mCanvas, diagnosticDataCollector);
            invalidate();
        }
    }

    public void setAxisScaleForBandWidth(DiagnosticsController.BandwidthScale bandwidthScale) {
        this.mBandwidthPlotter.setAxisScale(bandwidthScale);
    }

    private void drawLoop(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Iterator<GraphPlotter> it = this.mGraphList.iterator();
        while (it.hasNext()) {
            it.next().draw(canvas, diagnosticDataCollector);
        }
    }
}
