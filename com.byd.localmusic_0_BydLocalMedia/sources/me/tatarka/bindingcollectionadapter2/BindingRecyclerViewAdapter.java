package me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.OnRebindCallback;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BindingCollectionAdapter<T> {
    private static final Object DATA_INVALIDATION = new Object();
    private WeakReferenceOnListChangedCallback<T> callback;
    private LayoutInflater inflater;
    private ItemBinding<? super T> itemBinding;
    private ItemIds<? super T> itemIds;
    private List<T> items;
    private LifecycleOwner lifecycleOwner;
    private RecyclerView recyclerView;
    private ViewHolderFactory viewHolderFactory;

    public interface ItemIds<T> {
        long getItemId(int i, T t);
    }

    public interface ViewHolderFactory {
        RecyclerView.ViewHolder createViewHolder(ViewDataBinding viewDataBinding);
    }

    public BindingRecyclerViewAdapter() {
    }

    public BindingRecyclerViewAdapter(ItemBinding<? super T> itemBinding) {
        this.itemBinding = itemBinding;
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void setItemBinding(ItemBinding<? super T> itemBinding) {
        this.itemBinding = itemBinding;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        if (this.recyclerView != null) {
            for (int i = 0; i < this.recyclerView.getChildCount(); i++) {
                ViewDataBinding binding = DataBindingUtil.getBinding(this.recyclerView.getChildAt(i));
                if (binding != null) {
                    binding.setLifecycleOwner(lifecycleOwner);
                }
            }
        }
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public ItemBinding<? super T> getItemBinding() {
        ItemBinding<? super T> itemBinding = this.itemBinding;
        if (itemBinding != null) {
            return itemBinding;
        }
        throw new NullPointerException("itemBinding == null");
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void setItems(List<T> list) {
        List<T> list2 = this.items;
        if (list2 == list) {
            return;
        }
        if (this.recyclerView != null) {
            if (list2 instanceof ObservableList) {
                ((ObservableList) list2).removeOnListChangedCallback(this.callback);
                this.callback = null;
            }
            if (list instanceof ObservableList) {
                ObservableList observableList = (ObservableList) list;
                WeakReferenceOnListChangedCallback<T> weakReferenceOnListChangedCallback = new WeakReferenceOnListChangedCallback<>(this, observableList);
                this.callback = weakReferenceOnListChangedCallback;
                observableList.addOnListChangedCallback(weakReferenceOnListChangedCallback);
            }
        }
        this.items = list;
        notifyDataSetChanged();
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public T getAdapterItem(int i) {
        return this.items.get(i);
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public ViewDataBinding onCreateBinding(LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        return DataBindingUtil.inflate(layoutInflater, i, viewGroup, false);
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void onBindBinding(ViewDataBinding viewDataBinding, int i, int i2, int i3, T t) {
        tryGetLifecycleOwner();
        if (this.itemBinding.bind(viewDataBinding, t)) {
            viewDataBinding.executePendingBindings();
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            if (lifecycleOwner != null) {
                viewDataBinding.setLifecycleOwner(lifecycleOwner);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (this.recyclerView == null && (this.items instanceof ObservableList)) {
            WeakReferenceOnListChangedCallback<T> weakReferenceOnListChangedCallback = new WeakReferenceOnListChangedCallback<>(this, (ObservableList) this.items);
            this.callback = weakReferenceOnListChangedCallback;
            ((ObservableList) this.items).addOnListChangedCallback(weakReferenceOnListChangedCallback);
        }
        this.recyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (this.recyclerView != null) {
            List<T> list = this.items;
            if (list instanceof ObservableList) {
                ((ObservableList) list).removeOnListChangedCallback(this.callback);
                this.callback = null;
            }
        }
        this.recyclerView = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        ViewDataBinding viewDataBindingOnCreateBinding = onCreateBinding(this.inflater, i, viewGroup);
        final RecyclerView.ViewHolder viewHolderOnCreateViewHolder = onCreateViewHolder(viewDataBindingOnCreateBinding);
        viewDataBindingOnCreateBinding.addOnRebindCallback(new OnRebindCallback() { // from class: me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.1
            @Override // androidx.databinding.OnRebindCallback
            public boolean onPreBind(ViewDataBinding viewDataBinding) {
                return BindingRecyclerViewAdapter.this.recyclerView != null && BindingRecyclerViewAdapter.this.recyclerView.isComputingLayout();
            }

            @Override // androidx.databinding.OnRebindCallback
            public void onCanceled(ViewDataBinding viewDataBinding) {
                int adapterPosition;
                if (BindingRecyclerViewAdapter.this.recyclerView == null || BindingRecyclerViewAdapter.this.recyclerView.isComputingLayout() || (adapterPosition = viewHolderOnCreateViewHolder.getAdapterPosition()) == -1) {
                    return;
                }
                try {
                    BindingRecyclerViewAdapter.this.notifyItemChanged(adapterPosition, BindingRecyclerViewAdapter.DATA_INVALIDATION);
                } catch (IllegalStateException unused) {
                }
            }
        });
        return viewHolderOnCreateViewHolder;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewDataBinding viewDataBinding) {
        ViewHolderFactory viewHolderFactory = this.viewHolderFactory;
        if (viewHolderFactory != null) {
            return viewHolderFactory.createViewHolder(viewDataBinding);
        }
        return new BindingViewHolder(viewDataBinding);
    }

    private static class BindingViewHolder extends RecyclerView.ViewHolder {
        BindingViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder(viewHolder, i, Collections.emptyList());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        ViewDataBinding binding = DataBindingUtil.getBinding(viewHolder.itemView);
        if (isForDataBinding(list)) {
            binding.executePendingBindings();
        } else {
            onBindBinding(binding, this.itemBinding.variableId(), this.itemBinding.layoutRes(), i, this.items.get(i));
        }
    }

    private boolean isForDataBinding(List<Object> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != DATA_INVALIDATION) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        this.itemBinding.onItemBind(i, this.items.get(i));
        return this.itemBinding.layoutRes();
    }

    public void setItemIds(ItemIds<? super T> itemIds) {
        if (this.itemIds != itemIds) {
            this.itemIds = itemIds;
            setHasStableIds(itemIds != null);
        }
    }

    public void setViewHolderFactory(ViewHolderFactory viewHolderFactory) {
        this.viewHolderFactory = viewHolderFactory;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        ItemIds<? super T> itemIds = this.itemIds;
        return itemIds == null ? i : itemIds.getItemId(i, this.items.get(i));
    }

    private void tryGetLifecycleOwner() {
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null || lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            this.lifecycleOwner = Utils.findLifecycleOwner(this.recyclerView);
        }
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingRecyclerViewAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter, ObservableList<T> observableList) {
            this.adapterRef = AdapterReferenceCollector.createRef(bindingRecyclerViewAdapter, observableList, this);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter = this.adapterRef.get();
            if (bindingRecyclerViewAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            bindingRecyclerViewAdapter.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter = this.adapterRef.get();
            if (bindingRecyclerViewAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            bindingRecyclerViewAdapter.notifyItemRangeChanged(i, i2);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter = this.adapterRef.get();
            if (bindingRecyclerViewAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            bindingRecyclerViewAdapter.notifyItemRangeInserted(i, i2);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter = this.adapterRef.get();
            if (bindingRecyclerViewAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            for (int i4 = 0; i4 < i3; i4++) {
                bindingRecyclerViewAdapter.notifyItemMoved(i + i4, i2 + i4);
            }
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter = this.adapterRef.get();
            if (bindingRecyclerViewAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            bindingRecyclerViewAdapter.notifyItemRangeRemoved(i, i2);
        }
    }
}
