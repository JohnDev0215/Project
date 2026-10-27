package com.android.launcher3.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetImageView extends View {
    private Drawable mBadge;
    private final int mBadgeMargin;
    private Bitmap mBitmap;
    private final RectF mDstRectF;
    private final Paint mPaint;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public WidgetImageView(Context context) {
        this(context, null);
    }

    public WidgetImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mPaint = new Paint(3);
        this.mDstRectF = new RectF();
        this.mBadgeMargin = context.getResources().getDimensionPixelSize(R.dimen.profile_badge_margin);
    }

    public void setBitmap(Bitmap bitmap, Drawable badge) {
        this.mBitmap = bitmap;
        this.mBadge = badge;
        invalidate();
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mBitmap != null) {
            updateDstRectF();
            canvas.drawBitmap(this.mBitmap, (Rect) null, this.mDstRectF, this.mPaint);
            Drawable drawable = this.mBadge;
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    private void updateDstRectF() {
        float width = getWidth();
        float height = getHeight();
        float width2 = this.mBitmap.getWidth();
        float f = width2 > width ? width / width2 : 1.0f;
        float f2 = width2 * f;
        float height2 = this.mBitmap.getHeight() * f;
        this.mDstRectF.left = (width - f2) / 2.0f;
        this.mDstRectF.right = (width + f2) / 2.0f;
        if (height2 > height) {
            this.mDstRectF.top = 0.0f;
            this.mDstRectF.bottom = height2;
        } else {
            this.mDstRectF.top = (height - height2) / 2.0f;
            this.mDstRectF.bottom = (height + height2) / 2.0f;
        }
        Drawable drawable = this.mBadge;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int iBoundToRange = Utilities.boundToRange((int) ((this.mDstRectF.right + this.mBadgeMargin) - bounds.width()), this.mBadgeMargin, getWidth() - bounds.width());
            int iBoundToRange2 = Utilities.boundToRange((int) ((this.mDstRectF.bottom + this.mBadgeMargin) - bounds.height()), this.mBadgeMargin, getHeight() - bounds.height());
            this.mBadge.setBounds(iBoundToRange, iBoundToRange2, bounds.width() + iBoundToRange, bounds.height() + iBoundToRange2);
        }
    }

    public Rect getBitmapBounds() {
        updateDstRectF();
        Rect rect = new Rect();
        this.mDstRectF.round(rect);
        return rect;
    }
}
