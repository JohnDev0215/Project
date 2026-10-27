package com.amazon.podcast.views.followsTemplate;

import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.transformations.BorderedRoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class FollowsVerticalItemViewHolder extends RecyclerView.ViewHolder {
    private final int imageDimension;
    private final ImageView imageView;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final BorderedRoundedCornersTransformation transformation;

    public FollowsVerticalItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final BorderedRoundedCornersTransformation transformation, final int imageDimension) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = transformation;
        this.imageDimension = imageDimension;
        ImageView imageView = (ImageView) view.findViewById(R$id.grid_template_vertical_item_image);
        this.imageView = imageView;
        imageView.getLayoutParams().height = imageDimension;
        imageView.getLayoutParams().width = imageDimension;
    }

    public void bind(final VerticalItemElement element) {
        RequestCreator requestCreatorLoad;
        if (StringUtils.isEmpty(element.getImage())) {
            requestCreatorLoad = Picasso.get().load(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        } else {
            requestCreatorLoad = Picasso.get().load(element.getImage());
        }
        RequestCreator requestCreatorError = requestCreatorLoad.placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.transformation).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i = this.imageDimension;
        requestCreatorError.resize(i, i).into(this.imageView);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.followsTemplate.FollowsVerticalItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FollowsVerticalItemViewHolder.this.methodsDispatcher.dispatch(FollowsVerticalItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.imageView.setContentDescription(element.getAltText());
    }
}
