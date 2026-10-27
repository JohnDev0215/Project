package com.amazon.podcast.views.ptcEpisodesTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElement;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes5.dex */
final class PTCListItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView image;
    private int imageDimension;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private ImageButton playButton;
    private final Resources resources;
    private EmberTextView subtitle;
    private EmberTextView title;
    private Transformation transformation;
    private final View view;

    public PTCListItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources) {
        super(view);
        this.view = view;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerId = ownerId;
        this.resources = resources;
        init();
    }

    public final void bind(final ListItemElement element) {
        this.title.setText(element.getTitle());
        this.subtitle.setText(element.getSubtitle());
        RequestCreator requestCreatorError = Picasso.get().load(element.getImage()).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i = this.imageDimension;
        requestCreatorError.resize(i, i).transform(this.transformation).into(this.image);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcEpisodesTemplate.PTCListItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PTCListItemViewHolder.this.methodsDispatcher.dispatch(PTCListItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.image.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcEpisodesTemplate.PTCListItemViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PTCListItemViewHolder.this.methodsDispatcher.dispatch(PTCListItemViewHolder.this.ownerId, element.getOnImageSelected());
            }
        });
        this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcEpisodesTemplate.PTCListItemViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PodcastHapticsUtil.triggerHaptics();
                PTCListItemViewHolder.this.methodsDispatcher.dispatch(PTCListItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
    }

    private void init() {
        this.image = (ImageView) this.view.findViewById(R$id.podcast_episode_list_item_image);
        this.title = (EmberTextView) this.view.findViewById(R$id.podcast_list_item_primary_text);
        this.subtitle = (EmberTextView) this.view.findViewById(R$id.podcast_list_item_secondary_text);
        this.playButton = (ImageButton) this.view.findViewById(R$id.podcast_list_item_play_button);
        this.imageDimension = this.resources.getDimensionPixelSize(R$dimen.podcast_list_item_image_dimension);
        this.transformation = new RoundedCornersTransformation(this.resources.getDimensionPixelSize(R$dimen.podcast_list_item_image_corner_radius));
    }
}
