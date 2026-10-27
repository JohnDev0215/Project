package com.amazon.mp3.fragment.contextmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.mp3.playback.fragment.contextmenu.NowPlayingContextMenuAdapter;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: PlayerOverflowMenuMoreSectionFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreSectionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "menuViewModel", "Lcom/amazon/mp3/fragment/contextmenu/OverflowContextMenuViewModel;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "rootView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "currentQualityBadgeTitle", "", "getMenuEntriesForOverflow", "", "Landroid/view/MenuItem;", "isStage", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerOverflowMenuMoreSectionFragment extends Fragment {
    private OverflowContextMenuViewModel menuViewModel;
    private RecyclerView recyclerView;
    private ConstraintLayout rootView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_overflow_menu_more_section, container, false);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
        this.rootView = constraintLayout;
        return constraintLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        this.menuViewModel = (OverflowContextMenuViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(OverflowContextMenuViewModel.class);
        ConstraintLayout constraintLayout = this.rootView;
        OverflowContextMenuViewModel overflowContextMenuViewModel = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            constraintLayout = null;
        }
        View viewFindViewById = constraintLayout.findViewById(R.id.menu_list);
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreSectionFragment$onViewCreated$1$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Intrinsics.checkNotNullParameter(rv, "rv");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Intrinsics.checkNotNullParameter(rv, "rv");
                Intrinsics.checkNotNullParameter(e, "e");
                if (e.getAction() != 2) {
                    return false;
                }
                rv.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById<Re…\n            })\n        }");
        this.recyclerView = recyclerView;
        OverflowContextMenuViewModel overflowContextMenuViewModel2 = this.menuViewModel;
        if (overflowContextMenuViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
        } else {
            overflowContextMenuViewModel = overflowContextMenuViewModel2;
        }
        overflowContextMenuViewModel.subscribeToStyleSheet(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreSectionFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayerOverflowMenuMoreSectionFragment.m1137onViewCreated$lambda2(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m1137onViewCreated$lambda2(PlayerOverflowMenuMoreSectionFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OverflowContextMenuViewModel overflowContextMenuViewModel = this$0.menuViewModel;
        RecyclerView recyclerView = null;
        if (overflowContextMenuViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuViewModel");
            overflowContextMenuViewModel = null;
        }
        Action1<MenuItem> chainAction = overflowContextMenuViewModel.getChainAction();
        if (chainAction == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        NowPlayingContextMenuAdapter nowPlayingContextMenuAdapter = new NowPlayingContextMenuAdapter(chainAction, styleSheet);
        RecyclerView recyclerView2 = this$0.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            recyclerView = recyclerView2;
        }
        recyclerView.setAdapter(nowPlayingContextMenuAdapter);
        nowPlayingContextMenuAdapter.updateMenuEntries(this$0.getMenuEntriesForOverflow());
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x017d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<android.view.MenuItem> getMenuEntriesForOverflow() {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreSectionFragment.getMenuEntriesForOverflow():java.util.List");
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    private final String currentQualityBadgeTitle() {
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        PlaybackAudioQuality currentQuality = KatanaNPSBadgeProvider.getCurrentPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(currentQuality, "currentQuality");
        if (playbackAudioQualityToAudioQuality.isHD(currentQuality)) {
            return getString(R.string.dmusic_nerd_stats_hd_quality);
        }
        if (playbackAudioQualityToAudioQuality.isUHD(currentQuality)) {
            return getString(R.string.dmusic_nerd_stats_ultra_hd_quality);
        }
        if (playbackAudioQualityToAudioQuality.isAtmos(currentQuality)) {
            return getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
        }
        if (playbackAudioQualityToAudioQuality.isRa360(currentQuality)) {
            return getString(R.string.dmusic_nerd_stats_360_quality);
        }
        if (playbackAudioQualityToAudioQuality.isStandard(currentQuality)) {
            return getString(R.string.dmusic_nps_audio_quality_badge_sd_text);
        }
        return null;
    }
}
