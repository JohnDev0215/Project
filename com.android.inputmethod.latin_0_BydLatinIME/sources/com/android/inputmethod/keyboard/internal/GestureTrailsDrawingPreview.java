package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.util.SparseArray;
import com.android.inputmethod.keyboard.PointerTracker;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureTrailsDrawingPreview extends AbstractDrawingPreview implements Runnable {
    private final GestureTrailDrawingParams mDrawingParams;
    private final Paint mGesturePaint;
    private Bitmap mOffscreenBuffer;
    private int mOffscreenHeight;
    private int mOffscreenOffsetY;
    private int mOffscreenWidth;
    private final SparseArray<GestureTrailDrawingPoints> mGestureTrails = new SparseArray<>();
    private final Canvas mOffscreenCanvas = new Canvas();
    private final Rect mOffscreenSrcRect = new Rect();
    private final Rect mDirtyRect = new Rect();
    private final Rect mGestureTrailBoundsRect = new Rect();
    private final Handler mDrawingHandler = new Handler();

    public GestureTrailsDrawingPreview(final TypedArray mainKeyboardViewAttr) {
        this.mDrawingParams = new GestureTrailDrawingParams(mainKeyboardViewAttr);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        this.mGesturePaint = paint;
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void setKeyboardViewGeometry(final int[] originCoords, final int width, final int height) {
        super.setKeyboardViewGeometry(originCoords, width, height);
        int i = (int) (height * 0.25f);
        this.mOffscreenOffsetY = i;
        this.mOffscreenWidth = width;
        this.mOffscreenHeight = i + height;
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void onDeallocateMemory() {
        freeOffscreenBuffer();
    }

    private void freeOffscreenBuffer() {
        this.mOffscreenCanvas.setBitmap(null);
        this.mOffscreenCanvas.setMatrix(null);
        Bitmap bitmap = this.mOffscreenBuffer;
        if (bitmap != null) {
            bitmap.recycle();
            this.mOffscreenBuffer = null;
        }
    }

    private void mayAllocateOffscreenBuffer() {
        Bitmap bitmap = this.mOffscreenBuffer;
        if (bitmap != null && bitmap.getWidth() == this.mOffscreenWidth && this.mOffscreenBuffer.getHeight() == this.mOffscreenHeight) {
            return;
        }
        freeOffscreenBuffer();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.mOffscreenWidth, this.mOffscreenHeight, Bitmap.Config.ARGB_8888);
        this.mOffscreenBuffer = bitmapCreateBitmap;
        this.mOffscreenCanvas.setBitmap(bitmapCreateBitmap);
        this.mOffscreenCanvas.translate(0.0f, this.mOffscreenOffsetY);
    }

    private boolean drawGestureTrails(final Canvas offscreenCanvas, final Paint paint, final Rect dirtyRect) {
        boolean zDrawGestureTrail;
        if (!dirtyRect.isEmpty()) {
            paint.setColor(0);
            paint.setStyle(Paint.Style.FILL);
            offscreenCanvas.drawRect(dirtyRect, paint);
        }
        dirtyRect.setEmpty();
        synchronized (this.mGestureTrails) {
            int size = this.mGestureTrails.size();
            zDrawGestureTrail = false;
            for (int i = 0; i < size; i++) {
                zDrawGestureTrail |= this.mGestureTrails.valueAt(i).drawGestureTrail(offscreenCanvas, paint, this.mGestureTrailBoundsRect, this.mDrawingParams);
                dirtyRect.union(this.mGestureTrailBoundsRect);
            }
        }
        return zDrawGestureTrail;
    }

    @Override // java.lang.Runnable
    public void run() {
        invalidateDrawingView();
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void drawPreview(final Canvas canvas) {
        if (isPreviewEnabled()) {
            mayAllocateOffscreenBuffer();
            if (drawGestureTrails(this.mOffscreenCanvas, this.mGesturePaint, this.mDirtyRect)) {
                this.mDrawingHandler.removeCallbacks(this);
                this.mDrawingHandler.postDelayed(this, this.mDrawingParams.mUpdateInterval);
            }
            if (this.mDirtyRect.isEmpty()) {
                return;
            }
            this.mOffscreenSrcRect.set(this.mDirtyRect);
            this.mOffscreenSrcRect.offset(0, this.mOffscreenOffsetY);
            canvas.drawBitmap(this.mOffscreenBuffer, this.mOffscreenSrcRect, this.mDirtyRect, (Paint) null);
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void setPreviewPosition(final PointerTracker tracker) {
        GestureTrailDrawingPoints gestureTrailDrawingPoints;
        if (isPreviewEnabled()) {
            synchronized (this.mGestureTrails) {
                gestureTrailDrawingPoints = this.mGestureTrails.get(tracker.mPointerId);
                if (gestureTrailDrawingPoints == null) {
                    gestureTrailDrawingPoints = new GestureTrailDrawingPoints();
                    this.mGestureTrails.put(tracker.mPointerId, gestureTrailDrawingPoints);
                }
            }
            gestureTrailDrawingPoints.addStroke(tracker.getGestureStrokeDrawingPoints(), tracker.getDownTime());
            invalidateDrawingView();
        }
    }
}
