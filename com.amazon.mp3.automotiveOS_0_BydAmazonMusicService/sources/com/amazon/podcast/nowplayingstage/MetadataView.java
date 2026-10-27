package com.amazon.podcast.nowplayingstage;

import Podcast.PlaybackInterface.v1_0.MediaControlsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.podcast.NavigationHandler;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$anim;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.FadingEdgeTextView;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class MetadataView extends RelativeLayout implements Playback.PositionCallback, Playback.BufferedPositionCallback, Playback.DurationCallback, Media.MediaMetadataCallback, Media.MediaControlsCallback, Playback.TranscriptionStatusUpdateCallback, Playback.StateCallback, Playback.PlaybackFinishedCallback {
    private EmberTextView aboutOverlayButton;
    private ImageView artwork;
    private RelativeLayout artworkContainer;
    private int artworkSize;
    private int containerHeight;
    private EmberTextView durationText;
    private RelativeLayout expandedMetadata;
    private ImageButton expandedPlayPause;
    private EmberTextView expandedSubtitle;
    private FadingEdgeTextView expandedTitle;
    private GestureDetector gestureDetector;
    private boolean isDraggingSeekbar;
    private boolean isExpanded;
    private boolean isSeekbarTracking;
    private boolean isSeeking;
    private boolean isSequentialDownUp;
    private LinearLayout overlayButtonLayout;
    private FrameLayout overlayContainer;
    private String ownerId;
    private Playback playback;
    private int playbackState;
    private EmberTextView progressText;
    private RuntimeStyleSheet runtimeStyleSheet;
    private SeekBar seekBar;
    private EmberTextView subtitle;
    private FadingEdgeTextView title;
    private EmberTextView transcriptOverlayButton;
    private static final Logger logger = LoggerFactory.getLogger("MetadataView");
    private static int COLUMNS_XS_AND_LG_PLUS_SIZES = 6;
    private static int COLUMNS_SM_TO_MD_SIZES = 10;

    public MetadataView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.isSeeking = false;
        this.isDraggingSeekbar = false;
        this.isSequentialDownUp = false;
        this.isSeekbarTracking = false;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_now_playing_stage_metadata, this);
        Playback playback = Podcast.getPlayback();
        this.playback = playback;
        playback.addStateCallback(this);
        this.playback.addPositionCallback(this);
        this.playback.addBufferedPositionCallback(this);
        this.playback.addDurationCallback(this);
        this.playback.addTranscriptUpdatedCallback(this);
        this.playback.addPlaybackFinishedCallback(this);
        this.playback.getMedia().addMediaControlsCallback(this);
        this.playbackState = this.playback.getPlaybackState();
        if (Podcast.getRuntimeStyleSheet() == null) {
            Podcast.initializeRuntimeStyleSheet(getContext());
        }
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.artworkContainer = (RelativeLayout) findViewById(R$id.podcast_art_container);
        this.artwork = (ImageView) findViewById(R$id.podcast_art);
        this.overlayContainer = (FrameLayout) findViewById(R$id.overlay_container);
        this.expandedMetadata = (RelativeLayout) findViewById(R$id.expanded_metadata_controls);
        this.expandedPlayPause = (ImageButton) findViewById(R$id.expanded_playpause);
        this.gestureDetector = new GestureDetector(getContext(), new MetadataArtworkGestureListener());
        this.playback.getMedia().addMediaMetadataCallback(this);
        this.progressText = (EmberTextView) findViewById(R$id.podcast_progress_text);
        this.durationText = (EmberTextView) findViewById(R$id.podcast_duration_text);
        this.overlayButtonLayout = (LinearLayout) findViewById(R$id.overlay_button_layout);
        this.aboutOverlayButton = (EmberTextView) findViewById(R$id.about_overlay_button);
        this.transcriptOverlayButton = (EmberTextView) findViewById(R$id.transcript_overlay_button);
        if (this.playback.isHasFetchedTranscript()) {
            this.transcriptOverlayButton.setVisibility(0);
        }
        this.title = (FadingEdgeTextView) findViewById(R$id.podcast_now_playing_title);
        this.expandedTitle = (FadingEdgeTextView) findViewById(R$id.expanded_title);
        this.subtitle = (EmberTextView) findViewById(R$id.podcast_now_playing_subtitle);
        this.expandedSubtitle = (EmberTextView) findViewById(R$id.expanded_subtitle);
        SeekBar seekBar = (SeekBar) findViewById(R$id.podcast_now_playing_seekbar);
        this.seekBar = seekBar;
        seekBar.setPadding(5, 25, 5, 16);
        bindTranscriptIngressPane();
        bind(this.playback.getMedia().getMediaMetadataElement());
        addSeekBarListener();
        addSeekBarTouchListener();
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            this.title.setTypeface(runtimeStyleSheet.getHeadline4TypeFace());
            this.expandedTitle.setTypeface(this.runtimeStyleSheet.getHeadline4TypeFace());
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MetadataView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int iMin = Math.min(MetadataView.this.getWidth(), MetadataView.this.getResources().getDimensionPixelSize(R$dimen.podcast_artwork_max_size));
                int heightPixels = MetadataView.this.getHeightPixels();
                MetadataView metadataView = MetadataView.this;
                metadataView.containerHeight = heightPixels - metadataView.getMetadataMinHeight();
                int iMin2 = Math.min(iMin, MetadataView.this.containerHeight);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MetadataView.this.artworkContainer.getLayoutParams();
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MetadataView.this.overlayContainer.getLayoutParams();
                layoutParams.height = MetadataView.this.containerHeight;
                layoutParams2.height = MetadataView.this.containerHeight;
                MetadataView.this.overlayContainer.setLayoutParams(layoutParams2);
                MetadataView.this.artworkContainer.setLayoutParams(layoutParams);
                int iMin3 = Math.min(MetadataView.this.getWidth(), MetadataView.this.getArtworkSize());
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) MetadataView.this.artwork.getLayoutParams();
                MetadataView.this.artworkSize = iMin3;
                layoutParams3.height = iMin3;
                layoutParams3.width = iMin3;
                MetadataView.this.artwork.setLayoutParams(layoutParams3);
                if (iMin3 >= iMin2) {
                    int iMin4 = Math.min(iMin3, MetadataView.this.getMultiColumnWidth(MetadataView.COLUMNS_XS_AND_LG_PLUS_SIZES));
                    layoutParams3.height = iMin4;
                    layoutParams3.width = iMin4;
                    MetadataView.this.artworkSize = iMin4;
                    MetadataView.this.artwork.setLayoutParams(layoutParams3);
                }
                MetadataView metadataView2 = MetadataView.this;
                metadataView2.bind(metadataView2.playback.getMedia().getMediaMetadataElement());
            }
        });
        this.artwork.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return MetadataView.this.gestureDetector.onTouchEvent(motionEvent);
            }
        });
        bind(this.playback.getMedia().getMediaControlsElement());
    }

    public void setIsSeeking(final boolean isSeeking) {
        this.isSeeking = isSeeking;
    }

    public void addSeekBarListener() {
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.3
            private int prevProgress;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                if (!fromUser || seekBar.getMax() == 0) {
                    return;
                }
                long j = progress;
                String time = MetadataView.this.formatTime(new Long(j));
                seekBar.setMax((int) MetadataView.this.playback.getPlaybackDuration());
                String time2 = MetadataView.this.formatTime(new Long(seekBar.getMax() - progress));
                MetadataView.this.progressText.setText(time);
                MetadataView.this.durationText.setText(String.format("%s", time2));
                ((NowPlayingActivity) MetadataView.this.getContext()).setPreviewTranscriptIndex(j);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(final SeekBar seekBar) {
                MetadataView.this.playback.setSeekBarTracking(true);
                MetadataView.this.isSeekbarTracking = true;
                if (MetadataView.this.isDraggingSeekbar) {
                    this.prevProgress = seekBar.getProgress();
                    MetadataView metadataView = MetadataView.this;
                    Resources resources = metadataView.getContext().getResources();
                    int i = R$dimen.podcast_medium_icon;
                    metadataView.setSeekbarThumbSize(seekBar, (int) resources.getDimension(i), (int) MetadataView.this.getContext().getResources().getDimension(i));
                    if (MetadataView.this.playback.isHasFetchedTranscript()) {
                        if (!MetadataView.this.isSequentialDownUp) {
                            ((NowPlayingActivity) MetadataView.this.getContext()).setPreviewTranscriptVisible(true);
                        }
                        MetadataView.this.playback.dispatch(MetadataView.this.ownerId, UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ContainerType.NOW_PLAYING, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(final SeekBar seekBar) {
                MetadataView.this.playback.setSeekBarTracking(false);
                MetadataView.this.isSeekbarTracking = false;
                ((NowPlayingActivity) MetadataView.this.getContext()).closePreviewTranscript();
                MetadataView metadataView = MetadataView.this;
                Resources resources = metadataView.getContext().getResources();
                int i = R$dimen.podcast_tiny_icon;
                metadataView.setSeekbarThumbSize(seekBar, (int) resources.getDimension(i), (int) MetadataView.this.getContext().getResources().getDimension(i));
                if (MetadataView.this.playback.isHasFetchedTranscript()) {
                    ((NowPlayingActivity) MetadataView.this.getContext()).setPreviewTranscriptVisible(false);
                }
                if (MetadataView.this.isSeeking) {
                    MetadataView.this.isSeeking = false;
                } else {
                    seekBar.setProgress(this.prevProgress);
                }
                MetadataView.this.playback.seekTo(seekBar.getProgress());
            }
        });
    }

    private void addSeekBarTouchListener() {
        this.seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.4
            private float initialY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == 0) {
                    MetadataView.this.isSequentialDownUp = false;
                    this.initialY = event.getY();
                    if (MetadataView.this.isWithinDrawableThumb(event)) {
                        MetadataView.this.isDraggingSeekbar = true;
                    } else {
                        MetadataView.this.isDraggingSeekbar = false;
                        return true;
                    }
                } else if (action == 1) {
                    MetadataView.this.isSequentialDownUp = true;
                    MetadataView.this.isSeekbarTracking = false;
                    ((NowPlayingActivity) MetadataView.this.getContext()).closePreviewTranscript();
                    MetadataView.this.setIsSeeking(this.initialY - event.getY() < 120.0f);
                }
                return false;
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        bind(metadataElement);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(final long timeInMillis) {
        if (showSyncTranscriptsIngressPane()) {
            this.playback.setTranscriptSyncing(true);
            this.playback.setCurrentClockTime(0);
        }
        if (this.playback.isSeekBarTracking()) {
            return;
        }
        this.seekBar.setProgress((int) timeInMillis);
        this.seekBar.setMax((int) this.playback.getPlaybackDuration());
        this.progressText.setText(formatTime(Long.valueOf(timeInMillis)));
        this.durationText.setText(String.format("%s", formatTime(Long.valueOf(((long) this.seekBar.getMax()) - timeInMillis))));
    }

    @Override // com.amazon.podcast.media.playback.Playback.BufferedPositionCallback
    public void onBufferedPositionChange(final long bufferedPosition) {
        this.seekBar.setSecondaryProgress((int) bufferedPosition);
    }

    @Override // com.amazon.podcast.media.playback.Playback.DurationCallback
    public void onPlaybackDurationChange(final long duration) {
        if (duration <= 0) {
            return;
        }
        this.seekBar.setMax((int) duration);
        long playbackPosition = duration - this.playback.getPlaybackPosition();
        this.seekBar.setProgress((int) this.playback.getPlaybackPosition());
        this.durationText.setText(String.format("%s", formatTime(Long.valueOf(playbackPosition))));
    }

    public boolean getIsSeekbarTracking() {
        return this.isSeekbarTracking;
    }

    private void bind(final MediaControlsElement mediaControlsElement) {
        if (mediaControlsElement != null) {
            onPlaybackStateChange(this.playback.getPlaybackState());
            this.expandedPlayPause.setContentDescription(getResources().getString(R$string.podcast_pause_label));
            this.expandedPlayPause.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MetadataView.this.playback.playPause();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bind(final MediaMetadataElement metadataElement) {
        if (!this.title.isSelected()) {
            this.title.setSelected(true);
        }
        if (!this.expandedTitle.isSelected()) {
            this.expandedTitle.setSelected(true);
        }
        if (metadataElement == null) {
            this.title.setText("");
            this.expandedTitle.setText("");
            this.subtitle.setText("");
            this.expandedSubtitle.setText("");
            this.seekBar.setMax(100);
            this.seekBar.setProgress(0);
            Picasso.get().load(R$drawable.artwork_placeholder).into(this.artwork);
            return;
        }
        this.title.setText(metadataElement.getTitle());
        this.expandedTitle.setText(metadataElement.getTitle());
        this.subtitle.setText(metadataElement.getSubtitle());
        this.subtitle.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bind$0(metadataElement, view);
            }
        });
        this.expandedSubtitle.setText(metadataElement.getSubtitle());
        this.expandedSubtitle.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.nowplayingstage.MetadataView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bind$1(metadataElement, view);
            }
        });
        Long lValueOf = Long.valueOf(TimeUnit.SECONDS.toMillis(metadataElement.getDurationSeconds().longValue()));
        Long lValueOf2 = Long.valueOf(lValueOf.longValue() - this.playback.getPlaybackPosition());
        this.seekBar.setMax(lValueOf.intValue());
        this.durationText.setText(String.format("%s", formatTime(lValueOf2)));
        this.seekBar.setProgress((int) this.playback.getPlaybackPosition());
        this.progressText.setText(formatTime(Long.valueOf(this.playback.getPlaybackPosition())));
        bindPosition(this.playback.getPlaybackPosition());
        int width = getWidth();
        if (width == 0) {
            return;
        }
        RequestCreator requestCreatorResize = Picasso.get().load(metadataElement.getArtwork()).centerCrop().resize(width, width);
        int i = R$drawable.ic_empty_state_image;
        requestCreatorResize.placeholder(i).error(i).transform(new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_corner_radius_large))).into(this.artwork);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0(MediaMetadataElement mediaMetadataElement, View view) {
        new NavigationHandler(getContext()).navigateToDeeplink(Deeplinks.podcast(mediaMetadataElement.getMediaCollectionId(), mediaMetadataElement.getTitle()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$1(MediaMetadataElement mediaMetadataElement, View view) {
        new NavigationHandler(getContext()).navigateToDeeplink(Deeplinks.podcast(mediaMetadataElement.getMediaCollectionId(), mediaMetadataElement.getTitle()));
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            this.playback.setCurrentClockTime((int) SystemClock.uptimeMillis());
            Media media = this.playback.getMedia();
            bind(media.getMediaMetadataElement());
            bind(media.getMediaControlsElement());
            this.playback.addPositionCallback(this);
            this.playback.addBufferedPositionCallback(this);
            this.playback.addDurationCallback(this);
            this.playback.addTranscriptUpdatedCallback(this);
            this.playback.addPlaybackFinishedCallback(this);
            media.addMediaMetadataCallback(this);
            return;
        }
        this.playback.removePositionCallback(this);
        this.playback.removeBufferedPositionCallback(this);
        this.playback.removeDurationCallback(this);
        this.playback.removePlaybackFinishedCallback(this);
        this.playback.getMedia().removeMediaMetadataCallback(this);
        this.playback.removeTranscriptUpdatedCallback(this);
    }

    private void bindPosition(final long timeInMillis) {
        if (this.isSeeking) {
            return;
        }
        this.seekBar.setProgress((int) timeInMillis);
        this.progressText.setText(formatTime(Long.valueOf(timeInMillis)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatTime(final Long timeInMillis) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(timeInMillis.longValue());
        long jLongValue = timeInMillis.longValue();
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(jLongValue - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((timeInMillis.longValue() - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        if (hours > 0) {
            return String.format("%d:%02d:%02d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds));
        }
        if (seconds < 0) {
            return String.format("-%d:%02d", Long.valueOf(minutes), Long.valueOf(Math.abs(seconds)));
        }
        return String.format("%d:%02d", Long.valueOf(minutes), Long.valueOf(seconds));
    }

    public void bindTranscriptIngressPane() {
        if (this.playback.isHasFetchedTranscript()) {
            this.playback.dispatch(this.ownerId, UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ContainerType.NOW_PLAYING, UiMetricAttributes$ContentName.PODCASTS_SCRUB_INGRESS));
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptUpdated(List<ScrubSegment> scrubSegments) {
        logger.debug("onTranscriptUpdated");
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoading(final TranscriptLoadingType transcriptLoadingType) {
        logger.debug("onTranscriptLoading");
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoaded() {
        logger.debug("onTranscriptLoaded");
    }

    public void setAboutOverlayButtonClickedListener(final View.OnClickListener listener) {
        this.aboutOverlayButton.setOnClickListener(listener);
    }

    public void setTranscriptOverlayButtonClickedListener(final View.OnClickListener listener) {
        this.transcriptOverlayButton.setOnClickListener(listener);
    }

    public void setAboutStyleOnClicked(final boolean isActive) {
        if (isActive) {
            setActive(this.aboutOverlayButton);
            setInactive(this.transcriptOverlayButton);
        } else {
            setInactive(this.aboutOverlayButton);
        }
    }

    public void setTranscriptStyleOnClicked(final boolean isActive) {
        if (isActive) {
            setActive(this.transcriptOverlayButton);
            setInactive(this.aboutOverlayButton);
        } else {
            setInactive(this.transcriptOverlayButton);
        }
    }

    public void setTranscriptStyleOnScrub(final boolean isScrubbing) {
        if (isScrubbing) {
            setActive(this.transcriptOverlayButton);
        } else {
            setInactive(this.transcriptOverlayButton);
        }
    }

    private void setActive(final EmberTextView view) {
        view.setBackgroundResource(R$drawable.button_background_stage_solid);
        view.setTextColor(getResources().getColor(R$color.secondary));
    }

    private void setInactive(final EmberTextView view) {
        view.setBackgroundResource(R$drawable.button_background_stage);
        view.setTextColor(getResources().getColor(R$color.primary));
    }

    public void showMinimizeArtwork(final boolean show) {
        float dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.podcast_icon_button_small) * 1.0f;
        float top = this.artwork.getTop() + ((this.artworkSize * 1.0f) / 2.0f) + (dimensionPixelSize / 2.0f);
        if (show) {
            this.artwork.setVisibility(0);
            this.artwork.animate().scaleX(1.0f).scaleY(1.0f).translationY(0.0f);
            setAllParentsClip(this.artwork, true);
        } else {
            setAllParentsClip(this.artwork, false);
            float f = dimensionPixelSize / this.artworkSize;
            this.artwork.animate().scaleX(f).scaleY(f).translationY(-top);
        }
    }

    public View getArtwork() {
        return this.artwork;
    }

    public float getRatioForPreview() {
        return (getResources().getDimensionPixelSize(R$dimen.podcast_icon_button_small) * 1.0f) / this.artworkSize;
    }

    public float getTranslateForPreview() {
        return this.artwork.getTop() + ((this.artworkSize * 1.0f) / 2.0f) + ((getResources().getDimensionPixelSize(R$dimen.podcast_icon_button_small) * 1.0f) / 2.0f);
    }

    public void setPartialMode() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.overlayContainer.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.artworkContainer.getLayoutParams();
        this.expandedMetadata.setVisibility(8);
        if (this.isExpanded) {
            collapse(this.overlayContainer, layoutParams.height, this.containerHeight);
        } else {
            layoutParams.height = layoutParams2.height;
            this.overlayContainer.setLayoutParams(layoutParams);
        }
        this.title.setVisibility(0);
        this.subtitle.setVisibility(0);
        if (this.playback.isHasFetchedTranscript()) {
            this.transcriptOverlayButton.setVisibility(0);
        }
        this.aboutOverlayButton.setVisibility(0);
        this.seekBar.setVisibility(0);
        this.progressText.setVisibility(0);
        this.durationText.setVisibility(0);
        this.isExpanded = false;
    }

    public void setExpandedMode(final int height) {
        this.expandedMetadata.setVisibility(0);
        this.transcriptOverlayButton.setVisibility(8);
        this.aboutOverlayButton.setVisibility(8);
        this.title.setVisibility(8);
        this.subtitle.setVisibility(8);
        this.seekBar.setVisibility(8);
        this.progressText.setVisibility(8);
        this.durationText.setVisibility(8);
        expand(this.overlayContainer, this.containerHeight, height);
        this.artwork.setVisibility(0);
        bind(this.playback.getMedia().getMediaControlsElement());
        this.isExpanded = true;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override // com.amazon.podcast.media.playback.Playback.PlaybackFinishedCallback
    public void onPlaybackFinished() {
        logger.debug("onPlaybackFinished");
        this.playback.setTranscriptImplementation(null);
        this.playback.setHasFetchedTranscript(false);
        bindTranscriptIngressPane();
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        if (!this.playback.isHasFetchedTranscript()) {
            this.transcriptOverlayButton.setVisibility(8);
        }
        if (this.expandedPlayPause.getAnimation() != null) {
            this.expandedPlayPause.getAnimation().cancel();
        }
        if (state == 3) {
            this.expandedPlayPause.setContentDescription(getResources().getString(R$string.podcast_pause_label));
            this.expandedPlayPause.setImageResource(R$drawable.ic_playback_pause);
            this.expandedPlayPause.setColorFilter(getResources().getColor(R$color.primary));
        } else if (state == 2 || state == 7 || state == 8) {
            this.expandedPlayPause.setContentDescription(getResources().getString(R$string.podcast_play_label));
            this.expandedPlayPause.setImageResource(R$drawable.ic_playback_play);
            this.expandedPlayPause.setColorFilter(getResources().getColor(R$color.primary));
        } else {
            this.expandedPlayPause.setImageResource(R$drawable.ic_other_loader);
            RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
            if (runtimeStyleSheet != null) {
                this.expandedPlayPause.setColorFilter(runtimeStyleSheet.getAccentColor());
            }
            this.expandedPlayPause.startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.podcast_loading_spinner));
        }
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaControlsCallback
    public void onMediaControlsChange(MediaControlsElement controlsElement) {
        bind(controlsElement);
    }

    private class MetadataArtworkGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        public MetadataArtworkGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 120.0f && Math.abs(velocityX) > 200.0f) {
                MetadataView.this.playback.setTranscriptImplementation(null);
                MetadataView.this.playback.setHasFetchedTranscript(false);
                MetadataView.this.bindTranscriptIngressPane();
                MetadataView.this.transcriptOverlayButton.setVisibility(8);
                MetadataView.this.playback.next();
                return true;
            }
            if (e2.getX() - e1.getX() <= 120.0f || Math.abs(velocityX) <= 200.0f) {
                return true;
            }
            MetadataView.this.playback.setTranscriptImplementation(null);
            MetadataView.this.playback.setHasFetchedTranscript(false);
            MetadataView.this.bindTranscriptIngressPane();
            MetadataView.this.transcriptOverlayButton.setVisibility(8);
            MetadataView.this.playback.previous();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnContextClickListener
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }

    public boolean showSyncTranscriptsIngressPane() {
        return this.playback.getCurrentClockTime() != 0 && SystemClock.uptimeMillis() - ((long) this.playback.getCurrentClockTime()) > CoroutineLiveDataKt.DEFAULT_TIMEOUT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSeekbarThumbSize(final SeekBar seekBar, final int width, final int height) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getContext().getResources(), Bitmap.createScaledBitmap(convertDrawableToBitmap(getContext().getResources().getDrawable(R$drawable.thumb)), width, height, true));
        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        bitmapDrawable.setColorFilter(getContext().getResources().getColor(R$color.podcast_horizontal_progress_bar_progress), PorterDuff.Mode.SRC_ATOP);
        seekBar.setThumb(bitmapDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isWithinDrawableThumb(final MotionEvent event) {
        Rect bounds = this.seekBar.getThumb().getBounds();
        return event.getX() >= ((float) (bounds.left + (-10))) && event.getX() <= ((float) (bounds.right + 10));
    }

    public static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private void expand(final View view, final int prevHeight, final int finalHeight) {
        Animation animation = new Animation() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.6
            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int i;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (interpolatedTime == 1.0f) {
                    i = finalHeight;
                } else {
                    i = (int) (prevHeight + ((finalHeight - r0) * interpolatedTime));
                }
                layoutParams.height = i;
                view.requestLayout();
            }
        };
        animation.setDuration(200L);
        view.startAnimation(animation);
    }

    private void collapse(final View view, final int prevHeight, final int finalHeight) {
        Animation animation = new Animation() { // from class: com.amazon.podcast.nowplayingstage.MetadataView.7
            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int i;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (interpolatedTime == 1.0f) {
                    i = finalHeight;
                } else {
                    i = (int) (prevHeight - ((r0 - finalHeight) * interpolatedTime));
                }
                layoutParams.height = i;
                view.requestLayout();
            }
        };
        animation.setDuration(200L);
        view.startAnimation(animation);
    }

    public void setAllParentsClip(View view, boolean z) {
        View view2 = view;
        while (view2.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2.getParent();
            viewGroup.setClipChildren(z);
            viewGroup.setClipToPadding(z);
            view2 = viewGroup;
        }
    }

    private int getWidthPixels() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getHeightPixels() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private int getDensityPixels() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMetadataMinHeight() {
        return getResources().getDimensionPixelSize(R$dimen.podcast_transport_area_stage_min_height) + getResources().getDimensionPixelSize(R$dimen.podcast_top_bar_min_height) + (getResources().getDimensionPixelSize(R$dimen.podcast_spacer_epic) * 3) + this.subtitle.getHeight() + this.title.getHeight() + this.overlayButtonLayout.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getArtworkSize() {
        int i;
        int densityPixels = getDensityPixels();
        if (densityPixels >= 360 && densityPixels < 640) {
            i = COLUMNS_SM_TO_MD_SIZES;
        } else {
            i = COLUMNS_XS_AND_LG_PLUS_SIZES;
        }
        return getMultiColumnWidth(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMultiColumnWidth(final int numCols) {
        int widthPixels = getWidthPixels();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.gutter);
        return ((((widthPixels - ((dimensionPixelSize * 12) + 1)) - (getResources().getDimensionPixelSize(R$dimen.margin) * 2)) / 12) * numCols) + (dimensionPixelSize * (numCols - 1));
    }

    public void setMaxPlaybackDuration(int maxPlaybackDuration) {
        SeekBar seekBar = this.seekBar;
        if (seekBar != null) {
            seekBar.setMax(maxPlaybackDuration);
        }
    }
}
