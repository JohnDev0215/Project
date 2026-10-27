package com.amazon.mp3.auto.carmode.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.auto.carmode.OnBitmapChangeListener;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeMiniPlayerViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerViewController;", "Lcom/amazon/mp3/auto/carmode/view/CarModePlayerViewController;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "miniPlayerView", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "onBitmapChangeListener", "Lcom/amazon/mp3/auto/carmode/OnBitmapChangeListener;", "playerNavigationListener", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/auto/carmode/OnBitmapChangeListener;Lcom/amazon/mp3/playback/PlayerNavigationListener;)V", "updatePlayerView", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeMiniPlayerViewController extends CarModePlayerViewController {
    private final CarModeMiniPlayerView miniPlayerView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeMiniPlayerViewController(FragmentActivity activity, CarModeMiniPlayerView miniPlayerView, LifecycleOwner lifecycleOwner, PageType pageType, OnBitmapChangeListener onBitmapChangeListener, PlayerNavigationListener playerNavigationListener) {
        super(activity, miniPlayerView, lifecycleOwner, pageType, playerNavigationListener, 150);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(miniPlayerView, "miniPlayerView");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.miniPlayerView = miniPlayerView;
        if (onBitmapChangeListener != null) {
            miniPlayerView.addOnBitmapChangeListener(onBitmapChangeListener);
        }
    }

    @Override // com.amazon.mp3.auto.carmode.view.CarModePlayerViewController
    public void updatePlayerView() {
        this.miniPlayerView.updatePlayerView(getPlayingContentProvider().getPlayingContentTitle(), getPlayingContentProvider().getPlayingContentSubtitle());
    }
}
