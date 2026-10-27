package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.FollowElement;
import Podcast.FollowInterface.v1_0.FollowWriteElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$string;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ToastView;

/* JADX INFO: loaded from: classes5.dex */
final class FollowItemViewHolder extends ContextMenuItemViewHolder {
    private final Context context;
    private ToastView followToastView;
    private boolean isFollowed;
    private final Resources resources;

    public FollowItemViewHolder(@NonNull final View itemView, final Resources resources, final Context context) {
        super(itemView);
        this.resources = resources;
        this.context = context;
        init();
    }

    private void init() {
        this.followToastView = new ToastView(this.context, ToastView.ToastViewType.FOLLOW, "FOLLOW_PODCAST_NAVIGATION_TOAST_FLAG");
    }

    public void bind(final EpisodeOptionsElement episodeOptionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final EmberTextView dismiss, final boolean isPopUp) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.FollowItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!FollowItemViewHolder.this.isFollowed) {
                    FollowItemViewHolder.this.followToastView.dismissToast();
                    FollowItemViewHolder.this.followToastView.showToast();
                    methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getFollow().getOnFollow());
                } else {
                    methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getFollow().getOnUnFollow());
                }
                EmberTextView emberTextView = dismiss;
                if (emberTextView == null) {
                    return;
                }
                emberTextView.performClick();
            }
        };
        bindFollowState(episodeOptionsElement, lifecycleOwner, isPopUp);
        super.bindClickerListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindFollowIcon(final String podcastName, final boolean isPopUp) {
        if (this.isFollowed) {
            super.bindImage(R$drawable.ic_following);
            super.bindText(String.format(this.resources.getString(R$string.podcast_episode_options_unfollow_label), podcastName), isPopUp);
            this.itemView.setContentDescription(this.resources.getString(R$string.podcast_unFollow_label));
            this.itemView.setId(R$id.context_menu_unfollow_podcast_element);
            return;
        }
        super.bindImage(R$drawable.ic_follow);
        super.bindText(String.format(this.resources.getString(R$string.podcast_episode_options_follow_label), podcastName), isPopUp);
        this.itemView.setContentDescription(this.resources.getString(R$string.podcast_follow_label));
        this.itemView.setId(R$id.context_menu_follow_podcast_element);
    }

    private void bindFollowState(final EpisodeOptionsElement episodeOptionsElement, final LifecycleOwner lifecycleOwner, final boolean isPopUp) {
        final FollowElement follow = episodeOptionsElement.getFollow();
        Podcast.getAppSync().follow().readLive(this.context, follow.getId()).observe(lifecycleOwner, new Observer<Follow>() { // from class: com.amazon.podcast.views.contextMenu.FollowItemViewHolder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Follow follow2) {
                FollowItemViewHolder.this.isFollowed = follow2 == null ? false : follow2.isFollowed();
                FollowItemViewHolder.this.bindFollowIcon(((FollowWriteElement) ((WriteMethod) follow.getOnFollow().get(0)).getElement().get(0)).getTitle(), isPopUp);
            }
        });
    }
}
