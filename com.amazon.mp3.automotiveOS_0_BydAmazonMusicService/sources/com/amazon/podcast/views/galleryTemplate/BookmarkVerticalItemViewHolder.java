package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes5.dex */
final class BookmarkVerticalItemViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private final int imageDimension;
    private final ImageView imageView;
    private LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private ImageButton playButton;
    private final ProgressBar progressBar;
    private Resources resources;
    private final RoundedCornersTransformation transformation;
    private EmberTextView verticalItemPrimaryText;
    private EmberTextView verticalItemSecondaryText;

    public BookmarkVerticalItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final RoundedCornersTransformation transformation, final int imageDimension, final LifecycleOwner lifecycleOwner, final Context context, final Resources resources) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = transformation;
        this.imageDimension = imageDimension;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
        ImageView imageView = (ImageView) view.findViewById(R$id.vertical_item_image);
        this.imageView = imageView;
        ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.vertical_item_horizontal_progress_bar);
        this.progressBar = progressBar;
        this.verticalItemPrimaryText = (EmberTextView) view.findViewById(R$id.vertical_item_primary_text);
        this.verticalItemSecondaryText = (EmberTextView) view.findViewById(R$id.vertical_item_secondary_text);
        this.playButton = (ImageButton) view.findViewById(R$id.vertical_item_play_button);
        imageView.getLayoutParams().height = imageDimension;
        imageView.getLayoutParams().width = imageDimension;
        progressBar.getLayoutParams().width = imageDimension;
        this.verticalItemPrimaryText.getLayoutParams().width = imageDimension;
        this.verticalItemSecondaryText.getLayoutParams().width = imageDimension;
    }

    public void bind(final long progressMilliseconds, final long totalDurationMilliseconds, final VerticalItemElement element) {
        RequestCreator requestCreatorTransform = Picasso.get().load(element.getImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.transformation);
        int i = this.imageDimension;
        requestCreatorTransform.resize(i, i).into(this.imageView);
        bindBookmark(progressMilliseconds, totalDurationMilliseconds, element);
        String primaryText = element.getPrimaryText();
        String secondaryText = element.getSecondaryText();
        if (primaryText != null && !primaryText.isEmpty()) {
            this.verticalItemPrimaryText.setText(element.getPrimaryText());
            this.verticalItemPrimaryText.setVisibility(0);
        }
        if (secondaryText != null && !secondaryText.isEmpty()) {
            this.verticalItemSecondaryText.setText(element.getSecondaryText());
            this.verticalItemSecondaryText.setVisibility(0);
        }
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.BookmarkVerticalItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BookmarkVerticalItemViewHolder.this.methodsDispatcher.dispatch(BookmarkVerticalItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.itemView.setContentDescription(element.getPrimaryText());
    }

    private void bindBookmark(final long progressMilliseconds, final long totalDurationMilliseconds, final VerticalItemElement verticalItemElement) {
        bindProgress(progressMilliseconds, totalDurationMilliseconds);
        this.playButton.setVisibility(0);
        this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.BookmarkVerticalItemViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PodcastHapticsUtil.triggerHaptics();
                BookmarkVerticalItemViewHolder.this.methodsDispatcher.dispatch(BookmarkVerticalItemViewHolder.this.ownerId, verticalItemElement.getOnItemSelected());
            }
        });
    }

    private void bindProgress(final long progressMilliseconds, final long totalDurationMilliseconds) {
        this.progressBar.setMax((int) totalDurationMilliseconds);
        this.progressBar.setProgress((int) progressMilliseconds);
        this.progressBar.setVisibility(0);
        this.verticalItemSecondaryText.setText(Strings.getRemainingDurationString(this.resources, totalDurationMilliseconds - progressMilliseconds));
        this.verticalItemSecondaryText.setVisibility(0);
    }
}
