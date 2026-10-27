package com.android.inputmethod.latin.suggestions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.internal.KeyboardBuilder;
import com.android.inputmethod.keyboard.internal.KeyboardIconsSet;
import com.android.inputmethod.keyboard.internal.KeyboardParams;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.utils.TypefaceUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class MoreSuggestions extends Keyboard {
    public final SuggestedWords mSuggestedWords;

    MoreSuggestions(final MoreSuggestionsParam params, final SuggestedWords suggestedWords) {
        super(params);
        this.mSuggestedWords = suggestedWords;
    }

    private static final class MoreSuggestionsParam extends KeyboardParams {
        private static final int[][] COLUMN_ORDER_TO_NUMBER = {new int[]{0}, new int[]{1, 0}, new int[]{1, 0, 2}};
        private static final int MAX_COLUMNS_IN_ROW = 3;
        public Drawable mDivider;
        public int mDividerWidth;
        private int mNumRows;
        private final int[] mWidths = new int[18];
        private final int[] mRowNumbers = new int[18];
        private final int[] mColumnOrders = new int[18];
        private final int[] mNumColumnsInRow = new int[18];

        public int layout(final SuggestedWords suggestedWords, final int fromIndex, final int maxWidth, final int minWidth, final int maxRow, final Paint paint, final Resources res) {
            String label;
            clearKeys();
            Drawable drawable = res.getDrawable(R.drawable.more_suggestions_divider);
            this.mDivider = drawable;
            this.mDividerWidth = drawable.getIntrinsicWidth();
            float dimension = res.getDimension(R.dimen.config_more_suggestions_key_horizontal_padding);
            int iMin = Math.min(suggestedWords.size(), 18);
            int i = fromIndex;
            int i2 = i;
            int i3 = 0;
            while (i < iMin) {
                if (MoreSuggestions.isIndexSubjectToAutoCorrection(suggestedWords, i)) {
                    label = suggestedWords.getLabel(0);
                } else {
                    label = suggestedWords.getLabel(i);
                }
                this.mWidths[i] = (int) (TypefaceUtils.getStringWidth(label, paint) + dimension);
                int i4 = i - i2;
                int i5 = i4 + 1;
                int i6 = (maxWidth - (this.mDividerWidth * (i5 - 1))) / i5;
                if (i5 > 3 || !fitInWidth(i2, i + 1, i6)) {
                    int i7 = i3 + 1;
                    if (i7 >= maxRow) {
                        break;
                    }
                    this.mNumColumnsInRow[i3] = i4;
                    i2 = i;
                    i3 = i7;
                }
                this.mColumnOrders[i] = i - i2;
                this.mRowNumbers[i] = i3;
                i++;
            }
            this.mNumColumnsInRow[i3] = i - i2;
            this.mNumRows = i3 + 1;
            int iMax = Math.max(minWidth, calcurateMaxRowWidth(fromIndex, i));
            this.mOccupiedWidth = iMax;
            this.mBaseWidth = iMax;
            int i8 = (this.mNumRows * this.mDefaultRowHeight) + this.mVerticalGap;
            this.mOccupiedHeight = i8;
            this.mBaseHeight = i8;
            return i - fromIndex;
        }

        private boolean fitInWidth(final int startIndex, final int endIndex, final int width) {
            while (startIndex < endIndex) {
                if (this.mWidths[startIndex] > width) {
                    return false;
                }
                startIndex++;
            }
            return true;
        }

        private int calcurateMaxRowWidth(final int startIndex, final int endIndex) {
            int iMax = 0;
            for (int i = 0; i < this.mNumRows; i++) {
                int i2 = this.mNumColumnsInRow[i];
                int iMax2 = 0;
                while (startIndex < endIndex && this.mRowNumbers[startIndex] == i) {
                    iMax2 = Math.max(iMax2, this.mWidths[startIndex]);
                    startIndex++;
                }
                iMax = Math.max(iMax, (iMax2 * i2) + (this.mDividerWidth * (i2 - 1)));
            }
            return iMax;
        }

        public int getNumColumnInRow(final int index) {
            return this.mNumColumnsInRow[this.mRowNumbers[index]];
        }

        public int getColumnNumber(final int index) {
            return COLUMN_ORDER_TO_NUMBER[getNumColumnInRow(index) - 1][this.mColumnOrders[index]];
        }

        public int getX(final int index) {
            return getColumnNumber(index) * (getWidth(index) + this.mDividerWidth);
        }

        public int getY(final int index) {
            return (((this.mNumRows - 1) - this.mRowNumbers[index]) * this.mDefaultRowHeight) + this.mTopPadding;
        }

        public int getWidth(final int index) {
            int numColumnInRow = getNumColumnInRow(index);
            return (this.mOccupiedWidth - (this.mDividerWidth * (numColumnInRow - 1))) / numColumnInRow;
        }

        public void markAsEdgeKey(final Key key, final int index) {
            int i = this.mRowNumbers[index];
            if (i == 0) {
                key.markAsBottomEdge(this);
            }
            if (i == this.mNumRows - 1) {
                key.markAsTopEdge(this);
            }
            int i2 = this.mNumColumnsInRow[i];
            int columnNumber = getColumnNumber(index);
            if (columnNumber == 0) {
                key.markAsLeftEdge(this);
            }
            if (columnNumber == i2 - 1) {
                key.markAsRightEdge(this);
            }
        }
    }

    static boolean isIndexSubjectToAutoCorrection(final SuggestedWords suggestedWords, final int index) {
        return suggestedWords.mWillAutoCorrect && index == 1;
    }

    public static final class Builder extends KeyboardBuilder<MoreSuggestionsParam> {
        private int mFromIndex;
        private final MoreSuggestionsView mPaneView;
        private SuggestedWords mSuggestedWords;
        private int mToIndex;

        public Builder(final Context context, final MoreSuggestionsView paneView) {
            super(context, new MoreSuggestionsParam());
            this.mPaneView = paneView;
        }

        public Builder layout(final SuggestedWords suggestedWords, final int fromIndex, final int maxWidth, final int minWidth, final int maxRow, final Keyboard parentKeyboard) {
            load(R.xml.kbd_suggestions_pane_template, parentKeyboard.mId);
            MoreSuggestionsParam moreSuggestionsParam = (MoreSuggestionsParam) this.mParams;
            MoreSuggestionsParam moreSuggestionsParam2 = (MoreSuggestionsParam) this.mParams;
            int i = parentKeyboard.mVerticalGap / 2;
            moreSuggestionsParam2.mTopPadding = i;
            moreSuggestionsParam.mVerticalGap = i;
            this.mPaneView.updateKeyboardGeometry(((MoreSuggestionsParam) this.mParams).mDefaultRowHeight);
            int iLayout = ((MoreSuggestionsParam) this.mParams).layout(suggestedWords, fromIndex, maxWidth, minWidth, maxRow, this.mPaneView.newLabelPaint(null), this.mResources);
            this.mFromIndex = fromIndex;
            this.mToIndex = fromIndex + iLayout;
            this.mSuggestedWords = suggestedWords;
            return this;
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyboardBuilder
        public MoreSuggestions build() {
            String label;
            String debugString;
            MoreSuggestionsParam moreSuggestionsParam = (MoreSuggestionsParam) this.mParams;
            for (int i = this.mFromIndex; i < this.mToIndex; i++) {
                int x = moreSuggestionsParam.getX(i);
                int y = moreSuggestionsParam.getY(i);
                int width = moreSuggestionsParam.getWidth(i);
                if (MoreSuggestions.isIndexSubjectToAutoCorrection(this.mSuggestedWords, i)) {
                    label = this.mSuggestedWords.getLabel(0);
                    debugString = this.mSuggestedWords.getDebugString(0);
                } else {
                    label = this.mSuggestedWords.getLabel(i);
                    debugString = this.mSuggestedWords.getDebugString(i);
                }
                MoreSuggestionKey moreSuggestionKey = new MoreSuggestionKey(label, debugString, i, moreSuggestionsParam);
                moreSuggestionsParam.markAsEdgeKey(moreSuggestionKey, i);
                moreSuggestionsParam.onAddKey(moreSuggestionKey);
                if (moreSuggestionsParam.getColumnNumber(i) < moreSuggestionsParam.getNumColumnInRow(i) - 1) {
                    moreSuggestionsParam.onAddKey(new Divider(moreSuggestionsParam, moreSuggestionsParam.mDivider, x + width, y, moreSuggestionsParam.mDividerWidth, moreSuggestionsParam.mDefaultRowHeight));
                }
            }
            return new MoreSuggestions(moreSuggestionsParam, this.mSuggestedWords);
        }
    }

    static final class MoreSuggestionKey extends Key {
        public final int mSuggestedWordIndex;

        public MoreSuggestionKey(final String word, final String info, final int index, final MoreSuggestionsParam params) {
            super(word, 0, -4, word, info, 0, 1, params.getX(index), params.getY(index), params.getWidth(index), params.mDefaultRowHeight, params.mHorizontalGap, params.mVerticalGap);
            this.mSuggestedWordIndex = index;
        }
    }

    private static final class Divider extends Key.Spacer {
        private final Drawable mIcon;

        public Divider(final KeyboardParams params, final Drawable icon, final int x, final int y, final int width, final int height) {
            super(params, x, y, width, height);
            this.mIcon = icon;
        }

        @Override // com.android.inputmethod.keyboard.Key
        public Drawable getIcon(final KeyboardIconsSet iconSet, final int alpha) {
            this.mIcon.setAlpha(128);
            return this.mIcon;
        }
    }
}
