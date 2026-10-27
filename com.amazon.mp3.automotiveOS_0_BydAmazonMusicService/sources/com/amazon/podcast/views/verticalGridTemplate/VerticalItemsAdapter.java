package com.amazon.podcast.views.verticalGridTemplate;

import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class VerticalItemsAdapter extends RecyclerView.Adapter<VerticalItemViewHolder> {
    private Integer itemCount;
    private List<VerticalItemElement> items;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private final RoundedCornersTransformation transformation;
    private final int verticalItemDimension;

    VerticalItemsAdapter(final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher, final int verticalItemDimension) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_vertical_item_image_corner_radius));
        this.verticalItemDimension = verticalItemDimension;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VerticalItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int position) {
        return new VerticalItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_grid_template_vertical_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.transformation, this.verticalItemDimension);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final VerticalItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemCount.intValue();
    }

    public void addItems(final List<VerticalItemElement> items) {
        if (items.isEmpty()) {
            return;
        }
        this.items.addAll(items);
        this.itemCount = Integer.valueOf(this.itemCount.intValue() + items.size());
        notifyDataSetChanged();
    }

    public void bind(final List<VerticalItemElement> items) {
        ArrayList arrayList = new ArrayList(items);
        this.items = arrayList;
        this.itemCount = Integer.valueOf(arrayList.size());
        notifyDataSetChanged();
    }
}
