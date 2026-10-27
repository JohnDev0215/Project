package androidx.leanback.widget;

import androidx.collection.CircularIntArray;
import androidx.leanback.widget.Grid;
import androidx.recyclerview.widget.RecyclerView;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
class SingleRow extends Grid {
    private final Grid.Location mTmpLocation = new Grid.Location(0);

    SingleRow() {
        setNumRows(1);
    }

    @Override // androidx.leanback.widget.Grid
    public final Grid.Location getLocation(int index) {
        return this.mTmpLocation;
    }

    @Override // androidx.leanback.widget.Grid
    public final void debugPrint(PrintWriter pw) {
        pw.print("SingleRow<");
        pw.print(this.mFirstVisibleIndex);
        pw.print(",");
        pw.print(this.mLastVisibleIndex);
        pw.print(">");
        pw.println();
    }

    int getStartIndexForAppend() {
        if (this.mLastVisibleIndex >= 0) {
            return this.mLastVisibleIndex + 1;
        }
        if (this.mStartIndex != -1) {
            return Math.min(this.mStartIndex, this.mProvider.getCount() - 1);
        }
        return 0;
    }

    int getStartIndexForPrepend() {
        if (this.mFirstVisibleIndex >= 0) {
            return this.mFirstVisibleIndex - 1;
        }
        if (this.mStartIndex != -1) {
            return Math.min(this.mStartIndex, this.mProvider.getCount() - 1);
        }
        return this.mProvider.getCount() - 1;
    }

    @Override // androidx.leanback.widget.Grid
    protected final boolean prependVisibleItems(int toLimit, boolean oneColumnMode) {
        int edge;
        int edge2;
        if (this.mProvider.getCount() == 0) {
            return false;
        }
        if (!oneColumnMode && checkPrependOverLimit(toLimit)) {
            return false;
        }
        boolean filledOne = false;
        int minIndex = this.mProvider.getMinIndex();
        for (int index = getStartIndexForPrepend(); index >= minIndex; index--) {
            int size = this.mProvider.createItem(index, false, this.mTmpItem, false);
            if (this.mFirstVisibleIndex < 0 || this.mLastVisibleIndex < 0) {
                int edge3 = this.mReversedFlow ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                this.mFirstVisibleIndex = index;
                this.mLastVisibleIndex = index;
                edge = edge3;
            } else {
                if (this.mReversedFlow) {
                    edge2 = this.mProvider.getEdge(index + 1) + this.mSpacing + size;
                } else {
                    edge2 = (this.mProvider.getEdge(index + 1) - this.mSpacing) - size;
                }
                this.mFirstVisibleIndex = index;
                edge = edge2;
            }
            this.mProvider.addItem(this.mTmpItem[0], index, size, 0, edge);
            filledOne = true;
            if (oneColumnMode || checkPrependOverLimit(toLimit)) {
                break;
            }
        }
        return filledOne;
    }

    @Override // androidx.leanback.widget.Grid
    protected final boolean appendVisibleItems(int toLimit, boolean oneColumnMode) {
        int edge;
        int edge2;
        if (this.mProvider.getCount() == 0) {
            return false;
        }
        if (!oneColumnMode && checkAppendOverLimit(toLimit)) {
            return false;
        }
        boolean filledOne = false;
        for (int index = getStartIndexForAppend(); index < this.mProvider.getCount(); index++) {
            int size = this.mProvider.createItem(index, true, this.mTmpItem, false);
            if (this.mFirstVisibleIndex < 0 || this.mLastVisibleIndex < 0) {
                int edge3 = this.mReversedFlow ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                this.mFirstVisibleIndex = index;
                this.mLastVisibleIndex = index;
                edge = edge3;
            } else {
                if (this.mReversedFlow) {
                    edge2 = (this.mProvider.getEdge(index - 1) - this.mProvider.getSize(index - 1)) - this.mSpacing;
                } else {
                    edge2 = this.mProvider.getEdge(index - 1) + this.mProvider.getSize(index - 1) + this.mSpacing;
                }
                this.mLastVisibleIndex = index;
                edge = edge2;
            }
            this.mProvider.addItem(this.mTmpItem[0], index, size, 0, edge);
            filledOne = true;
            if (oneColumnMode || checkAppendOverLimit(toLimit)) {
                break;
            }
        }
        return filledOne;
    }

    @Override // androidx.leanback.widget.Grid
    public void collectAdjacentPrefetchPositions(int fromLimit, int da, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int indexToPrefetch;
        int itemSizeWithSpace;
        if (!this.mReversedFlow ? da < 0 : da > 0) {
            if (getFirstVisibleIndex() == 0) {
                return;
            }
            indexToPrefetch = getStartIndexForPrepend();
            itemSizeWithSpace = this.mProvider.getEdge(this.mFirstVisibleIndex) + (this.mReversedFlow ? this.mSpacing : -this.mSpacing);
        } else {
            int indexToPrefetch2 = getLastVisibleIndex();
            if (indexToPrefetch2 == this.mProvider.getCount() - 1) {
                return;
            }
            indexToPrefetch = getStartIndexForAppend();
            int itemSizeWithSpace2 = this.mProvider.getSize(this.mLastVisibleIndex) + this.mSpacing;
            itemSizeWithSpace = this.mProvider.getEdge(this.mLastVisibleIndex) + (this.mReversedFlow ? -itemSizeWithSpace2 : itemSizeWithSpace2);
        }
        int distance = Math.abs(itemSizeWithSpace - fromLimit);
        layoutPrefetchRegistry.addPosition(indexToPrefetch, distance);
    }

    @Override // androidx.leanback.widget.Grid
    public final CircularIntArray[] getItemPositionsInRows(int startPos, int endPos) {
        this.mTmpItemPositionsInRows[0].clear();
        this.mTmpItemPositionsInRows[0].addLast(startPos);
        this.mTmpItemPositionsInRows[0].addLast(endPos);
        return this.mTmpItemPositionsInRows;
    }

    @Override // androidx.leanback.widget.Grid
    protected final int findRowMin(boolean findLarge, int indexLimit, int[] indices) {
        if (indices != null) {
            indices[0] = 0;
            indices[1] = indexLimit;
        }
        return this.mReversedFlow ? this.mProvider.getEdge(indexLimit) - this.mProvider.getSize(indexLimit) : this.mProvider.getEdge(indexLimit);
    }

    @Override // androidx.leanback.widget.Grid
    protected final int findRowMax(boolean findLarge, int indexLimit, int[] indices) {
        if (indices != null) {
            indices[0] = 0;
            indices[1] = indexLimit;
        }
        return this.mReversedFlow ? this.mProvider.getEdge(indexLimit) : this.mProvider.getEdge(indexLimit) + this.mProvider.getSize(indexLimit);
    }
}
