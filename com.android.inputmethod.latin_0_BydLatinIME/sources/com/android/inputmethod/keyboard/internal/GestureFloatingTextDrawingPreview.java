package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import com.android.inputmethod.keyboard.PointerTracker;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.CoordinateUtils;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GestureFloatingTextDrawingPreview extends AbstractDrawingPreview {
    private final GesturePreviewTextParams mParams;
    private int mPreviewTextX;
    private int mPreviewTextY;
    private final RectF mGesturePreviewRectangle = new RectF();
    private SuggestedWords mSuggestedWords = SuggestedWords.getEmptyInstance();
    private final int[] mLastPointerCoords = CoordinateUtils.newInstance();

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void onDeallocateMemory() {
    }

    protected static final class GesturePreviewTextParams {
        private static final char[] TEXT_HEIGHT_REFERENCE_CHAR = {'M'};
        public final int mDisplayWidth;
        private final int mGesturePreviewColor;
        public final float mGesturePreviewHorizontalPadding;
        public final float mGesturePreviewRoundRadius;
        private final int mGesturePreviewTextColor;
        public final int mGesturePreviewTextHeight;
        public final int mGesturePreviewTextOffset;
        private final int mGesturePreviewTextSize;
        public final float mGesturePreviewVerticalPadding;
        private final Paint mPaint = new Paint();

        public GesturePreviewTextParams(final TypedArray mainKeyboardViewAttr) {
            this.mGesturePreviewTextSize = mainKeyboardViewAttr.getDimensionPixelSize(15, 0);
            this.mGesturePreviewTextColor = mainKeyboardViewAttr.getColor(12, 0);
            this.mGesturePreviewTextOffset = mainKeyboardViewAttr.getDimensionPixelOffset(14, 0);
            this.mGesturePreviewColor = mainKeyboardViewAttr.getColor(9, 0);
            this.mGesturePreviewHorizontalPadding = mainKeyboardViewAttr.getDimension(10, 0.0f);
            this.mGesturePreviewVerticalPadding = mainKeyboardViewAttr.getDimension(16, 0.0f);
            this.mGesturePreviewRoundRadius = mainKeyboardViewAttr.getDimension(11, 0.0f);
            this.mDisplayWidth = mainKeyboardViewAttr.getResources().getDisplayMetrics().widthPixels;
            Paint textPaint = getTextPaint();
            Rect rect = new Rect();
            textPaint.getTextBounds(TEXT_HEIGHT_REFERENCE_CHAR, 0, 1, rect);
            this.mGesturePreviewTextHeight = rect.height();
        }

        public Paint getTextPaint() {
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            this.mPaint.setTextSize(this.mGesturePreviewTextSize);
            this.mPaint.setColor(this.mGesturePreviewTextColor);
            return this.mPaint;
        }

        public Paint getBackgroundPaint() {
            this.mPaint.setColor(this.mGesturePreviewColor);
            return this.mPaint;
        }
    }

    public GestureFloatingTextDrawingPreview(final TypedArray mainKeyboardViewAttr) {
        this.mParams = new GesturePreviewTextParams(mainKeyboardViewAttr);
    }

    public void dismissGestureFloatingPreviewText() {
        setSuggetedWords(SuggestedWords.getEmptyInstance());
    }

    public void setSuggetedWords(@Nonnull final SuggestedWords suggestedWords) {
        if (isPreviewEnabled()) {
            this.mSuggestedWords = suggestedWords;
            updatePreviewPosition();
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void setPreviewPosition(final PointerTracker tracker) {
        if (isPreviewEnabled()) {
            tracker.getLastCoordinates(this.mLastPointerCoords);
            updatePreviewPosition();
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.AbstractDrawingPreview
    public void drawPreview(final Canvas canvas) {
        if (!isPreviewEnabled() || this.mSuggestedWords.isEmpty() || TextUtils.isEmpty(this.mSuggestedWords.getWord(0))) {
            return;
        }
        float f = this.mParams.mGesturePreviewRoundRadius;
        canvas.drawRoundRect(this.mGesturePreviewRectangle, f, f, this.mParams.getBackgroundPaint());
        canvas.drawText(this.mSuggestedWords.getWord(0), this.mPreviewTextX, this.mPreviewTextY, this.mParams.getTextPaint());
    }

    protected void updatePreviewPosition() {
        if (this.mSuggestedWords.isEmpty() || TextUtils.isEmpty(this.mSuggestedWords.getWord(0))) {
            invalidateDrawingView();
            return;
        }
        String word = this.mSuggestedWords.getWord(0);
        RectF rectF = this.mGesturePreviewRectangle;
        int i = this.mParams.mGesturePreviewTextHeight;
        float fMeasureText = this.mParams.getTextPaint().measureText(word);
        float f = this.mParams.mGesturePreviewHorizontalPadding;
        float f2 = this.mParams.mGesturePreviewVerticalPadding;
        float f3 = (f * 2.0f) + fMeasureText;
        float f4 = i + (f2 * 2.0f);
        float fMin = Math.min(Math.max(CoordinateUtils.x(this.mLastPointerCoords) - (f3 / 2.0f), 0.0f), this.mParams.mDisplayWidth - f3);
        float fY = (CoordinateUtils.y(this.mLastPointerCoords) - this.mParams.mGesturePreviewTextOffset) - f4;
        rectF.set(fMin, fY, f3 + fMin, f4 + fY);
        this.mPreviewTextX = (int) (fMin + f + (fMeasureText / 2.0f));
        this.mPreviewTextY = ((int) (fY + f2)) + i;
        invalidateDrawingView();
    }
}
