package android.graphics;

import android.util.Pools;
import android.view.DisplayListCanvas;
import android.view.TextureLayer;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;

/* JADX INFO: loaded from: classes.dex */
public final class RecordingCanvas extends DisplayListCanvas {
    public static final int MAX_BITMAP_SIZE = 104857600;
    private static final int POOL_LIMIT = 25;
    private static final Pools.SynchronizedPool<RecordingCanvas> sPool = new Pools.SynchronizedPool<>(25);
    private int mHeight;
    public RenderNode mNode;
    private int mWidth;

    @FastNative
    private static native void nCallDrawGLFunction(long j, long j2, Runnable runnable);

    @CriticalNative
    private static native long nCreateDisplayListCanvas(long j, int i, int i2);

    @CriticalNative
    private static native void nDrawCircle(long j, long j2, long j3, long j4, long j5);

    @CriticalNative
    private static native void nDrawRenderNode(long j, long j2);

    @CriticalNative
    private static native void nDrawRoundRect(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    @CriticalNative
    private static native void nDrawTextureLayer(long j, long j2);

    @CriticalNative
    private static native void nDrawWebViewFunctor(long j, int i);

    @CriticalNative
    private static native long nFinishRecording(long j);

    @CriticalNative
    private static native int nGetMaximumTextureHeight();

    @CriticalNative
    private static native int nGetMaximumTextureWidth();

    @CriticalNative
    private static native void nInsertReorderBarrier(long j, boolean z);

    @CriticalNative
    private static native void nResetDisplayListCanvas(long j, long j2, int i, int i2);

    @Override // android.graphics.Canvas, android.graphics.BaseCanvas
    public boolean isHardwareAccelerated() {
        return true;
    }

    @Override // android.graphics.Canvas
    public boolean isOpaque() {
        return false;
    }

    @Override // android.graphics.Canvas
    public void setDensity(int i) {
    }

    static RecordingCanvas obtain(RenderNode renderNode, int i, int i2) {
        if (renderNode == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
        RecordingCanvas recordingCanvasAcquire = sPool.acquire();
        if (recordingCanvasAcquire == null) {
            recordingCanvasAcquire = new RecordingCanvas(renderNode, i, i2);
        } else {
            nResetDisplayListCanvas(recordingCanvasAcquire.mNativeCanvasWrapper, renderNode.mNativeRenderNode, i, i2);
        }
        recordingCanvasAcquire.mNode = renderNode;
        recordingCanvasAcquire.mWidth = i;
        recordingCanvasAcquire.mHeight = i2;
        return recordingCanvasAcquire;
    }

    void recycle() {
        this.mNode = null;
        sPool.release(this);
    }

    long finishRecording() {
        return nFinishRecording(this.mNativeCanvasWrapper);
    }

    @Override // android.graphics.Canvas
    public boolean isRecordingFor(Object obj) {
        return obj == this.mNode;
    }

    protected RecordingCanvas(RenderNode renderNode, int i, int i2) {
        super(nCreateDisplayListCanvas(renderNode.mNativeRenderNode, i, i2));
        this.mDensity = 0;
    }

    @Override // android.graphics.Canvas
    public void setBitmap(Bitmap bitmap) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.Canvas
    public int getWidth() {
        return this.mWidth;
    }

    @Override // android.graphics.Canvas
    public int getHeight() {
        return this.mHeight;
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapWidth() {
        return nGetMaximumTextureWidth();
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapHeight() {
        return nGetMaximumTextureHeight();
    }

    @Override // android.graphics.Canvas
    public void enableZ() {
        nInsertReorderBarrier(this.mNativeCanvasWrapper, true);
    }

    @Override // android.graphics.Canvas
    public void disableZ() {
        nInsertReorderBarrier(this.mNativeCanvasWrapper, false);
    }

    @Deprecated
    public void callDrawGLFunction2(long j) {
        nCallDrawGLFunction(this.mNativeCanvasWrapper, j, null);
    }

    @Deprecated
    public void drawGLFunctor2(long j, Runnable runnable) {
        nCallDrawGLFunction(this.mNativeCanvasWrapper, j, runnable);
    }

    public void drawWebViewFunctor(int i) {
        nDrawWebViewFunctor(this.mNativeCanvasWrapper, i);
    }

    @Override // android.graphics.Canvas
    public void drawRenderNode(RenderNode renderNode) {
        nDrawRenderNode(this.mNativeCanvasWrapper, renderNode.mNativeRenderNode);
    }

    public void drawTextureLayer(TextureLayer textureLayer) {
        nDrawTextureLayer(this.mNativeCanvasWrapper, textureLayer.getLayerHandle());
    }

    @Override // android.view.DisplayListCanvas
    public void drawCircle(CanvasProperty<Float> canvasProperty, CanvasProperty<Float> canvasProperty2, CanvasProperty<Float> canvasProperty3, CanvasProperty<Paint> canvasProperty4) {
        nDrawCircle(this.mNativeCanvasWrapper, canvasProperty.getNativeContainer(), canvasProperty2.getNativeContainer(), canvasProperty3.getNativeContainer(), canvasProperty4.getNativeContainer());
    }

    @Override // android.view.DisplayListCanvas
    public void drawRoundRect(CanvasProperty<Float> canvasProperty, CanvasProperty<Float> canvasProperty2, CanvasProperty<Float> canvasProperty3, CanvasProperty<Float> canvasProperty4, CanvasProperty<Float> canvasProperty5, CanvasProperty<Float> canvasProperty6, CanvasProperty<Paint> canvasProperty7) {
        nDrawRoundRect(this.mNativeCanvasWrapper, canvasProperty.getNativeContainer(), canvasProperty2.getNativeContainer(), canvasProperty3.getNativeContainer(), canvasProperty4.getNativeContainer(), canvasProperty5.getNativeContainer(), canvasProperty6.getNativeContainer(), canvasProperty7.getNativeContainer());
    }

    @Override // android.graphics.BaseCanvas
    protected void throwIfCannotDraw(Bitmap bitmap) {
        super.throwIfCannotDraw(bitmap);
        int byteCount = bitmap.getByteCount();
        if (byteCount > 104857600) {
            throw new RuntimeException("Canvas: trying to draw too large(" + byteCount + "bytes) bitmap.");
        }
    }
}
