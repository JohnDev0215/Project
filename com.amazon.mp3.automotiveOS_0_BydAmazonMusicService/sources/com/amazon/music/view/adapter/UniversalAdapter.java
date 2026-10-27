package com.amazon.music.view.adapter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class UniversalAdapter extends RecyclerView.Adapter<UniversalViewHolder> {
    private boolean allowRebind;
    private Comparator itemComparator;
    private boolean sortOnAdd;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Map<Class, UniversalBinder> BINDERS = new HashMap();
    private final List<Class> modelClasses = new ArrayList();
    private final List<Object> items = new ArrayList();
    private final List<OnFocusChangedListener> focusListeners = new ArrayList();

    public UniversalAdapter(UniversalBinder... universalBinderArr) {
        buildBinders(Arrays.asList(universalBinderArr));
    }

    public void addItems(List<?> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.items.addAll(list);
        if (this.sortOnAdd) {
            sortItems();
        }
        notifyDataSetChangedOnUiThread();
    }

    public void addItem(Object obj) {
        if (obj != null) {
            this.items.add(obj);
            if (this.sortOnAdd) {
                sortItems();
            }
            notifyDataSetChangedOnUiThread();
        }
    }

    public void remove(Object obj) {
        if (obj != null) {
            this.items.remove(obj);
            notifyDataSetChangedOnUiThread();
        }
    }

    public void removeItems(List<?> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.items.removeAll(list);
        notifyDataSetChangedOnUiThread();
    }

    public List<Object> getItems() {
        return this.items;
    }

    public void sortItems() {
        Comparator comparator = this.itemComparator;
        if (comparator != null) {
            Collections.sort(this.items, comparator);
            return;
        }
        throw new NullPointerException("Comparator cannot be null - call setComparator first!");
    }

    public void allowRebind(boolean z) {
        this.allowRebind = z;
    }

    public void setComparator(Comparator comparator) {
        this.itemComparator = comparator;
    }

    public void sortOnAdd(boolean z) {
        this.sortOnAdd = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Object obj = this.items.get(i);
        for (int i2 = 0; i2 < this.modelClasses.size(); i2++) {
            if (this.modelClasses.get(i2).equals(obj.getClass())) {
                return i2;
            }
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public UniversalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Class cls = this.modelClasses.get(i);
        UniversalBinder universalBinder = this.BINDERS.get(cls);
        if (universalBinder != null) {
            return new UniversalViewHolder(universalBinder.createView(viewGroup.getContext()));
        }
        throw new RuntimeException("Adapter doesn't know how to inflate and bind " + cls + ". Register a UniversalBinder.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(UniversalViewHolder universalViewHolder, int i) {
        final Object obj = this.items.get(i);
        UniversalBinder universalBinder = this.BINDERS.get(obj.getClass());
        if (this.allowRebind || obj != universalViewHolder.getModel()) {
            universalViewHolder.setModel(obj);
            universalViewHolder.itemView.setTag(universalViewHolder);
            universalBinder.bind(universalViewHolder.itemView, obj);
            universalViewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.amazon.music.view.adapter.UniversalAdapter.1
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    Iterator it = UniversalAdapter.this.focusListeners.iterator();
                    while (it.hasNext()) {
                        ((OnFocusChangedListener) it.next()).onFocusChanged(view, obj, z);
                    }
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    private void buildBinders(List<UniversalBinder> list) {
        for (UniversalBinder universalBinder : list) {
            Class modelClass = universalBinder.getModelClass();
            this.modelClasses.add(modelClass);
            this.BINDERS.put(modelClass, universalBinder);
        }
    }

    private void notifyDataSetChangedOnUiThread() {
        this.handler.post(new Runnable() { // from class: com.amazon.music.view.adapter.UniversalAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                UniversalAdapter.this.notifyDataSetChanged();
            }
        });
    }
}
