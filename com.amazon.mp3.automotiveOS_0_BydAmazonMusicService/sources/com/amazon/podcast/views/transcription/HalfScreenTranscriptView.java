package com.amazon.podcast.views.transcription;

import android.content.Context;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.nowplaying.MiniPlayerView;
import com.amazon.podcast.views.transcription.HalfScreenTranscriptViewAdapter;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class HalfScreenTranscriptView extends LinearLayout implements Playback.PositionCallback, Playback.TranscriptionStatusUpdateCallback, Playback.StateCallback {
    private static final Logger logger = LoggerFactory.getLogger("HalfScreenTranscriptView");
    private int SCROLL_UNLOCK_DELAY;
    private Integer adIndex;
    private int autoScrollingBlockingTimer;
    private ImageView closeHalfScreenView;
    private Context context;
    private ImageView expandFullScreenView;
    private HalfScreenTranscriptViewAdapter halfScreenTranscriptAdapter;
    private Integer index;
    private boolean isTranscriptInAdRegion;
    private boolean isUserHoldingView;
    private TranscriptLayoutManager linearLayoutManager;
    private MiniPlayerView miniPlayerView;
    private String ownerId;
    private Playback playback;
    private int playbackState;
    private int prevIndex;
    private String previousLoadingType;
    private RuntimeStyleSheet runtimeStyleSheet;
    private TextView scrollTime;
    private List<ScrubSegment> scrubSegments;
    private RangeMap<Integer, Integer> timeRangeMap;
    private ImageView transcriptIndicator;
    private RecyclerView transcriptRecyclerView;

    public HalfScreenTranscriptView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.SCROLL_UNLOCK_DELAY = DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT;
        this.context = context;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_half_screen_transcript_view, this);
        Playback playback = Podcast.getPlayback();
        this.playback = playback;
        playback.addPositionCallback(this);
        this.playbackState = -1;
        this.playback.addTranscriptUpdatedCallback(this);
        this.playback.addStateCallback(this);
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.scrollTime = (TextView) findViewById(R$id.scroll_time);
        this.closeHalfScreenView = (ImageView) findViewById(R$id.close_transcript);
        this.expandFullScreenView = (ImageView) findViewById(R$id.full_screen_expand_button);
        bind();
    }

    private void bind() {
        this.isUserHoldingView = false;
        this.transcriptRecyclerView = (RecyclerView) findViewById(R$id.trancript_recyclerview);
        this.linearLayoutManager = new TranscriptLayoutManager(this.context) { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return true;
            }
        };
        ImageView imageView = (ImageView) findViewById(R$id.center_caret);
        this.transcriptIndicator = imageView;
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            imageView.setColorFilter(runtimeStyleSheet.getAccentColor());
        }
        this.transcriptRecyclerView.setLayoutManager(this.linearLayoutManager);
        this.transcriptRecyclerView.addItemDecoration(new TranscriptItemDecorator());
        this.timeRangeMap = TreeRangeMap.create();
        if (this.playback.getUpdatedScrubSegment() == null || this.playback.getUpdatedScrubSegment().isEmpty()) {
            this.scrubSegments = this.playback.getScrubSegments();
        } else {
            this.scrubSegments = this.playback.getUpdatedScrubSegment();
        }
        List<ScrubSegment> list = this.scrubSegments;
        if (list == null) {
            return;
        }
        HalfScreenTranscriptViewAdapter halfScreenTranscriptViewAdapter = new HalfScreenTranscriptViewAdapter(list, new HalfScreenTranscriptViewAdapter.OnItemClickListener() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.2
            @Override // com.amazon.podcast.views.transcription.HalfScreenTranscriptViewAdapter.OnItemClickListener
            public void onItemClick(ScrubSegment scrubSegment) {
                HalfScreenTranscriptView.this.unblockScrolling();
                HalfScreenTranscriptView.this.playback.seekTo(scrubSegment.getStartTime());
                HalfScreenTranscriptView.this.playback.dispatch(HalfScreenTranscriptView.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ActionType.SELECT_SCRUB_SEGMENT, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, HalfScreenTranscriptView.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
                if (HalfScreenTranscriptView.this.playbackState == 2) {
                    HalfScreenTranscriptView.this.playback.playPause();
                }
            }
        }, this.context);
        this.halfScreenTranscriptAdapter = halfScreenTranscriptViewAdapter;
        this.transcriptRecyclerView.setAdapter(halfScreenTranscriptViewAdapter);
        getTranscriptIndex(this.scrubSegments);
        this.transcriptRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == 0) {
                    HalfScreenTranscriptView.this.scrollTime.setVisibility(8);
                }
            }
        });
        this.transcriptRecyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1) {
                    HalfScreenTranscriptView.this.isUserHoldingView = false;
                    HalfScreenTranscriptView.this.autoScrollingBlockingTimer = (int) SystemClock.uptimeMillis();
                } else if (action == 2) {
                    HalfScreenTranscriptView.this.isUserHoldingView = true;
                    HalfScreenTranscriptView.this.scrollTime.setVisibility(0);
                    HalfScreenTranscriptView.this.scrollTime.setText(String.valueOf(HalfScreenTranscriptView.this.formatTime(Long.valueOf(((ScrubSegment) HalfScreenTranscriptView.this.scrubSegments.get((HalfScreenTranscriptView.this.linearLayoutManager.findFirstVisibleItemPosition() + HalfScreenTranscriptView.this.linearLayoutManager.findLastVisibleItemPosition()) / 2)).getStartTime()))));
                    HalfScreenTranscriptView.this.scrollTime.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
                    HalfScreenTranscriptView.this.scrollTime.setTypeface(Typeface.DEFAULT_BOLD);
                }
                return false;
            }
        });
    }

    public void setMinimizeOnClickListener(final View.OnClickListener listener) {
        this.miniPlayerView.setMinimizeOnClickListener(listener);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(long currentPosition) {
        RangeMap<Integer, Integer> rangeMap;
        if (isScrollingActive() || (rangeMap = this.timeRangeMap) == null) {
            return;
        }
        Integer num = rangeMap.get(Integer.valueOf((int) currentPosition));
        this.index = num;
        if (num == null) {
            return;
        }
        smoothScrollToPosition(num.intValue());
    }

    public void smoothScrollToPosition(final int index) {
        if (this.isTranscriptInAdRegion) {
            this.transcriptIndicator.setVisibility(4);
            Integer num = this.adIndex;
            if (num == null) {
                return;
            }
            this.halfScreenTranscriptAdapter.setCurrentPositionAndAdPosition(num.intValue());
            this.transcriptRecyclerView.scrollToPosition(this.adIndex.intValue());
        } else {
            if (!(this.prevIndex == index)) {
                this.transcriptIndicator.setVisibility(0);
                this.halfScreenTranscriptAdapter.setCurrentPosition(index);
                this.transcriptRecyclerView.smoothScrollToPosition(index);
            }
        }
        this.prevIndex = index;
    }

    public void instantScrollToPosition(int index) {
        this.transcriptRecyclerView.scrollToPosition(index);
    }

    public Integer getTranscriptIndex(long currentPosition) {
        RangeMap<Integer, Integer> rangeMap = this.timeRangeMap;
        if (rangeMap == null) {
            return null;
        }
        return rangeMap.get(Integer.valueOf((int) currentPosition));
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            return;
        }
        this.playback.removeTranscriptUpdatedCallback(this);
        this.playback.removePositionCallback(this);
        this.playback.removeStateCallback(this);
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptUpdated(final List<ScrubSegment> scrubSegmentList) {
        logger.debug("onTranscriptUpdated");
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.5
            @Override // java.lang.Runnable
            public void run() {
                HalfScreenTranscriptView.this.playback.setUpdatedScrubSegment(scrubSegmentList);
                if (HalfScreenTranscriptView.this.halfScreenTranscriptAdapter == null || TranscriptLoadingType.AD_START.name().equals(HalfScreenTranscriptView.this.previousLoadingType)) {
                    return;
                }
                HalfScreenTranscriptView.this.halfScreenTranscriptAdapter.setTranscript(scrubSegmentList);
                HalfScreenTranscriptView.this.getTranscriptIndex((List<ScrubSegment>) scrubSegmentList);
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoading(final TranscriptLoadingType transcriptLoadingType) {
        logger.debug("onTranscriptLoading");
        if (isScrollingActive()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.6
            @Override // java.lang.Runnable
            public void run() {
                if (HalfScreenTranscriptView.this.halfScreenTranscriptAdapter == null) {
                    return;
                }
                if (TranscriptLoadingType.AD_START.name().equals(transcriptLoadingType.name())) {
                    HalfScreenTranscriptView.this.isTranscriptInAdRegion = true;
                    HalfScreenTranscriptView halfScreenTranscriptView = HalfScreenTranscriptView.this;
                    halfScreenTranscriptView.adIndex = halfScreenTranscriptView.index;
                }
                HalfScreenTranscriptView.this.previousLoadingType = transcriptLoadingType.name();
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoaded() {
        logger.debug("onTranscriptLoaded");
        if (isScrollingActive()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.transcription.HalfScreenTranscriptView.7
            @Override // java.lang.Runnable
            public void run() {
                if (HalfScreenTranscriptView.this.halfScreenTranscriptAdapter == null) {
                    return;
                }
                if (HalfScreenTranscriptView.this.playback.getUpdatedScrubSegment() == null || HalfScreenTranscriptView.this.playback.getUpdatedScrubSegment().isEmpty()) {
                    HalfScreenTranscriptView.this.halfScreenTranscriptAdapter.setTranscript(HalfScreenTranscriptView.this.scrubSegments);
                    HalfScreenTranscriptView halfScreenTranscriptView = HalfScreenTranscriptView.this;
                    halfScreenTranscriptView.getTranscriptIndex((List<ScrubSegment>) halfScreenTranscriptView.scrubSegments);
                } else {
                    HalfScreenTranscriptView.this.halfScreenTranscriptAdapter.setTranscript(HalfScreenTranscriptView.this.playback.getUpdatedScrubSegment());
                    HalfScreenTranscriptView halfScreenTranscriptView2 = HalfScreenTranscriptView.this;
                    halfScreenTranscriptView2.getTranscriptIndex(halfScreenTranscriptView2.playback.getUpdatedScrubSegment());
                }
                HalfScreenTranscriptView.this.isTranscriptInAdRegion = false;
                HalfScreenTranscriptView.this.halfScreenTranscriptAdapter.setAdIndex(-1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getTranscriptIndex(List<ScrubSegment> scrubSegmentList) {
        this.timeRangeMap.clear();
        for (int i = 0; i < scrubSegmentList.size(); i++) {
            this.timeRangeMap.put(Range.closed(Integer.valueOf(scrubSegmentList.get(i).getStartTime()), Integer.valueOf(scrubSegmentList.get(i).getEndTime())), Integer.valueOf(i));
        }
        return null;
    }

    public void addHalfScreenTranscriptionCloseListener(View.OnClickListener listener) {
        ImageView imageView = this.closeHalfScreenView;
        if (imageView != null) {
            imageView.setOnClickListener(listener);
        }
    }

    public void addFullScreenTranscriptionExpandListener(View.OnClickListener listener) {
        ImageView imageView = this.expandFullScreenView;
        if (imageView != null) {
            imageView.setOnClickListener(listener);
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void unblockScrolling() {
        this.autoScrollingBlockingTimer = 0;
        this.isUserHoldingView = false;
    }

    private boolean isTimedBlockActive() {
        return SystemClock.uptimeMillis() - ((long) this.autoScrollingBlockingTimer) < ((long) this.SCROLL_UNLOCK_DELAY);
    }

    private boolean isScrollingActive() {
        return this.playback.isSeekBarTracking() || this.isUserHoldingView || isTimedBlockActive();
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        this.playbackState = state;
    }
}
