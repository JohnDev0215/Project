package com.amazon.podcast.views.ptcCategoriesTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class PillItemAdapter extends RecyclerView.Adapter<PillItemViewHolder> {
    private List<String> cachedItems;
    private List<PillItemElement> items;
    private OnPillStateUpdateListener listener;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public interface OnPillStateUpdateListener {
        void onStateUpdate(final PillItemElement element, final boolean isSelected);
    }

    public PillItemAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final OnPillStateUpdateListener listener) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.listener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PillItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int position) {
        return new PillItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_pill_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.listener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final PillItemViewHolder viewHolder, final int position) {
        PillItemElement pillItemElement = this.items.get(position);
        viewHolder.bind(pillItemElement, this.cachedItems.contains(pillItemElement.getId()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PillItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<PillItemElement> items, final List<String> cachedItems) {
        this.items = items;
        this.cachedItems = cachedItems;
        notifyDataSetChanged();
    }
}
