package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.CompletedElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
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
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ToastView;

/* JADX INFO: loaded from: classes5.dex */
final class MarkAsPlayedViewHolder extends ContextMenuItemViewHolder {
    private final Context context;
    private boolean isCompleted;
    private ToastView markAsPlayedToastView;
    private Playback playback;
    private final Resources resources;

    public MarkAsPlayedViewHolder(@NonNull final View itemView, final Resources resources, final Context context) {
        super(itemView);
        this.resources = resources;
        this.context = context;
        this.playback = Podcast.getPlayback();
        this.markAsPlayedToastView = new ToastView(context, ToastView.ToastViewType.MARK_AS_PLAYED, "MARK_AS_PLAYED_TOAST_FLAG");
    }

    public void bind(final EpisodeOptionsElement episodeOptionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final EmberTextView dismiss, final boolean isPopUp) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.MarkAsPlayedViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlaybackCache.get(episodeOptionsElement.getCompleted().getId()).updateProgress(0L);
                if (MarkAsPlayedViewHolder.this.isCompleted) {
                    if (MarkAsPlayedViewHolder.this.isEpisodeInMediaPlayer(episodeOptionsElement)) {
                        MarkAsPlayedViewHolder.this.playback.seekTo(0);
                    }
                    methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getCompleted().getOnMarkUnplayed());
                } else {
                    MarkAsPlayedViewHolder.this.markAsPlayedToastView.dismissToast();
                    MarkAsPlayedViewHolder.this.markAsPlayedToastView.showToast();
                    int playbackDuration = (int) MarkAsPlayedViewHolder.this.playback.getPlaybackDuration();
                    if (MarkAsPlayedViewHolder.this.isEpisodeInMediaPlayer(episodeOptionsElement)) {
                        int playbackState = Podcast.getPlayback().getPlaybackState();
                        MarkAsPlayedViewHolder.this.playback.pause();
                        MarkAsPlayedViewHolder.this.playback.seekTo(playbackDuration);
                        if (playbackState == 3) {
                            MarkAsPlayedViewHolder.this.playback.next();
                        }
                    }
                    methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getCompleted().getOnMarkPlayed());
                }
                EmberTextView emberTextView = dismiss;
                if (emberTextView == null) {
                    return;
                }
                emberTextView.performClick();
            }
        };
        bindCompletedState(episodeOptionsElement.getCompleted(), lifecycleOwner, isPopUp);
        bindCompletedIcon(isPopUp);
        super.bindClickerListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindCompletedIcon(final boolean isPopUp) {
        if (this.isCompleted) {
            super.bindImage(R$drawable.ic_action_radiouncheck);
            String string = this.resources.getString(R$string.podcast_mark_as_unplayed);
            super.bindText(string, isPopUp);
            this.itemView.setContentDescription(string);
            this.itemView.setId(R$id.context_menu_mark_as_unplayed_element);
            return;
        }
        super.bindImage(R$drawable.ic_action_doneinline);
        String string2 = this.resources.getString(R$string.podcast_mark_as_played);
        super.bindText(string2, isPopUp);
        this.itemView.setContentDescription(string2);
        this.itemView.setId(R$id.context_menu_mark_as_played_element);
    }

    private void bindCompletedState(final CompletedElement completedElement, final LifecycleOwner lifecycleOwner, final boolean isPopUp) {
        Podcast.getAppSync().completed().readLive(this.context, completedElement.getId()).observe(lifecycleOwner, new Observer<Completed>() { // from class: com.amazon.podcast.views.contextMenu.MarkAsPlayedViewHolder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(Completed completed) {
                MarkAsPlayedViewHolder.this.isCompleted = completed == null ? false : completed.getCompleted();
                MarkAsPlayedViewHolder.this.bindCompletedIcon(isPopUp);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEpisodeInMediaPlayer(final EpisodeOptionsElement episodeOptionsElement) {
        return episodeOptionsElement.getCompleted().getId().equals(this.playback.getMedia().mediaId());
    }
}
