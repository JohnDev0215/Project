package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadProgress;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.downloads.Downloads;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.views.DownloadButtonSmall;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.downloadsTemplate.DownloadsMethods;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
final class DownloadView extends ConstraintLayout implements Downloads.EventsListener {
    private UiMetricAttributes$ContentName contentName;
    private Download download;
    private DownloadButtonSmall downloadButton;
    private DownloadElement downloadElement;
    private String downloadLabel;
    private LiveData<Download> downloadLiveData;
    private UiMetricAttributes$PageType pageType;
    private String seeAllPageTitle;

    public DownloadView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void bind(final DownloadElement element, final String episodeName, final LifecycleOwner lifecycleOwner, final MethodsDispatcher methodsDispatcher, final String ownerId, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName) {
        bind(element, episodeName, lifecycleOwner, methodsDispatcher, ownerId, pageType);
        this.contentName = contentName;
    }

    public void bind(final DownloadElement element, final String episodeName, final LifecycleOwner lifecycleOwner, final MethodsDispatcher methodsDispatcher, final String ownerId, final UiMetricAttributes$PageType pageType, final String seeAllPageTitle) {
        bind(element, episodeName, lifecycleOwner, methodsDispatcher, ownerId, pageType);
        this.seeAllPageTitle = seeAllPageTitle;
    }

    private void bind(final DownloadElement element, final String episodeName, final LifecycleOwner lifecycleOwner, final MethodsDispatcher methodsDispatcher, final String ownerId, final UiMetricAttributes$PageType pageType) {
        LiveData<Download> liveData = this.downloadLiveData;
        if (liveData != null) {
            liveData.removeObservers(lifecycleOwner);
        }
        if (element == null) {
            this.downloadButton.setVisibility(8);
            return;
        }
        this.downloadButton.setVisibility(0);
        this.downloadElement = element;
        this.downloadLabel = String.format(getResources().getString(R$string.podcast_download_episode_button), episodeName);
        LiveData<Download> live = Podcast.getAppSync().download().readLive(getContext(), element.getId());
        this.downloadLiveData = live;
        live.observe(lifecycleOwner, new Observer<Download>() { // from class: com.amazon.podcast.views.episodeRowItemsListView.DownloadView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Download download) {
                DownloadView.this.bindDownload(download);
            }
        });
        this.downloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.DownloadView.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                DownloadView.this.handleDownloadClick(methodsDispatcher, ownerId);
            }
        });
        this.pageType = pageType;
    }

    @Override // com.amazon.podcast.downloads.Downloads.EventsListener
    public void onProgress(final long bytesDownloaded, final long fileSizeInBytes) {
        this.downloadButton.bindProgress(bytesDownloaded, fileSizeInBytes);
    }

    @Override // com.amazon.podcast.downloads.Downloads.EventsListener
    public void onDownloadRequested() {
        if (Podcast.getDownloads().isDownloadRequested(this.downloadElement.getId())) {
            this.downloadButton.bindDownloadRequested();
        }
    }

    @Override // com.amazon.podcast.downloads.Downloads.EventsListener
    public void onDownloadRequestCompleted() {
        this.downloadButton.bindDownloadRequestCompleted();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_episode_row_item_download_view, this);
        this.downloadButton = (DownloadButtonSmall) findViewById(R$id.episode_row_item_view_download_button_small);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindDownload(final Download download) {
        this.download = download;
        this.downloadButton.setVisibility(0);
        Downloads downloads = Podcast.getDownloads();
        if (download == null) {
            String id = this.downloadElement.getId();
            downloads.addEventsListener(id, this);
            if (downloads.isDownloadRequested(id)) {
                this.downloadButton.bindDownloadRequested();
            } else {
                this.downloadButton.bindNotDownloaded();
                this.downloadButton.setContentDescription(this.downloadLabel);
            }
            downloads.removeEventsListener(id, this);
            return;
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        if (DownloadState.QUEUED == downloadStateLookup) {
            this.downloadButton.bindQueued();
            this.downloadButton.setContentDescription(getResources().getString(R$string.podcast_cancel_download_button));
            return;
        }
        if (DownloadState.IN_PROGRESS == downloadStateLookup) {
            DownloadProgress progress = downloads.getProgress(download.getId());
            if (progress == null) {
                return;
            }
            this.downloadButton.bindInProgress(progress.getProgress(), progress.getMaxProgress());
            this.downloadButton.setContentDescription(getResources().getString(R$string.podcast_cancel_download_button));
            downloads.addEventsListener(download.getId(), this);
            return;
        }
        if (DownloadState.DOWNLOADED == downloadStateLookup) {
            this.downloadButton.bindDownloaded();
            this.downloadButton.setContentDescription(getResources().getString(R$string.podcast_remove_download_button));
            downloads.removeEventsListener(download.getId(), this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadClick(final MethodsDispatcher methodsDispatcher, final String ownerId) {
        Downloads downloads = Podcast.getDownloads();
        Download download = this.download;
        if (download == null) {
            ToastView toastView = new ToastView(getContext(), ToastView.ToastViewType.DOWNLOAD, "DOWNLOAD_PODCAST_NAVIGATION_TOAST_FLAG");
            toastView.dismissToast();
            toastView.showToast();
            downloads.checkSettingsAndDispatchOnDownload(ownerId, this.downloadElement.getOnDownload());
            return;
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        if (DownloadState.IN_PROGRESS == downloadStateLookup) {
            downloads.cancel(this.download.getId());
            return;
        }
        if (DownloadState.DOWNLOADED == downloadStateLookup) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(DownloadsMethods.deleteDownloadMethods(getResources(), this.download.getId()));
            String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), this.download.getId(), this.download.getPodcastEpisodeVariantId());
            UiMetricAttributes$ContentName uiMetricAttributes$ContentName = this.contentName;
            if (uiMetricAttributes$ContentName == null) {
                arrayList.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.REMOVE_DOWNLOAD_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, strPodcastEpisode, (Integer) null, this.seeAllPageTitle));
            } else {
                arrayList.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.REMOVE_DOWNLOAD_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, strPodcastEpisode, (Integer) null, uiMetricAttributes$ContentName));
            }
            methodsDispatcher.dispatch(ownerId, arrayList);
            return;
        }
        if (DownloadState.QUEUED == downloadStateLookup) {
            downloads.cancel(this.download.getId());
        }
    }
}
