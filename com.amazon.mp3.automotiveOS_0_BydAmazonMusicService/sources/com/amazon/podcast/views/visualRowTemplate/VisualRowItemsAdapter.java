package com.amazon.podcast.views.visualRowTemplate;

import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.TemplateContext;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class VisualRowItemsAdapter extends RecyclerView.Adapter<VisualRowItemViewHolder> {
    private final Context context;
    private final int imageDimension;
    private List<VisualRowItemElement> items;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final UiMetricAttributes$PageType pageType;
    private final Resources resources;
    private final TemplateContext templateContext;
    private final RoundedCornersTransformation transformation;

    public VisualRowItemsAdapter(final Resources resources, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final Context context, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType) {
        this.imageDimension = resources.getDimensionPixelSize(R$dimen.podcast_visual_row_item_image_dimension);
        this.transformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_visual_row_item_image_corner_radius));
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
        this.templateContext = templateContext;
        this.pageType = pageType;
    }

    public void addItems(final List<VisualRowItemElement> items) {
        if (items.isEmpty()) {
            return;
        }
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void bind(final List<VisualRowItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VisualRowItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int position) {
        return new VisualRowItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_visual_row_item, viewGroup, false), this.imageDimension, this.transformation, this.ownerId, this.methodsDispatcher, this.lifecycleOwner, this.context, this.resources, this.templateContext);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final VisualRowItemViewHolder viewHolder, final int position) {
        viewHolder.bind(this.items.get(position), this.pageType);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<VisualRowItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
