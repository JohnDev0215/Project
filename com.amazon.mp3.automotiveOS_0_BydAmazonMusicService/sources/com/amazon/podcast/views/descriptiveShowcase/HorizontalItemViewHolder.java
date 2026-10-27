package com.amazon.podcast.views.descriptiveShowcase;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$string;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ToastView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes5.dex */
public final class HorizontalItemViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    public final ImageView followButton;
    private final LinearLayout followButtonContainer;
    private FollowElement followElement;
    private ToastView followToastView;
    private final ImageView image;
    private final int imageDimension;
    private final EmberTextView index;
    private boolean isFollowed;
    private LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final EmberTextView primaryText;
    private final EmberTextView secondaryText;
    private final RoundedCornersTransformation transformation;

    public HorizontalItemViewHolder(@NonNull final View itemView, final int imageDimension, final RoundedCornersTransformation transformation, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(itemView);
        this.index = (EmberTextView) itemView.findViewById(R$id.podcast_row_item_index);
        this.primaryText = (EmberTextView) itemView.findViewById(R$id.title);
        this.secondaryText = (EmberTextView) itemView.findViewById(R$id.subtitle);
        this.image = (ImageView) itemView.findViewById(R$id.image_view);
        this.followButtonContainer = (LinearLayout) itemView.findViewById(R$id.podcast_horizontal_row_item_follow_button_container);
        this.followButton = (ImageView) itemView.findViewById(R$id.podcast_row_item_follow_button);
        Context context = itemView.getContext();
        this.context = context;
        this.followToastView = new ToastView(context, ToastView.ToastViewType.FOLLOW, "FOLLOW_PODCAST_NAVIGATION_TOAST_FLAG");
        this.imageDimension = imageDimension;
        this.transformation = transformation;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
    }

    public void bind(final int position, final HorizontalItemElement element, final View.OnClickListener followElementOnclickListener) {
        if (position < 0) {
            this.index.setVisibility(8);
        } else {
            this.index.setText(String.valueOf(position));
        }
        this.primaryText.setText(element.getPrimaryText());
        String secondaryText = element.getSecondaryText();
        if (TextUtils.isEmpty(secondaryText)) {
            this.secondaryText.setVisibility(8);
        } else {
            this.secondaryText.setVisibility(0);
            this.secondaryText.setText(secondaryText);
        }
        RequestCreator requestCreatorError = Picasso.get().load(element.getImage()).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i = this.imageDimension;
        requestCreatorError.resize(i, i).transform(this.transformation).into(this.image);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.descriptiveShowcase.HorizontalItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HorizontalItemViewHolder.this.methodsDispatcher.dispatch(HorizontalItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        bindFollow(element.getFollow(), followElementOnclickListener);
    }

    private void bindFollowWithCustomOnclick(final View.OnClickListener followLayoutOnclickListener) {
        this.followButton.setImageResource(R$drawable.ic_follow);
        this.followButtonContainer.setContentDescription(this.context.getResources().getString(R$string.podcast_follow_label));
        this.followButtonContainer.setOnClickListener(followLayoutOnclickListener);
        this.followButtonContainer.setVisibility(0);
    }

    private void bindFollow(final FollowElement element, final View.OnClickListener followElementOnClickListener) {
        if (element == null) {
            return;
        }
        this.followElement = element;
        if (followElementOnClickListener != null) {
            bindFollowWithCustomOnclick(followElementOnClickListener);
            return;
        }
        Podcast.getAppSync().follow().readLive(this.context, this.followElement.getId()).observe(this.lifecycleOwner, new Observer<Follow>() { // from class: com.amazon.podcast.views.descriptiveShowcase.HorizontalItemViewHolder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Follow follow) {
                HorizontalItemViewHolder.this.isFollowed = follow == null ? false : follow.isFollowed();
                if (HorizontalItemViewHolder.this.isFollowed) {
                    HorizontalItemViewHolder.this.followButton.setImageResource(R$drawable.ic_following);
                    HorizontalItemViewHolder.this.followButtonContainer.setContentDescription(HorizontalItemViewHolder.this.context.getResources().getString(R$string.podcast_unFollow_label));
                } else {
                    HorizontalItemViewHolder.this.followButton.setImageResource(R$drawable.ic_follow);
                    HorizontalItemViewHolder.this.followButtonContainer.setContentDescription(HorizontalItemViewHolder.this.context.getResources().getString(R$string.podcast_follow_label));
                }
            }
        });
        this.followButtonContainer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.descriptiveShowcase.HorizontalItemViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HorizontalItemViewHolder.this.handleFollowClick();
            }
        });
        this.followButtonContainer.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFollowClick() {
        if (this.isFollowed) {
            this.methodsDispatcher.dispatch(this.ownerId, this.followElement.getOnUnFollow());
            return;
        }
        this.followToastView.dismissToast();
        this.followToastView.showToast();
        this.methodsDispatcher.dispatch(this.ownerId, this.followElement.getOnFollow());
    }
}
