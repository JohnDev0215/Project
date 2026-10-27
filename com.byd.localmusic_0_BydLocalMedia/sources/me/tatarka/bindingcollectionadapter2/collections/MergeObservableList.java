package me.tatarka.bindingcollectionadapter2.collections;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableList;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MergeObservableList<T> extends AbstractList<T> implements ObservableList<T> {
    private final ArrayList<List<? extends T>> lists = new ArrayList<>();
    private final MergeObservableList<T>.ListChangeCallback callback = new ListChangeCallback();
    private final ListChangeRegistry listeners = new ListChangeRegistry();

    @Override // androidx.databinding.ObservableList
    public void addOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.add(onListChangedCallback);
    }

    @Override // androidx.databinding.ObservableList
    public void removeOnListChangedCallback(ObservableList.OnListChangedCallback<? extends ObservableList<T>> onListChangedCallback) {
        this.listeners.remove(onListChangedCallback);
    }

    public MergeObservableList<T> insertItem(T t) {
        this.lists.add(Collections.singletonList(t));
        this.modCount++;
        this.listeners.notifyInserted(this, size() - 1, 1);
        return this;
    }

    public MergeObservableList<T> insertList(ObservableList<? extends T> observableList) {
        observableList.addOnListChangedCallback(this.callback);
        int size = size();
        this.lists.add(observableList);
        this.modCount++;
        if (!observableList.isEmpty()) {
            this.listeners.notifyInserted(this, size, observableList.size());
        }
        return this;
    }

    public boolean removeItem(T t) {
        int size = this.lists.size();
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            List<? extends T> list = this.lists.get(i);
            if (!(list instanceof ObservableList)) {
                T t2 = list.get(0);
                if (t == null) {
                    if (t2 == null) {
                        this.lists.remove(i);
                        this.modCount++;
                        this.listeners.notifyRemoved(this, size2, 1);
                        return true;
                    }
                } else if (t.equals(t2)) {
                    this.lists.remove(i);
                    this.modCount++;
                    this.listeners.notifyRemoved(this, size2, 1);
                    return true;
                }
            }
            size2 += list.size();
        }
        return false;
    }

    public boolean removeList(ObservableList<? extends T> observableList) {
        int size = this.lists.size();
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            List<? extends T> list = this.lists.get(i);
            if (list == observableList) {
                observableList.removeOnListChangedCallback(this.callback);
                this.lists.remove(i);
                this.modCount++;
                this.listeners.notifyRemoved(this, size2, list.size());
                return true;
            }
            size2 += list.size();
        }
        return false;
    }

    public void removeAll() {
        int size = size();
        int size2 = this.lists.size();
        for (int i = 0; i < size2; i++) {
            List<? extends T> list = this.lists.get(i);
            if (list instanceof ObservableList) {
                ((ObservableList) list).removeOnListChangedCallback(this.callback);
            }
        }
        this.lists.clear();
        this.modCount++;
        if (size > 0) {
            this.listeners.notifyRemoved(this, 0, size);
        }
    }

    public int mergeToBackingIndex(ObservableList<? extends T> observableList, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        int size = this.lists.size();
        int size2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List<? extends T> list = this.lists.get(i2);
            if (observableList == list) {
                if (i < list.size()) {
                    return size2 + i;
                }
                throw new IndexOutOfBoundsException();
            }
            size2 += list.size();
        }
        throw new IllegalArgumentException();
    }

    public int backingIndexToMerge(ObservableList<? extends T> observableList, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        int size = this.lists.size();
        int size2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List<? extends T> list = this.lists.get(i2);
            if (observableList == list) {
                int i3 = i - size2;
                if (i3 < list.size()) {
                    return i3;
                }
                throw new IndexOutOfBoundsException();
            }
            size2 += list.size();
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        int size = this.lists.size();
        int size2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            List<? extends T> list = this.lists.get(i2);
            int i3 = i - size2;
            if (i3 < list.size()) {
                return list.get(i3);
            }
            size2 += list.size();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        int size = this.lists.size();
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            size2 += this.lists.get(i).size();
        }
        return size2;
    }

    class ListChangeCallback extends ObservableList.OnListChangedCallback {
        ListChangeCallback() {
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            MergeObservableList.this.modCount++;
            MergeObservableList.this.listeners.notifyChanged(MergeObservableList.this);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            int size = MergeObservableList.this.lists.size();
            int size2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                List list = (List) MergeObservableList.this.lists.get(i3);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyChanged(MergeObservableList.this, size2 + i, i2);
                    return;
                }
                size2 += list.size();
            }
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            MergeObservableList.this.modCount++;
            int size = MergeObservableList.this.lists.size();
            int size2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                List list = (List) MergeObservableList.this.lists.get(i3);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyInserted(MergeObservableList.this, size2 + i, i2);
                    return;
                }
                size2 += list.size();
            }
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            int size = MergeObservableList.this.lists.size();
            int size2 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                List list = (List) MergeObservableList.this.lists.get(i4);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyMoved(MergeObservableList.this, i + size2, size2 + i2, i3);
                    return;
                }
                size2 += list.size();
            }
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            MergeObservableList.this.modCount++;
            int size = MergeObservableList.this.lists.size();
            int size2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                List list = (List) MergeObservableList.this.lists.get(i3);
                if (list == observableList) {
                    MergeObservableList.this.listeners.notifyRemoved(MergeObservableList.this, size2 + i, i2);
                    return;
                }
                size2 += list.size();
            }
        }
    }
}
