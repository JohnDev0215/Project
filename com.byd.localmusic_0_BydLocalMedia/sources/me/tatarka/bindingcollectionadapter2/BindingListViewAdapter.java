package me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BindingListViewAdapter<T> extends BaseAdapter implements BindingCollectionAdapter<T> {
    private WeakReferenceOnListChangedCallback<T> callback;
    private int dropDownItemLayout;
    private LayoutInflater inflater;
    private ItemBinding<? super T> itemBinding;
    private ItemIds<? super T> itemIds;
    private ItemIsEnabled<? super T> itemIsEnabled;
    private final int itemTypeCount;
    private List<T> items;
    private int[] layouts;
    private LifecycleOwner lifecycleOwner;

    public interface ItemIds<T> {
        long getItemId(int i, T t);
    }

    public interface ItemIsEnabled<T> {
        boolean isEnabled(int i, T t);
    }

    public BindingListViewAdapter(int i) {
        this.itemTypeCount = i;
    }

    public BindingListViewAdapter(int i, ItemBinding<? super T> itemBinding) {
        this.itemTypeCount = i;
        this.itemBinding = itemBinding;
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void setItemBinding(ItemBinding<? super T> itemBinding) {
        this.itemBinding = itemBinding;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        notifyDataSetChanged();
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public ItemBinding<? super T> getItemBinding() {
        ItemBinding<? super T> itemBinding = this.itemBinding;
        if (itemBinding != null) {
            return itemBinding;
        }
        throw new NullPointerException("itemBinding == null");
    }

    public void setDropDownItemLayout(int i) {
        this.dropDownItemLayout = i;
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void setItems(List<T> list) {
        List<T> list2 = this.items;
        if (list2 == list) {
            return;
        }
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
        if (this.itemBinding.bind(viewDataBinding, t)) {
            viewDataBinding.executePendingBindings();
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            if (lifecycleOwner != null) {
                viewDataBinding.setLifecycleOwner(lifecycleOwner);
            }
        }
    }

    public void setItemIds(ItemIds<? super T> itemIds) {
        this.itemIds = itemIds;
    }

    public void setItemIsEnabled(ItemIsEnabled<? super T> itemIsEnabled) {
        this.itemIsEnabled = itemIsEnabled;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        return this.items.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        ItemIds<? super T> itemIds = this.itemIds;
        return itemIds == null ? i : itemIds.getItemId(i, this.items.get(i));
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        ItemIsEnabled<? super T> itemIsEnabled = this.itemIsEnabled;
        return itemIsEnabled == null || itemIsEnabled.isEnabled(i, this.items.get(i));
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        ViewDataBinding binding;
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        tryGetLifecycleOwner(viewGroup);
        int i2 = this.layouts[getItemViewType(i)];
        if (view == null) {
            binding = onCreateBinding(this.inflater, i2, viewGroup);
            binding.getRoot();
        } else {
            binding = DataBindingUtil.getBinding(view);
        }
        ViewDataBinding viewDataBinding = binding;
        View root = viewDataBinding.getRoot();
        onBindBinding(viewDataBinding, this.itemBinding.variableId(), i2, i, this.items.get(i));
        return root;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        ViewDataBinding binding;
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        tryGetLifecycleOwner(viewGroup);
        int i2 = this.dropDownItemLayout;
        if (i2 == 0) {
            return super.getDropDownView(i, view, viewGroup);
        }
        if (view == null) {
            binding = onCreateBinding(this.inflater, i2, viewGroup);
        } else {
            binding = DataBindingUtil.getBinding(view);
        }
        ViewDataBinding viewDataBinding = binding;
        View root = viewDataBinding.getRoot();
        onBindBinding(viewDataBinding, this.itemBinding.variableId(), i2, i, this.items.get(i));
        return root;
    }

    private void tryGetLifecycleOwner(View view) {
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null || lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            this.lifecycleOwner = Utils.findLifecycleOwner(view);
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        ensureLayoutsInit();
        this.itemBinding.onItemBind(i, this.items.get(i));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.layouts;
            if (i2 < iArr.length) {
                int iLayoutRes = this.itemBinding.layoutRes();
                int[] iArr2 = this.layouts;
                if (iLayoutRes == iArr2[i2]) {
                    return i2;
                }
                if (iArr2[i2] == 0) {
                    i3 = i2;
                }
                i2++;
            } else {
                iArr[i3] = this.itemBinding.layoutRes();
                return i3;
            }
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return this.itemIds != null;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return ensureLayoutsInit();
    }

    private int ensureLayoutsInit() {
        int i = this.itemTypeCount;
        if (this.layouts == null) {
            this.layouts = new int[i];
        }
        return i;
    }

    static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingListViewAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingListViewAdapter<T> bindingListViewAdapter, ObservableList<T> observableList) {
            this.adapterRef = AdapterReferenceCollector.createRef(bindingListViewAdapter, observableList, this);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            BindingListViewAdapter<T> bindingListViewAdapter = this.adapterRef.get();
            if (bindingListViewAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            bindingListViewAdapter.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }
    }
}
