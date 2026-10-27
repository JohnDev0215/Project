package me.tatarka.bindingcollectionadapter2.collections;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DiffObservableList<T> extends AbstractList<T> implements ObservableList<T> {
    private final Object LIST_LOCK;
    private final DiffUtil.ItemCallback<T> callback;
    private final boolean detectMoves;
    private List<T> list;
    private final DiffObservableList<T>.ObservableListUpdateCallback listCallback;
    private final ListChangeRegistry listeners;

    @Deprecated
    public interface Callback<T> {
        boolean areContentsTheSame(T t, T t2);

        boolean areItemsTheSame(T t, T t2);
    }

    @Deprecated
    public DiffObservableList(Callback<T> callback) {
        this((DiffUtil.ItemCallback) new ItemCallbackAdapter(callback), true);
    }

    @Deprecated
    public DiffObservableList(Callback<T> callback, boolean z) {
        this(new ItemCallbackAdapter(callback), z);
    }

    public DiffObservableList(DiffUtil.ItemCallback<T> itemCallback) {
        this((DiffUtil.ItemCallback) itemCallback, true);
    }

    public DiffObservableList(DiffUtil.ItemCallback<T> itemCallback, boolean z) {
        this.LIST_LOCK = new Object();
        this.list = Collections.emptyList();
        this.listeners = new ListChangeRegistry();
        this.listCallback = new ObservableListUpdateCallback();
        this.callback = itemCallback;
        this.detectMoves = z;
    }

    public DiffUtil.DiffResult calculateDiff(List<T> list) {
        ArrayList arrayList;
        synchronized (this.LIST_LOCK) {
            arrayList = new ArrayList(this.list);
        }
        return doCalculateDiff(arrayList, list);
    }

    private DiffUtil.DiffResult doCalculateDiff(final List<T> list, final List<T> list2) {
        return DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: me.tatarka.bindingcollectionadapter2.collections.DiffObservableList.1
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return list.size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                List list3 = list2;
                if (list3 != null) {
                    return list3.size();
                }
                return 0;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int i, int i2) {
                Object obj = list.get(i);
                Object obj2 = list2.get(i2);
                if (obj == null || obj2 == null) {
                    return obj == null && obj2 == null;
                }
                return DiffObservableList.this.callback.areItemsTheSame(obj, obj2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int i, int i2) {
                Object obj = list.get(i);
                Object obj2 = list2.get(i2);
                if (obj == null || obj2 == null) {
                    return true;
                }
                return DiffObservableList.this.callback.areContentsTheSame(obj, obj2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public Object getChangePayload(int i, int i2) {
                return DiffObservableList.this.callback.getChangePayload(list.get(i), list2.get(i2));
            }
        }, this.detectMoves);
    }

    public void update(List<T> list, DiffUtil.DiffResult diffResult) {
        synchronized (this.LIST_LOCK) {
            this.list = list;
        }
        diffResult.dispatchUpdatesTo(this.listCallback);
    }

    public void update(List<T> list) {
        DiffUtil.DiffResult diffResultDoCalculateDiff = doCalculateDiff(this.list, list);
        this.list = list;
        diffResultDoCalculateDiff.dispatchUpdatesTo(this.listCallback);
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
        return this.list.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }

    class ObservableListUpdateCallback implements ListUpdateCallback {
        ObservableListUpdateCallback() {
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int i, int i2, Object obj) {
            DiffObservableList.this.listeners.notifyChanged(DiffObservableList.this, i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int i, int i2) {
            DiffObservableList.this.modCount++;
            DiffObservableList.this.listeners.notifyInserted(DiffObservableList.this, i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int i, int i2) {
            DiffObservableList.this.modCount++;
            DiffObservableList.this.listeners.notifyRemoved(DiffObservableList.this, i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int i, int i2) {
            DiffObservableList.this.listeners.notifyMoved(DiffObservableList.this, i, i2, 1);
        }
    }

    static class ItemCallbackAdapter<T> extends DiffUtil.ItemCallback<T> {
        final Callback<T> callback;

        ItemCallbackAdapter(Callback<T> callback) {
            this.callback = callback;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(T t, T t2) {
            return this.callback.areItemsTheSame(t, t2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(T t, T t2) {
            return this.callback.areContentsTheSame(t, t2);
        }
    }
}
