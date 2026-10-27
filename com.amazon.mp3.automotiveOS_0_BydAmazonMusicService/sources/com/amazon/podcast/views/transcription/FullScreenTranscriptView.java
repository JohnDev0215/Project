package com.amazon.podcast.views.transcription;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.cast.ChromecastMediaProvider;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.nowplaying.MiniPlayerView;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.transcription.FullScreenTranscriptViewAdapter;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class FullScreenTranscriptView extends LinearLayout implements Media.MediaMetadataCallback, Playback.PositionCallback, Playback.TranscriptionStatusUpdateCallback, Playback.StateCallback, Playback.DismissFullScreenTranscriptCallback {
    private static final Logger logger = LoggerFactory.getLogger("FullScreenTranscriptView");
    private int SCROLL_UNLOCK_DELAY;
    private Integer adIndex;
    private int autoScrollingBlockingTimer;
    private ImageView background;
    private BlurTransformation blurTransformation;
    private Context context;
    private EmberTextView currentPlaybackTime;
    private FullScreenTranscriptViewAdapter fullscreenTranscriptViewAdapter;
    private Integer index;
    private boolean isInstantScrolling;
    private boolean isTranscriptInAdRegion;
    private boolean isUserHoldingView;
    private TranscriptLayoutManager linearLayoutManager;
    private ImageView loadingView;
    private MiniPlayerView miniPlayerView;
    private String ownerId;
    private Playback playback;
    private int playbackState;
    private int prevIndex;
    private String previousLoadingType;
    private Resources resources;
    private RuntimeStyleSheet runtimeStyleSheet;
    private EmberTextView scrollTime;
    private List<ScrubSegment> scrubSegments;
    private RangeMap<Integer, Integer> timeRangeMap;
    private EmberTextView totalDurationTime;
    private ImageView transcriptIndicator;
    private RecyclerView transcriptRecyclerView;
    private EmberTextView transcriptStatus;

    public FullScreenTranscriptView(Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.SCROLL_UNLOCK_DELAY = DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT;
        this.context = context;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_full_screen_transcript_view, this);
        this.blurTransformation = new BlurTransformation(getContext());
        this.playback = Podcast.getPlayback();
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.resources = this.context.getResources();
        this.isUserHoldingView = false;
        this.playbackState = -1;
        this.background = (ImageView) findViewById(R$id.now_playing_full_screen_transcription_background);
        MiniPlayerView miniPlayerView = (MiniPlayerView) findViewById(R$id.mini_player_view_full_screen_transcription);
        this.miniPlayerView = miniPlayerView;
        miniPlayerView.setIsSingleTapFunctionalityEnabled(false);
        this.transcriptRecyclerView = (RecyclerView) findViewById(R$id.podcast_full_trancript_recyclerview);
        this.currentPlaybackTime = (EmberTextView) findViewById(R$id.full_screen_transcript_current_playing_time);
        this.totalDurationTime = (EmberTextView) findViewById(R$id.full_screen_transcription_playback_duration);
        this.scrollTime = (EmberTextView) findViewById(R$id.full_screen_transcript_scroll_time);
        this.loadingView = (ImageView) findViewById(R$id.full_screen_transcript_syncing_loadingview);
        TranscriptLayoutManager transcriptLayoutManager = new TranscriptLayoutManager(this.context);
        this.linearLayoutManager = transcriptLayoutManager;
        this.transcriptRecyclerView.setLayoutManager(transcriptLayoutManager);
        this.transcriptStatus = (EmberTextView) findViewById(R$id.transcript_badging);
        this.transcriptIndicator = (ImageView) findViewById(R$id.center_caret);
        this.transcriptRecyclerView.addItemDecoration(new TranscriptItemDecorator());
        this.timeRangeMap = TreeRangeMap.create();
        this.playback.getMedia().addMediaMetadataCallback(this);
        this.playback.addPositionCallback(this);
        this.playback.addTranscriptUpdatedCallback(this);
        this.playback.addStateCallback(this);
        this.playback.addDismissTranscriptViewCallback(this);
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            this.scrollTime.setTextColor(runtimeStyleSheet.getAccentColor());
            this.loadingView.setColorFilter(this.runtimeStyleSheet.getAccentColor());
            this.transcriptIndicator.setColorFilter(this.runtimeStyleSheet.getAccentColor());
        }
        this.miniPlayerView.setFromAboutView();
        if (this.playback.getUpdatedScrubSegment() == null || this.playback.getUpdatedScrubSegment().isEmpty()) {
            this.scrubSegments = this.playback.getScrubSegments();
        } else {
            this.scrubSegments = this.playback.getUpdatedScrubSegment();
        }
        if (this.scrubSegments == null) {
            return;
        }
        bindButtonsControls();
        bindTranscriptViews();
    }

    private void bindTranscriptViews() {
        FullScreenTranscriptViewAdapter fullScreenTranscriptViewAdapter = new FullScreenTranscriptViewAdapter(this.scrubSegments, new FullScreenTranscriptViewAdapter.OnItemClickListener() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.1
            @Override // com.amazon.podcast.views.transcription.FullScreenTranscriptViewAdapter.OnItemClickListener
            public void onItemClick(ScrubSegment scrubSegment) {
                FullScreenTranscriptView.this.unblockScrolling();
                FullScreenTranscriptView.this.playback.seekTo(scrubSegment.getStartTime());
                FullScreenTranscriptView.this.playback.dispatch(FullScreenTranscriptView.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_FULL_SCRUB, UiMetricAttributes$ActionType.SELECT_SCRUB_SEGMENT, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, FullScreenTranscriptView.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_FULL_SCRUB));
                if (FullScreenTranscriptView.this.playbackState == 2) {
                    FullScreenTranscriptView.this.playback.playPause();
                }
            }
        }, this.context);
        this.fullscreenTranscriptViewAdapter = fullScreenTranscriptViewAdapter;
        this.transcriptRecyclerView.setAdapter(fullScreenTranscriptViewAdapter);
        getTranscriptIndex(this.scrubSegments);
        this.transcriptRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == 0) {
                    FullScreenTranscriptView.this.scrollTime.setVisibility(8);
                    FullScreenTranscriptView.this.currentPlaybackTime.setVisibility(0);
                }
            }
        });
        this.transcriptRecyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1) {
                    FullScreenTranscriptView.this.isUserHoldingView = false;
                    FullScreenTranscriptView.this.autoScrollingBlockingTimer = (int) SystemClock.uptimeMillis();
                } else if (action == 2) {
                    FullScreenTranscriptView.this.isUserHoldingView = true;
                    FullScreenTranscriptView.this.scrollTime.setVisibility(0);
                    FullScreenTranscriptView.this.currentPlaybackTime.setVisibility(8);
                    FullScreenTranscriptView.this.scrollTime.setText(String.valueOf(FullScreenTranscriptView.this.formatTime(Long.valueOf(((ScrubSegment) FullScreenTranscriptView.this.scrubSegments.get((FullScreenTranscriptView.this.linearLayoutManager.findFirstVisibleItemPosition() + FullScreenTranscriptView.this.linearLayoutManager.findLastVisibleItemPosition()) / 2)).getStartTime()))));
                }
                return false;
            }
        });
    }

    public void bindMetadata(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            return;
        }
        String artwork = metadataElement.getArtwork();
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            RequestCreator requestCreatorLoad = Picasso.get().load(artwork);
            int i = R$drawable.refresh_ic_empty_state_image;
            requestCreatorLoad.placeholder(i).error(i).transform(this.blurTransformation).into(this.background);
        } else {
            RequestCreator requestCreatorLoad2 = Picasso.get().load(artwork);
            int i2 = R$drawable.ic_empty_state_image;
            requestCreatorLoad2.placeholder(i2).error(i2).transform(this.blurTransformation).into(this.background);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getTranscriptIndex(List<ScrubSegment> scrubSegmentList) {
        this.timeRangeMap.clear();
        for (int i = 0; i < scrubSegmentList.size(); i++) {
            this.timeRangeMap.put(Range.closed(Integer.valueOf(scrubSegmentList.get(i).getStartTime()), Integer.valueOf(scrubSegmentList.get(i).getEndTime())), Integer.valueOf(i));
        }
        return null;
    }

    public void setMinimizeOnClickListener(final View.OnClickListener listener) {
        MiniPlayerView miniPlayerView = this.miniPlayerView;
        if (miniPlayerView != null) {
            miniPlayerView.setMinimizeOnClickListener(listener);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            Media media = this.playback.getMedia();
            bindMetadata(media.getMediaMetadataElement());
            media.addMediaMetadataCallback(this);
        } else {
            this.playback.getMedia().removeMediaMetadataCallback(this);
            this.playback.removePositionCallback(this);
            this.playback.removeDismissTranscriptViewCallback(this);
            this.playback.removeTranscriptUpdatedCallback(this);
            this.playback.removeStateCallback(this);
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(long position) {
        if (this.timeRangeMap == null) {
            return;
        }
        this.currentPlaybackTime.setText(formatTime(Long.valueOf(position)));
        this.totalDurationTime.setText(String.format("%s", formatTime(Long.valueOf(PlaybackCache.get(this.playback.getMedia().mediaId()).getDurationMilliseconds() - position))));
        Integer num = this.timeRangeMap.get(Integer.valueOf((int) position));
        this.index = num;
        if (num == null) {
            return;
        }
        if (!(this.prevIndex == num.intValue())) {
            this.isInstantScrolling = false;
        }
        if (isScrollingActive()) {
            return;
        }
        smoothScrollToPosition();
    }

    public void smoothScrollToPosition() {
        if (this.isTranscriptInAdRegion) {
            this.transcriptIndicator.setVisibility(4);
            Integer num = this.adIndex;
            if (num == null) {
                return;
            }
            this.fullscreenTranscriptViewAdapter.setCurrentPositionAndAdPosition(num.intValue());
            this.transcriptRecyclerView.scrollToPosition(this.adIndex.intValue());
        } else {
            if (!(this.prevIndex == this.index.intValue())) {
                this.transcriptIndicator.setVisibility(0);
                this.fullscreenTranscriptViewAdapter.setCurrentPosition(this.index.intValue());
                this.transcriptRecyclerView.smoothScrollToPosition(this.index.intValue());
            }
        }
        this.prevIndex = this.index.intValue();
    }

    public void instantScrollToPosition() {
        Integer num = this.index;
        if (num == null) {
            return;
        }
        this.isInstantScrolling = true;
        this.transcriptRecyclerView.scrollToPosition(num.intValue());
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptUpdated(final List<ScrubSegment> scrubSegments) {
        logger.debug("onTranscriptUpdated");
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.4
            @Override // java.lang.Runnable
            public void run() {
                FullScreenTranscriptView.this.playback.setUpdatedScrubSegment(scrubSegments);
                if (FullScreenTranscriptView.this.fullscreenTranscriptViewAdapter == null || TranscriptLoadingType.AD_START.name().equals(FullScreenTranscriptView.this.previousLoadingType)) {
                    return;
                }
                FullScreenTranscriptView.this.fullscreenTranscriptViewAdapter.setTranscriptValue(scrubSegments);
                FullScreenTranscriptView.this.getTranscriptIndex(scrubSegments);
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoading(final TranscriptLoadingType transcriptLoadingType) {
        logger.debug("onTranscriptLoading:   " + transcriptLoadingType.name());
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.5
            @Override // java.lang.Runnable
            public void run() {
                if (TranscriptLoadingType.AD_START.name().equals(transcriptLoadingType.name())) {
                    FullScreenTranscriptView.this.isTranscriptInAdRegion = true;
                    FullScreenTranscriptView fullScreenTranscriptView = FullScreenTranscriptView.this;
                    fullScreenTranscriptView.adIndex = fullScreenTranscriptView.index;
                }
                FullScreenTranscriptView.this.previousLoadingType = transcriptLoadingType.name();
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoaded() {
        logger.debug("onTranscriptLoaded");
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.6
            @Override // java.lang.Runnable
            public void run() {
                if (FullScreenTranscriptView.this.fullscreenTranscriptViewAdapter == null) {
                    return;
                }
                if (FullScreenTranscriptView.this.playback.getUpdatedScrubSegment() == null || FullScreenTranscriptView.this.playback.getUpdatedScrubSegment().isEmpty()) {
                    FullScreenTranscriptView.this.fullscreenTranscriptViewAdapter.setTranscriptValue(FullScreenTranscriptView.this.scrubSegments);
                    FullScreenTranscriptView fullScreenTranscriptView = FullScreenTranscriptView.this;
                    fullScreenTranscriptView.getTranscriptIndex(fullScreenTranscriptView.scrubSegments);
                } else {
                    FullScreenTranscriptView.this.fullscreenTranscriptViewAdapter.setTranscriptValue(FullScreenTranscriptView.this.playback.getUpdatedScrubSegment());
                    FullScreenTranscriptView fullScreenTranscriptView2 = FullScreenTranscriptView.this;
                    fullScreenTranscriptView2.getTranscriptIndex(fullScreenTranscriptView2.playback.getUpdatedScrubSegment());
                }
                FullScreenTranscriptView.this.isTranscriptInAdRegion = false;
                FullScreenTranscriptView.this.fullscreenTranscriptViewAdapter.setAdIndex(-1);
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(MediaMetadataElement metadataElement) {
        bindMetadata(metadataElement);
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

    private void stopSpinnerAnimation() {
        Animation animation = this.loadingView.getAnimation();
        if (animation == null) {
            return;
        }
        animation.cancel();
    }

    public void unblockScrolling() {
        this.autoScrollingBlockingTimer = 0;
        this.isUserHoldingView = false;
    }

    public void handlePauseState() {
        if (this.playbackState == 2) {
            this.transcriptStatus.setText(this.resources.getString(R$string.auto_transcription));
            this.loadingView.setVisibility(8);
            stopSpinnerAnimation();
        }
    }

    private boolean isTimedBlockActive() {
        return SystemClock.uptimeMillis() - ((long) this.autoScrollingBlockingTimer) < ((long) this.SCROLL_UNLOCK_DELAY);
    }

    private boolean isScrollingActive() {
        return this.isInstantScrolling || this.isUserHoldingView || isTimedBlockActive();
    }

    public void bindButtonsControls() {
        this.miniPlayerView.getPlayPause().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChromecastMediaProvider.setPlaybackOnChromecastPaused(false);
                FullScreenTranscriptView.this.playback.playPause();
                FullScreenTranscriptView.this.playback.dispatch(FullScreenTranscriptView.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_FULL_SCRUB, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, FullScreenTranscriptView.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_FULL_SCRUB));
            }
        });
        this.miniPlayerView.getFastForward().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.transcription.FullScreenTranscriptView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullScreenTranscriptView.this.playback.fastForward();
                FullScreenTranscriptView.this.playback.dispatch(FullScreenTranscriptView.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_FULL_SCRUB, UiMetricAttributes$ActionType.FAST_FORWARD_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, FullScreenTranscriptView.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_FULL_SCRUB));
            }
        });
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        this.playbackState = state;
    }

    @Override // com.amazon.podcast.media.playback.Playback.DismissFullScreenTranscriptCallback
    public void onDismissTranscriptView() {
        this.playback.dispatch(this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_FULL_SCRUB, UiMetricAttributes$ActionType.CLOSE_SCRUB, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_FULL_SCRUB));
        this.playback.removeDismissTranscriptViewCallback(this);
        setVisibility(8);
    }
}
