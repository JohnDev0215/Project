package com.amazon.podcast.views.followsTemplate;

import Podcast.FollowInterface.v1_0.FooterElement;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import SOATemplateListInterface.v1_0.TemplateElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.transformations.BorderedRoundedCornersTransformation;
import com.amazon.podcast.views.FooterItemViewHolder;
import com.amazon.podcast.views.PTCWidgetView;
import com.amazon.podcast.views.descriptiveShowcase.DescriptiveShowcaseElementView;
import com.amazon.podcast.views.descriptiveShowcase.DescriptiveShowcaseElementViewHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class FollowItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<TemplateElement> items = new ArrayList();
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private final Resources resources;
    private final BorderedRoundedCornersTransformation transformation;
    private final int verticalItemDimension;

    private enum ViewType {
        verticalItem(0),
        footer(1),
        ptcFooter(2),
        descriptiveShowcaseWidget(3);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    FollowItemsAdapter(final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher, final int verticalItemDimension) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = new BorderedRoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_vertical_item_image_corner_radius), resources);
        this.verticalItemDimension = verticalItemDimension;
        this.resources = resources;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        if (ViewType.verticalItem.value != viewType) {
            if (ViewType.ptcFooter.value != viewType) {
                if (ViewType.descriptiveShowcaseWidget.value == viewType) {
                    return new DescriptiveShowcaseElementViewHolder(new DescriptiveShowcaseElementView(viewGroup.getContext()), this.methodsDispatcher, this.ownerId);
                }
                return new FooterItemViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_explore_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.resources, viewGroup.getContext());
            }
            return new PTCWidgetViewHolder(new PTCWidgetView(viewGroup.getContext()), this.methodsDispatcher, this.ownerId);
        }
        return new FollowsVerticalItemViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_grid_template_vertical_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.transformation, this.verticalItemDimension);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof FollowsVerticalItemViewHolder) {
            ((FollowsVerticalItemViewHolder) viewHolder).bind((VerticalItemElement) this.items.get(position));
        } else if (viewHolder instanceof DescriptiveShowcaseElementViewHolder) {
            ((DescriptiveShowcaseElementViewHolder) viewHolder).bind((DescriptiveShowcaseElement) this.items.get(position));
        } else if (PodcastFeatureGating.TASTE_COLLECTION.isEnabled()) {
            ((PTCWidgetViewHolder) viewHolder).bind((FooterElement) this.items.get(position), true);
        } else {
            FooterElement footerElement = (FooterElement) this.items.get(position);
            ((FooterItemViewHolder) viewHolder).bind(footerElement.getText(), footerElement.getOnItemSelected());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        TemplateElement templateElement = this.items.get(position);
        if (templateElement instanceof VerticalItemElement) {
            return ViewType.verticalItem.value;
        }
        if (templateElement instanceof DescriptiveShowcaseElement) {
            return ViewType.descriptiveShowcaseWidget.value;
        }
        return PodcastFeatureGating.TASTE_COLLECTION.isEnabled() ? ViewType.ptcFooter.value : ViewType.footer.value;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void bind(final List<VerticalItemElement> newItems, final DescriptiveShowcaseElement descriptiveShowcaseElement, final FooterElement footerElement) {
        this.items.clear();
        this.items.addAll(newItems);
        if (descriptiveShowcaseElement != null) {
            this.items.add(descriptiveShowcaseElement);
        }
        if (footerElement != null) {
            this.items.add(footerElement);
        }
        notifyDataSetChanged();
    }

    public boolean isFooterOrDescriptiveShowcase(final int position) {
        if (this.items.size() == 0) {
            return false;
        }
        TemplateElement templateElement = this.items.get(position);
        return (templateElement instanceof FooterElement) || (templateElement instanceof DescriptiveShowcaseElement);
    }
}
