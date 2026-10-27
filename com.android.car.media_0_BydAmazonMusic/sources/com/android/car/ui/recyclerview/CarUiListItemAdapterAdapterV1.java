package com.android.car.ui.recyclerview;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import spi.pc;

/* JADX INFO: loaded from: classes2.dex */
public final class CarUiListItemAdapterAdapterV1 extends RecyclerView.Adapter<ViewHolderWrapper> {

    @NonNull
    private spi.e mAdapter;

    public static class ViewHolderWrapper extends RecyclerView.ViewHolder {

        @NonNull
        private final pc mViewHolder;

        public ViewHolderWrapper(@NonNull pc pcVar) {
            super(pcVar.getItemView());
            this.mViewHolder = pcVar;
            setIsRecyclable(pcVar.isRecyclable());
        }

        @NonNull
        public pc getViewHolder() {
            return this.mViewHolder;
        }
    }

    public CarUiListItemAdapterAdapterV1(@NonNull spi.e eVar) {
        this.mAdapter = eVar;
        super.setHasStableIds(eVar.hasStableIds());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mAdapter.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.mAdapter.getItemId(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mAdapter.getItemViewType(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mAdapter.onAttachedToRecyclerView(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.mAdapter.onDetachedFromRecyclerView(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolderWrapper viewHolderWrapper, int i) {
        this.mAdapter.bindViewHolder(viewHolderWrapper.getViewHolder(), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolderWrapper onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderWrapper(this.mAdapter.createViewHolder(viewGroup, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(ViewHolderWrapper viewHolderWrapper) {
        return this.mAdapter.onFailedToRecycleView(viewHolderWrapper.getViewHolder());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(ViewHolderWrapper viewHolderWrapper) {
        this.mAdapter.onViewAttachedToWindow(viewHolderWrapper.getViewHolder());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(ViewHolderWrapper viewHolderWrapper) {
        this.mAdapter.onViewDetachedFromWindow(viewHolderWrapper.getViewHolder());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(ViewHolderWrapper viewHolderWrapper) {
        this.mAdapter.onViewRecycled(viewHolderWrapper.getViewHolder());
    }
}
