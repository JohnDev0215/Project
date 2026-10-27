package com.android.car.ui.recyclerview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class DelegatingContentLimitingAdapter<T extends RecyclerView.ViewHolder> extends ContentLimitingAdapter<T> {
    public final RecyclerView.Adapter<T> e;
    public final int f;

    @IdRes
    public final int g;

    @NonNull
    public final DelegatingContentLimitingAdapter<T>.c h;

    public interface b {
        int a();

        int b();
    }

    public DelegatingContentLimitingAdapter(RecyclerView.Adapter<T> adapter, @IdRes int i) {
        this(adapter, i, Integer.MAX_VALUE, -1);
    }

    @Override // spi.w2
    @IdRes
    public final int c() {
        return this.g;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public int d() {
        RecyclerView.Adapter<T> adapter = this.e;
        if (adapter instanceof b) {
            return ((b) adapter).a();
        }
        return 0;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final int e(int i) {
        return this.e.getItemViewType(i);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final int f() {
        RecyclerView.Adapter<T> adapter = this.e;
        if (adapter instanceof b) {
            return ((b) adapter).b();
        }
        return -1;
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final int g() {
        return this.f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return this.e.getItemId(i);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final int h() {
        return this.e.getItemCount();
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final void i(T t, int i) {
        this.e.onBindViewHolder(t, i);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    @NonNull
    public final T j(@NonNull ViewGroup viewGroup, int i) {
        return (T) this.e.onCreateViewHolder(viewGroup, i);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final boolean k(@NonNull T t) {
        return this.e.onFailedToRecycleView(t);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final void l(@NonNull T t) {
        this.e.onViewAttachedToWindow(t);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final void m(@NonNull T t) {
        this.e.onViewDetachedFromWindow(t);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final void n(@NonNull T t) {
        this.e.onViewRecycled(t);
    }

    @Override // com.android.car.ui.recyclerview.ContentLimitingAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.c = recyclerView;
        this.e.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.e.onDetachedFromRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver adapterDataObserver) {
        if (!hasObservers()) {
            this.e.registerAdapterDataObserver(this.h);
        }
        super.registerAdapterDataObserver(adapterDataObserver);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void setHasStableIds(boolean z) {
        this.e.setHasStableIds(z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.unregisterAdapterDataObserver(adapterDataObserver);
        if (hasObservers()) {
            return;
        }
        this.e.unregisterAdapterDataObserver(this.h);
    }

    public class c extends RecyclerView.AdapterDataObserver {
        private c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        @SuppressLint({"NotifyDataSetChanged"})
        public final void onChanged() {
            DelegatingContentLimitingAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeChanged(i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeInserted(i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        @SuppressLint({"NotifyDataSetChanged"})
        public final void onItemRangeMoved(int i, int i2, int i3) {
            DelegatingContentLimitingAdapter.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeRemoved(i, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            DelegatingContentLimitingAdapter.this.notifyItemRangeChanged(i, i2, obj);
        }
    }

    @Deprecated
    public DelegatingContentLimitingAdapter(RecyclerView.Adapter<T> adapter, @IdRes int i, int i2, int i3) {
        this.e = adapter;
        this.g = i;
        this.f = i2;
        this.h = new c();
    }
}
