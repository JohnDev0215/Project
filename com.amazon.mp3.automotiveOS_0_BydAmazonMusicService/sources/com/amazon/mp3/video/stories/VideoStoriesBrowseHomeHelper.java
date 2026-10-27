package com.amazon.mp3.video.stories;

import com.amazon.mp3.video.stories.ThrottleTrackingBus;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.VideoStoryShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.HashMap;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoriesBrowseHomeHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoriesBrowseHomeHelper;", "", "()V", "getVideoStoryShovelerModel", "Lcom/amazon/music/views/library/models/VideoStoryShovelerModel;", "mPageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getVideoStoryShovelerRefId", "", "reportContentVideoStoryContentViewMetrics", "", "mContentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "visibleState", "Lcom/amazon/mp3/video/stories/ThrottleTrackingBus$VisibleState;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStoriesBrowseHomeHelper {
    public static final VideoStoriesBrowseHomeHelper INSTANCE = new VideoStoriesBrowseHomeHelper();

    private VideoStoriesBrowseHomeHelper() {
    }

    public final VideoStoryShovelerModel getVideoStoryShovelerModel(PageGridViewModel mPageModel) {
        if (mPageModel == null) {
            return null;
        }
        return (VideoStoryShovelerModel) mPageModel.getModels().stream().filter(new Predicate() { // from class: com.amazon.mp3.video.stories.VideoStoriesBrowseHomeHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VideoStoriesBrowseHomeHelper.m1666getVideoStoryShovelerModel$lambda0((BaseViewModel) obj);
            }
        }).findAny().orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getVideoStoryShovelerModel$lambda-0, reason: not valid java name */
    public static final boolean m1666getVideoStoryShovelerModel$lambda0(BaseViewModel baseViewModel) {
        Intrinsics.checkNotNullParameter(baseViewModel, "baseViewModel");
        return Intrinsics.areEqual(baseViewModel.getClass().getSimpleName(), VideoStoryShovelerModel.class.getSimpleName());
    }

    public final String getVideoStoryShovelerRefId(PageGridViewModel mPageModel) {
        VideoStoryShovelerModel videoStoryShovelerModel = getVideoStoryShovelerModel(mPageModel);
        if (videoStoryShovelerModel == null) {
            return null;
        }
        return videoStoryShovelerModel.getViewReferenceId();
    }

    public final void reportContentVideoStoryContentViewMetrics(final ContentViewManager mContentViewManager, final PageGridViewModel mPageModel, final ThrottleTrackingBus.VisibleState visibleState) {
        Intrinsics.checkNotNullParameter(mContentViewManager, "mContentViewManager");
        Intrinsics.checkNotNullParameter(mPageModel, "mPageModel");
        Intrinsics.checkNotNullParameter(visibleState, "visibleState");
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.video.stories.VideoStoriesBrowseHomeHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VideoStoriesBrowseHomeHelper.m1667reportContentVideoStoryContentViewMetrics$lambda2(mPageModel, visibleState, mContentViewManager);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reportContentVideoStoryContentViewMetrics$lambda-2, reason: not valid java name */
    public static final void m1667reportContentVideoStoryContentViewMetrics$lambda2(PageGridViewModel mPageModel, ThrottleTrackingBus.VisibleState visibleState, ContentViewManager mContentViewManager) {
        int iIndexOf;
        Intrinsics.checkNotNullParameter(mPageModel, "$mPageModel");
        Intrinsics.checkNotNullParameter(visibleState, "$visibleState");
        Intrinsics.checkNotNullParameter(mContentViewManager, "$mContentViewManager");
        VideoStoryShovelerModel videoStoryShovelerModel = INSTANCE.getVideoStoryShovelerModel(mPageModel);
        if (videoStoryShovelerModel != null && (iIndexOf = mPageModel.getModels().indexOf(videoStoryShovelerModel)) >= visibleState.getFirstCompletelyVisible() && iIndexOf <= visibleState.getLastCompletelyVisible()) {
            HashMap map = new HashMap();
            int size = videoStoryShovelerModel.getModels().size();
            String viewReferenceId = videoStoryShovelerModel.getViewReferenceId();
            if (viewReferenceId == null) {
                viewReferenceId = "";
            }
            map.put("blockRef", viewReferenceId);
            map.put("firstViewableIndex", "0");
            map.put("lastViewableIndex", String.valueOf(size - 1));
            map.put("numItemsAccessible", String.valueOf(size));
            String impressionTime = visibleState.getImpressionTime();
            if (impressionTime != null) {
                map.put("impressionTimestamp", impressionTime);
            }
            mContentViewManager.onItemRendered(videoStoryShovelerModel.getViewReferenceId(), map);
        }
    }
}
