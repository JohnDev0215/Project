package com.amazon.podcast.views.deeplinkThumbnailNavigator;

import Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DeeplinkThumbnailNavigatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final int itemHeight;
    private final int itemWidth;
    private List<ThumbnailNavigatorItemElement> items;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;

    public DeeplinkThumbnailNavigatorAdapter(final String ownerId, final Context context, final Resources resources, final MethodsDispatcher methodsDispatcher, final int itemWidth, final int itemHeight) {
        this.ownerId = ownerId;
        this.context = context;
        this.resources = resources;
        this.methodsDispatcher = methodsDispatcher;
        this.itemWidth = itemWidth;
        this.itemHeight = itemHeight;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        DeeplinkThumbnailNavigatorItemView deeplinkThumbnailNavigatorItemView = new DeeplinkThumbnailNavigatorItemView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.itemWidth, this.itemHeight);
        deeplinkThumbnailNavigatorItemView.setLayoutParams(new RecyclerView.LayoutParams(this.itemWidth, this.itemHeight));
        return new DeeplinkThumbnailNavigatorItemViewHolder(deeplinkThumbnailNavigatorItemView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof DeeplinkThumbnailNavigatorItemViewHolder) {
            ((DeeplinkThumbnailNavigatorItemViewHolder) viewHolder).bind(this.items.get(position));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ThumbnailNavigatorItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<ThumbnailNavigatorItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
