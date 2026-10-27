package com.android.car.ui.recyclerview;

import android.util.Log;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ContentLimitingAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ContentLimiting {
    private static final int SCROLLING_LIMITED_MESSAGE_VIEW_TYPE = Integer.MAX_VALUE;
    private static final String TAG = "ContentLimitingAdapter";
    private RecyclerView mRecyclerView;
    private Integer mScrollingLimitedMessageResId;
    private RangeFilter mRangeFilter = new PassThroughFilter();
    private boolean mIsLimiting = false;

    protected abstract int getUnrestrictedItemCount();

    protected abstract void onBindViewHolderImpl(T t, int i);

    protected abstract T onCreateViewHolderImpl(ViewGroup viewGroup, int i);

    public int getScrollingLimitedMessageViewType() {
        return Integer.MAX_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == getScrollingLimitedMessageViewType()) {
            return ScrollingLimitedViewHolder.create(parent);
        }
        return onCreateViewHolderImpl(parent, viewType);
    }

    protected int indexToPosition(int index) {
        return this.mRangeFilter.indexToPosition(index);
    }

    protected int positionToIndex(int position) {
        return this.mRangeFilter.positionToIndex(position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ScrollingLimitedViewHolder) {
            ScrollingLimitedViewHolder vh = (ScrollingLimitedViewHolder) holder;
            vh.bind(this.mScrollingLimitedMessageResId);
            return;
        }
        int index = this.mRangeFilter.positionToIndex(position);
        if (index != -1) {
            int size = getUnrestrictedItemCount();
            if (index >= 0 && index < size) {
                onBindViewHolderImpl(holder, index);
                return;
            }
            Log.e(TAG, "onBindViewHolder pos: " + position + " gave index: " + index + " out of bounds size: " + size + " " + this.mRangeFilter.toString());
            return;
        }
        Log.e(TAG, "onBindViewHolder invalid position " + position + " " + this.mRangeFilter.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int position) {
        if (this.mRangeFilter.positionToIndex(position) == -1) {
            return getScrollingLimitedMessageViewType();
        }
        return getItemViewTypeImpl(this.mRangeFilter.positionToIndex(position));
    }

    protected int getItemViewTypeImpl(int position) {
        return super.getItemViewType(position);
    }

    @Deprecated
    protected int getScrollingLimitedMessagePosition() {
        return getItemCount() - 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        if (this.mIsLimiting) {
            return this.mRangeFilter.getFilteredCount();
        }
        return getUnrestrictedItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (!(holder instanceof ScrollingLimitedViewHolder)) {
            onViewRecycledImpl(holder);
        }
    }

    protected void onViewRecycledImpl(T holder) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        if (!(holder instanceof ScrollingLimitedViewHolder)) {
            return onFailedToRecycleViewImpl(holder);
        }
        return super.onFailedToRecycleView(holder);
    }

    protected boolean onFailedToRecycleViewImpl(T holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (!(holder instanceof ScrollingLimitedViewHolder)) {
            onViewAttachedToWindowImpl(holder);
        }
    }

    protected void onViewAttachedToWindowImpl(T holder) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (!(holder instanceof ScrollingLimitedViewHolder)) {
            onViewDetachedFromWindowImpl(holder);
        }
    }

    protected void onViewDetachedFromWindowImpl(T holder) {
    }

    @Override // com.android.car.ui.recyclerview.ContentLimiting
    public void setMaxItems(int maxItems) {
        if (maxItems >= 0) {
            if (this.mRangeFilter != null && this.mIsLimiting) {
                Log.w(TAG, "A new filter range received before parked");
                this.mRangeFilter.removeFilter();
            }
            this.mIsLimiting = true;
            RangeFilterImpl rangeFilterImpl = new RangeFilterImpl(this, maxItems);
            this.mRangeFilter = rangeFilterImpl;
            rangeFilterImpl.recompute(getUnrestrictedItemCount(), computeAnchorIndexWhenRestricting());
            this.mRangeFilter.applyFilter();
            autoScrollWhenRestricted();
            return;
        }
        this.mRangeFilter.removeFilter();
        this.mIsLimiting = false;
        PassThroughFilter passThroughFilter = new PassThroughFilter();
        this.mRangeFilter = passThroughFilter;
        passThroughFilter.recompute(getUnrestrictedItemCount(), 0);
    }

    protected int getScrollToPositionWhenRestricted() {
        return -1;
    }

    private void autoScrollWhenRestricted() {
        int scrollToPosition = getScrollToPositionWhenRestricted();
        if (scrollToPosition >= 0) {
            RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (layoutManager != null) {
                this.mRecyclerView.getLayoutManager().scrollToPosition(scrollToPosition);
            }
        }
    }

    protected int computeAnchorIndexWhenRestricting() {
        return 0;
    }

    public void updateUnderlyingDataChanged(int unrestrictedCount, int newAnchorIndex) {
        this.mRangeFilter.recompute(unrestrictedCount, newAnchorIndex);
    }

    public void notifyLimitingAnchorChanged(int newPivotIndex) {
        this.mRangeFilter.notifyPivotIndexChanged(newPivotIndex);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimiting
    public void setScrollingLimitedMessageResId(int resId) {
        Integer num = this.mScrollingLimitedMessageResId;
        if (num == null || num.intValue() != resId) {
            this.mScrollingLimitedMessageResId = Integer.valueOf(resId);
            this.mRangeFilter.invalidateMessagePositions();
        }
    }
}
