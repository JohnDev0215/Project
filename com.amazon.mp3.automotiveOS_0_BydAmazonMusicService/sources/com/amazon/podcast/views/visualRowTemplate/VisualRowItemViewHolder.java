package com.amazon.podcast.views.visualRowTemplate;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.BookmarkElement;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.DownloadElement;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$string;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadProgress;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.downloads.Downloads;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.DownloadButtonSmall;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import com.amazon.podcast.views.downloadsTemplate.DownloadsMethods;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class VisualRowItemViewHolder extends RecyclerView.ViewHolder implements Playback.StateCallback, Downloads.EventsListener {
    private static final Logger logger = LoggerFactory.getLogger("VisualRowItemViewHolder");
    private BookmarkElement bookmarkElement;
    private LiveData<Bookmark> bookmarkLiveData;
    private final ImageView centerLargeIcon;
    private UiMetricAttributes$ContentName contentName;
    private Context context;
    private final EmberTextView description;
    private Download download;
    private DownloadButtonSmall downloadButton;
    private DownloadElement downloadElement;
    private ImageView downloadIcon;
    private LiveData<Download> downloadLiveData;
    private ToastView downloadToastView;
    private final Downloads downloads;
    private final ImageView equalizer;
    private final AnimationDrawable equalizerDrawable;
    private final ImageView image;
    private final int imageDimension;
    private final ImageView imageOverlay;
    private LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final ImageView overflowButton;
    private final String ownerId;
    private UiMetricAttributes$PageType pageType;
    private final EmberTextView primaryText;
    private ProgressBar progressBar;
    private final Resources resources;
    private final EmberTextView secondaryText;
    private final ImageView smallIcon;
    private final TemplateContext templateContext;
    private final EmberTextView tertiaryFollowingText;
    private final EmberTextView tertiaryText;
    private final RoundedCornersTransformation transformation;

    public VisualRowItemViewHolder(@NonNull final View itemView, final int imageDimension, final RoundedCornersTransformation transformation, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final Context context, final Resources resources, final TemplateContext templateContext) {
        super(itemView);
        this.templateContext = templateContext;
        this.primaryText = (EmberTextView) itemView.findViewById(R$id.podcast_visual_row_item_primary_text);
        this.secondaryText = (EmberTextView) itemView.findViewById(R$id.podcast_visual_row_item_secondary_text);
        this.tertiaryText = (EmberTextView) itemView.findViewById(R$id.podcast_visual_row_item_tertiary_text);
        this.tertiaryFollowingText = (EmberTextView) itemView.findViewById(R$id.podcast_visual_row_item_tertiary_following_text);
        this.description = (EmberTextView) itemView.findViewById(R$id.podcast_visual_row_item_description);
        this.image = (ImageView) itemView.findViewById(R$id.podcast_visual_row_item_image);
        this.smallIcon = (ImageView) itemView.findViewById(R$id.podcast_visual_row_item_small_icon);
        this.imageOverlay = (ImageView) itemView.findViewById(R$id.podcast_visual_row_item_image_overlay);
        this.centerLargeIcon = (ImageView) itemView.findViewById(R$id.podcast_visual_row_item_center_large_icon);
        this.progressBar = (ProgressBar) itemView.findViewById(R$id.podcast_visual_row_item_horizontal_progress_bar);
        ImageView imageView = (ImageView) itemView.findViewById(R$id.podcast_visual_row_item_image_equalizer);
        this.equalizer = imageView;
        this.equalizerDrawable = (AnimationDrawable) imageView.getDrawable();
        this.downloadButton = (DownloadButtonSmall) itemView.findViewById(R$id.podcast_visual_row_item_download);
        this.overflowButton = (ImageView) itemView.findViewById(R$id.podcast_visual_row_item_overflow_button);
        this.downloadIcon = (ImageView) itemView.findViewById(R$id.podcast_download_icon);
        this.imageDimension = imageDimension;
        this.transformation = transformation;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
        this.downloads = Podcast.getDownloads();
        Podcast.getPlayback().addStateCallback(this);
        this.downloadToastView = new ToastView(context, ToastView.ToastViewType.DOWNLOAD, "DOWNLOAD_PODCAST_NAVIGATION_TOAST_FLAG");
    }

    /* JADX INFO: renamed from: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType;

        static {
            int[] iArr = new int[UiMetricAttributes$PageType.values().length];
            $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType = iArr;
            try {
                iArr[UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[UiMetricAttributes$PageType.PODCASTS_SAVED_EPISODES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void bind(final VisualRowItemElement element, final UiMetricAttributes$PageType pageType) {
        String description;
        this.pageType = pageType;
        int i = AnonymousClass7.$SwitchMap$com$amazon$podcast$metrics$UiMetricAttributes$PageType[pageType.ordinal()];
        if (i == 1) {
            this.contentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_DOWNLOADS;
        } else if (i == 2) {
            this.contentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_PLAYLIST;
        } else if (i == 3) {
            this.contentName = UiMetricAttributes$ContentName.PODCASTS_JUMP_BACK_IN_EPISODES;
        } else if (i == 4) {
            this.contentName = UiMetricAttributes$ContentName.PODCASTS_SAVED_EPISODES;
        }
        this.primaryText.setText(element.getPrimaryText());
        String secondaryText = element.getSecondaryText();
        if (!StringUtils.isBlank(secondaryText)) {
            this.secondaryText.setText(secondaryText);
            this.secondaryText.setVisibility(0);
        }
        String tertiaryText = element.getTertiaryText();
        if (!StringUtils.isBlank(tertiaryText)) {
            this.tertiaryText.setText(tertiaryText);
            this.tertiaryText.setVisibility(0);
        }
        if (StringUtils.isBlank(element.getDescription())) {
            description = this.context.getResources().getString(R$string.podcast_description_placeholder_text);
        } else {
            description = element.getDescription();
        }
        EmberTextView emberTextView = this.description;
        emberTextView.setText(Strings.convertToSpannable(description, emberTextView));
        bindBookmark(element.getBookmark());
        bindContextMenuButton(element.getEpisodeOptions());
        bindEqualizer(element.getBookmark(), Podcast.getPlayback().getPlaybackState());
        bindDownloadElement(element.getDownload());
        RequestCreator requestCreatorError = Picasso.get().load(element.getImage()).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i2 = this.imageDimension;
        requestCreatorError.resize(i2, i2).transform(this.transformation).into(this.image);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VisualRowItemViewHolder.this.methodsDispatcher.dispatch(VisualRowItemViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.image.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VisualRowItemViewHolder.this.methodsDispatcher.dispatch(VisualRowItemViewHolder.this.ownerId, element.getOnImageSelected());
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        bindEqualizer(this.bookmarkElement, state);
    }

    private void bindEqualizer(final BookmarkElement bookmarkElement, final int state) {
        if (bookmarkElement == null) {
            hideEqualizer();
            return;
        }
        if (!bookmarkElement.getId().equals(Podcast.getPlayback().getMedia().mediaId())) {
            hideEqualizer();
        } else if (state != 3) {
            hideEqualizer();
        } else {
            startEqualizer();
        }
    }

    private void startEqualizer() {
        if (this.bookmarkElement == null) {
            return;
        }
        if (this.bookmarkElement.getId().equals(Podcast.getPlayback().getMedia().mediaId())) {
            this.imageOverlay.setVisibility(0);
            this.smallIcon.setVisibility(4);
            this.centerLargeIcon.setVisibility(4);
            this.progressBar.setVisibility(4);
            this.equalizerDrawable.start();
            this.equalizer.setVisibility(0);
        }
    }

    private void hideEqualizer() {
        if (this.bookmarkElement == null) {
            this.imageOverlay.setVisibility(4);
            this.smallIcon.setVisibility(0);
            this.centerLargeIcon.setVisibility(4);
            this.progressBar.setVisibility(4);
        }
        AnimationDrawable animationDrawable = this.equalizerDrawable;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.equalizer.setVisibility(4);
    }

    @Override // com.amazon.podcast.downloads.Downloads.EventsListener
    public void onProgress(final long bytesDownloaded, final long fileSizeInBytes) {
        this.downloadButton.bindProgress(bytesDownloaded, fileSizeInBytes);
    }

    @Override // com.amazon.podcast.downloads.Downloads.EventsListener
    public void onDownloadRequested() {
        this.downloadButton.bindDownloadRequested();
    }

    @Override // com.amazon.podcast.downloads.Downloads.EventsListener
    public void onDownloadRequestCompleted() {
        this.downloadButton.bindDownloadRequestCompleted();
    }

    private void bindContextMenuButton(final EpisodeOptionsElement episodeOptionsElement) {
        if (episodeOptionsElement == null) {
            return;
        }
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                ContextMenus.show(episodeOptionsElement, VisualRowItemViewHolder.this.context, VisualRowItemViewHolder.this.resources, VisualRowItemViewHolder.this.methodsDispatcher, VisualRowItemViewHolder.this.ownerId, VisualRowItemViewHolder.this.templateContext.getLifecycleOwner(), VisualRowItemViewHolder.this.templateContext.getFragmentManager(), VisualRowItemViewHolder.this.overflowButton, "VisualRowItemViewHolder", VisualRowItemViewHolder.this.pageType);
            }
        });
        this.overflowButton.setVisibility(0);
        this.overflowButton.setContentDescription(this.resources.getString(R$string.podcast_overflow_button));
    }

    private void bindDownloadElement(final DownloadElement element) {
        LiveData<Download> liveData = this.downloadLiveData;
        if (liveData != null) {
            liveData.removeObservers(this.lifecycleOwner);
        }
        if (element == null) {
            this.downloadButton.setVisibility(4);
            return;
        }
        this.downloadElement = element;
        LiveData<Download> live = Podcast.getAppSync().download().readLive(this.context, element.getId());
        this.downloadLiveData = live;
        live.observe(this.lifecycleOwner, new Observer<Download>() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Download download) {
                VisualRowItemViewHolder.this.bindDownload(download);
            }
        });
        this.downloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VisualRowItemViewHolder.this.downloadClick();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindDownload(final Download download) {
        this.download = download;
        if (this.downloadButton.getVisibility() != 0) {
            this.downloadButton.setVisibility(0);
        }
        Downloads downloads = Podcast.getDownloads();
        if (download == null) {
            String id = this.downloadElement.getId();
            downloads.addEventsListener(id, this);
            if (downloads.isDownloadRequested(id)) {
                this.downloadButton.bindDownloadRequested();
                return;
            } else {
                this.downloadButton.bindNotDownloaded();
                this.downloadButton.setContentDescription(this.context.getResources().getString(R$string.podcast_download_label));
                return;
            }
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        if (DownloadState.QUEUED == downloadStateLookup) {
            this.downloadButton.bindQueued();
            return;
        }
        if (DownloadState.IN_PROGRESS == downloadStateLookup) {
            DownloadProgress progress = downloads.getProgress(download.getId());
            if (progress == null) {
                return;
            }
            this.downloadButton.bindInProgress(progress.getProgress(), progress.getMaxProgress());
            downloads.addEventsListener(download.getId(), this);
            return;
        }
        if (DownloadState.DOWNLOADED == downloadStateLookup) {
            this.downloadButton.bindDownloaded();
            this.downloadButton.setContentDescription(this.context.getResources().getString(R$string.podcast_unDownload_label));
            downloads.removeEventsListener(download.getId(), this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadClick() {
        Downloads downloads = Podcast.getDownloads();
        Download download = this.download;
        if (download == null) {
            logger.debug("Download initiated");
            this.downloadToastView.dismissToast();
            this.downloadToastView.showToast();
            downloads.checkSettingsAndDispatchOnDownload(this.ownerId, this.downloadElement.getOnDownload());
            return;
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        if (DownloadState.IN_PROGRESS == downloadStateLookup) {
            logger.debug("Download canceled");
            downloads.cancel(this.download.getId());
        } else {
            if (DownloadState.DOWNLOADED == downloadStateLookup) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(DownloadsMethods.deleteDownloadMethods(this.resources, this.download.getId()));
                arrayList.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.REMOVE_DOWNLOAD_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), this.download.getId(), this.download.getPodcastEpisodeVariantId()), (Integer) null, this.contentName));
                this.methodsDispatcher.dispatch(this.ownerId, arrayList);
                return;
            }
            if (DownloadState.QUEUED == downloadStateLookup) {
                downloads.cancel(this.download.getId());
            }
        }
    }

    private void bindBookmark(final BookmarkElement element) {
        if (element == null) {
            return;
        }
        this.bookmarkElement = element;
        LiveData<Bookmark> liveData = this.bookmarkLiveData;
        if (liveData != null) {
            liveData.removeObservers(this.lifecycleOwner);
        }
        LiveData<Bookmark> live = Podcast.getAppSync().bookmark().readLive(this.context, this.bookmarkElement.getId());
        this.bookmarkLiveData = live;
        live.observe(this.lifecycleOwner, new Observer<Bookmark>() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowItemViewHolder.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Bookmark bookmark) {
                if (bookmark == null) {
                    if (VisualRowItemViewHolder.this.bookmarkElement.getTotalDurationMilliseconds() == null) {
                        return;
                    }
                    VisualRowItemViewHolder.this.tertiaryFollowingText.setText(Strings.getTextWithTotalDuration(VisualRowItemViewHolder.this.resources, VisualRowItemViewHolder.this.bookmarkElement.getTotalDurationMilliseconds(), VisualRowItemViewHolder.this.tertiaryText));
                    VisualRowItemViewHolder.this.tertiaryFollowingText.setVisibility(0);
                    VisualRowItemViewHolder.this.updateNonProgressViews();
                    return;
                }
                if (bookmark.getId().equals(VisualRowItemViewHolder.this.bookmarkElement.getId())) {
                    VisualRowItemViewHolder.this.onBookmarkChanged(bookmark);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBookmarkChanged(final Bookmark bookmark) {
        bindRemainingDurationText(bookmark);
        String strMediaId = Podcast.getPlayback().getMedia().mediaId();
        int playbackState = Podcast.getPlayback().getPlaybackState();
        if (bookmark.getId().equals(strMediaId) && playbackState == 3) {
            return;
        }
        hideEqualizer();
        bindProgress(bookmark);
    }

    private void bindRemainingDurationText(final Bookmark bookmark) {
        Completed completed = Podcast.getAppSync().completed().read(this.context, bookmark.getId());
        long totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
        long progressMilliseconds = totalDurationMilliseconds - bookmark.getProgressMilliseconds();
        if (completed != null) {
            this.tertiaryFollowingText.setText(Strings.getTextWithTotalDuration(this.resources, Long.valueOf(totalDurationMilliseconds), this.tertiaryText));
            this.tertiaryFollowingText.setVisibility(0);
        } else {
            if (progressMilliseconds <= TimeUnit.SECONDS.toMillis(1L)) {
                this.tertiaryFollowingText.setText(Strings.getTextWithTotalDuration(this.resources, Long.valueOf(totalDurationMilliseconds), this.tertiaryText));
            } else {
                this.tertiaryFollowingText.setText(Strings.getTextWithRemainingDuration(this.resources, Long.valueOf(progressMilliseconds), this.tertiaryText));
            }
            this.tertiaryFollowingText.setVisibility(0);
        }
    }

    private void bindCompleted(final Completed completed, long totalDurationMilliseconds, long progressMilliseconds) {
        Playback playback = Podcast.getPlayback();
        long progressMilliseconds2 = PlaybackCache.get(completed.getId()).getProgressMilliseconds();
        if (playback.isEpisodePaused(completed.getId()) || progressMilliseconds2 > 0) {
            this.progressBar.setMax((int) totalDurationMilliseconds);
            this.progressBar.setProgress((int) progressMilliseconds);
            updateInProgressViews();
        } else {
            if (!playback.isEpisodePlaying(completed.getId())) {
                if (isEpisodeCompleted(completed.getId())) {
                    int i = (int) totalDurationMilliseconds;
                    this.progressBar.setMax(i);
                    this.progressBar.setProgress(i);
                    updateInProgressViews();
                    return;
                }
                updateNonProgressViews();
                return;
            }
            this.imageOverlay.setVisibility(0);
            this.smallIcon.setVisibility(4);
            this.centerLargeIcon.setVisibility(4);
            this.progressBar.setVisibility(4);
        }
    }

    private void bindProgress(final Bookmark bookmark) {
        Completed completed = Podcast.getAppSync().completed().read(this.context, bookmark.getId());
        long totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
        long progressMilliseconds = bookmark.getProgressMilliseconds();
        long j = totalDurationMilliseconds - progressMilliseconds;
        if (completed != null) {
            bindCompleted(completed, totalDurationMilliseconds, progressMilliseconds);
            return;
        }
        if (j <= TimeUnit.SECONDS.toMillis(1L)) {
            int i = (int) totalDurationMilliseconds;
            this.progressBar.setMax(i);
            this.progressBar.setProgress(i);
        } else {
            this.progressBar.setMax((int) totalDurationMilliseconds);
            this.progressBar.setProgress((int) progressMilliseconds);
        }
        updateInProgressViews();
    }

    private boolean isEpisodeCompleted(final String id) {
        Completed completed = Podcast.getAppSync().completed().read(this.context, id);
        return completed != null && completed.getCompleted();
    }

    private void updateInProgressViews() {
        this.progressBar.setVisibility(0);
        this.imageOverlay.setVisibility(0);
        this.smallIcon.setVisibility(4);
        this.centerLargeIcon.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNonProgressViews() {
        this.progressBar.setVisibility(4);
        this.imageOverlay.setVisibility(4);
        this.smallIcon.setVisibility(0);
        this.centerLargeIcon.setVisibility(4);
    }
}
