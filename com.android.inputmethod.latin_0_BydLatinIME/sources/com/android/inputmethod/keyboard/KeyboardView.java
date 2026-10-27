package com.android.inputmethod.keyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.keyboard.internal.KeyVisualAttributes;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.utils.TypefaceUtils;
import com.byd.ime.BydFeatureFlags;
import com.byd.ime.ResModifier;
import java.util.HashSet;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class KeyboardView extends View {
    private static final float KET_TEXT_SHADOW_RADIUS_DISABLED = -1.0f;
    private static final float MAX_LABEL_RATIO = 0.9f;
    private final Rect mClipRect;
    private Context mContext;
    private final int mDefaultKeyLabelFlags;
    private final Paint.FontMetrics mFontMetrics;
    private final Drawable mFunctionalKeyBackground;
    private boolean mInvalidateAllKeys;
    private final HashSet<Key> mInvalidatedKeys;
    private final Drawable mKeyBackground;
    private final Rect mKeyBackgroundPadding;

    @Nonnull
    private final KeyDrawParams mKeyDrawParams;
    private final float mKeyHintLetterPadding;
    private final String mKeyPopupHintLetter;
    private final float mKeyPopupHintLetterPadding;
    private final float mKeyShiftedLetterHintPadding;
    private final float mKeyTextShadowRadius;
    private final KeyVisualAttributes mKeyVisualAttributes;

    @Nullable
    private Keyboard mKeyboard;
    private Bitmap mOffscreenBuffer;

    @Nonnull
    private final Canvas mOffscreenCanvas;

    @Nonnull
    private final Paint mPaint;
    private final Drawable mSpacebarBackground;
    private final float mSpacebarIconWidthRatio;
    private final float mVerticalCorrection;

    public KeyboardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.keyboardViewStyle);
    }

    public KeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        Rect rect = new Rect();
        this.mKeyBackgroundPadding = rect;
        this.mKeyDrawParams = new KeyDrawParams();
        this.mInvalidatedKeys = new HashSet<>();
        this.mClipRect = new Rect();
        this.mOffscreenCanvas = new Canvas();
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mFontMetrics = new Paint.FontMetrics();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.KeyboardView, defStyle, R.style.KeyboardView);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.mKeyBackground = drawable;
        drawable.getPadding(rect);
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(0);
        this.mFunctionalKeyBackground = drawable2 == null ? drawable : drawable2;
        Drawable drawable3 = typedArrayObtainStyledAttributes.getDrawable(7);
        this.mSpacebarBackground = drawable3 != null ? drawable3 : drawable;
        this.mSpacebarIconWidthRatio = typedArrayObtainStyledAttributes.getFloat(8, 1.0f);
        this.mKeyHintLetterPadding = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
        this.mKeyPopupHintLetter = typedArrayObtainStyledAttributes.getString(3);
        this.mKeyPopupHintLetterPadding = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.mKeyShiftedLetterHintPadding = typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
        this.mKeyTextShadowRadius = typedArrayObtainStyledAttributes.getFloat(6, -1.0f);
        this.mVerticalCorrection = typedArrayObtainStyledAttributes.getDimension(12, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attrs, R.styleable.Keyboard_Key, defStyle, R.style.KeyboardView);
        this.mDefaultKeyLabelFlags = typedArrayObtainStyledAttributes2.getInt(13, 0);
        this.mKeyVisualAttributes = KeyVisualAttributes.newInstance(typedArrayObtainStyledAttributes2);
        typedArrayObtainStyledAttributes2.recycle();
        paint.setAntiAlias(true);
        this.mContext = context;
    }

    @Nullable
    public KeyVisualAttributes getKeyVisualAttribute() {
        return this.mKeyVisualAttributes;
    }

    private static void blendAlpha(@Nonnull final Paint paint, final int alpha) {
        int color = paint.getColor();
        paint.setARGB((paint.getAlpha() * alpha) / 255, Color.red(color), Color.green(color), Color.blue(color));
    }

    public void setHardwareAcceleratedDrawingEnabled(final boolean enabled) {
        if (enabled) {
            setLayerType(2, null);
        }
    }

    public void setKeyboard(@Nonnull final Keyboard keyboard) {
        this.mKeyboard = keyboard;
        int i = keyboard.mMostCommonKeyHeight - keyboard.mVerticalGap;
        this.mKeyDrawParams.updateParams(i, this.mKeyVisualAttributes);
        this.mKeyDrawParams.updateParams(i, keyboard.mKeyVisualAttributes);
        invalidateAllKeys();
        requestLayout();
    }

    @Nullable
    public Keyboard getKeyboard() {
        return this.mKeyboard;
    }

    protected float getVerticalCorrection() {
        return this.mVerticalCorrection;
    }

    @Nonnull
    protected KeyDrawParams getKeyDrawParams() {
        return this.mKeyDrawParams;
    }

    protected void updateKeyDrawParams(final int keyHeight) {
        this.mKeyDrawParams.updateParams(keyHeight, this.mKeyVisualAttributes);
    }

    @Override // android.view.View
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(keyboard.mOccupiedWidth + getPaddingLeft() + getPaddingRight(), keyboard.mOccupiedHeight + getPaddingTop() + getPaddingBottom());
        }
    }

    @Override // android.view.View
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (canvas.isHardwareAccelerated()) {
            onDrawKeyboard(canvas);
            return;
        }
        if ((this.mInvalidateAllKeys || !this.mInvalidatedKeys.isEmpty()) || this.mOffscreenBuffer == null) {
            if (maybeAllocateOffscreenBuffer()) {
                this.mInvalidateAllKeys = true;
                this.mOffscreenCanvas.setBitmap(this.mOffscreenBuffer);
            }
            onDrawKeyboard(this.mOffscreenCanvas);
        }
        canvas.drawBitmap(this.mOffscreenBuffer, 0.0f, 0.0f, (Paint) null);
    }

    private boolean maybeAllocateOffscreenBuffer() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) {
            return false;
        }
        Bitmap bitmap = this.mOffscreenBuffer;
        if (bitmap != null && bitmap.getWidth() == width && this.mOffscreenBuffer.getHeight() == height) {
            return false;
        }
        freeOffscreenBuffer();
        this.mOffscreenBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        return true;
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

    private void onDrawKeyboard(@Nonnull final Canvas canvas) {
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }
        Paint paint = this.mPaint;
        Drawable background = getBackground();
        boolean z = this.mInvalidateAllKeys || this.mInvalidatedKeys.isEmpty();
        boolean zIsHardwareAccelerated = canvas.isHardwareAccelerated();
        if (z || zIsHardwareAccelerated) {
            if (!zIsHardwareAccelerated && background != null) {
                canvas.drawColor(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.CLEAR);
                background.draw(canvas);
            }
            Iterator<Key> it = keyboard.getSortedKeys().iterator();
            while (it.hasNext()) {
                onDrawKey(it.next(), canvas, paint);
            }
        } else {
            for (Key key : this.mInvalidatedKeys) {
                if (keyboard.hasKey(key)) {
                    if (background != null) {
                        int x = key.getX() + getPaddingLeft();
                        int y = key.getY() + getPaddingTop();
                        this.mClipRect.set(x, y, key.getWidth() + x, key.getHeight() + y);
                        canvas.save();
                        canvas.clipRect(this.mClipRect);
                        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.CLEAR);
                        background.draw(canvas);
                        canvas.restore();
                    }
                    onDrawKey(key, canvas, paint);
                }
            }
        }
        this.mInvalidatedKeys.clear();
        this.mInvalidateAllKeys = false;
    }

    private void onDrawKey(@Nonnull final Key key, @Nonnull final Canvas canvas, @Nonnull final Paint paint) {
        Drawable drawableSelectBackgroundDrawable;
        canvas.translate(key.getDrawX() + getPaddingLeft(), key.getY() + getPaddingTop());
        KeyDrawParams keyDrawParamsMayCloneAndUpdateParams = this.mKeyDrawParams.mayCloneAndUpdateParams(key.getHeight(), key.getVisualAttributes());
        keyDrawParamsMayCloneAndUpdateParams.mAnimAlpha = 255;
        if (!key.isSpacer() && (drawableSelectBackgroundDrawable = key.selectBackgroundDrawable(this.mKeyBackground, this.mFunctionalKeyBackground, this.mSpacebarBackground)) != null) {
            onDrawKeyBackground(key, canvas, drawableSelectBackgroundDrawable);
        }
        onDrawKeyTopVisuals(key, canvas, paint, keyDrawParamsMayCloneAndUpdateParams);
        canvas.translate(-r0, -r1);
    }

    protected void onDrawKeyBackground(@Nonnull final Key key, @Nonnull final Canvas canvas, @Nonnull final Drawable background) {
        int i;
        int i2;
        int i3;
        int i4;
        int drawWidth = key.getDrawWidth();
        int height = key.getHeight();
        if (key.needsToKeepBackgroundAspectRatio(this.mDefaultKeyLabelFlags) && !key.hasCustomActionLabel()) {
            float intrinsicWidth = background.getIntrinsicWidth();
            float intrinsicHeight = background.getIntrinsicHeight();
            float fMin = Math.min(drawWidth / intrinsicWidth, height / intrinsicHeight);
            i3 = (int) (intrinsicWidth * fMin);
            i = (int) (intrinsicHeight * fMin);
            i4 = (drawWidth - i3) / 2;
            i2 = (height - i) / 2;
        } else {
            Rect rect = this.mKeyBackgroundPadding;
            int i5 = drawWidth + rect.left + rect.right;
            i = rect.bottom + height + rect.top;
            int i6 = -rect.left;
            i2 = -rect.top;
            i3 = i5;
            i4 = i6;
        }
        Rect bounds = background.getBounds();
        if (i3 != bounds.right || i != bounds.bottom) {
            background.setBounds(0, 0, i3, i);
        }
        canvas.translate(i4, i2);
        background.draw(canvas);
        canvas.translate(-i4, -i2);
    }

    protected void onDrawKeyTopVisuals(@Nonnull final Key key, @Nonnull final Canvas canvas, @Nonnull final Paint paint, @Nonnull final KeyDrawParams params) {
        String str;
        float f;
        int iMin;
        float fMax;
        int drawWidth = key.getDrawWidth();
        int height = key.getHeight();
        float f2 = drawWidth;
        float f3 = f2 * 0.5f;
        float f4 = height * 0.5f;
        Keyboard keyboard = getKeyboard();
        Drawable icon = keyboard == null ? null : key.getIcon(keyboard.mIconsSet, params.mAnimAlpha);
        if (key.getCode() == -1) {
            icon = ResModifier.getShiftDrawable(this.mContext);
        }
        if (key.getCode() == -5 && ResModifier.currentSubtypeShouldRTL()) {
            icon = ResModifier.getRTLDeleteDrawable(this.mContext);
        }
        Drawable drawable = icon;
        String label = key.getLabel();
        if (label != null) {
            paint.setTypeface(key.selectTypeface(params));
            paint.setTextSize(key.selectTextSize(params));
            ResModifier.resetSwitchAlphaSymbolTextSize(this.mContext, key, params, paint);
            float referenceCharHeight = TypefaceUtils.getReferenceCharHeight(paint);
            float referenceCharWidth = TypefaceUtils.getReferenceCharWidth(paint);
            f = f4 + (referenceCharHeight / 2.0f);
            if (key.isAlignLabelOffCenter()) {
                f3 += params.mLabelOffCenterRatio * referenceCharWidth;
                paint.setTextAlign(Paint.Align.LEFT);
            } else {
                paint.setTextAlign(Paint.Align.CENTER);
            }
            float f5 = f3;
            if (key.needsAutoXScale()) {
                float fMin = Math.min(1.0f, (MAX_LABEL_RATIO * f2) / TypefaceUtils.getStringWidth(label, paint));
                if (key.needsAutoScale()) {
                    paint.setTextSize(paint.getTextSize() * fMin);
                } else {
                    paint.setTextScaleX(fMin);
                }
            }
            if (key.isEnabled()) {
                paint.setColor(key.selectTextColor(params));
                float f6 = this.mKeyTextShadowRadius;
                if (f6 > 0.0f) {
                    paint.setShadowLayer(f6, 0.0f, 0.0f, params.mTextShadowColor);
                } else {
                    paint.clearShadowLayer();
                }
            } else {
                paint.setColor(0);
                paint.clearShadowLayer();
            }
            blendAlpha(paint, params.mAnimAlpha);
            str = label;
            canvas.drawText(label, 0, label.length(), f5, f, paint);
            paint.clearShadowLayer();
            paint.setTextScaleX(1.0f);
            f3 = f5;
        } else {
            str = label;
            f = f4;
        }
        String hintLabel = key.getHintLabel();
        if (hintLabel != null) {
            paint.setTextSize(key.selectHintTextSize(params));
            paint.setColor(key.selectHintTextColor(params));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            blendAlpha(paint, params.mAnimAlpha);
            float referenceCharHeight2 = TypefaceUtils.getReferenceCharHeight(paint);
            float referenceCharWidth2 = TypefaceUtils.getReferenceCharWidth(paint);
            if (key.hasHintLabel()) {
                float f7 = f3 + (params.mHintLabelOffCenterRatio * referenceCharWidth2);
                if (!key.isAlignHintLabelToBottom(this.mDefaultKeyLabelFlags)) {
                    f = f4 + (referenceCharHeight2 / 2.0f);
                }
                paint.setTextAlign(Paint.Align.LEFT);
                fMax = f7;
            } else if (key.hasShiftedLetterHint()) {
                float f8 = (f2 - this.mKeyShiftedLetterHintPadding) - (referenceCharWidth2 / 2.0f);
                paint.getFontMetrics(this.mFontMetrics);
                float f9 = -this.mFontMetrics.top;
                paint.setTextAlign(Paint.Align.CENTER);
                fMax = f8;
                f = f9;
            } else {
                fMax = (f2 - this.mKeyHintLetterPadding) - (Math.max(TypefaceUtils.getReferenceDigitWidth(paint), TypefaceUtils.getStringWidth(hintLabel, paint)) / 2.0f);
                float f10 = -paint.ascent();
                paint.setTextAlign(Paint.Align.CENTER);
                f = f10;
            }
            canvas.drawText(hintLabel, 0, hintLabel.length(), fMax, f + (params.mHintLabelVerticalAdjustment * referenceCharHeight2) + 5.0f, paint);
        }
        if (str == null && drawable != null) {
            if (key.getCode() == 32 && (drawable instanceof NinePatchDrawable)) {
                iMin = (int) (f2 * this.mSpacebarIconWidthRatio);
            } else {
                iMin = Math.min(drawable.getIntrinsicWidth(), drawWidth);
            }
            int i = iMin;
            int intrinsicHeight = drawable.getIntrinsicHeight();
            drawIcon(canvas, drawable, (drawWidth - i) / 2, key.isAlignIconToBottom() ? height - intrinsicHeight : (height - intrinsicHeight) / 2, i, intrinsicHeight);
        }
        if (!key.hasPopupHint() || key.getMoreKeys() == null) {
            return;
        }
        drawKeyPopupHint(key, canvas, paint, params);
    }

    protected void drawKeyPopupHint(@Nonnull final Key key, @Nonnull final Canvas canvas, @Nonnull final Paint paint, @Nonnull final KeyDrawParams params) {
        if (TextUtils.isEmpty(this.mKeyPopupHintLetter) || key.getCode() == 32 || key.getCode() == -10) {
            return;
        }
        int drawWidth = key.getDrawWidth();
        int height = key.getHeight();
        paint.setTypeface(params.mTypeface);
        if (BydFeatureFlags.isLandscape(this.mContext)) {
            paint.setTextSize(params.mHintLetterSize * 1.5f);
        } else {
            paint.setTextSize(params.mHintLetterSize);
        }
        paint.setColor(params.mHintLabelColor);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.mKeyPopupHintLetter, (drawWidth - this.mKeyHintLetterPadding) - (TypefaceUtils.getReferenceCharWidth(paint) / 2.0f), height - this.mKeyPopupHintLetterPadding, paint);
    }

    protected static void drawIcon(@Nonnull final Canvas canvas, @Nonnull final Drawable icon, final int x, final int y, final int width, final int height) {
        canvas.translate(x, y);
        icon.setBounds(0, 0, width, height);
        icon.draw(canvas);
        canvas.translate(-x, -y);
    }

    public Paint newLabelPaint(@Nullable final Key key) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (key == null) {
            paint.setTypeface(this.mKeyDrawParams.mTypeface);
            paint.setTextSize(this.mKeyDrawParams.mLabelSize);
        } else {
            paint.setColor(key.selectTextColor(this.mKeyDrawParams));
            paint.setTypeface(key.selectTypeface(this.mKeyDrawParams));
            paint.setTextSize(key.selectTextSize(this.mKeyDrawParams));
        }
        return paint;
    }

    public void invalidateAllKeys() {
        this.mInvalidatedKeys.clear();
        this.mInvalidateAllKeys = true;
        invalidate();
    }

    public void invalidateKey(@Nullable final Key key) {
        if (this.mInvalidateAllKeys || key == null) {
            return;
        }
        this.mInvalidatedKeys.add(key);
        int x = key.getX() + getPaddingLeft();
        int y = key.getY() + getPaddingTop();
        invalidate(x, y, key.getWidth() + x, key.getHeight() + y);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        freeOffscreenBuffer();
    }

    public void deallocateMemory() {
        freeOffscreenBuffer();
    }
}
