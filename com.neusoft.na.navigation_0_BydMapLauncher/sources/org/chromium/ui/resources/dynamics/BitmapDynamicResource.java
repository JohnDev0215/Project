package org.chromium.ui.resources.dynamics;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapDynamicResource implements DynamicResource {
    private static final Rect EMPTY_RECT = new Rect();
    private Bitmap mBitmap;
    private final int mResId;
    private final Rect mSize = new Rect();
    private boolean mIsDirty = true;

    public BitmapDynamicResource(int i) {
        this.mResId = i;
    }

    public int getResId() {
        return this.mResId;
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.mIsDirty = true;
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.mBitmap = bitmap;
        this.mSize.set(0, 0, bitmap.getWidth(), this.mBitmap.getHeight());
    }

    @Override // org.chromium.ui.resources.Resource
    public Bitmap getBitmap() {
        this.mIsDirty = false;
        return this.mBitmap;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getBitmapSize() {
        return this.mSize;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getPadding() {
        return EMPTY_RECT;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getAperture() {
        return EMPTY_RECT;
    }

    @Override // org.chromium.ui.resources.dynamics.DynamicResource
    public boolean isDirty() {
        return this.mIsDirty;
    }
}
