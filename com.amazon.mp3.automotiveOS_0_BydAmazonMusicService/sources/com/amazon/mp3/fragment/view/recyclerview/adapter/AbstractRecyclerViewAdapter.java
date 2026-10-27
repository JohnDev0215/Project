package com.amazon.mp3.fragment.view.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.fragment.view.recyclerview.listener.BaseRecyclerViewListener;
import com.amazon.mp3.fragment.view.recyclerview.viewholder.AbstractViewHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractRecyclerViewAdapter<T, L extends BaseRecyclerViewListener, VH extends AbstractViewHolder<T, L>> extends RecyclerView.Adapter<VH> {
    private List<T> items = new ArrayList();
    private LayoutInflater layoutInflater;
    private L listener;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public AbstractRecyclerViewAdapter(Context context, L listener) {
        this.listener = listener;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VH holder, int position) {
        if (this.items.size() <= position) {
            return;
        }
        holder.onBind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setItems(List<T> items) {
        setItems(items, true);
    }

    public void setItems(List<T> items, boolean notifyChanges) throws IllegalArgumentException {
        if (items == null) {
            throw new IllegalArgumentException("Cannot set `null` item to the Recycler adapter");
        }
        this.items.clear();
        this.items.addAll(items);
        if (notifyChanges) {
            notifyDataSetChanged();
        }
    }

    public L getListener() {
        return this.listener;
    }

    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent, final boolean attachToRoot) {
        return this.layoutInflater.inflate(layout, parent, attachToRoot);
    }
}
