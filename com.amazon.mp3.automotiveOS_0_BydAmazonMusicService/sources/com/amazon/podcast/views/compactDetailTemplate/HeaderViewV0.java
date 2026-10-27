package com.amazon.podcast.views.compactDetailTemplate;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.NegativeButtonElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.DownloadElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.UnborderedBadgeElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import UpsellInterface.v1_0.PlaybackMode;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.podcast.AutoSizeTextUtil;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.downloads.DeleteDownloadMethod;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadProgress;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.downloads.Downloads;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.onboarding.ToolTipHandler;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.sharing.SharingManager;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.DownloadButtonMedium;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.LargeUpsellButton;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.ToolTipView;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import com.amazonaws.util.StringUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class HeaderViewV0 extends LinearLayout implements Playback.StateCallback, Downloads.EventsListener {
    private static final Logger logger = LoggerFactory.getLogger("HeaderView");
    private BookmarkElement bookmarkElement;
    private Download download;
    private DownloadButtonMedium downloadButton;
    private DownloadElement downloadElement;
    private ToastView downloadToastView;
    private final Downloads downloads;
    private RelativeLayout headerButtonsView;
    private HeaderElement headerElement;
    private ImageView image;
    private boolean isSaved;
    private EmberTextView label;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private ImageButton overflowButton;
    private String ownerId;
    private ImageButton playButton;
    private Playback playback;
    private EmberTextView primaryText;
    private ProgressBar progressBar;
    private ImageButton saveButton;
    private SaveElement saveElement;
    private ToastView savePodcastToastView;
    private EmberTextView secondaryText;
    private View secondaryTextTouchView;
    private ImageButton shareButton;
    private TemplateContext templateContext;
    private EmberTextView tertiaryFollowingText;
    private EmberTextView tertiaryText;
    private ToolTipView toolTipView;
    private AppCompatButton upsellBadge;
    private LargeUpsellButton upsellButton;
    private EmberTextView upsellTextView;

    public HeaderViewV0(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.downloads = Podcast.getDownloads();
        init();
    }

    public boolean isHeaderImageViewOnScreen() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect();
        Rect rect2 = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.image.getGlobalVisibleRect(rect);
        return rect.intersect(rect2);
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public void setMethodsDispatcher(final MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }

    public void setLifeCycleOwner(final LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void setTemplateContext(final TemplateContext templateContext) {
        this.templateContext = templateContext;
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        bindPlaybackState(state);
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

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            this.playback.addStateCallback(this);
            bindPlaybackState(this.playback.getPlaybackState());
            if (this.downloadElement == null) {
                return;
            }
            bindDownload(this.download);
            return;
        }
        this.playback.removeStateCallback(this);
        if (this.download != null) {
            Podcast.getDownloads().removeEventsListener(this.download.getId(), this);
        }
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_compact_detail_template_header_view_v0, this);
        this.toolTipView = new ToolTipView(getContext(), ToolTipView.ToolTipOrientation.BOTTOM, false, false);
        this.savePodcastToastView = new ToastView(getContext(), ToastView.ToastViewType.PLAYLIST, "ADD_PODCAST_NAVIGATION_TOAST_FLAG");
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.compact_detail_template_header_label);
        this.label = emberTextView;
        emberTextView.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        EmberTextView emberTextView2 = (EmberTextView) findViewById(R$id.compact_detail_template_header_primary_text);
        this.primaryText = emberTextView2;
        emberTextView2.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline3TypeFace());
        this.secondaryText = (EmberTextView) findViewById(R$id.compact_detail_template_header_secondary_text);
        this.secondaryTextTouchView = findViewById(R$id.podcast_compact_detail_template_header_secondary_text_touch_target);
        this.tertiaryText = (EmberTextView) findViewById(R$id.compact_detail_template_header_tertiary_text);
        this.image = (ImageView) findViewById(R$id.compact_detail_template_header_image);
        this.shareButton = (ImageButton) findViewById(R$id.podcast_share_button);
        this.playButton = (ImageButton) findViewById(R$id.compact_detail_template_header_view_play_button);
        this.downloadButton = (DownloadButtonMedium) findViewById(R$id.compact_detail_template_header_view_download_button);
        this.overflowButton = (ImageButton) findViewById(R$id.podcast_compact_detail_template_header_view_overflow_button);
        this.saveButton = (ImageButton) findViewById(R$id.podcast_compact_detail_template_header_view_save_button);
        this.progressBar = (ProgressBar) findViewById(R$id.compact_detail_template_header_view_progress_bar);
        this.tertiaryFollowingText = (EmberTextView) findViewById(R$id.compact_detail_template_header_tertiary_following_text);
        AppCompatButton appCompatButton = (AppCompatButton) findViewById(R$id.podcast_compact_detail_template_header_badge);
        this.upsellBadge = appCompatButton;
        appCompatButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), RuntimeStyleSheet.FONT_AMAZON_EMBER_BOLD_PATH));
        this.upsellTextView = (EmberTextView) findViewById(R$id.podcast_compact_detail_template_header_upsell_text);
        this.upsellButton = (LargeUpsellButton) findViewById(R$id.podcast_compact_detail_template_header_upsell_button);
        this.headerButtonsView = (RelativeLayout) findViewById(R$id.header_buttons);
        Playback playback = Podcast.getPlayback();
        this.playback = playback;
        playback.addStateCallback(this);
        this.downloadToastView = new ToastView(getContext(), ToastView.ToastViewType.DOWNLOAD, "DOWNLOAD_PODCAST_NAVIGATION_TOAST_FLAG");
    }

    public void bind(final HeaderElement element) {
        this.headerElement = element;
        this.label.setText(element.getLabel());
        this.primaryText.setText(WordUtils.capitalize(this.headerElement.getPrimaryText()));
        AutoSizeTextUtil.continuousAutoSizeTextType(getResources().getDimensionPixelSize(R$dimen.podcast_text_size_h4), getResources().getDimensionPixelSize(R$dimen.podcast_text_size_h3), getResources().getDimensionPixelSize(R$dimen.refresh_line_height_h4), getResources().getDimensionPixelSize(R$dimen.refresh_line_height_h3), this.primaryText);
        if (this.headerElement.getSecondaryText() != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.headerElement.getSecondaryText());
            spannableStringBuilder.setSpan(new UnderlineSpan(), 0, this.headerElement.getSecondaryText().length(), 18);
            this.secondaryText.setText(spannableStringBuilder);
        }
        this.secondaryTextTouchView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeaderViewV0.this.methodsDispatcher.dispatch(HeaderViewV0.this.ownerId, element.getOnSecondaryTextSelected());
            }
        });
        if (this.headerElement.getTertiaryText() != null) {
            this.tertiaryText.setText(this.headerElement.getTertiaryText());
            this.tertiaryText.setVisibility(0);
        }
        bindRectangleImage(getResources(), this.image, this.headerElement.getImage());
    }

    public void bindRedhoodPlusElements(final String upsellText, final ButtonElement buttonElement, final BadgeElement badgeElement) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.headerButtonsView.getLayoutParams();
        if (!StringUtils.isBlank(upsellText)) {
            this.upsellTextView.setText(upsellText);
            this.upsellTextView.setVisibility(0);
            if (buttonElement == null) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.upsellTextView.getLayoutParams();
                layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_large);
                this.upsellTextView.setLayoutParams(layoutParams2);
                layoutParams.bottomMargin = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_base);
            }
        }
        if (badgeElement != null) {
            this.upsellBadge.setText(badgeElement.getText());
            this.upsellBadge.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HeaderViewV0.this.methodsDispatcher.dispatch(HeaderViewV0.this.ownerId, badgeElement.getOnItemSelected());
                }
            });
            this.upsellBadge.setVisibility(0);
        }
        if (badgeElement instanceof BorderedBadgeElement) {
            this.upsellBadge.setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_bordered_badge_background));
            layoutParams.topMargin = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_base);
            this.upsellBadge.setTextColor(getResources().getColor(R$color.primary_glass_5));
        } else if (badgeElement instanceof UnborderedBadgeElement) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.upsellBadge.getLayoutParams();
            layoutParams3.topMargin = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_small);
            this.upsellBadge.setLayoutParams(layoutParams3);
            this.upsellBadge.setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_unbordered_badge_background));
            this.upsellBadge.setTextColor(getResources().getColor(R$color.primary_glass_5));
        } else if (badgeElement instanceof LightUnborderedBadgeElement) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.upsellBadge.getLayoutParams();
            layoutParams4.topMargin = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_small);
            this.upsellBadge.setLayoutParams(layoutParams4);
            this.upsellBadge.setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_regular_badge_background));
            this.upsellBadge.setTextColor(getResources().getColor(R$color.secondary));
        } else {
            layoutParams.topMargin = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_base);
        }
        this.headerButtonsView.setLayoutParams(layoutParams);
        if (buttonElement != null) {
            this.upsellButton.bindUpsellButtonText(buttonElement.getText());
            this.upsellButton.setUpsellButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HeaderViewV0.this.methodsDispatcher.dispatch(HeaderViewV0.this.ownerId, buttonElement.getOnItemSelected());
                }
            });
            this.upsellButton.setVisibility(0);
        }
    }

    public void bindBookmark(final BookmarkElement element, final String playbackMode) {
        if (PlaybackMode.NONE.equals(playbackMode)) {
            this.playButton.setVisibility(8);
            return;
        }
        this.bookmarkElement = element;
        if (element != null && element.getTotalDurationMilliseconds() != null) {
            this.tertiaryFollowingText.setText(Strings.getTextWithTotalDuration(getResources(), this.bookmarkElement.getTotalDurationMilliseconds(), this.tertiaryText));
        }
        this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeaderViewV0.this.handlePlayClick();
            }
        });
        bindPlaybackState(this.playback.getPlaybackState());
        bindProgress();
        this.tertiaryFollowingText.setVisibility(0);
        this.playButton.setVisibility(0);
    }

    public void bindDownloadElement(final DownloadElement element) {
        if (element == null) {
            return;
        }
        this.downloadElement = element;
        logger.debug("Binding download element");
        Podcast.getAppSync().download().readLive(getContext(), element.getId()).observe(this.lifecycleOwner, new Observer<Download>() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.5
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Download download) {
                HeaderViewV0.this.bindDownload(download);
            }
        });
        this.downloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeaderViewV0.this.downloadClick();
            }
        });
        this.downloadButton.setVisibility(0);
    }

    public void bindShareElement(final ShareElement shareElement) {
        boolean zIsEnabled = PodcastFeatureGating.SHARING.isEnabled();
        if (shareElement == null || !zIsEnabled) {
            return;
        }
        this.shareButton.setContentDescription(getResources().getString(R$string.podcast_share_show_prompt));
        this.shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharingManager.handleCompactDetailTemplateShareClick(HeaderViewV0.this.getContext(), shareElement);
            }
        });
        this.shareButton.setVisibility(0);
    }

    public void bindSave(final SaveElement element) {
        if (element == null) {
            return;
        }
        this.saveElement = element;
        Podcast.getAppSync().save().readLive(getContext(), this.saveElement.getId()).observe(this.lifecycleOwner, new Observer<Save>() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.8
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Save save) {
                HeaderViewV0.this.isSaved = save == null ? false : save.isSaved();
                if (HeaderViewV0.this.isSaved) {
                    HeaderViewV0.this.saveButton.setImageResource(R$drawable.ic_action_done);
                    HeaderViewV0.this.saveButton.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
                    HeaderViewV0.this.saveButton.setContentDescription(HeaderViewV0.this.getResources().getString(R$string.podcast_unSaved_label));
                } else {
                    HeaderViewV0.this.saveButton.setImageResource(R$drawable.ic_action_add);
                    HeaderViewV0.this.saveButton.setColorFilter(HeaderViewV0.this.getResources().getColor(R$color.primary));
                    HeaderViewV0.this.saveButton.setContentDescription(HeaderViewV0.this.getResources().getString(R$string.podcast_saved_label));
                }
            }
        });
        this.saveButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeaderViewV0.this.handleSaveClick();
            }
        });
        this.saveButton.setVisibility(0);
        showOnboardingTip();
    }

    public void bindContextMenuButton(final EpisodeOptionsElement episodeOptionsElement) {
        if (episodeOptionsElement == null) {
            return;
        }
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.10
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                ContextMenus.show(episodeOptionsElement, HeaderViewV0.this.getContext(), HeaderViewV0.this.getResources(), HeaderViewV0.this.methodsDispatcher, HeaderViewV0.this.ownerId, HeaderViewV0.this.templateContext.getLifecycleOwner(), HeaderViewV0.this.templateContext.getFragmentManager(), HeaderViewV0.this.overflowButton, "HeaderView", UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL);
            }
        });
        this.overflowButton.setVisibility(0);
        this.overflowButton.setContentDescription(getResources().getString(R$string.podcast_overflow_button));
    }

    private void showOnboardingTip() {
        if (!ToolTipHandler.checkIfFlagNotExists(getContext(), "SAVE_PODCAST_EPISODE_TOOLTIP_FLAG").booleanValue() || this.isSaved) {
            return;
        }
        this.toolTipView.dismissToolTip();
        this.toolTipView.bind(getResources().getString(R$string.podcast_saved_label));
        if (this.toolTipView.showToolTipForView(this.saveButton)) {
            ToolTipHandler.insertFlag(getContext(), "SAVE_PODCAST_EPISODE_TOOLTIP_FLAG");
        }
    }

    private void bindRectangleImage(final Resources resources, final ImageView imageView, final String url) {
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_detail_template_header_image_dimension);
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_detail_template_header_image_rounded_corner));
        if (url.isEmpty()) {
            Picasso.get().load(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(roundedCornersTransformation).resize(dimensionPixelSize, dimensionPixelSize).into(imageView);
        } else {
            Picasso.get().load(url).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(roundedCornersTransformation).resize(dimensionPixelSize, dimensionPixelSize).into(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSaveClick() {
        if (this.isSaved) {
            this.methodsDispatcher.dispatch(this.ownerId, this.saveElement.getOnUnsave());
            return;
        }
        this.savePodcastToastView.dismissToast();
        this.savePodcastToastView.showToast();
        this.methodsDispatcher.dispatch(this.ownerId, this.saveElement.getOnSave());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlayClick() {
        BookmarkElement bookmarkElement = this.bookmarkElement;
        if (bookmarkElement == null || this.methodsDispatcher == null) {
            return;
        }
        String mediaId = (this.playback.getMedia() == null || this.playback.getMedia().getMediaMetadataElement() == null) ? "" : this.playback.getMedia().getMediaMetadataElement().getMediaId();
        if (mediaId != null && mediaId.equals(this.bookmarkElement.getId())) {
            int playbackState = this.playback.getPlaybackState();
            if (playbackState == 2) {
                this.methodsDispatcher.dispatch(this.ownerId, bookmarkElement.getOnResumeSelected());
                return;
            } else if (playbackState == 3) {
                this.methodsDispatcher.dispatch(this.ownerId, bookmarkElement.getOnPauseSelected());
                return;
            } else {
                if (playbackState == 0) {
                    this.methodsDispatcher.dispatch(this.ownerId, bookmarkElement.getOnPlaySelected());
                    return;
                }
                return;
            }
        }
        this.methodsDispatcher.dispatch(this.ownerId, bookmarkElement.getOnPlaySelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindCompleted(final Completed completed, long totalDurationMilliseconds, long progressMilliseconds) {
        String id = completed.getId();
        Playback playback = Podcast.getPlayback();
        long progressMilliseconds2 = PlaybackCache.get(completed.getId()).getProgressMilliseconds();
        if (!playback.isEpisodePlaying(completed.getId())) {
            int i = (int) totalDurationMilliseconds;
            this.progressBar.setMax(i);
            if (isEpisodeCompleted(id)) {
                if (!playback.isEpisodePaused(id) && progressMilliseconds2 <= 0 && !playback.isEpisodePlaying(id)) {
                    this.progressBar.setProgress(i);
                } else {
                    this.progressBar.setProgress((int) progressMilliseconds);
                }
            } else {
                this.progressBar.setProgress(0);
            }
        }
        this.progressBar.setVisibility(0);
        this.tertiaryFollowingText.setText(Strings.getTextWithTotalDuration(getResources(), Long.valueOf(totalDurationMilliseconds), this.tertiaryText));
        this.tertiaryFollowingText.setVisibility(0);
    }

    private void bindProgress() {
        if (this.bookmarkElement == null) {
            return;
        }
        Podcast.getAppSync().bookmark().readLive(getContext(), this.bookmarkElement.getId()).observe(this.lifecycleOwner, new Observer<Bookmark>() { // from class: com.amazon.podcast.views.compactDetailTemplate.HeaderViewV0.11
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Bookmark bookmark) {
                if (bookmark == null) {
                    return;
                }
                Completed completed = Podcast.getAppSync().completed().read(HeaderViewV0.this.getContext(), bookmark.getId());
                long totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
                long progressMilliseconds = bookmark.getProgressMilliseconds();
                long j = totalDurationMilliseconds - progressMilliseconds;
                if (completed != null) {
                    HeaderViewV0.this.bindCompleted(completed, totalDurationMilliseconds, progressMilliseconds);
                    return;
                }
                if (j <= TimeUnit.SECONDS.toMillis(1L)) {
                    int i = (int) totalDurationMilliseconds;
                    HeaderViewV0.this.progressBar.setMax(i);
                    HeaderViewV0.this.progressBar.setProgress(i);
                    HeaderViewV0.this.tertiaryFollowingText.setText(Strings.getTextWithTotalDuration(HeaderViewV0.this.getResources(), Long.valueOf(totalDurationMilliseconds), HeaderViewV0.this.tertiaryText));
                } else {
                    HeaderViewV0.this.progressBar.setMax((int) totalDurationMilliseconds);
                    HeaderViewV0.this.progressBar.setProgress((int) progressMilliseconds);
                    HeaderViewV0.this.tertiaryFollowingText.setText(Strings.getTextWithRemainingDuration(HeaderViewV0.this.getResources(), Long.valueOf(j), HeaderViewV0.this.tertiaryText));
                }
                HeaderViewV0.this.progressBar.setVisibility(0);
            }
        });
    }

    private void bindPlaybackState(int state) {
        MediaMetadataElement mediaMetadataElement = this.playback.getMedia().getMediaMetadataElement();
        String string = getResources().getString(R$string.podcast_play_label);
        String mediaId = mediaMetadataElement == null ? "" : mediaMetadataElement.getMediaId();
        BookmarkElement bookmarkElement = this.bookmarkElement;
        if (bookmarkElement == null || !mediaId.equals(bookmarkElement.getId())) {
            this.playButton.setImageResource(R$drawable.ic_play);
            this.progressBar.setVisibility(0);
            this.playButton.setContentDescription(string);
        } else {
            if (state == 3) {
                String string2 = getResources().getString(R$string.podcast_pause_label);
                this.playButton.setImageResource(R$drawable.ic_playback_pause);
                this.progressBar.setVisibility(4);
                this.playButton.setContentDescription(string2);
                return;
            }
            this.playButton.setImageResource(R$drawable.ic_play);
            this.progressBar.setVisibility(0);
            this.playButton.setContentDescription(string);
        }
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
                this.downloadButton.setContentDescription(getResources().getString(R$string.podcast_download_label));
                return;
            }
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        if (DownloadState.QUEUED == downloadStateLookup) {
            this.downloadButton.bindQueued();
            downloads.addEventsListener(download.getId(), this);
        } else if (DownloadState.IN_PROGRESS == downloadStateLookup) {
            DownloadProgress progress = downloads.getProgress(download.getId());
            this.downloadButton.bindInProgress(progress.getProgress(), progress.getMaxProgress());
            downloads.addEventsListener(download.getId(), this);
        } else if (DownloadState.DOWNLOADED == downloadStateLookup) {
            this.downloadButton.bindDownloaded();
            downloads.removeEventsListener(download.getId(), this);
            this.downloadButton.setContentDescription(getResources().getString(R$string.podcast_unDownload_label));
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
                arrayList.addAll(deleteDownloadMethods(this.download.getId()));
                arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, UiMetricAttributes$ActionType.REMOVE_DOWNLOAD_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), this.download.getId(), this.download.getPodcastEpisodeVariantId()), (Integer) null, UiMetricAttributes$ContentName.PODCAST_EPISODE_DETAIL));
                this.methodsDispatcher.dispatch(this.ownerId, arrayList);
                return;
            }
            if (DownloadState.QUEUED == downloadStateLookup) {
                downloads.cancel(this.download.getId());
            }
        }
    }

    private List<Method> deleteDownloadMethods(final String id) {
        PositiveButtonElement positiveButtonElementBuild = PositiveButtonElement.builder().withText(getResources().getString(R$string.podcast_alert_yes)).withOnItemSelected(Collections.singletonList(new DeleteDownloadMethod(id, Queues.downloadSync()))).build();
        return Collections.singletonList(CreateAndBindTemplateMethod.builder().withTemplate(AlertTemplate.builder().withImage(String.valueOf(R$drawable.ic_action_delete)).withTitle(getResources().getString(R$string.podcast_remove_download_title)).withSubtitle(getResources().getString(R$string.podcast_remove_download_subtitle)).withPositiveButton(positiveButtonElementBuild).withNegativeButton(NegativeButtonElement.builder().withText(getResources().getString(R$string.podcast_alert_no)).withOnItemSelected(Collections.emptyList()).build()).build()).withQueue(Queues.template()).build());
    }

    private boolean isEpisodeCompleted(final String id) {
        Completed completed = Podcast.getAppSync().completed().read(getContext(), id);
        return completed != null && completed.getCompleted();
    }
}
