package com.amazon.mp3.auto.carmode.fragment;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.PlayingMediaContentProvider;
import com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView;
import com.amazon.mp3.auto.carmode.view.CarModeNowPlayingViewController;
import com.amazon.mp3.auto.carmode.view.CarModePlayerViewController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.PodcastMediaItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeNowPlayingFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\u0012\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeNowPlayingFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "carModeNowPlayingView", "Lcom/amazon/mp3/auto/carmode/view/CarModeNowPlayingView;", "lastMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playerViewController", "Lcom/amazon/mp3/auto/carmode/view/CarModeNowPlayingViewController;", "playingMediaContentProvider", "Lcom/amazon/mp3/auto/carmode/PlayingMediaContentProvider;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onPlayStateChanged", "onPodcastMediaMetadataChange", "metadata", "Lcom/amazon/podcast/external/car/PodcastMediaMetadata;", "onViewCreated", "view", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeNowPlayingFragment extends CarModeBaseFragment implements Media.PodcastMediaMetadataCallback, OnPlayStateChangedListener {
    private final String TAG = CarModeNowPlayingFragment.class.getSimpleName();
    private CarModeNowPlayingView carModeNowPlayingView;
    private MediaItem lastMediaItem;
    private final ActionValidatedPlaybackController playbackController;
    private CarModeNowPlayingViewController playerViewController;
    private final PlayingMediaContentProvider playingMediaContentProvider;

    public CarModeNowPlayingFragment() {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        this.playingMediaContentProvider = new PlayingMediaContentProvider(controller);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.car_mode_now_playing_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_now_playing);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.car_mode_now_playing)");
        this.carModeNowPlayingView = (CarModeNowPlayingView) viewFindViewById;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CarModeNowPlayingViewController carModeNowPlayingViewController = this.playerViewController;
        if (carModeNowPlayingViewController != null) {
            carModeNowPlayingViewController.clear();
        }
        CarModeNowPlayingView carModeNowPlayingView = this.carModeNowPlayingView;
        if (carModeNowPlayingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeNowPlayingView");
            carModeNowPlayingView = null;
        }
        carModeNowPlayingView.clear();
        this.playbackController.removeOnPlayStateChangedListener(this);
        CarModePresetsUtility.INSTANCE.removePodcastMediaMetadataCallback(this);
        this.lastMediaItem = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        CarModeNowPlayingView carModeNowPlayingView = this.carModeNowPlayingView;
        CarModeNowPlayingView carModeNowPlayingView2 = null;
        if (carModeNowPlayingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeNowPlayingView");
            carModeNowPlayingView = null;
        }
        carModeNowPlayingView.setEnablePlayQueue(AmazonApplication.getCapabilities().isCarModePlayQueueEnabled());
        CarModeNowPlayingView carModeNowPlayingView3 = this.carModeNowPlayingView;
        if (carModeNowPlayingView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeNowPlayingView");
        } else {
            carModeNowPlayingView2 = carModeNowPlayingView3;
        }
        carModeNowPlayingView2.setEnablePodcastEpisodeList(AmazonApplication.getCapabilities().isCarModePodcastEpisodeListEnabled());
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeNowPlayingFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeNowPlayingFragment.m287onViewCreated$lambda0(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-0, reason: not valid java name */
    public static final void m287onViewCreated$lambda0(CarModeNowPlayingFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CarModeNowPlayingView carModeNowPlayingView = this$0.carModeNowPlayingView;
        if (carModeNowPlayingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeNowPlayingView");
            carModeNowPlayingView = null;
        }
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        carModeNowPlayingView.setStyleSheet(styleSheet);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PageType pageType = PageType.CAR_MODE_NOW_PLAYING;
        AutoUiEventUtil.sendUiPageViewEvent(pageType, ExperienceMode.CAR_MODE);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            CarModeNowPlayingView carModeNowPlayingView = this.carModeNowPlayingView;
            if (carModeNowPlayingView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeNowPlayingView");
                carModeNowPlayingView = null;
            }
            CarModeNowPlayingViewController carModeNowPlayingViewController = new CarModeNowPlayingViewController(activity, carModeNowPlayingView, this, pageType, new PlayerNavigationListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeNowPlayingFragment$onActivityCreated$1$1
                @Override // com.amazon.mp3.playback.PlayerNavigationListener
                public void addTrackToLibrary() {
                }

                @Override // com.amazon.mp3.playback.PlayerNavigationListener
                public void onCreateContextMenu(ContextMenu menu) {
                }

                @Override // com.amazon.mp3.playback.PlayerNavigationListener
                public void showCastingView() {
                }

                @Override // com.amazon.mp3.playback.PlayerNavigationListener
                public void showNowPlaying(InteractionType type) {
                }

                @Override // com.amazon.mp3.playback.PlayerNavigationListener
                public void showPlayQueue() {
                    CarModeNavigation navigationListener = this.this$0.getNavigationListener();
                    if (navigationListener == null) {
                        return;
                    }
                    CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_PLAY_QUEUE_PAGE, null, 2, null);
                }

                @Override // com.amazon.mp3.playback.PlayerNavigationListener
                public void showPodcastEpisodeList() {
                    Bundle bundle = new Bundle();
                    bundle.putString("PODCAST_SHOW_ID", this.this$0.playingMediaContentProvider.getPlayingContentSourceInfo());
                    MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.CAR_MODE_PODCAST_EPISODE_LIST);
                    CarModeNavigation navigationListener = this.this$0.getNavigationListener();
                    if (navigationListener == null) {
                        return;
                    }
                    navigationListener.navigateTo(CarModeAction.SHOW_PODCAST_EPISODE_LIST_PAGE, bundle);
                }
            });
            carModeNowPlayingViewController.refreshPlayer();
            this.playerViewController = carModeNowPlayingViewController;
        }
        if (AmazonApplication.getCapabilities().isPodcastHarleyEnabled()) {
            return;
        }
        if (LibraryEqualizerStateManager.INSTANCE.isPodcastContentPlaying()) {
            onPodcastMediaMetadataChange(CarModePresetsUtility.INSTANCE.getPodcastMediaMetadata());
        }
        this.playbackController.addOnPlayStateChangedListener(this);
        CarModePresetsUtility.INSTANCE.addPodcastMediaMetadataCallback(this);
    }

    @Override // com.amazon.podcast.media.playback.Media.PodcastMediaMetadataCallback
    public void onPodcastMediaMetadataChange(PodcastMediaMetadata metadata) {
        if (metadata != null) {
            CarModeNowPlayingViewController carModeNowPlayingViewController = this.playerViewController;
            if (carModeNowPlayingViewController != null) {
                CarModePlayerViewController.refreshPlayerView$default(carModeNowPlayingViewController, null, true, 1, null);
            }
            CarModeNowPlayingViewController carModeNowPlayingViewController2 = this.playerViewController;
            if (carModeNowPlayingViewController2 == null) {
                return;
            }
            carModeNowPlayingViewController2.updatePlayerView();
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || (currentMediaItem instanceof PodcastMediaItem) || Intrinsics.areEqual(currentMediaItem, this.lastMediaItem)) {
            return;
        }
        this.lastMediaItem = currentMediaItem;
        CarModeNowPlayingViewController carModeNowPlayingViewController = this.playerViewController;
        if (carModeNowPlayingViewController != null) {
            CarModePlayerViewController.refreshPlayerView$default(carModeNowPlayingViewController, currentMediaItem, false, 2, null);
        }
        CarModeNowPlayingViewController carModeNowPlayingViewController2 = this.playerViewController;
        if (carModeNowPlayingViewController2 == null) {
            return;
        }
        carModeNowPlayingViewController2.refreshPlayer();
    }
}
