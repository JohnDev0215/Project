package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.SparseIntArray;
import com.android.inputmethod.latin.utils.ResourceUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyVisualAttributes {
    private static final int ATTR_DEFINED = 1;
    private static final int ATTR_NOT_FOUND = 0;
    private static final int[] VISUAL_ATTRIBUTE_IDS;
    private static final SparseIntArray sVisualAttributeIds = new SparseIntArray();
    public final int mFunctionalTextColor;
    public final int mHintLabelColor;
    public final float mHintLabelOffCenterRatio;
    public final float mHintLabelRatio;
    public final float mHintLabelVerticalAdjustment;
    public final int mHintLetterColor;
    public final float mHintLetterRatio;
    public final float mLabelOffCenterRatio;
    public final float mLabelRatio;
    public final int mLabelSize;
    public final float mLargeLetterRatio;
    public final float mLetterRatio;
    public final int mLetterSize;
    public final int mPreviewTextColor;
    public final float mPreviewTextRatio;
    public final int mShiftedLetterHintActivatedColor;
    public final int mShiftedLetterHintInactivatedColor;
    public final float mShiftedLetterHintRatio;
    public final int mTextColor;
    public final int mTextInactivatedColor;
    public final int mTextShadowColor;

    @Nullable
    public final Typeface mTypeface;

    static {
        int[] iArr = {28, 17, 15, 16, 11, 22, 8, 19, 25, 26, 27, 3, 10, 6, 21, 20, 18, 9, 14, 7};
        VISUAL_ATTRIBUTE_IDS = iArr;
        for (int i : iArr) {
            sVisualAttributeIds.put(i, 1);
        }
    }

    @Nullable
    public static KeyVisualAttributes newInstance(@Nonnull final TypedArray keyAttr) {
        int indexCount = keyAttr.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            if (sVisualAttributeIds.get(keyAttr.getIndex(i), 0) != 0) {
                return new KeyVisualAttributes(keyAttr);
            }
        }
        return null;
    }

    private KeyVisualAttributes(@Nonnull final TypedArray keyAttr) {
        if (keyAttr.hasValue(28)) {
            this.mTypeface = Typeface.defaultFromStyle(keyAttr.getInt(28, 0));
        } else {
            this.mTypeface = null;
        }
        this.mLetterRatio = ResourceUtils.getFraction(keyAttr, 17);
        this.mLetterSize = ResourceUtils.getDimensionPixelSize(keyAttr, 17);
        this.mLabelRatio = ResourceUtils.getFraction(keyAttr, 15);
        this.mLabelSize = ResourceUtils.getDimensionPixelSize(keyAttr, 15);
        this.mLargeLetterRatio = ResourceUtils.getFraction(keyAttr, 16);
        this.mHintLetterRatio = ResourceUtils.getFraction(keyAttr, 11);
        this.mShiftedLetterHintRatio = ResourceUtils.getFraction(keyAttr, 22);
        this.mHintLabelRatio = ResourceUtils.getFraction(keyAttr, 8);
        this.mPreviewTextRatio = ResourceUtils.getFraction(keyAttr, 19);
        this.mTextColor = keyAttr.getColor(25, 0);
        this.mTextInactivatedColor = keyAttr.getColor(26, 0);
        this.mTextShadowColor = keyAttr.getColor(27, 0);
        this.mFunctionalTextColor = keyAttr.getColor(3, 0);
        this.mHintLetterColor = keyAttr.getColor(10, 0);
        this.mHintLabelColor = keyAttr.getColor(6, 0);
        this.mShiftedLetterHintInactivatedColor = keyAttr.getColor(21, 0);
        this.mShiftedLetterHintActivatedColor = keyAttr.getColor(20, 0);
        this.mPreviewTextColor = keyAttr.getColor(18, 0);
        this.mHintLabelVerticalAdjustment = ResourceUtils.getFraction(keyAttr, 9, 0.0f);
        this.mLabelOffCenterRatio = ResourceUtils.getFraction(keyAttr, 14, 0.0f);
        this.mHintLabelOffCenterRatio = ResourceUtils.getFraction(keyAttr, 7, 0.0f);
    }
}
