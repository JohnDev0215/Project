package org.chromium.ui.resources;

import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes4.dex */
public class LayoutResource {
    private final RectF mAperture;
    private final RectF mBitmapSize;
    private final RectF mPadding;

    public LayoutResource(float f, Resource resource) {
        Rect padding = resource.getPadding();
        Rect bitmapSize = resource.getBitmapSize();
        Rect aperture = resource.getAperture();
        this.mPadding = new RectF(padding.left * f, padding.top * f, padding.right * f, padding.bottom * f);
        this.mBitmapSize = new RectF(bitmapSize.left * f, bitmapSize.top * f, bitmapSize.right * f, bitmapSize.bottom * f);
        this.mAperture = new RectF(aperture.left * f, aperture.top * f, aperture.right * f, aperture.bottom * f);
    }

    public RectF getPadding() {
        return this.mPadding;
    }

    public RectF getBitmapSize() {
        return this.mBitmapSize;
    }

    public RectF getAperture() {
        return this.mAperture;
    }
}
