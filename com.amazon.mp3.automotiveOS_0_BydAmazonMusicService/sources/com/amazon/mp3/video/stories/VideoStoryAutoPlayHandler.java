package com.amazon.mp3.video.stories;

import ExternalActionInterface.v1_0.VideoStoryContent;
import android.content.Context;
import android.view.TextureView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.video.stories.ThrottleTrackingBus;
import com.amazon.mp3.video.stories.metrics.EntityRenderingType;
import com.amazon.mp3.video.stories.metrics.VideoStoryAutoPlayMetricsImpl;
import com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Action1;

/* JADX INFO: compiled from: VideoStoryAutoPlayHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009b\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\b\u0007*\u0003>AJ\u0018\u0000 O2\u00020\u0001:\u0001OB\u000f\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\bM\u0010NJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J$\u0010\f\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nJ\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\nJ\u0010\u0010\u0012\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u0016\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\bJ\u001a\u0010\u0019\u001a\u00020\u00022\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u0017J\u0006\u0010\u001a\u001a\u00020\u0002J\u0006\u0010\u001b\u001a\u00020\u0002J\u000e\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R \u0010$\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010*R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\u0006008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u001b\u0010:\u001a\u0002058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0018\u0010<\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010?\u001a\u00020>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u001c\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u001c\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010H0D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010GR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010L¨\u0006P"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler;", "", "", "initThrottleTrackingBus", "initPlayer", "", "LExternalActionInterface/v1_0/VideoStoryContent;", "playbackItemsList", "", "startIndex", "", "startAutoPlay", "setPlayQueue", PageUriUtils.DO_PLAY_VALUE, "shouldResetSeekPosition", "prepare", "", "viewRefId", "setShovelerViewId", "Landroid/view/TextureView;", "textureView", "index", "setTextureView", "", "autoPlayViewsMap", "setAutoPlayViews", "resumePlayback", "pausePlayback", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "setRecyclerView", "Lcom/amazon/mp3/video/stories/VideoStoryPlaybackProvider;", "playbackProvider", "Lcom/amazon/mp3/video/stories/VideoStoryPlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/mp3/video/stories/VideoStoryPlaybackProvider;", "autoPlayViews", "Ljava/util/Map;", "Lcom/amazon/mp3/video/stories/ThrottleTrackingBus;", "throttleTrackingBus", "Lcom/amazon/mp3/video/stories/ThrottleTrackingBus;", "previousIndex", "I", "currentIndex", "Landroidx/recyclerview/widget/RecyclerView;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer;", "player", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer;", "", "playbackItems", "Ljava/util/List;", "viewId", "Ljava/lang/String;", "Lcom/amazon/mp3/video/stories/metrics/VideoStoryAutoPlayMetricsImpl;", "autoPlayMetricsSender$delegate", "Lkotlin/Lazy;", "getAutoPlayMetricsSender", "()Lcom/amazon/mp3/video/stories/metrics/VideoStoryAutoPlayMetricsImpl;", "autoPlayMetricsSender", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayEventHandler;", "autoPlayEventHandler", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayEventHandler;", "com/amazon/mp3/video/stories/VideoStoryAutoPlayHandler$recyclerViewListener$1", "recyclerViewListener", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler$recyclerViewListener$1;", "com/amazon/mp3/video/stories/VideoStoryAutoPlayHandler$autoPlayMetricsReporter$1", "autoPlayMetricsReporter", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler$autoPlayMetricsReporter$1;", "Lrx/functions/Action1;", "Lcom/amazon/mp3/video/stories/ThrottleTrackingBus$VisibleState;", "recyclerViewItemsTrackerCallbackListener", "Lrx/functions/Action1;", "", "recyclerViewItemsTrackerErrorListener", "com/amazon/mp3/video/stories/VideoStoryAutoPlayHandler$videoPlayerEventsListener$1", "videoPlayerEventsListener", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler$videoPlayerEventsListener$1;", "<init>", "(Lcom/amazon/mp3/video/stories/VideoStoryPlaybackProvider;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryAutoPlayHandler {
    private static final String TAG;
    private static final Logger logger;
    private VideoStoryAutoPlayEventHandler autoPlayEventHandler;
    private final VideoStoryAutoPlayHandler$autoPlayMetricsReporter$1 autoPlayMetricsReporter;

    /* JADX INFO: renamed from: autoPlayMetricsSender$delegate, reason: from kotlin metadata */
    private final Lazy autoPlayMetricsSender;
    private final Map<Integer, TextureView> autoPlayViews;
    private int currentIndex;
    private final List<VideoStoryContent> playbackItems;
    private final VideoStoryPlaybackProvider playbackProvider;
    private VideoPlayer player;
    private int previousIndex;
    private RecyclerView recyclerView;
    private final Action1<ThrottleTrackingBus.VisibleState> recyclerViewItemsTrackerCallbackListener;
    private final Action1<Throwable> recyclerViewItemsTrackerErrorListener;
    private VideoStoryAutoPlayHandler$recyclerViewListener$1 recyclerViewListener;
    private ThrottleTrackingBus throttleTrackingBus;
    private final VideoStoryAutoPlayHandler$videoPlayerEventsListener$1 videoPlayerEventsListener;
    private String viewId;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$videoPlayerEventsListener$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$recyclerViewListener$1] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.amazon.mp3.video.stories.AutoPlayMetricsReporter, com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$autoPlayMetricsReporter$1] */
    public VideoStoryAutoPlayHandler(VideoStoryPlaybackProvider playbackProvider) {
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        this.playbackProvider = playbackProvider;
        this.autoPlayViews = new LinkedHashMap();
        this.playbackItems = new ArrayList();
        this.autoPlayMetricsSender = LazyKt__LazyJVMKt.lazy(new Function0<VideoStoryAutoPlayMetricsImpl>() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$autoPlayMetricsSender$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoStoryAutoPlayMetricsImpl invoke() {
                return new VideoStoryAutoPlayMetricsImpl();
            }
        });
        this.recyclerViewListener = new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$recyclerViewListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ThrottleTrackingBus throttleTrackingBus;
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                if (dx == 0 && dy == 0) {
                    return;
                }
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager == null || (throttleTrackingBus = this.this$0.throttleTrackingBus) == null) {
                    return;
                }
                throttleTrackingBus.postViewEvent(new ThrottleTrackingBus.VisibleState(linearLayoutManager.findFirstCompletelyVisibleItemPosition(), linearLayoutManager.findLastCompletelyVisibleItemPosition(), null, 4, null));
            }
        };
        ?? r2 = new AutoPlayMetricsReporter() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$autoPlayMetricsReporter$1
            @Override // com.amazon.mp3.video.stories.AutoPlayMetricsReporter
            public void reportVideoStoryIPDMSMetrics(long ipdms) {
                VideoStoryContent videoStoryContent = (VideoStoryContent) CollectionsKt___CollectionsKt.getOrNull(this.this$0.playbackItems, this.this$0.currentIndex);
                String strAsin = videoStoryContent == null ? null : videoStoryContent.asin();
                if (strAsin == null) {
                    return;
                }
                this.this$0.getAutoPlayMetricsSender().sendIPDMSMetrics(strAsin, ipdms);
            }

            @Override // com.amazon.mp3.video.stories.AutoPlayMetricsReporter
            public void reportVideoStoryContentViewMetrics(EntityRenderingType entityRenderingType) {
                Intrinsics.checkNotNullParameter(entityRenderingType, "entityRenderingType");
                String str = this.this$0.viewId;
                if (str == null) {
                    return;
                }
                this.this$0.getAutoPlayMetricsSender().sendVideoStoryContentViewMetrics(str, entityRenderingType);
            }

            @Override // com.amazon.mp3.video.stories.AutoPlayMetricsReporter
            public void reportVideoStoryAutoPlayDurationMetrics(long autoPlayDuration, boolean currentStoryChanged) {
                VideoStoryContent videoStoryContent = (VideoStoryContent) CollectionsKt___CollectionsKt.getOrNull(this.this$0.playbackItems, currentStoryChanged ? this.this$0.previousIndex : this.this$0.currentIndex);
                String strAsin = videoStoryContent == null ? null : videoStoryContent.asin();
                if (strAsin == null) {
                    return;
                }
                this.this$0.getAutoPlayMetricsSender().sendAutoPlayDurationMetrics(strAsin, autoPlayDuration, (int) (autoPlayDuration / ((long) PathInterpolatorCompat.MAX_NUM_POINTS)));
            }
        };
        this.autoPlayMetricsReporter = r2;
        this.recyclerViewItemsTrackerCallbackListener = new Action1() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoStoryAutoPlayHandler.m1670recyclerViewItemsTrackerCallbackListener$lambda2(this.f$0, (ThrottleTrackingBus.VisibleState) obj);
            }
        };
        this.recyclerViewItemsTrackerErrorListener = new Action1() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoStoryAutoPlayHandler.m1671recyclerViewItemsTrackerErrorListener$lambda3((Throwable) obj);
            }
        };
        this.videoPlayerEventsListener = new VideoPlayer.Callback() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$videoPlayerEventsListener$1
            @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
            public void onContentLoading() {
            }

            @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
            public void onContentReady() {
            }

            @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
            public void onPlaybackFinished() {
            }

            @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
            public void onPlayerError(Exception error) {
            }

            @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
            public void onPlaybackStarted() {
                VideoStoryAutoPlayEventHandler videoStoryAutoPlayEventHandler = this.this$0.autoPlayEventHandler;
                if (videoStoryAutoPlayEventHandler == null) {
                    return;
                }
                videoStoryAutoPlayEventHandler.handleAutoPlayEvent(AutoPlayEvent.PLAYBACK_STARTED);
            }

            @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer.Callback
            public void onPlaybackPositionChanged(int queuePosition, long playbackPosition) {
                VideoPlayer videoPlayer;
                if (playbackPosition < 3000 || (videoPlayer = this.this$0.player) == null) {
                    return;
                }
                videoPlayer.seekPosition(0);
            }
        };
        initPlayer();
        initThrottleTrackingBus();
        this.autoPlayEventHandler = new VideoStoryAutoPlayEventHandler(r2);
    }

    static {
        String simpleName = VideoStoryAutoPlayHandler.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoStoryAutoPlayMetricsImpl getAutoPlayMetricsSender() {
        return (VideoStoryAutoPlayMetricsImpl) this.autoPlayMetricsSender.getValue();
    }

    public final void setPlayQueue(List<? extends VideoStoryContent> playbackItemsList, int startIndex, boolean startAutoPlay) {
        Intrinsics.checkNotNullParameter(playbackItemsList, "playbackItemsList");
        this.playbackItems.clear();
        this.playbackItems.addAll(playbackItemsList);
        this.previousIndex = this.currentIndex;
        this.currentIndex = startIndex;
        prepare$default(this, startAutoPlay, false, 2, null);
    }

    public static /* synthetic */ void prepare$default(VideoStoryAutoPlayHandler videoStoryAutoPlayHandler, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        videoStoryAutoPlayHandler.prepare(z, z2);
    }

    public final void prepare(boolean play, boolean shouldResetSeekPosition) {
        TextureView textureView = this.autoPlayViews.get(Integer.valueOf(this.currentIndex));
        if (textureView == null) {
            return;
        }
        VideoStoryContent videoStoryContent = (VideoStoryContent) CollectionsKt___CollectionsKt.getOrNull(this.playbackItems, this.currentIndex);
        String strUri = videoStoryContent == null ? null : videoStoryContent.uri();
        if (strUri == null) {
            return;
        }
        textureView.setVisibility(0);
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            videoPlayer.setContentView(null);
            videoPlayer.prepareQueueItem(this.currentIndex, strUri);
            videoPlayer.setContentView(textureView);
            videoPlayer.toggleMute(true);
        }
        if (play) {
            play(shouldResetSeekPosition);
        }
    }

    public final void setShovelerViewId(String viewRefId) {
        this.viewId = viewRefId;
    }

    public final void setTextureView(TextureView textureView, int index) {
        Intrinsics.checkNotNullParameter(textureView, "textureView");
        this.autoPlayViews.put(Integer.valueOf(index), textureView);
        if (index == this.currentIndex) {
            prepare$default(this, false, false, 2, null);
        }
    }

    public final void setAutoPlayViews(Map<Integer, TextureView> autoPlayViewsMap) {
        Intrinsics.checkNotNullParameter(autoPlayViewsMap, "autoPlayViewsMap");
        this.autoPlayViews.clear();
        this.autoPlayViews.putAll(autoPlayViewsMap);
    }

    public final void resumePlayback() {
        TextureView textureView = this.autoPlayViews.get(Integer.valueOf(this.currentIndex));
        if (textureView == null) {
            return;
        }
        textureView.setVisibility(0);
        this.playbackProvider.setPlayerCallback(this.videoPlayerEventsListener);
        prepare(true, false);
    }

    public final void pausePlayback() {
        VideoStoryAutoPlayEventHandler videoStoryAutoPlayEventHandler = this.autoPlayEventHandler;
        if (videoStoryAutoPlayEventHandler != null) {
            videoStoryAutoPlayEventHandler.handleAutoPlayEvent(AutoPlayEvent.PAUSE);
        }
        TextureView textureView = this.autoPlayViews.get(Integer.valueOf(this.currentIndex));
        if (textureView != null) {
            textureView.setVisibility(4);
        }
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer == null) {
            return;
        }
        videoPlayer.pause();
    }

    public final void play(boolean shouldResetSeekPosition) {
        VideoStoryAutoPlayEventHandler videoStoryAutoPlayEventHandler = this.autoPlayEventHandler;
        if (videoStoryAutoPlayEventHandler != null) {
            videoStoryAutoPlayEventHandler.handleAutoPlayEvent(AutoPlayEvent.PLAY);
        }
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer == null) {
            return;
        }
        if (shouldResetSeekPosition) {
            videoPlayer.seekPosition(0);
        }
        videoPlayer.play();
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        if (Intrinsics.areEqual(recyclerView, this.recyclerView)) {
            return;
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.recyclerViewListener);
        }
        this.recyclerView = recyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.addOnScrollListener(this.recyclerViewListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: recyclerViewItemsTrackerCallbackListener$lambda-2, reason: not valid java name */
    public static final void m1670recyclerViewItemsTrackerCallbackListener$lambda2(VideoStoryAutoPlayHandler this$0, ThrottleTrackingBus.VisibleState visibleState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1(this$0, visibleState, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: recyclerViewItemsTrackerErrorListener$lambda-3, reason: not valid java name */
    public static final void m1671recyclerViewItemsTrackerErrorListener$lambda3(Throwable th) {
        logger.error(Intrinsics.stringPlus("Error while tracking recycler view items ", th));
    }

    private final void initThrottleTrackingBus() {
        this.throttleTrackingBus = new ThrottleTrackingBus(this.recyclerViewItemsTrackerCallbackListener, this.recyclerViewItemsTrackerErrorListener);
    }

    private final void initPlayer() {
        VideoStoryPlaybackProvider videoStoryPlaybackProvider = this.playbackProvider;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        this.player = videoStoryPlaybackProvider.getPlayer(applicationContext, this.videoPlayerEventsListener);
    }
}
