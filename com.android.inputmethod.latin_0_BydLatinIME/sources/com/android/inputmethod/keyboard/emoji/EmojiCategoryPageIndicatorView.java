package com.android.inputmethod.keyboard.emoji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public final class EmojiCategoryPageIndicatorView extends View {
    private static final float BOTTOM_MARGIN_RATIO = 1.0f;
    private int mCategoryPageSize;
    private int mCurrentCategoryPageId;
    private float mOffset;
    private final Paint mPaint;

    public EmojiCategoryPageIndicatorView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiCategoryPageIndicatorView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mPaint = new Paint();
        this.mCategoryPageSize = 0;
        this.mCurrentCategoryPageId = 0;
        this.mOffset = 0.0f;
    }

    public void setColors(final int foregroundColor, final int backgroundColor) {
        this.mPaint.setColor(foregroundColor);
        setBackgroundColor(backgroundColor);
    }

    public void setCategoryPageId(final int size, final int id, final float offset) {
        this.mCategoryPageSize = size;
        this.mCurrentCategoryPageId = id;
        this.mOffset = offset;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(final Canvas canvas) {
        if (this.mCategoryPageSize <= 1) {
            canvas.drawColor(0);
            return;
        }
        float height = getHeight();
        float width = getWidth() / this.mCategoryPageSize;
        float f = (this.mCurrentCategoryPageId * width) + (this.mOffset * width);
        canvas.drawRect(f, 0.0f, f + width, height * 1.0f, this.mPaint);
    }
}
