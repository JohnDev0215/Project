package com.amazon.podcast.nowplaying;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$anim;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.transcription.HalfScreenTranscriptView;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class MetadataView extends RelativeLayout implements Playback.PositionCallback, Playback.BufferedPositionCallback, Playback.DurationCallback, Media.MediaMetadataCallback, Playback.TranscriptionStatusUpdateCallback, Playback.StateCallback, Playback.PlaybackFinishedCallback, Playback.DismissFullScreenTranscriptCallback {
    private static final Logger logger = LoggerFactory.getLogger("MetadataView");
    private ImageView artwork;
    private BlurTransformation blurTransformation;
    private EmberTextView durationText;
    private GestureDetector gestureDetector;
    private RelativeLayout halfScreenTranscriptLayout;
    private HalfScreenTranscriptView halfScreenTranscriptView;
    private ImageView ingressBackground;
    private boolean isSeeking;
    private ImageView loadingView;
    private String ownerId;
    private Playback playback;
    private int playbackState;
    private EmberTextView progressText;
    private RuntimeStyleSheet runtimeStyleSheet;
    private SeekBar seekBar;
    private EmberTextView subtitle;
    private EmberTextView title;
    private RelativeLayout transcriptIngressPane;
    private EmberTextView transcriptTapText;

    public MetadataView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.isSeeking = false;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_now_playing_metadata, this);
        Playback playback = Podcast.getPlayback();
        this.playback = playback;
        playback.addStateCallback(this);
        this.playback.addPositionCallback(this);
        this.playback.addBufferedPositionCallback(this);
        this.playback.addDurationCallback(this);
        this.playback.addTranscriptUpdatedCallback(this);
        this.playback.addPlaybackFinishedCallback(this);
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.playbackState = this.playback.getPlaybackState();
        this.artwork = (ImageView) findViewById(R$id.podcast_art);
        this.gestureDetector = new GestureDetector(getContext(), new MetadataArtworkGestureListener());
        this.blurTransformation = new BlurTransformation(getContext());
        this.playback.getMedia().addMediaMetadataCallback(this);
        this.loadingView = (ImageView) findViewById(R$id.transcript_syncing_loadingview);
        this.progressText = (EmberTextView) findViewById(R$id.podcast_progress_text);
        this.durationText = (EmberTextView) findViewById(R$id.podcast_duration_text);
        this.title = (EmberTextView) findViewById(R$id.podcast_now_playing_title);
        this.subtitle = (EmberTextView) findViewById(R$id.podcast_now_playing_subtitle);
        SeekBar seekBar = (SeekBar) findViewById(R$id.podcast_now_playing_seekbar);
        this.seekBar = seekBar;
        seekBar.setPadding(5, 25, 5, 16);
        this.transcriptIngressPane = (RelativeLayout) findViewById(R$id.transcipt_ingress_layout);
        this.transcriptTapText = (EmberTextView) findViewById(R$id.transcript_tap_text);
        HalfScreenTranscriptView halfScreenTranscriptView = (HalfScreenTranscriptView) findViewById(R$id.half_screen_ranscript_view);
        this.halfScreenTranscriptView = halfScreenTranscriptView;
        halfScreenTranscriptView.setOwnerId(this.ownerId);
        this.halfScreenTranscriptLayout = (RelativeLayout) findViewById(R$id.half_screen_transcript_view_layout);
        this.ingressBackground = (ImageView) findViewById(R$id.transcript_ingress_background);
        if ((this.playback.getIsTranscriptLoaded() && showSyncTranscriptsIngressPane()) || this.playback.isTranscriptSyncing() || this.playbackState == 2) {
            handleTapViewOnTranscriptIngress();
        } else {
            handleSyncingTranscriptsOnIngress();
        }
        bindTranscriptIngressPane();
        bind(this.playback.getMedia().getMediaMetadataElement());
        addSeekBarListener();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.nowplaying.MetadataView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MetadataView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int iMin = Math.min(MetadataView.this.getWidth(), MetadataView.this.getResources().getDimensionPixelSize(R$dimen.podcast_artwork_max_size));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MetadataView.this.artwork.getLayoutParams();
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.getLayoutParams();
                layoutParams2.width = iMin;
                layoutParams.height = Math.min(iMin, MetadataView.this.getArtworkPossibleMaxHeight(this));
                layoutParams.width = iMin;
                MetadataView.this.artwork.setLayoutParams(layoutParams);
                this.setLayoutParams(layoutParams2);
                MetadataView metadataView = MetadataView.this;
                metadataView.bind(metadataView.playback.getMedia().getMediaMetadataElement());
            }
        });
        this.artwork.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.nowplaying.MetadataView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return MetadataView.this.gestureDetector.onTouchEvent(motionEvent);
            }
        });
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            this.loadingView.setColorFilter(runtimeStyleSheet.getAccentColor());
            this.title.setTypeface(this.runtimeStyleSheet.getHeadline3TypeFace());
        }
    }

    public void addTitleSubtitleOnClickListener(final View.OnClickListener listener) {
        this.title.setOnClickListener(listener);
        this.subtitle.setOnClickListener(listener);
    }

    public void setIsSeeking(final boolean isSeeking) {
        this.isSeeking = isSeeking;
    }

    public void addSeekbarOnTouchListener(final View.OnTouchListener listener) {
        this.seekBar.setOnTouchListener(listener);
    }

    public void addSeekBarListener() {
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.podcast.nowplaying.MetadataView.3
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
                Integer transcriptIndex = MetadataView.this.halfScreenTranscriptView.getTranscriptIndex(j);
                if (transcriptIndex == null) {
                    return;
                }
                MetadataView.this.halfScreenTranscriptView.instantScrollToPosition(transcriptIndex.intValue());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(final SeekBar seekBar) {
                MetadataView.this.playback.setSeekBarTracking(true);
                this.prevProgress = seekBar.getProgress();
                MetadataView metadataView = MetadataView.this;
                Resources resources = metadataView.getContext().getResources();
                int i = R$dimen.podcast_medium_icon;
                metadataView.setSeekbarThumbSize(seekBar, (int) resources.getDimension(i), (int) MetadataView.this.getContext().getResources().getDimension(i));
                if (MetadataView.this.playback.isHasFetchedTranscript()) {
                    MetadataView.this.halfScreenTranscriptLayout.setVisibility(0);
                    MetadataView.this.transcriptIngressPane.setVisibility(8);
                    MetadataView.this.playback.dispatch(MetadataView.this.ownerId, UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ContainerType.NOW_PLAYING, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(final SeekBar seekBar) {
                MetadataView.this.playback.setSeekBarTracking(false);
                MetadataView metadataView = MetadataView.this;
                Resources resources = metadataView.getContext().getResources();
                int i = R$dimen.podcast_tiny_icon;
                metadataView.setSeekbarThumbSize(seekBar, (int) resources.getDimension(i), (int) MetadataView.this.getContext().getResources().getDimension(i));
                if (MetadataView.this.playback.isHasFetchedTranscript()) {
                    MetadataView.this.halfScreenTranscriptLayout.setVisibility(0);
                    MetadataView.this.transcriptIngressPane.setVisibility(8);
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

    public void showTranscriptIngressPane() {
        this.transcriptIngressPane.setVisibility(0);
        handlePausedState();
    }

    public void hideHalfScreenTranscriptView() {
        this.halfScreenTranscriptLayout.setVisibility(8);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        bind(metadataElement);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(final long timeInMillis) {
        if (showSyncTranscriptsIngressPane()) {
            this.playback.setTranscriptSyncing(true);
            handleTapViewOnTranscriptIngress();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void bind(final MediaMetadataElement metadataElement) {
        if (!this.title.isSelected()) {
            this.title.setSelected(true);
        }
        if (metadataElement == null) {
            this.title.setText("");
            this.subtitle.setText("");
            this.seekBar.setMax(100);
            this.seekBar.setProgress(0);
            Picasso.get().load(R$drawable.artwork_placeholder).into(this.artwork);
            return;
        }
        PodcastFeatureGating podcastFeatureGating = PodcastFeatureGating.IS_SONIC_RUSH;
        if (podcastFeatureGating.isEnabled()) {
            RequestCreator requestCreatorLoad = Picasso.get().load(metadataElement.getArtwork());
            int i = R$drawable.refresh_ic_empty_state_image;
            requestCreatorLoad.placeholder(i).error(i).transform(this.blurTransformation).into(this.ingressBackground);
        } else {
            RequestCreator requestCreatorLoad2 = Picasso.get().load(metadataElement.getArtwork());
            int i2 = R$drawable.ic_empty_state_image;
            requestCreatorLoad2.placeholder(i2).error(i2).transform(this.blurTransformation).into(this.ingressBackground);
        }
        this.title.setText(metadataElement.getTitle());
        this.subtitle.setText(metadataElement.getSubtitle());
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
        if (podcastFeatureGating.isEnabled()) {
            RequestCreator requestCreatorResize = Picasso.get().load(metadataElement.getArtwork()).centerCrop().resize(width, width);
            int i3 = R$drawable.refresh_ic_empty_state_image;
            requestCreatorResize.placeholder(i3).error(i3).transform(new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_now_playing_album_art_rounded_corner_radius))).into(this.artwork);
        } else {
            RequestCreator requestCreatorResize2 = Picasso.get().load(metadataElement.getArtwork()).centerCrop().resize(width, width);
            int i4 = R$drawable.ic_empty_state_image;
            requestCreatorResize2.placeholder(i4).error(i4).transform(new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_now_playing_album_art_rounded_corner_radius))).into(this.artwork);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            this.playback.setCurrentClockTime((int) SystemClock.uptimeMillis());
            Media media = this.playback.getMedia();
            bind(media.getMediaMetadataElement());
            this.playback.addPositionCallback(this);
            this.playback.addBufferedPositionCallback(this);
            this.playback.addDurationCallback(this);
            this.playback.addTranscriptUpdatedCallback(this);
            this.playback.addPlaybackFinishedCallback(this);
            this.playback.addDismissTranscriptViewCallback(this);
            media.addMediaMetadataCallback(this);
            return;
        }
        this.playback.removePositionCallback(this);
        this.playback.removeBufferedPositionCallback(this);
        this.playback.removeDurationCallback(this);
        this.playback.removePlaybackFinishedCallback(this);
        this.playback.removeDismissTranscriptViewCallback(this);
        this.playback.getMedia().removeMediaMetadataCallback(this);
        this.playback.removeTranscriptUpdatedCallback(this);
        this.playback.removeStateCallback(this);
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

    public void addTranscriptIngressPaneClickListener(View.OnClickListener onClickListener) {
        this.transcriptIngressPane.setOnClickListener(onClickListener);
    }

    public void bindTranscriptIngressPane() {
        if (this.playback.isHasFetchedTranscript()) {
            this.transcriptIngressPane.setVisibility(0);
            this.playback.dispatch(this.ownerId, UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ContainerType.NOW_PLAYING, UiMetricAttributes$ContentName.PODCASTS_SCRUB_INGRESS));
        } else {
            this.transcriptIngressPane.setVisibility(8);
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

    private void handleSyncingTranscriptsOnIngress() {
        this.transcriptIngressPane.setEnabled(false);
        this.transcriptTapText.setTypeface(Typeface.DEFAULT);
        this.transcriptTapText.setText(getResources().getString(R$string.syncing_transcription));
        this.loadingView.setVisibility(0);
        startSpinnerAnimation();
    }

    private void handleTapViewOnTranscriptIngress() {
        this.transcriptIngressPane.setEnabled(true);
        this.transcriptTapText.setTypeface(Typeface.DEFAULT_BOLD);
        this.transcriptTapText.setText(getResources().getString(R$string.tap_for_full_screen_or_browse_timeline_to_preview));
        this.loadingView.setVisibility(8);
        stopSpinnerAnimation();
    }

    private void startSpinnerAnimation() {
        this.loadingView.startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.podcast_loading_spinner));
    }

    private void stopSpinnerAnimation() {
        Animation animation = this.loadingView.getAnimation();
        if (animation == null) {
            return;
        }
        animation.cancel();
    }

    public void addHalfScreenDismissListener(View.OnClickListener listener) {
        this.halfScreenTranscriptView.addHalfScreenTranscriptionCloseListener(listener);
    }

    public void addFullScreenTranscriptionExpandListener(View.OnClickListener listener) {
        this.halfScreenTranscriptView.addFullScreenTranscriptionExpandListener(listener);
    }

    public void handlePausedState() {
        if (this.playbackState == 2) {
            handleTapViewOnTranscriptIngress();
        }
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
        hideHalfScreenTranscriptView();
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        this.playbackState = state;
        if (state == 2) {
            handleTapViewOnTranscriptIngress();
        }
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
                MetadataView.this.hideHalfScreenTranscriptView();
                MetadataView.this.playback.next();
                return true;
            }
            if (e2.getX() - e1.getX() <= 120.0f || Math.abs(velocityX) <= 200.0f) {
                return true;
            }
            MetadataView.this.playback.setTranscriptImplementation(null);
            MetadataView.this.playback.setHasFetchedTranscript(false);
            MetadataView.this.bindTranscriptIngressPane();
            MetadataView.this.hideHalfScreenTranscriptView();
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

    @Override // com.amazon.podcast.media.playback.Playback.DismissFullScreenTranscriptCallback
    public void onDismissTranscriptView() {
        this.transcriptIngressPane.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getArtworkPossibleMaxHeight(MetadataView view) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.podcast_transport_area_and_about_button_min_height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels - ((view.getTop() + view.getHeight()) + dimensionPixelSize);
    }

    public void setMaxPlaybackDuration(int maxPlaybackDuration) {
        SeekBar seekBar = this.seekBar;
        if (seekBar != null) {
            seekBar.setMax(maxPlaybackDuration);
        }
    }
}
