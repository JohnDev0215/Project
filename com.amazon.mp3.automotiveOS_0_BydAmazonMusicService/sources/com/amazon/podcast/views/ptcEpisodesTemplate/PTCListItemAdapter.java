package com.amazon.podcast.views.ptcEpisodesTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class PTCListItemAdapter extends RecyclerView.Adapter<PTCListItemViewHolder> {
    private List<ListItemElement> items;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;

    public PTCListItemAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.resources = resources;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PTCListItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        return new PTCListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_episode_list_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.resources);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final PTCListItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ListItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final void bind(final List<ListItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
