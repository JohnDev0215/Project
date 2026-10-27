package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class ExperienceVisualShovelerAdapter extends RecyclerView.Adapter<MediaItemViewHolder> {
    private final int itemDimension;
    private List<MediaItemElement> items;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private List<Method> onLaunchBitesExperience;
    private final String ownerId;
    private final Resources resources;

    public ExperienceVisualShovelerAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources, final int itemDimension, final LifecycleOwner lifecycleOwner) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.itemDimension = itemDimension;
        this.lifecycleOwner = lifecycleOwner;
        this.resources = resources;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public MediaItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        return new MediaItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_media_item, viewGroup, false), this.ownerId, this.resources, this.methodsDispatcher, this.lifecycleOwner, this.itemDimension);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MediaItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position), this.onLaunchBitesExperience);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MediaItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setOnLaunchBitesExperience(List<Method> onLaunchBitesExperience) {
        this.onLaunchBitesExperience = onLaunchBitesExperience;
    }

    public void bind(final List<MediaItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
