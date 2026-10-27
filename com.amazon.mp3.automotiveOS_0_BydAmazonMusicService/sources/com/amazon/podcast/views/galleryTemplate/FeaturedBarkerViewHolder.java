package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElement;
import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class FeaturedBarkerViewHolder extends RecyclerView.ViewHolder {
    private final ImageView backgroundImage;
    private final int barkerItemHeight;
    private final int barkerItemWidth;
    private final View featuredBarkerItem;
    private final int featuredBarkerThreshold;
    private final int featuredHorizontalImageItemDimen;
    private final View featuredHorizontalItem;
    private final int featuredHorizontalItemWidth;
    private final ImageView image;
    private boolean isFirstView;
    private final EmberTextView label;
    private final MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private final String ownerId;
    private final EmberTextView primaryText;
    private final Resources resources;
    private final int screenWidth;
    private final EmberTextView secondaryText;
    private final RoundedCornersTransformation transformation;

    public FeaturedBarkerViewHolder(final View itemView, final int barkerItemWidth, final int barkerItemHeight, final int featuredHorizontalImageItemDimen, final int featuredHorizontalItemWidth, final int screenWidth, final int featuredBarkerThreshold, final Resources resources, final RoundedCornersTransformation transformation, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(itemView);
        this.isFirstView = true;
        this.barkerItemWidth = barkerItemWidth;
        this.barkerItemHeight = barkerItemHeight;
        this.featuredHorizontalImageItemDimen = featuredHorizontalImageItemDimen;
        this.transformation = transformation;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.resources = resources;
        this.featuredBarkerThreshold = featuredBarkerThreshold;
        this.featuredHorizontalItemWidth = featuredHorizontalItemWidth;
        this.screenWidth = screenWidth;
        View viewFindViewById = itemView.findViewById(R$id.featured_barker_item);
        this.featuredBarkerItem = viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R$id.featured_horizontal_item);
        this.featuredHorizontalItem = viewFindViewById2;
        viewFindViewById.getLayoutParams().width = barkerItemWidth;
        viewFindViewById.getLayoutParams().height = barkerItemHeight;
        viewFindViewById2.getLayoutParams().width = featuredHorizontalItemWidth;
        this.backgroundImage = (ImageView) itemView.findViewById(R$id.featured_barker_image_view);
        this.image = (ImageView) itemView.findViewById(R$id.featured_horizontal_item_image);
        EmberTextView emberTextView = (EmberTextView) itemView.findViewById(R$id.featured_horizontal_item_label);
        this.label = emberTextView;
        emberTextView.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        this.primaryText = (EmberTextView) itemView.findViewById(R$id.featured_horizontal_item_primary_text);
        this.secondaryText = (EmberTextView) itemView.findViewById(R$id.featured_horizontal_item_secondary_text);
    }

    public void bind(final FeaturedBarkerElement featuredElement) {
        this.onViewed = featuredElement.getOnViewed();
        this.onContentFirstViewed = featuredElement.getOnContentFirstViewed();
        final FeaturedBarkerItemElement barker = featuredElement.getBarker();
        RequestCreator requestCreatorLoad = Picasso.get().load(barker.getImage());
        int i = R$drawable.artwork_placeholder;
        RequestCreator requestCreatorTransform = requestCreatorLoad.placeholder(i).transform(this.transformation);
        int i2 = this.featuredHorizontalImageItemDimen;
        requestCreatorTransform.resize(i2, i2).into(this.image);
        if (this.screenWidth > this.featuredBarkerThreshold) {
            Picasso.get().load(barker.getBackgroundImageLarge()).placeholder(i).transform(this.transformation).resize(this.barkerItemWidth, this.barkerItemHeight).into(this.backgroundImage);
        } else {
            RequestCreator requestCreatorTransform2 = Picasso.get().load(barker.getBackgroundImageSmall()).placeholder(i).transform(this.transformation);
            int i3 = this.barkerItemWidth;
            requestCreatorTransform2.resize(i3, i3).into(this.backgroundImage);
        }
        if (StringUtils.isBlank(barker.getLabel())) {
            this.label.setVisibility(8);
        } else {
            this.label.setVisibility(0);
            this.label.setText(barker.getLabel());
        }
        if (StringUtils.isBlank(barker.getPrimaryText())) {
            this.primaryText.setVisibility(8);
        } else {
            this.primaryText.setVisibility(0);
            this.primaryText.setText(barker.getPrimaryText());
        }
        if (StringUtils.isBlank(barker.getSecondaryText())) {
            this.secondaryText.setVisibility(8);
        } else {
            this.secondaryText.setVisibility(0);
            this.secondaryText.setText(barker.getSecondaryText());
        }
        this.itemView.setContentDescription(barker.getLabel() + barker.getPrimaryText());
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturedBarkerViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeaturedBarkerViewHolder.this.methodsDispatcher.dispatch(FeaturedBarkerViewHolder.this.ownerId, barker.getOnItemSelected());
            }
        });
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
        if (this.isFirstView) {
            handleOnContentFirstViewed();
        }
    }

    private void handleOnContentFirstViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onContentFirstViewed);
        this.isFirstView = false;
    }
}
