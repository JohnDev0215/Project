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
import com.amazon.music.views.library.models.BaseDetailHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RA360DetailBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0014J\b\u0010\u0006\u001a\u00020\u0004H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014J\n\u0010\r\u001a\u0004\u0018\u00010\fH\u0014R$\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/RA360DetailBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/DetailBrushFragment;", "Landroid/view/View;", "createActionBarView", "", "initActionBarView", "createBrushRootView", "onStart", "onStop", "initDefaultViews", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "", "getBackgroundImageUrl", "actionBarView", "Landroid/view/View;", "getActionBarView", "()Landroid/view/View;", "setActionBarView", "(Landroid/view/View;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RA360DetailBrushFragment extends DetailBrushFragment {
    private View actionBarView;

    public final View getActionBarView() {
        return this.actionBarView;
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

    private final View createActionBarView() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return new BauhausActionBarView(activity, null, null, null, null, null, 62, null);
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

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initDefaultViews() {
        super.initDefaultViews();
        FragmentActivity activity = getActivity();
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity == null) {
            return;
        }
        navigationActivity.setHeaderView(getActionBarView());
        navigationActivity.requestHomeButtonAsBack();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_RA360;
    }

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
            if (((BaseViewModel) next) instanceof BaseDetailHeaderModel) {
                break;
            }
        }
        BaseDetailHeaderModel baseDetailHeaderModel = (BaseDetailHeaderModel) next;
        if (baseDetailHeaderModel == null) {
            return null;
        }
        return baseDetailHeaderModel.getImageUrl();
    }
}
