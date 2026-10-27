package com.android.inputmethod.keyboard.internal;

import android.graphics.Canvas;
import android.view.View;
import com.android.inputmethod.keyboard.PointerTracker;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractDrawingPreview {
    private View mDrawingView;
    private boolean mHasValidGeometry;
    private boolean mPreviewEnabled;

    public abstract void drawPreview(@Nonnull final Canvas canvas);

    public abstract void onDeallocateMemory();

    public abstract void setPreviewPosition(@Nonnull final PointerTracker tracker);

    public void setDrawingView(@Nonnull final DrawingPreviewPlacerView drawingView) {
        this.mDrawingView = drawingView;
        drawingView.addPreview(this);
    }

    protected void invalidateDrawingView() {
        View view = this.mDrawingView;
        if (view != null) {
            view.invalidate();
        }
    }

    protected final boolean isPreviewEnabled() {
        return this.mPreviewEnabled && this.mHasValidGeometry;
    }

    public final void setPreviewEnabled(final boolean enabled) {
        this.mPreviewEnabled = enabled;
    }

    public void setKeyboardViewGeometry(@Nonnull final int[] originCoords, final int width, final int height) {
        this.mHasValidGeometry = width > 0 && height > 0;
    }
}
