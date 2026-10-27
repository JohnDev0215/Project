package me.tatarka.bindingcollectionadapter2;

import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList;

/* JADX INFO: loaded from: classes5.dex */
public class BindingRecyclerViewAdapters {
    public static <T> void setAdapter(RecyclerView recyclerView, ItemBinding<? super T> itemBinding, List<T> list, BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter, BindingRecyclerViewAdapter.ItemIds<? super T> itemIds, BindingRecyclerViewAdapter.ViewHolderFactory viewHolderFactory, AsyncDifferConfig<T> asyncDifferConfig) {
        if (itemBinding != null) {
            BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter2 = (BindingRecyclerViewAdapter) recyclerView.getAdapter();
            if (bindingRecyclerViewAdapter == null) {
                bindingRecyclerViewAdapter = bindingRecyclerViewAdapter2 == null ? new BindingRecyclerViewAdapter<>() : bindingRecyclerViewAdapter2;
            }
            bindingRecyclerViewAdapter.setItemBinding(itemBinding);
            if (asyncDifferConfig != null && list != null) {
                AsyncDiffObservableList asyncDiffObservableList = (AsyncDiffObservableList) recyclerView.getTag(me.tatarka.bindingcollectionadapter2.recyclerview.R.id.bindingcollectiondapter_list_id);
                if (asyncDiffObservableList == null) {
                    asyncDiffObservableList = new AsyncDiffObservableList(asyncDifferConfig);
                    recyclerView.setTag(me.tatarka.bindingcollectionadapter2.recyclerview.R.id.bindingcollectiondapter_list_id, asyncDiffObservableList);
                    bindingRecyclerViewAdapter.setItems(asyncDiffObservableList);
                }
                asyncDiffObservableList.update(list);
            } else {
                bindingRecyclerViewAdapter.setItems(list);
            }
            bindingRecyclerViewAdapter.setItemIds(itemIds);
            bindingRecyclerViewAdapter.setViewHolderFactory(viewHolderFactory);
            if (bindingRecyclerViewAdapter2 != bindingRecyclerViewAdapter) {
                recyclerView.setAdapter(bindingRecyclerViewAdapter);
                return;
            }
            return;
        }
        recyclerView.setAdapter(null);
    }

    public static <T> AsyncDifferConfig<T> toAsyncDifferConfig(DiffUtil.ItemCallback<T> itemCallback) {
        return new AsyncDifferConfig.Builder(itemCallback).build();
    }
}
