package com.android.car.ui.recyclerview;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class DelegatingContentLimitingAdapter<T extends RecyclerView.ViewHolder> extends ContentLimitingAdapter<T> {
    private static final int SCROLLING_LIMITED_MESSAGE_DEFAULT_POSITION_OFFSET = -1;
    private static final int SCROLLING_LIMITED_MESSAGE_VIEW_TYPE = Integer.MAX_VALUE;
    private final int mConfigId;
    private final RecyclerView.Adapter<T> mDelegate;
    private final int mScrollingLimitedMessagePositionOffset;
    private final int mScrollingLimitedMessageViewType;

    public interface ContentLimiting {
        int computeAnchorIndexWhenRestricting();

        int getScrollToPositionWhenRestricted();
    }

    public DelegatingContentLimitingAdapter(RecyclerView.Adapter<T> delegate, int configId) {
        this(delegate, configId, Integer.MAX_VALUE, -1);
    }

    @Deprecated
    public DelegatingContentLimitingAdapter(RecyclerView.Adapter<T> delegate, int configId, int viewType, int offset) {
        this.mDelegate = delegate;
        this.mConfigId = configId;
        this.mScrollingLimitedMessageViewType = viewType;
        this.mScrollingLimitedMessagePositionOffset = offset;
        delegate.registerAdapterDataObserver(new Observer());
    }

    private class Observer extends RecyclerView.AdapterDataObserver {
        private Observer() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            DelegatingContentLimitingAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int positionStart, int itemCount) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            DelegatingContentLimitingAdapter.this.notifyDataSetChanged();
        }
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public T onCreateViewHolderImpl(ViewGroup viewGroup, int i) {
        return (T) this.mDelegate.onCreateViewHolder(viewGroup, i);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public void onBindViewHolderImpl(T holder, int position) {
        this.mDelegate.onBindViewHolder(holder, position);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public int getItemViewTypeImpl(int position) {
        return this.mDelegate.getItemViewType(position);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected void onViewRecycledImpl(T holder) {
        this.mDelegate.onViewRecycled(holder);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected boolean onFailedToRecycleViewImpl(T holder) {
        return this.mDelegate.onFailedToRecycleView(holder);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected void onViewAttachedToWindowImpl(T holder) {
        this.mDelegate.onViewAttachedToWindow(holder);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected void onViewDetachedFromWindowImpl(T holder) {
        this.mDelegate.onViewDetachedFromWindow(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void setHasStableIds(boolean hasStableIds) {
        this.mDelegate.setHasStableIds(hasStableIds);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return this.mDelegate.getItemId(position);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegate.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegate.onDetachedFromRecyclerView(recyclerView);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected int computeAnchorIndexWhenRestricting() {
        RecyclerView.Adapter<T> adapter = this.mDelegate;
        if (adapter instanceof ContentLimiting) {
            return ((ContentLimiting) adapter).computeAnchorIndexWhenRestricting();
        }
        return 0;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected int getScrollToPositionWhenRestricted() {
        RecyclerView.Adapter<T> adapter = this.mDelegate;
        if (adapter instanceof ContentLimiting) {
            return ((ContentLimiting) adapter).getScrollToPositionWhenRestricted();
        }
        return -1;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public int getUnrestrictedItemCount() {
        return this.mDelegate.getItemCount();
    }

    @Override // com.android.car.ui.recyclerview.ContentLimiting
    public int getConfigurationId() {
        return this.mConfigId;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public int getScrollingLimitedMessageViewType() {
        return this.mScrollingLimitedMessageViewType;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    protected int getScrollingLimitedMessagePosition() {
        int i = this.mScrollingLimitedMessagePositionOffset;
        if (i < 0) {
            return getItemCount() + this.mScrollingLimitedMessagePositionOffset;
        }
        return i;
    }
}
