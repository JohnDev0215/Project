package androidx.leanback.widget;

import android.util.Log;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ArrayObjectAdapter extends ObjectAdapter {
    private static final Boolean DEBUG = false;
    private static final String TAG = "ArrayObjectAdapter";
    private final List<Object> mItems;
    ListUpdateCallback mListUpdateCallback;
    final List<Object> mOldItems;
    private List mUnmodifiableItems;

    public ArrayObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
        this.mItems = new ArrayList();
        this.mOldItems = new ArrayList();
    }

    public ArrayObjectAdapter(Presenter presenter) {
        super(presenter);
        this.mItems = new ArrayList();
        this.mOldItems = new ArrayList();
    }

    public ArrayObjectAdapter() {
        this.mItems = new ArrayList();
        this.mOldItems = new ArrayList();
    }

    @Override // androidx.leanback.widget.ObjectAdapter
    public int size() {
        return this.mItems.size();
    }

    @Override // androidx.leanback.widget.ObjectAdapter
    public Object get(int index) {
        return this.mItems.get(index);
    }

    public int indexOf(Object item) {
        return this.mItems.indexOf(item);
    }

    public void notifyArrayItemRangeChanged(int positionStart, int itemCount) {
        notifyItemRangeChanged(positionStart, itemCount);
    }

    public void add(Object item) {
        add(this.mItems.size(), item);
    }

    public void add(int index, Object item) {
        this.mItems.add(index, item);
        notifyItemRangeInserted(index, 1);
    }

    public void addAll(int index, Collection items) {
        int itemsCount = items.size();
        if (itemsCount == 0) {
            return;
        }
        this.mItems.addAll(index, items);
        notifyItemRangeInserted(index, itemsCount);
    }

    public boolean remove(Object item) {
        int index = this.mItems.indexOf(item);
        if (index >= 0) {
            this.mItems.remove(index);
            notifyItemRangeRemoved(index, 1);
        }
        return index >= 0;
    }

    public void move(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return;
        }
        Object item = this.mItems.remove(fromPosition);
        this.mItems.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void replace(int position, Object item) {
        this.mItems.set(position, item);
        notifyItemRangeChanged(position, 1);
    }

    public int removeItems(int position, int count) {
        int itemsToRemove = Math.min(count, this.mItems.size() - position);
        if (itemsToRemove <= 0) {
            return 0;
        }
        for (int i = 0; i < itemsToRemove; i++) {
            this.mItems.remove(position);
        }
        notifyItemRangeRemoved(position, itemsToRemove);
        return itemsToRemove;
    }

    public void clear() {
        int itemCount = this.mItems.size();
        if (itemCount == 0) {
            return;
        }
        this.mItems.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public <E> List<E> unmodifiableList() {
        if (this.mUnmodifiableItems == null) {
            this.mUnmodifiableItems = Collections.unmodifiableList(this.mItems);
        }
        return this.mUnmodifiableItems;
    }

    @Override // androidx.leanback.widget.ObjectAdapter
    public boolean isImmediateNotifySupported() {
        return true;
    }

    public void setItems(final List itemList, final DiffCallback callback) {
        if (callback == null) {
            this.mItems.clear();
            this.mItems.addAll(itemList);
            notifyChanged();
            return;
        }
        this.mOldItems.clear();
        this.mOldItems.addAll(this.mItems);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: androidx.leanback.widget.ArrayObjectAdapter.1
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return ArrayObjectAdapter.this.mOldItems.size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return itemList.size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return callback.areItemsTheSame(ArrayObjectAdapter.this.mOldItems.get(oldItemPosition), itemList.get(newItemPosition));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return callback.areContentsTheSame(ArrayObjectAdapter.this.mOldItems.get(oldItemPosition), itemList.get(newItemPosition));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                return callback.getChangePayload(ArrayObjectAdapter.this.mOldItems.get(oldItemPosition), itemList.get(newItemPosition));
            }
        });
        this.mItems.clear();
        this.mItems.addAll(itemList);
        if (this.mListUpdateCallback == null) {
            this.mListUpdateCallback = new ListUpdateCallback() { // from class: androidx.leanback.widget.ArrayObjectAdapter.2
                @Override // androidx.recyclerview.widget.ListUpdateCallback
                public void onInserted(int position, int count) {
                    if (ArrayObjectAdapter.DEBUG.booleanValue()) {
                        Log.d(ArrayObjectAdapter.TAG, "onInserted");
                    }
                    ArrayObjectAdapter.this.notifyItemRangeInserted(position, count);
                }

                @Override // androidx.recyclerview.widget.ListUpdateCallback
                public void onRemoved(int position, int count) {
                    if (ArrayObjectAdapter.DEBUG.booleanValue()) {
                        Log.d(ArrayObjectAdapter.TAG, "onRemoved");
                    }
                    ArrayObjectAdapter.this.notifyItemRangeRemoved(position, count);
                }

                @Override // androidx.recyclerview.widget.ListUpdateCallback
                public void onMoved(int fromPosition, int toPosition) {
                    if (ArrayObjectAdapter.DEBUG.booleanValue()) {
                        Log.d(ArrayObjectAdapter.TAG, "onMoved");
                    }
                    ArrayObjectAdapter.this.notifyItemMoved(fromPosition, toPosition);
                }

                @Override // androidx.recyclerview.widget.ListUpdateCallback
                public void onChanged(int position, int count, Object payload) {
                    if (ArrayObjectAdapter.DEBUG.booleanValue()) {
                        Log.d(ArrayObjectAdapter.TAG, "onChanged");
                    }
                    ArrayObjectAdapter.this.notifyItemRangeChanged(position, count, payload);
                }
            };
        }
        diffResult.dispatchUpdatesTo(this.mListUpdateCallback);
        this.mOldItems.clear();
    }
}
