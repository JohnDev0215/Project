package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.DownloadElement;
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
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.downloads.Downloads;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.downloadsTemplate.DownloadsMethods;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
final class DownloadItemViewHolder extends ContextMenuItemViewHolder {
    private final Context context;
    private DownloadElement downloadElement;
    private Download downloadObject;
    private ToastView downloadToastView;
    private final Resources resources;

    public DownloadItemViewHolder(@NonNull final View itemView, final Resources resources, final Context context) {
        super(itemView);
        this.resources = resources;
        this.context = context;
        init();
    }

    private void init() {
        this.downloadToastView = new ToastView(this.context, ToastView.ToastViewType.DOWNLOAD, "DOWNLOAD_PODCAST_NAVIGATION_TOAST_FLAG");
    }

    public void bind(final EpisodeOptionsElement episodeOptionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final EmberTextView dismiss, final boolean isPopUp, final UiMetricAttributes$PageType pageType) {
        this.downloadElement = episodeOptionsElement.getDownload();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.DownloadItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Downloads downloads = Podcast.getDownloads();
                if (DownloadItemViewHolder.this.downloadObject == null) {
                    DownloadItemViewHolder.this.downloadToastView.dismissToast();
                    DownloadItemViewHolder.this.downloadToastView.showToast();
                    downloads.checkSettingsAndDispatchOnDownload(ownerId, episodeOptionsElement.getDownload().getOnDownload());
                    EmberTextView emberTextView = dismiss;
                    if (emberTextView == null) {
                        return;
                    }
                    emberTextView.performClick();
                    return;
                }
                DownloadState downloadStateLookup = DownloadState.lookup(DownloadItemViewHolder.this.downloadObject.getState());
                String id = DownloadItemViewHolder.this.downloadObject.getId();
                if (DownloadState.IN_PROGRESS == downloadStateLookup || DownloadState.QUEUED == downloadStateLookup) {
                    downloads.cancel(id);
                } else if (DownloadState.DOWNLOADED == downloadStateLookup) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(DownloadsMethods.deleteDownloadMethods(DownloadItemViewHolder.this.resources, id));
                    arrayList.addAll(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.REMOVE_DOWNLOAD_PODCAST_EPISODE, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU));
                    MethodsDispatcher methodsDispatcher2 = methodsDispatcher;
                    if (methodsDispatcher2 instanceof Playback) {
                        methodsDispatcher2.dispatch(id, arrayList);
                    } else {
                        methodsDispatcher2.dispatch(ownerId, arrayList);
                    }
                }
                EmberTextView emberTextView2 = dismiss;
                if (emberTextView2 == null) {
                    return;
                }
                emberTextView2.performClick();
            }
        };
        String id = episodeOptionsElement.getDownload().getId();
        bindDownloadState(id, lifecycleOwner, isPopUp);
        if (this.downloadObject == null) {
            Download currentDownload = getCurrentDownload(id);
            this.downloadObject = currentDownload;
            if (currentDownload == null) {
                super.bindImage(R$drawable.ic_podcast_download);
                super.bindText(this.resources.getString(R$string.podcast_download), isPopUp);
            } else {
                bindDownloadIcon(isPopUp);
            }
        } else {
            bindDownloadIcon(isPopUp);
        }
        super.bindClickerListener(onClickListener);
    }

    private void bindDownloadState(final String episodeId, final LifecycleOwner lifecycleOwner, final boolean isPopUp) {
        Podcast.getAppSync().download().readLive(this.context, episodeId).observe(lifecycleOwner, new Observer<Download>() { // from class: com.amazon.podcast.views.contextMenu.DownloadItemViewHolder.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Download download) {
                DownloadItemViewHolder.this.downloadObject = download;
                DownloadItemViewHolder.this.bindDownloadIcon(isPopUp);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindDownloadIcon(final boolean isPopUp) {
        String string = this.resources.getString(R$string.podcast_download_label);
        String string2 = this.resources.getString(R$string.podcast_unDownload_label);
        Download download = this.downloadObject;
        if (download == null) {
            if (Podcast.getDownloads().isDownloadRequested(this.downloadElement.getId())) {
                bindDownloadQueuedIcon(isPopUp);
                return;
            }
            bindNotDownloadedIcon(isPopUp);
            this.itemView.setContentDescription(string);
            this.itemView.setId(R$id.context_menu_download_episode_element);
            return;
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        if (DownloadState.QUEUED == downloadStateLookup || DownloadState.IN_PROGRESS == downloadStateLookup) {
            bindDownloadQueuedIcon(isPopUp);
            this.itemView.setContentDescription(string2);
            this.itemView.setId(R$id.context_menu_remove_download_element);
        } else {
            if (DownloadState.DOWNLOADED == downloadStateLookup) {
                super.bindImage(R$drawable.ic_action_delete);
                super.bindText(string2, isPopUp);
                this.itemView.setContentDescription(string2);
                this.itemView.setId(R$id.context_menu_remove_download_element);
                return;
            }
            bindNotDownloadedIcon(isPopUp);
            this.itemView.setContentDescription(string);
            this.itemView.setId(R$id.context_menu_download_episode_element);
        }
    }

    private Download getCurrentDownload(final String mediaId) {
        return Podcast.getAppSync().download().read(this.context, mediaId);
    }

    private void bindNotDownloadedIcon(final boolean isPopUp) {
        super.bindImage(R$drawable.ic_podcast_download);
        super.bindText(this.resources.getString(R$string.podcast_download), isPopUp);
    }

    private void bindDownloadQueuedIcon(final boolean isPopUp) {
        String string = this.resources.getString(R$string.podcast_cancel_button);
        super.bindImage(R$drawable.podcast_ic_action_cancelinline);
        super.bindText(string, isPopUp);
        this.itemView.setContentDescription(string);
        this.itemView.setId(R$id.context_menu_cancel_download_element);
    }
}
