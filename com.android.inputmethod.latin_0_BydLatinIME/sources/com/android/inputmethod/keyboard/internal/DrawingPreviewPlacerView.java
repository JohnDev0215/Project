package com.android.inputmethod.keyboard.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.RelativeLayout;
import com.android.inputmethod.latin.common.CoordinateUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class DrawingPreviewPlacerView extends RelativeLayout {
    private final int[] mKeyboardViewOrigin;
    private final ArrayList<AbstractDrawingPreview> mPreviews;

    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public DrawingPreviewPlacerView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.mKeyboardViewOrigin = CoordinateUtils.newInstance();
        this.mPreviews = new ArrayList<>();
        setWillNotDraw(false);
    }

    public void setHardwareAcceleratedDrawingEnabled(final boolean enabled) {
        if (enabled) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            setLayerType(2, paint);
        }
    }

    public void addPreview(final AbstractDrawingPreview preview) {
        if (this.mPreviews.indexOf(preview) < 0) {
            this.mPreviews.add(preview);
        }
    }

    public void setKeyboardViewGeometry(final int[] originCoords, final int width, final int height) {
        CoordinateUtils.copy(this.mKeyboardViewOrigin, originCoords);
        int size = this.mPreviews.size();
        for (int i = 0; i < size; i++) {
            this.mPreviews.get(i).setKeyboardViewGeometry(originCoords, width, height);
        }
    }

    public void deallocateMemory() {
        int size = this.mPreviews.size();
        for (int i = 0; i < size; i++) {
            this.mPreviews.get(i).onDeallocateMemory();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        deallocateMemory();
    }

    @Override // android.view.View
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(CoordinateUtils.x(this.mKeyboardViewOrigin), CoordinateUtils.y(this.mKeyboardViewOrigin));
        int size = this.mPreviews.size();
        for (int i = 0; i < size; i++) {
            this.mPreviews.get(i).drawPreview(canvas);
        }
        canvas.translate(-r0, -r1);
    }
}
