package com.amazon.mp3.catalog.fragment;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MusicHomeBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/MusicHomeBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/BrushFragment;", "()V", "actionBarView", "Landroid/view/View;", "getActionBarViewForMusicHome", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "initActionBarView", "", "initDefaultViews", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicHomeBrushFragment extends BrushFragment {
    private static final String TAG = MusicHomeBrushFragment.class.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(MusicHomeBrushFragment.class.getName());
    private View actionBarView = this.mActionBarView;

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_HOME;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initDefaultViews() {
        initActionBarView();
        if (!Intrinsics.areEqual("home-music", this.mPageUri)) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
            }
            ((MusicHomeActivity) activity).setHeaderView(this.mActionBarView);
            FragmentActivity activity2 = getActivity();
            if (activity2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
            }
            ((MusicHomeActivity) activity2).requestHomeButtonAsBack();
            return;
        }
        FragmentActivity activity3 = getActivity();
        if (activity3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
        }
        ((MusicHomeActivity) activity3).restoreHomeButton();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        if (Intrinsics.areEqual("home-music", this.mPageUri)) {
            View actionBarViewForMusicHome = getActionBarViewForMusicHome();
            if (actionBarViewForMusicHome instanceof BauhausActionBarView) {
                setActionBarOnScrollListener(((BauhausActionBarView) actionBarViewForMusicHome).getOnScrollListener());
                FragmentActivity activity = getActivity();
                if (activity == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
                }
                ((MusicHomeActivity) activity).setHeaderView(actionBarViewForMusicHome);
                FragmentActivity activity2 = getActivity();
                if (activity2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
                }
                ((MusicHomeActivity) activity2).requestHomeButtonAsBack();
                return;
            }
            return;
        }
        String str = this.mStandaloneTitle;
        if (str == null) {
            str = this.mToolbarTitle;
        }
        PageGridViewModel pageGridViewModel = this.mPageModel;
        String subTitle = pageGridViewModel != null ? pageGridViewModel.getSubTitle() : null;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView == null) {
            this.mActionBarView = new ActionBarView(getActivity(), str, subTitle);
        } else {
            actionBarView.setTitles(str, subTitle);
        }
    }

    private final View getActionBarViewForMusicHome() {
        if (this.actionBarView == null) {
            this.actionBarView = new BauhausActionBarView(getActivity(), getResources().getString(R.string.dmusic_music_home_title));
        }
        return this.actionBarView;
    }
}
