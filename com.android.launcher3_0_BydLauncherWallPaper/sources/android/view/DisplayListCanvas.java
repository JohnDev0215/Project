package android.view;

import android.graphics.BaseRecordingCanvas;
import android.graphics.CanvasProperty;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DisplayListCanvas extends BaseRecordingCanvas {
    public abstract void drawCircle(CanvasProperty<Float> canvasProperty, CanvasProperty<Float> canvasProperty2, CanvasProperty<Float> canvasProperty3, CanvasProperty<Paint> canvasProperty4);

    public abstract void drawRoundRect(CanvasProperty<Float> canvasProperty, CanvasProperty<Float> canvasProperty2, CanvasProperty<Float> canvasProperty3, CanvasProperty<Float> canvasProperty4, CanvasProperty<Float> canvasProperty5, CanvasProperty<Float> canvasProperty6, CanvasProperty<Paint> canvasProperty7);

    protected DisplayListCanvas(long j) {
        super(j);
    }
}
