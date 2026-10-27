package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElement;
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
import com.amazon.podcast.save.Save;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ToastView;

/* JADX INFO: loaded from: classes5.dex */
final class SaveItemViewHolder extends ContextMenuItemViewHolder {
    private final Context context;
    private boolean isSaved;
    private final Resources resources;
    private ToastView savePodcastToastView;

    public SaveItemViewHolder(@NonNull final View itemView, final Resources resources, final Context context) {
        super(itemView);
        this.resources = resources;
        this.context = context;
        init();
    }

    private void init() {
        this.savePodcastToastView = new ToastView(this.context, ToastView.ToastViewType.PLAYLIST, "ADD_PODCAST_NAVIGATION_TOAST_FLAG");
    }

    public void bind(final EpisodeOptionsElement episodeOptionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final EmberTextView dismiss, final boolean isPopUp) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.SaveItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!SaveItemViewHolder.this.isSaved) {
                    SaveItemViewHolder.this.savePodcastToastView.dismissToast();
                    SaveItemViewHolder.this.savePodcastToastView.showToast();
                    methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getSave().getOnSave());
                } else {
                    methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getSave().getOnUnsave());
                }
                EmberTextView emberTextView = dismiss;
                if (emberTextView == null) {
                    return;
                }
                emberTextView.performClick();
            }
        };
        bindSaveState(episodeOptionsElement.getSave(), lifecycleOwner, isPopUp);
        bindSaveIcon(isPopUp);
        super.bindClickerListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSaveIcon(final boolean isPopUp) {
        if (this.isSaved) {
            super.bindImage(R$drawable.ic_action_cancel_inline);
            String string = this.resources.getString(R$string.podcast_unSave);
            super.bindText(string, isPopUp);
            this.itemView.setContentDescription(string);
            this.itemView.setId(R$id.context_menu_remove_from_playlist_element);
            return;
        }
        super.bindImage(R$drawable.ic_action_add);
        String string2 = this.resources.getString(R$string.podcast_save);
        super.bindText(string2, isPopUp);
        this.itemView.setContentDescription(string2);
        this.itemView.setId(R$id.context_menu_add_to_playlist_element);
    }

    private void bindSaveState(final SaveElement saveElement, final LifecycleOwner lifecycleOwner, final boolean isPopUp) {
        Podcast.getAppSync().save().readLive(this.context, saveElement.getId()).observe(lifecycleOwner, new Observer<Save>() { // from class: com.amazon.podcast.views.contextMenu.SaveItemViewHolder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Save save) {
                SaveItemViewHolder.this.isSaved = save == null ? false : save.isSaved();
                SaveItemViewHolder.this.bindSaveIcon(isPopUp);
            }
        });
    }
}
