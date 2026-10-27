package com.amazon.mp3.playback.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NowPlayingMiniPlayerViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/playback/view/NowPlayingMiniPlayerViewController;", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "playerView", "Lcom/amazon/mp3/playback/view/PlayerView;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "triggerSource", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController$MiniPLayerSource;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/playback/view/PlayerView;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/mp3/playback/view/MiniPlayerViewController$MiniPLayerSource;)V", "dismiss", "", "onForwardClicked", "onPlayerClicked", "onRewindClicked", "onSwipeDown", "onSwipeUp", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NowPlayingMiniPlayerViewController extends MiniPlayerViewController {
    private final FragmentActivity activity;

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onForwardClicked() {
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onRewindClicked() {
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.PlayerVerticalSwipeListener
    public void onSwipeUp() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NowPlayingMiniPlayerViewController(FragmentActivity activity, PlayerView playerView, LifecycleOwner lifecycleOwner, MiniPlayerViewController.MiniPLayerSource triggerSource) {
        super(activity, playerView, lifecycleOwner, triggerSource);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(triggerSource, "triggerSource");
        this.activity = activity;
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.PlayerVerticalSwipeListener
    public void onSwipeDown() {
        dismiss();
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onPlayerClicked() {
        dismiss();
    }

    public final void dismiss() {
        PageType pageType = getPageType();
        if (pageType != null) {
            sendUiClickMetric(pageType, "dismiss", InteractionType.TAP);
        }
        this.activity.onBackPressed();
    }
}
