package com.amazon.podcast.views.verticalGridTemplate;

import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes5.dex */
final class VerticalItemViewHolder extends RecyclerView.ViewHolder {
    private final int imageDimension;
    private final ImageView imageView;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final RoundedCornersTransformation transformation;

    public VerticalItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final RoundedCornersTransformation transformation, final int imageDimension) {
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
        RequestCreator requestCreatorTransform = Picasso.get().load(element.getImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.transformation);
        int i = this.imageDimension;
        requestCreatorTransform.resize(i, i).into(this.imageView);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.verticalGridTemplate.VerticalItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerticalItemViewHolder.this.methodsDispatcher.dispatch(VerticalItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.imageView.setContentDescription(element.getAltText());
    }
}
