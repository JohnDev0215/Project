package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class LinkNavigatorItemAdapter extends RecyclerView.Adapter<LinkNavigatorItemViewHolder> {
    private List<LinkNavigatorItemElement> items;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;

    public LinkNavigatorItemAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.resources = resources;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public LinkNavigatorItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int position) {
        return new LinkNavigatorItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.link_navigator_item_view, viewGroup, false), this.ownerId, this.methodsDispatcher, this.resources);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final LinkNavigatorItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<LinkNavigatorItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<LinkNavigatorItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
