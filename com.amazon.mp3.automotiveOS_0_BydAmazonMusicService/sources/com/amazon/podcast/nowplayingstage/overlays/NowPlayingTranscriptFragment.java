package com.amazon.podcast.nowplayingstage.overlays;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.nowplayingstage.NowPlayingActivity;
import com.amazon.podcast.nowplayingstage.overlays.TranscriptPreviewAdapter;
import com.amazon.podcast.views.transcriptOverlay.TranscriptItemDecorator;
import com.amazon.podcast.views.transcriptOverlay.TranscriptLayoutManager;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.TranscriptLoadingType;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class NowPlayingTranscriptFragment extends Fragment implements Playback.PositionCallback, Playback.TranscriptionStatusUpdateCallback, Playback.StateCallback {
    private static final Logger logger = LoggerFactory.getLogger("NowPlayingTranscriptFragment");
    private Integer adIndex;
    private int autoScrollingBlockingTimer;
    private TextView badging;
    private TranscriptPreviewAdapter halfScreenTranscriptAdapter;
    private Integer index;
    private boolean isTranscriptInAdRegion;
    private boolean isUserHoldingView;
    private TranscriptLayoutManager linearLayoutManager;
    private String ownerId;
    private Playback playback;
    private int playbackState;
    private int prevIndex;
    private String previousLoadingType;
    private List<ScrubSegment> scrubSegments;
    private RangeMap<Integer, Integer> timeRangeMap;
    private RecyclerView transcriptRecyclerView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R$layout.now_playing_transcript_fragment, container, false);
        Playback playback = Podcast.getPlayback();
        this.playback = playback;
        playback.addPositionCallback(this);
        this.playbackState = -1;
        this.playback.addTranscriptUpdatedCallback(this);
        this.playback.addStateCallback(this);
        bind(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setStartIndex();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setStartIndex();
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(long position) {
        RangeMap<Integer, Integer> rangeMap;
        if (isScrollingActive() || (rangeMap = this.timeRangeMap) == null) {
            return;
        }
        Integer num = rangeMap.get(Integer.valueOf((int) position));
        this.index = num;
        if (num == null) {
            return;
        }
        smoothScrollToPosition(num.intValue());
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        this.playbackState = state;
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptUpdated(final List<ScrubSegment> scrubSegmentList) {
        logger.debug("onTranscriptUpdated");
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment.1
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingTranscriptFragment.this.playback.setUpdatedScrubSegment(scrubSegmentList);
                if (NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter == null || TranscriptLoadingType.AD_START.name().equals(NowPlayingTranscriptFragment.this.previousLoadingType)) {
                    return;
                }
                NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter.setTranscript(scrubSegmentList);
                NowPlayingTranscriptFragment.this.getTranscriptIndex((List<ScrubSegment>) scrubSegmentList);
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoading(final TranscriptLoadingType transcriptLoadingType) {
        logger.debug("onTranscriptLoading");
        if (isScrollingActive()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment.2
            @Override // java.lang.Runnable
            public void run() {
                if (NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter == null) {
                    return;
                }
                if (TranscriptLoadingType.AD_START.name().equals(transcriptLoadingType.name())) {
                    NowPlayingTranscriptFragment.this.isTranscriptInAdRegion = true;
                    NowPlayingTranscriptFragment nowPlayingTranscriptFragment = NowPlayingTranscriptFragment.this;
                    nowPlayingTranscriptFragment.adIndex = nowPlayingTranscriptFragment.index;
                }
                NowPlayingTranscriptFragment.this.previousLoadingType = transcriptLoadingType.name();
            }
        });
    }

    @Override // com.amazon.podcast.media.playback.Playback.TranscriptionStatusUpdateCallback
    public void onTranscriptLoaded() {
        logger.debug("onTranscriptLoaded");
        if (isScrollingActive()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment.3
            @Override // java.lang.Runnable
            public void run() {
                if (NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter == null) {
                    return;
                }
                if (NowPlayingTranscriptFragment.this.playback.getUpdatedScrubSegment() == null || NowPlayingTranscriptFragment.this.playback.getUpdatedScrubSegment().isEmpty()) {
                    NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter.setTranscript(NowPlayingTranscriptFragment.this.scrubSegments);
                    NowPlayingTranscriptFragment nowPlayingTranscriptFragment = NowPlayingTranscriptFragment.this;
                    nowPlayingTranscriptFragment.getTranscriptIndex((List<ScrubSegment>) nowPlayingTranscriptFragment.scrubSegments);
                } else {
                    NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter.setTranscript(NowPlayingTranscriptFragment.this.playback.getUpdatedScrubSegment());
                    NowPlayingTranscriptFragment nowPlayingTranscriptFragment2 = NowPlayingTranscriptFragment.this;
                    nowPlayingTranscriptFragment2.getTranscriptIndex(nowPlayingTranscriptFragment2.playback.getUpdatedScrubSegment());
                }
                NowPlayingTranscriptFragment.this.isTranscriptInAdRegion = false;
                NowPlayingTranscriptFragment.this.halfScreenTranscriptAdapter.setAdIndex(-1);
            }
        });
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void smoothScrollToPosition(final int index) {
        if (this.isTranscriptInAdRegion) {
            Integer num = this.adIndex;
            if (num == null) {
                return;
            }
            this.halfScreenTranscriptAdapter.setCurrentPositionAndAdPosition(num.intValue());
            this.transcriptRecyclerView.scrollToPosition(this.adIndex.intValue());
        } else {
            if (!(this.prevIndex == index)) {
                this.halfScreenTranscriptAdapter.setCurrentPosition(index);
                this.transcriptRecyclerView.smoothScrollToPosition(index);
            }
        }
        this.prevIndex = index;
    }

    public void instantScrollToPosition(int index) {
        this.halfScreenTranscriptAdapter.setCurrentPosition(index);
        this.transcriptRecyclerView.scrollToPosition(index);
    }

    public Integer getTranscriptIndex(long currentPosition) {
        RangeMap<Integer, Integer> rangeMap = this.timeRangeMap;
        if (rangeMap == null) {
            return null;
        }
        return rangeMap.get(Integer.valueOf((int) currentPosition));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer getTranscriptIndex(List<ScrubSegment> scrubSegmentList) {
        this.timeRangeMap.clear();
        for (int i = 0; i < scrubSegmentList.size(); i++) {
            this.timeRangeMap.put(Range.closed(Integer.valueOf(scrubSegmentList.get(i).getStartTime()), Integer.valueOf(scrubSegmentList.get(i).getEndTime())), Integer.valueOf(i));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unblockScrolling() {
        this.autoScrollingBlockingTimer = 0;
        this.isUserHoldingView = false;
    }

    private boolean isTimedBlockActive() {
        return SystemClock.uptimeMillis() - ((long) this.autoScrollingBlockingTimer) < CoroutineLiveDataKt.DEFAULT_TIMEOUT;
    }

    private void setStartIndex() {
        Integer transcriptIndex = getTranscriptIndex((int) this.playback.getPlaybackPosition());
        if (transcriptIndex != null) {
            instantScrollToPosition(transcriptIndex.intValue());
        }
    }

    private boolean isScrollingActive() {
        return this.playback.isSeekBarTracking() || this.isUserHoldingView || isTimedBlockActive();
    }

    private void bind(final View view) {
        this.isUserHoldingView = false;
        TextView textView = (TextView) view.findViewById(R$id.transcript_badging);
        this.badging = textView;
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        this.transcriptRecyclerView = (RecyclerView) view.findViewById(R$id.transcript_recyclerview);
        TranscriptLayoutManager transcriptLayoutManager = new TranscriptLayoutManager(getContext()) { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return true;
            }
        };
        this.linearLayoutManager = transcriptLayoutManager;
        this.transcriptRecyclerView.setLayoutManager(transcriptLayoutManager);
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
        TranscriptPreviewAdapter transcriptPreviewAdapter = new TranscriptPreviewAdapter(list, new TranscriptPreviewAdapter.OnItemClickListener() { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment.5
            @Override // com.amazon.podcast.nowplayingstage.overlays.TranscriptPreviewAdapter.OnItemClickListener
            public void onItemClick(ScrubSegment scrubSegment) {
                NowPlayingTranscriptFragment.this.unblockScrolling();
                NowPlayingTranscriptFragment.this.playback.seekTo(scrubSegment.getStartTime());
                if (!((NowPlayingActivity) NowPlayingTranscriptFragment.this.getActivity()).getIsOverlayExpanded()) {
                    ((NowPlayingActivity) NowPlayingTranscriptFragment.this.getActivity()).setExpandedMode();
                }
                NowPlayingTranscriptFragment.this.playback.dispatch(NowPlayingTranscriptFragment.this.ownerId, UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, UiMetricAttributes$ActionType.SELECT_SCRUB_SEGMENT, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, NowPlayingTranscriptFragment.this.ownerId, (Integer) null, UiMetricAttributes$ContentName.PODCASTS_HALF_SCRUB));
                if (NowPlayingTranscriptFragment.this.playbackState == 2) {
                    NowPlayingTranscriptFragment.this.playback.playPause();
                }
            }
        }, getContext());
        this.halfScreenTranscriptAdapter = transcriptPreviewAdapter;
        this.transcriptRecyclerView.setAdapter(transcriptPreviewAdapter);
        getTranscriptIndex(this.scrubSegments);
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new OverlayGestureListener());
        this.transcriptRecyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.podcast.nowplayingstage.overlays.NowPlayingTranscriptFragment.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1) {
                    NowPlayingTranscriptFragment.this.isUserHoldingView = false;
                    NowPlayingTranscriptFragment.this.autoScrollingBlockingTimer = (int) SystemClock.uptimeMillis();
                } else if (action == 2) {
                    NowPlayingTranscriptFragment.this.isUserHoldingView = true;
                }
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private class OverlayGestureListener extends GestureDetector.SimpleOnGestureListener {
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

        public OverlayGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 != null && e2 != null) {
                boolean isOverlayExpanded = ((NowPlayingActivity) NowPlayingTranscriptFragment.this.getActivity()).getIsOverlayExpanded();
                float y = e1.getY() - e2.getY();
                boolean z = y < 0.0f;
                if (!isOverlayExpanded && !z) {
                    if (y >= 10.0f && Math.abs(velocityY) > 200.0f) {
                        ((NowPlayingActivity) NowPlayingTranscriptFragment.this.getActivity()).setExpandedMode();
                    }
                    return true;
                }
            }
            return false;
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
}
