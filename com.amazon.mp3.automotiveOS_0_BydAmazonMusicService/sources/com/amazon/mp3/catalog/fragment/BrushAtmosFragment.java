package com.amazon.mp3.catalog.fragment;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;

/* JADX INFO: compiled from: BrushAtmosFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/BrushAtmosFragment;", "Lcom/amazon/mp3/catalog/fragment/BrushFragment;", "()V", "actionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "getActionBarView", "()Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "setActionBarView", "(Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;)V", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "initActionBarView", "", "initDefaultViews", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushAtmosFragment extends BrushFragment {
    private BauhausActionBarView actionBarView;

    public final BauhausActionBarView getActionBarView() {
        return this.actionBarView;
    }

    public final void setActionBarView(BauhausActionBarView bauhausActionBarView) {
        this.actionBarView = bauhausActionBarView;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        if (this.actionBarView == null) {
            setActionBarView(new BauhausActionBarView(getActivity(), null, null, null, null, null, 62, null));
        }
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
        return PageType.BROWSE_ATMOS;
    }
}
