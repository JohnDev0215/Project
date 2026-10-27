package com.android.inputmethod.keyboard.internal;

import android.graphics.Typeface;
import com.android.inputmethod.latin.utils.ResourceUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyDrawParams {
    public int mAnimAlpha;
    public int mFunctionalTextColor;
    public int mHintLabelColor;
    public float mHintLabelOffCenterRatio;
    public int mHintLabelSize;
    public float mHintLabelVerticalAdjustment;
    public int mHintLetterColor;
    public int mHintLetterSize;
    public float mLabelOffCenterRatio;
    public int mLabelSize;
    public int mLargeLetterSize;
    public int mLetterSize;
    public int mPreviewTextColor;
    public int mPreviewTextSize;
    public int mShiftedLetterHintActivatedColor;
    public int mShiftedLetterHintInactivatedColor;
    public int mShiftedLetterHintSize;
    public int mTextColor;
    public int mTextInactivatedColor;
    public int mTextShadowColor;

    @Nonnull
    public Typeface mTypeface;

    private static int selectColor(final int attrColor, final int defaultColor) {
        return attrColor != 0 ? attrColor : defaultColor;
    }

    private static float selectFloatIfNonZero(final float attrFloat, final float defaultFloat) {
        return attrFloat != 0.0f ? attrFloat : defaultFloat;
    }

    public KeyDrawParams() {
        this.mTypeface = Typeface.DEFAULT;
    }

    private KeyDrawParams(@Nonnull final KeyDrawParams copyFrom) {
        this.mTypeface = Typeface.DEFAULT;
        this.mTypeface = copyFrom.mTypeface;
        this.mLetterSize = copyFrom.mLetterSize;
        this.mLabelSize = copyFrom.mLabelSize;
        this.mLargeLetterSize = copyFrom.mLargeLetterSize;
        this.mHintLetterSize = copyFrom.mHintLetterSize;
        this.mShiftedLetterHintSize = copyFrom.mShiftedLetterHintSize;
        this.mHintLabelSize = copyFrom.mHintLabelSize;
        this.mPreviewTextSize = copyFrom.mPreviewTextSize;
        this.mTextColor = copyFrom.mTextColor;
        this.mTextInactivatedColor = copyFrom.mTextInactivatedColor;
        this.mTextShadowColor = copyFrom.mTextShadowColor;
        this.mFunctionalTextColor = copyFrom.mFunctionalTextColor;
        this.mHintLetterColor = copyFrom.mHintLetterColor;
        this.mHintLabelColor = copyFrom.mHintLabelColor;
        this.mShiftedLetterHintInactivatedColor = copyFrom.mShiftedLetterHintInactivatedColor;
        this.mShiftedLetterHintActivatedColor = copyFrom.mShiftedLetterHintActivatedColor;
        this.mPreviewTextColor = copyFrom.mPreviewTextColor;
        this.mHintLabelVerticalAdjustment = copyFrom.mHintLabelVerticalAdjustment;
        this.mLabelOffCenterRatio = copyFrom.mLabelOffCenterRatio;
        this.mHintLabelOffCenterRatio = copyFrom.mHintLabelOffCenterRatio;
        this.mAnimAlpha = copyFrom.mAnimAlpha;
    }

    public void updateParams(final int keyHeight, @Nullable final KeyVisualAttributes attr) {
        if (attr == null) {
            return;
        }
        if (attr.mTypeface != null) {
            this.mTypeface = attr.mTypeface;
        }
        this.mLetterSize = selectTextSizeFromDimensionOrRatio(keyHeight, attr.mLetterSize, attr.mLetterRatio, this.mLetterSize);
        this.mLabelSize = selectTextSizeFromDimensionOrRatio(keyHeight, attr.mLabelSize, attr.mLabelRatio, this.mLabelSize);
        this.mLargeLetterSize = selectTextSize(keyHeight, attr.mLargeLetterRatio, this.mLargeLetterSize);
        this.mHintLetterSize = selectTextSize(keyHeight, attr.mHintLetterRatio, this.mHintLetterSize);
        this.mShiftedLetterHintSize = selectTextSize(keyHeight, attr.mShiftedLetterHintRatio, this.mShiftedLetterHintSize);
        this.mHintLabelSize = selectTextSize(keyHeight, attr.mHintLabelRatio, this.mHintLabelSize);
        this.mPreviewTextSize = selectTextSize(keyHeight, attr.mPreviewTextRatio, this.mPreviewTextSize);
        this.mTextColor = selectColor(attr.mTextColor, this.mTextColor);
        this.mTextInactivatedColor = selectColor(attr.mTextInactivatedColor, this.mTextInactivatedColor);
        this.mTextShadowColor = selectColor(attr.mTextShadowColor, this.mTextShadowColor);
        this.mFunctionalTextColor = selectColor(attr.mFunctionalTextColor, this.mFunctionalTextColor);
        this.mHintLetterColor = selectColor(attr.mHintLetterColor, this.mHintLetterColor);
        this.mHintLabelColor = selectColor(attr.mHintLabelColor, this.mHintLabelColor);
        this.mShiftedLetterHintInactivatedColor = selectColor(attr.mShiftedLetterHintInactivatedColor, this.mShiftedLetterHintInactivatedColor);
        this.mShiftedLetterHintActivatedColor = selectColor(attr.mShiftedLetterHintActivatedColor, this.mShiftedLetterHintActivatedColor);
        this.mPreviewTextColor = selectColor(attr.mPreviewTextColor, this.mPreviewTextColor);
        this.mHintLabelVerticalAdjustment = selectFloatIfNonZero(attr.mHintLabelVerticalAdjustment, this.mHintLabelVerticalAdjustment);
        this.mLabelOffCenterRatio = selectFloatIfNonZero(attr.mLabelOffCenterRatio, this.mLabelOffCenterRatio);
        this.mHintLabelOffCenterRatio = selectFloatIfNonZero(attr.mHintLabelOffCenterRatio, this.mHintLabelOffCenterRatio);
    }

    @Nonnull
    public KeyDrawParams mayCloneAndUpdateParams(final int keyHeight, @Nullable final KeyVisualAttributes attr) {
        if (attr == null) {
            return this;
        }
        KeyDrawParams keyDrawParams = new KeyDrawParams(this);
        keyDrawParams.updateParams(keyHeight, attr);
        return keyDrawParams;
    }

    private static int selectTextSizeFromDimensionOrRatio(final int keyHeight, final int dimens, final float ratio, final int defaultDimens) {
        return ResourceUtils.isValidDimensionPixelSize(dimens) ? dimens : ResourceUtils.isValidFraction(ratio) ? (int) (keyHeight * ratio) : defaultDimens;
    }

    private static int selectTextSize(final int keyHeight, final float ratio, final int defaultSize) {
        return ResourceUtils.isValidFraction(ratio) ? (int) (keyHeight * ratio) : defaultSize;
    }
}
