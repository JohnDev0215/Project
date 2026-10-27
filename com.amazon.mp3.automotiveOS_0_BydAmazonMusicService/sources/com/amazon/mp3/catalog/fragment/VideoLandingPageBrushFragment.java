package com.amazon.mp3.catalog.fragment;

import android.view.View;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.models.GenreDetailHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoLandingPageBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\u0007H\u0014J\b\u0010\r\u001a\u00020\u0007H\u0014J\b\u0010\u000e\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/VideoLandingPageBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/DetailBrushFragment;", "()V", "actionBarView", "Landroid/view/View;", "createActionBarView", "createBrushRootView", "", "getBackgroundImageUrl", "", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "initActionBarView", "initDefaultViews", "onDestroyView", "onStart", "onStop", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoLandingPageBrushFragment extends DetailBrushFragment {
    private View actionBarView = this.mActionBarView;

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment
    protected String getBackgroundImageUrl() {
        Object next;
        Iterator<T> it = this.mPageModel.getModels().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((BaseViewModel) next) instanceof GenreDetailHeaderModel) {
                break;
            }
        }
        GenreDetailHeaderModel genreDetailHeaderModel = (GenreDetailHeaderModel) next;
        if (genreDetailHeaderModel == null) {
            return null;
        }
        return genreDetailHeaderModel.getImageUrl();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_VIDEO;
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        if (this.actionBarView == null) {
            this.actionBarView = createActionBarView();
        }
        View view = this.actionBarView;
        BauhausActionBarView bauhausActionBarView = view instanceof BauhausActionBarView ? (BauhausActionBarView) view : null;
        if (bauhausActionBarView == null) {
            return;
        }
        setActionBarOnScrollListener(bauhausActionBarView.getOnScrollListener());
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment
    protected void createBrushRootView() {
        Set<Integer> of = isGridListHeaderViewPresent() ? SetsKt__SetsJVMKt.setOf(0) : null;
        BrushViews brushViews = this.brushViews;
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        List<BaseViewModel> models = this.mPageModel.getModels();
        int tileType = this.mPageModel.getTileType();
        RecyclerView mRecyclerView = this.mRecyclerView;
        Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
        brushViews.createRootView(userSubscription, models, tileType, mRecyclerView, this.swipeRefreshLayout, of);
        setBrushViewsTopOffsetIfNeed();
        setupLayoutAndScrollPosition();
        if (this.mPageModel.getNextPageToken() == null) {
            bindBackgroundImageToFirstItem();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setActionBarOverlay(true);
    }

    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment, com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setActionBarOverlay(false);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setActionBarOverlay(false);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initDefaultViews() {
        super.initDefaultViews();
        FragmentActivity activity = getActivity();
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity == null) {
            return;
        }
        navigationActivity.setHeaderView(this.actionBarView);
        navigationActivity.requestHomeButtonAsBack();
    }

    private final View createActionBarView() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return new BauhausActionBarView(activity, null, null, null, null, null, 62, null);
    }
}
