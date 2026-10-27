package org.chromium.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
class DropdownDividerDrawable extends Drawable {
    private Paint mPaint = new Paint();
    private Rect mDividerRect = new Rect();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawRect(this.mDividerRect, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.mDividerRect.set(0, 0, rect.width(), this.mDividerRect.height());
    }

    public void setHeight(int i) {
        Rect rect = this.mDividerRect;
        rect.set(0, 0, rect.right, i);
    }

    public void setColor(int i) {
        this.mPaint.setColor(i);
    }
}
