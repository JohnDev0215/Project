package me.tatarka.bindingcollectionadapter2.collections;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes5.dex */
public class AsyncDiffObservableList<T> extends AbstractList<T> implements ObservableList<T> {
    private final AsyncListDiffer<T> differ;
    private final ListChangeRegistry listeners;

    public AsyncDiffObservableList(DiffUtil.ItemCallback<T> itemCallback) {
        this(new AsyncDifferConfig.Builder(itemCallback).build());
    }

    public AsyncDiffObservableList(AsyncDifferConfig<T> asyncDifferConfig) {
        this.listeners = new ListChangeRegistry();
        this.differ = new AsyncListDiffer<>(new ObservableListUpdateCallback(), asyncDifferConfig);
    }

    public void update(List<T> list) {
        this.differ.submitList(list);
    }

    @Override // androidx.databinding.ObservableList
    public void addOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.add(onListChangedCallback);
    }

    @Override // androidx.databinding.ObservableList
    public void removeOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.remove(onListChangedCallback);
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        return this.differ.getCurrentList().get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.differ.getCurrentList().size();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        return this.differ.getCurrentList().indexOf(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        return this.differ.getCurrentList().lastIndexOf(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public List<T> subList(int i, int i2) {
        return this.differ.getCurrentList().subList(i, i2);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return this.differ.getCurrentList().hashCode();
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj instanceof AsyncDiffObservableList) {
            return this.differ.getCurrentList().equals(((AsyncDiffObservableList) obj).differ.getCurrentList());
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<T> listIterator(int i) {
        return this.differ.getCurrentList().listIterator(i);
    }

    class ObservableListUpdateCallback implements ListUpdateCallback {
        ObservableListUpdateCallback() {
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int i, int i2, Object obj) {
            AsyncDiffObservableList.this.listeners.notifyChanged(AsyncDiffObservableList.this, i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int i, int i2) {
            AsyncDiffObservableList.this.listeners.notifyInserted(AsyncDiffObservableList.this, i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int i, int i2) {
            AsyncDiffObservableList.this.listeners.notifyRemoved(AsyncDiffObservableList.this, i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int i, int i2) {
            AsyncDiffObservableList.this.listeners.notifyMoved(AsyncDiffObservableList.this, i, i2, 1);
        }
    }
}
