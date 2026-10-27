package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.verticalItemView.VerticalItemView;
import com.amazon.podcast.views.verticalItemView.VerticalItemViewHolder;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SavesVisualShovelerCompactAdapter extends RecyclerView.Adapter<VerticalItemViewHolder> {
    private final Context context;
    private List<VerticalItemElement> items;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final RoundedCornersTransformation transformation;
    private final int verticalItemDimension;

    public SavesVisualShovelerCompactAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources, final int verticalItemDimension, final LifecycleOwner lifecycleOwner, final Context context) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_vertical_item_image_corner_radius));
        this.verticalItemDimension = verticalItemDimension;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VerticalItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        return new VerticalItemViewHolder(new VerticalItemView(this.context, this.ownerId, this.methodsDispatcher, this.lifecycleOwner));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final VerticalItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<VerticalItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<VerticalItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
