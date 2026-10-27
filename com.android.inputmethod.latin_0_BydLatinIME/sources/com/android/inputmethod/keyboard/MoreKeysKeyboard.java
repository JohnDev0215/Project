package com.android.inputmethod.keyboard;

import android.content.Context;
import android.graphics.Paint;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.internal.KeyboardBuilder;
import com.android.inputmethod.keyboard.internal.KeyboardParams;
import com.android.inputmethod.keyboard.internal.MoreKeySpec;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.utils.TypefaceUtils;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class MoreKeysKeyboard extends Keyboard {
    private final int mDefaultKeyCoordX;

    MoreKeysKeyboard(final MoreKeysKeyboardParams params) {
        super(params);
        this.mDefaultKeyCoordX = params.getDefaultKeyCoordX() + (params.mDefaultKeyWidth / 2);
    }

    public int getDefaultCoordX() {
        return this.mDefaultKeyCoordX;
    }

    static class MoreKeysKeyboardParams extends KeyboardParams {
        public int mColumnWidth;
        public int mDividerWidth;
        public boolean mIsMoreKeysFixedOrder;
        public int mLeftKeys;
        public int mNumColumns;
        public int mNumRows;
        public int mRightKeys;
        public int mTopKeys;
        int mTopRowAdjustment;

        public void setParameters(final int numKeys, final int numColumn, final int keyWidth, final int rowHeight, final int coordXInParent, final int parentKeyboardWidth, final boolean isMoreKeysFixedColumn, final boolean isMoreKeysFixedOrder, final int dividerWidth) {
            int optimizedColumns;
            int autoOrderTopRowAdjustment;
            this.mIsMoreKeysFixedOrder = isMoreKeysFixedOrder;
            if (parentKeyboardWidth / keyWidth < Math.min(numKeys, numColumn)) {
                throw new IllegalArgumentException("Keyboard is too small to hold more keys: " + parentKeyboardWidth + " " + keyWidth + " " + numKeys + " " + numColumn);
            }
            this.mDefaultKeyWidth = keyWidth;
            this.mDefaultRowHeight = rowHeight;
            this.mNumRows = ((numKeys + numColumn) - 1) / numColumn;
            if (isMoreKeysFixedColumn) {
                optimizedColumns = Math.min(numKeys, numColumn);
            } else {
                optimizedColumns = getOptimizedColumns(numKeys, numColumn);
            }
            this.mNumColumns = optimizedColumns;
            int i = numKeys % optimizedColumns;
            if (i == 0) {
                i = optimizedColumns;
            }
            this.mTopKeys = i;
            int i2 = (optimizedColumns - 1) / 2;
            int i3 = optimizedColumns - i2;
            int i4 = coordXInParent / keyWidth;
            int i5 = (parentKeyboardWidth - coordXInParent) / keyWidth;
            if (i2 > i4) {
                i3 = optimizedColumns - i4;
                i2 = i4;
            } else {
                int i6 = i5 + 1;
                if (i3 > i6) {
                    i2 = optimizedColumns - i6;
                    i3 = i6;
                }
            }
            if (i4 == i2 && i2 > 0) {
                i2--;
                i3++;
            }
            if (i5 == i3 - 1 && i3 > 1) {
                i2++;
                i3--;
            }
            this.mLeftKeys = i2;
            this.mRightKeys = i3;
            if (isMoreKeysFixedOrder) {
                autoOrderTopRowAdjustment = getFixedOrderTopRowAdjustment();
            } else {
                autoOrderTopRowAdjustment = getAutoOrderTopRowAdjustment();
            }
            this.mTopRowAdjustment = autoOrderTopRowAdjustment;
            this.mDividerWidth = dividerWidth;
            int i7 = this.mDefaultKeyWidth;
            int i8 = this.mDividerWidth;
            int i9 = i7 + i8;
            this.mColumnWidth = i9;
            int i10 = (this.mNumColumns * i9) - i8;
            this.mOccupiedWidth = i10;
            this.mBaseWidth = i10;
            int i11 = ((this.mNumRows * this.mDefaultRowHeight) - this.mVerticalGap) + this.mTopPadding + this.mBottomPadding;
            this.mOccupiedHeight = i11;
            this.mBaseHeight = i11;
        }

        private int getFixedOrderTopRowAdjustment() {
            if (this.mNumRows == 1) {
                return 0;
            }
            int i = this.mTopKeys;
            return (i % 2 == 1 || i == this.mNumColumns || this.mLeftKeys == 0 || this.mRightKeys == 1) ? 0 : -1;
        }

        private int getAutoOrderTopRowAdjustment() {
            int i;
            return (this.mNumRows == 1 || (i = this.mTopKeys) == 1 || this.mNumColumns % 2 == i % 2 || this.mLeftKeys == 0 || this.mRightKeys == 1) ? 0 : -1;
        }

        int getColumnPos(final int n) {
            return this.mIsMoreKeysFixedOrder ? getFixedOrderColumnPos(n) : getAutomaticColumnPos(n);
        }

        private int getFixedOrderColumnPos(final int n) {
            int i = this.mNumColumns;
            int i2 = n % i;
            if (!isTopRow(n / i)) {
                return i2 - this.mLeftKeys;
            }
            int i3 = this.mTopKeys;
            int i4 = i3 / 2;
            int i5 = i3 - (i4 + 1);
            int i6 = i2 - i5;
            int i7 = this.mLeftKeys + this.mTopRowAdjustment;
            int i8 = this.mRightKeys - 1;
            return (i8 < i4 || i7 < i5) ? i8 < i4 ? i6 - (i4 - i8) : i6 + (i5 - i7) : i6;
        }

        private int getAutomaticColumnPos(final int n) {
            int i = this.mNumColumns;
            int i2 = n % i;
            int i3 = n / i;
            int i4 = this.mLeftKeys;
            if (isTopRow(i3)) {
                i4 += this.mTopRowAdjustment;
            }
            int i5 = 0;
            if (i2 == 0) {
                return 0;
            }
            int i6 = 0;
            int i7 = 1;
            int i8 = 0;
            do {
                if (i7 < this.mRightKeys) {
                    i5++;
                    int i9 = i7;
                    i7++;
                    i6 = i9;
                }
                if (i5 >= i2) {
                    break;
                }
                if (i8 < i4) {
                    i8++;
                    i6 = -i8;
                    i5++;
                }
            } while (i5 < i2);
            return i6;
        }

        private static int getTopRowEmptySlots(final int numKeys, final int numColumns) {
            int i = numKeys % numColumns;
            if (i == 0) {
                return 0;
            }
            return numColumns - i;
        }

        private int getOptimizedColumns(final int numKeys, final int maxColumns) {
            int iMin = Math.min(numKeys, maxColumns);
            while (getTopRowEmptySlots(numKeys, iMin) >= this.mNumRows) {
                iMin--;
            }
            return iMin;
        }

        public int getDefaultKeyCoordX() {
            return (this.mLeftKeys * this.mColumnWidth) + this.mLeftPadding;
        }

        public int getX(final int n, final int row) {
            int columnPos = (getColumnPos(n) * this.mColumnWidth) + getDefaultKeyCoordX();
            return isTopRow(row) ? columnPos + (this.mTopRowAdjustment * (this.mColumnWidth / 2)) : columnPos;
        }

        public int getY(final int row) {
            return (((this.mNumRows - 1) - row) * this.mDefaultRowHeight) + this.mTopPadding;
        }

        public void markAsEdgeKey(final Key key, final int row) {
            if (row == 0) {
                key.markAsTopEdge(this);
            }
            if (isTopRow(row)) {
                key.markAsBottomEdge(this);
            }
        }

        private boolean isTopRow(final int rowCount) {
            int i = this.mNumRows;
            return i > 1 && rowCount == i - 1;
        }
    }

    public static class Builder extends KeyboardBuilder<MoreKeysKeyboardParams> {
        private static final float DIVIDER_RATIO = 0.2f;
        private static final float LABEL_PADDING_RATIO = 0.2f;
        private final Key mParentKey;

        public Builder(final Context context, final Key key, final Keyboard keyboard, final boolean isSingleMoreKeyWithPreview, final int keyPreviewVisibleWidth, final int keyPreviewVisibleHeight, final Paint paintToMeasure) {
            int maxKeyWidth;
            int i;
            super(context, new MoreKeysKeyboardParams());
            load(keyboard.mMoreKeysTemplate, keyboard.mId);
            ((MoreKeysKeyboardParams) this.mParams).mVerticalGap = keyboard.mVerticalGap / 2;
            this.mParentKey = key;
            if (isSingleMoreKeyWithPreview) {
                maxKeyWidth = keyPreviewVisibleWidth;
                i = keyPreviewVisibleHeight + ((MoreKeysKeyboardParams) this.mParams).mVerticalGap;
            } else {
                maxKeyWidth = getMaxKeyWidth(key, ((MoreKeysKeyboardParams) this.mParams).mDefaultKeyWidth, context.getResources().getDimension(R.dimen.config_more_keys_keyboard_key_horizontal_padding) + (key.hasLabelsInMoreKeys() ? ((MoreKeysKeyboardParams) this.mParams).mDefaultKeyWidth * 0.2f : 0.0f), paintToMeasure);
                i = keyboard.mMostCommonKeyHeight;
            }
            ((MoreKeysKeyboardParams) this.mParams).setParameters(key.getMoreKeys().length, key.getMoreKeysColumnNumber(), maxKeyWidth, i, key.getX() + (key.getWidth() / 2), keyboard.mId.mWidth, key.isMoreKeysFixedColumn(), key.isMoreKeysFixedOrder(), key.needsDividersInMoreKeys() ? (int) (maxKeyWidth * 0.2f) : 0);
        }

        private static int getMaxKeyWidth(final Key parentKey, final int minKeyWidth, final float padding, final Paint paint) {
            for (MoreKeySpec moreKeySpec : parentKey.getMoreKeys()) {
                String str = moreKeySpec.mLabel;
                if (str != null && StringUtils.codePointCount(str) > 1) {
                    minKeyWidth = Math.max(minKeyWidth, (int) (TypefaceUtils.getStringWidth(str, paint) + padding));
                }
            }
            return minKeyWidth;
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyboardBuilder
        @Nonnull
        public MoreKeysKeyboard build() {
            int i;
            MoreKeysKeyboardParams moreKeysKeyboardParams = (MoreKeysKeyboardParams) this.mParams;
            int moreKeyLabelFlags = this.mParentKey.getMoreKeyLabelFlags();
            MoreKeySpec[] moreKeys = this.mParentKey.getMoreKeys();
            for (int i2 = 0; i2 < moreKeys.length; i2++) {
                MoreKeySpec moreKeySpec = moreKeys[i2];
                int i3 = i2 / moreKeysKeyboardParams.mNumColumns;
                int x = moreKeysKeyboardParams.getX(i2, i3);
                int y = moreKeysKeyboardParams.getY(i3);
                Key keyBuildKey = moreKeySpec.buildKey(x, y, moreKeyLabelFlags, moreKeysKeyboardParams);
                moreKeysKeyboardParams.markAsEdgeKey(keyBuildKey, i3);
                moreKeysKeyboardParams.onAddKey(keyBuildKey);
                int columnPos = moreKeysKeyboardParams.getColumnPos(i2);
                if (moreKeysKeyboardParams.mDividerWidth > 0 && columnPos != 0) {
                    if (columnPos > 0) {
                        i = x - moreKeysKeyboardParams.mDividerWidth;
                    } else {
                        i = x + moreKeysKeyboardParams.mDefaultKeyWidth;
                    }
                    moreKeysKeyboardParams.onAddKey(new MoreKeyDivider(moreKeysKeyboardParams, i, y, moreKeysKeyboardParams.mDividerWidth, moreKeysKeyboardParams.mDefaultRowHeight));
                }
            }
            return new MoreKeysKeyboard(moreKeysKeyboardParams);
        }
    }

    public static class MoreKeyDivider extends Key.Spacer {
        public MoreKeyDivider(final KeyboardParams params, final int x, final int y, final int width, final int height) {
            super(params, x, y, width, height);
        }
    }
}
