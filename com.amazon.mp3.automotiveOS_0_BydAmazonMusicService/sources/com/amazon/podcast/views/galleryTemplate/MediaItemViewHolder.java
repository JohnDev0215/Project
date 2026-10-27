package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$string;
import com.amazon.podcast.biteBookmark.BiteBookmark;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class MediaItemViewHolder extends RecyclerView.ViewHolder {
    private static final Double MILLISECONDS_TO_MINUTES_RATIO = Double.valueOf(60000.0d);
    private final Context context;
    private RelativeLayout durationDisplay;
    private EmberTextView durationText;
    private ImageView image;
    private ImageView imageOutline;
    private boolean isListened;
    private final int itemDimension;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final RoundedCornersTransformation transformation;

    public MediaItemViewHolder(@NonNull final View itemView, final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final int itemDimension) {
        super(itemView);
        this.context = itemView.getContext();
        this.ownerId = ownerId;
        this.resources = resources;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.itemDimension = itemDimension;
        this.transformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_small_item_image_corner_radius));
        init(itemView);
    }

    private void init(View itemView) {
        this.image = (ImageView) itemView.findViewById(R$id.media_item_image);
        this.imageOutline = (ImageView) itemView.findViewById(R$id.media_item_outline);
        this.durationDisplay = (RelativeLayout) itemView.findViewById(R$id.media_item_duration_display);
        this.durationText = (EmberTextView) itemView.findViewById(R$id.media_item_duration_text);
        ViewGroup.LayoutParams layoutParams = this.imageOutline.getLayoutParams();
        int i = this.itemDimension;
        Resources resources = this.resources;
        int i2 = R$dimen.podcast_small_item_outline_offset;
        layoutParams.width = i + resources.getDimensionPixelSize(i2);
        layoutParams.height = this.itemDimension + this.resources.getDimensionPixelSize(i2);
        this.imageOutline.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.image.getLayoutParams();
        int i3 = this.itemDimension;
        layoutParams2.width = i3;
        layoutParams2.height = i3;
        this.image.setLayoutParams(layoutParams2);
    }

    public void bind(final MediaItemElement element, final List<Method> onLaunchBitesExperience) {
        if (element.getImage().isEmpty()) {
            Picasso picasso = Picasso.get();
            int i = R$drawable.refresh_ic_empty_state_image;
            RequestCreator requestCreatorTransform = picasso.load(i).centerCrop().error(i).transform(this.transformation);
            int i2 = this.itemDimension;
            requestCreatorTransform.resize(i2, i2).into(this.image);
        } else {
            RequestCreator requestCreatorCenterCrop = Picasso.get().load(element.getImage()).centerCrop();
            int i3 = R$drawable.refresh_ic_empty_state_image;
            RequestCreator requestCreatorTransform2 = requestCreatorCenterCrop.placeholder(i3).error(i3).transform(this.transformation);
            int i4 = this.itemDimension;
            requestCreatorTransform2.resize(i4, i4).into(this.image);
        }
        bindBitesListenedProgress(element);
        bindDurationLabel(element, onLaunchBitesExperience);
        this.image.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.MediaItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaItemViewHolder.this.handleOnClickLaunchExperience(element, onLaunchBitesExperience);
            }
        });
    }

    private void bindBitesListenedProgress(final MediaItemElement itemElement) {
        Podcast.getAppSync().biteBookmark().readLive(this.context, itemElement.getMediaId()).observe(this.lifecycleOwner, new Observer<BiteBookmark>() { // from class: com.amazon.podcast.views.galleryTemplate.MediaItemViewHolder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final BiteBookmark biteBookmark) {
                MediaItemViewHolder.this.isListened = biteBookmark != null && biteBookmark.getProgressMilliseconds() > 0;
                if (MediaItemViewHolder.this.isListened) {
                    MediaItemViewHolder.this.imageOutline.setVisibility(4);
                    MediaItemViewHolder.this.image.setContentDescription(String.format(MediaItemViewHolder.this.resources.getString(R$string.podcast_bites_visual_shoveler_item_heard), Integer.toString(MediaItemViewHolder.this.getAdapterPosition())));
                } else {
                    MediaItemViewHolder.this.imageOutline.setVisibility(0);
                    MediaItemViewHolder.this.image.setContentDescription(String.format(MediaItemViewHolder.this.resources.getString(R$string.podcast_bites_visual_shoveler_item_unheard), Integer.toString(MediaItemViewHolder.this.getAdapterPosition())));
                }
            }
        });
    }

    private void bindDurationLabel(final MediaItemElement itemElement, final List<Method> onLaunchBitesExperience) {
        Long durationMilliseconds = itemElement.getDurationMilliseconds();
        if (durationMilliseconds != null && durationMilliseconds.longValue() > 0) {
            this.durationDisplay.setVisibility(0);
            String mediaItemDurationString = getMediaItemDurationString(durationMilliseconds.longValue());
            this.durationText.setText(mediaItemDurationString);
            this.durationText.setContentDescription(String.format(this.resources.getString(R$string.podcast_bites_visual_shoveler_duration), Integer.toString(getAdapterPosition()), mediaItemDurationString));
            this.durationDisplay.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.MediaItemViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MediaItemViewHolder.this.handleOnClickLaunchExperience(itemElement, onLaunchBitesExperience);
                }
            });
            return;
        }
        this.durationDisplay.setVisibility(8);
    }

    private String getMediaItemDurationString(final long durationInMilliseconds) {
        String string;
        int iRound = (int) Math.round(durationInMilliseconds / MILLISECONDS_TO_MINUTES_RATIO.doubleValue());
        if (iRound <= 1) {
            string = this.resources.getString(R$string.podcast_one_minute);
        } else {
            string = String.format(this.resources.getString(R$string.podcast_number_of_minutes), Integer.toString(iRound));
        }
        return string.toUpperCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnClickLaunchExperience(final MediaItemElement itemElement, final List<Method> onLaunchBitesExperience) {
        Podcast.getPlayback().setBitesPlaybackStartingIndex(getAdapterPosition());
        this.methodsDispatcher.dispatch(this.ownerId, itemElement.getOnItemSelected());
        this.methodsDispatcher.dispatch(this.ownerId, onLaunchBitesExperience);
    }
}
