package com.android.car.ui.recyclerview;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class RangeFilterImpl implements RangeFilter {
    private static final String TAG = "RangeFilterImpl";
    private final RecyclerView.Adapter<?> mAdapter;
    private final int mMaxItems;
    private final int mMaxItemsFirstHalf;
    private final int mMaxItemsSecondHalf;
    private int mPivotIndex;
    private final ListRange mRange = new ListRange();
    private int mUnlimitedCount;

    public RangeFilterImpl(RecyclerView.Adapter<?> adapter, int maxItems) {
        this.mAdapter = adapter;
        if (maxItems <= 0) {
            this.mMaxItemsFirstHalf = 0;
            this.mMaxItemsSecondHalf = 0;
            this.mMaxItems = 0;
        } else {
            int i = maxItems / 2;
            this.mMaxItemsFirstHalf = i;
            this.mMaxItemsSecondHalf = maxItems - i;
            this.mMaxItems = maxItems;
        }
    }

    public String toString() {
        return "RangeFilterImpl{mMaxItemsFirstHalf=" + this.mMaxItemsFirstHalf + "mMaxItemsSecondHalf=" + this.mMaxItemsSecondHalf + ", mUnlimitedCount=" + this.mUnlimitedCount + ", mPivotIndex=" + this.mPivotIndex + ", mRange=" + this.mRange.toString() + '}';
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public int getFilteredCount() {
        return this.mRange.mLimitedCount;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void invalidateMessagePositions() {
        if (this.mRange.mClampedHead > 0) {
            this.mAdapter.notifyItemChanged(0);
        }
        if (this.mRange.mClampedTail > 0) {
            this.mAdapter.notifyItemChanged(getFilteredCount() - 1);
        }
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void applyFilter() {
        if (this.mRange.isTailClamped()) {
            this.mAdapter.notifyItemInserted(this.mUnlimitedCount);
            this.mAdapter.notifyItemRangeRemoved(this.mRange.mEndIndex, this.mUnlimitedCount - this.mRange.mEndIndex);
        }
        if (this.mRange.isHeadClamped()) {
            this.mAdapter.notifyItemRangeRemoved(0, this.mRange.mStartIndex);
            this.mAdapter.notifyItemInserted(0);
        }
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void removeFilter() {
        if (this.mRange.isTailClamped()) {
            this.mAdapter.notifyItemRemoved(this.mRange.mLimitedCount - 1);
            this.mAdapter.notifyItemRangeInserted(this.mRange.mLimitedCount - 1, this.mUnlimitedCount - this.mRange.mEndIndex);
        }
        if (this.mRange.isHeadClamped()) {
            this.mAdapter.notifyItemRangeInserted(1, this.mRange.mStartIndex);
            this.mAdapter.notifyItemRemoved(0);
        }
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void recompute(int newCount, int pivotIndex) {
        if (pivotIndex < 0 || newCount <= pivotIndex) {
            Log.e(TAG, "Invalid pivotIndex: " + pivotIndex + " newCount: " + newCount);
            pivotIndex = 0;
        }
        this.mUnlimitedCount = newCount;
        this.mPivotIndex = pivotIndex;
        this.mRange.mClampedHead = 0;
        this.mRange.mClampedTail = 0;
        int i = this.mUnlimitedCount;
        int i2 = this.mMaxItems;
        if (i <= i2) {
            this.mRange.mStartIndex = 0;
            this.mRange.mEndIndex = this.mUnlimitedCount;
            this.mRange.mLimitedCount = this.mUnlimitedCount;
            return;
        }
        if (i2 <= 0) {
            this.mRange.mStartIndex = 0;
            this.mRange.mEndIndex = 0;
            this.mRange.mLimitedCount = 1;
            this.mRange.mClampedTail = 1;
            return;
        }
        int i3 = this.mPivotIndex;
        int i4 = this.mMaxItemsFirstHalf;
        if (i3 <= i4) {
            this.mRange.mStartIndex = 0;
            this.mRange.mEndIndex = this.mMaxItems;
            this.mRange.mLimitedCount = this.mMaxItems + 1;
            this.mRange.mClampedTail = 1;
            return;
        }
        if ((i - 1) - i3 <= this.mMaxItemsSecondHalf) {
            this.mRange.mEndIndex = i;
            ListRange listRange = this.mRange;
            listRange.mStartIndex = listRange.mEndIndex - this.mMaxItems;
            this.mRange.mLimitedCount = this.mMaxItems + 1;
            this.mRange.mClampedHead = 1;
            return;
        }
        this.mRange.mStartIndex = i3 - i4;
        this.mRange.mEndIndex = this.mPivotIndex + this.mMaxItemsSecondHalf;
        this.mRange.mLimitedCount = this.mMaxItems + 2;
        this.mRange.mClampedHead = 1;
        this.mRange.mClampedTail = 1;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void notifyPivotIndexChanged(int pivotIndex) {
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public int indexToPosition(int index) {
        if (this.mRange.mStartIndex <= index && index < this.mRange.mEndIndex) {
            return this.mRange.indexToPosition(index);
        }
        return -1;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public int positionToIndex(int position) {
        return this.mRange.positionToIndex(position);
    }

    ListRange getRange() {
        return this.mRange;
    }

    static class ListRange {
        public static final int INVALID_INDEX = -1;
        int mClampedHead;
        int mClampedTail;
        int mEndIndex;
        int mLimitedCount;
        int mStartIndex;

        ListRange() {
        }

        public void copyFrom(ListRange range) {
            this.mStartIndex = range.mStartIndex;
            this.mEndIndex = range.mEndIndex;
            this.mClampedHead = range.mClampedHead;
            this.mClampedTail = range.mClampedTail;
            this.mLimitedCount = range.mLimitedCount;
        }

        public String toString() {
            return "ListRange{mStartIndex=" + this.mStartIndex + ", mEndIndex=" + this.mEndIndex + ", mClampedHead=" + this.mClampedHead + ", mClampedTail=" + this.mClampedTail + ", mLimitedCount=" + this.mLimitedCount + '}';
        }

        public boolean intersects(ListRange range) {
            return range.mEndIndex > this.mStartIndex && this.mEndIndex > range.mStartIndex;
        }

        public int indexToPosition(int index) {
            return (index - this.mStartIndex) + this.mClampedHead;
        }

        public int positionToIndex(int position) {
            int i = position - this.mClampedHead;
            int i2 = this.mStartIndex;
            int index = i + i2;
            if (index < i2 || this.mEndIndex <= index) {
                return -1;
            }
            return index;
        }

        public boolean isHeadClamped() {
            return this.mClampedHead == 1;
        }

        public boolean isTailClamped() {
            return this.mClampedTail == 1;
        }
    }
}
