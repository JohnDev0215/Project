package com.google.android.flexbox;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class FlexLine {
    int mCrossSize;
    int mDividerLengthInMainSize;
    int mFirstIndex;
    int mGoneItemCount;
    int mItemCount;
    int mLastIndex;
    int mMainSize;
    int mMaxBaseline;
    int mSumCrossSizeBefore;
    float mTotalFlexGrow;
    float mTotalFlexShrink;
    int mLeft = Integer.MAX_VALUE;
    int mTop = Integer.MAX_VALUE;
    int mRight = Integer.MIN_VALUE;
    int mBottom = Integer.MIN_VALUE;
    List<Integer> mIndicesAlignSelfStretch = new ArrayList();

    FlexLine() {
    }

    public int getMainSize() {
        return this.mMainSize;
    }

    public int getCrossSize() {
        return this.mCrossSize;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public int getItemCountNotGone() {
        return this.mItemCount - this.mGoneItemCount;
    }

    public float getTotalFlexGrow() {
        return this.mTotalFlexGrow;
    }

    public float getTotalFlexShrink() {
        return this.mTotalFlexShrink;
    }

    public int getFirstIndex() {
        return this.mFirstIndex;
    }
}
