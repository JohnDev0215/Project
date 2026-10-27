package com.amazon.mp3.video.stories;

import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.VideoStoryContent;
import PlaybackMetricsInterface.v1_0.PlaybackSignalType;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import UpsellInterface.v1_0.PlaybackMode;
import android.view.TextureView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.video.stories.ThrottleTrackingBus;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.VideoStoryShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.VideoStoryAutoPlayViewProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryAutoplayCoordinator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 L2\u00020\u0001:\u0002MLB\u0007¢\u0006\u0004\bJ\u0010KJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u001a\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nJ\u0010\u0010\u0010\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0015J\u0010\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0015J\u0018\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0011H\u0016R(\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R.\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001a0,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u0010:\u001a\u0004\u0018\u0001098\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0018\u0010@\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\"\u0010B\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010I¨\u0006N"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryAutoplayCoordinator;", "Lcom/amazon/music/views/library/providers/VideoStoryAutoPlayViewProvider;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "mPageModel", "", "getVideoStoryShovelerPosition", "Lcom/amazon/mp3/video/stories/ThrottleTrackingBus$VisibleState;", "visibleState", "", "isVideoStoryShovelerInFocus", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "", "initialize", "", "viewRefId", "setShovelerViewId", "Landroidx/recyclerview/widget/RecyclerView;", "mRecyclerView", "playStoryIfStoryShovelerInFocus", "handleVisibleStateChange", "Lcom/amazon/mp3/video/stories/VideoStoryAutoplayCoordinator$AutoplayStateChangeReason;", "stateChangeReason", "resumeAutoPlay", "pauseAutoPlay", "position", "Landroid/view/TextureView;", "textureView", "setTextureView", "recyclerView", "setRecyclerView", "", "LExternalActionInterface/v1_0/VideoStoryContent;", "playbackItems", "Ljava/util/List;", "getPlaybackItems", "()Ljava/util/List;", "setPlaybackItems", "(Ljava/util/List;)V", "Landroidx/recyclerview/widget/RecyclerView;", "getMRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setMRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "", "textureViews", "Ljava/util/Map;", "getTextureViews", "()Ljava/util/Map;", "setTextureViews", "(Ljava/util/Map;)V", "currentIndex", "I", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler;", "videoStoryAutoPlayHandler", "Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler;", "getVideoStoryAutoPlayHandler", "()Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler;", "setVideoStoryAutoPlayHandler", "(Lcom/amazon/mp3/video/stories/VideoStoryAutoPlayHandler;)V", "shovelerViewRefId", "Ljava/lang/String;", "shouldPlay", "Z", "getShouldPlay", "()Z", "setShouldPlay", "(Z)V", "playStateChangedReason", "Lcom/amazon/mp3/video/stories/VideoStoryAutoplayCoordinator$AutoplayStateChangeReason;", "<init>", "()V", "Companion", "AutoplayStateChangeReason", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryAutoplayCoordinator implements VideoStoryAutoPlayViewProvider {
    private static final String TAG = VideoStoryAutoplayCoordinator.class.getSimpleName();
    private int currentIndex;
    private RecyclerView mRecyclerView;
    private boolean shouldPlay;
    private String shovelerViewRefId;
    private VideoStoryAutoPlayHandler videoStoryAutoPlayHandler;
    private List<? extends VideoStoryContent> playbackItems = new ArrayList();
    private Map<Integer, TextureView> textureViews = new LinkedHashMap();
    private AutoplayStateChangeReason playStateChangedReason = AutoplayStateChangeReason.NONE;

    /* JADX INFO: compiled from: VideoStoryAutoplayCoordinator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryAutoplayCoordinator$AutoplayStateChangeReason;", "", "(Ljava/lang/String;I)V", PlaybackSignalType.PLAYBACK_STARTED, "PLAYBACK_ENDED", PlaybackMode.NONE, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum AutoplayStateChangeReason {
        PLAYBACK_STARTED,
        PLAYBACK_ENDED,
        NONE
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public final VideoStoryAutoPlayHandler getVideoStoryAutoPlayHandler() {
        return this.videoStoryAutoPlayHandler;
    }

    public final void initialize(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.videoStoryAutoPlayHandler = new VideoStoryAutoPlayHandler(VideoStoryPlaybackProvider.INSTANCE);
        VideoStoryPlaybackDataRepository.INSTANCE.get().getPlaybackTemplates().observe(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.video.stories.VideoStoryAutoplayCoordinator$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoStoryAutoplayCoordinator.m1672initialize$lambda1(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initialize$lambda-1, reason: not valid java name */
    public static final void m1672initialize$lambda1(VideoStoryAutoplayCoordinator this$0, List playbackTemplates) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(playbackTemplates, "playbackTemplates");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(playbackTemplates, 10));
        Iterator it = playbackTemplates.iterator();
        while (it.hasNext()) {
            PlayableContent playableContent = ((PlaybackTemplate) it.next()).playableContent();
            if (playableContent == null) {
                throw new NullPointerException("null cannot be cast to non-null type ExternalActionInterface.v1_0.VideoStoryContent");
            }
            arrayList.add((VideoStoryContent) playableContent);
        }
        this$0.playbackItems = arrayList;
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = this$0.videoStoryAutoPlayHandler;
        if (videoStoryAutoPlayHandler != null) {
            videoStoryAutoPlayHandler.setAutoPlayViews(this$0.textureViews);
        }
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler2 = this$0.videoStoryAutoPlayHandler;
        if (videoStoryAutoPlayHandler2 == null) {
            return;
        }
        videoStoryAutoPlayHandler2.setPlayQueue(this$0.playbackItems, this$0.currentIndex, this$0.shouldPlay);
    }

    public final void setShovelerViewId(String viewRefId) {
        if (viewRefId == null) {
            return;
        }
        this.shovelerViewRefId = viewRefId;
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = getVideoStoryAutoPlayHandler();
        if (videoStoryAutoPlayHandler == null) {
            return;
        }
        videoStoryAutoPlayHandler.setShovelerViewId(viewRefId);
    }

    public final void playStoryIfStoryShovelerInFocus(RecyclerView mRecyclerView, PageGridViewModel mPageModel) {
        Intrinsics.checkNotNullParameter(mRecyclerView, "mRecyclerView");
        int videoStoryShovelerPosition = getVideoStoryShovelerPosition(mPageModel);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (linearLayoutManager != null && videoStoryShovelerPosition >= linearLayoutManager.findFirstCompletelyVisibleItemPosition() && videoStoryShovelerPosition <= linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
            resumeAutoPlay$default(this, null, 1, null);
        }
    }

    public final void handleVisibleStateChange(ThrottleTrackingBus.VisibleState visibleState, PageGridViewModel mPageModel) {
        Intrinsics.checkNotNullParameter(visibleState, "visibleState");
        if (isVideoStoryShovelerInFocus(visibleState, mPageModel)) {
            resumeAutoPlay$default(this, null, 1, null);
        } else {
            pauseAutoPlay$default(this, null, 1, null);
        }
    }

    public static /* synthetic */ void resumeAutoPlay$default(VideoStoryAutoplayCoordinator videoStoryAutoplayCoordinator, AutoplayStateChangeReason autoplayStateChangeReason, int i, Object obj) {
        if ((i & 1) != 0) {
            autoplayStateChangeReason = AutoplayStateChangeReason.NONE;
        }
        videoStoryAutoplayCoordinator.resumeAutoPlay(autoplayStateChangeReason);
    }

    public final void resumeAutoPlay(AutoplayStateChangeReason stateChangeReason) {
        Intrinsics.checkNotNullParameter(stateChangeReason, "stateChangeReason");
        if (stateChangeReason == AutoplayStateChangeReason.NONE && this.playStateChangedReason == AutoplayStateChangeReason.PLAYBACK_STARTED) {
            return;
        }
        this.playStateChangedReason = stateChangeReason;
        this.shouldPlay = true;
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = this.videoStoryAutoPlayHandler;
        if (videoStoryAutoPlayHandler == null) {
            return;
        }
        videoStoryAutoPlayHandler.resumePlayback();
    }

    public static /* synthetic */ void pauseAutoPlay$default(VideoStoryAutoplayCoordinator videoStoryAutoplayCoordinator, AutoplayStateChangeReason autoplayStateChangeReason, int i, Object obj) {
        if ((i & 1) != 0) {
            autoplayStateChangeReason = AutoplayStateChangeReason.NONE;
        }
        videoStoryAutoplayCoordinator.pauseAutoPlay(autoplayStateChangeReason);
    }

    public final void pauseAutoPlay(AutoplayStateChangeReason stateChangeReason) {
        Intrinsics.checkNotNullParameter(stateChangeReason, "stateChangeReason");
        if (this.playStateChangedReason != AutoplayStateChangeReason.PLAYBACK_STARTED) {
            this.playStateChangedReason = stateChangeReason;
        }
        this.shouldPlay = false;
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = this.videoStoryAutoPlayHandler;
        if (videoStoryAutoPlayHandler == null) {
            return;
        }
        videoStoryAutoPlayHandler.pausePlayback();
    }

    @Override // com.amazon.music.views.library.providers.VideoStoryAutoPlayViewProvider
    public void setTextureView(int position, TextureView textureView) {
        Intrinsics.checkNotNullParameter(textureView, "textureView");
        this.textureViews.put(Integer.valueOf(position), textureView);
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = this.videoStoryAutoPlayHandler;
        if (videoStoryAutoPlayHandler == null) {
            return;
        }
        videoStoryAutoPlayHandler.setTextureView(textureView, position);
    }

    @Override // com.amazon.music.views.library.providers.VideoStoryAutoPlayViewProvider
    public void setRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this.mRecyclerView = recyclerView;
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = this.videoStoryAutoPlayHandler;
        if (videoStoryAutoPlayHandler == null) {
            return;
        }
        videoStoryAutoPlayHandler.setRecyclerView(recyclerView);
    }

    private final int getVideoStoryShovelerPosition(PageGridViewModel mPageModel) {
        List<BaseViewModel> models;
        VideoStoryShovelerModel videoStoryShovelerModel = VideoStoriesBrowseHomeHelper.INSTANCE.getVideoStoryShovelerModel(mPageModel);
        if (videoStoryShovelerModel == null || mPageModel == null || (models = mPageModel.getModels()) == null) {
            return -1;
        }
        return models.indexOf(videoStoryShovelerModel);
    }

    private final boolean isVideoStoryShovelerInFocus(ThrottleTrackingBus.VisibleState visibleState, PageGridViewModel mPageModel) {
        int videoStoryShovelerPosition = getVideoStoryShovelerPosition(mPageModel);
        return videoStoryShovelerPosition >= visibleState.getFirstCompletelyVisible() && videoStoryShovelerPosition <= visibleState.getLastCompletelyVisible();
    }
}
