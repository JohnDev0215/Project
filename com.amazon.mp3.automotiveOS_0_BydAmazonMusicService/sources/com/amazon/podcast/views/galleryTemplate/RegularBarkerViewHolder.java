package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElement;
import Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.SingleClickListener;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class RegularBarkerViewHolder extends RecyclerView.ViewHolder {
    private final int barkerItemHeight;
    private final int barkerItemThreshold;
    private final int barkerItemWidth;
    private final ImageView image;
    private boolean isFirstView;
    private final MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private final String ownerId;
    private final Resources resources;
    private final int screenWidth;
    private final RoundedCornersTransformation transformation;

    public RegularBarkerViewHolder(final View itemView, final int barkerItemWidth, final int barkerItemHeight, final int screenWidth, final int barkerItemThreshold, final Resources resources, final RoundedCornersTransformation transformation, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(itemView);
        this.isFirstView = true;
        this.barkerItemWidth = barkerItemWidth;
        this.barkerItemHeight = barkerItemHeight;
        this.screenWidth = screenWidth;
        this.barkerItemThreshold = barkerItemThreshold;
        this.resources = resources;
        this.transformation = transformation;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.image = (ImageView) itemView.findViewById(R$id.regular_barker_image);
    }

    public void bind(final RegularBarkerElement element) {
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        final BarkerItemElement barker = element.getBarker();
        if (barker == null) {
            return;
        }
        if (this.screenWidth > this.barkerItemThreshold) {
            Picasso.get().load(barker.getBackgroundImageLarge()).placeholder(R$drawable.artwork_placeholder).transform(this.transformation).resize(this.barkerItemWidth, this.barkerItemHeight).into(this.image);
        } else {
            Picasso.get().load(barker.getBackgroundImageSmall()).placeholder(R$drawable.artwork_placeholder).transform(this.transformation).resize(this.barkerItemWidth, this.barkerItemHeight).into(this.image);
        }
        this.itemView.setContentDescription(barker.getTitle());
        this.itemView.setOnClickListener(new SingleClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.RegularBarkerViewHolder.1
            @Override // com.amazon.podcast.SingleClickListener
            public void onSingleClick(View view) {
                RegularBarkerViewHolder.this.methodsDispatcher.dispatch(RegularBarkerViewHolder.this.ownerId, barker.getOnItemSelected());
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
