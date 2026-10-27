package com.amazon.mp3.auto.carmode.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.podcast.Podcast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeNowPlayingViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/auto/carmode/view/CarModeNowPlayingViewController;", "Lcom/amazon/mp3/auto/carmode/view/CarModePlayerViewController;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "nowPlayingView", "Lcom/amazon/mp3/auto/carmode/view/CarModeNowPlayingView;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playerNavigationListener", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/auto/carmode/view/CarModeNowPlayingView;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/playback/PlayerNavigationListener;)V", "onRewindClicked", "", "onThumbsUpClicked", "toggleLikeState", "updatePlayerView", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeNowPlayingViewController extends CarModePlayerViewController {
    private static final String TAG = CarModeNowPlayingViewController.class.getSimpleName();
    private final CarModeNowPlayingView nowPlayingView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeNowPlayingViewController(FragmentActivity activity, CarModeNowPlayingView nowPlayingView, LifecycleOwner lifecycleOwner, PageType pageType, PlayerNavigationListener playerNavigationListener) {
        super(activity, nowPlayingView, lifecycleOwner, pageType, playerNavigationListener, 450);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(nowPlayingView, "nowPlayingView");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.nowPlayingView = nowPlayingView;
    }

    @Override // com.amazon.mp3.auto.carmode.view.CarModePlayerViewController
    public void updatePlayerView() {
        this.nowPlayingView.updatePlayerView(getPlayingContentProvider().getPlayingContentTitle(), getPlayingContentProvider().getPlayingContentSubtitle());
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onThumbsUpClicked() {
        toggleLikeState();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void toggleLikeState() {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            Log.error(TAG, "current media item is null, can't update like state");
            return;
        }
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            Log.error(TAG, "no internet connection, can't update like state");
            return;
        }
        LikesServiceProxy.Companion companion = LikesServiceProxy.INSTANCE;
        ActionValidatedPlaybackController mPlaybackController = this.mPlaybackController;
        Intrinsics.checkNotNullExpressionValue(mPlaybackController, "mPlaybackController");
        boolean z = LikesServiceProxy.Companion.toggleLikeStatus$default(companion, currentMediaItem, mPlaybackController, null, 4, null);
        this.mPlayerView.ratingChanged(z ? 1 : 0);
        if (z) {
            sendUiClickEvent(ActionType.THUMBS_UP);
        } else {
            sendUiClickEvent(ActionType.THUMBS_UP_UNDO);
        }
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onRewindClicked() {
        if (isPodcastPlayerView()) {
            sendUiClickEvent(ActionType.REWIND);
            Podcast.getPlayback().rewind();
        }
    }
}
