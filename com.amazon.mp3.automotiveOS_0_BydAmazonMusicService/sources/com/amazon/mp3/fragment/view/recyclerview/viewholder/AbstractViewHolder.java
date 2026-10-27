package com.amazon.mp3.fragment.view.recyclerview.viewholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.fragment.view.recyclerview.listener.BaseRecyclerViewListener;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractViewHolder<T, L extends BaseRecyclerViewListener> extends RecyclerView.ViewHolder {
    private L listener;

    public abstract void onBind(T item);

    public AbstractViewHolder(View itemView, L listener) {
        super(itemView);
        this.listener = listener;
    }
}
