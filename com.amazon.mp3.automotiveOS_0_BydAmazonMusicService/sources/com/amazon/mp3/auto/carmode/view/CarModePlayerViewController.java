package com.amazon.mp3.auto.carmode.view;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.PlayingMediaContentProvider;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.mp3.playback.view.PlayerView;
import com.amazon.mp3.playback.view.PlayerViewConfig;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.config.MediaItemImageManager;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModePlayerViewController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010:\u001a\u000209\u0012\u0006\u0010<\u001a\u00020;\u0012\u0006\u0010>\u001a\u00020=\u0012\u0006\u0010!\u001a\u00020\u001b\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?\u0012\u0006\u0010B\u001a\u00020A¢\u0006\u0004\bC\u0010DJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0016J\u0012\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\f\u001a\u00020\u0002H\u0016J\u001c\u0010\u0010\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\tJ\b\u0010\u0011\u001a\u00020\tH\u0004J\b\u0010\u0012\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u0002H&J\u0016\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00150\u0014H\u0016J\b\u0010\u0018\u001a\u00020\tH\u0014J\b\u0010\u001a\u001a\u00020\u0019H\u0014J\b\u0010\u001c\u001a\u00020\u001bH\u0014J\b\u0010\u001d\u001a\u00020\u0002H\u0016J\u000e\u0010 \u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u001eR\u0014\u0010!\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\"\u0010$\u001a\u00020#8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010,\u001a\n +*\u0004\u0018\u00010*0*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u0010\u000f\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108¨\u0006E"}, d2 = {"Lcom/amazon/mp3/auto/carmode/view/CarModePlayerViewController;", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", "", "attachPodcastListeners", "setPodcastArtwork", "setPodcastPlayer", "onPlayPauseClicked", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "onPrevClicked", "onNextClicked", "onForwardClicked", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "isPodcast", "refreshPlayerView", "isPodcastPlayerView", "refreshPlayer", "updatePlayerView", "Landroidx/lifecycle/Observer;", "", "Lcom/amazon/mp3/playback/PlayerViewModel$PlayerContentViewModel;", "getPlayerContentViewModelsObserver", "skipObserverUpdate", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "getCurrentExperienceMode", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getCurrentPageType", "clear", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "sendUiClickEvent", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/mp3/auto/carmode/PlayingMediaContentProvider;", "playingContentProvider", "Lcom/amazon/mp3/auto/carmode/PlayingMediaContentProvider;", "getPlayingContentProvider", "()Lcom/amazon/mp3/auto/carmode/PlayingMediaContentProvider;", "setPlayingContentProvider", "(Lcom/amazon/mp3/auto/carmode/PlayingMediaContentProvider;)V", "Lcom/amazon/mp3/playback/view/PlayerViewConfig;", "kotlin.jvm.PlatformType", "podcastPlayerViewConfig", "Lcom/amazon/mp3/playback/view/PlayerViewConfig;", "isPodcastHarleyEnabled", "Z", "Lcom/amazon/music/media/playback/config/MediaItemImageManager;", "podcastImageManager", "Lcom/amazon/music/media/playback/config/MediaItemImageManager;", "Lcom/amazon/music/media/playback/config/MediaItemImageManager$OnBitmapChangedListener;", "onPodcastBitmapChangedListener", "Lcom/amazon/music/media/playback/config/MediaItemImageManager$OnBitmapChangedListener;", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "podcastMediaMetadataCallback", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/playback/view/PlayerView;", "playerView", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "playerNavigationListener", "", "artworkSize", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/playback/view/PlayerView;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/playback/PlayerNavigationListener;I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class CarModePlayerViewController extends MiniPlayerViewController {
    private boolean isPodcast;
    private boolean isPodcastHarleyEnabled;
    private final MediaItemImageManager.OnBitmapChangedListener onPodcastBitmapChangedListener;
    private final PageType pageType;
    private PlayingMediaContentProvider playingContentProvider;
    private final MediaItemImageManager podcastImageManager;
    private final Media.PodcastMediaMetadataCallback podcastMediaMetadataCallback;
    private final PlayerViewConfig podcastPlayerViewConfig;

    public abstract void updatePlayerView();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModePlayerViewController(FragmentActivity activity, PlayerView playerView, LifecycleOwner lifecycleOwner, PageType pageType, PlayerNavigationListener playerNavigationListener, int i) {
        super(activity, playerView, lifecycleOwner, MiniPlayerViewController.MiniPLayerSource.CAR_MODE);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playerView, "playerView");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.pageType = pageType;
        ActionValidatedPlaybackController mPlaybackController = this.mPlaybackController;
        Intrinsics.checkNotNullExpressionValue(mPlaybackController, "mPlaybackController");
        this.playingContentProvider = new PlayingMediaContentProvider(mPlaybackController);
        this.podcastPlayerViewConfig = PlayerViewConfig.createPlayerViewConfig(PlayerViewConfig.PlayerViewContentType.PODCAST);
        this.isPodcastHarleyEnabled = AmazonApplication.getCapabilities().isPodcastHarleyEnabled();
        this.podcastImageManager = new MediaItemImageManager(this.mPlaybackController.getPlayback().getPlaybackConfig(), i, i, new MediaItem.ImageType[0]);
        this.onPodcastBitmapChangedListener = new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModePlayerViewController$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public final void onBitmapChanged(MediaItemImageManager mediaItemImageManager) {
                CarModePlayerViewController.m351onPodcastBitmapChangedListener$lambda1(this.f$0, mediaItemImageManager);
            }
        };
        this.podcastMediaMetadataCallback = new Media.PodcastMediaMetadataCallback() { // from class: com.amazon.mp3.auto.carmode.view.CarModePlayerViewController$$ExternalSyntheticLambda1
            @Override // com.amazon.podcast.media.playback.Media.PodcastMediaMetadataCallback
            public final void onPodcastMediaMetadataChange(PodcastMediaMetadata podcastMediaMetadata) {
                CarModePlayerViewController.m352podcastMediaMetadataCallback$lambda4(this.f$0, podcastMediaMetadata);
            }
        };
        if (playerNavigationListener != null) {
            this.mPlayerView.addPlayerNavigationListener(playerNavigationListener);
        }
        attachPodcastListeners();
    }

    protected final PlayingMediaContentProvider getPlayingContentProvider() {
        return this.playingContentProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPodcastBitmapChangedListener$lambda-1, reason: not valid java name */
    public static final void m351onPodcastBitmapChangedListener$lambda1(CarModePlayerViewController this$0, MediaItemImageManager mediaItemImageManager) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (mediaItemImageManager != null && (bitmap = mediaItemImageManager.getBitmap()) != null) {
            this$0.mPlayerView.setCurrentBitmap(bitmap);
        }
        if (mediaItemImageManager == null) {
            return;
        }
        mediaItemImageManager.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: podcastMediaMetadataCallback$lambda-4, reason: not valid java name */
    public static final void m352podcastMediaMetadataCallback$lambda4(CarModePlayerViewController this$0, PodcastMediaMetadata podcastMediaMetadata) {
        Long durationSeconds;
        String artwork;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (podcastMediaMetadata != null && (artwork = podcastMediaMetadata.getArtwork()) != null) {
            Uri uri = Uri.parse(artwork);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
            if (uri != null) {
                this$0.podcastImageManager.setArtworkUri(uri);
            }
        }
        if (podcastMediaMetadata == null || (durationSeconds = podcastMediaMetadata.getDurationSeconds()) == null) {
            return;
        }
        this$0.mPlayerView.updateDuration(TimeUnit.SECONDS.toMillis(durationSeconds.longValue()));
    }

    private final void attachPodcastListeners() {
        this.podcastImageManager.addOnBitmapChangedListener(this.onPodcastBitmapChangedListener);
        CarModePresetsUtility.INSTANCE.addPodcastMediaMetadataCallback(this.podcastMediaMetadataCallback);
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onPlayPauseClicked() {
        if (isPodcastPlayerView()) {
            int playbackState = Podcast.getPlayback().getPlaybackState();
            if (playbackState == 2) {
                sendUiClickEvent(ActionType.PLAY);
            } else if (playbackState == 3) {
                sendUiClickEvent(ActionType.PAUSE);
            }
            Podcast.getPlayback().playPause();
            return;
        }
        super.onPlayPauseClicked();
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean onPrevClicked(InteractionType type) {
        if (isPodcastPlayerView()) {
            sendUiClickEvent(ActionType.SKIP_PREVIOUS);
            Podcast.getPlayback().previous();
            return true;
        }
        return super.onPrevClicked(type);
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public boolean onNextClicked(InteractionType type) {
        if (isPodcastPlayerView()) {
            sendUiClickEvent(ActionType.SKIP_NEXT);
            Podcast.getPlayback().next();
            return true;
        }
        return super.onNextClicked(type);
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
    public void onForwardClicked() {
        if (isPodcastPlayerView()) {
            sendUiClickEvent(ActionType.FAST_FORWARD);
            Podcast.getPlayback().fastForward();
        }
    }

    public static /* synthetic */ void refreshPlayerView$default(CarModePlayerViewController carModePlayerViewController, MediaItem mediaItem, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: refreshPlayerView");
        }
        if ((i & 1) != 0) {
            mediaItem = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        carModePlayerViewController.refreshPlayerView(mediaItem, z);
    }

    public final void refreshPlayerView(MediaItem mediaItem, boolean isPodcast) {
        if (isPodcast) {
            this.isPodcast = true;
            this.mPlayerView.updatePlaybackControl(this.podcastPlayerViewConfig);
            setPodcastPlayer();
        } else if (mediaItem != null) {
            this.isPodcast = false;
            this.mPlayerView.updatePlaybackControl(PlayerViewConfig.createPlayerViewConfig(PlayerViewConfig.PlayerViewContentType.fromMediaItem(mediaItem)));
        }
    }

    private final void setPodcastArtwork() {
        this.podcastMediaMetadataCallback.onPodcastMediaMetadataChange(CarModePresetsUtility.INSTANCE.getPodcastMediaMetadata());
    }

    private final void setPodcastPlayer() {
        setPodcastArtwork();
        this.mPlayerView.setPlayerState(CarModePresetsUtility.INSTANCE.convertPlaybackState(Podcast.getPlayback().getPlaybackState()));
        this.mPlayerView.hasPrevTrack(true);
        this.mPlayerView.hasNextTrack(true);
    }

    protected final boolean isPodcastPlayerView() {
        PlayerViewConfig playerViewConfig = this.mPlayerView.getPlayerViewConfig();
        return playerViewConfig != null && playerViewConfig.isPodcastView();
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController
    public void refreshPlayer() {
        super.refreshPlayer();
        setPodcastArtwork();
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController
    public Observer<List<PlayerViewModel.PlayerContentViewModel>> getPlayerContentViewModelsObserver() {
        return new Observer() { // from class: com.amazon.mp3.auto.carmode.view.CarModePlayerViewController$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarModePlayerViewController.m350getPlayerContentViewModelsObserver$lambda6(this.f$0, (List) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getPlayerContentViewModelsObserver$lambda-6, reason: not valid java name */
    public static final void m350getPlayerContentViewModelsObserver$lambda6(CarModePlayerViewController this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            this$0.updatePlayerView();
        }
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController
    protected boolean skipObserverUpdate() {
        return !this.isPodcastHarleyEnabled && this.isPodcast;
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController
    protected ExperienceMode getCurrentExperienceMode() {
        return ExperienceMode.CAR_MODE;
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController
    /* JADX INFO: renamed from: getCurrentPageType, reason: from getter */
    protected PageType getPageType() {
        return this.pageType;
    }

    @Override // com.amazon.mp3.playback.view.MiniPlayerViewController
    public void clear() {
        super.clear();
        this.podcastImageManager.removeOnBitmapChangedListener(this.onPodcastBitmapChangedListener);
        CarModePresetsUtility.INSTANCE.removePodcastMediaMetadataCallback(this.podcastMediaMetadataCallback);
    }

    public final void sendUiClickEvent(ActionType actionType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        AutoUiEventUtil.sendUiClickEvent$default(actionType, this.pageType, ExperienceMode.CAR_MODE, null, 8, null);
    }
}
