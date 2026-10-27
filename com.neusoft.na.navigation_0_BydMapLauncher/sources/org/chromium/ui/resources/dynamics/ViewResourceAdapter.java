package org.chromium.ui.resources.dynamics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes4.dex */
public class ViewResourceAdapter implements DynamicResource, View.OnLayoutChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Bitmap mBitmap;
    private final View mView;
    private final Rect mDirtyRect = new Rect();
    private final Rect mContentPadding = new Rect();
    private final Rect mContentAperture = new Rect();
    private Rect mBitmapSize = new Rect();

    protected void onCaptureEnd() {
    }

    protected void onCaptureStart(Canvas canvas, Rect rect) {
    }

    public ViewResourceAdapter(View view) {
        this.mView = view;
        view.addOnLayoutChangeListener(this);
    }

    @Override // org.chromium.ui.resources.Resource
    public Bitmap getBitmap() {
        if (!isDirty()) {
            return this.mBitmap;
        }
        TraceEvent.begin("ViewResourceAdapter:getBitmap");
        if (validateBitmap()) {
            Canvas canvas = new Canvas(this.mBitmap);
            onCaptureStart(canvas, this.mDirtyRect.isEmpty() ? null : this.mDirtyRect);
            if (!this.mDirtyRect.isEmpty()) {
                canvas.clipRect(this.mDirtyRect);
            }
            capture(canvas);
            onCaptureEnd();
        } else {
            this.mBitmap.setPixel(0, 0, 0);
        }
        this.mDirtyRect.setEmpty();
        TraceEvent.end("ViewResourceAdapter:getBitmap");
        return this.mBitmap;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getBitmapSize() {
        return this.mBitmapSize;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getPadding() {
        computeContentPadding(this.mContentPadding);
        return this.mContentPadding;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getAperture() {
        computeContentAperture(this.mContentAperture);
        return this.mContentAperture;
    }

    @Override // org.chromium.ui.resources.dynamics.DynamicResource
    public boolean isDirty() {
        if (this.mBitmap == null) {
            this.mDirtyRect.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
        }
        return !this.mDirtyRect.isEmpty();
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i3 - i;
        int i10 = i4 - i2;
        int i11 = i8 - i6;
        if (i9 == i7 - i5 && i10 == i11) {
            return;
        }
        this.mDirtyRect.set(0, 0, i9, i10);
    }

    public void invalidate(Rect rect) {
        if (rect == null) {
            this.mDirtyRect.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
        } else {
            this.mDirtyRect.union(rect);
        }
    }

    protected Rect getDirtyRect() {
        return this.mDirtyRect;
    }

    protected void capture(Canvas canvas) {
        this.mView.draw(canvas);
    }

    protected void computeContentPadding(Rect rect) {
        rect.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
    }

    protected void computeContentAperture(Rect rect) {
        rect.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
    }

    private boolean validateBitmap() {
        int width = this.mView.getWidth();
        int height = this.mView.getHeight();
        boolean z = width == 0 || height == 0;
        if (z) {
            width = 1;
            height = 1;
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && (bitmap.getWidth() != width || this.mBitmap.getHeight() != height)) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
        if (this.mBitmap == null) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            this.mBitmap = bitmapCreateBitmap;
            bitmapCreateBitmap.setHasAlpha(true);
            this.mDirtyRect.set(0, 0, width, height);
            this.mBitmapSize.set(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
        }
        return !z;
    }
}
